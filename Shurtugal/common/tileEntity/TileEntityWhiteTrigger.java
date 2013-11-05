package Shurtugal.common.tileEntity;

import java.util.List;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;

import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;

public class TileEntityWhiteTrigger extends TileEntity
{

	public void spawnegg()
	{
		worldObj.playSoundEffect(xCoord, yCoord, zCoord, "random.explode", 4.0F, (1.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F) * 0.7F);
		worldObj.spawnParticle("hugeexplosion", xCoord, yCoord, zCoord, 0.0D, 0.0D, 0.0D);
		worldObj.setBlock(xCoord, yCoord, zCoord, ShurtugalMod.blockHandler.Brick.blockID, Colors.White, 2);
		worldObj.setBlock(xCoord, yCoord + 1, zCoord, ShurtugalMod.blockHandler.Egg.blockID, Colors.White, 2);
		this.invalidate();
	}

}
