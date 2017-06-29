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
import org.lwjgl.opengl.GL11;
import rsstats.common.RSStats;
import net.minecraft.client.renderer.Tessellator;  //!!
import rsstats.common.container.MainMenuContainer;

/**
 *
 * @author rares
 */
public class MainMenuGUI extends InventoryEffectRenderer {
    public static final ResourceLocation background = 
			new ResourceLocation("rsstats","textures/gui/StatsAndInvTab1.png");
    
    /**
     * x size of the inventory window in pixels. Defined as  float, passed as int
     */
    private float xSizeFloat;
    /**
     * y size of the inventory window in pixels. Defined as  float, passed as int.
     */
    private float ySizeFloat;

    public MainMenuGUI(EntityPlayer player) {
        super(new MainMenuContainer());
        this.allowUserInput = true;
    }

    /*
    Этот метод нужн только для того, чтобы из ClienPrxy вызывать GUI. Потом этот конструктор следут удалить
    */
    public MainMenuGUI() {
        super(new MainMenuContainer());
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
    }
    
    @Override
    public void drawScreen(int par1, int par2, float par3)
    {
        super.drawScreen(par1, par2, par3);
        this.xSizeFloat = (float)par1;
        this.ySizeFloat = (float)par2;
    }
    
    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        
        this.guiLeft = (this.width - 353) / 2;
        this.guiTop = (this.height - 212) / 2;
        
        int x = this.guiLeft;
        int y = this.guiTop;
        
        //this.xSize = 179;
        //this.ySize = 230;
        
        //this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
        
        /*
         1.8
        public static void drawModalRectWithCustomSizedTexture(int x, int y,
        float u, float v, int width, int height, float textureWidth, float textureHeight)
        */
        // 1.7.10
        //func_146110_a(x, y, 0, 0, width, height, 512, 512);
        
        
        
       
        /*
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();    
        tessellator.addVertexWithUV(x        , y + height, 0, 0.0, 1.0);
        tessellator.addVertexWithUV(x + width, y + height, 0, 1.0, 1.0);
        tessellator.addVertexWithUV(x + width, y         , 0, 1.0, 0.0);
        tessellator.addVertexWithUV(x        , y         , 0, 0.0, 0.0);
        tessellator.draw();
        */
        
        
        
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();    
        tessellator.addVertexWithUV(x        , y + 512, 0, 0.0, 1.0);
        tessellator.addVertexWithUV(x + 512, y + 512, 0, 1.0, 1.0);
        tessellator.addVertexWithUV(x + 512, y         , 0, 1.0, 0.0);
        tessellator.addVertexWithUV(x        , y         , 0, 0.0, 0.0);
        tessellator.draw();
                
        
        
        //this.drawTexturedModalRect(0, 0, 200, 0, 16, 16);
        
        /*for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1)
        {
            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i1);
            if (slot.getHasStack() && slot.getSlotStackLimit()==1)
            {
            	this.drawTexturedModalRect(k+slot.xDisplayPosition, l+slot.yDisplayPosition, 200, 0, 16, 16);
            }
        }*/
        
        /*Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(k + 0, l + height, zLevel, 0,1);
        tessellator.addVertexWithUV(k + width, l + height, zLevel, 1, 1);
        tessellator.addVertexWithUV(k + width, l + 0, zLevel, 1,0);
        tessellator.addVertexWithUV(k + 0, l + 0, zLevel, 0, 0);
        tessellator.draw();*/
        
        /*for (int i1 = 0; i1 < this.inventorySlots.inventorySlots.size(); ++i1)
        {
            Slot slot = (Slot)this.inventorySlots.inventorySlots.get(i1);
            if (slot.getHasStack() && slot.getSlotStackLimit()==1)
            {
            	this.drawTexturedModalRect(k+slot.xDisplayPosition, l+slot.yDisplayPosition, 200, 0, 16, 16);
            }
        }*/
        
        //drawPlayerModel(k + 51, l + 75, 30, (float)(k + 51) - this.xSizeFloat, (float)(l + 75 - 50) - this.ySizeFloat, this.mc.thePlayer);
    
    }
}
