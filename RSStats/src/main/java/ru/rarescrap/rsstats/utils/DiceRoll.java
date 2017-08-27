/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rarescrap.rsstats.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Объект броска дайсов
 * @author RareScrap
 */
public class DiceRoll implements Serializable {
    /** Количество граней на дайсе */
    private int dice;
    /** Список модификаторов, которые должны быть учтены */
    private List<RollModificator> modificators;
    
    /**
     * Конструктор, инициализирующий свои поля
     * @param dice Количество граней на дайсе
     */
    public DiceRoll(int dice) {
        this.dice = dice;
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
}
