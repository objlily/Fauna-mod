package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.BeetleEntity;
import net.lily.fauna.fauna;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class BeetleModel extends AnimatedGeoModel<BeetleEntity> {
    @Override
    public Identifier getModelResource(BeetleEntity instance) {
        return new Identifier(fauna.MOD_ID, "geo/beetle.geo.json");
    }

    @Override
    public Identifier getTextureResource(BeetleEntity object) {
        return BeetleRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public Identifier getAnimationResource(BeetleEntity animatable) {
        return new Identifier(fauna.MOD_ID, "animations/beetle.animation.json");
    }
}
