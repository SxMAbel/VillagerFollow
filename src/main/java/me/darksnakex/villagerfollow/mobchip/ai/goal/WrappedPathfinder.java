package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.jetbrains.annotations.Nullable;

public final class WrappedPathfinder {
   private final int priority;
   private final Pathfinder pathfinder;

   public WrappedPathfinder(@Nullable Pathfinder p, int priority) {
      this.pathfinder = p;
      this.priority = priority;
   }

   public int getPriority() {
      return this.priority;
   }

   @Nullable
   public Pathfinder getPathfinder() {
      return this.pathfinder;
   }
}
