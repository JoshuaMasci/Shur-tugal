package Shurtugal.common.Item;

import net.minecraft.item.*;
import net.minecraftforge.common.EnumHelper;
import Shurtugal.ShurtugalMod;
import Shurtugal.client.forge.ClientProxy;
import Shurtugal.common.Handlers.ConfigHandler;
import Shurtugal.common.Item.Tools.ItemModAxe;
import Shurtugal.common.Item.Tools.ItemModHoe;
import Shurtugal.common.Item.Tools.ItemModPickaxe;
import Shurtugal.common.Item.Tools.ItemModSpade;
import Shurtugal.common.Item.Tools.ItemRoranHammer;
import Shurtugal.common.block.BlockHandler;

public class ItemHandler
{
	public static EnumArmorMaterial steelArmor = EnumHelper.addArmorMaterial("steel", 22, new int[]
	{ 3, 7, 6, 3 }, 9);
	public static EnumToolMaterial steelTool = EnumHelper.addToolMaterial("steel", 3, 905, 7F, 2, 22);
	public static EnumArmorMaterial birghtSteelArmor = EnumHelper.addArmorMaterial("brightSteel", 0, new int[]
	{ 4, 8, 6, 4 }, 25);
	
	public static Item DragonBlade;
	
	public static Item MageBlade;

	public static Item steelIngot;
	public static Item brightsteelIngot;
	public static Item denseLeather;

	public static Item helmetBrightSteel;

	public static Item plateBrightSteel;

	public static Item legsBrightSteel;

	public static Item bootsBrightSteel;

	public static Item helmetSteel;

	public static Item plateSteel;

	public static Item legsSteel;

	public static Item bootsSteel;

	public static Item steelSword;

	public static Item steelSpade;

	public static Item steelPickaxe;

	public static Item steelAxe;

	public static Item steelHoe;

	public static Item dragonScale;

	public static Item dragonShears;

	public static Item SaddleBag;

	public static Item helmetLeatherDragon;

	public static Item plateLeatherDragon;

	public static Item legsLeatherDragon;

	public static Item tailLeatherDragon;

	public static Item helmetIronDragon;

	public static Item plateIronDragon;

	public static Item legsIronDragon;

	public static Item tailIronDragon;

	public static Item helmetGoldDragon;

	public static Item plateGoldDragon;

	public static Item legsGoldDragon;

	public static Item tailGoldDragon;

	public static Item helmetDiamondDragon;

	public static Item plateDiamondDragon;

	public static Item legsDiamondDragon;

	public static Item tailDiamondDragon;
	
	public static Item eldunari;
	
	public static Item hammer;
	
	public void onInti()
	{

		DragonBlade = new ItemDragonRiderSword(ConfigHandler.DragonBladeID).setUnlocalizedName("Dragon's Blade");
		
		MageBlade = new ItemMageSword(ConfigHandler.MageBladeID).setUnlocalizedName("Mage Blade");

		steelIngot = new ItemIngot(ConfigHandler.steelIngotID).setUnlocalizedName("steelingot");
		brightsteelIngot = new ItemIngot(ConfigHandler.brightsteelIngotID).setUnlocalizedName("brightsteelingot");
		denseLeather = new ItemIngot(ConfigHandler.denseLeatherID).setUnlocalizedName("denseLeather");
		
		steelArmor.customCraftingMaterial = steelIngot;
		steelTool.customCraftingMaterial = 	steelIngot;

		helmetSteel = new ItemModArmor(ConfigHandler.helmetSteelID, steelArmor, 0, 0).setUnlocalizedName("SteelHelm");

		plateSteel = new ItemModArmor(ConfigHandler.plateSteelID, steelArmor, 0, 1).setUnlocalizedName("steelPlate");

		legsSteel = new ItemModArmor(ConfigHandler.legsSteelID, steelArmor, 0, 2).setUnlocalizedName("steelLegs");

		bootsSteel = new ItemModArmor(ConfigHandler.bootsSteelID, steelArmor, 0, 3).setUnlocalizedName("steelBoots");

		steelSword = new ItemModSword(ConfigHandler.steelSwordID, steelTool).setUnlocalizedName("steelsword").setCreativeTab(ShurtugalMod.tabShurtugal);

		steelSpade = new ItemModSpade(ConfigHandler.steelSpadeID, steelTool).setUnlocalizedName("steelspade").setCreativeTab(ShurtugalMod.tabShurtugal);

		steelPickaxe = new ItemModPickaxe(ConfigHandler.steelPickaxeID, steelTool).setUnlocalizedName("steelpic").setCreativeTab(ShurtugalMod.tabShurtugal);

		steelAxe = new ItemModAxe(ConfigHandler.steelAxeID, steelTool).setUnlocalizedName("steelaxe").setCreativeTab(ShurtugalMod.tabShurtugal);

		steelHoe = new ItemModHoe(ConfigHandler.steelHoeID, steelTool).setUnlocalizedName("steelhoe").setCreativeTab(ShurtugalMod.tabShurtugal);

		dragonScale = new ItemScale(ConfigHandler.dragonScaleID).setUnlocalizedName("scale");

		dragonShears = new ItemDiamondShears(ConfigHandler.dragonShearsID).setUnlocalizedName("DragonShears").setMaxDamage(1000);

		SaddleBag = new ItemSaddleBag(ConfigHandler.SaddleBagID).setUnlocalizedName("SaddleBag").setCreativeTab(ShurtugalMod.tabShurtugal);

		eldunari = new ItemEldunari(ConfigHandler.eldunariID).setUnlocalizedName("eldunari");
		
		hammer = new ItemRoranHammer(ConfigHandler.hammerID).setUnlocalizedName("hammer").setCreativeTab(ShurtugalMod.tabShurtugal);
		
		//helmetBrightSteel = new ItemModArmor(ConfigHandler.helmetBrightsteelID, birghtSteelArmor, 0, 0).setUnlocalizedName("BrightsteelHelm");

		//plateBrightSteel = new ItemModArmor(ConfigHandler.plateBrightsteelID, birghtSteelArmor, 0, 1).setUnlocalizedName("BrightsteelPlate");

		//legsBrightSteel = new ItemModArmor(ConfigHandler.legsBrightsteelID, birghtSteelArmor, 0, 2).setUnlocalizedName("BrightsteelLegs");

		//bootsBrightSteel = new ItemModArmor(ConfigHandler.bootsBrightsteelID, birghtSteelArmor, 0, 3).setUnlocalizedName("BrightsteelBoots");

		// The waves of Dragon Armors
		helmetLeatherDragon = new ItemDragonArmor(ConfigHandler.helmetLeatherDragonID, EnumArmorMaterial.CLOTH, 0, 0).setArmorTextureFile(
				"/mods/Shurtugal/textures/dragon/armor/DragonArmorLeather2.png").setUnlocalizedName("DragonHelmetLeather");
		plateLeatherDragon = new ItemDragonArmor(ConfigHandler.plateLeatherDragonID, EnumArmorMaterial.CLOTH, 0, 1)
				.setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorLeather2.png").setUnlocalizedName("DragonPlateLeather");
		legsLeatherDragon = new ItemDragonArmor(ConfigHandler.legsLeatherDragonID, EnumArmorMaterial.CLOTH, 0, 2).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorLeather2.png")
				.setUnlocalizedName("DragonLegsLeather");
		tailLeatherDragon = new ItemDragonArmor(ConfigHandler.tailLeatherDragonID, EnumArmorMaterial.CLOTH, 0, 3).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorLeather2.png")
				.setUnlocalizedName("DragonTailLeather");

		helmetIronDragon = new ItemDragonArmor(ConfigHandler.helmetIronDragonID, EnumArmorMaterial.IRON, 1, 0).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorIron.png")
				.setUnlocalizedName("DragonHelmetIron");
		plateIronDragon = new ItemDragonArmor(ConfigHandler.plateIronDragonID, EnumArmorMaterial.IRON, 1, 1).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorIron.png")
				.setUnlocalizedName("DragonPlateIron");
		legsIronDragon = new ItemDragonArmor(ConfigHandler.legsIronDragonID, EnumArmorMaterial.IRON, 1, 2).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorIron.png")
				.setUnlocalizedName("DragonLegsIron");
		tailIronDragon = new ItemDragonArmor(ConfigHandler.tailIronDragonID, EnumArmorMaterial.IRON, 1, 3).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorIron.png")
				.setUnlocalizedName("DragonTailIron");

		helmetGoldDragon = new ItemDragonArmor(ConfigHandler.helmetGoldDragonID, EnumArmorMaterial.GOLD, 2, 0).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorGold.png")
				.setUnlocalizedName("DragonHelmetGold");
		plateGoldDragon = new ItemDragonArmor(ConfigHandler.plateGoldDragonID, EnumArmorMaterial.GOLD, 2, 1).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorGold.png")
				.setUnlocalizedName("DragonPlateGold");
		legsGoldDragon = new ItemDragonArmor(ConfigHandler.legsGoldDragonID, EnumArmorMaterial.GOLD, 2, 2).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorGold.png")
				.setUnlocalizedName("DragonLegsGold");
		tailGoldDragon = new ItemDragonArmor(ConfigHandler.tailGoldDragonID, EnumArmorMaterial.GOLD, 2, 3).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorGold.png")
				.setUnlocalizedName("DragonTailGold");

		helmetDiamondDragon = new ItemDragonArmor(ConfigHandler.helmetDiamondDragonID, EnumArmorMaterial.DIAMOND, 3, 0).setArmorTextureFile(
				"/mods/Shurtugal/textures/dragon/armor/DragonArmorDiamond.png").setUnlocalizedName("DragonHelmetDiamond");
		plateDiamondDragon = new ItemDragonArmor(ConfigHandler.plateDiamondDragonID, EnumArmorMaterial.DIAMOND, 3, 1).setArmorTextureFile(
				"/mods/Shurtugal/textures/dragon/armor/DragonArmorDiamond.png").setUnlocalizedName("DragonPlateDiamond");
		legsDiamondDragon = new ItemDragonArmor(ConfigHandler.legsDiamondDragonID, EnumArmorMaterial.DIAMOND, 3, 2).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorDiamond.png")
				.setUnlocalizedName("DragonLegsDiamond");
		tailDiamondDragon = new ItemDragonArmor(ConfigHandler.tailDiamondDragonID, EnumArmorMaterial.DIAMOND, 3, 3).setArmorTextureFile("/mods/Shurtugal/textures/dragon/armor/DragonArmorDiamond.png")
				.setUnlocalizedName("DragonTailDiamond");

	}
}
