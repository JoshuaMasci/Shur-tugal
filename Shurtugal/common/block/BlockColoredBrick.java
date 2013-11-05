package Shurtugal.common.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.client.forge.ClientProxy;
import Shurtugal.common.Handlers.ConfigHandler;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.Icon;

public class BlockColoredBrick extends Block
{

	public Icon[] icons;

	public BlockColoredBrick(int par1)
	{
		super(par1, Material.rock);
		this.setCreativeTab(ShurtugalMod.tabShurtugal);
	}

	@Override
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	@Override
	public Icon getIcon(int par1, int par2)
	{
		return icons[par2];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons = new Icon[Colors.ColorNames.length];
		for (int i = 0; i < Colors.ColorNames.length; i++)
		{
			String str = Colors.ColorNames[i].toLowerCase() + "brick";
			str = "shurtugal:brick/" + str;
			icons[i] = par1IconRegister.registerIcon(str);
		}

	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems)
	{
		for (int ix = 0; ix < Colors.ColorNames.length; ix++)
		{
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

}
