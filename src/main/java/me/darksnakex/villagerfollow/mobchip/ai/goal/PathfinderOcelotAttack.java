package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderOcelotAttack extends Pathfinder {
   public PathfinderOcelotAttack(@NotNull Mob m) {
      super(m);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalOcelotAttack";
   }
}
