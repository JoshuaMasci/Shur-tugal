package Shurtugal.common.entity.Dragon;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentThorns;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import Shurtugal.client.model.anim.DragonAnimator;
import Shurtugal.common.Item.ItemHandler;
import Shurtugal.common.entity.EntityFlyingTameable;
import Shurtugal.common.entity.LifeStage;
import Shurtugal.common.entity.Dragon.AI.DragonBodyHelper;
import Shurtugal.common.entity.Dragon.AI.DragonMoveHelper;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class EntityBaseDragon extends EntityFlyingTameable
{

	// life stages
	public LifeStage lifeStage = LifeStage.HATCHLING;
	public LifeStage lifeStagePrev = LifeStage.HATCHLING;

	// client-only stuff
	public DragonAnimator animator;

	public static final double BASE_SPEED = 0.3;
	public static final double BASE_DAMAGE = 8;
	public static final int BASE_HEALTH = 60;
	public static final int BASE_WIDTH = 4;
	public static final int BASE_HEIGHT = 3;
	public static final int HOME_RADIUS = 256;

	private static final int INDEX_HEALTH = 20;
	public static final int INDEX_COLOR = 21;

	/**
	 * Should ONLY be used Server side, Client will use data watcher.
	 */
	public int Color = 0;

	public EntityBaseDragon(World world, int i)
	{
		super(world);
		// HAAAAAX! Private and no setter available...
		try
		{
			// required to enable moving with non-waypoint player controlled AIs
			// (riding)
			ObfuscationReflectionHelper.setPrivateValue(EntityLiving.class, this, new DragonMoveHelper(this), "moveHelper", "field_70765_h", "i");
			// required to fixate body while sitting. also slows down rotation
			// while standing.
			ObfuscationReflectionHelper.setPrivateValue(EntityLiving.class, this, new DragonBodyHelper(this), "bodyHelper", "field_70762_j", "bn");
		}
		catch (Exception ex)
		{

		}

		isImmuneToFire = true;
		stepHeight = 1;
		ignoreFrustumCheck = true;

		if (isClient())
		{
			animator = new DragonAnimator(this);
		}
		else
		{
			// too big!
			getNavigator().setEnterDoors(false);
			this.Color = i;
			// tasks.addTask(0, new EntityAIBlock(this)); // mutex all
		}
		onNewLifeStage();
	}

	public EntityBaseDragon(World world)
	{
		this(world, 0);
	}

	@Override
	protected void func_110147_ax()
	{
		super.func_110147_ax();
		func_110140_aT().func_111150_b(SharedMonsterAttributes.field_111264_e);
		func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(BASE_HEALTH * getSize()); // maxHealth
		func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(BASE_SPEED); // movementSpeed
		func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(BASE_DAMAGE * getSize()); // attackDamage
	}

	public int GetColor()
	{
		return this.dataWatcher.getWatchableObjectInt(INDEX_COLOR);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(INDEX_HEALTH, 0);
		dataWatcher.addObject(INDEX_COLOR, 0);
	}

	/**
	 * Returns the 0 if the dragon is not saddled. Returns 1 if has normal
	 * saddle. Returns 2 if has saddle bag.
	 */
	public int isSaddled()
	{
		return 0;
	}

	public DragonAnimator getAnimator()
	{
		return animator;
	}

	@Deprecated
	// so I don't accidentally use it...
	public int getDragonHealth()
	{
		// getDragonHealth() and getGrowingAge() have the exact same signature
		// after obfuscation! Fix it by returning the value of getGrowingAge()
		// and implementing getDragonHealth() somewhere else...
		return super.getGrowingAge();
	}

	/**
	 * Actually returns the health points of the dragon (used by clients only).
	 */
	public int getDragonHealth2()
	{
		return this.dataWatcher.getWatchableObjectInt(INDEX_HEALTH);
	}

	public void setDragonHealth(int health)
	{
		dataWatcher.updateObject(INDEX_HEALTH, health);
	}

	/**
	 * Returns the size multiplier for the current age.
	 * 
	 * @return size
	 */
	public float getSize()
	{
		return getLifeStage().getSize();
	}

	/**
	 * Returns the current life stage of the dragon.
	 * 
	 * @return current life stage
	 */
	public LifeStage getLifeStage()
	{
		// lifeStage can be null when called in constructor
		return lifeStage == null ? LifeStage.HATCHLING : lifeStage;
	}

	// *****************UPDATEING**************************************************

	@Override
	public void onLivingUpdate()
	{
		int age = getGrowingAge();
		lifeStage = LifeStage.getLifeStageForAge(age);

		if (isServer())
		{
			dataWatcher.updateObject(INDEX_COLOR, Color);
		}

		// trigger event when a new life stage was reached
		if (lifeStagePrev != lifeStage)
		{
			onNewLifeStage();
			lifeStagePrev = lifeStage;
		}

		// temporary fix to stop dragon babies from sitting around
		// uncontrollably
		if (isServer() && isSitting() && (lifeStage == LifeStage.HATCHLING || lifeStage == LifeStage.JUVENILE))
		{
			aiSit.setSitting(false);
		}

		if (isClient())
		{

			animator.setOnGround(!isFlying());
			animator.update();
		}
		else
		{
			setDragonHealth((int) this.func_110143_aJ());
		}
		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
	}

	public boolean hasDroppedEldunari = false;

	@Override
	protected void onDeathUpdate()
	{
		super.onDeathUpdate();
		if (riddenByEntity != null)
		{
			riddenByEntity.mountEntity(null);
		}

		if (!this.hasDroppedEldunari && !this.worldObj.isRemote && (lifeStage == lifeStage.ADULT || lifeStage == lifeStage.ELDER))
		{
			ItemStack item = new ItemStack(ItemHandler.eldunari.itemID, 1, this.GetColor());
			this.entityDropItem(item, 0.0F);
			this.hasDroppedEldunari = true;
		}

	}

	/**
	 * Returns the height of the eyes. Used for looking at other entities.
	 */
	@Override
	public float getEyeHeight()
	{
	      float eyeHeight = height;
	        
	        eyeHeight *= 2 + getHealthRelative() * 2;
	        
	        if (isSitting()) {
	            eyeHeight *= 0.8f;
	        }
	        
	        return eyeHeight;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		super.dropFewItems(par1, par2);
	}

	protected void spawnBodyParticle(String effect)
	{
		double ox, oy, oz;
		float s = getSize() * 1.2f;

		if (effect.equals("explode"))
		{
			ox = rand.nextGaussian() * s;
			oy = rand.nextGaussian() * s;
			oz = rand.nextGaussian() * s;
		}
		else if (effect.equals("cloud"))
		{
			ox = (rand.nextDouble() - 0.5) * 0.1;
			oy = rand.nextDouble() * 0.2;
			oz = (rand.nextDouble() - 0.5) * 0.1;
		}
		else if (effect.equals("reddust"))
		{
			ox = 0.8;
			oy = 0;
			oz = 0.8;
		}
		else
		{
			ox = 0;
			oy = 0;
			oz = 0;
		}

		// use generic random box spawning
		double x = posX + (rand.nextDouble() - 0.5) * width * s;
		double y = posY + (rand.nextDouble() - 0.5) * height * s;
		double z = posZ + (rand.nextDouble() - 0.5) * width * s;

		worldObj.spawnParticle(effect, x, y, z, ox, oy, oz);
	}

	protected void spawnBodyParticles(String effect, int baseAmount)
	{
		int amount = (int) (baseAmount * getSize());
		for (int i = 0; i < amount; i++)
		{
			spawnBodyParticle(effect);
		}
	}

	protected void spawnBodyParticles(String effect)
	{
		spawnBodyParticles(effect, 32);
	}

	/**
	 * Spawns an explosion particle around the Entity's location
	 */
	@Override
	public void spawnExplosionParticle()
	{
		spawnBodyParticles("explode");
	}

	public void onWingsDown(float speed)
	{
		// play wing sounds
		float pitch = 0.8f + (0.5f - rand.nextFloat()) * 0.3f + (1 - speed) * 0.2f;
		pitch *= getSize();
		float volume = 0.3f + (1 - speed) * 0.2f;

		worldObj.playSound(posX, posY, posZ, getWingsSound(), volume, pitch, false);
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound()
	{
		if (isFlying())
		{
			return "";
		}
		else
		{
			return "mob.enderdragon.idle";
		}
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound()
	{
		return "mob.enderdragon.hit";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound()
	{
		return "mob.enderdragon.death";
	}

	/**
	 * Returns the sound this mob makes when it beats its wings.
	 */
	protected String getWingsSound()
	{
		return inWater ? null : "mob.enderdragon.wings";
	}

	/**
	 * Plays step sound at given x, y, z for the entity
	 */
	@Override
	protected void playStepSound(int x, int y, int z, int blockId)
	{
		if (inWater)
		{

		}
		else if (lifeStage == LifeStage.HATCHLING)
		{
			// play default step sound for babies
			super.playStepSound(x, y, z, blockId);
		}
		else
		{
			// play stomping for bigger dragons
			worldObj.playSoundAtEntity(this, "mob.enderdragon.walk", 0.5f, 1);
		}
	}

	/**
	 * Plays living's sound at its position
	 */
	@Override
	public void playLivingSound()
	{
		String sound = getLivingSound();

		if (sound != null)
		{
			this.playSound(sound, getSoundVolume(), getSoundPitch() * 0.5f);
		}
	}

	public String getTexture(int index)
	{
		switch (index)
		{
		case 0:
			return getcoloredbody(this.GetColor());
		case 1:
			return "/textures/dragon/armor/body_saddle.png";
		default:
			return null;
		}
	}

	public String getcoloredbody(int color)
	{
		switch (color)
		{
		case 0:
			return "/textures/dragon/dragonbody_black.png";
		case 1:
			return "/textures/dragon/dragonbody_blue.png";
		case 2:
			return "/textures/dragon/dragonbody_brown.png";
		case 3:
			return "/textures/dragon/dragonbody_green.png";
		case 4:
			return "/textures/dragon/dragonbody_pink.png";
		case 5:
			return "/textures/dragon/dragonbody_purple.png";
		case 6:
			return "/textures/dragon/dragonbody_red.png";
		case 7:
			return "/textures/dragon/dragonbody_white.png";
		case 8:
			return "/textures/dragon/dragonbody_yellow.png";
		case 9:
			return "/textures/dragon/dragonbody_orange.png";
		}

		return null;
	}

	/**
	 * Returns the texture's file path as a String.
	 */
	public String getTexture()
	{
		return getTexture(0);
	}

	/**
	 * Sets a new life stage for the dragon.
	 * 
	 * @param lifeStage
	 *            new life stage
	 */
	public final void setLifeStage(LifeStage lifeStage)
	{
		setGrowingAge(lifeStage.getAgeLimit());
	}

	/**
	 * Called when the dragon enters a new life stage.
	 */
	public void onNewLifeStage()
	{

		this.setCanFly(lifeStage != LifeStage.HATCHLING);

		if (isClient())
		{
			spawnExplosionParticle();
		}
		else
		{
			// heal dragon to updated full health
			setEntityHealth(getMaxHealth());
		}

		float hitboxSize = getSize() * 3;
		setSize(hitboxSize, hitboxSize);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	@Override
	public boolean canBeCollidedWith()
	{
		return super.canBeCollidedWith();
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	@Override
	protected void fall(float par1)
	{
		// this thing's got wings!
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities
	 * when colliding.
	 */
	@Override
	public boolean canBePushed()
	{
		// one does not simply push a dragon!
		return false;
	}

	/**
	 * Determines if an entity can be despawned, used on idle far away entities
	 */
	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	/**
	 * returns true if this entity is by a ladder, false otherwise
	 */
	@Override
	public boolean isOnLadder()
	{
		// this better doesn't happen...
		return false;
	}

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

			// ignore damage from rider
			if (srcEnt == riddenByEntity)
			{
				return true;
			}
		}

		// ignore suffocation damage
		if (src.damageType.equals("inWall"))
		{
			return true;
		}

		return false;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource src, float par2)
	{
		if (isImmuneToDamage(src))
		{
			return false;
		}

		// don't just sit there!
		aiSit.setSitting(false);

		// growl when being attacked
		if (src.getEntity() != null && src.getEntity() != getAITarget())
		{
			worldObj.playSoundAtEntity(this, "mob.enderdragon.growl", 1, 0.5f + (1 / lifeStage.getSize()) * 0.5f);
		}
		return super.attackEntityFrom(src, par2);
	}

    @Override
    public boolean attackEntityAsMob(Entity victim) {
        float attackDamage = (float) this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        int knockback = 0;

        if (victim instanceof EntityLivingBase) {
            attackDamage += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase) victim);
            knockback += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase) victim);
        }

        boolean attacked = victim.attackEntityFrom(DamageSource.causeMobDamage(this), attackDamage);

        if (attacked) {
            if (knockback > 0) {
                double vx = -Math.sin(Math.toRadians(rotationYaw)) * knockback * 0.5;
                double vy = 0.1;
                double vz = Math.cos(Math.toRadians(rotationYaw)) * knockback * 0.5;
                victim.addVelocity(vx, vy, vz);
                
                motionX *= 0.6;
                motionZ *= 0.6;
            }

            int fireAspect = EnchantmentHelper.getFireAspectModifier(this);

            if (fireAspect > 0) {
                victim.setFire(fireAspect * 4);
            }

            if (victim instanceof EntityLivingBase) {
                EnchantmentThorns.func_92096_a(this, (EntityLivingBase) victim, rand);
            }
            
            func_130011_c(victim);
            
            // play eating sound
            float volume = getSoundVolume() * 0.7f;
            float pitch = getSoundPitch();
            worldObj.playSoundAtEntity(this, "random.eat", volume, pitch);
        }

        return attacked;
    }

	public int getDeathTime()
	{
		return deathTime;
	}

    public double getHealthRelative() 
    {
        return func_110143_aJ() / (double) func_110138_aP();
    }
	
	public int getMaxHealth()
	{
		return (int) (80 * getSize());
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Color", Color);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		Color = nbt.getInteger("Color");
	}

	public void setDragonName(String str)
	{
		this.setCustomNameTag(str);
	}

	public String getDragonName()
	{
		String tempStr = this.getCustomNameTag();
		return !tempStr.equals("") ? tempStr : super.getEntityName();
	}

	@Override
	public boolean getAlwaysRenderNameTag()
	{
		return false;
	}

	@Override
	public boolean getAlwaysRenderNameTagForRender()
	{
		return false;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		// TODO at some point
		return null;
	}

	public EntityPlayer getRidingPlayer()
	{
		// No Rider For base Dragon
		return null;
	}
}
