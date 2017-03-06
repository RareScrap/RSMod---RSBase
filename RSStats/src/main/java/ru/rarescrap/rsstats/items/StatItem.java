package ru.rarescrap.rsstats.items;

import net.minecraft.client.gui.GuiScreen; // Нужен для зажима Shift при показе описания
import net.minecraft.client.renderer.texture.IIconRegister; // Регистрация иконок в игре
import net.minecraft.creativetab.CreativeTabs; // Вкладки креативного режима. Нужно для расположения в них элементов
import net.minecraft.entity.player.EntityPlayer; // Сущность игрока. Передается в некоторые методы
import net.minecraft.item.Item; // Родительский класс
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector; // Класс для получения строкиз файла локализации

import java.util.List;
import ru.rarescrap.rsstats.utils.DescriptionCutter;

public class StatItem extends Item {
    private int NUMBER_OF_SUBTYPES = 10; // Количество видов этого предмета (например, количество вида одной статы - от 1 до 10)
    
    public IIcon[] icons = new IIcon[NUMBER_OF_SUBTYPES]; // хранилище иконок для всего семейства предмета
    public String[] dices = new String[NUMBER_OF_SUBTYPES]; // Бросок, соответствующей каждому предмету из семейству
    private String descriprion; // Описание предмета, получаемое из файла локализации

    public StatItem() {
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
        this.setUnlocalizedName("StrenghtStatItem");
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setHasSubtypes(true);
    }

    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
        DescriptionCutter descriptionCutter = new DescriptionCutter();
        list.add(this.dices[3]);
        list.add("");
        if (GuiScreen.isShiftKeyDown()) {
            String[] descrStrings = descriptionCutter.cut(3, StatCollector.translateToLocal((String)"item.StatItem.descriprion"));
            for (int i = 0; i < descrStrings.length; ++i) {
                list.add(descrStrings[i]);
            }
        } else {
            String[] moreInfoStrings = descriptionCutter.cut(3, StatCollector.translateToLocal((String)"item.StatItem.moreInformation"));
            for (int i = 0; i < moreInfoStrings.length; ++i) {
                list.add(moreInfoStrings[i]);
            }
        }
    }

    /**
     * 
     * @param reg 
     */
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = reg.registerIcon("rsstats:strenght" + (i + 1));
        }
    }

    public IIcon getIconFromDamage(int meta) {
        if (meta > this.icons.length) {
            meta = 0;
        }
        return this.icons[meta];
    }

    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.icons.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public String func_77667_c(ItemStack stack) {
        return this.getUnlocalizedName() + "_" + (Integer.valueOf(stack.getItemDamage()) + 1);
    }
}