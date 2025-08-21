package com.June.cgt_mod.item;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.ModCreativeTab;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraft.world.item.Rarity.RARE;

public class ModItems {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cgt_mod.MOD_ID);

    public static final RegistryObject<Item> STICK_BUNDLE = ITEMS.register("stick_bundle",() -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> LITTLE_STONE = ITEMS.register("little_stone",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> WEDDING_RING = ITEMS.register("wedding_ring",() -> new Item(new Item.Properties()
            .stacksTo(1)
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> HUMAN_FLESH = ITEMS.register("human_flesh",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(4)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 6000, 0), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.POISON, 3000, 3), 0.3F)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 600, 9), 0.5F)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> GUTS = ITEMS.register("guts",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(6)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 6000, 0), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.POISON, 3000, 3), 0.3F)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 3000, 9), 0.9F)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_ZOMBIE_ARM = ITEMS.register("cooked_zombie_arm",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_VILLAGER_ARM = ITEMS.register("cooked_villager_arm",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_PILLAGER_ARM = ITEMS.register("cooked_pillager_arm",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_PLAYER_ARM = ITEMS.register("cooked_player_arm",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_ZOMBIE_LEG = ITEMS.register("cooked_zombie_leg",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_VILLAGER_LEG = ITEMS.register("cooked_villager_leg",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_PILLAGER_LEG = ITEMS.register("cooked_pillager_leg",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_PLAYER_LEG = ITEMS.register("cooked_player_leg",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_ZOMBIE_TORSO = ITEMS.register("cooked_zombie_torso",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(7)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_VILLAGER_TORSO = ITEMS.register("cooked_villager_torso",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(7)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_PILLAGER_TORSO = ITEMS.register("cooked_pillager_torso",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(7)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COOKED_PLAYER_TORSO = ITEMS.register("cooked_player_torso",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(7)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> SOUP_DE_CANNIBAL = ITEMS.register("soup_de_cannibal",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(12)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> ZOMBIE_HEAD_ON_STICK = ITEMS.register("zombie_head_on_stick",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> SKELETON_HEAD_ON_STICK = ITEMS.register("skeleton_head_on_stick",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> PILLAGER_HEAD_ON_STICK = ITEMS.register("pillager_head_on_stick",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> VILLAGER_HEAD_ON_STICK = ITEMS.register("villager_head_on_stick",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat().build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> PLAYER_HEAD_ON_STICK = ITEMS.register("player_head_on_stick",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> PILLAGER_HEAD = ITEMS.register("pillager_head",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
            .rarity(Rarity.UNCOMMON)
    ));

    public static final RegistryObject<Item> VILLAGER_HEAD = ITEMS.register("villager_head",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
            .rarity(Rarity.UNCOMMON)
    ));

    public static final RegistryObject<Item> ZOMBIE_TORSO = ITEMS.register("zombie_torso",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> SKELETON_TORSO = ITEMS.register("skeleton_torso",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> PILLAGER_TORSO = ITEMS.register("pillager_torso",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> VILLAGER_TORSO = ITEMS.register("villager_torso",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> PLAYER_TORSO = ITEMS.register("player_torso",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> ZOMBIE_ARM = ITEMS.register("zombie_arm",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> SKELETON_ARM = ITEMS.register("skeleton_arm",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> PILLAGER_ARM = ITEMS.register("pillager_arm",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> VILLAGER_ARM = ITEMS.register("villager_arm",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> PLAYER_ARM = ITEMS.register("player_arm",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> ZOMBIE_LEG = ITEMS.register("zombie_leg",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> SKELETON_LEG = ITEMS.register("skeleton_leg",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> PILLAGER_LEG = ITEMS.register("pillager_leg",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> VILLAGER_LEG = ITEMS.register("villager_leg",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> PLAYER_LEG = ITEMS.register("player_leg",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> EMMENTAL = ITEMS.register("emmental",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(6)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> CAMEMBERT = ITEMS.register("camembert",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> FROMAGE_DE_CHEVRE = ITEMS.register("fromage_de_chevre",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> EPOISSES = ITEMS.register("epoisses",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(6)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3000, 0), 1F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> TOMME_DU_JURA = ITEMS.register("tomme_du_jura",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(6)
                    .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 3000, 0), 1F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> COMTE = ITEMS.register("comte",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> MORBIER = ITEMS.register("morbier",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(6)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 3000, 0), 1F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> RACLETTE = ITEMS.register("raclette",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1000, 0), 0.8F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> BRIA_SAVARIN = ITEMS.register("bria_savarin",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(6)
                    .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 3000, 0), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.SLOW_FALLING, 3000, 0), 0.1F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> CASU_MARZU = ITEMS.register("casu_marzu",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(1)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 200, 5), 0.7F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> CHEDDAR = ITEMS.register("cheddar",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(10)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> RACLETTE_PLATE = ITEMS.register("raclette_plate",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(16)
                    .alwaysEat()
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3000, 1), 1F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> SHEEP_MILK_BUCKET = ITEMS.register("sheep_milk_bucket",() -> new Item(new Item.Properties()
            .stacksTo(1)
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> BASKET = ITEMS.register("basket",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> MILK_BASKET = ITEMS.register("milk_basket",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> SHEEP_MILK_BOWL = ITEMS.register("sheep_milk_bowl",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> MILK_BOWL = ITEMS.register("milk_bowl",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> SHEEP_MILK_BASKET = ITEMS.register("sheep_milk_basket",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(5)
                    .alwaysEat()
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> SHEEP_MILK = ITEMS.register("sheep_milk",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> HIGHSHROOM = ITEMS.register("highshroom",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(2)
                    .alwaysEat()
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 10), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 400, 5), 1F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> IEIUNIUM_HIGHSHROOM = ITEMS.register("ieiunium_highshroom",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(2)
                    .alwaysEat()
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 10), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 5), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 400, 5), 1F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> FORTIS_HIGHSHROOM = ITEMS.register("fortis_highshroom",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(2)
                    .alwaysEat()
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 1000, 10), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3000, 1), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 3000, 1), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 400, 5), 1F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));
    public static final RegistryObject<Item> GLOWIUS_HIGHSHROOM = ITEMS.register("glowius_highshroom",() -> new Item(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(2)
                    .alwaysEat()
                    .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 1000, 1), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 1000, 10), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3000, 1), 1F)
                    .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 400, 5), 1F)
                    .build())
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> TRUE_BIBLE_1 = ITEMS.register("true_bible_1",() -> new TrueBibleOneItem(new Item.Properties()
            .tab(ModCreativeTab.instance)
            .rarity(RARE)

    ));

    public static final RegistryObject<Item> TRUE_BIBLE_2 = ITEMS.register("true_bible_2",() -> new TrueBibleTwoItem(new Item.Properties()
            .tab(ModCreativeTab.instance)
            .rarity(RARE)

    ));

    public static final RegistryObject<Item> TRUE_BIBLE_3 = ITEMS.register("true_bible_3",() -> new TrueBibleThreeItem(new Item.Properties()
            .tab(ModCreativeTab.instance)
            .rarity(RARE)
    ));

    public static final RegistryObject<Item> DIAMOND_FRAGMENT = ITEMS.register("diamond_fragment",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

//Armor Items


    public static final RegistryObject<ArmorItem> AKINA_JACKET = ITEMS.register("akina_jacket",() -> new ArmorItem(ArmorMaterials.LEATHER, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeTab.instance)));





    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
