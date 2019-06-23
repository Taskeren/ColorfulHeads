package cn.glycol.colorfulheads;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = ColorfulHeads.MODID, name = ColorfulHeads.NAME, version = ColorfulHeads.VERSION)
public class ColorfulHeads {
	public static final String MODID = "colorful_heads";
	public static final String NAME = "Colorful Heads";
	public static final String VERSION = "dev(0)";
	
	public static final List<ItemStack> CFH_COLORS = Lists.<ItemStack>newArrayList();
	public static final List<ItemStack> CFH_BLOCKS = Lists.<ItemStack>newArrayList();
	
	@EventHandler
	public void init(FMLInitializationEvent evt) {
		load(CFH_COLORS, CFG.skullsColors);
		load(CFH_BLOCKS, CFG.skullsBlocks);
	}
	
	public static void load(List<ItemStack> list, String[] codes) {
		for(String code : codes) {
			ItemStack skull = SkullUtils.deserialize(code);
			if(skull != ItemStack.EMPTY) list.add(skull);
		}
	}
	
	public static final CreativeTabs tabColors = new SkullTab("cfh.colors", Items.DYE.getDefaultInstance(), CFH_COLORS);
	public static final CreativeTabs tabBlocks = new SkullTab("cfh.blocks", Items.BRICK.getDefaultInstance(), CFH_BLOCKS);
	
}
