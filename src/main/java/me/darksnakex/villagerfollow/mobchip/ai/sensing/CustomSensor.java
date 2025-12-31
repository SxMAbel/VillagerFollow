package me.darksnakex.villagerfollow.mobchip.ai.sensing;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public abstract class CustomSensor<T extends LivingEntity> implements Sensor<T> {
   private final int scanRate;
   private final Class<T> entityClass;
   private final NamespacedKey key;

   public CustomSensor(@NotNull Class<T> entityClass, @NotNull NamespacedKey key) {
      this(entityClass, key, 20);
   }

   public CustomSensor(@NotNull Class<T> entityClass, @NotNull NamespacedKey key, int scanRate) throws IllegalArgumentException {
      if (scanRate < 1) {
         throw new IllegalArgumentException("Scan Rate must be positive!");
      } else {
         this.scanRate = scanRate;
         this.entityClass = entityClass;
         this.key = key;
      }
   }

   public final int getScanRate() {
      return this.scanRate;
   }

   @NotNull
   public final Class<T> getEntityClass() {
      return this.entityClass;
   }

   public final NamespacedKey getKey() {
      return this.key;
   }
}
