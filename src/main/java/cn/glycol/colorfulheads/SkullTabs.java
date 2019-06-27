package cn.glycol.colorfulheads;

import java.util.List;

import cn.glycol.colorfulheads.data.DataManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SkullTabs extends CreativeTabs {

	final ItemStack icon;
	
	final List<ItemStack> skulls;
	
	public SkullTabs(String name, ItemStack icon, List<ItemStack> items) {
		super(name);
		this.icon = icon;
		this.skulls = items;
	}
	
	@Override
	public ItemStack createIcon() {
		return icon;
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> items) {
		items.addAll(this.skulls);
		super.displayAllRelevantItems(items);
	}
	
	public static SkullTabs tabCustom, tabColors, tabBlocks, tabInterior;
	
	public static void init() {
		
		tabCustom = new SkullTabs("cfh.custom", new ItemStack(Items.APPLE), DataManager.getSkulls("custom"));
		tabColors = new SkullTabs("cfh.colors", new ItemStack(Items.DYE, 1, 15), DataManager.getSkulls("colors"));
		tabBlocks = new SkullTabs("cfh.blocks", new ItemStack(Blocks.BRICK_BLOCK), DataManager.getSkulls("blocks"));
		tabInterior = new SkullTabs("cfh.interior", new ItemStack(Items.FLOWER_POT), DataManager.getSkulls("interior"));
		
	}
	
}
