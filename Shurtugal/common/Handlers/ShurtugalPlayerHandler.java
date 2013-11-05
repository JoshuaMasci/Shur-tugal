package Shurtugal.common.Handlers;

import Shurtugal.ShurtugalMod;
import Shurtugal.common.DragonList.ActiveDragonList;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ShurtugalPlayerHandler 
{
	@ForgeSubscribe
	public void OnLivingDeath(LivingDeathEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			System.out.println("Player on death Called For " + player.username);
			if(ShurtugalMod.proxy.isPlayerRider(player))
			{
				if(!player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
				{
					EntityTameableDragon dragon = ActiveDragonList.getDragon(player);
					dragon.Despawn();	
				}
				ShurtugalMod.proxy.killDragon(player, null);
			}
		}
	}
	
}
