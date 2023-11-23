package net.lily.fauna;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.lily.fauna.block.ModBlocks;
import net.lily.fauna.entity.ModEntities;
import net.lily.fauna.entity.custom.*;
import net.lily.fauna.item.ModItems;
import net.lily.fauna.util.ModRegistries;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib.GeckoLib;

public class fauna implements ModInitializer {
	public static final String MOD_ID = "fauna";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		GeckoLib.initialize();
		ModRegistries.registerModStuffs();


		FabricDefaultAttributeRegistry.register(ModEntities.NEWT, NewtEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CHAMELEON, ChameleonEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.RACCOON, RaccoonEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CAPYBARA, CapybaraEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CRAB, CrabEntity.setAttributes());

	}
}
