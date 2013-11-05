package Shurtugal.common.entity.Dragon.Inventory;

import Shurtugal.common.Item.ItemDragonArmor;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import Shurtugal.client.gui.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerDragon extends Container
{
	private final EntityPlayer thePlayer;

	public ContainerDragon(EntityTameableDragon dragon, EntityPlayer player)
	{
		thePlayer = player;
		DragonInventory Inventory = dragon.inventory;

		InventoryPlayer par1InventoryPlayer = thePlayer.inventory;
		int var4;
		int var5;

		// Adds the 4 armor slots 0-3
		for (var4 = 0; var4 < 4; ++var4)
		{
			this.addSlotToContainer(new SlotDragonArmor(Inventory, var4 + 27, 8, 5 + var4 * 18, var4));
		}

		// Players hot bar 4-12
		for (var4 = 0; var4 < 9; ++var4)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var4, 8 + var4 * 18, 150));
		}

		// Players Inventory 13-39
		for (var4 = 0; var4 < 3; ++var4)
		{
			for (var5 = 0; var5 < 9; ++var5)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var5 + var4 * 9 + 9, 8 + var5 * 18, 82 + var4 * 18));
			}
		}

	}

	/**
	 * Called when a player shift-clicks on a slot. You must override this or
	 * you will crash when someone does that.
	 */
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = null;
		Slot slotClick = (Slot) this.inventorySlots.get(par2);

		// if the slot has any items
		if (slotClick.getHasStack())
		{
			// gets ItemStack and Item
			ItemStack stack = slotClick.getStack();
			Item item = stack.getItem();

			// if its not the armor inventory
			if (par2 > 3)
			{
				// if it is Dragon Armor
				if (item instanceof ItemDragonArmor)
				{
					// get armor type
					int armorType = ((ItemDragonArmor) item).armorType;
					// get correct armor slot
					Slot slot = (Slot) this.inventorySlots.get(armorType);

					// checks if the slot is empty
					if (!slot.getHasStack())
					{
						// adds stack to slot
						slot.putStack(stack);

						// clears orginal stack
						slotClick.putStack(null);
						return null;
					}
				}
			}

		}

		return var3;
	}

	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return true;
	}

}
