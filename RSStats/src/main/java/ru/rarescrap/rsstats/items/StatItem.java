package ru.rarescrap.rsstats.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
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
import ru.rarescrap.network.packets.RollPacketToServer;
import static ru.rarescrap.rsstats.RSStats.INSTANCE;
import ru.rarescrap.rsstats.utils.DiceRoll;

public class StatItem extends Item {
    private int NUMBER_OF_SUBTYPES = 5; // Количество видов этого предмета (например, количество вида одной статы - от 1 до 10)
    
    public IIcon[] icons = new IIcon[NUMBER_OF_SUBTYPES]; // хранилище иконок для всего семейства предмета
    public String[] dices = new String[NUMBER_OF_SUBTYPES]; // Бросок, соответствующей каждому предмету из семейству
    private String descriprion; // Описание предмета, получаемое из файла локализации
    /** Префикс, используемый игрой для нахождеия текстур мода */
    private String registerIconPrefix; // "rarescrap:StrenghtIcon_" например
    /** Префикс, используемый игрой для нахождеия файлов локализации мода */
    private String localePrefix; // "item.StrenghtStatItem" например
    
    private DiceRoll[] basicRolls;
    
    /** Префикс, одинаковый для всех статов */
    private String generalPrefix = "item.StatItem";
    /**
     * Конструктор, инициализирующий свои поля
     * @param diceInput Информация о броске на каждый уровень ({@link #NUMBER_OF_SUBTYPES}) статы.
     * @param unlocalizedName Нелокализированое имя мода (TODO: ХЗ для чего нужно)
     * @param registerIconPrefix Префикс, который игра будет использовать игрой для нахождения текстур для данного предмета
     * @param localePrefix Префикс, который игра будет использовать игрой для нахождения файлов локализации для данного предмета
     */
    public StatItem(DiceRoll[] basicRolls, String unlocalizedName, String registerIconPrefix, String localePrefix) {
        // TODO: Дайсы должны задаваться через серверный конфиг
        this.basicRolls = basicRolls;
        
        this.registerIconPrefix = registerIconPrefix;
        this.localePrefix = localePrefix;
        
        this.setUnlocalizedName(unlocalizedName);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabMaterials);
        this.setHasSubtypes(true);
    }

    @Override
    public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4) {
        // Уровень статы
        int statLevel = itemstack.getItemDamage(); // Нумерация с нуля
        
        // Строка "Уровень X"
        list.add(StatCollector.translateToLocalFormatted( generalPrefix + ".level", statLevel+1) );
        
        // Строка броска (пример: "Бросок: d6+1")
        list.add(StatCollector.translateToLocal(generalPrefix + ".roll") + ": d" + basicRolls[statLevel].dice);
        
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
            list.add( StatCollector.translateToLocal(generalPrefix + ".moreInfo") );
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
    
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack) {
         //par1ItemStack.setTagInfo("ench", new NBTTagList());
         if (par1ItemStack.getItemDamage() == NUMBER_OF_SUBTYPES-1)
            return true;
         else
             return false;
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
    
    @SideOnly(Side.SERVER)
    public String roll() {
        return "Бросок Силы: d6+1 = 6+6+4+1=17";
    }
       
    
    // Работает когда юзаешь предмет на панели
    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
        if (!world.isRemote) { // TODO: На какой стороне вычисляется бросок?
            //String num = String.valueOf( basicRolls[ Integer.parseInt(itemstack.getIconIndex().toString()) ].dice );
            String statName = StatCollector.translateToLocalFormatted( localePrefix + ".name");
            String str = itemstack.getIconIndex().getIconName();
            str = str.replaceAll("[^\\d.]", "");
            INSTANCE.sendToServer(new RollPacketToServer(str+"_"+statName)); // "123" // itemstack.getIconIndex(
            entityplayer.addChatComponentMessage(new ChatComponentText(str));
        }
        //entityplayer.addChatComponentMessage(new ChatComponentText(this.roll()));
        
        return itemstack;
        //return super.onItemRightClick(itemstack, world, entityplayer); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /*public float getStrVsBlock(ItemStack stack, Block block, int meta) 
    {
        entityplayer.addChatMessage("Open inventory");
        
        return (float) 1.0;
    }*/
}