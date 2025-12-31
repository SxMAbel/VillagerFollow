package me.darksnakex.villagerfollow.mobchip;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import me.darksnakex.villagerfollow.mobchip.ai.EntityAI;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.Attribute;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.AttributeInstance;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.EntityBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.controller.EntityController;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Memory;
import me.darksnakex.villagerfollow.mobchip.ai.memories.MemoryStatus;
import me.darksnakex.villagerfollow.mobchip.ai.navigation.EntityNavigation;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.EntityScheduleManager;
import me.darksnakex.villagerfollow.mobchip.ai.sensing.EntitySenses;
import me.darksnakex.villagerfollow.mobchip.combat.EntityCombatTracker;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityBrain {
   @NotNull
   Mob getEntity();

   @NotNull
   EntityAI getGoalAI();

   @NotNull
   AttributeInstance getAttributeInstance(@NotNull Attribute var1);

   @NotNull
   EntityAI getTargetAI();

   @NotNull
   EntityNavigation createNavigation();

   @NotNull
   EntityController getController();

   @NotNull
   EntityScheduleManager getScheduleManager();

   EntityBehavior getBehaviors();

   <T> void setMemory(@NotNull Memory<T> var1, @Nullable T var2) throws IllegalArgumentException;

   <T> void setMemory(@NotNull Memory<T> var1, @Nullable T var2, long var3) throws IllegalArgumentException;

   default void setMemories(@NotNull Map<Memory, ?> map) {
      Iterator var2 = map.entrySet().iterator();

      while(var2.hasNext()) {
         Entry<Memory, ?> entry = (Entry)var2.next();
         this.setMemory((Memory)entry.getKey(), entry.getValue());
      }

   }

   default void setMemories(@NotNull Map<Memory, ?> map, long expire) {
      Iterator var4 = map.entrySet().iterator();

      while(var4.hasNext()) {
         Entry<Memory, ?> entry = (Entry)var4.next();
         this.setMemory((Memory)entry.getKey(), entry.getValue(), expire);
      }

   }

   @Nullable
   <T> T getMemory(@NotNull Memory<T> var1);

   long getExpiration(@NotNull Memory<?> var1);

   boolean containsMemory(@NotNull Memory<?> var1);

   void removeMemory(@NotNull Memory<?> var1);

   MemoryStatus getMemoryStatus(@NotNull Memory<?> var1);

   default boolean containsAllMemories(@NotNull Memory<?>... memories) {
      boolean contains = true;
      Memory[] var3 = memories;
      int var4 = memories.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Memory m = var3[var5];
         if (!this.containsMemory(m)) {
            contains = false;
            break;
         }
      }

      return contains;
   }

   boolean isInRestriction();

   void setRestrictionArea(Location var1, int var2);

   void clearRestrictionArea();

   Location getRestrictionArea();

   boolean hasRestriction();

   int getRestrictionRadius();

   boolean canSee(@Nullable Entity var1);

   default boolean canSee(@Nullable EntityBrain brain) {
      return brain == null ? false : this.canSee((Entity)brain.getEntity());
   }

   @NotNull
   EntityBody getBody();

   @NotNull
   EntityCombatTracker getCombatTracker();

   @NotNull
   EntitySenses getSenses();
}
