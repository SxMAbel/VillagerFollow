package me.darksnakex.villagerfollow.mobchip.combat;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum CombatLocation implements Keyed {
   VINES("vines"),
   SCAFFOLDING("scaffolding"),
   OTHER_CLIMBABLE("other_climbable"),
   TWISTING_VINES("twisting_vines"),
   WEEPING_VINES("weeping_vines"),
   LADDER("ladder"),
   WATER("water");

   private final String key;

   private CombatLocation(String param3) {
      this.key = id;
   }

   @NotNull
   public NamespacedKey getKey() {
      return NamespacedKey.minecraft(this.key);
   }

   @Nullable
   public static CombatLocation getByKey(@Nullable NamespacedKey key) {
      CombatLocation[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         CombatLocation l = var1[var3];
         if (l.key.equals(key.getKey())) {
            return l;
         }
      }

      return null;
   }

   // $FF: synthetic method
   private static CombatLocation[] $values() {
      return new CombatLocation[]{VINES, SCAFFOLDING, OTHER_CLIMBABLE, TWISTING_VINES, WEEPING_VINES, LADDER, WATER};
   }
}
