/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsstats.data;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import rsstats.inventory.StatsInventory;

/**
 *
 * @author rares
 */
public class ExtendedPlayer implements IExtendedEntityProperties {
    /** Каждый наследник {@link IExtendedEntityProperties} должен иметь индивидуальное имя */
    private static final String INV_NAME = "StatsInventory";
    
    private final EntityPlayer player;
    
    /** Инвентарь для статов */
    public final StatsInventory statsInventory = new StatsInventory();
    
    /*
    Тут в виде полей можно хранить дополнительную информацию о Entity: мана,
    золото, хп, переносимый вес, уровень радиации, репутацию и т.д. Т.е. все то,
    что нельзя хранить в виде блоков
    */

    public ExtendedPlayer(EntityPlayer player) {
        this.player = player;
    }
    
    /**
     * Used to register these extended properties for the player during EntityConstructing event
     * This method is for convenience only; it will make your code look nicer
     * @param player
     */
    public static final void register(EntityPlayer player) {
        player.registerExtendedProperties(ExtendedPlayer.INV_NAME, new ExtendedPlayer(player));
    }
    
    /**
     * Returns ExtendedPlayer properties for player
     * This method is for convenience only; it will make your code look nicer
     */
    public static final ExtendedPlayer get(EntityPlayer player) {
       return (ExtendedPlayer) player.getExtendedProperties(INV_NAME);
    }

    public boolean isServerSide() {
        return this.player instanceof EntityPlayerMP;
    }

    // LOAD, SAVE =============================================================

    @Override
    public void saveNBTData(NBTTagCompound properties) {
        this.statsInventory.writeToNBT(properties);
    }

    @Override
    public void loadNBTData(NBTTagCompound properties) {
        this.statsInventory.readFromNBT(properties);
    }

    @Override
    public void init(Entity entity, World world) {
    }
    
}
