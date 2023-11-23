package net.lily.fauna.entity.client;


import net.lily.fauna.entity.custom.NewtEntity;
import net.lily.fauna.entity.custom.RaccoonEntity;
import net.lily.fauna.fauna;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
public class RaccoonModel extends GeoModel<RaccoonEntity> {
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


    @Override
    public void setCustomAnimations(RaccoonEntity animatable, long instanceId, AnimationState<RaccoonEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");


        if (animatable.isBaby()) {
            head.setScaleX(1.4F);
            head.setScaleY(1.4F);
            head.setScaleZ(1.4F);
        } else {
            head.setScaleX(1.0F);
            head.setScaleY(1.0F);
            head.setScaleZ(1.0F);
        }


        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
