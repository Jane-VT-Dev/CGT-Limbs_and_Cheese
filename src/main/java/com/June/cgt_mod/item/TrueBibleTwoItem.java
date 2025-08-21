package com.June.cgt_mod.item;

import com.June.cgt_mod.procedures.TrueBible2UsingProcedure;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TrueBibleTwoItem extends Item {


    public TrueBibleTwoItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        TrueBible2UsingProcedure.execute(world, x, y, z, entity);
        return ar;
    }

}
