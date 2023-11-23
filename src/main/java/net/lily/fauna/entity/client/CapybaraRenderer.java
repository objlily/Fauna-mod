package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.CapybaraEntity;
import net.lily.fauna.fauna;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class CapybaraRenderer extends GeoEntityRenderer<CapybaraEntity> {


    public CapybaraRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CapybaraModel());
        this.shadowRadius = 0.4f;
    }


    @Override
    public void render(CapybaraEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}