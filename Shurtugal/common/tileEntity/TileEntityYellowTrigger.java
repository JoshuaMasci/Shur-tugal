package Shurtugal.common.tileEntity;

import java.util.List;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityYellowTrigger extends TileEntity
{
	public int item1Amount = 0;

	@Override
	public void updateEntity()
	{
		TileEntityChest chest = null;
		if (!this.worldObj.isRemote)
		{
			TileEntity tile = worldObj.getBlockTileEntity(xCoord + -1, yCoord + 1, zCoord + -3);
			if (tile != null && tile instanceof TileEntityChest)
			{
				chest = (TileEntityChest) tile;
				for (int i = 0; i < chest.getSizeInventory(); i++)
				{
					ItemStack item = chest.getStackInSlot(i);
					if (item != null && item.itemID == Block.cloth.blockID && item.getItemDamage() != 0)
					{
						int Needleft = 40 - item1Amount;
						if (Needleft > 0)
						{
							if (item.stackSize > Needleft)
							{
								item1Amount += Needleft;
								item.stackSize -= Needleft;
							}
							else
							{
								item1Amount += item.stackSize;
								chest.decrStackSize(i, item.stackSize);
							}
						}

					}

				}
			}
			if (item1Amount == 40)
			{
				spawnegg();
			}
		}
	}

	public void spawnegg()
	{
		ShurtugalMod.proxy.sendParticleSpawnPacket(worldObj, "hugeexplosion", xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
		worldObj.playSoundEffect(xCoord, yCoord, zCoord, "shurtugal:Green", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		worldObj.spawnParticle("hugeexplosion", xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
		worldObj.setBlock(xCoord, yCoord, zCoord, ShurtugalMod.blockHandler.Brick.blockID, Colors.Yellow, 2);
		worldObj.setBlock(xCoord, yCoord + 1, zCoord, ShurtugalMod.blockHandler.Egg.blockID, Colors.Yellow, 2);
		this.invalidate();
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		item1Amount = nbt.getInteger("item1Amount");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setInteger("item1Amount", item1Amount);
	}

}
