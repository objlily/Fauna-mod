package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.ChameleonEntity;
import net.lily.fauna.fauna;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class ChameleonModel extends AnimatedGeoModel<ChameleonEntity> {
    @Override
    public Identifier getModelResource(ChameleonEntity instance) {
        return new Identifier(fauna.MOD_ID, "geo/chameleon.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChameleonEntity object) {
        return new Identifier(fauna.MOD_ID, "textures/entity/chameleon.png");
    }

    @Override
    public Identifier getAnimationResource(ChameleonEntity animatable) {
        return new Identifier(fauna.MOD_ID, "animations/chameleon.animation.json");
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(ChameleonEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180f));
        }
    }
}
