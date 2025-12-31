package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.SkeletonHorse;
import org.jetbrains.annotations.NotNull;

public final class PathfinderSkeletonTrap extends Pathfinder {
   public PathfinderSkeletonTrap(@NotNull SkeletonHorse horse) {
      super(horse);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public String getInternalName() {
      return "PathfinderGoalHorseTrap";
   }
}
