/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rarescrap.rsstats.items;

import java.util.ArrayList;
import ru.rarescrap.rsstats.utils.DiceRoll;

/**
 *
 * @author rares
 */
public class SkillItem extends StatItem {
    public String parentStatUnlocalizedName;

    public SkillItem(ArrayList<DiceRoll> basicRolls, String unlocalizedName, String registerIconPrefix, String localePrefix) {
        super(basicRolls, unlocalizedName, registerIconPrefix, localePrefix);
    }
    
}
