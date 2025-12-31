package me.darksnakex.villagerfollow.mobchip.bukkit.events.memory;

import me.darksnakex.villagerfollow.mobchip.EntityBrain;
import me.darksnakex.villagerfollow.mobchip.ai.memories.EntityMemory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MemoryChangeEvent extends MemoryEvent {
   private final Object oldV;
   private final Object newV;

   public MemoryChangeEvent(@NotNull EntityBrain brain, @NotNull EntityMemory<?> memory, @Nullable Object oldValue, @Nullable Object newValue) {
      super(brain, memory);
      this.oldV = oldValue;
      this.newV = newValue;
   }

   public Object getOldValue() {
      return this.oldV;
   }

   public Object getNewValue() {
      return this.newV;
   }
}
