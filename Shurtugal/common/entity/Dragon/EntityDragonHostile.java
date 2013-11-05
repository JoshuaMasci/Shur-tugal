package Shurtugal.common.entity.Dragon;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.entity.LifeStage;
import Shurtugal.common.tileEntity.*;
import Shurtugal.client.model.anim.DragonAnimator;
import Shurtugal.common.util.ItemUtils;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.util.Vec3Pool;
import net.minecraft.world.World;

public class EntityDragonHostile extends EntityBaseDragon implements IBossDisplayData
{
	public int EggLocX = 0;
	public int EggLocY = 0;
	public int EggLocZ = 0;

	public EntityDragonHostile(World world)
	{
		super(world);
		Color = Colors.White;
		setLifeStage(LifeStage.ADULT);
		this.experienceValue = 500;
		float hitboxSize = getSize() * 3;
		setSize(hitboxSize * 2, hitboxSize * 2);
	}

	public EntityDragonHostile(World world, int X, int Y, int Z)
	{
		this(world);
		this.EggLocX = X;
		this.EggLocY = Y;
		this.EggLocZ = Z;
		float hitboxSize = getSize() * 3;
		setSize(hitboxSize * 2, hitboxSize * 2);
	}

	public EntityLivingBase Target;
	public int TimeInAir = 0;
	public float lookYaw = 0;
	public float lookPitch = 0;
	public int attackCounter = 0;

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();

		if (isClient() && Target != null)
		{
			AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(posX - 25, posY - 25, posZ - 25, posX + 25, posY + 25, posZ + 25);
			List<Entity> entities = worldObj.getEntitiesWithinAABB(EntityPlayer.class, bb);
			if (entities.size() > 0)
			{
				for (Entity entity : entities)
				{
					if (entity instanceof EntityPlayer && entity == Minecraft.getMinecraft().thePlayer)
					{
						BossStatus.func_82824_a(this, false);
					}
				}
			}

		}

		if (Target == null)
		{
			Entity temp = this.worldObj.getClosestVulnerablePlayerToEntity(this, 50.0D);
			if (temp != null)
			{
				Target = (EntityLivingBase) temp;
			}
		}

		if (isFlying())
		{
			TimeInAir++;
		}
		else
		{
			TimeInAir = 0;
		}

		if (Target != null)
		{
			// an attempt to get the dragon to look at the player wile flying
			float Xdist = (float) (Target.posX - this.posX);
			float Zdist = (float) (Target.posZ - this.posZ);

			lookYaw = (float) Math.toDegrees(Math.atan2(Zdist, Xdist)) - 90;

			this.rotationYaw = lookYaw;
			this.renderYawOffset = lookYaw;
			this.rotationYawHead = lookYaw;

			float Ydist = (float) (Target.posY - this.posY);
			Vec3 dragonVec1 = Vec3.createVectorHelper(this.posX, 0, this.posZ);
			Vec3 targetVec1 = Vec3.createVectorHelper(Target.posX, 0, Target.posZ);
			float Hdist = (float) dragonVec1.distanceTo(targetVec1);
			this.getLookHelper().setLookPosition(Target.posX, Target.posY + Target.getEyeHeight(), Target.posZ, 10, this.getVerticalFaceSpeed());

			// System.out.println("X:"+ Xdist +" Z:" + Zdist +" R:" +
			// newRotationYaw);
			attackCounter++;

			if (TimeInAir > 100)
			{

				if (Hdist > 20)
				{
					double midX = (Target.posX + posX) / 2;
					double midZ = (Target.posZ + posZ) / 2;
					this.setWaypoint(midX, Target.posY + 10, midZ);
				}
				else
				{
					this.getWaypoint().clear();
				}

				if (((attackCounter > 20 && attackCounter < 40) || (attackCounter > 50 && attackCounter < 70) || (attackCounter > 100 && attackCounter < 120)) && func_110143_aJ() > 0)
				{
					if (!isClient())
					{

						double var11 = this.Target.posX - this.posX;
						double var13 = this.Target.boundingBox.minY - (this.posY + (double) (this.height / 2.0F));
						double var15 = this.Target.posZ - this.posZ;

						for (int i = 0; i < 4; i++)
						{
							EntitySmallFireball var17 = new EntitySmallFireball(this.worldObj, this, var11, var13, var15);
							double var18 = 4.0D;
							
							Vec3 var20 = this.getLook(1.0F);
							var17.posX = this.posX + var20.xCoord * var18;
							var17.posY = this.posY + this.getEyeHeight() - 3.2;
							var17.posZ = this.posZ + var20.zCoord * var18;
							this.worldObj.spawnEntityInWorld(var17);
						}

					}
				}
				else if (Hdist > 100)
				{
					Target = null;
				}
			}

			if (attackCounter > 150)
			{
				attackCounter = 0;
			}
		}

		if (isClient())
		{
			animator.setOnGround(!isFlying());
			animator.update();
			if (Target != null)
			{
				this.animator.setLook(lookYaw, lookPitch);
			}
		}
		else
		{
			if (!this.isFlying() && this.Target != null)
			{
				this.takeoff();
				this.setWaypoint(posX, posY + 5, posZ);
			}
		}
	}

	// data value IDs
	public static final int INDEX_HEALTH = 22;

	@Override
	public void onDeathUpdate()
	{
		super.onDeathUpdate();
		if (!worldObj.isRemote)
		{
			TileEntity entity = this.worldObj.getBlockTileEntity(EggLocX, EggLocY, EggLocZ);
			if (entity != null && entity instanceof TileEntityWhiteTrigger)
			{
				TileEntityWhiteTrigger trigger = (TileEntityWhiteTrigger) entity;
				trigger.spawnegg();
			}
		}
		if (this.deathTime == 1 && !this.worldObj.isRemote)
		{
			int i = 1000;

			while (i > 0)
			{
				int j = EntityXPOrb.getXPSplit(i);
				i -= j;
				this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
			}
		}

	}

	public void takeoff()
	{
		this.getJumpHelper().setJumping();
		this.jump();
		this.setWaypoint(posX, posY + 5, posZ);
	}

	private void setWaypoint(double posX, double posY, double posZ)
	{
		this.getWaypoint().fromVector(Vec3.fakePool.getVecFromPool(posX, posY, posZ));
	}

	protected void entityInit()
	{
		super.entityInit();
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		return false;
	}

	@Override
	public boolean isImmuneToDamage(DamageSource src)
	{
		Entity srcEnt = src.getEntity();
		if (srcEnt != null)
		{
			// ignore own damage
			if (srcEnt == this)
			{
				return true;
			}
			if (srcEnt instanceof EntityPlayer)
			{
				this.Target = (EntityLivingBase) srcEnt;
			}
		}
		// ignore damage from walls
		if (src.equals(DamageSource.inWall))
		{
			return true;
		}
		return false;
	}

	@Override
	protected void fall(float par1)
	{
		// this thing's got wings!
	}

	@Override
	public String getEntityName()
	{
		return "Mother Dragon";
	}

	@Override
	public double getAltitude()
	{
		// Should Make Dragon do Hover Animation always unless moving
		return 10;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("EggX", EggLocX);
		nbt.setInteger("EggY", EggLocY);
		nbt.setInteger("EggZ", EggLocZ);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		EggLocX = nbt.getInteger("EggX");
		EggLocY = nbt.getInteger("EggY");
		EggLocZ = nbt.getInteger("EggZ");
	}

}