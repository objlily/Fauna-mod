package net.lily.fauna.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.lily.fauna.fauna;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.item.BlockItem;



public class ModBlocks {

    public static final Block ORANGE_PLANKS = registerBlock("orange_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)));

    public static final Block ORANGE_LEAVES = registerBlock("orange_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).nonOpaque()));

    public static final Block ORANGE_LOG = (Block) registerBlock("orange_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)));

    public static final Block ORANGE_WOOD = registerBlock("orange_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)));

    public static final Block STRIPPED_ORANGE_LOG = (Block) registerBlock("stripped_orange_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG)));

    public static final Block STRIPPED_ORANGE_WOOD = registerBlock("stripped_orange_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(fauna.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(fauna.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }


    public static void registerModBlocks() {
        fauna.LOGGER.debug("Registering ModBlocks for " + fauna.MOD_ID);
    }
}
