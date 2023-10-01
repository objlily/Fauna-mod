package net.lily.fauna.entity.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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

public class CapybaraEntity extends TameableEntity implements IAnimatable {

    private static final Ingredient BREEDING_INGREDIENT = Ingredient.ofItems(Items.MELON_SLICE);
    private static final TrackedData<Boolean> IS_SEATED = DataTracker.registerData(CapybaraEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public EntityGroup getGroup() {
        return EntityGroup.DEFAULT;
    }


    private final AnimationFactory factory;

    public CapybaraEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.factory = new AnimationFactory(this);
        this.dataTracker.startTracking(IS_SEATED, false);
    }




    public static DefaultAttributeContainer.Builder setAttributes() {
        return TameableEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15f);
    }


    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (dataTracker.get(IS_SEATED)) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.capybara.sit", true));
        } else {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.capybara.walk", true));
            } else if (isSubmergedInWater()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.capybara.swim", true));
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.capybara.idle", true));
            }
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

        this.goalSelector.add(0, new SitGoal(this));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(1, new FollowOwnerGoal(this, 1.0, 10.0F, 5.0F, false));
        this.goalSelector.add(1, new TemptGoal(this, 1.2, BREEDING_INGREDIENT, false));
        this.goalSelector.add(2, new LookAroundGoal(this));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
    }

    private static final TrackedData<Boolean> SITTING =
            DataTracker.registerData(CapybaraEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getStackInHand(hand);
        Item item = itemstack.getItem();

        Item itemForTaming = Items.MELON;

        if (item == itemForTaming && !isTamed()) {
            if (this.world.isClient()) {
                return ActionResult.CONSUME;
            } else {
                if (!player.getAbilities().creativeMode) {
                    itemstack.decrement(1);
                }

                if (!this.world.isClient()) {
                    super.setOwner(player);
                    this.navigation.recalculatePath();
                    this.setTarget(null);
                    this.world.sendEntityStatus(this, (byte)7);
                    setSit(true);
                }

                return ActionResult.SUCCESS;
            }
        }

        if(isTamed() && !this.world.isClient() && hand == Hand.MAIN_HAND) {
            setSit(!isSitting());
            return ActionResult.SUCCESS;
        }

        if (itemstack.getItem() == itemForTaming) {
            return ActionResult.PASS;
        }

        return super.interactMob(player, hand);
    }

    public void setSit(boolean sitting) {
        this.dataTracker.set(IS_SEATED, sitting);
        super.setSitting(sitting);
    }

    public boolean isSitting() {
        return this.dataTracker.get(IS_SEATED);
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
        this.playSound(SoundEvents.ENTITY_PIG_STEP, 0.15F, 1.0F);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}