package net.lily.fauna.entity.custom;

import net.lily.fauna.entity.variant.BeetleVariant;
import net.lily.fauna.entity.variant.NewtVariant;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.YawAdjustingLookControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class BeetleEntity extends PassiveEntity implements IAnimatable {

    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }

    private final AnimationFactory factory;

    public BeetleEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
        this.factory = new AnimationFactory(this);
        this.moveControl = new FlightMoveControl(this, 20, false);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return PassiveEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            if (isOnGround()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.beetle.walk", true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.beetle.fly", true));
            }
        } else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.beetle.idle", true));
        }
        return PlayState.CONTINUE;
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
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(2, new LookAroundGoal(this));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(DATA_ID_TYPE_VARIANT, 0);
    }



    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(BeetleEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty,
                                 SpawnReason spawnReason, @Nullable EntityData entityData,
                                 @Nullable NbtCompound entityNbt) {
        BeetleVariant variant = Util.getRandom(BeetleVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }



    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant().getId());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Variant")) {
            int variantId = nbt.getInt("Variant");
            this.setVariant(BeetleVariant.byId(variantId));
        }
    }



    public BeetleVariant getVariant() {
        return BeetleVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(BeetleVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }
}