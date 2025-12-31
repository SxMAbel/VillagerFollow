package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.AbstractVillager;
import org.jetbrains.annotations.NotNull;

public final class PathfinderTradePlayer extends Pathfinder {
   public PathfinderTradePlayer(@NotNull AbstractVillager m) {
      super(m);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.JUMPING, Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public String getInternalName() {
      return "PathfinderGoalTradeWithPlayer";
   }
}
