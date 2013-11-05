package Shurtugal.common.Item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.client.forge.ClientProxy;
import Shurtugal.common.Gen.Temples.BlackTemple;
import Shurtugal.common.Gen.Temples.WhiteTemple2;
import Shurtugal.common.SchemReader.Schematic;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemScale extends Item
{

	private Icon[] icons;

	public ItemScale(int par1)
	{
		super(par1);
		setHasSubtypes(true);
		this.setCreativeTab(ShurtugalMod.tabShurtugal);
	}

	public Icon getIconFromDamage(int par1)
	{
		return icons[par1];
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons = new Icon[Colors.ColorNames.length];
		for (int i = 0; i < Colors.ColorNames.length; i++)
		{
			String str = Colors.ColorNames[i].toLowerCase() + "scale";
			str = "shurtugal:scale/" + str;
			icons[i] = par1IconRegister.registerIcon(str);
		}
	}

	  /*public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) 
	  { 
		  //new BlackTemple().generate(par2World, itemRand,(int) par3EntityPlayer.posX,(int) par3EntityPlayer.posY,(int) par3EntityPlayer.posZ); 
		  Schematic white = new Schematic("/mods/Shurtugal/schematic/WhiteTemple.schematic");
		  white.addBlockReplace(Block.blockSteel.blockID, ShurtugalMod.blockHandler.Brick.blockID, Colors.White);
		  white.generate(par2World, (int) par3EntityPlayer.posX,(int) par3EntityPlayer.posY,(int) par3EntityPlayer.posZ);
		  
		  new WhiteTemple2().generate(par2World, itemRand, (int) par3EntityPlayer.posX - 11, (int) par3EntityPlayer.posY - 40, (int) par3EntityPlayer.posZ - 11);
	  
	  
	  return par1ItemStack; 
	  }*/

	@SideOnly(Side.CLIENT)
	public void getSubItems(int i, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(i, 1, 0));
		list.add(new ItemStack(i, 1, 1));
		list.add(new ItemStack(i, 1, 2));
		list.add(new ItemStack(i, 1, 3));
		list.add(new ItemStack(i, 1, 4));
		list.add(new ItemStack(i, 1, 5));
		list.add(new ItemStack(i, 1, 6));
		list.add(new ItemStack(i, 1, 7));
		list.add(new ItemStack(i, 1, 8));
		list.add(new ItemStack(i, 1, 9));
	}

	public String getItemDisplayName(ItemStack par1ItemStack)
	{
		int metadata = par1ItemStack.getItemDamage();
		return Colors.ColorNames[metadata] + " Scale";
	}
}
