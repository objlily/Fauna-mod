package net.lily.fauna.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.lily.fauna.entity.ModEntities;
import net.lily.fauna.fauna;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public class ModItems {



    public static final Item ORANGE = registerItem("orange",
            new Item(new FabricItemSettings()));


    public static final Item CRAB_CLAW = registerItem("crab_claw",
            new Item(new FabricItemSettings().maxCount(1)));


    public static final Item NEWT_SPAWN_EGG = registerItem("newt_spawn_egg",
            new SpawnEggItem(ModEntities.NEWT,0x697e94, 0xd9782e,
                    new FabricItemSettings()));
    public static final Item RACCOON_SPAWN_EGG = registerItem("raccoon_spawn_egg",
            new SpawnEggItem(ModEntities.RACCOON,0xa3a3a3, 0x4d4d4d,
                    new FabricItemSettings()));

    public static final Item CHAMELEON_SPAWN_EGG = registerItem("chameleon_spawn_egg",
            new SpawnEggItem(ModEntities.CHAMELEON,0xaccf3c, 0xf5eb82,
                    new FabricItemSettings()));

    public static final Item CAPYBARA_SPAWN_EGG = registerItem("capybara_spawn_egg",
            new SpawnEggItem(ModEntities.CAPYBARA,0xb56736, 0x40220f,
                    new FabricItemSettings()));

    public static final Item CRAB_SPAWN_EGG = registerItem("crab_spawn_egg",
            new SpawnEggItem(ModEntities.CRAB,0x3963e3, 0xd97e23,
                    new FabricItemSettings()));


    private static void addItemsToSpawnEggItemGroup(FabricItemGroupEntries entries) {
        entries.add(NEWT_SPAWN_EGG);
        entries.add(RACCOON_SPAWN_EGG);
        entries.add(CHAMELEON_SPAWN_EGG);
        entries.add(CAPYBARA_SPAWN_EGG);
        entries.add(CRAB_SPAWN_EGG);
    }

    private static void addItemsToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(CRAB_CLAW);
    }

    private static void addItemsToFoodsItemGroup(FabricItemGroupEntries entries) {
        entries.add(ORANGE);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(fauna.MOD_ID, name), item);
    }


    public static void registerModItems() {
        fauna.LOGGER.info("Registering Mod Items for " + fauna.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToToolsItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(ModItems::addItemsToSpawnEggItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodsItemGroup);
    }
}
