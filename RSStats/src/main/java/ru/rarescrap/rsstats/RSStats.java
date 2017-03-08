package ru.rarescrap.rsstats;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import ru.rarescrap.rsstats.items.StatItem;

@Mod(modid="rsstats", version="0.0.1a", name="RSStats")
public class RSStats {
    public static final String MODID = "rsstats"; // ID мода
    public static final String MODNAME = "RSStats"; // Имя мода
    public static final String VERSION = "0.0.1a"; // Версия мода
    public static Item strenghtStatItem;
    
    public String[] dices = new String[10];
    

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        this.dices[0] = "d4";
        this.dices[1] = "d4+1";
        this.dices[2] = "d6";
        this.dices[3] = "d6+1";
        this.dices[4] = "d8";
        this.dices[5] = "d8+1";
        this.dices[6] = "d10";
        this.dices[7] = "d10+1";
        this.dices[8] = "d12";
        this.dices[9] = "d20";
        
        strenghtStatItem = new StatItem(dices, "StrenghtStatItem", "rarescrap:StrenghtIcon_", "item.StrenghtStatItem");
        GameRegistry.registerItem((Item)strenghtStatItem, (String)"StrenghtStatItem");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }
}