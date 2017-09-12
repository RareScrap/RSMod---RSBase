package rsstats.common.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import rsstats.common.RSStats;

/**
 * Обработччик пакетов вызова UI
 * @author RareScrap
 */
public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RSStats.MODID.toLowerCase());

    /**
     * Инициализирует обработчик, регистрируя сооб
     */
    public static void init() { 
        INSTANCE.registerMessage(PacketOpenRSStatsInventory.MessageHandler.class, PacketOpenRSStatsInventory.class, 0, Side.SERVER);
    }
}
