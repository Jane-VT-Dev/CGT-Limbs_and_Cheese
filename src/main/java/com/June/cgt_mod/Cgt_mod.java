package com.June.cgt_mod;

import com.June.cgt_mod.config.EffigyConfig;
import com.June.cgt_mod.config.ConsumableConfig;
import com.June.cgt_mod.config.JarConfig;
import com.June.cgt_mod.block.ModBlocks;
import com.June.cgt_mod.block.entity.ModBlockEntities;
import com.June.cgt_mod.item.ModItems;
import com.June.cgt_mod.menu.ModMenus;
import com.mojang.logging.LogUtils;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
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
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConsumableConfig.SPEC, "cgt_mod_cheese_limbs-consumables.toml");

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so items get registered
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenus.register(modEventBus);

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
