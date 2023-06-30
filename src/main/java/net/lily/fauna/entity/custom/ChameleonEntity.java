package net.lily.fauna.entity.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ChameleonEntity extends PassiveEntity implements IAnimatable {



    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }


    private final AnimationFactory factory;

    public ChameleonEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
        this.factory = new AnimationFactory(this);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return PassiveEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.05);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (hasEntityMoved(event)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chameleon.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.chameleon.idle", true));
        }
        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> boolean hasEntityMoved(AnimationEvent<E> event) {
        Entity entity = this;
        double prevX = entity.prevX;
        double prevY = entity.prevY;
        double prevZ = entity.prevZ;
        double currentX = entity.getX();
        double currentY = entity.getY();
        double currentZ = entity.getZ();

        return prevX != currentX || prevY != currentY || prevZ != currentZ;
    }



    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 4, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(0, new WanderAroundFarGoal(this, 1.0));
    }
}