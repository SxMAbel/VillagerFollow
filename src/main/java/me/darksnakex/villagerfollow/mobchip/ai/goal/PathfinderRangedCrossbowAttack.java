package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Pillager;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRangedCrossbowAttack extends Pathfinder implements SpeedModifier, Ranged {
   private double speedMod;
   private float range;

   public PathfinderRangedCrossbowAttack(@NotNull Pillager m) {
      this(m, 1.5D);
   }

   public PathfinderRangedCrossbowAttack(@NotNull Pillager m, double speedMod) {
      this(m, speedMod, 6.0F);
   }

   public PathfinderRangedCrossbowAttack(@NotNull Pillager m, double speedMod, float range) {
      super(m);
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
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalCrossbowAttack";
   }
}
