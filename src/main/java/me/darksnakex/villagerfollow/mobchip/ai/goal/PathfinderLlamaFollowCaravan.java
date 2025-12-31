package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Llama;
import org.jetbrains.annotations.NotNull;

public final class PathfinderLlamaFollowCaravan extends Pathfinder implements SpeedModifier {
   private double speedMod;

   public PathfinderLlamaFollowCaravan(@NotNull Llama m) {
      this(m, 1.0D);
   }

   public PathfinderLlamaFollowCaravan(@NotNull Llama m, double speedMod) {
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
      return "PathfinderGoalLlamaFollow";
   }
}
