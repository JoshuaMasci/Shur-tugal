/*
 ** 2011 December 10
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */

package Shurtugal.client.render;

import Shurtugal.common.entity.LifeStage;
import Shurtugal.client.model.DragonModel;
import Shurtugal.client.model.ModelPartProxy;
import Shurtugal.common.Item.ItemDragonArmor;
import Shurtugal.common.entity.Dragon.EntityBaseDragon;
import Shurtugal.common.entity.Dragon.EntityTameableDragon;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelDragon;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.src.*;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

/**
 * Generic renderer for all dragons.
 * 
 * @author Iamshortman
 */
public class DragonRenderer extends RenderLiving
{

	public static boolean updateModel;
	public DragonModel modelArmor;
	public DragonModel modelDragon;

	public static final float armorScale = 0.6F;

	public DragonRenderer(DragonModel model)
	{
		super(model, 0);
		renderPassModel = model;

		modelDragon = new DragonModel();
		modelArmor = new DragonModel(armorScale);

	}

	/**
	 * Queries whether should render the specified pass or not.
	 */
	@Override
	public int shouldRenderPass(EntityLivingBase entity, int pass, float scale)
	{
		return shouldRenderPass((EntityBaseDragon) entity, pass, scale);
	}

	public int shouldRenderPass(EntityBaseDragon dragon, int pass, float scale)
	{
		// update dragon model every second if enabled
		if (updateModel && dragon.ticksExisted % 20 == 0)
		{
			this.modelDragon = new DragonModel();
			this.modelArmor = new DragonModel(armorScale);
		}

		//Adds 1 to get the item
		ItemStack var4 = dragon.getCurrentItemOrArmor(pass + 1);

		if (var4 != null)
		{
			Item var5 = var4.getItem();

			if (var5 instanceof ItemDragonArmor)
			{
				ItemDragonArmor var6 = (ItemDragonArmor) var5;
				this.loadTexture(var6.getArmorTextureFile());
				DragonModel var7 = modelArmor;
				var7.renderPass = pass;
				var7.head.showModel = (pass == 0);
				var7.neck.showModel = (pass == 0);

				var7.body.showModel = (pass == 1);
				var7.back.showModel = (pass == 1);

				var7.forecrus.showModel = (pass == 2);
				var7.forefoot.showModel = (pass == 2);
				var7.forethigh.showModel = (pass == 2);
				var7.foretoe.showModel = (pass == 2);

				var7.hindcrus.showModel = (pass == 2);
				var7.hindfoot.showModel = (pass == 2);
				var7.hindthigh.showModel = (pass == 2);
				var7.hindtoe.showModel = (pass == 2);

				var7.tail.showModel = (pass == 3);

				var7.wingArm.showModel = false;
				var7.wingForearm.showModel = false;

				this.setRenderPassModel(var7);

				if (var7 != null)
				{
					var7.onGround = this.mainModel.onGround;
				}

				if (var7 != null)
				{
					var7.isRiding = this.mainModel.isRiding;
				}

				if (var7 != null)
				{
					var7.isChild = this.mainModel.isChild;
				}

				float var8 = 1.0F;

				if (var6.getArmorMaterial() == EnumArmorMaterial.CLOTH)
				{
					int var9 = var6.getColor(var4);
					float var10 = (float) (var9 >> 16 & 255) / 255.0F;
					float var11 = (float) (var9 >> 8 & 255) / 255.0F;
					float var12 = (float) (var9 & 255) / 255.0F;
					GL11.glColor3f(var8 * var10, var8 * var11, var8 * var12);

					if (var4.isItemEnchanted())
					{
						return 31;
					}

					return 16;
				}

				GL11.glColor3f(var8, var8, var8);

				if (var4.isItemEnchanted())
				{
					return 15;
				}

				return 1;
			}
		}

		return -1;
	}

	private void loadTexture(String str) 
	{
		Minecraft.getMinecraft().func_110434_K().func_110577_a(new ResourceLocation("shurtugal", str));
	}

	@Override
	protected void rotateCorpse(EntityLivingBase par1EntityLiving, float par2, float par3, float par4)
	{
		rotateCorpse((EntityBaseDragon) par1EntityLiving, par2, par3, par4);
	}

	protected void rotateCorpse(EntityBaseDragon dragon, float par2, float par3, float par4)
	{
		GL11.glRotatef(180 - par3, 0, 1, 0);
	}

	/**
	 * Renders the model in RenderLiving
	 */
	@Override
	protected void renderModel(EntityLivingBase entity, float moveTime, float moveSpeed, float ticksExisted, float lookYaw, float lookPitch, float scale)
	{
		renderModel((EntityBaseDragon) entity, moveTime, moveSpeed, ticksExisted, lookYaw, lookPitch, scale);
	}

	protected void renderModel(EntityBaseDragon dragon, float moveTime, float moveSpeed, float ticksExisted, float lookYaw, float lookPitch, float scale)
	{
		if (dragon.isSaddled() != 0)
		{
			this.loadTexture(dragon.getTexture(1));
			((DragonModel) mainModel).renderPass = 5;
			((DragonModel) mainModel).render(dragon, moveTime, moveSpeed, ticksExisted, lookYaw, lookPitch, scale);
		}

		((DragonModel) mainModel).renderPass = -1;
		this.loadTexture(dragon.getTexture(0));
		this.mainModel.render(dragon, moveTime, moveSpeed, ticksExisted, lookYaw, lookPitch, scale);

	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTicks)
	{
		doRender((EntityBaseDragon) entity, x, y, z, yaw, partialTicks);
	}

	public void doRender(EntityBaseDragon dragon, double x, double y, double z, float yaw, float partialTicks)
	{
		super.doRender(dragon, x, y, z, yaw, partialTicks);
	}

	@Override
	protected void func_82408_c(EntityLivingBase par1EntityLiving, int par2, float par3)
	{
		ItemStack var4 = par1EntityLiving.getCurrentItemOrArmor(par2);

		if (var4 != null)
		{
			Item var5 = var4.getItem();

			if (var5 instanceof ItemDragonArmor)
			{
				ItemDragonArmor var6 = (ItemDragonArmor) var5;
				this.loadTexture("/textures/dragon/armor/DragonArmorLeather.png");
				float var7 = 1.0F;
				GL11.glColor3f(var7, var7, var7);
			}
		}
	}


	@Override
	protected void preRenderCallback(EntityLivingBase entity, float partialTicks)
	{
		preRenderCallback((EntityBaseDragon) entity, partialTicks);
	}

	protected void preRenderCallback(EntityBaseDragon dragon, float partialTicks)
	{
		float size = dragon.getSize() * 0.8f;
		GL11.glScalef(size, size, size);
	}

	@Override
	protected ResourceLocation func_110775_a(Entity entity) 
	{
		return null;
	}
	
	@Override
    protected void renderLivingLabel(EntityLivingBase par1EntityLivingBase, String par2Str, double par3, double par5, double par7, int par9)
    {
		//This Stops the Name Tags From Being Rendered
    }
}
