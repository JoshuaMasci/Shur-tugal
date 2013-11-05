package Shurtugal.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.client.forge.ClientProxy;
import Shurtugal.common.Handlers.ConfigHandler;

public class BlockHandler
{
	public static Block Brick;

	public static Block Egg;

	public static Block brightSteelOre;

	public static Block RedTempleTrigger;

	public static Block YellowTempleTrigger;

	public static Block PurpleTempleTrigger;

	public static Block GreenTempleTrigger;

	public static Block WhiteTempleTrigger;

	public static Block BlackTempleTrigger;

	public static Block PinkTempleTrigger;

	public static Block IronFurnaceIdle;
	public static Block IronFurnaceActive;

	public void onInti()
	{
		brightSteelOre = new BlockBrightSteelOre(ConfigHandler.brightSteelOreID, Material.rock).setHardness(3F).setResistance(5F).setLightValue(1.0F).setUnlocalizedName("brightsteelore")
				.setCreativeTab(CreativeTabs.tabBlock);

		RedTempleTrigger = new TempleTrigger(ConfigHandler.RedTempleBlockID, Colors.Red).setUnlocalizedName("TriggerRed");

		YellowTempleTrigger = new TempleTrigger(ConfigHandler.YellowTempleBlockID, Colors.Yellow).setUnlocalizedName("TriggerYellow");

		PurpleTempleTrigger = new TempleTrigger(ConfigHandler.PurpleTempleBlockID, Colors.Purple).setUnlocalizedName("TriggerRurple");

		GreenTempleTrigger = new TempleTrigger(ConfigHandler.GreenTempleBlockID, Colors.Green).setUnlocalizedName("TriggerGreen");

		WhiteTempleTrigger = new TempleTrigger(ConfigHandler.WhiteTempleBlockID, Colors.White).setUnlocalizedName("TriggerWhite");

		BlackTempleTrigger = new TempleTrigger(ConfigHandler.BlackTempleBlockID, Colors.Black).setUnlocalizedName("TriggerBlack");

		PinkTempleTrigger = new TempleTrigger(ConfigHandler.PinkTempleBlockID, Colors.Pink).setUnlocalizedName("TriggerPink");

		IronFurnaceIdle = new BlockIronFurnace(ConfigHandler.IronFurnaceIdleID, false).setUnlocalizedName("IronFunace");

		IronFurnaceActive = new BlockIronFurnace(ConfigHandler.IronFurnaceActiveID, true).setUnlocalizedName("IronFunace");

		Brick = new BlockColoredBrick(ConfigHandler.BrickID).setHardness(1.5F).setResistance(10F).setUnlocalizedName("Brick");

		Egg = new DragonEggBlock(ConfigHandler.EggID).setUnlocalizedName("dragonEgg");

		GameRegistry.registerBlock(Egg, ItemBlockEgg.class, "DragonEgg");

		GameRegistry.registerBlock(Brick, ItemBlockBrick.class, "DragonBrick");

		registerBlock(brightSteelOre, "BrightSteelOre");
		registerBlock(RedTempleTrigger, "RedTempleTrigger");
		registerBlock(YellowTempleTrigger, "YellowTempleTrigger");
		registerBlock(PurpleTempleTrigger, "PurpleTempleTrigger");
		registerBlock(GreenTempleTrigger, "GreenTempleTrigger");
		registerBlock(WhiteTempleTrigger, "WhiteTempleTrigger");
		registerBlock(BlackTempleTrigger, "BlackTempleTrigger");
		registerBlock(PinkTempleTrigger, "PinkTempleTrigger");

		registerBlock(IronFurnaceIdle, "IronFurnace");
		registerBlock(IronFurnaceActive, "IronFurnaceIdle");

		// extra stuff
		brightSteelOre.setCreativeTab(ShurtugalMod.tabShurtugal);

	}

	/**
	 * Registers the Block with both GameRegistry and LanguageRegistry
	 * @param block
	 * @param name
	 */
	public static void registerBlock(Block block, String name)
	{
		registerBlock(block, ItemBlock.class, name);
	}

	/**
	 * Registers the Block with both GameRegistry and LanguageRegistry
	 * @param block
	 * @param name
	 * @param itemBlock Class
	 */
	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlock, String name)
	{
		GameRegistry.registerBlock(block, itemBlock, name, ShurtugalMod.modID);
	}
	
}
