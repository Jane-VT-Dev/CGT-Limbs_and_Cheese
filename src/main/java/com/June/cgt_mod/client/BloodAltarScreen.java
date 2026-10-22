package com.June.cgt_mod.client;

import com.June.cgt_mod.config.BloodAltarConfig;
import com.June.cgt_mod.menu.BloodAltarMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.List;

public class BloodAltarScreen extends AbstractContainerScreen<BloodAltarMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation("cgt_mod_cheese_limbs", "textures/gui/blood_altar.png");
    private static final int TEXTURE_WIDTH = 256;
    private static final int TEXTURE_HEIGHT = 256;
    private static final int TANK_X = 178;
    private static final int TANK_Y = 25;
    private static final int TANK_WIDTH = 14;
    private static final int TANK_HEIGHT = 74;
    private static final int PROGRESS_X = 109;
    private static final int PROGRESS_Y = 37;
    private static final int PROGRESS_WIDTH = 54;

    public BloodAltarScreen(BloodAltarMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        imageWidth = 220;
        imageHeight = 202;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        blit(poseStack, leftPos, topPos, 0, 0, imageWidth, imageHeight, TEXTURE_WIDTH, TEXTURE_HEIGHT);

        int tankLeft = leftPos + TANK_X;
        int tankTop = topPos + TANK_Y;
        int fluidHeight = Math.min(TANK_HEIGHT,
                (int) ((long) menu.getFluidAmount() * TANK_HEIGHT / BloodAltarConfig.TANK_CAPACITY.get()));
        if (fluidHeight > 0) {
            fill(poseStack, tankLeft + 1, tankTop + TANK_HEIGHT - fluidHeight,
                    tankLeft + TANK_WIDTH - 1, tankTop + TANK_HEIGHT - 1, 0xFF981B24);
        }

        int progress = menu.getCraftProgress() * PROGRESS_WIDTH / 1000;
        fill(poseStack, leftPos + PROGRESS_X - 1, topPos + PROGRESS_Y - 1,
                leftPos + PROGRESS_X + PROGRESS_WIDTH + 1, topPos + PROGRESS_Y + 7, 0xFF51463D);
        fill(poseStack, leftPos + PROGRESS_X, topPos + PROGRESS_Y,
                leftPos + PROGRESS_X + PROGRESS_WIDTH, topPos + PROGRESS_Y + 6, 0xFF302F32);
        if (progress > 0) {
            fill(poseStack, leftPos + PROGRESS_X, topPos + PROGRESS_Y,
                    leftPos + PROGRESS_X + progress, topPos + PROGRESS_Y + 6, 0xFF981B24);
        }
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);
        renderTooltip(poseStack, mouseX, mouseY);
        if (mouseX >= leftPos + TANK_X - 2 && mouseX < leftPos + TANK_X + TANK_WIDTH + 2
                && mouseY >= topPos + TANK_Y - 2 && mouseY < topPos + TANK_Y + TANK_HEIGHT + 2) {
            renderTooltip(poseStack, List.of(
                    Component.literal("Blood").getVisualOrderText(),
                    Component.literal(menu.getFluidAmount() + " / "
                            + BloodAltarConfig.TANK_CAPACITY.get() + " mB").getVisualOrderText()), mouseX, mouseY);
        }
    }

    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        font.draw(poseStack, title, 8, 7, 0x3A211C);
        font.draw(poseStack, Component.translatable("container.inventory"), 8, 108, 0x3A211C);
        font.draw(poseStack, "IN", 62, 22, 0x3A211C);
        font.draw(poseStack, "OUT", 59, 58, 0x3A211C);
    }
}
