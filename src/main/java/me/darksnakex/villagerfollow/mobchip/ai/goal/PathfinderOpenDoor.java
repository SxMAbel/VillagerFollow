package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderOpenDoor extends Pathfinder {
   private boolean close;

   public PathfinderOpenDoor(@NotNull Mob m) {
      this(m, true);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public PathfinderOpenDoor(@NotNull Mob m, boolean close) {
      super(m);
      this.close = close;
   }

   public boolean mustClose() {
      return this.close;
   }

   public void setMustClose(boolean close) {
      this.close = close;
   }

   public String getInternalName() {
      return "PathfinderGoalDoorOpen";
   }
}
