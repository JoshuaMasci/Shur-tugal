package Shurtugal.common.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import Shurtugal.Colors;

public class ItemBlockEgg extends ItemBlock
{

	public ItemBlockEgg(int id)
	{
		super(id);
		setHasSubtypes(true);
		this.setUnlocalizedName("Egg");
	}

	@Override
	public int getMetadata(int damageValue)
	{
		return damageValue;
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack)
	{
		return Colors.ColorNames[itemstack.getItemDamage()] + " Egg";
	}

}
