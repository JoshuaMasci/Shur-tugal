package Shurtugal.common.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Shurtugal.ShurtugalMod;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;

public class ItemDiamondShears extends ItemShears
{

	public ItemDiamondShears(int par1)
	{
		super(par1);
		this.setCreativeTab(ShurtugalMod.tabShurtugal);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		this.itemIcon = par1IconRegister.registerIcon("shurtugal:diamondshears");
	}

}
