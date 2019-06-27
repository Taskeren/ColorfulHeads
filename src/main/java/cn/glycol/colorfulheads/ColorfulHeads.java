package cn.glycol.colorfulheads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

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
	
	@EventHandler
	public void server(FMLServerStartingEvent evt) {
		SkullTabs.reload();
	}
	
}
