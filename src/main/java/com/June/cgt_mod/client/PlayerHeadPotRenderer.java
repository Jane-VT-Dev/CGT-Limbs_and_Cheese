package com.June.cgt_mod.client;

import com.June.cgt_mod.block.entity.PlayerHeadPotBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.BambooBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;

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
        if (!(plant.getItem() instanceof BlockItem blockItem)) {
            return;
        }

        BlockState plantState = blockItem.getBlock().defaultBlockState();
        if (plantState.getBlock() instanceof BambooBlock) {
            plantState = plantState.setValue(BambooBlock.LEAVES, BambooLeaves.LARGE)
                    .setValue(BambooBlock.AGE, 1)
                    .setValue(BambooBlock.STAGE, 1);
        }

        poseStack.pushPose();
        poseStack.translate(0.175, 0.5, 0.175);
        poseStack.scale(0.65F, 0.65F, 0.65F);
        Minecraft.getInstance().getBlockRenderer().renderSingleBlock(plantState, poseStack, buffer,
                packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
