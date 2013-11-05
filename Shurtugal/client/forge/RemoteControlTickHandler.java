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

import Shurtugal.ShurtugalMod;
import Shurtugal.common.Handlers.ConfigHandler;
import Shurtugal.common.Handlers.DragonKeyHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.common.network.PacketDispatcher;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.MovementInput;

/**
 * 
 * @author Iamshortman
 */
public class RemoteControlTickHandler implements ITickHandler
{

	private static final Logger L = Logger.getLogger(RemoteControlTickHandler.class.getName());

	private final Minecraft client = Minecraft.getMinecraft();
	private MovementInput previousInput = new MovementInput();
	private boolean PrevGuiPress = false;
	private boolean PrevDownPress = false;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData)
	{
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData)
	{
		if (client.thePlayer != null && movementChanged(client.thePlayer))
		{
			PacketDispatcher.sendPacketToServer(createPacket(client.thePlayer));
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
		return getClass().getSimpleName();
	}

	public Packet createPacket(EntityPlayerSP player)
	{
		MovementInput input = player.movementInput;

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);

		try
		{
			
			dos.writeFloat(input.moveForward);
			dos.writeFloat(input.moveStrafe);
			dos.writeBoolean(input.jump);
			dos.writeBoolean(ClientProxy.flyDown.pressed);
			dos.writeBoolean(ClientProxy.openGui.pressed);
			dos.close();
		}
		catch (IOException ex)
		{
			L.log(Level.WARNING, "Can't pack packet", ex);
		}

		return new Packet250CustomPayload("PlayerMoveInput", bos.toByteArray());
	}

	public boolean movementChanged(EntityClientPlayerMP player)
	{
		MovementInput currentInput = player.movementInput;

		boolean changed = false;

		if (previousInput.jump != currentInput.jump)
		{
			previousInput.jump = currentInput.jump;
			changed = true;
		}

		if (this.PrevDownPress != ClientProxy.flyDown.pressed)
		{
			this.PrevDownPress = ClientProxy.flyDown.pressed;
			changed = true;
		}

		if (previousInput.moveForward != currentInput.moveForward)
		{
			previousInput.moveForward = currentInput.moveForward;
			changed = true;
		}

		if (previousInput.moveStrafe != currentInput.moveStrafe)
		{
			previousInput.moveStrafe = currentInput.moveStrafe;
			changed = true;
		}

		if (this.PrevGuiPress != ClientProxy.openGui.pressed)
		{
			this.PrevGuiPress = ClientProxy.openGui.pressed;
			changed = true;
		}

		return changed;
	}
}
