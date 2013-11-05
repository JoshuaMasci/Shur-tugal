/*
 ** 2012 August 24
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.cmd;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import Shurtugal.ShurtugalMod;
import Shurtugal.common.entity.LifeStage;
import Shurtugal.common.DragonList.ActiveDragonList;
import Shurtugal.common.Gen.Temples.BlueTemple;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;

/**
 * 
 * @author Iamshortman
 */
public class CommandDragon extends CommandBase
{

	@Override
	public String getCommandName()
	{
		return "shurtugal";
	}

	public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] params)
	{
		if (par1ICommandSender instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) par1ICommandSender;
			
			if (!ShurtugalMod.proxy.isPlayerRider(player))
			{
				return null;
			}
			
			boolean isOp = MinecraftServer.getServer().getConfigurationManager().areCommandsAllowed(player.username);
			if (params.length == 1)
			{
				if (isOp)
				{
					return getListOfStringsMatchingLastWord(params, new String[] { "lifestage", "call", "despawn" });	
				}
				return getListOfStringsMatchingLastWord(params, new String[] { "call", "despawn" });
			}
			else if (params.length == 2 && params[0].toLowerCase().equals("lifestage") && isOp)
			{
				return getListOfStringsMatchingLastWord(params, new String[]
				{ "baby", "teen", "adult", "elder" });
			}
		}

        return null;
    }
    
    public int getRequiredPermissionLevel()
    {
        return 0;
    }
	
	@Override
	public void processCommand(ICommandSender sender, String[] params)
	{
		if(sender instanceof MinecraftServer)
		{
			
		}
		else if(sender instanceof EntityPlayer)
		{
			this.processCommandPlayer(sender, params, (EntityPlayer) sender);
		}
	}

	@Override
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender)
    {
    	return true;
    }
	
    @Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return "commands.Shurtugal.usage";
    }
    
	public void processCommandPlayer(ICommandSender sender, String[] params ,EntityPlayer player)
	{
		boolean isOp = MinecraftServer.getServer().getConfigurationManager().areCommandsAllowed(player.username);
		if (params.length >= 1 && params[0].length() > 0)
		{
			String command = params[0];

			if (!ShurtugalMod.proxy.isPlayerRider(player))
			{
				player.addChatMessage("commands.Shurtugal.notRider");
				return;
			}

			if (command.equals("call"))
			{
				EntityPlayer entityPlayer = (EntityPlayer) player;
				if (ActiveDragonList.getDragon(player) != null)
				{
					ActiveDragonList.getDragon(player).Despawn();
				}

				if(entityPlayer.worldObj.isRemote)
				{
					return;
				}
				EntityTameableDragon dragon = ShurtugalMod.proxy.getPlayersDragon(entityPlayer, entityPlayer.worldObj);
				dragon.setLocationAndAngles(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ, dragon.rotationYaw, dragon.rotationPitch);
				if(entityPlayer.worldObj.spawnEntityInWorld(dragon))
				{
					ActiveDragonList.putDragon(entityPlayer, dragon);	
				}

			}
			else if (command.equals("despawn"))
			{
				if (ActiveDragonList.getDragon(player) == null)
				{
					player.addChatMessage("commands.Shurtugal.notSpawn");
					return;
				}
				EntityTameableDragon dragon = ActiveDragonList.getDragon(player);
				dragon.Despawn();
			}
			else if (command.equals("lifestage"))
			{
				if (isOp)
				{
					if (ActiveDragonList.getDragon(player) == null)
					{
						player.addChatMessage("commands.Shurtugal.notSpawn");
						return;
					}
					EntityTameableDragon dragon = ActiveDragonList.getDragon(player);

					String lifestage = "";
					if (params.length > 1)
					{
						lifestage = params[1];
					}

					if (lifestage.equals("baby"))
					{
						dragon.setLifeStage(LifeStage.HATCHLING);
					}
					else if (lifestage.equals("teen"))
					{
						dragon.setLifeStage(LifeStage.JUVENILE);
					}
					else if (lifestage.equals("adult"))
					{
						dragon.setLifeStage(LifeStage.ADULT);
					}
					else if(lifestage.equals("elder"))
					{
						dragon.setLifeStage(LifeStage.ELDER);
					}
					else
					{
						throw new WrongUsageException("commands.Shurtugal.lifestage.usage");
					}
				}
				else
				{
					player.addChatMessage("commands.Shurtugal.notUse");
				}
			}

		}
		else
		{
			if(isOp)
			{
				throw new WrongUsageException("commands.Shurtugal.usage");
			}
			throw new WrongUsageException("commands.Shurtugal.usageNoOp");
		}
	}
	
}
