package Shurtugal.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

public class BlockBrightSteelOre extends Block
{

	public BlockBrightSteelOre(int par1, Material par2Material)
	{
		super(par1, par2Material);
	}

	public void registerIcons(IconRegister par1IconRegister)
	{
		this.blockIcon = par1IconRegister.registerIcon("shurtugal:brightsteelore");
	}
}
