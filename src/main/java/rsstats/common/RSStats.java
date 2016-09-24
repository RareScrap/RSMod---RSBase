package rsstats.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RSStats.MODID, version = RSStats.VERSION)
public class RSStats
{
    public static final String MODID = "rsstats";
    public static final String MODNAME = "RS Stats";
    public static final String VERSION = "0.0.1a";
    
    @Mod.Instance(MODID)
    public static RSStats instance = new RSStats();
    
    //@SidedProxy(clientSide = "rsstats.client.ClientProxy", serverSide = "rsstats.server.ServerProxy")
    //public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //this.proxy.preInit(event);
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
	//this.proxy.init(event);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //this.proxy.postInit(event);
    }
}

//TODO: Добавить прокси