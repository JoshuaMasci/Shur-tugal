package Shurtugal.common.block;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.Handlers.ConfigHandler;
import Shurtugal.common.entity.*;
import Shurtugal.client.forge.ClientProxy;
import Shurtugal.common.tileEntity.*;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class TempleTrigger extends BlockContainer
{

	public int Color;

	public TempleTrigger(int par1, int par2)
	{
		super(par1, Material.rock);
		this.Color = par2;
		this.setTickRandomly(true);
		this.setBlockUnbreakable();
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		par1World.setBlockTileEntity(par2, par3, par4, this.createNewTileEntity(par1World));
	}

	public TileEntity createNewTileEntity(World par1World)
	{
		switch (Color)
		{
		case 0:
			return new TileEntityBlackTrigger();
		case 1:// does not use one
		case 2:// does not use one
		case 3:
			return new TileEntityGreenTrigger();
		case 4:
			return new TileEntityPinkTrigger();
		case 5:
			return new TileEntityPurpleTrigger();
		case 6:
			return new TileEntityRedTrigger();
		case 7:
			return new TileEntityWhiteTrigger();
		case 8:
			return new TileEntityYellowTrigger();
		}
		return new TileEntityRedTrigger();
	}

	public int idDropped(int par1, Random par2Random, int par3)
	{
		return ShurtugalMod.blockHandler.Brick.blockID;
	}

	@Override
	public int damageDropped(int metadata)
	{
		return metadata;
	}

	public Icon[] icons;

	@Override
	public Icon getIcon(int par1, int par2)
	{
		return icons[Color];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
		icons = new Icon[Colors.ColorNames.length];
		for (int i = 0; i < Colors.ColorNames.length; i++)
		{
			String str = Colors.ColorNames[i].toLowerCase() + "brick";
			str = "shurtugal:brick/" + str;
			icons[i] = par1IconRegister.registerIcon(str);
		}

	}

}
