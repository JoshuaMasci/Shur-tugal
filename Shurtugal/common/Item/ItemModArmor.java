package Shurtugal.common.Item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Shurtugal.ShurtugalMod;
import Shurtugal.client.forge.ClientProxy;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemModArmor extends ItemArmor
{

	public ItemModArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4)
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		this.setCreativeTab(ShurtugalMod.tabShurtugal);
	}

	private static final String[] field_94606_cu = new String[]
	{ "helmet", "chestplate", "leggings", "boots" };

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		String str = "shurtugal:armor/";
		String name = field_94606_cu[this.armorType] + this.getArmorMaterial().name();
		str += name;
		this.itemIcon = par1IconRegister.registerIcon(str);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
	{
		if (armorType == 0 || armorType == 1 || armorType == 3)
		{
			return "/mods/Shurtugal/textures/armor/steel_1.png";
		}
		else
		{
			return "/mods/Shurtugal/textures/armor/steel_2.png";
		}
	}
}
