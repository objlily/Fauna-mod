package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.CapybaraEntity;
import net.lily.fauna.entity.custom.NewtEntity;
import net.lily.fauna.fauna;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;

public class NewtModel extends GeoModel<NewtEntity> {
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

    @Override
    public void setCustomAnimations(NewtEntity animatable, long instanceId, AnimationState<NewtEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");


        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
