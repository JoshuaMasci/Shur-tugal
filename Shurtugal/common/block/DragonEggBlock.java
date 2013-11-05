/*
 ** 2011 December 10
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */

package Shurtugal.common.block;

import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDragonEgg;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.DragonList.ActiveDragonList;
import Shurtugal.common.Handlers.ConfigHandler;
import Shurtugal.common.Achievement.ShurtugalAchievement;
import Shurtugal.common.entity.LifeStage;
import Shurtugal.client.forge.ClientProxy;
import Shurtugal.common.entity.Dragon.DragonName;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;

/**
 * Eggs for the Colored Dragons
 * 
 * @author Iamshortman
 */
public class DragonEggBlock extends BlockDragonEgg
{
	public Icon[] icons;

	public DragonEggBlock(int par1)
	{
		super(par1);
		setHardness(.5F);
		setResistance(10);
		setStepSound(soundStoneFootstep);
		setLightValue(0.125f);
		this.setCreativeTab(ShurtugalMod.tabShurtugal);
	}

	@Override
	public void onBlockClicked(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{

	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (!world.isRemote)
		{
			if (ShurtugalMod.proxy.isPlayerRider(player))
			{
				player.addChatMessage("Shurtugal.alreadyRider");
				return true;
			}
			int Color = world.getBlockMetadata(x, y, z);

			world.setBlock(x, y, z, 0);

			EntityTameableDragon dragon = new EntityTameableDragon(world);
			dragon.setPosition(x + 0.5, y + 0.5, z + 0.5);
			dragon.Color = Color;
			dragon.setLifeStage(LifeStage.HATCHLING);
			// player.addStat(ShurtugalAchievement.Rider, 1);
			dragon.setOwner(player.username);
			if (player.username.equalsIgnoreCase("1Codemaster"))
			{
				dragon.setDragonName("Kodarent");
			}
			else if (player.username.equalsIgnoreCase("camtheman16"))
			{
				dragon.setDragonName("Domovoi");
			}
			else if (player.username.equalsIgnoreCase("AnimatorBlake"))
			{
				dragon.setDragonName("Lalassu");
			}
			else
			{
				dragon.setDragonName(new DragonName().getRandomDragonName());
			}
			dragon.setTamed(true);
			ShurtugalMod.proxy.updatePlayerDragon(player, dragon);
			if (world.spawnEntityInWorld(dragon))
			{
				ActiveDragonList.putDragon(player, dragon);
			}
			return true;
		}

		return true;
	}

	/**
	 * Return true if a player with Silk Touch can harvest this block directly,
	 * and not its normal drops.
	 */
	@Override
	protected boolean canSilkHarvest()
	{
		return true;
	}

	@Override
	public int getRenderType()
	{
		return ShurtugalMod.DragonEggRenderID;
	}

	/**
	 * only called by clickMiddleMouseButton , and passed to
	 * inventory.setCurrentItem (along with isCreative)
	 */
	@Override
	public int idPicked(World par1World, int par2, int par3, int par4)
	{
		return blockID;
	}

	@Override
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons = new Icon[Colors.ColorNames.length];
		for (int i = 0; i < Colors.ColorNames.length; i++)
		{
			String str = Colors.ColorNames[i].toLowerCase() + "egg";
			str = "shurtugal:egg/" + str;
			icons[i] = par1IconRegister.registerIcon(str);
		}

	}

	@Override
	public Icon getIcon(int par1, int par2)
	{
		return icons[par2];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems)
	{
		for (int ix = 0; ix < Colors.ColorNames.length; ix++)
		{
			subItems.add(new ItemStack(this, 1, ix));
		}
	}

}
