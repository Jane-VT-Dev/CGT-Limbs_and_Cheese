package com.June.cgt_mod.procedures;

import com.June.cgt_mod.item.ModItems;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class TrueBible1UsingProcedure {

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if (world instanceof ServerLevel _level)
            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                    "give @p written_book{pages:['[[\"\",{\"text\":\"***Bible #1***\",\"underlined\":true,\"bold\":true},\"\\\\n\\\\nIn the beginning was\\\\nthe Word,\\\\nand the Word was \",{\"text\":\"\\\\\"Arrrgh!\\\\\"\",\"italic\":true}]]','[[\"\u2014PIRATICUS 13:7 \\\\nFive Thousand Years Ago:\\\\n\\\\nThe Beginning\\\\nTHE FLYING SPAGHETTI MONSTER created the universe and a bunch of planets,\\\\n\\\\nincluding Earth.\"]]','[[\"No one except Himself was around to see it,\\\\n\\\\nbut we suspect it was rather dull.\\\\n\\\\nThe initial creation, obviously,\\\\n\\\\nmust have been spectacular,\"]]','[[\"but He then spent the next ten to one hundred years\\\\n\\\\npainstakingly preparing the universe to appear older than it actually is.\"]]','[[\"Photons were placed individually,\\\\n\\\\n\\\\nen route to earth, ostensibly emitted millions of years ago\\\\n\\\\nfrom stars across the galaxy.\"]]','[[\"In reality, we know that each photon was divinely placed\\\\n\\\\nand red-shifted appropriately to make the universe appear\\\\n\\\\nto be billions of years old.\"]]','[[\"We are still finding His camouflage methods at work today;\\\\n\\\\neach time scientists discover apparent evidence of a\\\\n\\\\nbillions-of-years-old universe,\"]]','[[\"we can be assured that this is just\\\\n\\\\nmore elaborate preparation He put in place.\"]]','[[\" Earth was created in approximately 0.062831853 seconds\\\\n\\\\nand was similarly disguised to appear much older.\"]]','[[\"We can be certain that the FSM spent even more time preparing the earth,\\\\n\\\\nbecause, being allknowing,\"]]','[[\"He was well aware that soon enough\\\\n\\\\nthere would be nosy people poking around everywhere.\"]]','[[\" Known as \\\\\"scientists,\\\\\" these nosy people have a sick need\u2014probably sexually motivated\u2014to figure out how things work,\\\\n\\\\nand so it was even more important that our apparent reality be well designed to hide the truth.\"]]','[[\"Our Noodly Creator then placed fossils, hidden under the earth\\'s surface,\\\\n\\\\nknowing that they would later be found\u2014thus,\\\\n\\\\nseemingly proving that these creatures existed some time ago.\"]]','[[\" Dinosaur bones, for example,\\\\nwere placed so well and in such numbers that it\\'s widely\\\\n\\\\nbelieved dinosaurs roamed the earth millions of years ago.\"]]','[[\"Interestingly, dinosaurs did exist,\\\\n\\\\nbut not millions of years ago, because,\\\\n\\\\nof course, how could they have existed before the earth was even here?\\\\n\\\\nIn reality they lived with us,\"]]','[[\" alongside\u2014and occasionally on top of\u2014humans around\\\\n\\\\nthree thousand years ago.\"]]'],title:\"True Bible 1\",author:unknown}");
        if (entity instanceof Player _player) {
            ItemStack _stktoremove = new ItemStack(ModItems.TRUE_BIBLE_1.get());
            _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
        }
    }

}
