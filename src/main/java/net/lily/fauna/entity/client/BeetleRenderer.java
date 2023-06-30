package net.lily.fauna.entity.client;

import com.google.common.collect.Maps;
import net.lily.fauna.entity.custom.BeetleEntity;
import net.lily.fauna.entity.variant.BeetleVariant;
import net.lily.fauna.fauna;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import java.util.Map;


public class BeetleRenderer extends GeoEntityRenderer<BeetleEntity> {
    public static final Map<BeetleVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BeetleVariant.class), (map) -> {
                map.put(BeetleVariant.HERACROSS,
                        new Identifier(fauna.MOD_ID, "textures/entity/heracross.png"));
                map.put(BeetleVariant.STAG,
                        new Identifier(fauna.MOD_ID, "textures/entity/stag.png"));
                map.put(BeetleVariant.RHINO,
                        new Identifier(fauna.MOD_ID, "textures/entity/rhino.png"));
                map.put(BeetleVariant.HORNED,
                        new Identifier(fauna.MOD_ID, "textures/entity/horned.png"));
            });

    public BeetleRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new BeetleModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(BeetleEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderLayer getRenderType(BeetleEntity animatable, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder,
                packedLightIn, textureLocation);
    }
}