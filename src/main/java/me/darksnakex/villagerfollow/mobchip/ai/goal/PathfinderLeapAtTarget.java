package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderLeapAtTarget extends Pathfinder {
   private float height;

   public PathfinderLeapAtTarget(@NotNull Mob m) {
      this(m, 3.0F);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.JUMPING, Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public PathfinderLeapAtTarget(@NotNull Mob m, float y) {
      super(m);
      this.height = y;
   }

   public float getHeight() {
      return this.height;
   }

   public void setHeight(float height) {
      this.height = height;
   }

   public String getInternalName() {
      return "PathfinderGoalLeapAtTarget";
   }
}
