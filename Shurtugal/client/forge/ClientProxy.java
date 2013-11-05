/*
 ** 2012 August 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.client.forge;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Keyboard;

import Shurtugal.ShurtugalMod;
import Shurtugal.client.model.DragonModel;
import Shurtugal.client.render.BlockDragonEggRender;
import Shurtugal.client.render.BlockIronFurnaceRender;
import Shurtugal.client.render.DragonRenderer;
import Shurtugal.common.Handlers.DragonKeyHandler;
import Shurtugal.common.entity.Dragon.EntityDragonHostile;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import Shurtugal.common.forge.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundPool;
import net.minecraft.client.audio.SoundPoolEntry;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

//import Shurtugal.icode.easteregg.CodeMasterDojo;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy
{

	private static final Logger L = Logger.getLogger(ClientProxy.class.getName());
	public static KeyBinding openGui = new KeyBinding("Open The Dragon's SaddleBag when Riding", Keyboard.KEY_R);
	public static KeyBinding flyDown = new KeyBinding("Replaces left shift in flight controls", Keyboard.KEY_LCONTROL);
	
	@Override
	public void onPreinit(FMLPreInitializationEvent evt)
	{
		int ID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(ID, new BlockDragonEggRender());
		ShurtugalMod.DragonEggRenderID = ID;
		
		ID = RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(ID, new BlockIronFurnaceRender());
		ShurtugalMod.IronFurnaceRenderID = ID;
		
		MinecraftForge.EVENT_BUS.register(new Shurtugal.common.sound.SoundEvents());
	}

	@Override
	public void onInit(FMLInitializationEvent evt)
	{
		super.onInit(evt);
		KeyBinding[] key =
		{ openGui, flyDown};
		boolean[] repeat =
		{ false, false};
		KeyBindingRegistry.registerKeyBinding(new DragonKeyHandler(key, repeat));

		// register tick handlers
		TickRegistry.registerTickHandler(new OverlayTickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new RemoteControlTickHandler(), Side.CLIENT);
		TickRegistry.registerTickHandler(new ThirdPersonCameraTickHandler(), Side.CLIENT);
		// TickRegistry.registerTickHandler(new GuiTickHandler(), Side.CLIENT);

		// register entity renderer
		RenderingRegistry.registerEntityRenderingHandler(EntityTameableDragon.class, new DragonRenderer(new DragonModel()));
		RenderingRegistry.registerEntityRenderingHandler(EntityDragonHostile.class, new DragonRenderer(new DragonModel()));
		//RenderingRegistry.registerEntityRenderingHandler(CodeMasterDojo.class, new RenderZombie());
		//RenderingRegistry.registerEntityRenderingHandler(EntityGuard.class, new RenderBiped(new ModelBiped(), 0.5F));

		
		// register sounds
		//TODO Re implement this entire system at some point
	}
	
	@Override
	public void sendParticleSpawnPacket(World world ,String par1Str, double posX, double posY, double posZ, double par8, double par10, double par12)
	{
		//Does not Use this Client Side
	}
}
