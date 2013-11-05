package Shurtugal.client.gui.dragonInventory;

import org.lwjgl.opengl.GL11;

import Shurtugal.ShurtugalMod;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import Shurtugal.common.entity.Dragon.Inventory.ContainerDragon;
import Shurtugal.common.entity.Dragon.Inventory.DragonInventory;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiDragonInventory extends GuiContainer
{
	public EntityTameableDragon Dragon;
	public EntityPlayer Player;
	public static final ResourceLocation textureLoc = new ResourceLocation("/mods/Shurtugal/textures/gui/DragonGui.png");
	
	// No Saddle Bag GUI
	public GuiDragonInventory(EntityTameableDragon dragon, EntityPlayer player)
	{
		super(new ContainerDragon(dragon, player));
		Dragon = dragon;
		Player = player;
		this.xSize = 175;
		this.ySize = 148 + 25;
	}

	public GuiDragonInventory(Container container)
	{
		super(container);
	}
	
	@Override
	public void initGui()
    {
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(1, 0, 0, "Mouse Based Flight"));
    }

	/**
	 * Draw the foreground layer for the GuiContainer (everything in front of
	 * the items)
	 */
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString(StatCollector.translateToLocal("Shurtugal.DragonGui"), 60, 6, 4210752);
		// this.fontRenderer.drawString(StatCollector.translateToLocal("Name: "
		// + Dragon.getEntityName()), 30, 18, 4210752);
		float dragonHealth = ((float) Dragon.getDragonHealth2() / (float) Dragon.getMaxHealth()) * 100;

		this.fontRenderer.drawString(("Health: " + dragonHealth + "%"), 30, 18, 4210752);
		this.fontRenderer.drawString(("Armor Value: " + Dragon.getTotalArmorValue()), 30, 30, 4210752);

		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 100, 70, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
	    this.mc.func_110434_K().func_110577_a(textureLoc);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;

		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, 148);
		this.drawTexturedModalRect(var5, var6 + 148, 0, 206, this.xSize, 25);

	}

}
