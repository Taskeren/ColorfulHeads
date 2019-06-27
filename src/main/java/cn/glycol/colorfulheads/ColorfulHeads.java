package cn.glycol.colorfulheads;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

import cn.glycol.colorfulheads.data.DataManager;
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
	
	public static final Logger LOGGER = LogManager.getLogger("CFH");
	
	@EventHandler
	public void init(FMLInitializationEvent evt) {
		SkullTabs.init();
	}
	
}
