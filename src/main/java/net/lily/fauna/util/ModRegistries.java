package net.lily.fauna.util;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.lily.fauna.block.ModBlocks;
import net.minecraft.registry.tag.ItemTags;

public class ModRegistries {
    public static void registerModStuffs() {
        registerStrippables();
        registerFlammableBlocks();
    }
    private static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.ORANGE_LOG, ModBlocks.STRIPPED_ORANGE_LOG);
        StrippableBlockRegistry.register(ModBlocks.ORANGE_WOOD, ModBlocks.STRIPPED_ORANGE_WOOD);
    }

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