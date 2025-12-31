package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Cat;
import org.jetbrains.annotations.NotNull;

public final class PathfinderCatOnBlock extends Pathfinder implements SpeedModifier {
   private double speedMod;

   public PathfinderCatOnBlock(@NotNull Cat cat, double speedMod) {
      super(cat);
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

   @NotNull
   public Cat getEntity() {
      return (Cat)this.entity;
   }

   public String getInternalName() {
      return "PathfinderGoalJumpOnBlock";
   }
}
