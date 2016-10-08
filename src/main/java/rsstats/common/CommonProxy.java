/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsstats.common;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import rsstats.common.container.StatsAndInvContainer;
import rsstats.common.event.KeyHandler;

/**
 *
 * @author rares
 */
public class CommonProxy  implements IGuiHandler {
    
    public KeyHandler keyHandler;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case RSStats.GUI: return new StatsAndInvContainer();
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
