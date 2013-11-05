package Shurtugal.common.entity.Dragon.Inventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.Item.ItemDragonArmor;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class SlotDragonArmor extends Slot
{

	private int armorType;
	public static final String[] field_94603_a = new String[] {"slot_empty_helmet", "slot_empty_chestplate", "slot_empty_leggings", "slot_empty_tail"};
	
	public SlotDragonArmor(IInventory par1, int par2, int par3, int par4, int type)
	{
		super(par1, par2, par3, par4);
		this.armorType = type;
	}

	public int getSlotStackLimit()
	{
		return 1;
	}

	/**
	 * Check if the stack is a valid item for this slot. Always true beside for
	 * the armor slots.
	 */
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		return par1ItemStack == null ? false : (par1ItemStack.getItem() instanceof ItemDragonArmor && ((ItemDragonArmor) par1ItemStack.getItem()).armorType == this.armorType);
	}
    @SideOnly(Side.CLIENT)

    /**
     * Returns the icon index on items.png that is used as background image of the slot.
     */
    public Icon getBackgroundIconIndex()
    {
        return ItemDragonArmor.getbackgroundIcon(this.armorType);
    }

}
