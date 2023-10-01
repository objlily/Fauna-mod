package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.RaccoonEntity;
import net.lily.fauna.fauna;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;


public class RaccoonRenderer extends GeoEntityRenderer<RaccoonEntity> {


    public RaccoonRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RaccoonModel());
        this.shadowRadius = 0.4f;
    }


    @Override
    public Identifier getTextureResource(RaccoonEntity object) {
        return new Identifier(fauna.MOD_ID, "textures/entity/raccoon.png");
    }

    @Override
    public RenderLayer getRenderType(RaccoonEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        if(animatable.isBaby()) {
            stack.scale(0.5f, 0.5f, 0.5f);
        } else {
            stack.scale(1f, 1f, 1f);
        }

        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder,
                packedLightIn, textureLocation);
    }
}