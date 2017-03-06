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

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        strenghtStatItem = new StatItem();
        GameRegistry.registerItem((Item)strenghtStatItem, (String)"StrenghtStatItem");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }
}