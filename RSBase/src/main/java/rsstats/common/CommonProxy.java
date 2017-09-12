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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import static rsstats.common.RSStats.instance;
import static rsstats.common.RSStats.proxy;
import rsstats.common.container.MainMenuContainer;
import rsstats.common.event.KeyHandler;
import rsstats.common.network.PacketHandler;

/**
 *
 * @author rares
 */
public class CommonProxy  implements IGuiHandler {
    /** у*/
    public KeyHandler keyHandler;

    void preInit(FMLPreInitializationEvent event) {
        PacketHandler.init();
    }

    void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
        /*proxy.*/registerKeyBindings();
    }

    void postInit(FMLPostInitializationEvent event) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case RSStats.GUI: return new MainMenuContainer();
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
