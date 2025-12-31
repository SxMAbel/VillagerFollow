package me.darksnakex.villagerfollow.mobchip.abstraction.v1_18_R2;

import com.google.common.collect.ImmutableList;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import me.darksnakex.villagerfollow.mobchip.ai.sensing.EntitySenses;
import net.minecraft.core.IRegistry;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.ai.BehaviorController;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

final class EntitySenses1_18_R2 implements EntitySenses {
   private final Mob m;
   private final EntityInsentient nmsMob;
   private final Map<SensorType<?>, Sensor<?>> sensorsHandle = new HashMap();

   public EntitySenses1_18_R2(Mob m) {
      this.m = m;
      this.nmsMob = ChipUtil1_18_R2.toNMS(m);

      try {
         Field sensorsF = BehaviorController.class.getDeclaredField("e");
         sensorsF.setAccessible(true);
         Map<SensorType<?>, Sensor<?>> sensors = (Map)sensorsF.get(this.nmsMob.du());
         this.sensorsHandle.putAll(sensors);
      } catch (ReflectiveOperationException var7) {
         Bukkit.getLogger().severe(var7.getMessage());
         StackTraceElement[] var3 = var7.getStackTrace();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            StackTraceElement st = var3[var5];
            Bukkit.getLogger().severe(st.toString());
         }
      }

   }

   @NotNull
   public Mob getEntity() {
      return this.m;
   }

   private void save() {
      try {
         Field sensorsF = BehaviorController.class.getDeclaredField("e");
         sensorsF.setAccessible(true);
         sensorsF.set(this.nmsMob.du(), this.sensorsHandle);
      } catch (ReflectiveOperationException var6) {
         Bukkit.getLogger().severe(var6.getMessage());
         StackTraceElement[] var2 = var6.getStackTrace();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            StackTraceElement st = var2[var4];
            Bukkit.getLogger().severe(st.toString());
         }
      }

   }

   @NotNull
   public List<me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?>> getSensors() {
      return (List)this.sensorsHandle.values().stream().map(ChipUtil1_18_R2::fromNMS).collect(Collectors.toList());
   }

   public void addSensor(@NotNull me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> sensor) throws IllegalArgumentException {
      if (!(new ChipUtil1_18_R2()).existsSensor(sensor.getKey())) {
         throw new IllegalArgumentException("Unregistered Sensor: " + String.valueOf(sensor.getKey()));
      } else {
         this.sensorsHandle.put(ChipUtil1_18_R2.toNMSType(sensor), ChipUtil1_18_R2.toNMS(sensor));
         this.save();
      }
   }

   public void removeSensor(@NotNull me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> sensor) {
      if (!(new ChipUtil1_18_R2()).existsSensor(sensor.getKey())) {
         throw new IllegalArgumentException("Unregistered Sensor: " + String.valueOf(sensor.getKey()));
      } else {
         SensorType<?> type = ChipUtil1_18_R2.toNMSType(sensor);
         MinecraftKey key = IRegistry.aq.b(type);
         Iterator it = this.sensorsHandle.entrySet().iterator();

         while(it.hasNext()) {
            Entry<SensorType<?>, Sensor<?>> entry = (Entry)it.next();
            SensorType<?> currentType = (SensorType)entry.getKey();
            MinecraftKey currentKey = IRegistry.aq.b(currentType);
            if (currentKey.equals(key)) {
               it.remove();
               break;
            }
         }

         this.sensorsHandle.clear();
         this.sensorsHandle.putAll((Map)ImmutableList.copyOf(it).stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
         this.save();
      }
   }

   public void removeSensor(@NotNull NamespacedKey key) {
      if (!(new ChipUtil1_18_R2()).existsSensor(key)) {
         throw new IllegalArgumentException("Unregistered Sensor: " + String.valueOf(key));
      } else {
         MinecraftKey keyH = ChipUtil1_18_R2.toNMS(key);
         Iterator it = this.sensorsHandle.entrySet().iterator();

         while(it.hasNext()) {
            Entry<SensorType<?>, Sensor<?>> entry = (Entry)it.next();
            SensorType<?> currentType = (SensorType)entry.getKey();
            MinecraftKey currentKey = IRegistry.aq.b(currentType);
            if (currentKey.equals(keyH)) {
               it.remove();
               break;
            }
         }

         this.sensorsHandle.clear();
         this.sensorsHandle.putAll((Map)ImmutableList.copyOf(it).stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue)));
         this.save();
      }
   }

   public boolean hasSensor(@NotNull NamespacedKey key) {
      AtomicBoolean b = new AtomicBoolean(false);
      Iterator var3 = this.sensorsHandle.keySet().iterator();

      while(var3.hasNext()) {
         SensorType<?> t = (SensorType)var3.next();
         MinecraftKey currentKey = IRegistry.aq.b(t);
         if (ChipUtil1_18_R2.toNMS(key).equals(currentKey)) {
            b.set(true);
            break;
         }
      }

      return b.get();
   }
}
