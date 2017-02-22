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
import rsstats.data.StatUnit;

/**
 *
 * @author rares
 */
public class PlayerStatsSet implements IExtendedEntityProperties {
    
    private static final String identifier = "tutorialPlayerData";

    // PROPERTIES =============================================================

    private final EntityPlayer player;
    
    public StatUnit[] statUnit;

    // CONSTRUCTOR, GETTER, REGISTER ==========================================

    public PlayerStatsSet(EntityPlayer player) {
        this.player = player;
        //Тут нужно сделать загрузку из даты игрока (короч тут присваивается значения статам)
    }

    public static PlayerStatsSet get(EntityPlayer player) {
        return (PlayerStatsSet) player.getExtendedProperties(identifier);
    }

    public static void register(EntityPlayer player) {
        player.registerExtendedProperties(identifier, new PlayerStatsSet(player));
    }

    public boolean isServerSide() {
        return this.player instanceof EntityPlayerMP;
    }

    // LOAD, SAVE =============================================================

    @Override
    public void saveNBTData(NBTTagCompound nbt) {
    }

    @Override
    public void loadNBTData(NBTTagCompound nbt) {
    }

    @Override
    public void init(Entity entity, World world) {
    }
    
}
