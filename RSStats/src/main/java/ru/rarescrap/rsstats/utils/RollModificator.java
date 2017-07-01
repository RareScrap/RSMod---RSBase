/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rarescrap.rsstats.utils;

/**
 *
 * @author rares
 */
public class RollModificator {
    public String name;
    public int value;
    public String description;
    
    public RollModificator(int value, String name, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }
}
