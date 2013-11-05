package Shurtugal.common.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;

public class TileEntityBlackTrigger extends TileEntity
{
	public void updateEntity()
	{
		if (worldObj.isRemote)
		{
			return;
		}
		boolean Flag = false;
		if (worldObj.getBlockId(xCoord, yCoord, zCoord - 3) == Block.mobSpawner.blockID)
		{
			Flag = true;
		}
		if (worldObj.getBlockId(xCoord, yCoord, zCoord + 3) == Block.mobSpawner.blockID)
		{
			Flag = true;
		}

		if (worldObj.getBlockId(xCoord - 6, yCoord, zCoord - 3) == Block.mobSpawner.blockID)
		{
			Flag = true;
		}
		if (worldObj.getBlockId(xCoord - 6, yCoord, zCoord + 3) == Block.mobSpawner.blockID)
		{
			Flag = true;
		}
		if (worldObj.getBlockId(xCoord + 6, yCoord, zCoord - 3) == Block.mobSpawner.blockID)
		{
			Flag = true;
		}
		if (worldObj.getBlockId(xCoord + 6, yCoord, zCoord + 3) == Block.mobSpawner.blockID)
		{
			Flag = true;
		}

		if (!Flag)
		{
			spawnEgg();
		}

	}

	public void spawnEgg()
	{
		worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		worldObj.spawnParticle("hugeexplosion", xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
		worldObj.setBlock(xCoord, yCoord, zCoord, ShurtugalMod.blockHandler.Brick.blockID, Colors.Black, 2);
		worldObj.setBlock(xCoord, yCoord + 1, zCoord, ShurtugalMod.blockHandler.Egg.blockID, Colors.Black, 2);
		this.invalidate();
	}
}
