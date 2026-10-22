package com.June.cgt_mod.menu;

import com.June.cgt_mod.Cgt_mod;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {
	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Cgt_mod.MOD_ID);
	public static final RegistryObject<MenuType<EffigyMenu>> EFFIGY_MENU = MENUS.register(
			"effigy", () -> IForgeMenuType.create(EffigyMenu::new));
	public static final RegistryObject<MenuType<JarMenu>> JAR_MENU = MENUS.register(
			"jar", () -> IForgeMenuType.create(JarMenu::new));
	public static final RegistryObject<MenuType<BloodAltarMenu>> BLOOD_ALTAR_MENU = MENUS.register(
			"blood_altar", () -> IForgeMenuType.create(BloodAltarMenu::new));

	public static void register(IEventBus eventBus) {
		MENUS.register(eventBus);
	}
}