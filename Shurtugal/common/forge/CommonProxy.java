/*
 ** 2012 August 27
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.forge;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartedEvent;
import cpw.mods.fml.common.event.FMLServerStoppedEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.logging.Level;
import java.util.logging.Logger;

import Shurtugal.ShurtugalMod;
import Shurtugal.common.entity.*;
import Shurtugal.common.DragonList.ActiveDragonList;
import Shurtugal.common.DragonList.DragonListFileHandler;
import Shurtugal.common.Network.DragonInventoryPacket;
import Shurtugal.common.Network.MovementInputPacketHandler;
import Shurtugal.common.Network.ParticleSpawnPacket;
import Shurtugal.common.cmd.CommandDragon;
import Shurtugal.common.entity.Dragon.EntityDragonHostile;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import Shurtugal.common.tileEntity.*;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * 
 * @author Iamshortman
 */
public class CommonProxy
{

	private static final Logger L = Logger.getLogger(CommonProxy.class.getName());
	private DragonListFileHandler dragonFile;
	private static String nbt_Dragon = "Dragon";

	
	public void onInit(FMLInitializationEvent evt)
	{
		// register entities
		int dragonMountId = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityTameableDragon.class, "PlayerDragon", dragonMountId);
		EntityRegistry.registerGlobalEntityID(EntityDragonHostile.class, "EvilDragon", EntityRegistry.findGlobalUniqueEntityId(), 0xffffff, 0xcc00ff);

		// EntityRegistry.registerGlobalEntityID(EntityGuard.class, "NPC GUARD",
		// EntityRegistry.findGlobalUniqueEntityId(), 0xffffff, 0xcc00ff);
		// EntityRegistry.registerModEntity(EntityGuard.class, "NPC GUARD",
		// EntityRegistry.findGlobalUniqueEntityId(), ShurtugalMod.instance, 64,
		// 2, true);

		GameRegistry.registerTileEntity(TileEntityRedTrigger.class, "RedTempleSpawner");
		GameRegistry.registerTileEntity(TileEntityYellowTrigger.class, "YellowTempleSpawner");
		GameRegistry.registerTileEntity(TileEntityPinkTrigger.class, "PinkTempleSpawner");
		GameRegistry.registerTileEntity(TileEntityPurpleTrigger.class, "PurpleTempleSpawner");
		GameRegistry.registerTileEntity(TileEntityGreenTrigger.class, "GreenTempleSpawner");
		GameRegistry.registerTileEntity(TileEntityWhiteTrigger.class, "WhiteTempleSpawner");
		GameRegistry.registerTileEntity(TileEntityIronFurnace.class, "tileEntityIronFurnace");
		GameRegistry.registerTileEntity(TileEntityBlackTrigger.class, "BlackTempleSpawner");

		
		//LanguageRegistry.instance().addStringLocalization("achievement.Rider", "en_US", "Yer a Rider, Harry!");
		//LanguageRegistry.instance().addStringLocalization("achievement.Rider.desc", "en_US", "You Hatched a Dragon Egg!");
		
		
	}

	public void onPreinit(FMLPreInitializationEvent evt)
	{
		
	}

	public void onServerStart(FMLServerStartedEvent evt)
	{
		MinecraftServer server = MinecraftServer.getServer();
		
		//this.dragonFile = new DragonListFileHandler(server);
		
		
		// clear cached network movement data
		MovementInputPacketHandler.clear();
		ActiveDragonList.clear();
		// register server commands

		ServerCommandManager cmdman = (ServerCommandManager) server.getCommandManager();
		cmdman.registerCommand(new CommandDragon());
	}
	
	public void updatePlayerDragon(EntityPlayer player, EntityTameableDragon dragon)
	{
		if(this.dragonFile != null)
		{
			this.dragonFile.UpdatePlayerDragonInFile(player.username, dragon);
		}
		else
		{
			if(player == null)
			{
				return;
			}
			
			NBTTagCompound nbt = player.getEntityData();
			NBTTagCompound nbtDragon = new NBTTagCompound();
			dragon.writeDragonToNBT(nbtDragon);
			nbt.setCompoundTag(nbt_Dragon, nbtDragon);
			if(player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
			{
				if(!nbt.hasKey(player.PERSISTED_NBT_TAG))
				{
					nbt.setCompoundTag(player.PERSISTED_NBT_TAG, new NBTTagCompound());
				}
		        NBTTagCompound nbtPersisted = nbt.getCompoundTag(player.PERSISTED_NBT_TAG); 
		        nbtPersisted.setCompoundTag(nbt_Dragon, nbtDragon);
			}
		}
	}
	
	public boolean isPlayerRider(EntityPlayer player)
	{
		if(this.dragonFile != null)
		{
			return this.dragonFile.doesPlayerHaveDragon(player);
		}
		else
		{
			if(player == null)
			{
				return false;
			}
			NBTTagCompound nbt = player.getEntityData();
			if(nbt.hasKey(nbt_Dragon))
			{
				NBTTagCompound nbtDragon = nbt.getCompoundTag(nbt_Dragon);
				if(nbtDragon.hasKey("isDead"))
				{
					boolean dead = nbtDragon.getBoolean("isDead");
					return !dead;
				}
			}
			else if(player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory") && nbt.hasKey(player.PERSISTED_NBT_TAG))
			{

				NBTTagCompound nbtPersisted = nbt.getCompoundTag(player.PERSISTED_NBT_TAG); 
				if(nbtPersisted.hasKey(nbt_Dragon))
				{
					NBTTagCompound nbtDragon = nbtPersisted.getCompoundTag(nbt_Dragon);
					nbt.setCompoundTag(nbt_Dragon, nbtDragon);
					if(nbtDragon.hasKey("isDead"))
					{
						boolean dead = nbtDragon.getBoolean("isDead");
						return !dead;
					}
				}
			}
		}
		return false;
	}
	
	public EntityTameableDragon getPlayersDragon(EntityPlayer player, World world)
	{
		if(this.dragonFile != null)
		{
			return this.dragonFile.createDragonInstance(world, player);
		}
		else
		{
			if(player == null)
			{
				return null;
			}
			
			if(!this.isPlayerRider(player))
			{
				return null;
			}
			NBTTagCompound nbt = player.getEntityData();
			if(nbt.hasKey(nbt_Dragon))
			{
				EntityTameableDragon dragon = new EntityTameableDragon(world);
				NBTTagCompound nbtDragon = nbt.getCompoundTag(nbt_Dragon);
				dragon.readDragonFromNBT(nbtDragon);
				return dragon;
			}
			
		}
		return null;
	}
	
	public int getDragonDimension(EntityPlayer player)
	{
		if(this.dragonFile != null)
		{
			return this.dragonFile.getDragonDimension(player);
		}
		else
		{
			if(player == null)
			{
				return 0;
			}
			
			if(!this.isPlayerRider(player))
			{
				return 0;
			}
			NBTTagCompound nbt = player.getEntityData();
			if(nbt.hasKey(nbt_Dragon))
			{
				NBTTagCompound nbtDragon = nbt.getCompoundTag(nbt_Dragon);
				return nbt.getInteger("Dimension");
			}
		}
		return 0;
	}
	
	
	public void killDragon(EntityPlayer player,EntityTameableDragon dragon)
	{
		if(this.dragonFile != null && player.username != null)
		{
			this.dragonFile.clearDragonFileForUser(player.username, dragon);
		}
		else
		{
			if(player == null)
			{
				return;
			}
			
			if(!this.isPlayerRider(player))
			{
				return;
			}
			NBTTagCompound nbt = player.getEntityData();
			nbt.setCompoundTag(nbt_Dragon, new NBTTagCompound());
			if(!player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
			{
				if(!nbt.hasKey(player.PERSISTED_NBT_TAG))
				{
					NBTTagCompound nbtPersisted = nbt.getCompoundTag(player.PERSISTED_NBT_TAG); 
					nbtPersisted.setCompoundTag(nbt_Dragon, new NBTTagCompound());
				}
			}
		}
	}

	public void onServerStop(FMLServerStoppedEvent evt)
	{
		this.dragonFile = null;
		
		// clear cached network movement data
		MovementInputPacketHandler.clear();
		ActiveDragonList.clear();
		
	}
	
	public void sendParticleSpawnPacket(World world ,String par1Str, double posX, double posY, double posZ, double par8, double par10, double par12)
	{
		PacketDispatcher.sendPacketToAllAround(posX, posY, posZ, 200, world.provider.dimensionId, ParticleSpawnPacket.createPacket(par1Str, posX, posY, posZ, par8, par10, par12));
	}
	
	
}
