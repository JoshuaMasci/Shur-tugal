/**
 * @Author Iamshortman
 */
package Shurtugal.common.IronFurnace;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;

public class IronFurnaceRecipes
{
	private static final IronFurnaceRecipes smeltingBase = new IronFurnaceRecipes();

	/** The list of smelting results. */
	private Map smeltingList = new HashMap();
	private Map experienceList = new HashMap();
	private HashMap<List<Integer>, ItemStack> metaSmeltingList = new HashMap<List<Integer>, ItemStack>();
	private HashMap<List<Integer>, Float> metaExperience = new HashMap<List<Integer>, Float>();

	/**
	 * Used to call methods addSmelting and getSmeltingResult.
	 */
	public static final IronFurnaceRecipes smelting()
	{
		return smeltingBase;
	}

	private IronFurnaceRecipes()
	{
		this.addSmelting(ShurtugalMod.blockHandler.brightSteelOre.blockID, new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), 1.0F);
		this.addSmelting(Item.ingotIron.itemID, new ItemStack(ShurtugalMod.itemHandeler.steelIngot), 0.7F);
		this.addSmelting(ShurtugalMod.blockHandler.Egg.blockID, new ItemStack(ShurtugalMod.blockHandler.Egg.blockID, 1, Colors.Orange), 1F);
	}

	/**
	 * Adds a smelting recipe.
	 */
	public void addSmelting(int par1, ItemStack par2ItemStack, float par3)
	{
		this.smeltingList.put(Integer.valueOf(par1), par2ItemStack);
		this.experienceList.put(Integer.valueOf(par2ItemStack.itemID), Float.valueOf(par3));
	}

	public Map getSmeltingList()
	{
		return this.smeltingList;
	}

	/**
	 * A metadata sensitive version of adding a furnace recipe.
	 */
	public void addSmelting(int itemID, int metadata, ItemStack itemstack, float experience)
	{
		metaSmeltingList.put(Arrays.asList(itemID, metadata), itemstack);
		metaExperience.put(Arrays.asList(itemID, metadata), experience);
	}

	/**
	 * Used to get the resulting ItemStack form a source ItemStack
	 * 
	 * @param item
	 *            The Source ItemStack
	 * @return The result ItemStack
	 */
	public ItemStack getSmeltingResult(ItemStack item)
	{
		if (item == null)
		{
			return null;
		}
		ItemStack ret = (ItemStack) metaSmeltingList.get(Arrays.asList(item.itemID, item.getItemDamage()));
		if (ret != null)
		{
			return ret;
		}
		ret = (ItemStack) smeltingList.get(Integer.valueOf(item.itemID));
		if (ret == null)
		{
			ret = FurnaceRecipes.smelting().getSmeltingResult(item);
		}
		return ret;
	}

	/**
	 * Grabs the amount of base experience for this item to give when pulled
	 * from the furnace slot.
	 */
	public float getExperience(ItemStack item)
	{
		if (item == null || item.getItem() == null)
		{
			return 0;
		}
		float ret = item.getItem().getSmeltingExperience(item);
		if (ret < 0 && metaExperience.containsKey(Arrays.asList(item.itemID, item.getItemDamage())))
		{
			ret = metaExperience.get(Arrays.asList(item.itemID, item.getItemDamage()));
		}
		if (ret < 0 && experienceList.containsKey(item.itemID))
		{
			ret = ((Float) experienceList.get(item.itemID)).floatValue();
		}
		if (ret < 0)
		{
			ret = FurnaceRecipes.smelting().getExperience(item);
		}
		return (ret < 0 ? 0 : ret);
	}
}
