package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderFloat extends Pathfinder {
   public PathfinderFloat(@NotNull Mob entity) {
      super(entity);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.JUMPING};
   }

   public String getInternalName() {
      return "PathfinderGoalFloat";
   }
}
