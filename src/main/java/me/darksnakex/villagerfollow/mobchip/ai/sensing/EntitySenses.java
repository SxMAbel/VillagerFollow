package me.darksnakex.villagerfollow.mobchip.ai.sensing;

import java.util.List;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public interface EntitySenses {
   @NotNull
   Mob getEntity();

   @NotNull
   List<Sensor<?>> getSensors();

   void addSensor(@NotNull Sensor<?> var1) throws IllegalArgumentException;

   void removeSensor(@NotNull Sensor<?> var1);

   void removeSensor(@NotNull NamespacedKey var1);

   boolean hasSensor(@NotNull NamespacedKey var1);
}
