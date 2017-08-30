package ru.rarescrap.network.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import net.minecraft.util.StatCollector;
import ru.rarescrap.rsstats.utils.DiceRoll;
import ru.rarescrap.rsstats.utils.RollModificator;
       
/**
 * Этот пакет отсылается сервера
 * @author RareScrap
 */
public class RollPacketToServer implements IMessage {
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
        byte[] bytes = buf.array();
        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
        ObjectInputStream o = null;
        try {
            o = new ObjectInputStream(b);
            //this.diceRollMessage = (DiceRoll) o.readObject();
            this.diceRollMessage = new DiceRoll(12);
            this.diceRollMessage.setStatName("PIDOR");
        } catch (IOException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        } /*catch (ClassNotFoundException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        if (o != null)
            try {
                o.close();
        } catch (IOException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        // ГОВНО
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("D:\\Users\\rares\\Downloads\\checkstyle1.txt");
            out.write(bytes);
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (out != null)
            try {
                out.close();
        } catch (IOException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ObjectOutputStream o = null;
        try {
            o = new ObjectOutputStream(b);
            o.writeObject(diceRollMessage);
        } catch (IOException ex) {
             Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (o != null)
            try {
                o.close();
        } catch (IOException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        //ByteBufUtils.writeUTF8String(buf, b.);
        buf.writeBytes(b.toByteArray());
               
        
        
        // ГОВНО
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("D:\\Users\\rares\\Downloads\\checkstyle.txt");
            out.write(buf.array());
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (out != null)
            try {
                out.close();
        } catch (IOException ex) {
            Logger.getLogger(RollPacketToServer.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            
            message.diceRollMessage = new DiceRoll(12);
            message.diceRollMessage.setStatName("PIDOR");
            
            System.out.print("onMessage");
            if (message.diceRollMessage == null)
                throw new NullPointerException("diceRollMessage is null");
            
            String result = message.diceRollMessage.roll();
            // и вывести его в чат
            serverPlayer.addChatComponentMessage(new ChatComponentText(result));
            
            
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