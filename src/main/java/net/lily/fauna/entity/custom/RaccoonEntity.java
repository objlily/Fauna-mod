package net.lily.fauna.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.task.TemptTask;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
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

public class RaccoonEntity extends PassiveEntity implements IAnimatable {


    private static final TrackedData<Boolean> IS_TEMPTED = DataTracker.registerData(RaccoonEntity.class, TrackedDataHandlerRegistry.BOOLEAN);





    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }


    private final AnimationFactory factory;

    public RaccoonEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
        this.factory = new AnimationFactory(this);
        this.dataTracker.startTracking(IS_TEMPTED, false);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return PassiveEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25f);
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {

        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.raccoon.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.raccoon.idle", true));
        }

        return PlayState.CONTINUE;
    }

    private <E extends IAnimatable> PlayState predicate2(AnimationEvent<E> event) {

        if (dataTracker.get(IS_TEMPTED)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.raccoon.lookingup", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.raccoon.idle2", true));
        }
        return PlayState.CONTINUE;
    }



    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 4, this::predicate));
        animationData.addAnimationController(new AnimationController<>(this, "tempted_controller", 12, this::predicate2));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }



    @Override
    protected void initGoals() {
        TemptGoal temptGoal = new TemptGoal(this, 1.2, Ingredient.ofItems(new ItemConvertible[]{Items.EGG,Items.APPLE,Items.POTATO,Items.BEETROOT,Items.MELON,Items.CARROT,Items.BEEF,Items.COOKED_BEEF,Items.COOKED_CHICKEN,Items.COOKED_COD,Items.COOKED_MUTTON,Items.COOKED_PORKCHOP,Items.COOKED_RABBIT,Items.COOKED_SALMON,Items.CHICKEN,Items.RABBIT,Items.SALMON,Items.COD,Items.PORKCHOP,Items.TROPICAL_FISH}), false) {

            @Override
            public void start() {
                super.start();
                dataTracker.set(IS_TEMPTED, true);
            }

            @Override
            public void stop() {
                super.stop();
                dataTracker.set(IS_TEMPTED, false);
            }
        };


        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(2, new LookAroundGoal(this));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(5, temptGoal);
    }


    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_FOX_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_FOX_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_FOX_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_WOLF_STEP, 0.15F, 1.0F);
    }
}