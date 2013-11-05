/*
 ** 2012 August 13
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.common.entity.Dragon;


import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.network.PacketDispatcher;

import Shurtugal.ShurtugalMod;
import Shurtugal.common.entity.EntityFlyingTameable;
import Shurtugal.common.entity.LifeStage;
import Shurtugal.client.forge.ClientProxy;
import Shurtugal.client.model.anim.DragonAnimator;
import Shurtugal.common.DragonList.ActiveDragonList;
import Shurtugal.common.Network.DragonInventoryPacket;
import Shurtugal.common.Network.MovementInputPacketHandler;
import Shurtugal.common.entity.Dragon.AI.Air.EntityAILand;
import Shurtugal.common.entity.Dragon.AI.Air.EntityAIRideAir;
import Shurtugal.common.entity.Dragon.AI.Ground.EntityAIBlock;
import Shurtugal.common.entity.Dragon.AI.Ground.EntityAIHunt;
import Shurtugal.common.entity.Dragon.AI.Ground.EntityAIPanicChild;
import Shurtugal.common.entity.Dragon.AI.Ground.EntityAIRideGround;
import Shurtugal.common.entity.Dragon.AI.Ground.EntityAIWatchIdle;
import Shurtugal.common.entity.Dragon.AI.Ground.EntityAIWatchLiving;
import Shurtugal.common.entity.Dragon.Inventory.DragonInventory;
import Shurtugal.common.util.ItemUtils;
import Shurtugal.common.util.MovementInputProxy;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.ISpecialArmor.ArmorProperties;

/**
 * 
 * @author Iamshortman
 */
public class EntityTameableDragon extends EntityBaseDragon
{

	private static final Logger L = Logger.getLogger(EntityTameableDragon.class.getName());

	// data value IDs
	private static final int INDEX_SADDLED = 22;

	public DragonInventory inventory;

	private float moveSpeed = 0.7F;

	// data NBT IDs
	private static final String NBT_SADDLED = "Saddle";
	private static final String NBT_Inventory = "Inventory";

	public EntityTameableDragon(World world)
	{
		super(world);
		this.inventory = new DragonInventory(this);
		
		if (isClient())
		{

		}
		else
		{
            // mutex 1: movement
            // mutex 2: looking
            // mutex 4: special state
            tasks.addTask(0, new EntityAIBlock(this)); // mutex all
            tasks.addTask(1, new EntityAIRideGround(this)); // mutex all
            tasks.addTask(2, new EntityAISwimming(this)); // mutex 4
            tasks.addTask(3, aiSit); // mutex 4+1
            tasks.addTask(6, new EntityAIAttackOnCollide(this, 1, true)); // mutex 2+1
            tasks.addTask(7, new EntityAIFollowParent(this, 0.8f)); // mutex 2+1
//            tasks.addTask(8, new EntityAIFollowOwnerGround(this, 12, 24, 32)); // mutex 2+1
            tasks.addTask(8, new EntityAIPanicChild(this, 1)); // mutex 1
            tasks.addTask(9, new EntityAIWander(this, 1)); // mutex 1
            tasks.addTask(10, new EntityAIWatchIdle(this)); // mutex 2
            tasks.addTask(10, new EntityAIWatchLiving(this, 16, 0.05f)); // mutex 2
            
            // mutex 1: waypointing
            // mutex 2: continuous waypointing
            airTasks.addTask(0, new EntityAIRideAir(this)); // mutex all
            airTasks.addTask(0, new EntityAILand(this)); // mutex all
            
            // mutex 1: generic targeting
            targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this)); // mutex 1
            targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this)); // mutex 1
            targetTasks.addTask(3, new EntityAIHurtByTarget(this, false)); // mutex 1
            targetTasks.addTask(4, new EntityAIHunt(this, EntitySheep.class, 200, false)); // mutex 1
            targetTasks.addTask(4, new EntityAIHunt(this, EntityPig.class, 200, false)); // mutex 1
            targetTasks.addTask(4, new EntityAIHunt(this, EntityChicken.class, 200, false)); // mutex 1
		}

	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(INDEX_SADDLED, 0);
	}

	/**
	 * A simple counter that will send a packet to all clients about the dragons
	 * armor
	 */
	private int counter = 0;

	@Override
	public void onLivingUpdate()
	{		
		super.onLivingUpdate();
		if(this.isServer() && !this.getOwnerName().equals(""))
		{
			if(ActiveDragonList.getDragon(this.getOwnerName()) != null && ActiveDragonList.getDragon(this.getOwnerName()).entityId != this.entityId)
			{
				this.setDead();
			}
			if(this.isOwnerOnline(this.getOwnerName()))
			{
				ShurtugalMod.proxy.updatePlayerDragon((EntityPlayer) this.getPlayer(this.getOwnerName()), this);
			}
		}
		
		if (this.isServer() && this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) this.riddenByEntity;
			MovementInputProxy input = MovementInputPacketHandler.getMovementInput(player);
			if (input != null && input.buttonGui)
			{
				this.inventory.OpenGui(player);
			}
		}
		if (this.isServer())
		{	
			
			if (counter == 0)
			{	
				PacketDispatcher.sendPacketToAllAround(posX, posY, posZ, 200, this.worldObj.provider.dimensionId, DragonInventoryPacket.createPacket(this));
				counter = 50;
			}
			else if (counter > 0)
			{
				counter--;
			}
		}
	}

	@Override
	public void setDead()
	{
		super.setDead();
	}
	
	@Override
	public void onDeathUpdate()
	{
		this.inventory.DropAllItems(true);
		super.onDeathUpdate();
	}
	
	/** 
	 * used By Dragon list to save only too the dragon file
	 * @param nbt
	 */
	public void writeDragonToNBT(NBTTagCompound nbt)
	{
        nbt.setInteger("Age", this.getGrowingAge());
        if (this.getOwnerName() == null)
        {
        	nbt.setString("Owner", "");
        }
        else
        {
        	nbt.setString("Owner", this.getOwnerName());
        }
        nbt.setDouble("posX", this.posX);
        nbt.setDouble("posY", this.posY);
        nbt.setDouble("posZ", this.posZ);
        nbt.setFloat("yaw", this.rotationYaw);
        nbt.setInteger("Dimension", this.dimension);
		nbt.setInteger("Color", Color);
		nbt.setInteger(NBT_SADDLED, isSaddled());
		nbt.setBoolean("isDead", this.isDead);
        nbt.setBoolean("Sitting", this.isSitting());
        nbt.setString("CustomName", this.getCustomNameTag());
		this.inventory.writeToNBT(nbt);
	}

	/** 
	 * used By Dragon list to save only too the dragon file
	 * @param nbt
	 */
	public void readDragonFromNBT(NBTTagCompound nbt)
	{
        this.setGrowingAge(nbt.getInteger("Age"));
        String s = nbt.getString("Owner");
        if (s.length() > 0)
        {
            this.setOwner(s);
            this.setTamed(true);
        }
		Color = nbt.getInteger("Color");
		setSaddled(nbt.getInteger(NBT_SADDLED));
		this.isDead = nbt.getBoolean("isDead");
		this.posX = nbt.getDouble("posX");
		this.posY = nbt.getDouble("posY");
		this.posZ = nbt.getDouble("posZ");
		this.rotationYaw = nbt.getFloat("yaw");
		this.rotationPitch = nbt.getFloat("pitch");
        this.dimension = nbt.getInteger("Dimension");
        this.aiSit.setSitting(nbt.getBoolean("Sitting"));
        this.setSitting(nbt.getBoolean("Sitting"));
        this.setDragonName(nbt.getString("CustomName"));
		this.inventory.readFromNBT(nbt);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{

	}


	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.setDead();
	}
	
	public String getTexture(int index)
	{
		switch (index)
		{
		case 0:
			return getcoloredbody(this.GetColor());
		case 1:
			return getSaddleType();
		default:
			return null;
		}
	}

	private String getSaddleType()
	{
		switch (isSaddled())
		{
		case 0:
			return "";
		case 1:
			return "/textures/dragon/armor/body_saddle.png";
		case 2:
			return "/textures/dragon/armor/body_saddle1.png";
		}
		return null;
	}

	
	public ItemStack getCurrentArmor(int par1)
	{
		return this.inventory.getArmor(par1);
	}

	public void setCurrentArmor(int par1, ItemStack item)
	{
		this.inventory.setInventorySlotContents(27 + par1, item);
	}

	/**
	 * Returns 0 if not Saddled.
	 */
	public int isSaddled()
	{
		return dataWatcher.getWatchableObjectInt(INDEX_SADDLED);
	}

	/**
	 * Set or remove the saddle of the dragon.
	 */
	public void setSaddled(int saddled)
	{
		dataWatcher.updateObject(INDEX_SADDLED, saddled);
	}

	public boolean isOwner(EntityPlayer player)
	{
		return player.username.equalsIgnoreCase(getOwnerName());
	}

	public boolean isRiddenByOwner()
	{
		return riddenByEntity == getOwner();
	}

	public EntityPlayer getRidingPlayer()
	{
		if (riddenByEntity instanceof EntityPlayer)
		{
			return (EntityPlayer) riddenByEntity;
		}
		else
		{
			return null;
		}
	}

	public void setRidingPlayer(EntityPlayer player)
	{
		if (riddenByEntity == player)
		{
			player.mountEntity(null);
			player.setPositionAndUpdate(player.posX, player.posY, player.posZ);
		}
		else
		{
			player.mountEntity(this);
		}
	}

	@Override
	public boolean interact(EntityPlayer player)
	{

		if (isTamed())
		{
			// heal dragon with food
			ItemFood food = null;

			// eat only if hurt
			if (getDragonHealth2() != getMaxHealth())
			{
				food = (ItemFood) ItemUtils.consumeEquipped(player, Item.fishRaw, Item.porkRaw, Item.beefRaw, Item.chickenRaw);
			}

			// heal only if the food was actually consumed
			if (food != null)
			{
				heal(food.getHealAmount());
				worldObj.playSoundAtEntity(this, "random.eat", 0.7f, (rand.nextFloat() - rand.nextFloat()) * 0.2f + 0.5f);
				return true;
			}

			if (ActiveDragonList.getDragon(player) != this)
			{
				if (isServer())
				{
					// that's not your dragon!
					player.addChatMessage("shurtugal.owned");
				}
			}
			else if (this.lifeStage != LifeStage.JUVENILE && this.lifeStage != LifeStage.HATCHLING)
			{
				// activate love mode with raw fish
				if (ItemUtils.consumeEquipped(player, Item.fishRaw))
				{
					// need to better program it
				}
				else
				{
					if (isServer())
					{
						if (ItemUtils.consumeEquipped(player, Item.saddle))
						{
							// put on a saddle
							if (!player.capabilities.isCreativeMode)
							{
								dropItem(this.DropBackItem(), 1);
							}
							setSaddled(1);
							this.inventory.DropAllItems(false);
						}
						else if (ItemUtils.consumeEquipped(player, ShurtugalMod.itemHandeler.SaddleBag))
						{
							// put on a saddle with bag
							if (!player.capabilities.isCreativeMode)
							{
								dropItem(this.DropBackItem(), 1);
							}
							setSaddled(2);
						}
						else if (riddenByEntity == null && ItemUtils.hasEquipped(player, Item.bone))
						{
							System.out.println(!isSitting());
							// toggle sitting state with the bone item
							aiSit.setSitting(false);
							isJumping = false;
							setPathToEntity(null);
						}
						else if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().itemID == ShurtugalMod.itemHandeler.dragonShears.itemID)
						{
							ItemStack item = new ItemStack(ShurtugalMod.itemHandeler.dragonScale, 1, Color);
							player.getCurrentEquippedItem().damageItem(1, this);
							Entity entity = new EntityItem(worldObj, player.posX, player.posY, player.posZ, item);
							worldObj.spawnEntityInWorld(entity);
							return true;

						}
						else if (isSaddled() != 0)
						{
							// (un-)mount dragon when saddled
							setRidingPlayer(player);
						}
					}
				}
			}
		}

		return false;
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		super.dropFewItems(par1, par2);

		dropItem(this.DropBackItem(), 1);
	}

	public int DropBackItem()
	{
		switch (isSaddled())
		{
		case 0:
			return 0;
		case 1:
			return Item.saddle.itemID;
		case 2:
			return ShurtugalMod.itemHandeler.SaddleBag.itemID;

		}
		return 0;

	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource src, float par2)
	{

		// growl when being attacked
		if (src.getEntity() != null && src.getEntity() != getAITarget() && src.getEntity() != getOwner())
		{
			worldObj.playSoundAtEntity(this, "mob.enderdragon.growl", 1, 0.5f + (1 / lifeStage.getSize()) * 0.5f);
		}

		return super.attackEntityFrom(src, par2);
	}

	/**
	 * Returns true if other Entities should be prevented from moving through
	 * this Entity.
	 */
	@Override
	public boolean canBeCollidedWith()
	{
		// disable projectile collision while the rider is using the bow
		EntityPlayer rider = getRidingPlayer();
		if (rider != null && ItemUtils.hasEquipped(rider, Item.bow))
		{
			return false;
		}

		return super.canBeCollidedWith();
	}

	/**
	 * Returns true if the mob is currently able to mate with the specified mob.
	 */
	@SuppressWarnings("unused")
	@Override
	public boolean canMateWith(EntityAnimal mate)
	{
		//No Dragon Breeding Yet
		if(true)
			return false;
		
		if (mate == this)
		{
			// No. Just... no.
			return false;
		}
		else if (!isTamed())
		{
			return false;
		}
		else if (!(mate instanceof EntityTameableDragon))
		{
			return false;
		}
		else
		{
			EntityTameableDragon dragonMate = (EntityTameableDragon) mate;
			return !dragonMate.isTamed() ? false : isInLove() && dragonMate.isInLove();
		}
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding
	 * this one.
	 */
	@Override
	public double getMountedYOffset()
	{
		double offset = isSitting() ? 1.7f : 2.2f;
		if(this.lifeStage == LifeStage.ELDER)
		{
			offset *= 1.2F;
		}
		return offset;
	}

	@Override
	protected void playTameEffect(boolean success)
	{
		spawnBodyParticles(success ? "heart" : "smoke");
	}

	
	public void damageArmor(int par1)
	{
		//System.out.println("Damage Armor Called");
		this.inventory.damageArmor(par1);
	}

	/**
	 * Returns the current armor value as determined by a call to
	 * InventoryPlayer.getTotalArmorValue
	 */
	@Override
	public int getTotalArmorValue()
	{
		//System.out.println("getTotalArmorValue Called");
		return this.inventory.getTotalArmorValue();
	}

	public void Despawn()
	{
		if(this.isServer())
		{
			ActiveDragonList.removeDragon(this.getOwnerName());
		}
		super.setDead();
	}

	public boolean isOwnerOnline(String username)
	{
		boolean isPlayerOn = false;
		String[] array = MinecraftServer.getServer().getAllUsernames();
		for(int i = 0; i < array.length; i++)
		{
			String str = array[0];
			if(str.equals(username))
			{
				isPlayerOn = true;
			}	
		}
		return isPlayerOn;
	}
	
	 protected void setOnFireFromLava()
	    {
	        if (this.isImmuneToFire)
	        {
	        	this.heal(0.005F);
	            
	        }
	    }
	
	public EntityPlayer getPlayer(String username)
	{
		int i = this.getCorrectDimentionID(this.worldObj.provider.dimensionId);
		
		return MinecraftServer.getServer().worldServers[i].getPlayerEntityByName(username);
	}
	
	private int getCorrectDimentionID(int dimensionId) 
	{
		switch(dimensionId)
		{
		case -1: dimensionId = 1;
			break;
		case 1: dimensionId = 2;
			break;
		}
		return dimensionId;
	}

	@Override
    public boolean isChild()
    {
        return false;
    }
}
