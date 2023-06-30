package net.lily.fauna;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.lily.fauna.entity.ModEntities;
import net.lily.fauna.entity.custom.BeetleEntity;
import net.lily.fauna.entity.custom.ChameleonEntity;
import net.lily.fauna.entity.custom.NewtEntity;
import net.lily.fauna.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;

public class fauna implements ModInitializer {
	public static final String MOD_ID = "fauna";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();

		GeckoLib.initialize();

		FabricDefaultAttributeRegistry.register(ModEntities.NEWT, NewtEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CHAMELEON, ChameleonEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.BEETLE, BeetleEntity.setAttributes());
	}
}
