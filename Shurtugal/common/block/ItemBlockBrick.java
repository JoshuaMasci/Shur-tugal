package Shurtugal.common.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import Shurtugal.Colors;

public class ItemBlockBrick extends ItemBlock
{

	public ItemBlockBrick(int id)
	{
		super(id);
		setHasSubtypes(true);
		this.setUnlocalizedName("Brick");
	}

	@Override
	public int getMetadata(int damageValue)
	{
		return damageValue;
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack)
	{
		return Colors.ColorNames[itemstack.getItemDamage()] + " Brick";
	}

}
