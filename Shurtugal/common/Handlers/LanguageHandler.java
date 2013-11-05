package Shurtugal.common.Handlers;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.LanguageRegistry;
import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.Item.ItemHandler;
import Shurtugal.common.block.BlockHandler;
import Shurtugal.common.block.ItemBlockBrick;

public class LanguageHandler
{

	public void RegisterNames()
	{
		this.RegisterBlocks();
		this.RegisterItems();

		LanguageRegistry lr = LanguageRegistry.instance();
		lr.addStringLocalization("entity.PlayerDragon.name", "Dragon");
		lr.addStringLocalization("shurtugal.owned", "You do not own this Dragon.");
		lr.addStringLocalization("entity.EvilDragon.name", "Mother Dragon");
		lr.addStringLocalization("commands.Shurtugal.usage", "/shurtugal <lifestage| call | despawn>");
		lr.addStringLocalization("commands.Shurtugal.usageNoOp", "/shurtugal <call | despawn>");	
		lr.addStringLocalization("commands.Shurtugal.lifestage.usage", "/shurtugal lifestage <baby | teen | adult | elder>");
		lr.addStringLocalization("commands.Shurtugal.notRider", "You are not a Dragon Rider, you can not use this command.");
		lr.addStringLocalization("commands.Shurtugal.notSpawn", "Your dragon is not around to use this command.");
		lr.addStringLocalization("commands.Shurtugal.notUse", "You can't use this command.");
		lr.addStringLocalization("Shurtugal.alreadyRider", "You already have a dragon.");
		
		lr.addStringLocalization("Shurtugal.lifeStage.hatchling", "hatchling");
		lr.addStringLocalization("Shurtugal.lifeStage.juvenile", "juvenile");
		lr.addStringLocalization("Shurtugal.lifeStage.adult", "adult");
		lr.addStringLocalization("Shurtugal.lifeStage.elder", "elder");
		lr.addStringLocalization("container.Ironfurnace", "Iron Furnace");
		lr.addStringLocalization("Shurtugal.DragonGui", "Dragon Inventory");
		lr.addStringLocalization("Shurtugal.SaddleBag", "Saddlebag");
	}

	private void RegisterItems()
	{
		// names items
		//LanguageRegistry.addName(ShurtugalMod.itemHandeler.helmetBrightSteel, "BrightSteel Helmet");
		//LanguageRegistry.addName(ShurtugalMod.itemHandeler.plateBrightSteel, "BrightSteel ChestPlate");
		//LanguageRegistry.addName(ShurtugalMod.itemHandeler.legsBrightSteel, "BrightSteel Leggings");
		//LanguageRegistry.addName(ShurtugalMod.itemHandeler.bootsBrightSteel, "BrightSteel Boots");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.helmetSteel, "Steel Helmet");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.plateSteel, "Steel Chestplate");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.legsSteel, "Steel Leggings");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.bootsSteel, "Steel Boots");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.steelSword, "Steel Sword");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.steelSpade, "Steel Spade");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.steelPickaxe, "Steel Pickaxe");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.steelAxe, "Steel Axe");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.steelHoe, "Steel Hoe");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.dragonShears, "Diamond Shears");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.SaddleBag, "SaddleBag");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.hammer, "Roran's Hammer");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.steelIngot, "Steel Ingot");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.brightsteelIngot, "Brightsteel Ingot");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.denseLeather, "Dense Leather");
		
		// Dragon Armor Start
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.helmetLeatherDragon, "Leather Dragon Helmet");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.plateLeatherDragon, "Leather Dragon Chestplate");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.legsLeatherDragon, "Leather Dragon Legs");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.tailLeatherDragon, "Leather Dragon Tail");

		LanguageRegistry.addName(ShurtugalMod.itemHandeler.helmetIronDragon, "Iron Dragon Helmet");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.plateIronDragon, "Iron Dragon Chestplate");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.legsIronDragon, "Iron Dragon Legs");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.tailIronDragon, "Iron Dragon Tail");

		LanguageRegistry.addName(ShurtugalMod.itemHandeler.helmetGoldDragon, "Gold Dragon Helmet");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.plateGoldDragon, "Gold Dragon Chestplate");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.legsGoldDragon, "Gold Dragon Legs");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.tailGoldDragon, "Gold Dragon Tail");

		LanguageRegistry.addName(ShurtugalMod.itemHandeler.helmetDiamondDragon, "Diamond Dragon Helmet");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.plateDiamondDragon, "Diamond Dragon Chestplate");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.legsDiamondDragon, "Diamond Dragon Legs");
		LanguageRegistry.addName(ShurtugalMod.itemHandeler.tailDiamondDragon, "Diamond Dragon Tail");
	}

	private void RegisterBlocks()
	{
		// names blocks
		for (int ix = 0; ix < 9; ix++)
		{
			ItemStack BlockColoredEgg = new ItemStack(ShurtugalMod.blockHandler.Egg, 1, ix);
			LanguageRegistry.addName(BlockColoredEgg, Colors.ColorNames[ix] + " Egg");
		}

		LanguageRegistry.addName(BlockHandler.brightSteelOre, "Brightsteel Ore");
		for (int ix = 0; ix < 9; ix++)
		{
			ItemStack BlockColoredBrick = new ItemStack(ShurtugalMod.blockHandler.Brick, 1, ix);
			LanguageRegistry.addName(BlockColoredBrick, Colors.ColorNames[ix] + " Brick");
		}
		LanguageRegistry.addName(ShurtugalMod.blockHandler.IronFurnaceIdle, "Iron Furnace");

	}
}
