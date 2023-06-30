package net.lily.fauna;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.lily.fauna.entity.ModEntities;
import net.lily.fauna.entity.client.BeetleRenderer;
import net.lily.fauna.entity.client.ChameleonRenderer;
import net.lily.fauna.entity.client.NewtRenderer;

public class faunaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.NEWT, NewtRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHAMELEON, ChameleonRenderer::new);
        EntityRendererRegistry.register(ModEntities.BEETLE, BeetleRenderer::new);
    }
}
