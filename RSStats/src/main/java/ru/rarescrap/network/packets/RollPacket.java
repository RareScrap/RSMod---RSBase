/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.rarescrap.network.packets;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
       

public class RollPacket implements IMessage {
    private String diceRollMessage;

    public RollPacket() {}

    public RollPacket(String message) {
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
    
    public static class MessageHandler implements IMessageHandler<RollPacket, IMessage> {
        // Do note that the default constructor is required, but implicitly defined in this case

        @Override
        public IMessage onMessage(RollPacket message, MessageContext ctx) {
            // This is the player the packet was sent to the server from
            EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
            // The value that was sent
            String amount = message.diceRollMessage;
            // No response packet
            
            serverPlayer.addChatComponentMessage(new ChatComponentText(amount));
            return null;
        }
    }

}