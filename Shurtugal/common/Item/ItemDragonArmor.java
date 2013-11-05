package Shurtugal.common.Item;

import Shurtugal.ShurtugalMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemDragonArmor extends Item
{
	/** Holds the 'base' maxDamage that each armorType have. */
	private static final int[] maxDamageArray = new int[]
	{ 11, 16, 15, 13 };

	/**
	 * Stores the armor type: 0 is helmet, 1 is plate, 2 is legs and 3 is Tail
	 */
	public final int armorType;

	/** Holds the amount of damage that the armor reduces at full durability. */
	public final int damageReduceAmount;

	/** The EnumArmorMaterial used for this ItemArmor */
	private final EnumArmorMaterial material;

	public String fileLoc = "";

	
    public static final String[] field_94603_a = new String[] {"slot_empty_helmet", "slot_empty_chestplate", "slot_empty_leggings", "slot_empty_tail"};
    private static final String[] field_94606_cu = new String[] {"helmetCloth_overlay", "chestplateCloth_overlay", "leggingsCloth_overlay", "tailCloth_overlay"};
    public static final String[] name = new String[] {"helmet", "chestplate", "leggings", "tail"};
    
    @SideOnly(Side.CLIENT)
    private Icon field_94605_cw;

    
    public Icon BackGroundIcon;
    
	public ItemDragonArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial, int textureIndex, int ArmorType)
	{
		super(par1);
		this.material = par2EnumArmorMaterial;
		this.armorType = ArmorType;
		this.damageReduceAmount = par2EnumArmorMaterial.getDamageReductionAmount(ArmorType);
		this.setMaxDamage(par2EnumArmorMaterial.getDurability(ArmorType));
		this.maxStackSize = 1;

		this.setCreativeTab(ShurtugalMod.tabShurtugal);
	}

	public ItemDragonArmor setArmorTextureFile(String str)
	{
		fileLoc = str;
		return this;
	}

	public String getArmorTextureFile()
	{
		return fileLoc;
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
	{
		if (par2 > 0)
		{
			return 16777215;
		}
		else
		{
			int var3 = this.getColor(par1ItemStack);

			if (var3 < 0)
			{
				var3 = 16777215;
			}

			return var3;
		}
	}

	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return this.material == EnumArmorMaterial.CLOTH;
	}

	/**
	 * Return the enchantability factor of the item, most of the time is based
	 * on material.
	 */
	public int getItemEnchantability()
	{
		return this.material.getEnchantability();
	}

	/**
	 * Return the armor material for this armor item.
	 */
	public EnumArmorMaterial getArmorMaterial()
	{
		return this.material;
	}

	/**
	 * Return whether the specified armor ItemStack has a color.
	 */
	public boolean hasColor(ItemStack par1ItemStack)
	{
		return this.material != EnumArmorMaterial.CLOTH ? false : (!par1ItemStack.hasTagCompound() ? false : (!par1ItemStack.getTagCompound().hasKey("display") ? false : par1ItemStack
				.getTagCompound().getCompoundTag("display").hasKey("color")));
	}

	/**
	 * Return the color for the specified armor ItemStack.
	 */
	public int getColor(ItemStack par1ItemStack)
	{
		if (this.material != EnumArmorMaterial.CLOTH)
		{
			return -1;
		}
		else
		{
			NBTTagCompound var2 = par1ItemStack.getTagCompound();

			if (var2 == null)
			{
				return 10511680;
			}
			else
			{
				NBTTagCompound var3 = var2.getCompoundTag("display");
				return var3 == null ? 10511680 : (var3.hasKey("color") ? var3.getInteger("color") : 10511680);
			}
		}
	}

	public boolean onItemUseFirst(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		if (!par3World.isRemote)
		{
			if (par3World.getBlockId(par4, par5, par6) == Block.cauldron.blockID)
			{
				this.removeColor(par1ItemStack);
				return true;
			}
		}
		return false;
	}
	
	
	
	/**
	 * Remove the color from the specified armor ItemStack.
	 */
	public void removeColor(ItemStack par1ItemStack)
	{
		if (this.material == EnumArmorMaterial.CLOTH)
		{
			NBTTagCompound var2 = par1ItemStack.getTagCompound();

			if (var2 != null)
			{
				NBTTagCompound var3 = var2.getCompoundTag("display");

				if (var3.hasKey("color"))
				{
					var3.removeTag("color");
				}
			}
		}
	}

	public void func_82813_b(ItemStack par1ItemStack, int par2)
	{
		if (this.material != EnumArmorMaterial.CLOTH)
		{
			throw new UnsupportedOperationException("Can\'t dye non-leather!");
		}
		else
		{
			NBTTagCompound var3 = par1ItemStack.getTagCompound();

			if (var3 == null)
			{
				var3 = new NBTTagCompound();
				par1ItemStack.setTagCompound(var3);
			}

			NBTTagCompound var4 = var3.getCompoundTag("display");

			if (!var3.hasKey("display"))
			{
				var3.setCompoundTag("display", var4);
			}

			var4.setInteger("color", par2);
		}
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{

		return this.material.getArmorCraftingMaterial() == par2ItemStack.itemID ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	/**
	 * Returns the 'max damage' factor array for the armor, each piece of armor
	 * have a durability factor (that gets multiplied by armor material factor)
	 */
	static int[] getMaxDamageArray()
	{
		return maxDamageArray;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister par1IconRegister)
	{
    	String str = "shurtugal:dragonarmor/";
    	String name = this.name[this.armorType] + this.material.name();
    	name = name.toLowerCase();
    	str += name;
    	this.itemIcon = par1IconRegister.registerIcon(str);

        if (this.material == EnumArmorMaterial.CLOTH)
        {
            this.field_94605_cw = par1IconRegister.registerIcon("shurtugal:dragonarmor/" + field_94606_cu[this.armorType]);
        }

        this.BackGroundIcon = par1IconRegister.registerIcon("shurtugal:dragonarmor/" + field_94603_a[this.armorType]);
    }
	
    @Override
    public Icon getIconFromDamageForRenderPass(int par1, int par2)
    {
        return par2 == 1 ? this.field_94605_cw : super.getIconFromDamageForRenderPass(par1, par2);
    }
    
    @SideOnly(Side.CLIENT)
    public static Icon getbackgroundIcon(int par0)
    {
        switch (par0)
        {
            case 0:
                return ((ItemDragonArmor) ItemHandler.helmetDiamondDragon).BackGroundIcon;
            case 1:
                return ((ItemDragonArmor) ItemHandler.plateDiamondDragon).BackGroundIcon;
            case 2:
                return ((ItemDragonArmor) ItemHandler.legsDiamondDragon).BackGroundIcon;
            case 3:
                return ((ItemDragonArmor) ItemHandler.tailDiamondDragon).BackGroundIcon;
            default:
                return null;
        }
    }
    
}
