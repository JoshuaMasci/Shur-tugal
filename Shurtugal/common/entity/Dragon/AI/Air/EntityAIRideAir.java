/*
 ** 2012 April 25
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.entity.Dragon.AI.Air;

import Shurtugal.common.Network.MovementInputPacketHandler;
import Shurtugal.common.entity.EntityFlyingTameable;
import Shurtugal.common.entity.Dragon.EntityBaseDragon;
import Shurtugal.common.entity.Dragon.AI.EntityAIRide;
import Shurtugal.common.util.ItemUtils;
import Shurtugal.common.util.MovementInputProxy;
import net.minecraft.item.Item;
import net.minecraft.util.Vec3;

/**
 * AI for player-controlled air movements.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIRideAir extends EntityAIRide
{

	public EntityAIRideAir(EntityBaseDragon dragon)
	{
		super(dragon);
	}

	@Override
	public void updateTask()
	{
		super.updateTask();

		MovementInputProxy playerInput = MovementInputPacketHandler.instance.getMovementInput(rider);
		double speed = dragon.func_110148_a(EntityFlyingTameable.MOVE_SPEED_AIR).func_111126_e();
		double dist = 100;

		if (rider.moveForward > 0)
		{
			Vec3 wp = rider.getLookVec();

			// scale with distance
			wp.xCoord *= dist;
			wp.yCoord *= dist;
			wp.zCoord *= dist;

			// convert to absolute position
			wp.xCoord += dragon.posX;
			wp.yCoord += dragon.posY;
			wp.zCoord += dragon.posZ;

			float verticalSpeed = 0;

			// control height with jumping/sneaking when hovering
			if (playerInput.jump)
			{
				verticalSpeed = (float) (speed * 0.5);
			}
			else if (playerInput.down)
			{
				verticalSpeed = (float) (-speed * 0.7);
			}

			dragon.setMoveSpeedAirVertical(verticalSpeed);

			dragon.getWaypoint().fromVector(wp);
			dragon.setMoveSpeedAir(speed);

		}
		else
		{
			Vec3 wp = dragon.getLookVec();

			// scale with distance
			wp.xCoord *= dist;
			wp.yCoord *= dist;
			wp.zCoord *= dist;

			// convert to absolute position
			wp.xCoord += dragon.posX;
			wp.yCoord += dragon.posY;
			wp.zCoord += dragon.posZ;

			dragon.setMoveSpeedAirVertical(0);
			float verticalSpeed = 0;

			// control height with jumping/sneaking when hovering
			if (playerInput.jump)
			{
				verticalSpeed = (float) (speed * 0.5);
			}
			else if (playerInput.down)
			{
				verticalSpeed = (float) (-speed * 0.7);
			}

			dragon.setMoveSpeedAirVertical(verticalSpeed);

			dragon.getWaypoint().fromVector(wp);

			double speedAir = 0;

			// change speed with forward
			if (rider.moveForward != 0)
			{
				speedAir = speed;

				// fly slower backwards
				// (I'm surprised this is kinda working at all...)
				if (rider.moveForward < 0)
				{
					speedAir *= 0.5;
				}

				speedAir *= rider.moveForward;
			}

			dragon.setMoveSpeedAir(speedAir);

			// control rotation with strafing
			if (rider.moveStrafing != 0)
			{
				dragon.rotationYaw -= rider.moveStrafing * 6;
			}
		}
	}
}
