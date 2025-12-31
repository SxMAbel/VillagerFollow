package me.darksnakex.villagerfollow.mobchip.bukkit.events.memory;

import me.darksnakex.villagerfollow.mobchip.EntityBrain;
import me.darksnakex.villagerfollow.mobchip.ai.memories.EntityMemory;
import me.darksnakex.villagerfollow.mobchip.bukkit.events.BrainEvent;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.NotNull;

public abstract class MemoryEvent extends BrainEvent implements Cancellable {
   private EntityMemory<?> memory;
   private boolean cancel;

   public MemoryEvent(@NotNull EntityBrain brain, EntityMemory<?> memory) {
      super(brain);
      this.memory = memory;
      this.cancel = false;
   }

   public boolean isCancelled() {
      return this.cancel;
   }

   public void setCancelled(boolean cancel) {
      this.cancel = cancel;
   }

   public EntityMemory<?> getMemory() {
      return this.memory;
   }

   public void setMemory(EntityMemory<?> memory) {
      this.memory = memory;
   }
}
