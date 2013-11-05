/*
 ** 2012 March 18
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.entity.Dragon.AI;

import Shurtugal.common.entity.Dragon.EntityBaseDragon;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

/**
 * Abstract "AI" for player-controlled movements.
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public abstract class EntityAIRide extends EntityAIBase {

    protected final EntityBaseDragon dragon;
    protected EntityPlayer rider;

    public EntityAIRide(EntityBaseDragon dragon) {
        this.dragon = dragon;
        setMutexBits(Integer.MAX_VALUE);
    }
    
    @Override
    public boolean shouldExecute() {   
        rider = dragon.getRidingPlayer();
        return rider != null;
    }
}
