package me.darksnakex.villagerfollow.mobchip.ai.gossip;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.Nullable;

public enum GossipType implements Keyed {
   MAJOR_NEGATIVE("major_negative", -5, 100, 10, 10),
   MINOR_NEGATIVE("minor_negative", -1, 200, 20, 20),
   MINOR_POSITIVE("minor_positive", 1, 200, 1, 5),
   MAJOR_POSITIVE("major_positive", 5, 100, 0, 100),
   TRADING("trading", 1, 25, 2, 20);

   private final String type;
   private final int priority;
   private final int max;
   private final int dailyDecay;
   private final int transferDecay;

   private GossipType(String param3, int param4, int param5, int param6, int param7) {
      this.type = id;
      this.priority = priority;
      this.max = max;
      this.dailyDecay = dailyDecay;
      this.transferDecay = transferDecay;
   }

   public int getPriority() {
      return this.priority;
   }

   public int getDefaultMaxDecay() {
      return this.max;
   }

   public int getDailyDecay() {
      return this.dailyDecay;
   }

   public int getTransferDecay() {
      return this.transferDecay;
   }

   public NamespacedKey getKey() {
      return NamespacedKey.minecraft(this.type);
   }

   @Nullable
   public static GossipType getByKey(@Nullable NamespacedKey key) {
      if (key == null) {
         return null;
      } else {
         GossipType[] var1 = values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            GossipType type = var1[var3];
            if (type.getKey().equals(key)) {
               return type;
            }
         }

         return null;
      }
   }

   // $FF: synthetic method
   private static GossipType[] $values() {
      return new GossipType[]{MAJOR_NEGATIVE, MINOR_NEGATIVE, MINOR_POSITIVE, MAJOR_POSITIVE, TRADING};
   }
}
