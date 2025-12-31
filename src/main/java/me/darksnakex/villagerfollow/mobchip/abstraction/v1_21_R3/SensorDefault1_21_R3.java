package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Memory;
import me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityLiving;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

final class SensorDefault1_21_R3 implements Sensor<LivingEntity> {
   private final net.minecraft.world.entity.ai.sensing.Sensor<?> handle;

   public SensorDefault1_21_R3(net.minecraft.world.entity.ai.sensing.Sensor<?> handle) {
      this.handle = handle;
   }

   public net.minecraft.world.entity.ai.sensing.Sensor<?> getHandle() {
      return this.handle;
   }

   @NotNull
   public List<Memory<?>> required() {
      return (List)this.handle.a().stream().map(ChipUtil1_21_R3::fromNMS).collect(Collectors.toList());
   }

   public int getScanRate() {
      try {
         Field scan = net.minecraft.world.entity.ai.sensing.Sensor.class.getDeclaredField("j");
         scan.setAccessible(true);
         return scan.getInt(this.handle);
      } catch (ReflectiveOperationException var6) {
         Bukkit.getLogger().severe(var6.getMessage());
         StackTraceElement[] var2 = var6.getStackTrace();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            StackTraceElement st = var2[var4];
            Bukkit.getLogger().severe(st.toString());
         }

         return 20;
      }
   }

   @NotNull
   public Class<LivingEntity> getEntityClass() {
      return LivingEntity.class;
   }

   public void run(@NotNull World w, LivingEntity entity) {
      try {
         Method doTick = net.minecraft.world.entity.ai.sensing.Sensor.class.getDeclaredMethod("a", WorldServer.class, EntityLiving.class);
         doTick.setAccessible(true);
         doTick.invoke(this.handle, ChipUtil1_21_R3.toNMS(w), ChipUtil1_21_R3.toNMS(entity));
      } catch (ReflectiveOperationException var8) {
         Bukkit.getLogger().severe(var8.getMessage());
         StackTraceElement[] var4 = var8.getStackTrace();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            StackTraceElement st = var4[var6];
            Bukkit.getLogger().severe(st.toString());
         }
      }

   }

   @NotNull
   public NamespacedKey getKey() {
      AtomicReference<NamespacedKey> key = new AtomicReference(NamespacedKey.minecraft("unknown"));
      BuiltInRegistries.A.s().filter((s) -> {
         return s.a().equals(this.handle);
      }).findFirst().ifPresent((s) -> {
         key.set(ChipUtil1_21_R3.fromNMS(BuiltInRegistries.A.b(s)));
      });
      return (NamespacedKey)key.get();
   }
}
