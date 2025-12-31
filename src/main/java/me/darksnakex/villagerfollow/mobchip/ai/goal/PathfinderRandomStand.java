package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.AbstractHorse;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRandomStand extends Pathfinder {
   public PathfinderRandomStand(@NotNull AbstractHorse m) {
      super(m);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public String getInternalName() {
      return "RandomStandGoal";
   }
}
