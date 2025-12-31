package me.darksnakex.villagerfollow.mobchip.bukkit.events.pathfinder;

import me.darksnakex.villagerfollow.mobchip.ai.EntityAI;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.Nullable;

public abstract class PathfinderEvent extends Event {
   private final EntityAI ai;
   private final Pathfinder pathfinder;
   private final boolean isTarget;
   private static final HandlerList HANDLERS = new HandlerList();

   public PathfinderEvent(@Nullable EntityAI ai, @Nullable Pathfinder pathfinder, boolean target) {
      this.ai = ai;
      this.pathfinder = pathfinder;
      this.isTarget = target;
   }

   public HandlerList getHandlers() {
      return HANDLERS;
   }

   public static HandlerList getHandlerList() {
      return HANDLERS;
   }

   @Nullable
   public EntityAI getAI() {
      return this.ai;
   }

   public boolean isTarget() {
      return this.isTarget;
   }

   @Nullable
   public Pathfinder getPathfinder() {
      return this.pathfinder;
   }
}
