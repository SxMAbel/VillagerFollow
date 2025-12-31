package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderBreathAir extends Pathfinder {
   public PathfinderBreathAir(@NotNull Creature c) {
      super(c);
   }

   @NotNull
   public Creature getEntity() {
      return (Creature)this.entity;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalBreath";
   }
}
