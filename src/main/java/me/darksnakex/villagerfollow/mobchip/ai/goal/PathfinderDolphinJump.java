package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Dolphin;
import org.jetbrains.annotations.NotNull;

public final class PathfinderDolphinJump extends Pathfinder implements Repeated {
   private int interval;

   public PathfinderDolphinJump(@NotNull Dolphin d, int interval) throws IllegalArgumentException {
      super(d);
      if (interval < 0) {
         throw new IllegalArgumentException("Interval must be greater than 0");
      } else {
         this.interval = interval;
      }
   }

   public int getInterval() {
      return this.interval;
   }

   public void setInterval(int interval) throws IllegalArgumentException {
      if (interval < 0) {
         throw new IllegalArgumentException("Must be greater than 0");
      } else {
         this.interval = interval;
      }
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.JUMPING};
   }

   public String getInternalName() {
      return "PathfinderGoalWaterJump";
   }
}
