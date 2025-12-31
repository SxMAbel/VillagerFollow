package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderFollowMob extends Pathfinder implements SpeedModifier, Ranged {
   private float range;
   private double speedMod;
   private float stopDistance;

   public PathfinderFollowMob(@NotNull Mob m) {
      this(m, 1.0F);
   }

   public PathfinderFollowMob(@NotNull Mob m, float stopDistance) {
      this(m, stopDistance, 5.0F);
   }

   public PathfinderFollowMob(@NotNull Mob m, float stopDistance, float lookRange) {
      this(m, 1.5D, stopDistance, lookRange);
   }

   public PathfinderFollowMob(@NotNull Mob m, double speedMod, float stopDistance, float lookRange) {
      super(m);
      this.range = lookRange;
      this.speedMod = speedMod;
      this.stopDistance = stopDistance;
   }

   public float getStopDistance() {
      return this.stopDistance;
   }

   public void setStopDistance(float stop) {
      this.stopDistance = stop;
   }

   public float getRange() {
      return this.range;
   }

   public void setRange(float range) {
      this.range = range;
   }

   public double getSpeedModifier() {
      return this.speedMod;
   }

   public void setSpeedModifier(double mod) {
      this.speedMod = mod;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalFollowEntity";
   }
}
