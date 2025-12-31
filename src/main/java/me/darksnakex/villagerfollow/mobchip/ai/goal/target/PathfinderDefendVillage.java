package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import org.bukkit.entity.IronGolem;
import org.jetbrains.annotations.NotNull;

public final class PathfinderDefendVillage extends TargetPathfinder {
   public PathfinderDefendVillage(@NotNull IronGolem m) {
      super(m, false, true);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.TARGETING};
   }

   public String getInternalName() {
      return "PathfinderGoalDefendVillage";
   }
}
