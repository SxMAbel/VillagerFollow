package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderMoveTowardsTarget extends Pathfinder implements SpeedModifier, Ranged {
   public static final float DEFAULT_RANGE = 50.0F;
   private float range;
   private double speedMod;

   public PathfinderMoveTowardsTarget(@NotNull Creature c) {
      this(c, 1.5D);
   }

   public PathfinderMoveTowardsTarget(@NotNull Creature c, double speedMod) {
      this(c, speedMod, 50.0F);
   }

   public PathfinderMoveTowardsTarget(@NotNull Creature c, double speedMod, float range) {
      super(c);
      this.speedMod = speedMod;
      this.range = range;
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
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public String getInternalName() {
      return "PathfinderGoalMoveTowardsTarget";
   }
}
