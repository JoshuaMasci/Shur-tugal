package Shurtugal.common.Gen.Temples;

import java.util.List;
import java.util.Random;

import Shurtugal.Colors;
import Shurtugal.ShurtugalMod;
import Shurtugal.common.block.BlockHandler;

import net.minecraft.block.Block;
import net.minecraft.src.*;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.ComponentVillage;
import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;

public class ComponentVillagePinkTemple extends ComponentVillage
{
	private int averageGroundLevel = -1;

	public ComponentVillagePinkTemple(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5)
	{
		super(par1ComponentVillageStartPiece, par2);
		this.coordBaseMode = par5;
		this.boundingBox = par4StructureBoundingBox;
	}

	public static ComponentVillagePinkTemple SomeCrap(ComponentVillageStartPiece par0ComponentVillageStartPiece, List par1List, Random par2Random, int par3, int par4, int par5, int par6, int par7)
	{
		StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(par3, par4, par5, 0, -1, 0, 17, 4, 13, par6);
		return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(par1List, var8) == null ? new ComponentVillagePinkTemple(par0ComponentVillageStartPiece, par7, par2Random, var8, par6)
				: null;
	}

	/**
	 * second Part of Structure generating, this for example places Spiderwebs,
	 * Mob Spawners, it closes Mineshafts at the end, it adds Fences...
	 */
	public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox)
	{
		if (this.averageGroundLevel < 0)
		{
			this.averageGroundLevel = this.getAverageGroundLevel(par1World, par3StructureBoundingBox);

			if (this.averageGroundLevel < 0)
			{
				return true;
			}

			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4 - 1, 0);
		}

		this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 1, 0, 12, 10, 8, 0, 0, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, -1, 2, 14, -1, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID,
				Colors.Pink, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 14, 0, 2, 14, 2, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID,
				Colors.Pink, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 0, 2, 13, 0, 4, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID,
				Colors.Pink, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 11, 0, 8, 13, 0, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID,
				Colors.Pink, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 13, 0, 5, 13, 0, 7, Block.lavaMoving.blockID, Block.lavaStill.blockID, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 11, 0, 5, 12, 0, 7, 0, 0, false);

		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 0, 2, 10, 0, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID,
				Colors.Pink, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 2, 7, 2, 4, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 8, 7, 2, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 1, 2, 14, 2, 2, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		// this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2,
		// 1, 8, 4, 2, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
		// ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 0, 11, 9, 3, 11, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID,
				Colors.Pink, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 10, 0, 10, 14, 2, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID,
				Colors.Pink, false);

		// roof 1
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 3, 3, 14, 3, 9, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 3, 4, 13, 3, 8, 0, 0, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 4, 3, 10, 10, 3, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID,
				Colors.Pink, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 3, 9, 9, 3, 10, 0, 0, false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 2, 3, 5, 7, 3, 7, 0, 0, false);

		// roof 2
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 4, 4, 7, 4, 8, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		// this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2,
		// 4, 4, 5, 4, 4, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
		// ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 4, 3, 8, 4, 3, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 4, 4, 14, 4, 4, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 14, 4, 5, 14, 4, 7, Block.thinGlass.blockID, Block.thinGlass.blockID, false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 4, 8, 5, 4, 8, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 4, 8, 14, 4, 8, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 5, 4, 8, 7, 4, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 9, 4, 8, 9, 4, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 4, 11, 8, 4, 11, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID,
				Colors.Pink, false);

		// roof 3
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 5, 5, 14, 5, 7, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 6, 5, 4, 8, 5, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 5, 3, 7, 5, 11, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithBlocks(par1World, par3StructureBoundingBox, 8, 5, 6, 13, 5, 6, 0, 0, false);

		// roof 4
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 7, 6, 4, 7, 6, 10, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);
		this.fillWithMetadataBlocks(par1World, par3StructureBoundingBox, 2, 6, 6, 14, 6, 6, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink, ShurtugalMod.blockHandler.Brick.blockID, Colors.Pink,
				false);

		this.fillWithBlocks(par1World, par3StructureBoundingBox, 10, 0, 6, 10, 0, 6, ShurtugalMod.blockHandler.PinkTempleTrigger.blockID, ShurtugalMod.blockHandler.PinkTempleTrigger.blockID, false);

		int var4;

		for (var4 = 1; var4 <= 7; ++var4)
		{

		}

		for (var4 = 0; var4 < 9; ++var4)
		{
			for (int var5 = 0; var5 < 13; ++var5)
			{
				// this.clearCurrentPositionBlocksUpwards(par1World, var5, 4,
				// var4, par3StructureBoundingBox);
				this.fillCurrentPositionBlocksDownwards(par1World, Block.dirt.blockID, 0, var5, -1, var4, par3StructureBoundingBox);
			}
		}

		return true;
	}
}
