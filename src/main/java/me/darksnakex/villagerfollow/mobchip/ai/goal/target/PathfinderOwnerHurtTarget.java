package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.NotNull;

public final class PathfinderOwnerHurtTarget extends TargetPathfinder {
   public PathfinderOwnerHurtTarget(@NotNull Tameable t) {
      super((Animals)t, false, false);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.TARGETING};
   }

   public String getInternalName() {
      return "PathfinderGoalOwnerHurtTarget";
   }
}
