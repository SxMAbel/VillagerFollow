package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRandomStrollThroughVillage extends Pathfinder {
   private int interval;

   public PathfinderRandomStrollThroughVillage(@NotNull Creature c) {
      this(c, 100);
   }

   public PathfinderRandomStrollThroughVillage(@NotNull Creature c, int interval) {
      super(c);
      this.interval = interval;
   }

   public int getInterval() {
      return this.interval;
   }

   public void setInterval(int interval) {
      this.interval = interval;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public String getInternalName() {
      return "PathfinderGoalNearestVillage";
   }
}
