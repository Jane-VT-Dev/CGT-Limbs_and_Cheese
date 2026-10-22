package com.June.cgt_mod;

import com.June.cgt_mod.config.EffigyConfig;
import com.June.cgt_mod.config.ConsumableConfig;
import com.June.cgt_mod.config.JarConfig;
import com.June.cgt_mod.config.HeadPlantPotConfig;
import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.block.entity.ModBlockEntities;
import com.June.cgt_mod.fluid.ModFluids;
import com.June.cgt_mod.item.ModItems;
import com.June.cgt_mod.menu.ModMenus;
import com.June.cgt_mod.recipe.BloodAltarRecipe;
import com.mojang.logging.LogUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Cgt_mod.MOD_ID)
public class Cgt_mod {

    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "cgt_mod_cheese_limbs";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();




    public Cgt_mod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, EffigyConfig.SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, JarConfig.SPEC, "cgt_mod_cheese_limbs-jar.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, HeadPlantPotConfig.SPEC,
                "cgt_mod_cheese_limbs-head-plant-pots.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConsumableConfig.SPEC, "cgt_mod_cheese_limbs-consumables.toml");

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ModFluids.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);
        BloodAltarRecipe.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }


    private void commonSetup(final FMLCommonSetupEvent event) {

    }



    // use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    @SubscribeEvent
    public void onSheepInteract(PlayerInteractEvent.EntityInteract event) {
        if (!(event.getTarget() instanceof Sheep sheep) || sheep.isBaby()) {
            return;
        }

        ItemStack heldItem = event.getItemStack();
        ItemStack milk;
        if (heldItem.is(Items.GLASS_BOTTLE)) {
            milk = new ItemStack(ModItems.SHEEP_MILK.get());
        } else if (heldItem.is(Items.BOWL)) {
            milk = new ItemStack(ModItems.SHEEP_MILK_BOWL.get());
        } else if (heldItem.is(Items.BUCKET)) {
            milk = new ItemStack(ModItems.SHEEP_MILK_BUCKET.get());
        } else if (heldItem.is(ModItems.BASKET.get())) {
            milk = new ItemStack(ModItems.SHEEP_MILK_BASKET.get());
        } else {
            return;
        }

        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));

        if (!event.getLevel().isClientSide) {
            event.getEntity().setItemInHand(event.getHand(),
                    ItemUtils.createFilledResult(heldItem, event.getEntity(), milk));
        }
    }

    @SubscribeEvent
    public void onBloodBottleInteract(PlayerInteractEvent.RightClickBlock event) {
        ItemStack heldItem = event.getItemStack();
        var clickedState = event.getLevel().getBlockState(event.getPos());
        boolean emptyCauldron = clickedState.is(Blocks.CAULDRON);
        boolean isBloodCauldron = clickedState.is(ModFluids.BLOOD_CAULDRON.get());
        boolean isBloodBlob = heldItem.is(ModItems.BLOOD_BLOB.get());
        boolean isBloodBottle = heldItem.is(ModItems.BLOOD_BOTTLE.get());
        if ((isBloodBlob || isBloodBottle) && (emptyCauldron || isBloodCauldron)) {
            int currentLevel = isBloodCauldron ? clickedState.getValue(LayeredCauldronBlock.LEVEL) : 0;
            if (currentLevel < 3) {
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
                if (!event.getLevel().isClientSide) {
                    var filledState = ModFluids.BLOOD_CAULDRON.get().defaultBlockState()
                            .setValue(LayeredCauldronBlock.LEVEL, currentLevel + 1);
                    event.getLevel().setBlockAndUpdate(event.getPos(), filledState);
                    if (isBloodBlob && !event.getEntity().getAbilities().instabuild) {
                        heldItem.shrink(1);
                    } else if (isBloodBottle) {
                        event.getEntity().setItemInHand(event.getHand(),
                                ItemUtils.createFilledResult(heldItem, event.getEntity(),
                                        new ItemStack(Items.GLASS_BOTTLE)));
                    }
                    event.getEntity().awardStat(net.minecraft.stats.Stats.FILL_CAULDRON);
                    event.getLevel().playSound(null, event.getPos(), SoundEvents.BOTTLE_EMPTY,
                            SoundSource.BLOCKS, 1.0F, 1.0F);
                    event.getLevel().gameEvent(GameEvent.FLUID_PLACE, event.getPos(),
                            GameEvent.Context.of(event.getEntity()));
                }
                return;
            }
        }

        if (heldItem.is(Items.GLASS_BOTTLE)
                && event.getLevel().getFluidState(event.getPos()).isSource()
                && event.getLevel().getFluidState(event.getPos()).getType() == ModFluids.BLOOD.get()) {
            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
            if (!event.getLevel().isClientSide) {
                event.getEntity().setItemInHand(event.getHand(),
                        ItemUtils.createFilledResult(heldItem, event.getEntity(),
                                new ItemStack(ModItems.BLOOD_BOTTLE.get())));
                event.getLevel().playSound(null, event.getPos(), SoundEvents.BOTTLE_FILL,
                        SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return;
        }

        if (!heldItem.is(ModItems.BLOOD_BUCKET.get())
                || !event.getLevel().getBlockState(event.getPos()).is(Blocks.CAULDRON)) {
            return;
        }

        event.setCanceled(true);
        event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
        if (!event.getLevel().isClientSide) {
            var bloodCauldron = ModFluids.BLOOD_CAULDRON.get().defaultBlockState()
                    .setValue(LayeredCauldronBlock.LEVEL, 3);
            event.getLevel().setBlockAndUpdate(event.getPos(), bloodCauldron);
            event.getEntity().setItemInHand(event.getHand(),
                    ItemUtils.createFilledResult(heldItem, event.getEntity(), new ItemStack(Items.BUCKET)));
            event.getEntity().awardStat(net.minecraft.stats.Stats.FILL_CAULDRON);
            event.getLevel().playSound(null, event.getPos(), SoundEvents.BUCKET_EMPTY,
                    SoundSource.BLOCKS, 1.0F, 1.0F);
        }
    }

    @SubscribeEvent
    public void onWandererJoin(EntityJoinLevelEvent event) {
        if (event.getLevel().isClientSide()
                || !(event.getEntity() instanceof WanderingTrader trader)) {
            return;
        }

        String tradeCheckTag = "cgt_mod:highshroom_trade_checked";
        if (trader.getPersistentData().getBoolean(tradeCheckTag)) {
            return;
        }
        trader.getPersistentData().putBoolean(tradeCheckTag, true);

        if (trader.getRandom().nextFloat() >= 0.05F) {
            return;
        }

        ItemStack highshroom = switch (trader.getRandom().nextInt(4)) {
            case 0 -> new ItemStack(ModItems.HIGHSHROOM.get());
            case 1 -> new ItemStack(ModItems.IEIUNIUM_HIGHSHROOM.get());
            case 2 -> new ItemStack(ModItems.FORTIS_HIGHSHROOM.get());
            default -> new ItemStack(ModItems.GLOWIUS_HIGHSHROOM.get());
        };
        ItemStack emeraldCost = new ItemStack(Items.EMERALD, trader.getRandom().nextInt(5) + 1);
        trader.getOffers().add(new MerchantOffer(emeraldCost, highshroom, 12, 5, 0.05F));
    }

}
