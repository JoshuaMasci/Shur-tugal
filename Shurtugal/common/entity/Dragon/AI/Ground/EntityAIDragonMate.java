/*
 ** 2012 August 26
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.entity.Dragon.AI.Ground;

import java.util.List;

import Shurtugal.common.entity.LifeStage;
import Shurtugal.common.entity.Dragon.EntityBaseDragon;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;

/**
 * Derivative EntityAIMate class to deal with some special values that can't be
 * applied with an extension thanks to the visibility.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIDragonMate extends EntityAIBase
{

	private EntityBaseDragon dragon;
	private EntityBaseDragon dragonMate;
	private World theWorld;
	private int spawnBabyDelay = 0;

	public EntityAIDragonMate(EntityBaseDragon dragon)
	{
		this.dragon = dragon;
		theWorld = dragon.worldObj;
		setMutexBits(3);
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		if (!dragon.isInLove())
		{
			return false;
		}
		else
		{
			dragonMate = getNearbyMate();
			return dragonMate != null;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting()
	{
		return dragonMate.isEntityAlive() && dragonMate.isInLove() && spawnBabyDelay < 60;
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		dragonMate = null;
		spawnBabyDelay = 0;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		double speed = dragon.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e() * 0.6;

		dragon.getLookHelper().setLookPositionWithEntity(dragonMate, 10.0F, (float) dragon.getVerticalFaceSpeed());
		dragon.getNavigator().tryMoveToEntityLiving(dragonMate, speed);

		++spawnBabyDelay;

		if (spawnBabyDelay == 60)
		{
			spawnBaby();
		}
	}

	/**
	 * Loops through nearby animals and finds another animal of the same type
	 * that can be mated with. Returns the first valid mate found.
	 */
	private EntityBaseDragon getNearbyMate()
	{
		double range = 12;
		List<Entity> nearbyEntities = theWorld.getEntitiesWithinAABB(EntityBaseDragon.class, dragon.boundingBox.expand(range, range, range));

		for (Entity entity : nearbyEntities)
		{
			EntityBaseDragon nearbyDragon = (EntityBaseDragon) entity;
			if (dragon.canMateWith(nearbyDragon))
			{
				return nearbyDragon;
			}
		}

		return null;
	}

	/**
	 * Spawns a baby animal of the same type.
	 */
	private void spawnBaby()
	{
		EntityBaseDragon dragonBaby = (EntityBaseDragon) dragon.createChild(dragonMate);

		if (dragonBaby != null)
		{
			// TODO: Drop Egg
		}
	}
}
