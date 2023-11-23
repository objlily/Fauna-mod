package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.ChameleonEntity;
import net.lily.fauna.entity.custom.RaccoonEntity;
import net.lily.fauna.fauna;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Map;


public class ChameleonRenderer extends GeoEntityRenderer<ChameleonEntity> {


    public ChameleonRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ChameleonModel());
        this.shadowRadius = 0.4f;
    }


    @Override
    public void render(ChameleonEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}