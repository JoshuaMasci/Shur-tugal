/*
 ** 2012 April 25
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.entity.Dragon.AI.Ground;

import Shurtugal.common.Network.MovementInputPacketHandler;
import Shurtugal.common.entity.Dragon.EntityBaseDragon;
import Shurtugal.common.entity.Dragon.AI.EntityAIRide;
import Shurtugal.common.util.ItemUtils;
import Shurtugal.common.util.MovementInputProxy;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;

/**
 * AI for player-controlled ground movements.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIRideGround extends EntityAIRide
{

	public EntityAIRideGround(EntityBaseDragon dragon)
	{
		super(dragon);
	}

	@Override
	public void startExecuting()
	{
		dragon.getNavigator().clearPathEntity();
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		MovementInputProxy playerInput = MovementInputPacketHandler.instance.getMovementInput(rider);
		double speed = dragon.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e();

		if (rider.moveForward > 0)
		{
			dragon.rotationYaw = rider.rotationYaw;
			dragon.prevRotationYaw = rider.prevRotationYaw;
			dragon.renderYawOffset = dragon.rotationYaw;

			dragon.setAIMoveSpeed((float) speed);
		}
		else
		{
			dragon.setAIMoveSpeed(0);

			// rotate with strafing
			if (rider.moveStrafing != 0)
			{
				dragon.rotationYaw -= rider.moveStrafing * 6;
				dragon.renderYawOffset = dragon.rotationYaw;
			}
		}

		// lift off by Jumping
		if (playerInput != null && playerInput.jump)
		{
			dragon.liftOff();
		}
	}
}
