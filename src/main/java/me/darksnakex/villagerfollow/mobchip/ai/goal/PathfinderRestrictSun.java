package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRestrictSun extends Pathfinder {
   public PathfinderRestrictSun(@NotNull Creature c) {
      super(c);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public String getInternalName() {
      return "PathfinderGoalRestrictSun";
   }
}
