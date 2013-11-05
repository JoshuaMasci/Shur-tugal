package Shurtugal.common.Handlers;

import Shurtugal.Colors;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.event.Event;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ConfigHandler
{
	//Temple Gen Rates
	public static int[] templeSpawnRate = new int[9];

	// Blocks
	public static int IronFurnaceIdleID;
	public static int IronFurnaceActiveID;
	public static int BlackTempleBlockID;
	public static int PinkTempleBlockID;
	public static int WhiteTempleBlockID;
	public static int GreenTempleBlockID;
	public static int PurpleTempleBlockID;
	public static int YellowTempleBlockID;
	public static int RedTempleBlockID;
	public static int brightSteelOreID;
	public static int EggID;
	public static int BrickID;

	// items
	public static int DragonBladeID;
	public static int MageBladeID;
	public static int steelIngotID;
	public static int helmetSteelID;
	public static int plateSteelID;
	public static int legsSteelID;
	public static int bootsSteelID;
	public static int steelSwordID;
	public static int steelSpadeID;
	public static int steelPickaxeID;
	public static int steelAxeID;
	public static int steelHoeID;
	public static int dragonScaleID;
	public static int dragonShearsID;
	public static int SaddleBagID;
	public static int helmetBrightsteelID;
	public static int plateBrightsteelID;
	public static int legsBrightsteelID;
	public static int bootsBrightsteelID;
	public static int eldunariID;
	public static int hammerID;
	public static int brightsteelIngotID;
	public static int denseLeatherID;
	
	public static int helmetLeatherDragonID;
	public static int plateLeatherDragonID;
	public static int legsLeatherDragonID;
	public static int tailLeatherDragonID;

	public static int helmetIronDragonID;
	public static int plateIronDragonID;
	public static int legsIronDragonID;
	public static int tailIronDragonID;

	public static int helmetGoldDragonID;
	public static int plateGoldDragonID;
	public static int legsGoldDragonID;
	public static int tailGoldDragonID;

	public static int helmetDiamondDragonID;
	public static int plateDiamondDragonID;
	public static int legsDiamondDragonID;
	public static int tailDiamondDragonID;
	
	public static boolean FlightType = false; 
	
	
	public static boolean debug = false;

	public static void Load(FMLPreInitializationEvent evt)
	{
		Configuration config = new Configuration(evt.getSuggestedConfigurationFile());

		config.load();
		// BLocks
		BrickID = config.getBlock("ColoredBricks", 4095).getInt();
		EggID = config.getBlock("DragonEggs", 4094).getInt();
		brightSteelOreID = config.getBlock("brightSteelOre", 4093).getInt();
		RedTempleBlockID = config.getBlock("RedTempleBlock", 4092).getInt();
		YellowTempleBlockID = config.getBlock("YellowTempleBlock", 4091).getInt();
		PurpleTempleBlockID = config.getBlock("PurpleTempleBlock", 4090).getInt();
		GreenTempleBlockID = config.getBlock("GreenTempleBlock", 4089).getInt();
		WhiteTempleBlockID = config.getBlock("WhiteTempleBlock", 4088).getInt();
		PinkTempleBlockID = config.getBlock("PinkTempleBlock", 4087).getInt();
		BlackTempleBlockID = config.getBlock("BlackTempleBlock", 4086).getInt();
		IronFurnaceActiveID = config.getBlock("IronFurnaceActive", 4085).getInt();
		IronFurnaceIdleID = config.getBlock("IronFurnaceIdle", 4084).getInt();

		// Items TODO create better item IDs
		DragonBladeID = config.getItem("Riders Blades", 3940).getInt();
		MageBladeID = config.getItem("Mage Blades", 3976).getInt();
		steelIngotID = config.getItem("Steel Ingots", 3941).getInt();
		helmetSteelID = config.getItem("Helmet Steel", 3943).getInt();
		plateSteelID = config.getItem("Chest Steel", 3944).getInt();
		legsSteelID = config.getItem("Legs Steel", 3945).getInt();
		bootsSteelID = config.getItem("Boots Steel", 3946).getInt();
		steelSwordID = config.getItem("Steel Sword", 3947).getInt();
		steelSpadeID = config.getItem("Steel Shovel", 3948).getInt();
		steelPickaxeID = config.getItem("Steel Pickaxe", 3949).getInt();
		steelAxeID = config.getItem("Steel Axe", 3950).getInt();
		steelHoeID = config.getItem("Steel Hoe", 3951).getInt();
		dragonScaleID = config.getItem("Dragon Scales", 3952).getInt();
		dragonShearsID = config.getItem("Dragon Shears", 3953).getInt();
		SaddleBagID = config.getItem("Saddle Bag", 3954).getInt();
		eldunariID = config.getItem("Eldunari", 4000).getInt();
		hammerID = config.getItem("Roran's Hammer", 4001).getInt();
		denseLeatherID = config.getItem("Dense Leather", 4002).getInt();
		brightsteelIngotID = config.getItem("Brightsteel Ingot", 4003).getInt();
		
		//helmetBrightsteelID = config.getItem("Helmet BrightSteel", 3955).getInt();
		//plateBrightsteelID = config.getItem("Chest  BrightSteel", 3956).getInt();
		//legsBrightsteelID = config.getItem("Legs BrightSteel", 3957).getInt();
		//bootsBrightsteelID = config.getItem("Boots BrightSteel", 3958).getInt();

		// here comes the waves of dragon armor
		helmetLeatherDragonID = config.getItem("Dragon Armor", "Dragon Helmet Leather", 3959).getInt();
		plateLeatherDragonID = config.getItem("Dragon Armor", "Dragon ChestPlate Leather", 3960).getInt();
		legsLeatherDragonID = config.getItem("Dragon Armor", "Dragon Legs Leather", 3961).getInt();
		tailLeatherDragonID = config.getItem("Dragon Armor", "Dragon Tail Leather", 3962).getInt();

		helmetIronDragonID = config.getItem("Dragon Armor", "Dragon Helmet Iron", 3963).getInt();
		plateIronDragonID = config.getItem("Dragon Armor", "Dragon ChestPlate Iron", 3964).getInt();
		legsIronDragonID = config.getItem("Dragon Armor", "Dragon Legs Iron", 3965).getInt();
		tailIronDragonID = config.getItem("Dragon Armor", "Dragon Tail Iron", 3966).getInt();

		helmetGoldDragonID = config.getItem("Dragon Armor", "Dragon Helmet Gold", 3967).getInt();
		plateGoldDragonID = config.getItem("Dragon Armor", "Dragon ChestPlate Gold", 3968).getInt();
		legsGoldDragonID = config.getItem("Dragon Armor", "Dragon Legs Gold", 3969).getInt();
		tailGoldDragonID = config.getItem("Dragon Armor", "Dragon Tail Gold", 3970).getInt();

		helmetDiamondDragonID = config.getItem("Dragon Armor", "Dragon Helmet Diamond", 3971).getInt();
		plateDiamondDragonID = config.getItem("Dragon Armor", "Dragon ChestPlate Diamond", 3972).getInt();
		legsDiamondDragonID = config.getItem("Dragon Armor", "Dragon Legs Diamond", 3973).getInt();
		tailDiamondDragonID = config.getItem("Dragon Armor", "Dragon Tail Diamond", 3974).getInt();

		templeSpawnRate[0] = 750;
		templeSpawnRate[1] = 250;
		templeSpawnRate[2] = 250;
		templeSpawnRate[3] = 250;
		templeSpawnRate[4] = -1;
		templeSpawnRate[5] = 300;
		templeSpawnRate[6] = 250;
		templeSpawnRate[7] = 100;
		templeSpawnRate[8] = 350;
		
		for(int i = 0; i < templeSpawnRate.length; i++)
		{
			if(i == Colors.Pink)
			{
				continue;
			}
			String str = Colors.ColorNames[i] + " Temple Spawn Rate";
			templeSpawnRate[i] = config.get("Temple Gen Rates",str, templeSpawnRate[i]).getInt();
		}
		
		
		debug = config.get("Debug", "debug", false, "for testing temples and other code").getBoolean(false);
		config.save();
		if (debug)
		{
			System.out.println("shurtugal: Debug Mode is on, when on you will be given the location of temples when they spawn");
		}
	}

}
