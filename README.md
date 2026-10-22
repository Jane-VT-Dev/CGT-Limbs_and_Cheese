Mod description:
This is a mod that i made together with my friends and for my friends, everyone else is ofc welcome to try it out.
This mod adds Questionable Items, Canibalsm , Cheese... a lot of Cheese and other food!
====================================================================================================================================================
Textures & Models are self made, all rights are reserved to the original owner.
====================================================================================================================================================
You Are free to use this mod in your modpack, as long as it stays within Curseforge / Modrinth.
This Mod is only available on my GitHub,Curseforge and Modrinth.
====================================================================================================================================================
Dependencies for this mod are:
Farmer's Delight,
Alex's mobs
====================================================================================================================================================
I recommend to use a recipe look up mod like JEI/REI/EMI.

Effigy head filters and rotations:
The common config file `config/cgt_mod_cheese_limbs-common.toml` contains `head_whitelist`, `head_blacklist`, and `head_rotations`. The whitelist includes the vanilla skulls and the mod's pillager and villager heads by default. Add or remove item IDs such as `"minecraft:player_head"` or `"cgt_mod_cheese_limbs:pillager_head"` to control which heads can be placed. An empty whitelist allows all recognized heads; the blacklist always takes precedence.

Set individual rotation offsets in `head_rotations` using `"item_id=degrees"` entries. For example, `"minecraft:player_head=90"` turns player heads 90 degrees relative to the effigy's facing. Values from -360 to 360 are supported. All listed heads default to 0 degrees. Restart the game or reload the config after editing.

Jar item deny list:
The jar config file `config/cgt_mod_cheese_limbs-jar.toml` contains `item_deny_list`, which is empty by default. Add item IDs such as `"minecraft:stone"` to prevent those items from being inserted into jars. Both regular insertion and shift-click insertion honor the deny list.

Consumable effects:
The common config file `config/cgt_mod_cheese_limbs-consumables.toml` lists the mod's edible items under `[consumables]`. The `[effects.<item_id>]` sections contain settings for each existing food effect. Effect durations are in ticks; strength uses Minecraft's amplifier value, where 0 is level I and 1 is level II. Changing the reference item list does not register or remove items. Effect chances and nutrition remain unchanged.
