package net.lily.fauna.entity.goals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.lily.fauna.entity.custom.CrabEntity;

public class CrabWaveGoal extends Goal {
    private final LivingEntity crab;
    private boolean isWaving;
    private int waveTimer;
    private int waveCooldown;
    private final double waveDistance;

    public CrabWaveGoal(LivingEntity crab, double waveDistance) {
        this.crab = crab;
        this.waveDistance = waveDistance;
    }

    @Override
    public boolean canStart() {
        if (waveCooldown > 0) {
            waveCooldown--;
            if (crab instanceof CrabEntity) {
                ((CrabEntity) crab).getDataTracker().set(CrabEntity.IS_WAVING, false);
            }
            return false;
        }

        if (crab.hasVehicle() || !crab.isOnGround() || crab.isTouchingWater()) {
            return false;
        }

        PlayerEntity nearestPlayer = crab.getWorld().getClosestPlayer(crab, waveDistance);
        return nearestPlayer != null && crab.squaredDistanceTo(nearestPlayer) < waveDistance * waveDistance;
    }

    @Override
    public void start() {
        waveTimer = 20;
    }

    @Override
    public void tick() {
        if (waveTimer > 0) {
            waveTimer--;
            if (crab instanceof CrabEntity) {
                ((CrabEntity) crab).getDataTracker().set(CrabEntity.IS_WAVING, true);
            }

            PlayerEntity nearestPlayer = crab.getWorld().getClosestPlayer(crab, waveDistance);
            if (nearestPlayer != null) {
            }
        } else {
            waveCooldown = crab.getRandom().nextInt(200) + 100;

            if (crab instanceof CrabEntity) {
                ((CrabEntity) crab).getDataTracker().set(CrabEntity.IS_WAVING, false);
            }
        }
    }
}
