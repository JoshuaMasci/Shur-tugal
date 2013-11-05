package Shurtugal.common.tileEntity;

import java.util.List;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;

public class TileEntityPurpleTrigger extends TileEntity
{
	public int item1Amount = 0;

	@Override
	public void updateEntity()
	{
		TileEntityChest chest = null;
		if (!this.worldObj.isRemote)
		{
			TileEntity tile = worldObj.getBlockTileEntity(xCoord, yCoord + 1, zCoord + -1);
			if (tile != null && tile instanceof TileEntityChest)
			{
				chest = (TileEntityChest) tile;
				for (int i = 0; i < chest.getSizeInventory(); i++)
				{
					ItemStack item = chest.getStackInSlot(i);
					if (item != null && item.itemID == Item.diamond.itemID)
					{
						chest.decrStackSize(i, 1);
						item1Amount += 36;
					}
					if (item != null && item.itemID == Item.ingotGold.itemID)
					{
						chest.decrStackSize(i, 1);
						item1Amount += 9;
					}
					if (item != null && item.itemID == Item.goldNugget.itemID)
					{
						chest.decrStackSize(i, 1);
						item1Amount += 1;
					}
					if (item != null && item.itemID == Block.blockGold.blockID)
					{
						chest.decrStackSize(i, 1);
						item1Amount += 81;
					}
					if (item != null && item.itemID == Block.blockDiamond.blockID)
					{
						chest.decrStackSize(i, 1);
						item1Amount += 324;
					}
				}
			}
			if (item1Amount >= 300)
			{
				spawnegg();
			}
		}
	}

	public void spawnegg()
	{
		worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		worldObj.spawnParticle("hugeexplosion", xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
		worldObj.setBlock(xCoord, yCoord, zCoord, ShurtugalMod.blockHandler.Brick.blockID, Colors.Purple, 2);
		worldObj.setBlock(xCoord, yCoord + 1, zCoord, ShurtugalMod.blockHandler.Egg.blockID, Colors.Purple, 2);
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
