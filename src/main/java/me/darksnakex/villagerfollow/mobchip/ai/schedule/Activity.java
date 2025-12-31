package me.darksnakex.villagerfollow.mobchip.ai.schedule;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;

public enum Activity implements Keyed {
   CORE("core"),
   IDLE("idle"),
   WORK("work"),
   PLAY("play"),
   REST("rest"),
   HIDE("hide"),
   RAID("raid"),
   PRE_RAID("pre_raid");

   private final NamespacedKey key;

   private Activity(String param3) {
      this.key = NamespacedKey.minecraft(id);
   }

   public NamespacedKey getKey() {
      return this.key;
   }

   @Nullable
   public static Activity getByKey(@Nullable NamespacedKey key) {
      Activity[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Activity a = var1[var3];
         if (a.getKey().equals(key)) {
            return a;
         }
      }

      return null;
   }

   // $FF: synthetic method
   private static Activity[] $values() {
      return new Activity[]{CORE, IDLE, WORK, PLAY, REST, HIDE, RAID, PRE_RAID};
   }
}
