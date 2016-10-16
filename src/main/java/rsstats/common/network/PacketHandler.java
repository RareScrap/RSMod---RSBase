package rsstats.common.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import rsstats.common.RSStats;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RSStats.MODID.toLowerCase());

    public static void init()
    { 
        INSTANCE.registerMessage(PacketOpenRSStatsInventory.class, PacketOpenRSStatsInventory.class, 0, Side.SERVER);
      
    }
    
    
}
