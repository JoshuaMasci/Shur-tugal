package Shurtugal.common.Item.Tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class ItemModPickaxe extends ItemPickaxe
{

	public ItemModPickaxe(int par1, EnumToolMaterial par2EnumToolMaterial)
	{
		super(par1, par2EnumToolMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		String str = "shurtugal:";
		String name = "pick" + this.toolMaterial.name();
		str += name;
		this.itemIcon = par1IconRegister.registerIcon(str);
	}
}
