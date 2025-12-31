package me.darksnakex.villagerfollow.mobchip.bosses;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.AttributeInstance;
import me.darksnakex.villagerfollow.mobchip.bosses.annotations.Repeatable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class Boss<T extends Mob> {
   public static final int DEFAULT_HEALTH = 20;
   @Nullable
   protected T mob;
   private static long idCounter = 0L;
   private static final Map<Long, Boss<?>> bosses = new HashMap();
   private Sound deathSound;
   private Sound spawnSound;
   private float volume;
   private float pitch;
   private double health;
   private final Map<EquipmentSlot, ItemStack> equipment;
   private final Map<AttributeInstance, Double> attributes;
   private final EntityType type;
   private final Plugin plugin;
   private final Class<T> mobClazz;
   private final BossHandler handler;
   protected final long id;

   protected Boss(@NotNull EntityType t, @NotNull Plugin plugin) throws IllegalArgumentException {
      this.plugin = plugin;

      try {
         this.mobClazz = t.getEntityClass().asSubclass(Mob.class);
      } catch (ClassCastException var4) {
         throw new IllegalArgumentException("Invalid EntityType: " + t.name());
      }

      this.type = t;
      this.mob = null;
      this.id = idCounter;
      bosses.put(this.id, this);
      this.deathSound = Sound.ENTITY_WITHER_DEATH;
      this.spawnSound = Sound.ENTITY_WITHER_SPAWN;
      this.handler = new BossHandler(this, plugin);
      ++idCounter;
   }

   @Nullable
   public static Boss<?> valueOf(long id) {
      Iterator var2 = bosses.entrySet().iterator();

      Entry entry;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         entry = (Entry)var2.next();
      } while((Long)entry.getKey() != id);

      return (Boss)entry.getValue();
   }

   @Nullable
   public static Boss<?> valueOf(@NotNull UUID uid) {
      Iterator var1 = bosses.values().iterator();

      Boss boss;
      do {
         if (!var1.hasNext()) {
            return null;
         }

         boss = (Boss)var1.next();
      } while(boss.getMob() == null || !boss.getMob().getUniqueId().equals(uid));

      return boss;
   }

   @Nullable
   public static <T extends Mob> Boss<T> valueOf(@NotNull T entity) {
      try {
         return valueOf(entity.getUniqueId());
      } catch (ClassCastException var2) {
         return null;
      }
   }

   @NotNull
   public final BossHandler getHandler() {
      return this.handler;
   }

   public final Class<T> getEntityClass() {
      return this.mobClazz;
   }

   public final EntityType getEntityType() {
      return this.type;
   }

   public final long getBossId() {
      return this.id;
   }

   public final void spawn(@NotNull Location l) {
      final T mob = (Mob)l.getWorld().spawn(l, this.getEntityClass());
      this.mob = mob;
      Iterator var3 = this.attributes.keySet().iterator();

      while(var3.hasNext()) {
         AttributeInstance a = (AttributeInstance)var3.next();
         a.setBaseValue((Double)this.attributes.get(a));
      }

      var3 = this.equipment.keySet().iterator();

      while(var3.hasNext()) {
         EquipmentSlot s = (EquipmentSlot)var3.next();
         switch(s) {
         case HAND:
            mob.getEquipment().setItemInMainHand((ItemStack)this.equipment.get(s));
            break;
         case OFF_HAND:
            mob.getEquipment().setItemInOffHand((ItemStack)this.equipment.get(s));
            break;
         case FEET:
            mob.getEquipment().setBoots((ItemStack)this.equipment.get(s));
            break;
         case LEGS:
            mob.getEquipment().setLeggings((ItemStack)this.equipment.get(s));
            break;
         case CHEST:
            mob.getEquipment().setChestplate((ItemStack)this.equipment.get(s));
            break;
         case HEAD:
            mob.getEquipment().setHelmet((ItemStack)this.equipment.get(s));
         }
      }

      if (this.spawnSound != null) {
         l.getWorld().playSound(l, this.deathSound, 3.0F, 1.0F);
      }

      final Boss inst = this;

      try {
         Method[] var12 = this.getClass().getDeclaredMethods();
         int var5 = var12.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            final Method m = var12[var6];
            m.setAccessible(true);
            if (m.getAnnotation(Repeatable.class) != null) {
               Repeatable r = (Repeatable)m.getAnnotation(Repeatable.class);
               (new BukkitRunnable() {
                  public void run() {
                     if (mob.isDead()) {
                        this.cancel();
                     } else {
                        StackTraceElement[] var2;
                        int var3;
                        int var4;
                        StackTraceElement s;
                        try {
                           m.invoke(inst);
                        } catch (InvocationTargetException var6) {
                           Bukkit.getLogger().severe(var6.getCause().getClass().getSimpleName());
                           Bukkit.getLogger().severe(var6.getCause().getMessage());
                           var2 = var6.getCause().getStackTrace();
                           var3 = var2.length;

                           for(var4 = 0; var4 < var3; ++var4) {
                              s = var2[var4];
                              Bukkit.getLogger().severe(s.toString());
                           }
                        } catch (Exception var7) {
                           Bukkit.getLogger().severe(var7.getMessage());
                           var2 = var7.getStackTrace();
                           var3 = var2.length;

                           for(var4 = 0; var4 < var3; ++var4) {
                              s = var2[var4];
                              Bukkit.getLogger().severe(s.toString());
                           }
                        }

                     }
                  }
               }).runTaskTimer(this.plugin, r.delay(), r.interval());
            }
         }
      } catch (Exception var9) {
      }

      CreatureSpawnEvent event = new CreatureSpawnEvent(mob, SpawnReason.CUSTOM);
      this.onSpawn(event);
   }

   @NotNull
   public final Sound getDeathSound() {
      return this.deathSound;
   }

   public final void setDeathSound(@Nullable Sound s) {
      this.deathSound = s == null ? Sound.ENTITY_WITHER_DEATH : s;
   }

   @NotNull
   public final Sound getSpawnSound() {
      return this.spawnSound;
   }

   public final void setSpawnSound(@Nullable Sound s) {
      if (s == null) {
         this.spawnSound = s;
      } else {
         this.spawnSound = Sound.ENTITY_WITHER_SPAWN;
      }

   }

   public final float getSoundPitch() {
      return this.pitch;
   }

   public final float getSoundVolume() {
      return this.volume;
   }

   public final void setSoundPitch(float pitch) throws IllegalArgumentException {
      if (!(pitch < 2.0F) && !(pitch > 2.0F)) {
         this.pitch = pitch;
      } else {
         throw new IllegalArgumentException("Pitch must be between -2F and 2F");
      }
   }

   public final void setSoundVolume(float volume) throws IllegalArgumentException {
      if (volume < 0.0F) {
         throw new IllegalArgumentException("Volume must be more than or equal to 0");
      } else {
         this.volume = volume;
      }
   }

   @Nullable
   public final T getMob() {
      return this.mob;
   }

   @NotNull
   public final Map<AttributeInstance, Double> getAttributes() {
      return this.attributes;
   }

   public final double getHealth() {
      return this.health;
   }

   public final void setHealth(double health) {
      this.health = health;
   }

   public final void addAttribute(@NotNull AttributeInstance inst, double value) {
      this.attributes.put(inst, value);
   }

   public final void removeAttribute(@NotNull AttributeInstance inst) {
      this.attributes.remove(inst);
   }

   @NotNull
   public final Map<EquipmentSlot, ItemStack> getEquipment() {
      return this.equipment;
   }

   public final void setItem(@NotNull EquipmentSlot slot, @Nullable ItemStack item) {
      if (item != null) {
         this.equipment.put(slot, item);
      }
   }

   public final void setHelmet(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.HEAD, item);
   }

   public final void setChestplate(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.CHEST, item);
   }

   public final void setLeggings(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.LEGS, item);
   }

   public final void setBoots(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.FEET, item);
   }

   public final void setMainhand(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.HAND, item);
   }

   public final void setOffhand(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.OFF_HAND, item);
   }

   public abstract List<ItemStack> getDrops();

   public void onSpawn(CreatureSpawnEvent e) {
   }

   public void onDeath(EntityDeathEvent death) {
   }

   public void onDamageDefensive(EntityDamageEvent e) {
   }

   public void onDamageOffensive(EntityDamageByEntityEvent e) {
   }
}
