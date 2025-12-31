package me.darksnakex.villagerfollow.mobchip.bukkit.events.pathfinder;

import me.darksnakex.villagerfollow.mobchip.ai.EntityAI;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PathfinderClearEvent extends Event {
   private static final HandlerList HANDLERS = new HandlerList();
   private final EntityAI ai;
   private final boolean isTarget;

   public PathfinderClearEvent(@NotNull EntityAI ai, boolean target) {
      this.ai = ai;
      this.isTarget = target;
   }

   public HandlerList getHandlers() {
      return HANDLERS;
   }

   public static HandlerList getHandlerList() {
      return HANDLERS;
   }

   public EntityAI getAI() {
      return this.ai;
   }

   public boolean isTarget() {
      return this.isTarget;
   }
}
