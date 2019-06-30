package cn.glycol.cfh;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

@Mod(CFH.MODID)
public class CFH {

	public static final String MODID = "cfh";
	
	public static final Logger LOGGER = LogManager.getLogger("cfh");

	public CFH() {
		System.out.println("A player's head!");
	}
	
	
	@Mod.EventBusSubscriber
	public static class Registry {
		
		@SubscribeEvent
		public static void onServerStart(FMLServerStartingEvent evt) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("CFH.onServerStart()");
			System.out.println();
			System.out.println();
			System.out.println();
			SkullTabs.init();
		}
		
	}
	
}
