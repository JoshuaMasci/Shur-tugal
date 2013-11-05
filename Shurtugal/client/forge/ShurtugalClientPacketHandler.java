package Shurtugal.client.forge;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import Shurtugal.common.Network.DragonInventoryPacket;
import Shurtugal.common.Network.ParticleSpawnPacket;
import Shurtugal.common.util.MathX;
import Shurtugal.common.util.MovementInputProxy;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

public class ShurtugalClientPacketHandler implements IPacketHandler
{

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		if (packet.channel.equals("DragonGui"))
		{
			DragonInventoryPacket.unpack(packet);
		}
		else if(packet.channel.equals("ParticleSpawn"))
		{
			ParticleSpawnPacket.unpackAndUsePacket(manager, packet, player);
		}
	}
}
