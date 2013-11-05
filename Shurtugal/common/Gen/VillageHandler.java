package Shurtugal.common.Gen;

import java.util.List;
import java.util.Random;

import Shurtugal.common.Gen.Temples.ComponentVillagePinkTemple;

import net.minecraft.world.gen.structure.ComponentVillageStartPiece;
import net.minecraft.world.gen.structure.StructureVillagePieceWeight;

import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;

public class VillageHandler implements IVillageCreationHandler
{

	@Override
	public StructureVillagePieceWeight getVillagePieceWeight(Random random, int i)
	{
		return new StructureVillagePieceWeight(ComponentVillagePinkTemple.class, 20, 1);
	}

	@Override
	public Class<?> getComponentClass()
	{
		return ComponentVillagePinkTemple.class;
	}

	@Override
	public Object buildComponent(StructureVillagePieceWeight villagePiece, ComponentVillageStartPiece startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5)
	{
		return ComponentVillagePinkTemple.SomeCrap(startPiece, pieces, random, p1, p2, p3, p4, p5);
	}

}
