package Shurtugal.common.Item.Tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

public class ItemModHoe extends ItemHoe
{

	public ItemModHoe(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		String str = "shurtugal:";
		String name = "hoe" + this.theToolMaterial.name();
		str += name;
		this.itemIcon = par1IconRegister.registerIcon(str);
	}

}
