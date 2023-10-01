package net.lily.fauna.entity.client;


import net.lily.fauna.entity.custom.CapybaraEntity;
import net.lily.fauna.fauna;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

public class CapybaraModel extends AnimatedGeoModel<CapybaraEntity> {
    @Override
    public Identifier getModelResource(CapybaraEntity instance) {
        return new Identifier(fauna.MOD_ID, "geo/capybara.geo.json");
    }

    @Override
    public Identifier getTextureResource(CapybaraEntity object) {
        return new Identifier(fauna.MOD_ID, "textures/entity/capybara.png");
    }

    @Override
    public Identifier getAnimationResource(CapybaraEntity animatable) {
        return new Identifier(fauna.MOD_ID, "animations/capybara.animation.json");
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void setLivingAnimations(CapybaraEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        IBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * ((float) Math.PI / 180f));
            head.setRotationY(extraData.netHeadYaw * ((float) Math.PI / 180f));
        }
    }
}
