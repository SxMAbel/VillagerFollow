package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderFleeSun extends Pathfinder implements SpeedModifier {
   private double speedMod;

   public PathfinderFleeSun(@NotNull Creature m) {
      this(m, 1.5D);
   }

   public PathfinderFleeSun(@NotNull Creature m, double speedMod) {
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
      return "PathfinderGoalFleeSun";
   }
}
