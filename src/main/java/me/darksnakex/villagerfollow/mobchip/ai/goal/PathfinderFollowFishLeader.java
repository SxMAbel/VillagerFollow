package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Fish;
import org.jetbrains.annotations.NotNull;

public final class PathfinderFollowFishLeader extends Pathfinder {
   public PathfinderFollowFishLeader(@NotNull Fish f) {
      super(f);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public String getInternalName() {
      return "PathfinderGoalFishSchool";
   }
}
