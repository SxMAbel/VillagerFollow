package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.IronGolem;
import org.jetbrains.annotations.NotNull;

public final class PathfinderOfferFlower extends Pathfinder {
   public PathfinderOfferFlower(@NotNull IronGolem m) {
      super(m);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalOfferFlower";
   }
}
