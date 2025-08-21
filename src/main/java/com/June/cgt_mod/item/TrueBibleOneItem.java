package com.June.cgt_mod.item;

import com.June.cgt_mod.procedures.TrueBible1UsingProcedure;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class TrueBibleOneItem extends Item {


    public TrueBibleOneItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
        InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
        ItemStack itemstack = ar.getObject();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();

        TrueBible1UsingProcedure.execute(world, x, y, z, entity);
        return ar;
    }

}
