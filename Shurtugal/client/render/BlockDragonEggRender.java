package Shurtugal.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.src.*;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import Shurtugal.ShurtugalMod;

public class BlockDragonEggRender implements ISimpleBlockRenderingHandler
{

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		int x = 0;
		int y = 0;
		int z = 0;

		int l = 0;

		for (int i1 = 0; i1 < 8; ++i1)
		{
			byte b0 = 0;
			byte b1 = 1;

			if (i1 == 0)
			{
				b0 = 2;
			}

			if (i1 == 1)
			{
				b0 = 3;
			}

			if (i1 == 2)
			{
				b0 = 4;
			}

			if (i1 == 3)
			{
				b0 = 5;
				b1 = 2;
			}

			if (i1 == 4)
			{
				b0 = 6;
				b1 = 3;
			}

			if (i1 == 5)
			{
				b0 = 7;
				b1 = 5;
			}

			if (i1 == 6)
			{
				b0 = 6;
				b1 = 2;
			}

			if (i1 == 7)
			{
				b0 = 3;
			}

			float f = (float) b0 / 16.0F;
			float f1 = 1.0F - (float) l / 16.0F;
			float f2 = 1.0F - (float) (l + b1) / 16.0F;
			l += b1;
			renderer.setRenderBounds((double) (0.5F - f), (double) f2, (double) (0.5F - f), (double) (0.5F + f), (double) f1, (double) (0.5F + f));
			this.renderNormalInventory(block, renderer, metadata);
		}

		renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	}

	private void renderNormalInventory(Block block, RenderBlocks renderer, int metadata)
	{
		GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);

		Tessellator tessellator = Tessellator.instance;

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 0, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 1, metadata));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 2, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 3, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 4, metadata));
		tessellator.draw();
		
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderer.getBlockIconFromSideAndMetadata(block, 5, metadata));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		boolean flag = false;
		int l = 0;

		for (int i1 = 0; i1 < 8; ++i1)
		{
			byte b0 = 0;
			byte b1 = 1;

			if (i1 == 0)
			{
				b0 = 2;
			}

			if (i1 == 1)
			{
				b0 = 3;
			}

			if (i1 == 2)
			{
				b0 = 4;
			}

			if (i1 == 3)
			{
				b0 = 5;
				b1 = 2;
			}

			if (i1 == 4)
			{
				b0 = 6;
				b1 = 3;
			}

			if (i1 == 5)
			{
				b0 = 7;
				b1 = 5;
			}

			if (i1 == 6)
			{
				b0 = 6;
				b1 = 2;
			}

			if (i1 == 7)
			{
				b0 = 3;
			}

			float f = (float) b0 / 16.0F;
			float f1 = 1.0F - (float) l / 16.0F;
			float f2 = 1.0F - (float) (l + b1) / 16.0F;
			l += b1;
			renderer.setRenderBounds((double) (0.5F - f), (double) f2, (double) (0.5F - f), (double) (0.5F + f), (double) f1, (double) (0.5F + f));
			renderer.renderStandardBlock(block, x, y, z);
		}

		flag = true;
		renderer.setRenderBounds(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
		return flag;
	}

	@Override
	public boolean shouldRender3DInInventory()
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return ShurtugalMod.DragonEggRenderID;
	}

}
