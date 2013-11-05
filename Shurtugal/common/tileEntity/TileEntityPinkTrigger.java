package Shurtugal.common.tileEntity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;

public class TileEntityPinkTrigger extends TileEntity
{

	@Override
	public void updateEntity()
	{

		if (!this.worldObj.isRemote)
		{
			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(xCoord - 5, yCoord - 2, zCoord - 5, xCoord + 5, yCoord + 6, zCoord + 5);
			List<Entity> entities = worldObj.getEntitiesWithinAABB(Entity.class, bb);
			if (entities.size() > 0)
			{
				for (Entity entity : entities)
				{
					if (entity instanceof EntitySheep)
					{
						EntitySheep sheep = (EntitySheep) entity;

						if (sheep.getFleeceColor() == 6 && sheep.deathTime > 5)
						{
							spawnegg();
						}
					}
				}
			}
		}

	}

	public void spawnegg()
	{
		worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		worldObj.spawnParticle("hugeexplosion", xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
		worldObj.setBlock(xCoord, yCoord, zCoord, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, 2);
		worldObj.setBlock(xCoord, yCoord + 1, zCoord, ShurtugalMod.blockHandler.Egg.blockID, Colors.Pink, 2);
		this.invalidate();
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
	}

}
