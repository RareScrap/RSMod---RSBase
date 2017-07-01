package ru.rarescrap.network.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;

import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.util.StatCollector;
       
/**
 * Этот пакет отсылается сервера
 * @author RareScrap
 */
public class RollPacketToServer implements IMessage {
    private String diceRollMessage;
    
    // На форумах и в доках пишут, что конструктор по умолчанию необходим
    public RollPacketToServer() {}

    public RollPacketToServer(String message) {
        this.diceRollMessage = message;
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        this.diceRollMessage = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.diceRollMessage);
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
            // The value that was sent
            String amount = message.diceRollMessage;
            // No response packet
            
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
            
            return null;
        }
    }

}