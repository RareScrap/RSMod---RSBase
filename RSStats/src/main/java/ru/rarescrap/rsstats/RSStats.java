package ru.rarescrap.rsstats;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import java.util.List;
import net.minecraft.item.Item;
import ru.rarescrap.network.packets.RollPacketToServer;

import ru.rarescrap.rsstats.items.StatItem;
import ru.rarescrap.rsstats.utils.DiceRoll;

/**
 * Главный класс мода RSStats
 * @author RareScrap
 */
@Mod(modid="rsstats", version="0.0.1a", name="RSStats")
public class RSStats {
    /** ID мода для итендефекации в FML */
    public static final String MODID = "rsstats";
    /** Отображаемое имя мода (не путать с ID) */
    public static final String MODNAME = "RSStats";
    /** Версия мода */
    public static final String VERSION = "0.0.1a";
    
    /** Прокси, с функциями, которые должы вызываться и на сервере и на клиенте */
    @SidedProxy(clientSide="ru.rarescrap.rsstats.ClientProxy", serverSide="ru.rarescrap.rsstats.ServerProxy")
    public static CommonProxy proxy;
    
    // ХЗ можно ли делать это статиком
    /** TODO: ХЗ зачем это */
    public static SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper("test");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        
        //TODO: КОД НИЖЕ МОЖНО(НУЖНО?) ВЫНЕСТИ В СООТВЕТСТВУЮЩИЕ ПРОКСИ
        
        //INSTANCE.registerMessage(RollPacketToServer.MessageHandler.class, RollPacketToServer.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(RollPacketToServer.MessageHandler.class, RollPacketToServer.class, 1, Side.SERVER);
    
        DiceRoll[] dices = new DiceRoll[5];
        dices[0] = new DiceRoll(4);
        dices[1] = new DiceRoll(6);
        dices[2] = new DiceRoll(8);
        dices[3] = new DiceRoll(10);
        dices[4] = new DiceRoll(12);
        
        // Инициализация и регистрация тестового предмета
        StatItem strenghtStatItem = new StatItem(dices, "StrenghtStatItem", "rsstats:strenght", "item.StrenghtStatItem"); // 3 - rarescrap:StrenghtIcon_
        StatItem agilityStatItem = new StatItem(dices, "AgilityStatItem", "rsstats:strenght", "item.AgilityStatItem");
        StatItem intelligenceStatItem = new StatItem(dices, "IntelligenceStatItem", "rsstats:strenght", "item.IntelligenceStatItem");
        StatItem enduranceStatItem = new StatItem(dices, "EnduranceStatItem", "rsstats:strenght", "item.EnduranceStatItem");
        StatItem charismaStatItem = new StatItem(dices, "CharismaStatItem", "rsstats:strenght", "item.CharismaStatItem");
        
        GameRegistry.registerItem(strenghtStatItem, "StrenghtStatItem");
        GameRegistry.registerItem(agilityStatItem, "AgilityStatItem");
        GameRegistry.registerItem(intelligenceStatItem, "IntelligenceStatItem");
        GameRegistry.registerItem(enduranceStatItem, "EnduranceStatItem");
        GameRegistry.registerItem(charismaStatItem, "CharismaStatItem");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}