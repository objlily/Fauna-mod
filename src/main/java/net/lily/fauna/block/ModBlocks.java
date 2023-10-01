package net.lily.fauna.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.lily.fauna.fauna;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.item.BlockItem;



public class ModBlocks {

    public static final Block ORANGE_PLANKS = registerBlock("orange_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)), ItemGroup.MISC);

    public static final Block ORANGE_LEAVES = registerBlock("orange_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).nonOpaque()), ItemGroup.MISC);

    public static final Block ORANGE_LOG = (Block) registerBlock("orange_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), ItemGroup.MISC);

    public static final Block ORANGE_WOOD = registerBlock("orange_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)), ItemGroup.MISC);

    public static final Block STRIPPED_ORANGE_LOG = (Block) registerBlock("stripped_orange_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.MISC);

    public static final Block STRIPPED_ORANGE_WOOD = registerBlock("stripped_orange_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.MISC);

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(fauna.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(fauna.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        fauna.LOGGER.debug("Registering ModBlocks for " + fauna.MOD_ID);
    }
}
