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
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.Item;
import ru.rarescrap.network.packets.RollPacketToServer;

import ru.rarescrap.rsstats.items.StatItem;
import ru.rarescrap.rsstats.utils.DiceRoll;

/**
 * Главный класс мода RSStats - видоизмененной ролевой системы
 * "Дневник аватюриста".
 * 
 * @author RareScrap
 */
@Mod(modid=RSStats.MODID, version=RSStats.VERSION, name=RSStats.MODNAME)
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
    
    /** Объект, регистриующий сообщения, которыми обмениваются клиент и сервер */
    public static SimpleNetworkWrapper INSTANCE = new SimpleNetworkWrapper(MODID);

    /** Дайсы, которые будут использоваться в моде */
    ArrayList<DiceRoll> dices = new ArrayList<DiceRoll>();
    
    /**
     * Конструктор, инициализирующий список допустимых дайсов
     */
    public RSStats() {
        // Определяем дайсы
        dices.add(new DiceRoll(4));
        dices.add(new DiceRoll(6));
        dices.add(new DiceRoll(8));
        dices.add(new DiceRoll(10));
        dices.add(new DiceRoll(12));
    }
    
    /**
     * Фаза преинициализации мода. Тут регистрируются предметы, блоки и сообщения
     * @param event Объект события преинициализации
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event, dices); // Преинициализация в общем прокси
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