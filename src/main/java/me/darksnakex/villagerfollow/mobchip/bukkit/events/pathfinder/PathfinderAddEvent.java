package me.darksnakex.villagerfollow.mobchip.bukkit.events.pathfinder;

import me.darksnakex.villagerfollow.mobchip.ai.EntityAI;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public class PathfinderAddEvent extends PathfinderEvent implements Cancellable {
   private boolean cancel;
   private final int priority;

   public PathfinderAddEvent(@NotNull EntityAI ai, @NotNull Pathfinder p, boolean target, int priority) {
      super(ai, p, target);
      this.priority = priority;
   }

   public int getPriority() {
      return this.priority;
   }

   public boolean isCancelled() {
      return this.cancel;
   }

   public void setCancelled(boolean cancel) {
      this.cancel = cancel;
   }
}
