package Shurtugal.common.Crafting;

import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.Achievement.ShurtugalAchievement;
import Shurtugal.common.Item.ItemHandler;
import cpw.mods.fml.common.ICraftingHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftingHandler implements ICraftingHandler
{
	public void AddCrafting()
	{
		GameRegistry.addRecipe(new RecipesDragonArmorDyes());

		// recipes
		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.helmetSteel, 1), new Object[]
		{ "@@@", "@ @", Character.valueOf('@'),  new ItemStack(ShurtugalMod.itemHandeler.steelIngot) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.plateSteel, 1), new Object[]
		{ "@ @", "@@@", "@@@", Character.valueOf('@'),  new ItemStack(ShurtugalMod.itemHandeler.steelIngot) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.legsSteel, 1), new Object[]
		{ "@@@", "@ @", "@ @", Character.valueOf('@'),  new ItemStack(ShurtugalMod.itemHandeler.steelIngot) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.bootsSteel, 1), new Object[]
		{ "@ @", "@ @", Character.valueOf('@'),  new ItemStack(ShurtugalMod.itemHandeler.steelIngot) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.blockHandler.IronFurnaceIdle, 1), new Object[]
		{ "@@@", "@ @", "@@@", Character.valueOf('@'), Item.ingotIron });

		GameRegistry.addRecipe(new ItemStack(ItemHandler.denseLeather), new Object[]
		{ "@@@", "@@@", "@@@", Character.valueOf('@'), Item.leather });
		
		GameRegistry.addShapelessRecipe(new ItemStack(Item.leather, 9, 0), new ItemStack(ItemHandler.denseLeather));
		
		// GameRegistry.addRecipe(new ItemStack(Thatch, 6), new Object[] {
		// "@@@", "@@@", "@@@", Character.valueOf('@'), Item.wheat});

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.steelSword, 1), new Object[]
		{ "@", "@", "|", Character.valueOf('@'),  new ItemStack(ShurtugalMod.itemHandeler.steelIngot), Character.valueOf('|'), Item.stick });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.steelSpade, 1), new Object[]
		{ "@", "|", "|", Character.valueOf('@'),  new ItemStack(ShurtugalMod.itemHandeler.steelIngot), Character.valueOf('|'), Item.stick });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.steelPickaxe, 1), new Object[]
		{ "@@@", " | ", " | ", Character.valueOf('@'),  new ItemStack(ShurtugalMod.itemHandeler.steelIngot), Character.valueOf('|'), Item.stick });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.steelAxe, 1), new Object[]
		{ "XX", "X#", " #", Character.valueOf('X'),  new ItemStack(ShurtugalMod.itemHandeler.steelIngot), Character.valueOf('#'), Item.stick });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.steelHoe, 1), new Object[]
		{ "@@", " |", " |", Character.valueOf('@'),  new ItemStack(ShurtugalMod.itemHandeler.steelIngot), Character.valueOf('|'), Item.stick });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.DragonBlade, 1, Colors.Black), new Object[]
		{ "xx?", "x?x", "DSx", Character.valueOf('?'),  new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), Character.valueOf('D'), Item.diamond, Character.valueOf('S'),
				new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Colors.Black) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.DragonBlade, 1, Colors.Blue), new Object[]
		{ "xx?", "x?x", "DSx", Character.valueOf('?'),  new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), Character.valueOf('D'), Item.diamond, Character.valueOf('S'),
				new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Colors.Blue) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.DragonBlade, 1, Colors.Brown), new Object[]
		{ "xx?", "x?x", "DSx", Character.valueOf('?'),  new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), Character.valueOf('D'), Item.diamond, Character.valueOf('S'),
				new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Colors.Brown) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.DragonBlade, 1, Colors.Green), new Object[]
		{ "xx?", "x?x", "DSx", Character.valueOf('?'),  new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), Character.valueOf('D'), Item.diamond, Character.valueOf('S'),
				new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Colors.Green) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.DragonBlade, 1, Colors.Pink), new Object[]
		{ "xx?", "x?x", "DSx", Character.valueOf('?'),  new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), Character.valueOf('D'), Item.diamond, Character.valueOf('S'),
				new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Colors.Pink) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.DragonBlade, 1, Colors.Purple), new Object[]
		{ "xx?", "x?x", "DSx", Character.valueOf('?'),  new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), Character.valueOf('D'), Item.diamond, Character.valueOf('S'),
				new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Colors.Purple) });
		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.DragonBlade, 1, Colors.Red), new Object[]
		{ "xx?", "x?x", "DSx", Character.valueOf('?'),  new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), Character.valueOf('D'), Item.diamond, Character.valueOf('S'),
				new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Colors.Red) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.DragonBlade, 1, Colors.White), new Object[]
		{ "xx?", "x?x", "DSx", Character.valueOf('?'),  new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), Character.valueOf('D'), Item.diamond, Character.valueOf('S'),
				new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Colors.White) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.DragonBlade, 1, Colors.Yellow), new Object[]
		{ "xx?", "x?x", "DSx", Character.valueOf('?'), new ItemStack(ShurtugalMod.itemHandeler.brightsteelIngot), Character.valueOf('D'), Item.diamond, Character.valueOf('S'),
				new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Colors.Yellow) });

		GameRegistry.addRecipe(new ItemStack(ShurtugalMod.itemHandeler.dragonShears, 1), new Object[]
				{" #", "# ", '#', Item.diamond});

		GameRegistry.addShapelessRecipe(new ItemStack(ShurtugalMod.itemHandeler.SaddleBag, 1), new Object[]
		{ Item.saddle, Block.chest });
		
		this.registerDragonArmor(Block.blockIron,new ItemStack[] {new ItemStack(ItemHandler.helmetIronDragon), new ItemStack(ItemHandler.plateIronDragon),new ItemStack(ItemHandler.legsIronDragon), new ItemStack(ItemHandler.tailIronDragon)});
		this.registerDragonArmor(Block.blockDiamond,new ItemStack[] {new ItemStack(ItemHandler.helmetDiamondDragon), new ItemStack(ItemHandler.plateDiamondDragon),new ItemStack(ItemHandler.legsDiamondDragon), new ItemStack(ItemHandler.tailDiamondDragon)});
		this.registerDragonArmor(Block.blockGold,new ItemStack[] {new ItemStack(ItemHandler.helmetGoldDragon), new ItemStack(ItemHandler.plateGoldDragon),new ItemStack(ItemHandler.legsGoldDragon), new ItemStack(ItemHandler.tailGoldDragon)});
		this.registerDragonArmor(new ItemStack(ItemHandler.denseLeather),new ItemStack[] {new ItemStack(ItemHandler.helmetLeatherDragon), new ItemStack(ItemHandler.plateLeatherDragon),new ItemStack(ItemHandler.legsLeatherDragon), new ItemStack(ItemHandler.tailLeatherDragon)});

	}
	
	public void registerDragonArmor(Object material,ItemStack armor[])
	{
		//Head
		GameRegistry.addShapedRecipe(armor[0], new Object[] {"XXX","X X", Character.valueOf('X'), material});
		//Body
		GameRegistry.addShapedRecipe(armor[1], new Object[] {"X X","XXX","XXX", Character.valueOf('X'), material});
		//Legs
		GameRegistry.addShapedRecipe(armor[2], new Object[] {"X X","X X", Character.valueOf('X'), material});
		//Tail
		GameRegistry.addShapedRecipe(armor[3], new Object[] {"XX","XX","XX", Character.valueOf('X'), material});	
	}
	
	@Override
	public void onCrafting(EntityPlayer player, ItemStack item, IInventory craftMatrix)
	{
	if (item.itemID == ShurtugalMod.itemHandeler.DragonBlade.itemID)
	{
		//player.addStat(ShurtugalAchievement.Smith, 1);
	}
	else if (item.itemID == ShurtugalMod.blockHandler.IronFurnaceIdle.blockID)
	{
		//player.addStat(ShurtugalAchievement.Smith, 1);
	}
	else if (item.itemID == ShurtugalMod.itemHandeler.SaddleBag.itemID)
	{
		//player.addStat(ShurtugalAchievement.Saddlebag, 1);
	}
	}

	@Override
	public void onSmelting(EntityPlayer player, ItemStack item)
	{
		if (item.itemID == ShurtugalMod.blockHandler.Egg.blockID)
		{
			//player.addStat(ShurtugalAchievement.Rider, 1);
		}
	}
	
}
