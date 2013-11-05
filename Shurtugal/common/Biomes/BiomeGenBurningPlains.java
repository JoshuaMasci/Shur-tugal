package Shurtugal.common.Biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;

public class BiomeGenBurningPlains extends BiomeGenBase
{
	public BiomeGenBurningPlains(int par1)
	{
		super(par1);
		this.spawnableCreatureList.clear();
		this.topBlock = (byte) Block.grass.blockID;
		this.fillerBlock = (byte) Block.obsidian.blockID;
		// this.theBiomeDecorator.treesPerChunk = -999;
		// this.theBiomeDecorator.deadBushPerChunk = 4;

	}

	public int waterColorMultiplier = 43200;

	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);

		if (par2Random.nextInt(1000) == 0)
		{
			int var5 = par3 + par2Random.nextInt(16) + 8;
			int var6 = par4 + par2Random.nextInt(16) + 8;
			WorldGenGlowStone1 var7 = new WorldGenGlowStone1();
			var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
		}
	}
}
