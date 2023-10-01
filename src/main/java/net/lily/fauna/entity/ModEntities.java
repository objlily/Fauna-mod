package net.lily.fauna.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.lily.fauna.entity.custom.*;
import net.lily.fauna.fauna;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModEntities {
    public static final EntityType<NewtEntity> NEWT = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(fauna.MOD_ID, "newt"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, NewtEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 0.5f)).build());

    public static final EntityType<ChameleonEntity> CHAMELEON = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(fauna.MOD_ID, "chameleon"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ChameleonEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 0.5f)).build());

    public static final EntityType<BeetleEntity> BEETLE = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(fauna.MOD_ID, "beetle"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BeetleEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).build());

    public static final EntityType<RaccoonEntity> RACCOON = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(fauna.MOD_ID, "raccoon"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RaccoonEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 0.8f)).build());

    public static final EntityType<CapybaraEntity> CAPYBARA = Registry.register(
            Registry.ENTITY_TYPE, new Identifier(fauna.MOD_ID, "capybara"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CapybaraEntity::new)
                    .dimensions(EntityDimensions.fixed(0.7f, 1f)).build());
}
