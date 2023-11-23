package net.lily.fauna;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.lily.fauna.entity.ModEntities;
import net.lily.fauna.entity.client.*;
import net.minecraft.entity.EntityType;

public class faunaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.NEWT, NewtRenderer::new);
        EntityRendererRegistry.register(ModEntities.CHAMELEON, ChameleonRenderer::new);
        EntityRendererRegistry.register(ModEntities.RACCOON, RaccoonRenderer::new);
        EntityRendererRegistry.register(ModEntities.CAPYBARA, CapybaraRenderer::new);
        EntityRendererRegistry.register(ModEntities.CRAB, CrabRenderer::new);

    }
}
