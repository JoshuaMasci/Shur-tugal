package Shurtugal.client.forge;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import Shurtugal.ShurtugalMod;
import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.Handlers.ConfigHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class TabShurtugal extends CreativeTabs
{
	public int time = 0;
	public int icon = 0;
	public List items = new ArrayList();

	public TabShurtugal(int i, String label)
	{
		super(i, label);
	}

	@Override
	public ItemStack getIconItemStack()
	{
		/*
		 * if(items.size() == 0) { this.addItemsToList(); } time++; if(time % 40
		 * == 0) { icon = new Random().nextInt(items.size()); } Object obj =
		 * items.get(icon); if(obj instanceof ItemStack) { return (ItemStack)
		 * obj; }
		 */
		return new ItemStack(ShurtugalMod.blockHandler.Egg, 1, Colors.Black);

	}

	/**
	 * Adds All Display Items to the list
	 */
	public void addItemsToList()
	{
		/*items.add(new ItemStack(ShurtugalMod.itemHandeler.steelIngot));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.brightSteelIngot));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.steelSword));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.steelAxe));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.steelPickaxe));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.steelSpade));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.steelHoe));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.dragonShears));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.helmetSteel));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.plateSteel));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.legsSteel));
		items.add(new ItemStack(ShurtugalMod.itemHandeler.bootsSteel));
		ShurtugalMod.itemHandeler.DragonBlade.getSubItems(ShurtugalMod.itemHandeler.DragonBlade.itemID, this, items);
		ShurtugalMod.itemHandeler.dragonScale.getSubItems(ShurtugalMod.itemHandeler.dragonScale.itemID, this, items);
		ShurtugalMod.blockHandler.Brick.getSubBlocks(1, this, items);
		ShurtugalMod.blockHandler.Egg.getSubBlocks(1, this, items);
		items.add(new ItemStack(ShurtugalMod.blockHandler.IronFurnaceIdle));
		items.add(new ItemStack(ShurtugalMod.blockHandler.brightSteelOre));*/
	}

	public String getTranslatedTabLabel()
	{
		return "Shurtugal";
	}

	@SideOnly(Side.CLIENT)
	/**
	 * only shows items which have tabToDisplayOn == this
	 */
	public void displayAllReleventItems(List par1List)
	{
		super.displayAllReleventItems(par1List);
		items = par1List;
	}
}
