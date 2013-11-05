package Shurtugal.common.Item;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.client.forge.ClientProxy;
import Shurtugal.common.Handlers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemDragonRiderSword extends Item
{
	private int weaponDamage;

	public ItemDragonRiderSword(int par1)
	{
		super(par1);
		maxStackSize = 1;
		setMaxDamage(0);
		weaponDamage = 6;
		setHasSubtypes(true);
		setCreativeTab(ShurtugalMod.tabShurtugal);
	}

	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		int metadata = par1ItemStack.getItemDamage();
		return Colors.ColorNames[metadata] + " Rider Sword";
	}

	@SideOnly(Side.CLIENT)
	public Icon[] icons;

	@SideOnly(Side.CLIENT)
	@Override
	public Icon getIconFromDamage(int i)
	{
		return icons[i];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons = new Icon[Colors.ColorNames.length];
		for (int i = 0; i < Colors.ColorNames.length; i++)
		{
			String str = Colors.ColorNames[i].toLowerCase() + "ridersword";
			str = "shurtugal:ridersword/" + str;
			icons[i] = par1IconRegister.registerIcon(str);
		}
	}

	/**
	 * Returns the strength of the stack against a given block. 1.0F base,
	 * (Quality+1)*2 if correct blocktype, 1.5F if sword
	 */
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block)
	{
		return par2Block.blockID != Block.web.blockID ? 1.5F : 15F;
	}

	/**
	 * Current implementations of this method in child classes do not use the
	 * entry argument beside ev. They just raise the damage on the stack.
	 */
	public boolean hitEntity(ItemStack par1ItemStack, EntityLiving par2EntityLiving, EntityLiving par3EntityLiving)
	{
		return true;
	}

	public boolean onBlockDestroyed(ItemStack par1ItemStack, int par2, int par3, int par4, int par5, EntityLiving par6EntityLiving)
	{
		return true;
	}

	/**
	 * Returns the damage against a given entity.
	 */
	public int getDamageVsEntity(Entity par1Entity)
	{
		return weaponDamage;
	}

	/**
	 * Returns True is the item is renderer in full 3D when hold.
	 */
	public boolean isFull3D()
	{
		return true;
	}

	/**
	 * returns the action that specifies what animation to play when the items
	 * is being used
	 */
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return EnumAction.block;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
		return 0x11940;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}

	/**
	 * Returns if the item (tool) can harvest results from the block type.
	 */
	public boolean canHarvestBlock(Block par1Block)
	{
		return par1Block.blockID == Block.web.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int i, CreativeTabs tab, List list)
	{

		for (int ix = 0; ix < Colors.ColorNames.length; ix++)
		{
			list.add(new ItemStack(i, 1, ix));
		}
	}

}
