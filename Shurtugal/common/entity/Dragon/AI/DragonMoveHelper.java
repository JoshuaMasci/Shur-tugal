/*
 ** 2012 March 20
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */

package Shurtugal.common.entity.Dragon.AI;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityMoveHelper;

public class DragonMoveHelper extends EntityMoveHelper {
    
    private EntityLiving entity;
    
    public DragonMoveHelper(EntityLiving entity) {
        super(entity);
        this.entity = entity;
    }

    @Override
    public void onUpdateMoveHelper() {
        float moveForward = entity.moveForward;
        super.onUpdateMoveHelper();
        // fix moveForward when not updating
        if (!isUpdating()) {
            entity.moveForward = moveForward;
        }
    }
}
