package me.darksnakex.villagerfollow.mobchip.util;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public final class Registration {
   private static String version = "unknown";

   private Registration() {
      throw new UnsupportedOperationException("This class cannot be instantiated.");
   }

   @NotNull
   public static String getVersion() {
      return version;
   }

   public static void setVersion(@NotNull String version) {
      Registration.version = version;
   }

   public static void usePlugin(@NotNull Plugin plugin) {
      setVersion(plugin.getDescription().getVersion());
   }
}
