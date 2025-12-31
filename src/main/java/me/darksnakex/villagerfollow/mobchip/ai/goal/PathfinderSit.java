package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.NotNull;

public final class PathfinderSit extends Pathfinder {
   public PathfinderSit(@NotNull Tameable m) {
      super((Animals)m);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.JUMPING};
   }

   public String getInternalName() {
      return "PathfinderGoalSit";
   }
}
