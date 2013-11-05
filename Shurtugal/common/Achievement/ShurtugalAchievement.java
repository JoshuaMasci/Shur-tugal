package Shurtugal.common.Achievement;

import java.lang.reflect.Field;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementMap;
import net.minecraft.stats.StatList;
import net.minecraftforge.common.AchievementPage;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;

public class ShurtugalAchievement
{
	public static AchievementPage Shurtugal;
	//Achievements

	public static Achievement Rider;
	public static Achievement Smith;
	public static Achievement Forge;
	public static Achievement Saddlebag;

	public static void load()
	{
	
		
	if(StatList.getOneShotStat(231) == null)
	{
		Rider = new Achievement(231, "Rider", 0, 0, ShurtugalMod.blockHandler.Egg, null).registerAchievement();
	}

	addAchievementName("Rider", "Yer a Rider, Harry!");
	addAchievementDesc("Rider", "Hatched your first dragon!");
	
	Forge = new Achievement(232, "Forge", 1, 0, ShurtugalMod.blockHandler.IronFurnaceIdle, null).registerAchievement();
	
	addAchievementName("Forge", "Iron Foundrey");
	addAchievementDesc("Forge", "Crafted an iron furnce.");
	
	Smith = new Achievement(233, "Smith", 2, 1, ShurtugalMod.itemHandeler.DragonBlade, Forge).registerAchievement();

	addAchievementName("Smith", "Smith of The Riders");
	addAchievementDesc("Smith", "Forged your sword.");
	
	Saddlebag = new Achievement(234, "Saddlebag", -1, -1, ShurtugalMod.itemHandeler.SaddleBag, Rider).registerAchievement();

	addAchievementName("Saddlebag", "Saddle 2.0");
	addAchievementDesc("Saddlebag", "You created a Saddle Bag!");
	
	Shurtugal = new AchievementPage("Shur'tugal", Rider, Smith, Forge, Saddlebag);
	AchievementPage.registerAchievementPage(Shurtugal);
	}
	
	private static void addAchievementName(String ach, String name)
	{
	LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
	}

	private static void addAchievementDesc(String ach, String desc)
	{
	LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}
	
}
