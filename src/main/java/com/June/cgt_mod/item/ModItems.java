package com.June.cgt_mod.item;

import com.June.cgt_mod.Cgt_mod;
import com.June.cgt_mod.ModCreativeTab;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {


    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Cgt_mod.MOD_ID);

    public static final RegistryObject<Item> STICK_BUNDLE = ITEMS.register("stick_bundle",() -> new Item(new Item.Properties().tab(ModCreativeTab.instance)));

    public static final RegistryObject<Item> LITTLE_STONE = ITEMS.register("little_stone",() -> new Item(new Item.Properties()
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


    public static final RegistryObject<Item> TORN_PAGE = ITEMS.register("torn_page",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> HUMAN_LEATHER = ITEMS.register("human_leather",() -> new Item(new Item.Properties()
            .tab(ModCreativeTab.instance)
    ));

    public static final RegistryObject<Item> CURSED_BIBLE_I = ITEMS.register("cursed_bible_i",
            () -> new LoreBookItem(bookProps(), List.of(
                    // Page 1: cover
                    cover(),
                    // Page 2: heading + start of text
                    heading("Five Thousand Years Ago:\nThe Beginning",
                            "THE FLYING SPAGHETTI MONSTER created the universe and a bunch of planets, including Earth. No one except Himself was around to see it, but we suspect"),
                    body("it was rather dull. The initial creation, obviously, must have been spectacular, but He then spent the next ten to one hundred years painstakingly preparing the universe to appear older than it"),
                    body("actually is. Photons were placed individually, en route to earth, ostensibly emitted millions of years ago from stars across the galaxy. In reality, we know that each photon was divinely placed and red-shifted"),
                    body("appropriately to make the universe appear to be billions of years old. We are still finding His camouflage methods at work today; each time scientists discover apparent evidence of a"),
                    body("billions-of-years-old universe, we can be assured that this is just more elaborate preparation He put in place. Earth was created in approximately 0.062831853 seconds and was similarly disguised to appear"),
                    body("much older. We can be certain that the FSM spent even more time preparing the earth, because, being all-knowing, He was well aware that soon enough there would be nosy people poking around everywhere. Known as"),
                    body("\"scientists,\" these nosy people have a sick need\u2014probably sexually motivated\u2014to figure out how things work, and so it was even more important that our apparent reality be well designed to hide the truth. Our"),
                    body("Noodly Creator then placed fossils, hidden under the earth's surface, knowing that they would later be found\u2014thus, seemingly proving that these creatures existed some time ago. Dinosaur bones, for"),
                    body("example, were placed so well and in such numbers that it's widely believed dinosaurs roamed the earth millions of years ago. Interestingly, dinosaurs did exist, but not millions of years ago, because, of"),
                    body("course, how could they have existed before the earth was even here? In reality they lived with us, alongside\u2014and occasionally on top of\u2014humans around three thousand years ago.")
            )));

    public static final RegistryObject<Item> CURSED_BIBLE_II = ITEMS.register("cursed_bible_ii",
            () -> new LoreBookItem(bookProps(), List.of(
                    titlePage("Bible #2"),
                    body("You may wonder why we find no bones from dinosaurs from this era, and rightly so. But keep in mind that dinosaurs don't actually have bones\u2014the whole dinosaurs had bones thing is all an elaborate hoax"),
                    body("planned for His own divine amusement. Real dinosaurs, as any enlightened paleontologist\u2014or bone doctor, as they prefer to be called\u2014will tell you, were able to stand erect by engorging selected muscles with blood,"),
                    body("making the once flaccid limb rigid. By alternating which muscles were engorged in the correct sequence, a very effective locomotion and rudimentary skeletal structure was achieved. Perverted readers"),
                    body("may recognize that this mechanism is similar to what happens in the male penis. Dinosaurs were, in essence, not much more than a massive collection of penises (penii) under a thick skin. While very few"),
                    body("accurate descriptions of these creatures have existed into present times, we can be pleased to learn that awareness of them has propagated generationally in our culture. Most men don't even"),
                    body("realize that when they exaggerate the size of their penis\u2014referring to it as \"monstrous\" or \"dinosaurlike\"\u2014they are helping to keep alive the hidden truth of the strange and horny beasts we know as"),
                    body("dinosaurs. Some time later, as society progressed, the attention of mankind moved away from dinosaurs\u2014by now they had been conquered and placed under the control of men for work and play\u2014and instead man turned"),
                    body("to philosophical thought. The question of our origins came up, and it was decided, based on the apparent natural evidence, that all creatures had evolved from a common ancestor over time some"),
                    body("millions of years ago."),
                    heading("Twenty-five Hundred Years Ago:\nThe Golden Age of Pirates",
                            "What happened next is still a mystery. Mankind up until this time had been successfully duped by the FSM, wrongly believing that"),
                    body("natural explanations could explain our origins. One would think that the FSM Himself would be pleased by this, as He had gone to so much trouble disguising His creation work. But for whatever"),
                    body("reason, He felt the need to expose the truth to us. This was the date, some twenty-five hundred years ago, that He first revealed His Noodly Appendage to us, showing us the way. From this point on, those who accepted His"),
                    body("message knew that we were to live a certain way\u2014on the water in great wooden ships, loaded with grog, swag, and, hopefully, wenches. This was His will, and so it was done. Unfortunately, many of the details from"),
                    body("this era are lost to us, possibly because many ships sank, due to overloading. Swag is very heavy, and these, the first Pastafarians, showed less than 100 percent perfect judgment, having drunk too much"),
                    body("grog. What we do know, though, is that this was the Golden Age of the Pirate lifestyle. Millions, possibly hundreds, of Pirate ships roamed the world's oceans and maybe lakes, searching for a good time,"),
                    body("spreading joy and maybe VD to whomever they came into contact with. What we're told of Pirates in history books today is blatantly wrong. Thieves and outcasts they were not\u2014these were His Chosen People, the"),
                    body("ones who listened and followed His divine plan, whatever it was. The commonly propagated myth that Pirates were thieves can be traced, unsurprisingly, to the Christian theologists of the"),
                    body("Middle Ages. It's just another example of the discrimination and misinformation that we've had to contend with over the years, and another reason Pastafarians have been so secretive about their"),
                    body("beliefs. Regardless of the lies told about them, the first Pastafarians were peace-loving explorers and spreaders of goodwill, not bloodthirsty criminal Pirates. In fact, they were well known to"),
                    body("distribute candy to passing children, thus establishing what is now known as Halloween. Of course it was not all good times. Not everyone was a believer, and some rejected His Word and felt the need to go out on the"),
                    body("ocean in their own (probably lame) ships and pick fights with the Pastafarians. Most notable was Noah, of biblical fame, who slapped together a monstrous barge made of wood and whatever else was"),
                    body("around\u2014probably dirt, who knows? Noah, well known for his love of animals, always had plenty around. So when he found himself with a lack of building materials, he decided to use hundreds of"),
                    body("defenseless animals as ballast\u2014mainly the slowest, dumbest, and most dense that he could come across. It's not known exactly what occurred during this time of Noah and the Pirates, but enough historical texts"),
                    body("have survived through the years to get a rough picture of the events that transpired. Noah, alone except for his animals/ballast, propelled by jealousy and maybe a group of talking"),
                    body("seals, set forth in search of Pastafarians. Unfortunately for Noah, he found one of the most bad-ass Pirate ships around, and started talking way too much smack. The Pastafarians, being above all peaceful,"),
                    body("and maybe drunk, ignored his verbal abuse. It was only when Noah, ever the dick, physically attacked the Pastafarian ship by hurling from his bow the pointiest of animals that the Pastafarians took notice. We are told"),
                    body("that the largest, scariest of the Pastafarians swam, or maybe just jumped, from ship to ship\u2014they were that powerful\u2014and confronted Noah. Immediately seeing the error of his ways, Noah offered some turtles or"),
                    body("something as a way of apologizing. The Pastafarians, probably having plenty of their own turtles, said no deal, and proceeded to intimidate the bejesus out of Noah. We don't know exactly what was said, but it's"),
                    body("clear that Noah wet himself to such an extent that even Christians associate him with \"the Great Flood.\" Needless to say, he never mistreated animals again\u2014not even chickens, who are pretty much asking for it.")
            )));


    public static final RegistryObject<Item> CURSED_BIBLE_III = ITEMS.register("cursed_bible_iii",
            () -> new LoreBookItem(bookProps(), List.of(
                    titlePage("Bible #3"),
                    heading("1700 to Today:\nOf Past a and Pirates",
                            "For centuries after that, no one messed with the Pirates, and the natural order of things was kept in balance. Although swag and"),
                    body("grog sent many a ship to the bottom of the sea, losing important historical documentation in the process, the Pirates lived a life of peace and merriment, spreading His Word as far as places"),
                    body("like Belgium. They continued to celebrate Halloween and, during the last two months of every year, took time off from sailing the seas to relax and spend time with their families during their most holiest"),
                    body("time of Holiday. But there were dark storms on the horizon, and the Pirates did not know what evil awaited them. Convinced of the inherent evil of Pirates, Hari Krishnas, who are descended from"),
                    body("Ninjas, banded together at various seaports and declared a holy war against the Pirates. By tens of thousands, maybe even dozens, they boarded steel-plated kayaks and paddled out in search of Pirates,"),
                    body("whom they intended to annihilate from the four corners of the earth. You might think that the FSM would have noticed the Hari Krishnas and protected His Chosen People, but He mistook the Krishnas for just"),
                    body("another musical band of seagoing beggars, or maybe fishermen singing their shanties, and He let them pass unharmed. As the first Krishnas arrived at a Pirate ship on Halloween, the Pirates mistook them for overly"),
                    body("dedicated trick-or-treaters. What followed next was mass slaughter as the Pirates tried to pass out treats while the Krishnas beat and sliced them to death with their double-bladed kayak paddles. Sadly, this pattern"),
                    body("was repeated several times that day. The next year was even worse. Eventually, the Pirates retreated to hidden coves where they could keep a lookout for the bloodthirsty Krishna bastards. Was there something"),
                    body("in the Krishnas' singing that blinded the Pirates to their evil? We may never know. And while mainstream education tells us that Pirates were hunted down because they were thieves, killers, etc., this is largely a"),
                    body("misinformation campaign propagated by the Krishnas and many of the other religions that banded together to begin their systematic assault on the Pirates' worship of the FSM. Hunted nearly to extinction, the"),
                    body("Pirates were indeed quite pissed off for several centuries, and the textbooks reveal every detail of the looting and pillaging but are suspiciously quiet about the fact that Pirates were well known for passing"),
                    body("out candy to children. The sad truth is that the other religions were jealous of the Pirates and their happy lifestyles\u2014it's that simple. Thankfully for the Pirates, the attacks eventually slowed down and"),
                    body("then nearly stopped altogether as the other religions inevitably turned against one another. Which is where we find ourselves today. While it is becoming common knowledge that declining Pirate"),
                    body("numbers are a direct result of religious persecution, what is not yet known is what happened to the remaining Pirates and where they are located. Sadly, many Pirates simply hid their treasures, gave"),
                    body("away their giant turtles, and retired, moving to places like Ireland and, ironically, India. Others hid out in the Straits of Malaysia, while some formed well known sports franchises. What is little known about"),
                    body("our mystical forerunners is that in addition to hiding treasure, Pirates sought to conceal their religious texts. In fact, the treasure was included largely to ensure that others would go out and look for"),
                    body("these documents in the future, during more tolerant times. On this count, the Pirates were wildly successful, as there are still treasure hunters searching for ancient Pirate loot."),
                    body("Unfortunately, many of our original texts have been lost, as their importance was overlooked, being mistaken for recipes in some cases.")
            )));

    public static final RegistryObject<Item> CURSED_BIBLE_IV = ITEMS.register("cursed_bible_iv",
            () -> new LoreBookItem(bookProps(), List.of(
                    titlePage("Bible #4",
                            "Totally legit Bible, by Paul (who is long dead, but still got insulted by my dad)"),
                    body("In the beginning, God created the world by pressing \"Create world\". After loading for 6 days, because clearly computers weren't that great at the time, he got tired and took a \"nap\". When he woke up, the week was"),
                    body("already over. Anyway.\nHe sees the world was really empty and felt lonely. And so, he added NPCs and looked up baby names. He named them Adam and Eve. Adam and Eve then fucked and had kids, despite not"),
                    body("being very different genetically. I'm supposed to tell you inbreeding is bad, but if God's NPCs did it, so can you !\nAdam and Eve had 2 sons, Abel and Cain. My memory isn't the best, but"),
                    body("I'm pretty sure Cain stabbed Abel to death. Apparently his other siblings aren't important so I have no clue who they are, but since they're the only humans in the world, it doesn't take a lot of"),
                    body("braincells to figure out Cain fucked his sister(s). Multiple times. Actually, I'm not sure beds were a concept then, so they probably fucked outside, in the grass or something. But yeah, the point"),
                    body("is that they had enough kids to save their entire population from extinction, so congrats I guess ?\n\nOne of the kids (I mean they're everyone's ancestor so every character in this book are"),
                    body("their kids ?) was a short motherfucker called David. When David was born everyone thought he was just a head, but then the doctor said \"Wait, this stupid muthafuckin' tiny, short ass baby got a tiny, little itty bitty"),
                    body("body and I hate it\". It must be true, since even after puberty David had nothing but his brain. But yeah, the point of me telling you this story is because David decided to fight this big dude called Goliath, and"),
                    body("he one-shot him with his slingshot. Moral of the story ? Yeet a rock at your siblings !\n\nAnother notable kid was named Noah. He was God's good boy, so God told him he was gonna kill everyone except"),
                    body("him. Noah thought this was perfectly reasonable, and a great solution to the genetic diversity the world was starting to have, so he built a huge boat and put one couple of each species on it (and his family because"),
                    body("he's a selfish asshole). God then uses /kill @e[insert code to omit Noah and his friends], and now everyone is inbred again !\n\nFast forward because I'm supposed to be"),
                    body("reviewing for exams and not writing a religious book, Ao is the cutest girl in the world and I love her, which is why I'm going to burn down a forest in her absence (even if that won't even remotely come close to how hot"),
                    body("she is uwu).\n\n-Signed Aki (not Paul, hehe I lied)")
            )));

    private static FormattedText cover() {
        MutableComponent title = Component.literal("Bible #1\n\n").withStyle(ChatFormatting.BOLD);
        MutableComponent quote = Component.literal(
                        "In the beginning was the Word,\nand the Word was \"Arrrgh!\"\n\n")
                .withStyle(ChatFormatting.ITALIC);
        MutableComponent source = Component.literal("\u2014PIRATICUS 13:7");
        return Component.literal("").append(title).append(quote).append(source);
    }

    private static FormattedText heading(String head, String text) {
        return Component.literal("").append(Component.literal(head + "\n\n").withStyle(ChatFormatting.BOLD))
                .append(Component.literal(text));
    }

    private static FormattedText body(String text) {
        return Component.literal(text);
    }

    private static FormattedText titlePage(String title, String subtitle) {
        return Component.literal("")
                .append(Component.literal(title + "\n\n").withStyle(ChatFormatting.BOLD))
                .append(Component.literal(subtitle).withStyle(ChatFormatting.ITALIC));
    }


    private static FormattedText titlePage(String title) {
        return Component.literal(title).withStyle(ChatFormatting.BOLD);
    }

    private static Item.Properties bookProps() {
        return new Item.Properties().tab(ModCreativeTab.instance).stacksTo(1);
    }

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
