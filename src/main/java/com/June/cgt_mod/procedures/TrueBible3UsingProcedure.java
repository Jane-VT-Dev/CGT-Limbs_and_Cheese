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

public class TrueBible3UsingProcedure {

    public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
        if (entity == null)
            return;
        if (world instanceof ServerLevel _level)
            _level.getServer().getCommands().performPrefixedCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null).withSuppressedOutput(),
                    "give @p written_book{pages:['[[\"\",{\"text\":\"***Bible #3***\",\"underlined\":true,\"bold\":true},\"\\\\n\\\\n1700 to Today: Of Past a and Pirates\\\\n\\\\nFor centuries after that, no one messed with the Pirates, and the natural order of things was kept in balance.\"]]','[[\"Although swag and grog sent many a ship to the bottom of the sea, losing important historical documentation in the process, the Pirates lived a life of peace and merriment, spreading His Word as far as places like Belgium.\"]]','[[\" They continued to celebrate Halloween and, during the last two months of every year, took time off from sailing the seas to relax and spend time with their families during their most holiest time of Holi- day.8\"]]','[[\"But there were dark storms on the horizon, and the Pirates did not know what evil awaited them.\"]]','[[\"Convinced of the inherent evil of Pirates, Hari Krishnas, who are descended from Ninjas, banded together at various seaports and declared a holy war against the Pirates.\"]]','[[\" By tens of thousands, maybe even dozens, they boarded steel-plated kayaks and paddled out in search of Pirates, whom they intended to annihilate from the four corners of the earth.\"]]','[[\" You might think that the FSM would have noticed the Hari Krishnas and protected His Chosen People, but He mistook the Krishnas for just another musical band of seagoing beggars, or maybe fishermen singing their shanties, and He let them pass unharmed.\"]]','[[\" As the first Krishnas arrived at a Pirate ship on Halloween, the Pirates mistook them for overly dedicated trick-or-treaters.\"]]','[[\" What followed next was mass slaughter as the Pirates tried to pass out treats while the Krishnas beat and sliced them to death with their double-bladed kayak paddles. Sadly, this pattern was repeated several times that day.\"]]','[[\" The next year was even worse. Eventually, the Pirates retreated to hidden coves where they could keep a lookout for the bloodthirsty Krishna bastards.\"]]','[[\" Was there something in the Krishnas\\' singing that blinded the Pirates to their evil? We may never know. And while mainstream education tells us that Pirates were hunted down because they were thieves, killers, etc.,\"]]','[[\" this is largely a misinformation campaign propagated by the Krishnas and many of the other religions that banded together to begin their systematic assault on the Pirates\\' worship of the FSM.\"]]','[[\" Hunted nearly to extinction, the Pirates were indeed quite pissed off for several centuries, and the textbooks reveal every detail of the looting and pillaging but are suspiciously quiet about the fact that Pirates were well known for passing out candy to children.\"]]','[[\" 1 0 The sad truth is that the other religions were jealous of the Pirates and their happy lifestyles—it\\'s that simple.\"]]','[[\"Thankfully for the Pirates, the attacks eventually slowed down and then nearly stopped altogether as the other religions inevitably turned against one another.\"]]','[[\" Which is where we find ourselves today.  While it is becoming common knowledge that declining Pirate numbers are a direct result of religious persecution, what is not yet known is what happened to the remaining Pirates and where they are located.\"]]','[[\" Sadly, many Pirates simply hid their treasures, gave away their giant turtles,and retired, moving to places like Ireland and, ironically, India.\"]]','[[\" Others hid out in the Straits of Malaysia, while some formed well known sports franchises. What is little known about our mystical forerunners is that in addition to hiding treasure, Pirates sought to conceal their religious texts.\"]]','[[\" In fact, the treasure was included largely to ensure that others would go out and look for these documents in the future, during more tolerant times.\"]]','[[\" On this count, the Pirates were wildly successful, as there are still treasure hunters searching for ancient Pirate loot.\"]]','[[\" Unfortunately, many of our original texts have been lost, as their importance was overlooked, being mistaken for recipes in some cases.\"]]'],title:\"True Bible 3\",author:unknown}");
        if (entity instanceof Player _player) {
            ItemStack _stktoremove = new ItemStack(ModItems.TRUE_BIBLE_3.get());
            _player.getInventory().clearOrCountMatchingItems(p -> _stktoremove.getItem() == p.getItem(), 1, _player.inventoryMenu.getCraftSlots());
        }
    }

}
