package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.NotNull;

public final class PathfinderOwnerHurtByTarget extends TargetPathfinder {
   public PathfinderOwnerHurtByTarget(@NotNull Tameable m) {
      super((Animals)m, false, false);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.TARGETING};
   }

   public String getInternalName() {
      return "PathfinderGoalOwnerHurtByTarget";
   }
}
