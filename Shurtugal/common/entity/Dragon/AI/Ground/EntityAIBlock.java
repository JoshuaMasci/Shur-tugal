/*
 ** 2012 August 17
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.entity.Dragon.AI.Ground;

import Shurtugal.common.entity.Dragon.EntityBaseDragon;
import net.minecraft.entity.ai.EntityAIBase;

/**
 *
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAIBlock extends EntityAIBase {
    
    private final EntityBaseDragon entity;
    
    public EntityAIBlock(EntityBaseDragon entity) {
        this.entity = entity;
        
        // block ALL the tasks!
        setMutexBits(Integer.MAX_VALUE);
    }

    @Override
    public boolean shouldExecute() {
        return entity.isFlying();
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }
}
