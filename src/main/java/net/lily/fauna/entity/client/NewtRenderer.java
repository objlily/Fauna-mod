package net.lily.fauna.entity.client;

import net.lily.fauna.entity.custom.CrabEntity;
import net.lily.fauna.entity.custom.NewtEntity;
import net.lily.fauna.entity.variant.NewtVariant;
import net.lily.fauna.fauna;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import com.google.common.collect.Maps;
import net.minecraft.util.Util;
import net.minecraft.client.render.RenderLayer;

import net.minecraft.client.render.VertexConsumerProvider;

import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

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




    public RenderLayer getRenderType(CrabEntity entity, float partialTicks, MatrixStack stack,
                                     VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder,
                                     int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityCutoutNoCull(textureLocation);
    }
}