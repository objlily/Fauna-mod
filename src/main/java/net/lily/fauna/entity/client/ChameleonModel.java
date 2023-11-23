package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.CapybaraEntity;
import net.lily.fauna.entity.custom.ChameleonEntity;
import net.lily.fauna.fauna;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ChameleonModel extends GeoModel<ChameleonEntity> {
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

    @Override
    public void setCustomAnimations(ChameleonEntity animatable, long instanceId, AnimationState<ChameleonEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");


        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}
