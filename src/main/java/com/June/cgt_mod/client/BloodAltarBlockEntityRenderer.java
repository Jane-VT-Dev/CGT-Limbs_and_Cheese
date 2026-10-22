package com.June.cgt_mod.client;

import com.June.cgt_mod.block.entity.BloodAltarBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;

public class BloodAltarBlockEntityRenderer implements BlockEntityRenderer<BloodAltarBlockEntity> {
    public BloodAltarBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(BloodAltarBlockEntity altar, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        renderItem(altar.getItem(0), 0.25F, poseStack, buffer, packedLight);
        renderItem(altar.getItem(1), 0.75F, poseStack, buffer, packedLight);
    }

    private void renderItem(ItemStack item, float x, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (item.isEmpty()) {
            return;
        }
        poseStack.pushPose();
        poseStack.translate(x, 0.92, 0.5);
        poseStack.scale(0.4F, 0.4F, 0.4F);
        Minecraft.getInstance().getItemRenderer().renderStatic(item, ItemTransforms.TransformType.FIXED,
                packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, 0);
        poseStack.popPose();
    }
}
