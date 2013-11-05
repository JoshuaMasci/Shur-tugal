package Shurtugal.common.SchemReader;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class Schematic
{

	public byte[] blocks, meta;
	public short width, length, height;
	public short[] size;
	public String name;

	public ArrayList<SchematicBlockReplace> blockReplace = new ArrayList<SchematicBlockReplace>();

	public Schematic(String name)
	{
		try
		{
			this.name = name;
			File file = null;

			NBTTagCompound nbt = CompressedStreamTools.readCompressed(new FileInputStream(file));

			blocks = nbt.getByteArray("Blocks");
			meta = nbt.getByteArray("Data");
			width = nbt.getShort("Width");
			length = nbt.getShort("Length");
			height = nbt.getShort("Height");

			size = new short[]
			{ width, length, height };

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.out.println("Could not find file " + name + ".schematic");
		}
	}

	public boolean generate(World world, int x, int y, int z)
	{
		if (blocks == null)
			return false;

		if (blockReplace.size() > 0)
			parseBlocks();

		try
		{
			int xSize = 0, ySize = 0, zSize = 0;

			for (int i = 0; i < blocks.length; i++)
			{
				if (blocks[i] >= 0)
				{
					world.setBlockMetadataWithNotify(x + xSize, y + ySize, z + zSize, blocks[i], meta[i]);
				}

				if (xSize < width - 1)
				{
					xSize++;
				}
				else if ((xSize >= width - 1) && (zSize < length - 1))
				{
					xSize = 0;
					zSize++;
				}
				else if ((xSize >= width - 1) && (zSize >= length - 1) && (ySize < height - 1))
				{
					xSize = 0;
					zSize = 0;
					ySize++;
				}
			}

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return false;
		}

		System.out.println("Generated " + name);
		return true;
	}

	public Schematic addBlockReplace(int i, int j, int k)
	{
		return addBlockReplace(new SchematicBlockReplace(i, j, k));
	}

	public Schematic addBlockReplace(int i, int j)
	{
		return addBlockReplace(new SchematicBlockReplace(i, j));
	}

	public Schematic addBlockReplace(SchematicBlockReplace sbr)
	{
		blockReplace.add(sbr);
		return this;
	}

	public void parseBlocks()
	{
		for (int i = 0; i < blocks.length; i++)
		{
			for (int j = 0; j < blockReplace.size(); j++)
			{		
				if (blocks[i] == blockReplace.get(i).getFromBlock())
				{
					if((byte) blockReplace.get(i).getFromMeta() ==  -1)
					{
						blocks[i] = (byte) blockReplace.get(i).getToBlock();
						meta[i] = (byte) blockReplace.get(i).getMeta();
					}
					else if (meta[i] == blockReplace.get(i).getFromMeta())
					{
						blocks[i] = (byte) blockReplace.get(i).getToBlock();
						meta[i] = (byte) blockReplace.get(i).getMeta();
					}
				}
			}
		}
	}

}
