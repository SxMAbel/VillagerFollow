package me.darksnakex.villagerfollow.mobchip.ai.animation;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum EntityAnimation implements Keyed {
   DAMAGE("hurt"),
   CRITICAL_DAMAGE("critical_hit"),
   MAGICAL_CRITICAL_DAMAGE("magic_critical_hit"),
   SPAWN("spawn");

   private final NamespacedKey key;

   private EntityAnimation(String param3) {
      this.key = NamespacedKey.minecraft(key);
   }

   @NotNull
   public NamespacedKey getKey() {
      return this.key;
   }

   @Nullable
   public static EntityAnimation getByKey(@Nullable NamespacedKey key) {
      if (key == null) {
         return null;
      } else {
         EntityAnimation[] var1 = values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            EntityAnimation a = var1[var3];
            if (a.getKey().equals(key)) {
               return a;
            }
         }

         return null;
      }
   }

   // $FF: synthetic method
   private static EntityAnimation[] $values() {
      return new EntityAnimation[]{DAMAGE, CRITICAL_DAMAGE, MAGICAL_CRITICAL_DAMAGE, SPAWN};
   }
}
