package me.darksnakex.villagerfollow.mobchip.bukkit.events;

import me.darksnakex.villagerfollow.mobchip.EntityBrain;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public abstract class BrainEvent extends Event {
   private final EntityBrain brain;
   private static final HandlerList HANDLERS = new HandlerList();

   protected BrainEvent(@NotNull EntityBrain brain) {
      this.brain = brain;
   }

   protected BrainEvent(@NotNull EntityBrain brain, boolean async) {
      super(async);
      this.brain = brain;
   }

   public HandlerList getHandlers() {
      return HANDLERS;
   }

   public static HandlerList getHandlerList() {
      return HANDLERS;
   }

   public final EntityBrain getBrain() {
      return this.brain;
   }
}
