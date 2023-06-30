package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.NewtEntity;
import net.lily.fauna.fauna;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;

import software.bernie.geckolib3.model.provider.data.EntityModelData;
public class NewtModel extends AnimatedGeoModel<NewtEntity> {
    @Override
    public Identifier getModelResource(NewtEntity instance) {
        return new Identifier(fauna.MOD_ID, "geo/newt.geo.json");
    }

    @Override
    public Identifier getTextureResource(NewtEntity object) {
        return NewtRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public Identifier getAnimationResource(NewtEntity animatable) {
        return new Identifier(fauna.MOD_ID, "animations/newt.animation.json");
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(NewtEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180f));
        }
    }
}
