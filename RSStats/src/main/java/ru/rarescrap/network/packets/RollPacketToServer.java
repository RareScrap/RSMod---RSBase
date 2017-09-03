package ru.rarescrap.network.packets;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import net.minecraft.util.StatCollector;
import org.apache.commons.io.FileUtils;
import ru.rarescrap.rsstats.items.StatItem;
import ru.rarescrap.rsstats.utils.DiceRoll;
import ru.rarescrap.rsstats.utils.RollModificator;
       
/**
 * Этот пакет отсылается сервера
 * @author RareScrap
 */
public class RollPacketToServer implements IMessage {
    public static int BUFFER_INT_SIZE = 1;
    private DiceRoll diceRollMessage;
    
    /**
     * Необходимый конструктор по умолчанию. Он необходим для того, чтобы на
     * стороне-обработчике создать объект и распаковать в него буффер. 
     */
    public RollPacketToServer() {}

    public RollPacketToServer(DiceRoll message) {
        System.out.print("const");
        this.diceRollMessage = message;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        // Создаем обьект броска и передаем в него дайс для броска
        String playerName = ByteBufUtils.readUTF8String(buf);
        String rollName = ByteBufUtils.readUTF8String(buf);
        int dice = ByteBufUtils.readVarInt(buf, BUFFER_INT_SIZE);
        
        ArrayList<RollModificator> modificators = new ArrayList<RollModificator>();
        int size = ByteBufUtils.readVarInt(buf, BUFFER_INT_SIZE);
        if (size > 0) {
            int value = ByteBufUtils.readVarInt(buf, BUFFER_INT_SIZE);
            String description = ByteBufUtils.readUTF8String(buf);
            modificators.add(new RollModificator(value, description));
        }
        
        String template = ByteBufUtils.readUTF8String(buf);
        
        /*if (size > 0)
            this.diceRollMessage = new DiceRoll(playerName, rollName, dice, template);
        else
            this.diceRollMessage = new DiceRoll(playerName, rollName, dice, modificators, template);
        */
        this.diceRollMessage = new DiceRoll(playerName, rollName, dice, template);
    }

    /**
     * ВНИМАНИЕ: в {@link #fromBytes(io.netty.buffer.ByteBuf)} нужно читать данные в
     * порядке их записи в {@link #toBytes(io.netty.buffer.ByteBuf)}!
     * @param buf 
     */
    @Override
    public void toBytes(ByteBuf buf) {
        // TODO: ОПАСНО! Может оказаться, что одного байта не хватит (последний аргумент)
        ByteBufUtils.writeUTF8String(buf, diceRollMessage.getPlayerName());
        ByteBufUtils.writeUTF8String(buf, diceRollMessage.getRollName());
        ByteBufUtils.writeVarInt(buf, diceRollMessage.getDice(), BUFFER_INT_SIZE);
        
        if (diceRollMessage.getModificators() != null && !diceRollMessage.getModificators().isEmpty()) {
            ByteBufUtils.writeVarInt(buf, diceRollMessage.getModificators().size(), BUFFER_INT_SIZE);
            for (int i = 0; i < diceRollMessage.getModificators().size(); i++) {
                RollModificator modificator = diceRollMessage.getModificators().get(i);
                ByteBufUtils.writeVarInt(buf, modificator.value, BUFFER_INT_SIZE);
                ByteBufUtils.writeUTF8String(buf, modificator.description);
            }
        } else {
            // Записываем размер пустого списка модификаторов
            ByteBufUtils.writeVarInt(buf, 0, BUFFER_INT_SIZE);
        }
        
        ByteBufUtils.writeUTF8String(buf, diceRollMessage.getTemplate());
    }
    
    /**
     * Этот внутренний класс обрабатывает пришедший пакет НА СТОРОНЕ СЕРВЕРА
     */
    public static class MessageHandler implements IMessageHandler<RollPacketToServer, IMessage> {
        // Do note that the default constructor is required, but implicitly defined in this case
        public MessageHandler() {}

        @Override
        public IMessage onMessage(RollPacketToServer message, MessageContext ctx) {
            // This is the player the packet was sent to the server from
            EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
            
            /*message.diceRollMessage = new DiceRoll(12);
            message.diceRollMessage.setStatName("PIDOR");*/
            
            System.out.print("onMessage");
            if (message.diceRollMessage == null)
                throw new NullPointerException("diceRollMessage is null");
            
            String result = message.diceRollMessage.roll();
            try {
                // ГОВНО
                FileUtils.writeStringToFile(new File("D:\\Users\\rares\\Downloads\\checkstyle1.txt"), result);
            } catch (IOException ex) {
                Logger.getLogger(StatItem.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // и вывести его в чат
            serverPlayer.addChatComponentMessage(new ChatComponentText(result));
            //serverPlayer.addChatComponentMessage(new ChatComponentText(message.diceRollMessage.dice + " " + message.diceRollMessage.statName));
            
            /*try {
                //serverPlayer.addChatComponentMessage(new ChatComponentText(Minecraft.getMinecraft().gameSettings.language));
            } catch (Throwable e) {
                serverPlayer.addChatComponentMessage(new ChatComponentText("SERVER SIDE"));
            }*/
            serverPlayer.addChatComponentMessage(new ChatComponentText(FMLCommonHandler.instance().getEffectiveSide().name()));
            
            
            /*
            String maxS = new String();
            String stat = new String();
            int i = 0;
            for (; amount.charAt(i) != '_'; i++) {
                maxS += amount.charAt(i);
            }
            i++;
            for (; i < amount.length(); i++) {
                stat += amount.charAt(i);
            }
            
            int min = 1;
            int max = Integer.parseInt(maxS);
            
            String result = StatCollector.translateToLocalFormatted("item.StatItem.rollChatMessage", stat) + ": ";
            int resultInt = 0;
            int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
            resultInt += randomNum;
            while(randomNum == max) {
                result += String.valueOf(randomNum)+ "+";
                resultInt += randomNum;
                randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
                
            }
            result += String.valueOf(randomNum)+ "= " + resultInt+randomNum;
            resultInt += randomNum;
            
            
            // Заменить на нормальное вычисление
            serverPlayer.addChatComponentMessage(new ChatComponentText(result));
            */
            return null;
        }
    }

}