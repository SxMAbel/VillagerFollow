package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Creeper;
import org.jetbrains.annotations.NotNull;

public final class PathfinderSwellCreeper extends Pathfinder {
   public PathfinderSwellCreeper(@NotNull Creeper m) {
      super(m);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public String getInternalName() {
      return "PathfinderGoalSwell";
   }
}
