package me.darksnakex.villagerfollow.mobchip.ai.sensing;

import java.util.List;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Memory;
import org.bukkit.Keyed;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface Sensor<T extends LivingEntity> extends Keyed {
   int DEFAULT_SCAN_RATE = 20;

   @NotNull
   List<Memory<?>> required();

   int getScanRate();

   @NotNull
   Class<T> getEntityClass();

   void run(@NotNull World var1, LivingEntity var2);
}
