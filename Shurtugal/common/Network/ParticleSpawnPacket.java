package Shurtugal.common.Network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;

public class ParticleSpawnPacket 
{
	public static Packet250CustomPayload createPacket(String par1Str, double posX, double posY, double posZ, double par8, double par10, double par12)
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		
		try
		{
			dos.writeUTF(par1Str);
			dos.writeDouble(posX);
			dos.writeDouble(posY);
			dos.writeDouble(posZ);
			dos.writeDouble(par8);
			dos.writeDouble(par10);
			dos.writeDouble(par12);
		}
		catch(IOException e)
		{
			
		}
		
		return new Packet250CustomPayload("ParticleSpawn", bos.toByteArray());
	}
	
	public static void unpackAndUsePacket(INetworkManager manager, Packet250CustomPayload packet, Player player)
	{
		EntityPlayer entPlayer = Minecraft.getMinecraft().thePlayer;
		double posX, posY, posZ, par8, par10, par12;
		String particleName;
		ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
		DataInputStream dis = new DataInputStream(bis);
		try
		{
			particleName = dis.readUTF();
			posX = dis.readDouble();
			posY = dis.readDouble();
			posZ = dis.readDouble();
			par8 = dis.readDouble();
			par10 = dis.readDouble();
			par12 = dis.readDouble();
			entPlayer.worldObj.spawnParticle(particleName, posX, posY, posZ, par8, par10, par12);
		}
		catch(IOException e)
		{
			
		}
		
	}
}
