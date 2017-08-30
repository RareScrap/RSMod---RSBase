/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rarescrap.rsstats.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import net.minecraft.util.StatCollector;

/**
 * Объект броска дайсов
 * @author RareScrap
 */
public class DiceRoll implements Serializable {
    /** Имя пробрасываемой статы */
    private String statName = null;
    /** Количество граней на дайсе */
    public final int dice;
    /** Список модификаторов, которые должны быть учтены */
    private List<RollModificator> modificators;
    
    /**
     * Конструктор, инициализирующий свои поля
     * @param dice Количество граней на дайсе 
     */
    public DiceRoll(int dice) {
        this.dice = dice;
    }
    
    public void setStatName(String statName) {
        this.statName = statName;
    }
    
    /**
     * Добавляет новый модификатор к броску
     * @param rollModificator 
     */
    public void addModificatot(RollModificator rollModificator) {
        modificators.add(rollModificator);
    }
    
    public List<RollModificator> getModificators() {
        return modificators;
    }
    
    /**
     * Вычисляет бросок
     * @return Сообщения броска
     */
    public String roll() {
        if (statName == null)
            throw new NullPointerException("statName is null");
        
        // Подготовка объектов для генерации случайных чисел
        Random randomObject = new Random(); // Генератор случайных чисел
        int rollResultInt = 0; // Сумма всех бросков при взрыве
        String rollResultString = new String(); // Строка вида "4+4+4+2"

        int random; // Случайное число, заполняемое при каждой итерации цикла
        do {
            random = randomObject.nextInt(dice);
            rollResultInt += random;
            rollResultString += random;

            // Обработка взрывных бросков
            if (random == dice) {
                rollResultString += "+";
            } else {
                rollResultString += dice + "=" + rollResultInt;
                break;
            }
        } while (true);
        
        rollResultString += " ";

        // Обработка модификаторов
        for (int i = 0; i < modificators.size(); ++i) {
            RollModificator rollModificator = modificators.get(i);

            rollResultInt += rollModificator.value;
            rollResultString += "+" +rollModificator.value + "("
                    + rollModificator.description + ") ";
        }

        // Вывести общую сумму бросков
        rollResultString += "=== " + rollResultInt;

        // Сформировать итоговое сообщение
        return StatCollector.translateToLocalFormatted("item.StatItem.rollChatMessage", statName)
                + ": " + rollResultString;
    }
}
