package net.lily.fauna.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class ModFlammableBlockRegistry {
    public static void registerFlammableBlocks() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(ModBlocks.ORANGE_LOG, 5, 5);
        registry.add(ModBlocks.ORANGE_WOOD, 5, 5);
        registry.add(ModBlocks.STRIPPED_ORANGE_LOG, 5, 5);
        registry.add(ModBlocks.STRIPPED_ORANGE_WOOD, 5, 5);

        registry.add(ModBlocks.ORANGE_LEAVES, 30, 60);
        registry.add(ModBlocks.ORANGE_PLANKS, 5, 20);
    }
}
