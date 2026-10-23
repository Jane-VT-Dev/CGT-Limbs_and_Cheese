package com.June.cgt_mod.item;

import com.June.cgt_mod.Cgt_mod;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(modid = Cgt_mod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ScytheItem extends SwordItem {
    public static final int DURABILITY = 1000;

    private static final AttributeModifier ATTACK_RANGE_MODIFIER = new AttributeModifier(
            UUID.fromString("7a4f4bde-62aa-42e9-a462-3cb1d529aef0"),
            "Scythe attack range",
            2.0D,
            AttributeModifier.Operation.ADDITION
    );

    private static final AttributeModifier DEPLETED_DAMAGE_MODIFIER = new AttributeModifier(
            UUID.fromString("3e45e6d8-85c6-49dc-90b5-05b9b9587264"),
            "Depleted scythe damage",
            -1.0D,
            AttributeModifier.Operation.MULTIPLY_TOTAL
    );

    // Built lazily: ForgeMod.ATTACK_RANGE is not registered yet when this class is first loaded.
    private static Multimap<Attribute, AttributeModifier> depletedAttributes;

    private static Multimap<Attribute, AttributeModifier> getDepletedAttributes() {
        if (depletedAttributes == null) {
            depletedAttributes = ImmutableMultimap.of(
                    Attributes.ATTACK_DAMAGE, DEPLETED_DAMAGE_MODIFIER,
                    ForgeMod.ATTACK_RANGE.get(), ATTACK_RANGE_MODIFIER
            );
        }
        return depletedAttributes;
    }

    public ScytheItem(Item.Properties properties) {
        super(Tiers.DIAMOND, 3, -2.4F, properties);
    }

    @Override
    public int getMaxDamage(ItemStack stack) {
        return stack.getDamageValue() >= DURABILITY ? DURABILITY + 1 : DURABILITY;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, T entity, Consumer<T> onBroken) {
        if (stack.getDamageValue() >= DURABILITY) {
            return 0;
        }
        return Math.min(amount, DURABILITY - stack.getDamageValue());
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        if (slot == EquipmentSlot.MAINHAND && stack.getDamageValue() >= DURABILITY) {
            return getDepletedAttributes();
        }
        Multimap<Attribute, AttributeModifier> attributes = super.getAttributeModifiers(slot, stack);
        if (slot != EquipmentSlot.MAINHAND) {
            return attributes;
        }
        return ImmutableMultimap.<Attribute, AttributeModifier>builder()
                .putAll(attributes)
                .put(ForgeMod.ATTACK_RANGE.get(), ATTACK_RANGE_MODIFIER)
                .build();
    }

    @Override
    public boolean isValidRepairItem(ItemStack toRepair, ItemStack repair) {
        return repair.is(Items.IRON_INGOT);
    }

    @SubscribeEvent
    public static void preventDepletedAttacks(AttackEntityEvent event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();
        if (heldItem.getItem() instanceof ScytheItem && heldItem.getDamageValue() >= DURABILITY) {
            event.setCanceled(true);
        }
    }
}
