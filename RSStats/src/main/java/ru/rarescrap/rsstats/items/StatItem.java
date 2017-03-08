package ru.rarescrap.rsstats.items;

import net.minecraft.client.gui.GuiScreen; // Нужен для зажима Shift при показе описания
import net.minecraft.client.renderer.texture.IIconRegister; // Регистрация иконок в игре
import net.minecraft.creativetab.CreativeTabs; // Вкладки креативного режима. Нужно для расположения в них элементов

// Эти классы передаются в методы
import net.minecraft.entity.player.EntityPlayer; // Сущность игрока.
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item; // Родительский класс

import net.minecraft.util.IIcon; // Хранилище иконок
import net.minecraft.util.StatCollector; // Класс для получения строкиз файла локализации
import ru.rarescrap.rsstats.utils.DescriptionCutter; // Для обрезки длинных предложений
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class StatItem extends Item {
    private int NUMBER_OF_SUBTYPES = 10; // Количество видов этого предмета (например, количество вида одной статы - от 1 до 10)
    
    public IIcon[] icons = new IIcon[NUMBER_OF_SUBTYPES]; // хранилище иконок для всего семейства предмета
    public String[] dices = new String[NUMBER_OF_SUBTYPES]; // Бросок, соответствующей каждому предмету из семейству
    private String descriprion; // Описание предмета, получаемое из файла локализации
    private String registerIconPrefix; // "rarescrap:StrenghtIcon_" например
    private String localePrefix; // "item.StrenghtStatItem" например
    
    public StatItem(String diceInput[], String unlocalizedName, String registerIconPrefix, String localePrefix) {
        // TODO: Дайсы должны задаваться через серверный конфиг
        for (int i = 0; i < NUMBER_OF_SUBTYPES; i++) { // Копирование входящих дайсов в поле класса
            this.dices[i] = diceInput[i];
        }
        
        this.localePrefix = localePrefix;
        this.registerIconPrefix = registerIconPrefix;
        
        this.setUnlocalizedName(unlocalizedName);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setHasSubtypes(true);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
        // Строка броска (пример: "Бросок: d6+1")
        switch(itemstack.getItemDamage()) {
        case 0:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 1) );
            break;
        case 1:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 2) );
            break;
        case 2:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 3) );
            break;
        case 3:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 4) );
            break;
        case 4:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 5) );
            break;
        case 5:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 6) );
            break;
        case 6:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 7) );
            break;
        case 7:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 8) );
            break;
        case 8:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 9) );
            break;
        case 9:
            list.add(StatCollector.translateToLocal(localePrefix + ".roll") + ": " +
                    StatCollector.translateToLocalFormatted(localePrefix + ".level", 10) );
            break;
        }
        list.add(""); // Пустая строка-разделитель
        
        // Дополнительная информация по кнопке Shift
        if (GuiScreen.isShiftKeyDown()) {
            /*String[] descrStrings = descriptionCutter.cut(3, StatCollector.translateToLocal((String)"item.StatItem.descriprion"));
            for (int i = 0; i < descrStrings.length; ++i) {
                list.add(descrStrings[i]);
            }*/
            list.add( StatCollector.translateToLocal(localePrefix + ".descriprion") );
        } else {
            /*String[] moreInfoStrings = descriptionCutter.cut(3, StatCollector.translateToLocal((String)"item.StatItem.moreInformation"));
            for (int i = 0; i < moreInfoStrings.length; ++i) {
                list.add(moreInfoStrings[i]);
            }*/
            list.add( StatCollector.translateToLocal(localePrefix + ".moreInfo") );
        }
    }

    @Override
    public void registerIcons(IIconRegister reg) {
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = reg.registerIcon(registerIconPrefix + (i + 1));
        }
    }

    @Override
    public IIcon getIconFromDamage(int meta) {
        if (meta > this.icons.length) {
            meta = 0;
        }
        return this.icons[meta];
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.icons.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName();
        //return this.getUnlocalizedName() + "_" + (Integer.valueOf( stack.getItemDamage() ) + 1); - пригодится когда каждому подтипу нужно дать индивидуальное имя
    }
    
    /*@Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        entityplayer.addChatComponentMessage(new ChatComponentText("ROLL!"));
        
       return itemstack;
        //return super.onItemRightClick(itemstack, world, entityplayer); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    
    /*public float getStrVsBlock(ItemStack stack, Block block, int meta) 
    {
        entityplayer.addChatMessage("Open inventory");
        
        return (float) 1.0;
    }*/
}