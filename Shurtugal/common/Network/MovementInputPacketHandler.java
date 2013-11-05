package Shurtugal.common.Network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import cpw.mods.fml.common.network.Player;

import Shurtugal.common.util.MathX;
import Shurtugal.common.util.MovementInputProxy;

public class MovementInputPacketHandler
{
	public static MovementInputPacketHandler instance = new MovementInputPacketHandler();

	private static Map<String, MovementInputProxy> moveMap = new HashMap<String, MovementInputProxy>();

	public void onPacket(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		EntityPlayer playerEnt = (EntityPlayer) player;
		MovementInputProxy input;

		if (!moveMap.containsKey(playerEnt))
		{
			moveMap.put(playerEnt.username, input = new MovementInputProxy());
		}
		else
		{
			input = moveMap.get(playerEnt);
		}

		unpack(packet, input);
	}

	private void unpack(Packet250CustomPayload packet, MovementInputProxy input)
	{
		ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
		DataInputStream dis = new DataInputStream(bis);
		try
		{
			input.moveForward = dis.readFloat();
			input.moveStrafe = dis.readFloat();
			input.jump = dis.readBoolean();
			input.down = dis.readBoolean();
			input.buttonGui = dis.readBoolean();
			dis.close();
		}
		catch (IOException ex)
		{
		}

		// validate speeds
		input.moveForward = MathX.clamp(input.moveForward, -1, 1);
		input.moveStrafe = MathX.clamp(input.moveStrafe, -1, 1);
	}

	public static MovementInputProxy getMovementInput(EntityPlayer player)
	{
		return moveMap.get(player.username);
	}

	public static void clear()
	{
		moveMap.clear();
	}

}
