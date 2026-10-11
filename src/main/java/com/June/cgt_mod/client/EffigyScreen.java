package com.June.cgt_mod.client;

import com.June.cgt_mod.menu.EffigyMenu;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import com.mojang.blaze3d.vertex.PoseStack;

public class EffigyScreen extends AbstractContainerScreen<EffigyMenu> {
	public EffigyScreen(EffigyMenu menu, Inventory inventory, Component title) {
		super(menu, inventory, title);
		imageWidth = 176;
		imageHeight = 166;
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
		fill(poseStack, leftPos, topPos, leftPos + imageWidth, topPos + imageHeight, 0xFF292522);
		fill(poseStack, leftPos + 5, topPos + 5, leftPos + imageWidth - 5, topPos + 77, 0xFF51483D);
		fill(poseStack, leftPos + 5, topPos + 80, leftPos + imageWidth - 5, topPos + imageHeight - 5, 0xFF3B3834);
		for (int[] slot : new int[][]{{25, 47}, {80, 18}, {135, 47}}) {
			fill(poseStack, leftPos + slot[0] - 1, topPos + slot[1] - 1,
					leftPos + slot[0] + 17, topPos + slot[1] + 17, 0xFF181715);
			fill(poseStack, leftPos + slot[0], topPos + slot[1],
					leftPos + slot[0] + 16, topPos + slot[1] + 16, 0xFF756A5C);
		}
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
		renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, partialTick);
		renderTooltip(poseStack, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		font.draw(poseStack, title, 8, 6, 0xFFE6D9C4);
		font.draw(poseStack, Component.translatable("container.inventory"), 8, 72, 0xFFE6D9C4);
	}
}