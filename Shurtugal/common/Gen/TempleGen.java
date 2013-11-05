package Shurtugal.common.Gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.*;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.common.IWorldGenerator;
import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.Gen.Temples.*;
import Shurtugal.common.Handlers.ConfigHandler;

public class TempleGen implements IWorldGenerator
{

	private void generateSurface(World world, Random random, int blockX, int blockZ)
	{

		if (world.getBiomeGenForCoords(blockX, blockZ) == BiomeGenBase.taiga)
		{
			int R = random.nextInt(ConfigHandler.templeSpawnRate[Colors.White]);
			if (R == 0)
			{

				int RandPosX = blockX + random.nextInt(5);
				int RandPosZ = blockZ + random.nextInt(5);
				int RandPosY = world.getHeightValue(RandPosX, RandPosZ);
				Boolean bln = true;
				while (bln)
				{
					if (world.getBlockId(RandPosX, RandPosY - 1, RandPosZ) != Block.grass.blockID)
					{
						RandPosY -= 1;
						if (world.getBlockId(RandPosX, RandPosY, RandPosZ) == Block.stone.blockID)
							;
						{
							bln = false;
						}
					}
					else
					{
						if (world.getBlockId(RandPosX + 22, RandPosY, RandPosZ + 22) == Block.grass.blockID)
						{
							this.printDebugMessage(RandPosX, RandPosY, RandPosZ, Colors.White);
							(new WhiteTemple()).generate(world, random, RandPosX, RandPosY, RandPosZ);
						}
						bln = false;
					}
				}

			}
		}

		if (world.getBiomeGenForCoords(blockX, blockZ) == BiomeGenBase.plains)
		{

			int R = random.nextInt(ConfigHandler.templeSpawnRate[Colors.Brown]);
			if (R == 1)
			{
				int RandPosX = blockX + random.nextInt(10);
				int RandPosZ = blockZ + random.nextInt(10);
				int RandPosY = world.getHeightValue(RandPosX, RandPosZ) - 6;

				if (world.getBlockId(RandPosX, world.getHeightValue(RandPosX, RandPosZ) - 1, RandPosZ) == Block.dirt.blockID
						|| world.getBlockId(RandPosX, world.getHeightValue(RandPosX, RandPosZ) - 1, RandPosZ) == Block.grass.blockID)
				{
					this.printDebugMessage(RandPosX, RandPosY, RandPosZ, Colors.Brown);
					(new BrownTemple()).generate(world, random, RandPosX, RandPosY, RandPosZ);
				}
			}
		}

		if (world.getBiomeGenForCoords(blockX, blockZ) == BiomeGenBase.extremeHills)
		{
			int R = random.nextInt(ConfigHandler.templeSpawnRate[Colors.Purple]);
			if (R == 1)
			{

				int RandPosX = blockX + random.nextInt(5);
				int RandPosZ = blockZ + random.nextInt(5);
				int RandPosY = world.getHeightValue(RandPosX, RandPosZ);
				Boolean bln = true;
				while (bln)
				{
					if (world.getBlockId(RandPosX + 11, RandPosY, RandPosZ) != Block.grass.blockID)
					{
						RandPosY -= 1;
						if (world.getBlockId(RandPosX + 11, RandPosY, RandPosZ) == Block.stone.blockID)
							;
						{
							bln = false;
						}
					}
					else
					{
						if (RandPosY > 60)
						{
							this.printDebugMessage(RandPosX, RandPosY, RandPosZ, Colors.Purple);
							(new PurpleTemple()).generate(world, random, RandPosX, RandPosY, RandPosZ);
						}
						bln = false;
					}

				}
			}
		}

		if (world.getBiomeGenForCoords(blockX, blockZ) == BiomeGenBase.jungle)
		{
			int R = random.nextInt(ConfigHandler.templeSpawnRate[Colors.Green]);
			if (R == 1)
			{

				int RandPosX = blockX + random.nextInt(5);
				int RandPosZ = blockZ + random.nextInt(5);
				int RandPosY = world.getHeightValue(RandPosX, RandPosZ);
				Boolean bln = true;
				while (bln)
				{
					if (world.getBlockId(RandPosX, RandPosY - 1, RandPosZ) != Block.grass.blockID)
					{
						RandPosY -= 1;
						if (world.getBlockId(RandPosX, RandPosY, RandPosZ) == Block.stone.blockID)
							;
						{
							bln = false;
						}
					}
					else
					{
						this.printDebugMessage(RandPosX, RandPosY, RandPosZ, Colors.Green);
						(new GreenTemple()).generate(world, random, RandPosX, RandPosY, RandPosZ);
						bln = false;
					}
				}
			}
		}

		if (world.getBiomeGenForCoords(blockX, blockZ) == BiomeGenBase.desert)
		{
			int R = random.nextInt(ConfigHandler.templeSpawnRate[Colors.Yellow]);
			if (R == 1)
			{

				int RandPosX = blockX + random.nextInt(10);
				int RandPosZ = blockZ + random.nextInt(10);
				int RandPosY = world.getHeightValue(RandPosX, RandPosZ);
				this.printDebugMessage(RandPosX, RandPosY, RandPosZ, Colors.Yellow);
				(new YellowTemple()).generate(world, random, RandPosX, RandPosY - 1, RandPosZ);
			}
		}

		if (world.getBiomeGenForCoords(blockX, blockZ) == BiomeGenBase.ocean)
		{
			int R = random.nextInt(ConfigHandler.templeSpawnRate[Colors.Blue]);
			if (R == 1)
			{

				int RandPosX = blockX + random.nextInt(10);
				int RandPosZ = blockZ + random.nextInt(10);
				int RandPosY = world.getTopSolidOrLiquidBlock(RandPosX, RandPosZ) - 8;
				this.printDebugMessage(RandPosX, RandPosY, RandPosZ, Colors.Blue);
				new BlueTemple().generate(world, random, blockX, RandPosY, blockZ);

			}
		}

		int R = random.nextInt(ConfigHandler.templeSpawnRate[Colors.Black]);
		if (R == 1)
		{

			int RandPosX = blockX + random.nextInt(10);
			int RandPosZ = blockZ + random.nextInt(10);
			int RandPosY = 10 + random.nextInt(20);
			this.printDebugMessage(RandPosX, RandPosY, RandPosZ, Colors.Yellow);
			new BlackTemple().generate(world, random, blockX, RandPosY, blockZ);
		}

	}

	private void generateNether(World world, Random random, int blockX, int blockZ)
	{
		int R = random.nextInt(ConfigHandler.templeSpawnRate[Colors.Red]);
		if (R == 1)
		{
			int RandPosX = blockX + random.nextInt(5);
			int RandPosY = random.nextInt(100);
			int RandPosZ = blockZ + random.nextInt(5);
			this.printDebugMessage(RandPosX, RandPosY, RandPosZ, Colors.Red);
			(new RedTemple()).generate(world, random, RandPosX, RandPosY, RandPosZ);
		}

	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
		if (world.provider.terrainType == WorldType.FLAT)
		{
			return;
		}
		switch (world.provider.dimensionId)
		{
		case -1:
			generateNether(world, random, chunkX * 16, chunkZ * 16);
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
		}
	}
	
	public static void printDebugMessage(int RandPosX, int RandPosY, int RandPosZ, int Color)
	{
		if (ConfigHandler.debug)
		{
			MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatMessageComponent().func_111079_a("( " + RandPosX + " , " + RandPosY + " , " + RandPosZ + " )" + "Temple " + Colors.ColorNames[Color]));
			System.out.println("( " + RandPosX + " , " + RandPosY + " , " + RandPosZ + " )" + "Temple " + Colors.ColorNames[Color]);
		}
	}
	

	// loot for Chest, feel free to add things to it.
	public static ItemStack getRandomLoot(Random random)
	{

		int i = random.nextInt(100);
		if (i < 10)
		{
			return new ItemStack(Item.appleRed, random.nextInt(5) + 1);
		}
		else if (i < 20)
		{
			return new ItemStack(Item.gunpowder, random.nextInt(3) + 1);
		}
		else if (i < 30)
		{
			return new ItemStack(Item.saddle);
		}
		else if (i < 42)
		{
			return new ItemStack(ShurtugalMod.blockHandler.brightSteelOre, random.nextInt(3));
		}
		else if (i < 50)
		{
			return new ItemStack(Item.ingotIron, random.nextInt(6) + 1);
		}
		else if (i < 55)
		{
			return new ItemStack(Item.swordGold);
		}
		else if (i < 60)
		{
			return new ItemStack(Item.swordIron);
		}
		else if (i > 95)
		{
			return new ItemStack(ShurtugalMod.itemHandeler.plateLeatherDragon);
		}
		else if (i > 96)
		{
			return new ItemStack(ShurtugalMod.itemHandeler.tailIronDragon);
		}
		else if (i > 97)
		{
			return new ItemStack(ShurtugalMod.itemHandeler.legsGoldDragon);
		}
		else if (i > 98)
		{
			return new ItemStack(ShurtugalMod.itemHandeler.helmetDiamondDragon);
		}
		else if (i > 99)
		{
			return new ItemStack(ShurtugalMod.itemHandeler.hammer);
		}
		else
		{
			return new ItemStack(Item.ingotGold);
		}
	}

}
