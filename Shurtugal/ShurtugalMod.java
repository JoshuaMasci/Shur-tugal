package Shurtugal;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.src.*;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import Shurtugal.common.entity.*;
import Shurtugal.client.forge.TabShurtugal;
import Shurtugal.client.gui.GuiHandler;
import Shurtugal.common.Biomes.BiomeGenBurningPlains;
import Shurtugal.common.Crafting.CraftingHandler;
import Shurtugal.common.Crafting.RecipesDragonArmorDyes;
import Shurtugal.common.Gen.OreGeneration;
import Shurtugal.common.Gen.TempleGen;
import Shurtugal.common.Gen.VillageHandler;
import Shurtugal.common.Gen.Temples.ComponentVillagePinkTemple;
import Shurtugal.common.Handlers.*;
import Shurtugal.common.Item.*;
//import Shurtugal.common.MagicAPI.Base.Spells;
import Shurtugal.common.Network.ShurtugalCommonPacketHandler;
import Shurtugal.common.Network.ShurtugalConnectionHandler;
import Shurtugal.common.Achievement.ShurtugalAchievement;
import Shurtugal.common.block.*;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.ServerStarted;
import cpw.mods.fml.common.Mod.ServerStopped;
import cpw.mods.fml.common.Mod.ServerStopping;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.NetworkMod.SidedPacketHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import Shurtugal.common.block.*;
import Shurtugal.common.forge.*;
import Shurtugal.common.sound.SoundEvents;
import Shurtugal.client.forge.*;

@Mod(modid = ShurtugalMod.modID, name = "Shur'tugal", version = "0.5.2", useMetadata = true)
@NetworkMod(connectionHandler = ShurtugalConnectionHandler.class, clientSideRequired = true, serverSideRequired = false, clientPacketHandlerSpec = @SidedPacketHandler(channels =
{ "PlayerMoveInput", "IronFurnace", "DragonGui", "ParticleSpawn" }, packetHandler = ShurtugalClientPacketHandler.class), serverPacketHandlerSpec = @SidedPacketHandler(channels =
{ "PlayerMoveInput", "IronFurnace", "DragonGui", "ParticleSpawn" }, packetHandler = ShurtugalCommonPacketHandler.class))
public class ShurtugalMod
{
	// Codes testing area
	// End testing area
	public static final String modID = "Shur'tugal";

	@SidedProxy(serverSide = "Shurtugal.common.forge.CommonProxy", clientSide = "Shurtugal.client.forge.ClientProxy")
	public static CommonProxy proxy;

	@Instance
	public static ShurtugalMod instance;

	public static BlockHandler blockHandler = new BlockHandler();
	public static ItemHandler itemHandeler = new ItemHandler();
	public static LanguageHandler languageHandler = new LanguageHandler();
	public static CraftingHandler craftingHandler = new CraftingHandler();
	public static SoundEvents soundHandler = new SoundEvents();
	public static CreativeTabs tabShurtugal = new TabShurtugal(CreativeTabs.getNextID(), "Shur'tugal");
	//public static ShurtugalAchievement achievementHandler = new ShurtugalAchievement();
	
	public static int DragonEggRenderID;
	public static int IronFurnaceRenderID;

	// Energy List (Move if can be added elsewhere)
	public static ArrayList<Item> energyList = new ArrayList<Item>();

	// Biome being reimplimented in pre4 of 0.5.0 or 0.5.1
	// public static final BiomeGenBurningPlains fireplans = new
	// BiomeGenBurningPlains(25);
	private GuiHandler guiHandler = new GuiHandler();

	@EventHandler
	public void onPreinit(FMLPreInitializationEvent evt)
	{
		ConfigHandler.Load(evt);
		proxy.onPreinit(evt);
	}

	@EventHandler
	public void onInit(FMLInitializationEvent evt)
	{
		this.blockHandler.onInti();
		this.itemHandeler.onInti();
		this.languageHandler.RegisterNames();
		this.craftingHandler.AddCrafting();
		//this.achievementHandler.load();
		
		GameRegistry.registerCraftingHandler(craftingHandler);
		
		// world gen
		GameRegistry.registerWorldGenerator(new OreGeneration());
		GameRegistry.registerWorldGenerator(new TempleGen());

		// GameRegistry.addBiome(fireplans);

		// Gui Stuff
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);

		// Event Handler
		MinecraftForge.EVENT_BUS.register(new ShurtugalPlayerHandler());

		// Register Village parts
		VillagerRegistry.instance().registerVillageCreationHandler(new VillageHandler());

		proxy.onInit(evt);
		
		// Energy Stuffs
		Shurtugal.common.MagicAPI.Energy.EnergyHandler.addItems(energyList);
	}

	@EventHandler
	public void onServerStart(FMLServerStartedEvent evt)
	{
		proxy.onServerStart(evt);
	}

	@EventHandler
	public void onServerStop(FMLServerStoppedEvent evt)
	{
		proxy.onServerStop(evt);
	}

}
