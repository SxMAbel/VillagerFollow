package me.darksnakex.villagerfollow.mobchip.ai.schedule;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityScheduleManager {
   @Nullable
   Schedule getCurrentSchedule();

   void setSchedule(@NotNull Schedule var1);

   @NotNull
   Set<Activity> getActiveActivities();

   void setDefaultActivity(@NotNull Activity var1);

   void useDefaultActivity();

   void setRunningActivity(@NotNull Activity var1);

   @Nullable
   Activity getRunningActivity();

   boolean isRunning(@NotNull Activity var1);

   @Nullable
   Consumer<Mob> put(@NotNull Activity var1, Consumer<Mob> var2);

   boolean isEmpty();

   int size();

   default void putAll(Map<Activity, Consumer<Mob>> map) {
      map.forEach(this::put);
   }

   void clear();
}
