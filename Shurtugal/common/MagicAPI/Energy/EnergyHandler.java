package Shurtugal.common.MagicAPI.Energy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;

public class EnergyHandler {

	public static void addItems(ArrayList<Item> list){
		list.add(Shurtugal.common.Item.ItemHandler.eldunari);
		list.add(Shurtugal.common.Item.ItemHandler.MageBlade);
	}
	
	public static void takeEAnimals(EntityPlayer p, int e){
		
		World world = p.worldObj;
	    if(world.getLoadedEntityList().contains(EntityCow.class)){
	    	Iterator<Entity> Iter = world.getLoadedEntityList().iterator();
	    	while (Iter.hasNext()){
	    		Entity entity = Iter.next();
	    	}
	    }
	}
	
	
	public static void takeEPlants(EntityPlayer p, int e){
		World world = p.worldObj;
		int x = (int) p.posX;
		int y = (int) p.posY;
		int z = (int) p.posZ;
		int distancex = -5;
		int distancey = -5;
		int distancez = -5;
			while(distancex <= 5){
			x = distancex;
			while(distancey <= 5){
				y = distancey;
				distancey ++;
				while(distancez <= 5){
					z = distancez;
					distancez++;
					for(PlantLifeEnergy pl : PlantLifeEnergy.getPlants()){
					if(world.getBlockId(x, y, z) == pl.getBlock()){
						world.setBlock(x, y, z, pl.getReplacingBlock());
					}
					}
				}
			}
			distancex ++;
			}
	}
	
	public static void useEnergy(EntityPlayer p, int e){
		InventoryPlayer inv = p.inventory;
		int looping = 0;
		int energy2 = 0;
		ArrayList<Item> list = Shurtugal.ShurtugalMod.energyList;
		while(looping < 36){
			int energy1 = 0;
			ItemStack itemStack = inv.getStackInSlot(looping);
			Item item = itemStack.getItem();
			if(item != null && list.contains(item)){
				ImmutableList.Builder<String> loreBuilder = ImmutableList.builder();
				NBTTagList loreNbt = itemStack.stackTagCompound.getTagList("Lore");
				for (int i = 0; i < loreNbt.tagCount(); i++) {
				    loreBuilder.add(((NBTTagString)loreNbt.tagAt(i)).data);
				}
				List<String> lore = loreBuilder.build();
				String lore1 = lore.toString();
				energy1 = Integer.parseInt(lore1);
				energy2 = energy2 + energy1;
			}
			if(energy2 > 0 && e > 0){
				if(energy1 != 0){
					if(energy2 - e > 0){
						int energy3 = energy2 - e;
						itemStack.stackTagCompound.setString("Lore", "" + energy3);
					}
					e = e - energy1;
					energy2 = energy2 - energy1;
				}
			}
			looping ++;
		}
		FoodStats food = p.getFoodStats();
		int hunger = food.getFoodLevel();
		int hp = (int) p.func_110143_aJ();
		if (e - energy2 >= e) {
			e = e - energy2;
			if (hunger >= e) {
				p.getFoodStats().setFoodLevel(hunger - e);
			} else {
				p.setEntityHealth(hp - e);
			}
		}
		
	}
}
