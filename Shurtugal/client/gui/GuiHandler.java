/**
 * @Author Iamshortman
 */
package Shurtugal.client.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import Shurtugal.client.gui.dragonInventory.GuiDragonInventory;
import Shurtugal.client.gui.dragonInventory.GuiDragonInventorySize1;
import Shurtugal.common.Inventory.ContainerIronFurnace;
import Shurtugal.common.entity.Dragon.EntityBaseDragon;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import Shurtugal.common.entity.Dragon.Inventory.ContainerDragon;
import Shurtugal.common.entity.Dragon.Inventory.ContainerDragonSize1;
import Shurtugal.common.tileEntity.TileEntityIronFurnace;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		// Dont worrie about this one
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (id == 0 && tile_entity instanceof TileEntityIronFurnace)
		{

			return new ContainerIronFurnace(player.inventory, (TileEntityIronFurnace) tile_entity);
		}

		else if (id == 1)
		{
			Entity entity = player.ridingEntity;

			if (entity == null || !(entity instanceof EntityTameableDragon))
			{
				return null;
			}
			EntityTameableDragon dragon = (EntityTameableDragon) entity;

			return new ContainerDragon(dragon, player);
		}
		else if (id == 2)
		{
			Entity entity = player.ridingEntity;

			if (entity == null || !(entity instanceof EntityTameableDragon))
			{
				return null;
			}
			EntityTameableDragon dragon = (EntityTameableDragon) entity;

			return new ContainerDragonSize1(dragon, player);
		}

		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

		if (id == 0 && tile_entity instanceof TileEntityIronFurnace)
		{
			return new GuiIronFurnace(player.inventory, (TileEntityIronFurnace) tile_entity);
		}

		// This is the one i care about
		else if (id == 1)
		{
			Entity entity = player.ridingEntity;

			if (entity == null || !(entity instanceof EntityTameableDragon))
			{
				return null;
			}
			EntityTameableDragon dragon = (EntityTameableDragon) entity;

			return new GuiDragonInventory(dragon, player);

		}
		else if (id == 2)
		{
			Entity entity = player.ridingEntity;

			if (entity == null || !(entity instanceof EntityTameableDragon))
			{
				return null;
			}
			EntityTameableDragon dragon = (EntityTameableDragon) entity;

			return new GuiDragonInventorySize1(dragon, player);
		}

		return null;
	}
}
