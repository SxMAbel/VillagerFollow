package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderMoveTowardsRestriction extends Pathfinder implements SpeedModifier {
   private double speedMod;

   public PathfinderMoveTowardsRestriction(@NotNull Creature c) {
      this(c, 1.0D);
   }

   public PathfinderMoveTowardsRestriction(@NotNull Creature c, double speedMod) {
      super(c);
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
      return "PathfinderGoalMoveTowardsRestriction";
   }
}
