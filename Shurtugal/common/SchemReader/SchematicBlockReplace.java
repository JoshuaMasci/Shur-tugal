package Shurtugal.common.SchemReader;

public class SchematicBlockReplace
{

	int fromBlock, toBlock, meta;
	int fromMeta = -1;

	public SchematicBlockReplace(int i, int j, int k, int l)
	{
		fromBlock = i;
		toBlock = j;
		meta = k;
		fromMeta = l;
	}
	
	public SchematicBlockReplace(int i, int j, int k)
	{
		fromBlock = i;
		toBlock = j;
		meta = k;
	}

	public SchematicBlockReplace(int i, int j)
	{
		this(i, j, 0);
	}
	
	

	public int getFromBlock()
	{
		return fromBlock;
	}

	public int getToBlock()
	{
		return toBlock;
	}

	public int getMeta()
	{
		return meta;
	}
	
	public int getFromMeta()
	{
		return fromMeta;
	}

}
