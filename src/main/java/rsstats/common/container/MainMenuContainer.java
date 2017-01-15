/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsstats.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/**
 *
 * @author rares
 */
public class MainMenuContainer extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
    
}
