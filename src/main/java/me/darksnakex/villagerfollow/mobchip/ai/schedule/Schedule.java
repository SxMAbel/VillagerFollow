package me.darksnakex.villagerfollow.mobchip.ai.schedule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Schedule {
   private final Map<Integer, Activity> timeline;

   private Schedule(Map<Integer, Activity> timeline) {
      this.timeline = timeline;
   }

   public static Schedule.Builder builder() {
      return new Schedule.Builder();
   }

   public int size() {
      return this.timeline.size();
   }

   public boolean contains(int key) {
      return this.timeline.containsKey(key);
   }

   public boolean contains(@NotNull Activity value) {
      return this.timeline.containsValue(value);
   }

   @Nullable
   public Activity get(int key) {
      return (Activity)this.timeline.get(key);
   }

   @NotNull
   public Set<Integer> keySet() {
      return this.timeline.keySet();
   }

   @NotNull
   public Collection<Activity> values() {
      return this.timeline.values();
   }

   @NotNull
   public Set<Entry<Integer, Activity>> entrySet() {
      return this.timeline.entrySet();
   }

   // $FF: synthetic method
   Schedule(Map x0, Object x1) {
      this(x0);
   }

   public static final class Builder {
      private final Map<Integer, Activity> map;

      private Builder() {
         this.map = new HashMap();
      }

      public Schedule.Builder addActivity(int time, @NotNull Activity activity) throws IllegalArgumentException {
         if (time >= 0 && time <= 24000) {
            if (activity == null) {
               throw new IllegalArgumentException("Activity cannot be null");
            } else {
               this.map.put(time, activity);
               return this;
            }
         } else {
            throw new IllegalArgumentException("Time must be between 0 and 24000");
         }
      }

      @NotNull
      public Map<Integer, Activity> getTimeline() {
         return this.map;
      }

      @NotNull
      public Schedule build() throws IllegalArgumentException {
         if (this.map.isEmpty()) {
            throw new IllegalArgumentException("Timeline cannot be empty");
         } else {
            return new Schedule(this.map);
         }
      }

      // $FF: synthetic method
      Builder(Object x0) {
         this();
      }
   }
}
