package Shurtugal.client.forge;

import java.util.EnumSet;

import Shurtugal.common.entity.Dragon.EntityTameableDragon;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class GuiTickHandler implements ITickHandler
{

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (player != null && player.ridingEntity != null)
		{
			if (player.ridingEntity instanceof EntityTameableDragon)
			{
				EntityTameableDragon dragon = (EntityTameableDragon) player.ridingEntity;
				if (ClientProxy.openGui.pressed)
				{
					dragon.inventory.OpenGui(player);
					player.addChatMessage(player.username + " Has Tried to open Dragon Gui");
				}
			}

		}

	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.WORLD, TickType.CLIENT);
	}

	@Override
	public String getLabel()
	{
		return "Gui Tick Shurtugal";
	}

}
