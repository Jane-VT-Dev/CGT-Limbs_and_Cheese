package com.June.cgt_mod.client;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.block.entity.ModBlockEntities;
import com.June.cgt_mod.fluid.ModFluids;
import com.June.cgt_mod.item.ModItems;
import com.June.cgt_mod.menu.ModMenus;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Cgt_mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CgtModClientEvents {
    @SubscribeEvent
    public static void onRegisterItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> tintIndex == 1 ? 0xFF8A1010 : 0xFFFFFFFF,
                ModItems.BLOOD_BOTTLE.get());
        event.register((stack, tintIndex) -> 0xFF9B1C1C, ModItems.BLOOD_BLOB.get());
    }

    @SubscribeEvent
    public static void onRegisterBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> 0xFF8A1010, ModFluids.BLOOD_CAULDRON.get());
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenus.EFFIGY_MENU.get(), EffigyScreen::new);
            MenuScreens.register(ModMenus.JAR_MENU.get(), JarScreen::new);
           ItemBlockRenderTypes.setRenderLayer(ModBlocks.JAR.get(), RenderType.translucent());
           ItemBlockRenderTypes.setRenderLayer(ModFluids.BLOOD_BLOCK.get(), RenderType.translucent());
        });
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.EFFIGY_EMPTY.get(), EffigyBlockEntityRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.PLAYER_HEAD_POT.get(), PlayerHeadPotRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.JAR.get(), JarBlockEntityRenderer::new);
    }
}
