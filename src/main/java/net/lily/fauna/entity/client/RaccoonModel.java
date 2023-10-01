package net.lily.fauna.entity.client;


import net.lily.fauna.entity.custom.RaccoonEntity;
import net.lily.fauna.fauna;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class RaccoonModel extends AnimatedGeoModel<RaccoonEntity> {
    @Override
    public Identifier getModelResource(RaccoonEntity instance) {
        return new Identifier(fauna.MOD_ID, "geo/raccoon.geo.json");
    }

    @Override
    public Identifier getTextureResource(RaccoonEntity object) {
        return new Identifier(fauna.MOD_ID, "textures/entity/raccoon.png");
    }

    @Override
    public Identifier getAnimationResource(RaccoonEntity animatable) {
        return new Identifier(fauna.MOD_ID, "animations/raccoon.animation.json");
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(RaccoonEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180f));
        }
    }
}
