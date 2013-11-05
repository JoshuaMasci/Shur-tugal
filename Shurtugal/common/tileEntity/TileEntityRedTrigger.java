package Shurtugal.common.tileEntity;

import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.block.BlockHandler;

public class TileEntityRedTrigger extends TileEntity
{
	private int item1Amount = 0;
	private int item2Amount = 0;
	private int item3Amount = 0;

	@Override
	public void updateEntity()
	{

		if (!this.worldObj.isRemote)
		{
			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord - 2, yCoord - 1, zCoord - 2, xCoord + 2, yCoord + 1, zCoord + 2);
			List<Entity> entities = worldObj.getEntitiesWithinAABB(Entity.class, bb);
			if (entities.size() > 0)
			{
				for (Entity entity : entities)
				{
					if (entity instanceof EntityItem)
					{

						ItemStack item = ((EntityItem) entity).getEntityItem();
						if (item.itemID == Item.blazeRod.itemID)
						{
							item1Amount += item.stackSize;
						}
						else if (item.itemID == Item.magmaCream.itemID)
						{
							item2Amount += item.stackSize;
						}
						else if (item.itemID == Item.netherStalkSeeds.itemID)
						{
							item3Amount += item.stackSize;
						}
						entity.setDead();

					}
				}
			}
		}
		if (item3Amount >= 1 && item2Amount >= 3 && item1Amount >= 5)
		{
			spawnegg();
		}

	}

	public void spawnegg()
	{
		worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		worldObj.setBlock(xCoord, yCoord, zCoord, ShurtugalMod.blockHandler.Brick.blockID, Colors.Red, 2);
		worldObj.setBlock(xCoord, yCoord + 1, zCoord, ShurtugalMod.blockHandler.Egg.blockID, Colors.Red, 2);
		this.invalidate();
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		item1Amount = nbt.getInteger("item1Amount");
		item2Amount = nbt.getInteger("item2Amount");
		item3Amount = nbt.getInteger("item3Amount");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setInteger("item1Amount", item1Amount);
		nbt.setInteger("item2Amount", item2Amount);
		nbt.setInteger("item3Amount", item3Amount);
	}

}
