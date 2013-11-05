package Shurtugal.common.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemModSword extends ItemSword
{

	private String Material = "";
	public ItemModSword(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
		this.Material = par2EnumToolMaterial.name();
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		String str = "shurtugal:";
		String name = "sword" + this.Material;
		str += name;
		this.itemIcon = par1IconRegister.registerIcon(str);
	}
}
