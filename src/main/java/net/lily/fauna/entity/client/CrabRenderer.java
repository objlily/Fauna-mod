package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.CrabEntity;
import net.lily.fauna.fauna;
import net.lily.fauna.entity.client.CrabModel;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrabRenderer extends GeoEntityRenderer<CrabEntity> {

    public CrabRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new CrabModel());
        this.shadowRadius = 0.4f;
    }

    public RenderLayer getRenderType(CrabEntity entity, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityCutoutNoCull(textureLocation);
    }
}
