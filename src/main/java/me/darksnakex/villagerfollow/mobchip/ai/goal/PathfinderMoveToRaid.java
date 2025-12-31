package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Raider;
import org.jetbrains.annotations.NotNull;

public final class PathfinderMoveToRaid extends Pathfinder {
   public PathfinderMoveToRaid(@NotNull Raider m) {
      super(m);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public String getInternalName() {
      return "PathfinderGoalRaid";
   }
}
