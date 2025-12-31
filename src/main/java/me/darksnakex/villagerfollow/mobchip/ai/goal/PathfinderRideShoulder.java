package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Parrot;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRideShoulder extends Pathfinder {
   public PathfinderRideShoulder(@NotNull Parrot p) {
      super(p);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public String getInternalName() {
      return "PathfinderGoalPerch";
   }
}
