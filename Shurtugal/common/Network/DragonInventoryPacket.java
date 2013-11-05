package Shurtugal.common.Network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import Shurtugal.common.entity.Dragon.EntityBaseDragon;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;

/**
 * A simple class to inform clients of the armor of each dragon
 * 
 * @author Iamshortman
 * 
 */
public class DragonInventoryPacket
{

	public static Packet250CustomPayload createPacket(EntityBaseDragon dragon)
	{
		int ID = dragon.entityId;
		ItemStack Head = dragon.getCurrentItemOrArmor(1);
		ItemStack Body = dragon.getCurrentItemOrArmor(2);
		ItemStack Legs = dragon.getCurrentItemOrArmor(3);
		ItemStack Tail = dragon.getCurrentItemOrArmor(4);

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try
		{
			dos.writeInt(ID);
			Packet.writeItemStack(Head, dos);
			Packet.writeItemStack(Body, dos);
			Packet.writeItemStack(Legs, dos);
			Packet.writeItemStack(Tail, dos);
		}
		catch (IOException ex)
		{

		}

		return new Packet250CustomPayload("DragonGui", bos.toByteArray());
	}

	public static void unpack(Packet250CustomPayload packet)
	{
		// Reads the Packet
		ByteArrayInputStream bis = new ByteArrayInputStream(packet.data);
		DataInputStream dis = new DataInputStream(bis);
		int ID = 0;
		ItemStack Head = null;
		ItemStack Body = null;
		ItemStack Legs = null;
		ItemStack Tail = null;
		try
		{
			ID = dis.readInt();
			Head = Packet.readItemStack(dis);
			Body = Packet.readItemStack(dis);
			Legs = Packet.readItemStack(dis);
			Tail = Packet.readItemStack(dis);
			dis.close();
		}
		catch (IOException ex)
		{
		}
		// gets dragon instance
		if (ID != 0)
		{
			EntityTameableDragon dragon = null;
			try
			{
				Entity entity = Minecraft.getMinecraft().theWorld.getEntityByID(ID);
				dragon = (EntityTameableDragon) entity;
			}
			catch (ClassCastException ex)
			{
				System.out.println("ID: for the Entity Was incorect. we have a problem");
			}

			if (dragon != null)
			{
				// sets the dragons armor
				dragon.setCurrentArmor(0, Head);
				dragon.setCurrentArmor(1, Body);
				dragon.setCurrentArmor(2, Legs);
				dragon.setCurrentArmor(3, Tail);
			}

		}

	}

}
