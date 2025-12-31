package me.darksnakex.villagerfollow.mobchip.ai.schedule;

import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DefaultSchedules {
   private static final ChipUtil wrapper = ChipUtil.getWrapper();
   public static final Schedule EMPTY;
   public static final Schedule SIMPLE;
   public static final Schedule VILLAGER;
   public static final Schedule BABY_VILLAGER;

   private DefaultSchedules() {
   }

   @NotNull
   public static Schedule get(@Nullable String id) {
      if (id == null) {
         return EMPTY;
      } else {
         Schedule s = wrapper.getDefaultSchedule(id);
         return s == null ? EMPTY : s;
      }
   }

   static {
      EMPTY = wrapper.getDefaultSchedule("empty");
      SIMPLE = wrapper.getDefaultSchedule("simple");
      VILLAGER = wrapper.getDefaultSchedule("villager_default");
      BABY_VILLAGER = wrapper.getDefaultSchedule("villager_baby");
   }
}
