/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsstats.client;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import rsstats.client.gui.MainMenuGUI;
import rsstats.common.CommonProxy;
import rsstats.common.RSStats;
import rsstats.common.event.KeyHandler;

/**
 *
 * @author rares
 */
public class ClientProxy extends CommonProxy {
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world instanceof WorldClient) {
            switch (ID) {
                case RSStats.GUI: return new MainMenuGUI();
            }
        }
        return null;
    }
    
    @Override
	public void registerKeyBindings() {
            keyHandler = new KeyHandler();
            FMLCommonHandler.instance().bus().register(keyHandler);
            MinecraftForge.EVENT_BUS.register(new MainMenuGUI());
	}
}
