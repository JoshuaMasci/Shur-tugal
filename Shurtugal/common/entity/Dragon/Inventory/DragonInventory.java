package Shurtugal.common.entity.Dragon.Inventory;

import java.util.Random;

import cpw.mods.fml.common.network.PacketDispatcher;

import Shurtugal.ShurtugalMod;
import Shurtugal.common.Item.ItemDragonArmor;
import Shurtugal.common.Network.DragonInventoryPacket;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class DragonInventory implements IInventory {
	public ItemStack[] mainInventory = new ItemStack[33];

	public EntityTameableDragon dragon;

	public DragonInventory(EntityTameableDragon entity) {
		dragon = entity;

	}

	public void OpenGui(EntityPlayer player) {
		if (dragon.isSaddled() != 0) {
			player.openGui(ShurtugalMod.instance, dragon.isSaddled(),
					this.dragon.worldObj, (int) dragon.posX, (int) dragon.posY,
					(int) dragon.posZ);
		}
	}

	public void DropAllItems(boolean dropArmor) {
		if (this.dragon.worldObj.isRemote) {
			return;
		}
		Random random = new Random();
		if (mainInventory != null) {
			int length = mainInventory.length;
			if (!dropArmor) {
				length = 27;
			}
			for (int var8 = 0; var8 < length; ++var8) {
				ItemStack var9 = mainInventory[var8];

				if (var9 != null) {
					float var10 = random.nextFloat() * 0.8F + 0.1F;
					float var11 = random.nextFloat() * 0.8F + 0.1F;
					EntityItem var14;

					for (float var12 = random.nextFloat() * 0.8F + 0.1F; var9.stackSize > 0; dragon.worldObj
							.spawnEntityInWorld(var14)) {
						int var13 = random.nextInt(21) + 10;

						if (var13 > var9.stackSize) {
							var13 = var9.stackSize;
						}

						var9.stackSize -= var13;
						var14 = new EntityItem(dragon.worldObj,
								(double) ((float) dragon.posX + var10),
								(double) ((float) dragon.posY + var11),
								(double) ((float) dragon.posZ + var12),
								new ItemStack(var9.itemID, var13,
										var9.getItemDamage()));
						float var15 = 0.05F;
						var14.motionX = (double) ((float) random.nextGaussian() * var15);
						var14.motionY = (double) ((float) random.nextGaussian()
								* var15 + 0.2F);
						var14.motionZ = (double) ((float) random.nextGaussian() * var15);

						if (var9.hasTagCompound()) {
							var14.getEntityItem().setTagCompound(
									(NBTTagCompound) var9.getTagCompound()
											.copy());
						}
						mainInventory[var8] = null;
					}
				}
			}
		}

	}

	public ItemStack getArmor(int Slot) {
		return mainInventory[27 + Slot];
	}

	@Override
	public int getSizeInventory() {
		return mainInventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		if (var1 < mainInventory.length) {
			return mainInventory[var1];
		}
		return null;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		if (this.mainInventory[var1] != null) {
			ItemStack var3;

			if (this.mainInventory[var1].stackSize <= var2) {
				var3 = this.mainInventory[var1];
				this.mainInventory[var1] = null;
				return var3;
			} else {
				var3 = this.mainInventory[var1].splitStack(var2);

				if (this.mainInventory[var1].stackSize == 0) {
					this.mainInventory[var1] = null;
				}

				return var3;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		if (this.mainInventory[var1] != null) {
			ItemStack var2 = this.mainInventory[var1];
			this.mainInventory[var1] = null;
			return var2;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		this.mainInventory[var1] = var2;

		if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
			var2.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return "Shurtugal.DragonGui";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void onInventoryChanged() {

	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return dragon.getOwnerName() == var1.username;
	}

	@Override
	public void openChest() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeChest() {

	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < mainInventory.length; i++) {
			if (mainInventory[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				mainInventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbttagcompound.setTag("Items", nbttaglist);
	}

	/**
	 * Reads from the given tag list and fills the slots in the inventory with
	 * the correct items.
	 */
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
		mainInventory = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++) {
			NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist
					.tagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 0xff;
			if (j >= 0 && j < mainInventory.length) {
				mainInventory[j] = ItemStack
						.loadItemStackFromNBT(nbttagcompound1);
			}
		}
	}

	public void damageArmor(int par1) {
		par1 /= 4;

		if (par1 < 1) {
			par1 = 1;
		}

		for (int var2 = 0; var2 < this.getarmorInventory().length; ++var2) {
			if (this.getarmorInventory()[var2] != null
					&& this.getarmorInventory()[var2].getItem() instanceof ItemDragonArmor) {
				this.getarmorInventory()[var2].damageItem(par1, this.dragon);

				if (this.getarmorInventory()[var2].stackSize == 0) {
					this.getarmorInventory()[var2] = null;
				}
			}
		}
	}

	public ItemStack[] getarmorInventory() {
		ItemStack[] stack = new ItemStack[4];
		for (int i = 0; i < 4; i++) {
			stack[i] = this.mainInventory[27 + i];
		}
		return stack;
	}

	public int getTotalArmorValue() {
		int var1 = 0;

		for (int var2 = 0; var2 < this.getarmorInventory().length; ++var2) {
			if (this.getarmorInventory()[var2] != null
					&& this.getarmorInventory()[var2].getItem() instanceof ItemDragonArmor) {
				int var3 = ((ItemDragonArmor) this.getarmorInventory()[var2]
						.getItem()).damageReduceAmount;
				var1 += var3;
			}
		}

		return var1;
	}

	@Override
	public boolean isInvNameLocalized() 
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		return false;
	}
}
