/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsstats.common;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import static rsstats.common.RSStats.instance;
import static rsstats.common.RSStats.proxy;
import rsstats.common.event.KeyHandler;
import rsstats.common.network.PacketOpenRSStatsInventory;
import rsstats.common.network.PacketOpenSSPPage;
import rsstats.data.ExtendedPlayer;
import rsstats.inventory.container.StatsContainer;

/**
 *
 * @author rares
 */
public class CommonProxy implements IGuiHandler {
    /** у*/
    public KeyHandler keyHandler;
    
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(RSStats.MODID.toLowerCase());

    void preInit(FMLPreInitializationEvent event) {
        // Когда сообщений станет много, их можно вынести в отдельный класс в метод init()
        INSTANCE.registerMessage(PacketOpenRSStatsInventory.MessageHandler.class, PacketOpenRSStatsInventory.class, 0, Side.SERVER);
        INSTANCE.registerMessage(PacketOpenSSPPage.MessageHandler.class, PacketOpenSSPPage.class, 1, Side.SERVER);
    }

    void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        
        // КОПИПАСТ ИЗ ТУТОРА. ХЗ насчет работоспособности
        //NetworkRegistry.INSTANCE.registerGuiHandler(this, new CommonProxy());
        
        /*proxy.*/registerKeyBindings();
    }

    void postInit(FMLPostInitializationEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        System.out.print("PIZDA1"+ID+"\n");
        switch (ID) {
            // Вызов тестового UI
            case RSStats.GUI: return new StatsContainer();
            // Вызов UI инвентаря статов
            case RSStats.SSP_UI_CODE: {
                System.out.print("player: " + player.toString());
                System.out.print("player.inventory: " + player.inventory.toString());
                System.out.print("ExtendedPlayer.get(player).statsInventory: " + ExtendedPlayer.get(player).statsInventory.toString());
                
                StatsContainer returned = new StatsContainer(player, player.inventory, ExtendedPlayer.get(player).statsInventory);
                System.out.print("returned: " + returned.toString());
                
                
                return returned;
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null; // Переопределяется в ClientProxy
    }
    
    // Переопределяется в ClientProxy
    public void registerKeyBindings() {}

    
    
}
