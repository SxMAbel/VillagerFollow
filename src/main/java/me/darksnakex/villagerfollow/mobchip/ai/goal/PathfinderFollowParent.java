package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Animals;
import org.jetbrains.annotations.NotNull;

public final class PathfinderFollowParent extends Pathfinder implements SpeedModifier {
   private double speedMod;

   public PathfinderFollowParent(@NotNull Animals m) {
      this(m, 1.0D);
   }

   public PathfinderFollowParent(@NotNull Animals m, double speedMod) {
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
      return new Pathfinder.PathfinderFlag[0];
   }

   public String getInternalName() {
      return "PathfinderGoalFollowParent";
   }
}
