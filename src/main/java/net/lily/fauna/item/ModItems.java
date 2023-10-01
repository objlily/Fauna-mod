package net.lily.fauna.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.lily.fauna.entity.ModEntities;
import net.lily.fauna.fauna;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final Item ORANGE = registerItem("orange",
            new Item(new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item NEWT_SPAWN_EGG = registerItem("newt_spawn_egg",
            new SpawnEggItem(ModEntities.NEWT,0x697e94, 0xd9782e,
                    new FabricItemSettings().group(ItemGroup.MISC)));
    public static final Item RACCOON_SPAWN_EGG = registerItem("raccoon_spawn_egg",
            new SpawnEggItem(ModEntities.RACCOON,0xa3a3a3, 0x4d4d4d,
                    new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item CHAMELEON_SPAWN_EGG = registerItem("chameleon_spawn_egg",
            new SpawnEggItem(ModEntities.CHAMELEON,0xaccf3c, 0xf5eb82,
                    new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item BEETLE_SPAWN_EGG = registerItem("beetle_spawn_egg",
            new SpawnEggItem(ModEntities.BEETLE,0x262523, 0xd48831,
                    new FabricItemSettings().group(ItemGroup.MISC)));

    public static final Item CAPYBARA_SPAWN_EGG = registerItem("capybara_spawn_egg",
            new SpawnEggItem(ModEntities.CAPYBARA,0xb56736, 0x40220f,
                    new FabricItemSettings().group(ItemGroup.MISC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(fauna.MOD_ID, name), item);
    }
    public static void registerModItems() {
        fauna.LOGGER.debug("Registering Mod Items for " + fauna.MOD_ID);
    }
}
