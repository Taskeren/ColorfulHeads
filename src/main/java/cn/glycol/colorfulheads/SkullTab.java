package cn.glycol.colorfulheads;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SkullTab extends CreativeTabs {

	final ItemStack icon;
	
	final List<ItemStack> items;
	
	public SkullTab(String name, ItemStack icon, List<ItemStack> items) {
		super(name);
		this.icon = icon;
		this.items = items;
	}
	
	@Override
	public ItemStack createIcon() {
		return icon;
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> items) {
		items.addAll(this.items);
		super.displayAllRelevantItems(items);
	}
	
}
