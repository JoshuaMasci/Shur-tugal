package Shurtugal.common.Network;

import Shurtugal.ShurtugalMod;
import Shurtugal.common.DragonList.ActiveDragonList;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class ShurtugalConnectionHandler implements IConnectionHandler
{

	@Override
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager)
	{
		System.out.println("PlayerLogOn Event Called");

		if (player instanceof EntityPlayer)
		{
			EntityPlayer entityPlayer = (EntityPlayer) player;
			
			if (ShurtugalMod.proxy.isPlayerRider((EntityPlayer) player))
			{
				World world = MinecraftServer.getServer().worldServerForDimension(ShurtugalMod.proxy.getDragonDimension(entityPlayer));
				if (world != null && !world.isRemote)
				{
					EntityTameableDragon dragon = ShurtugalMod.proxy.getPlayersDragon(entityPlayer, world);
					dragon.setLocationAndAngles(dragon.posX, dragon.posY, dragon.posZ, dragon.rotationYaw, dragon.rotationPitch);
					if (world.spawnEntityInWorld(dragon))
					{
						ActiveDragonList.putDragon(entityPlayer, dragon);
					}
				}
			}
		}
	}

	@Override
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager)
	{
		return null;
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager)
	{
		
	}

	@Override
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager)
	{
		
	}

	@Override
	public void connectionClosed(INetworkManager manager)
	{
		
	}

	@Override
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login)
	{
	}

}
