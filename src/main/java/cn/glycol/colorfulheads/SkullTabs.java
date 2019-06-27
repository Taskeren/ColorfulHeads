package cn.glycol.colorfulheads;

import java.util.List;

import com.google.common.collect.Lists;

import cn.glycol.colorfulheads.data.DataManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class SkullTabs extends CreativeTabs {

	/** 重载时调用的列表 */
	private static final List<SkullTabs> tabsReloadable = Lists.newArrayList();
	
	final ItemStack icon;
	
	protected String dataName;
	protected List<ItemStack> skulls;
	
	/**
	 * 用这种方法构造的，不会自动读取头颅列表，需要在FMLServerStartingEvent时调用reload()加载。
	 */
	public SkullTabs(String name, ItemStack icon, String dataName) {
		super(name);
		tabsReloadable.add(this);
		this.icon = icon;
		this.dataName = dataName;
	}
	
	/**
	 * 用这种方法构造的，无法重载头颅列表。
	 */
	@Deprecated
	public SkullTabs(String name, ItemStack icon, List<ItemStack> items) {
		super(name);
		this.icon = icon;
		this.skulls = items;
	}
	
	/**
	 * 载入头颅。FMLServerStartingEvent时被调用。
	 */
	public void load() {
		if(dataName != null) {
			skulls = DataManager.getSkulls(dataName);
		}
	}
	
	@Override
	public void displayAllRelevantItems(NonNullList<ItemStack> items) {
		items.addAll(this.skulls);
		super.displayAllRelevantItems(items);
	}
	
	@Override
	public ItemStack createIcon() {
		return icon;
	}
	
	/** 加载默认的几个列表，请在FMLServerStartingEvent时调用reload()！ */
	public static void init() {
		
		new SkullTabs("cfh.custom", new ItemStack(Items.APPLE), "custom");
		new SkullTabs("cfh.colors", new ItemStack(Items.DYE, 1, 15), "colors");
		new SkullTabs("cfh.blocks", new ItemStack(Blocks.BRICK_BLOCK), "blocks");
		new SkullTabs("cfh.interior", new ItemStack(Items.FLOWER_POT), "interior");
		
	}
	
	/**
	 * 调用可重载头颅的所有列表重载头颅。请在FMLServerStartingEvent调用！
	 */
	public static void reload() {
		tabsReloadable.forEach(tab -> tab.load());
	}
	
}
