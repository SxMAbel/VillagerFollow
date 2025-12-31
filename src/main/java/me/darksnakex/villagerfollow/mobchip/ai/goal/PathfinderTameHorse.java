package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.AbstractHorse;
import org.jetbrains.annotations.NotNull;

public final class PathfinderTameHorse extends Pathfinder implements SpeedModifier {
   private double speedMod;

   public PathfinderTameHorse(@NotNull AbstractHorse m) {
      this(m, 1.0D);
   }

   public PathfinderTameHorse(@NotNull AbstractHorse m, double speedMod) {
      super(m);
      this.speedMod = speedMod;
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
      return "PathfinderGoalTame";
   }
}
