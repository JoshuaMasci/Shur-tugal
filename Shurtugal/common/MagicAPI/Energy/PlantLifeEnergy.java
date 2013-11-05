package Shurtugal.common.MagicAPI.Energy;

import net.minecraft.block.Block;
public enum PlantLifeEnergy {
	grass(Block.grass.blockID, Block.dirt.blockID, 1), tallGrass(Block.tallGrass.blockID, Block.deadBush.blockID, 2), wood(Block.wood.blockID, Block.netherrack.blockID, 1), leaves(Block.leaves.blockID, 0, 4);
	private int block1;
	private int blockr;
	private int energy;
	
	private PlantLifeEnergy(int block, int block2, int e){
		block1 = block;
		blockr = block2;
		energy = e;
	}
	
	 public int getBlock() {
		   return block1;
		 }
	 public int getReplacingBlock(){
		 return blockr;
	 }
	 public int getEnergy(){
		 return energy;
	 }
	 	 
	public static PlantLifeEnergy[] getPlants(){
		PlantLifeEnergy[] enume = {grass, tallGrass, wood, leaves};
		return enume;
	}
}
