package com.jomfer.minecrobes.screen;

import com.jomfer.minecrobes.MinecrobesMod;
import com.jomfer.minecrobes.menu.LabBenchMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class LabBenchScreen extends AbstractContainerScreen<LabBenchMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MinecrobesMod.MODID, "textures/gui/lab_bench.png");

    private static final int TEXTURE_WIDTH = 204;
    private static final int TEXTURE_HEIGHT = 166;

    public LabBenchScreen(LabBenchMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, TEXTURE);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        graphics.blit(TEXTURE, x, y, 0, 0, this.imageWidth, this.imageHeight, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        // Draw arrow between inputs and output if recipe is valid
        if (this.menu.hasRecipe()) {
            graphics.blit(TEXTURE, x + 89, y + 34, 176, 0, 28, 18, TEXTURE_WIDTH, TEXTURE_HEIGHT);
        }
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
