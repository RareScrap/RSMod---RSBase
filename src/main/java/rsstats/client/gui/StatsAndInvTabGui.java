/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsstats.client.gui;

import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import rsstats.common.container.StatsAndInvContainer;

/**
 *
 * @author rares
 */
public class StatsAndInvTabGui extends InventoryEffectRenderer {
    public static final ResourceLocation background = 
			new ResourceLocation("RSStats","textures/gui/StatsAndInvTab.png");
    
    /**
     * x size of the inventory window in pixels. Defined as  float, passed as int
     */
    private float xSizeFloat;
    /**
     * y size of the inventory window in pixels. Defined as  float, passed as int.
     */
    private float ySizeFloat;

    public StatsAndInvTabGui(EntityPlayer player) {
        super(new StatsAndInvContainer());
        this.allowUserInput = true;
    }

    /*
    Этот метод нужн только для того, чтобы из ClienPrxy вызывать GUI. Потом этот конструктор следут удалить
    */
    public StatsAndInvTabGui() {
        super(new StatsAndInvContainer());
        this.allowUserInput = true;
    }
    
    /**
     * Called from the main game loop to update the screen.
     */
    @Override 
    public void updateScreen() {
    	try {
            //((ContainerPlayerExpanded)inventorySlots).baubles.blockEvents=false;
        } catch (Exception e) {	}
        //this.updateActivePotionEffects();
    }
    
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        //GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int k = this.guiLeft;
        int l = this.guiTop;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        
        for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1)
        {
            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i1);
            if (slot.getHasStack() && slot.getSlotStackLimit()==1)
            {
            	this.drawTexturedModalRect(k+slot.xDisplayPosition, l+slot.yDisplayPosition, 200, 0, 16, 16);
            }
        }
        
        //drawPlayerModel(k + 51, l + 75, 30, (float)(k + 51) - this.xSizeFloat, (float)(l + 75 - 50) - this.ySizeFloat, this.mc.thePlayer);
    
    }
    
}
