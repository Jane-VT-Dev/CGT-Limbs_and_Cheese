package com.June.cgt_mod.client;

import com.June.cgt_mod.block.entity.JarBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.ItemStack;

public class JarBlockEntityRenderer implements BlockEntityRenderer<JarBlockEntity> {
    public JarBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(JarBlockEntity jar, float partialTick, PoseStack poseStack, MultiBufferSource buffer,
                       int packedLight, int packedOverlay) {
        ItemStack stored = jar.getItem(0);
        if (stored.isEmpty()) {
            return;
        }

        poseStack.pushPose();
        poseStack.translate(0.5, 0.24, 0.5);
        Vec3 cameraPosition = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        double cameraX = cameraPosition.x - (jar.getBlockPos().getX() + 0.5);
        double cameraZ = cameraPosition.z - (jar.getBlockPos().getZ() + 0.5);
        float facingAngle = (float) Math.toDegrees(Math.atan2(cameraX, cameraZ));
        poseStack.mulPose(Vector3f.YP.rotationDegrees(facingAngle));
        poseStack.scale(0.35F, 0.35F, 0.35F);
        Minecraft.getInstance().getItemRenderer().renderStatic(stored, ItemTransforms.TransformType.FIXED,
                packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, 0);
        poseStack.popPose();
    }
}
