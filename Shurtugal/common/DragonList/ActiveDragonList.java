package Shurtugal.common.DragonList;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

import Shurtugal.common.entity.Dragon.EntityTameableDragon;

public class ActiveDragonList
{
	public static Map<String, EntityTameableDragon> activeDragons = new HashMap<String, EntityTameableDragon>();
	
	public static EntityTameableDragon getDragon(EntityPlayer player)
	{
		return getDragon(player.username);
	}
	
	public static EntityTameableDragon getDragon(String username)
	{
		if(activeDragons.containsKey(username))
		{
			return activeDragons.get(username);
		}
		return null;
	}
	
	public static void putDragon(EntityPlayer player, EntityTameableDragon dragon)
	{
		if(dragon.isServer())
		{
			activeDragons.put(player.username, dragon);	
		}
	}
	
	public static void removeDragon(String username)
	{
		activeDragons.remove(username);
	}
	
	public static void clear()
	{
		activeDragons.clear();
	}
}
