package net.lily.fauna.entity.custom;

import net.lily.fauna.entity.goals.CrabWaveGoal;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import static net.lily.fauna.item.ModItems.CRAB_CLAW;

public class CrabEntity extends AnimalEntity implements GeoEntity {
    private boolean isClawed;
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private boolean isDancing;
    private boolean songPlaying;


    @Nullable
    private BlockPos songSource;
    private static final TrackedData<Boolean> IS_DANCING = DataTracker.registerData(CrabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final TrackedData<Boolean> IS_WAVING = DataTracker.registerData(CrabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public static final TrackedData<Boolean> IS_CLAWED = DataTracker.registerData(CrabEntity.class, TrackedDataHandlerRegistry.BOOLEAN);


    public CrabEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        this.dataTracker.startTracking(IS_DANCING, false);
        this.dataTracker.startTracking(IS_WAVING, false);
        this.isClawed = false;
    }




        public boolean isSongPlaying() {
            return this.songPlaying;
        }

        public EntityGroup getGroup() {
            return EntityGroup.ARTHROPOD;
        }

        @Override
        public void tick () {
            super.tick();
        }

        public void setNearbySongPlaying (BlockPos songPosition,boolean playing){
            this.songSource = songPosition;
            this.songPlaying = playing;
            this.isDancing = playing;
            this.dataTracker.set(IS_DANCING, playing);
            this.dataTracker.set(IS_WAVING, !playing);
        }



    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.SHEARS)) {
            if (!this.getWorld().isClient && this.isShearable()) {
                this.sheared(SoundCategory.PLAYERS);
                this.emitGameEvent(GameEvent.SHEAR, player);
                itemStack.damage(1, player, (playerx) -> {
                    playerx.sendToolBreakStatus(hand);
                });
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        } else {
            return super.interactMob(player, hand);
        }
    }

    public void sheared(SoundCategory shearedSoundCategory) {
        this.getWorld().playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        this.isClawed = true;
        int i = 1;
        for(int j = 0; j < i; ++j) {
            ItemEntity itemEntity = this.dropItem(CRAB_CLAW);
            if (itemEntity != null) {
                itemEntity.setVelocity(itemEntity.getVelocity().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
            }
        }

    }


    public boolean isShearable() {
        return this.isAlive() && !this.isClawed;
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Sheared", this.isClawed);
    }


        @Nullable
        @Override
        public PassiveEntity createChild (ServerWorld world, PassiveEntity entity){
            return null;
        }

        public static DefaultAttributeContainer.Builder setAttributes () {
            return createMobAttributes()
                    .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                    .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
        }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 4, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "controller2", 12, this::predicate2));
        controllerRegistrar.add(new AnimationController<>(this, "controller3", 0, this::predicate3));
    }

        private <T extends GeoAnimatable > PlayState predicate(AnimationState < T > tAnimationState) {
            if (tAnimationState.isMoving()) {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.crab.walk", Animation.LoopType.LOOP));
            }
            else
            {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.crab.idle", Animation.LoopType.LOOP));
            }


            if (this.isClawed) {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.crab.dance", Animation.LoopType.LOOP));
            }

            return PlayState.CONTINUE;
        }


    private <T extends GeoAnimatable> PlayState predicate2(AnimationState<T> tAnimationState) {
            if (isDancing) {
                if (dataTracker.get(IS_DANCING)) {
                    tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.crab.dance", Animation.LoopType.LOOP));
                }
            } else if (dataTracker.get(IS_WAVING)) {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.crab.wave", Animation.LoopType.PLAY_ONCE));
            }
            return PlayState.CONTINUE;
        }


    private <T extends GeoAnimatable> PlayState predicate3(AnimationState<T> tAnimationState) {
        if (isClawed) {
            if (dataTracker.get(IS_CLAWED)) {
                tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.crab.noclaw", Animation.LoopType.LOOP));
            }
        }
        return PlayState.CONTINUE;
    }

        @Override
        protected void initGoals () {

            this.goalSelector.add(0, new CrabWaveGoal(this, 5));
            this.goalSelector.add(1, new SwimGoal(this));
            this.goalSelector.add(2, new LookAroundGoal(this));
            this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        }

    public static boolean canSpawn(EntityType<? extends AnimalEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isIn(BlockTags.FROGS_SPAWNABLE_ON) && isLightLevelValidForNaturalSpawn(world, pos);
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_SPIDER_STEP, 0.05F, 1.5F);
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}