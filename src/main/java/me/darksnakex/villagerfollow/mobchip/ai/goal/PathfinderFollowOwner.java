package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.NotNull;

public final class PathfinderFollowOwner extends Pathfinder implements SpeedModifier {
   private double speedMod;
   private float stopDist;
   private float startDist;
   private boolean canFly;

   public PathfinderFollowOwner(@NotNull Tameable t, float startDistance, float stopDistance) {
      this(t, 1.0D, startDistance, stopDistance);
   }

   public PathfinderFollowOwner(@NotNull Tameable t, double speedMod, float startDistance, float stopDistance) {
      this(t, speedMod, startDistance, stopDistance, true);
   }

   public PathfinderFollowOwner(@NotNull Tameable t, double speedMod, float startDistance, float stopDistance, boolean fly) {
      super(t instanceof Mob ? (Mob)t : null);
      this.speedMod = speedMod;
      this.stopDist = stopDistance;
      this.startDist = startDistance;
      this.canFly = fly;
   }

   public float getStartDistance() {
      return this.startDist;
   }

   public float getStopDistance() {
      return this.stopDist;
   }

   public void setStartDistance(float start) {
      this.startDist = start;
   }

   public void setStopDistance(float stop) {
      this.stopDist = stop;
   }

   public boolean canFly() {
      return this.canFly;
   }

   public void setCanFly(boolean fly) {
      this.canFly = fly;
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
      return "PathfinderGoalFollowOwner";
   }
}
