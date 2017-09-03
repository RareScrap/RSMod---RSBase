/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rarescrap.rsstats;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import ru.rarescrap.network.packets.RollPacketToServer;
import static ru.rarescrap.rsstats.RSStats.INSTANCE;
import ru.rarescrap.rsstats.items.StatItem;
import ru.rarescrap.rsstats.utils.DiceRoll;

/**
 *
 * @author rares
 */
public class CommonProxy {
    /**s
     * Фаза преинициализации мода. Тут регистрируются предметы, блоки и сообщения
     * @param e Объект события преинициализации
     * @param dices Список базовых дайсов, которыми могут пользоваться статы
     */
    public void preInit(FMLPreInitializationEvent e, ArrayList<DiceRoll> dices) {
        // Регистрация сообщения о пробросе статы
        INSTANCE.registerMessage(RollPacketToServer.MessageHandler.class, RollPacketToServer.class, 0, Side.SERVER);
        
        // Инициализация предметов статов
        StatItem strenghtStatItem = new StatItem(dices, "StrenghtStatItem", "rsstats:strenght", "item.StrenghtStatItem"); // 3 - rarescrap:StrenghtIcon_
        StatItem agilityStatItem = new StatItem(dices, "AgilityStatItem", "rsstats:agility", "item.AgilityStatItem");
        StatItem intelligenceStatItem = new StatItem(dices, "IntelligenceStatItem", "rsstats:intelligence", "item.IntelligenceStatItem");
        StatItem enduranceStatItem = new StatItem(dices, "EnduranceStatItem", "rsstats:endurance", "item.EnduranceStatItem");
        StatItem characterStatItem = new StatItem(dices, "CharacterStatItem", "rsstats:character", "item.CharacterStatItem");
        // Регистрация предметов статов
        GameRegistry.registerItem(strenghtStatItem, "StrenghtStatItem");
        GameRegistry.registerItem(agilityStatItem, "AgilityStatItem");
        GameRegistry.registerItem(intelligenceStatItem, "IntelligenceStatItem");
        GameRegistry.registerItem(enduranceStatItem, "EnduranceStatItem");
        GameRegistry.registerItem(characterStatItem, "CharacterStatItem");
    }
    
    public void preInit(FMLPreInitializationEvent e) {
    
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
