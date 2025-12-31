package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderEatTile extends Pathfinder {
   public PathfinderEatTile(@NotNull Mob m) {
      super(m);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.JUMPING, Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalEatTile";
   }
}
