package com.June.cgt_mod.client;

import com.June.cgt_mod.block.custom.EffigyEmptyBlock;
import com.June.cgt_mod.block.entity.EffigyBlockEntity;
import com.June.cgt_mod.config.EffigyConfig;
import com.June.cgt_mod.item.ModItems;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.renderer.block.model.ItemTransforms;

public class EffigyBlockEntityRenderer implements BlockEntityRenderer<EffigyBlockEntity> {
	public EffigyBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
	}

	@Override
	public void render(EffigyBlockEntity effigy, float partialTick, PoseStack poseStack, MultiBufferSource buffer,
			int packedLight, int packedOverlay) {
		Direction facing = effigy.getBlockState().getValue(EffigyEmptyBlock.FACING);
		if (facing == Direction.UP || facing == Direction.DOWN) {
			facing = Direction.NORTH;
		}
		facing = facing.getOpposite();
		Direction right = facing.getClockWise();
		double[][] offsets = {{-0.5625, 1.1375}, {-0.0375, 1.75}, {0.50, 1.20}};

		for (int slot = 0; slot < offsets.length; slot++) {
			ItemStack stack = effigy.getItem(slot);
			if (stack.isEmpty()) {
				continue;
			}

			double side = offsets[slot][0];
			double forward = stack.is(ModItems.PILLAGER_HEAD.get()) || stack.is(ModItems.VILLAGER_HEAD.get())
					? -1.0 / 16.0 : 0.0;
			forward += 2.0 / 16.0;
			poseStack.pushPose();
			poseStack.translate(0.5 + right.getStepX() * side + facing.getStepX() * forward,
					offsets[slot][1], 0.5 + right.getStepZ() * side + facing.getStepZ() * forward);
			poseStack.mulPose(Vector3f.YP.rotationDegrees(facing.toYRot()));
			String itemId = ForgeRegistries.ITEMS.getKey(stack.getItem()).toString();
			poseStack.mulPose(Vector3f.YP.rotationDegrees(EffigyConfig.getHeadRotation(itemId)));
			poseStack.scale(0.8F, 0.8F, 0.8F);
			Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.FIXED,
					packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, 0);
			poseStack.popPose();
		}
	}
}