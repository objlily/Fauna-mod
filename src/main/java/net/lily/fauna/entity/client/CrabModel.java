package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.CrabEntity;
import net.lily.fauna.fauna;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CrabModel extends GeoModel<CrabEntity> {
    @Override
    public Identifier getModelResource(CrabEntity instance) {
        return new Identifier(fauna.MOD_ID, "geo/crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrabEntity object) {
        return new Identifier(fauna.MOD_ID, "textures/entity/crab.png");
    }

    @Override
    public Identifier getAnimationResource(CrabEntity animatable) {
        return new Identifier(fauna.MOD_ID, "animations/crab.animation.json");
    }
}
