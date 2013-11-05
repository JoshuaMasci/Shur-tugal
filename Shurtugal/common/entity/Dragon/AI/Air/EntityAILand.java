/*
 ** 2013 July 28
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.entity.Dragon.AI.Air;

import Shurtugal.common.entity.EntityFlyingTameable;
import Shurtugal.common.entity.Dragon.EntityBaseDragon;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

/**
 * Dragon AI for instant landing, if left unmounted in air.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class EntityAILand extends EntityAIBase {
    
    private final EntityBaseDragon dragon;
    private Vec3 landTarget;
    
    public EntityAILand(EntityBaseDragon dragon) {
        this.dragon = dragon;
    }

    @Override
    public boolean shouldExecute() {
        if (!dragon.isFlying() || dragon.getRidingPlayer() != null) {
            return false;
        }
        
        landTarget = RandomPositionGenerator.findRandomTarget(dragon, 16, 256);
        
        return landTarget != null;
    }

    @Override
    public void startExecuting() {
        landTarget.yCoord = 0;
        dragon.getWaypoint().fromVector(landTarget);
        
        double speed = dragon.func_110148_a(EntityFlyingTameable.MOVE_SPEED_AIR).func_111126_e();
        dragon.setMoveSpeedAir(speed);
        
//        World world = dragon.worldObj;
//        
//        // get the material of the block beneath the waypoint
//        ChunkCoordinates cc = new ChunkCoordinates();
//        cc.posX = (int) (dragon.posX - 0.5);
//        cc.posZ = (int) (dragon.posZ - 0.5);
//        double terrainHeight = dragon.worldObj.getHeightValue(cc.posX, cc.posZ);
//        cc.posY = (int) (terrainHeight - 0.5);
//        int blockBeneath = world.getBlockId(blockX, blockY - 1, blockZ);
//        Material material = blockBeneath != 0 ? Block.blocksList[blockBeneath].blockMaterial : Material.air;
//
//        // accept only if the landing block isn't liquid
//        if (material.isLiquid()) {
//            wp = null;
//            continue;
//        }
    }
}
