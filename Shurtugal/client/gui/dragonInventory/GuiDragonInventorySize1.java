package Shurtugal.client.gui.dragonInventory;

import org.lwjgl.opengl.GL11;

import Shurtugal.ShurtugalMod;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import Shurtugal.common.entity.Dragon.Inventory.ContainerDragon;
import Shurtugal.common.entity.Dragon.Inventory.ContainerDragonSize1;
import Shurtugal.common.entity.Dragon.Inventory.DragonInventory;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.StatCollector;

public class GuiDragonInventorySize1 extends GuiDragonInventory
{
	
	// No Saddle Bag GUI
	public GuiDragonInventorySize1(EntityTameableDragon dragon, EntityPlayer player)
	{
		super(new ContainerDragonSize1(dragon, player));
		this.Dragon = dragon;
		this.xSize = 175;
		this.ySize = 231;
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

		this.fontRenderer.drawString(StatCollector.translateToLocal("Shurtugal.SaddleBag"), 100, 70, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{

	    this.mc.func_110434_K().func_110577_a(textureLoc);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);

	}

}
