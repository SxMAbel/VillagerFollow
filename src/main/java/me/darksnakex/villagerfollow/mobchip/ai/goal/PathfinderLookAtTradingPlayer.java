package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.AbstractVillager;
import org.jetbrains.annotations.NotNull;

public final class PathfinderLookAtTradingPlayer extends Pathfinder {
   public PathfinderLookAtTradingPlayer(@NotNull AbstractVillager m) {
      super(m);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalLookAtTradingPlayer";
   }
}
