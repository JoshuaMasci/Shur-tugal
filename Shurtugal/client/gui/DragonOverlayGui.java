/*
 ** 2012 April 3
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package Shurtugal.client.gui;

import Shurtugal.common.entity.LifeStage;
import Shurtugal.common.entity.Dragon.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

/**
 * Dragon in-game overlay.
 * 
 * @author Iamshortman
 */
public class DragonOverlayGui extends Gui
{

	private Minecraft mc;
	private static final ResourceLocation textureLoc = new ResourceLocation("shurtugal", "/textures/gui/gui.png");

	public DragonOverlayGui(Minecraft mc)
	{
		this.mc = mc;
	}

	public void draw()
	{
		if (mc.gameSettings.hideGUI || mc.currentScreen != null)
		{
			return;
		}

		EntityPlayerSP player = mc.thePlayer;

		if (player == null)
		{
			return;
		}

		EntityBaseDragon dragon;

		if (player.ridingEntity != null && player.ridingEntity instanceof EntityBaseDragon)
		{
			dragon = (EntityBaseDragon) player.ridingEntity;
		}
		else if (mc.objectMouseOver != null)
		{
			Entity entity = mc.objectMouseOver.entityHit;

			if (entity == null || !(entity instanceof EntityBaseDragon))
			{
				return;
			}

			// get dragon base entity if a part is selected
			// if (entity instanceof DragonPart) {
			// entity = ((DragonPart) entity).base;
			// }

			dragon = (EntityBaseDragon) entity;
		}
		else
		{

			return;
		}

		if (dragon instanceof EntityDragonHostile)
		{
			return;
		}

		ScaledResolution res = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);

		int width = res.getScaledWidth();
		int barWidth = 182;
		int barWidthFilled = (int) (dragon.getHealthRelative() * (float) (barWidth + 1));
		int barHeight = 5;

		int x = width / 2 - barWidth / 2;
		int y = res.getScaledHeight() - 46;

		if (player.capabilities.isCreativeMode)
		{
			y += 16;
		}
		else if (player.isInsideOfMaterial(Material.water) || player.getTotalArmorValue() > 0)
		{
			y -= 10;
		}

		int u = 0;
		int v = 0;

	    this.mc.func_110434_K().func_110577_a(textureLoc);
		drawTexturedModalRect(x, y, u, v, barWidth, barHeight);
		drawTexturedModalRect(x, y, u, v, barWidth, barHeight);

		if (barWidthFilled > 0)
		{
			drawTexturedModalRect(x, y, u, v + barHeight, barWidthFilled, barHeight);
		}

		String caption = dragon.getDragonName();

		int color = 0x05db34;
		FontRenderer fr = mc.fontRenderer;
		fr.drawString(caption, width / 2 - fr.getStringWidth(caption) / 2, y - 10, color);
		
		//fr.drawStringWithShadow(caption, width / 2 - fr.getStringWidth(caption) / 2, y - 10, color);
		GL11.glColor4f(1, 1, 1, 1);
	}
}
