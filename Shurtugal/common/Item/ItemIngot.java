package Shurtugal.common.Item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemIngot extends Item
{
	
	public ItemIngot(int par1)
	{
		super(par1);
		this.setCreativeTab(ShurtugalMod.tabShurtugal);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		String temp = this.getUnlocalizedName().substring(5);
		this.itemIcon = par1IconRegister.registerIcon("shurtugal:" + temp.toLowerCase());
	}
}
