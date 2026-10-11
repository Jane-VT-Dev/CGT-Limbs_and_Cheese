package com.June.cgt_mod.client;

import com.June.cgt_mod.block.entity.PlayerHeadPotBlockEntity;
import com.June.cgt_mod.block.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;

public class PlayerHeadPotRenderer implements BlockEntityRenderer<PlayerHeadPotBlockEntity> {
    public PlayerHeadPotRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(PlayerHeadPotBlockEntity pot, float partialTick, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight, int packedOverlay) {
        ItemStack plant = pot.getPlant();
        if (plant.isEmpty()) {
            return;
        }

        double plantHeight = pot.getBlockState().is(ModBlocks.PILLAGER_HEAD_POT.get())
                || pot.getBlockState().is(ModBlocks.VILLAGER_HEAD_POT.get())
                ? 1.025 : 0.8;
        renderPlant(plant, 45.0F, plantHeight, poseStack, buffer, packedLight);
        renderPlant(plant, -45.0F, plantHeight, poseStack, buffer, packedLight);
    }

    private void renderPlant(ItemStack plant, float rotation, double plantHeight, PoseStack poseStack,
                             MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.5, plantHeight, 0.5);
        poseStack.scale(0.85F, 0.85F, 0.85F);
        poseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));
        Minecraft.getInstance().getItemRenderer().renderStatic(plant, ItemTransforms.TransformType.FIXED,
                packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, 0);
        poseStack.popPose();
    }
}
