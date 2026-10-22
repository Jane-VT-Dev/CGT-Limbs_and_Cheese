package com.June.cgt_mod.fluid;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.client.extensions.common.IClientFluidTypeExtensions;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ModFluids {
    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, Cgt_mod.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, Cgt_mod.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Cgt_mod.MOD_ID);

    public static final RegistryObject<FluidType> BLOOD_TYPE = FLUID_TYPES.register("blood",
            () -> new FluidType(FluidType.Properties.create()
                    .descriptionId("fluid_type." + Cgt_mod.MOD_ID + ".blood")
                    .density(1000)
                    .viscosity(1000)
                    .canConvertToSource(false)
                    .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL)
                    .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY)) {
                @Override
                public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
                    consumer.accept(new IClientFluidTypeExtensions() {
                        private static final ResourceLocation STILL =
                                new ResourceLocation("minecraft", "block/water_still");
                        private static final ResourceLocation FLOWING =
                                new ResourceLocation("minecraft", "block/water_flow");

                        @Override
                        public ResourceLocation getStillTexture() {
                            return STILL;
                        }

                        @Override
                        public ResourceLocation getFlowingTexture() {
                            return FLOWING;
                        }

                        @Override
                        public int getTintColor() {
                            return 0xFF8A1010;
                        }
                    });
                }
            });

    public static final RegistryObject<FlowingFluid> BLOOD = FLUIDS.register("blood",
            () -> new ForgeFlowingFluid.Source(bloodProperties()));
    public static final RegistryObject<FlowingFluid> FLOWING_BLOOD = FLUIDS.register("flowing_blood",
            () -> new ForgeFlowingFluid.Flowing(bloodProperties()));

    public static final RegistryObject<LiquidBlock> BLOOD_BLOCK = BLOCKS.register("blood",
            () -> new LiquidBlock(BLOOD, BlockBehaviour.Properties.of(Material.WATER)
                    .noLootTable()
                    .strength(100.0F)));

    public static final RegistryObject<LayeredCauldronBlock> BLOOD_CAULDRON = BLOCKS.register("blood_cauldron",
            () -> new BloodCauldronBlock(BlockBehaviour.Properties.copy(Blocks.WATER_CAULDRON)
                    .noLootTable()));

    private static ForgeFlowingFluid.Properties bloodProperties() {
        return new ForgeFlowingFluid.Properties(BLOOD_TYPE, BLOOD, FLOWING_BLOOD)
                .bucket(() -> ModItems.BLOOD_BUCKET.get())
                .block(BLOOD_BLOCK);
    }

    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
        FLUIDS.register(eventBus);
        BLOCKS.register(eventBus);
    }
}
