package rsstats.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import rsstats.common.network.PacketHandler;

/**
 * Главный класс мода. Представляет собой основу для всех остальных РП модов.
 * Реализует главное меню для UI других РП модов. Предоставляет API создания
 * "страниц" меню, которые затем помещаются на хост-мод (этот мод)
 * @author rares
 */
@Mod(modid = RSStats.MODID, version = RSStats.VERSION)
public class RSStats {
    /** ID мода */
    public static final String MODID = "rsstats";
    /** Имя мода */
    public static final String MODNAME = "RS Stats";
    /** Версия мода */
    public static final String VERSION = "0.0.1a";
    
    public static final int GUI = 0; // Код GUI окна, кажется
    
    /** Объект-экземпляр мода */
    @Mod.Instance(MODID)
    public static RSStats instance = new RSStats();
    
    /** Общий прокси */
    @SidedProxy(clientSide = "rsstats.client.ClientProxy", serverSide = "rsstats.common.СommonProxy")
    public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {	
	proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
