package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.NewtEntity;
import net.lily.fauna.entity.variant.NewtVariant;
import net.lily.fauna.fauna;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import com.google.common.collect.Maps;
import net.minecraft.util.Util;
import net.minecraft.client.render.RenderLayer;

import net.minecraft.client.render.VertexConsumerProvider;

import net.minecraft.client.util.math.MatrixStack;

import java.util.Map;


public class NewtRenderer extends GeoEntityRenderer<NewtEntity> {
    public static final Map<NewtVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(NewtVariant.class), (map) -> {
                map.put(NewtVariant.ALPINE,
                        new Identifier(fauna.MOD_ID, "textures/entity/alpine.png"));
                map.put(NewtVariant.ORANGE,
                        new Identifier(fauna.MOD_ID, "textures/entity/orange.png"));
                map.put(NewtVariant.SPOTTED,
                        new Identifier(fauna.MOD_ID, "textures/entity/spotted.png"));
                map.put(NewtVariant.ROUGH,
                        new Identifier(fauna.MOD_ID, "textures/entity/rough_skinned.png"));
            });

    public NewtRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new NewtModel());
        this.shadowRadius = 0.4f;
    }

    @Override
    public Identifier getTextureResource(NewtEntity instance) {
        return LOCATION_BY_VARIANT.get(instance.getVariant());
    }

    @Override
    public RenderLayer getRenderType(NewtEntity animatable, float partialTicks, MatrixStack stack,
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