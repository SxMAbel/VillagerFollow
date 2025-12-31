package me.darksnakex.villagerfollow.mobchip.bukkit;

import java.lang.reflect.Constructor;
import me.darksnakex.villagerfollow.mobchip.DragonBrain;
import me.darksnakex.villagerfollow.mobchip.EntityBody;
import me.darksnakex.villagerfollow.mobchip.EntityBrain;
import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import me.darksnakex.villagerfollow.mobchip.ai.EntityAI;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.Attribute;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.AttributeInstance;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.AllayBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.AxolotlBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.CamelBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.DragonBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.EntityBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.FrogBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.PiglinBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.WardenBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.controller.EntityController;
import me.darksnakex.villagerfollow.mobchip.ai.memories.EntityMemory;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Memory;
import me.darksnakex.villagerfollow.mobchip.ai.memories.MemoryStatus;
import me.darksnakex.villagerfollow.mobchip.ai.navigation.EntityNavigation;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.EntityScheduleManager;
import me.darksnakex.villagerfollow.mobchip.ai.sensing.EntitySenses;
import me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor;
import me.darksnakex.villagerfollow.mobchip.bukkit.events.RestrictionSetEvent;
import me.darksnakex.villagerfollow.mobchip.bukkit.events.memory.MemoryChangeEvent;
import me.darksnakex.villagerfollow.mobchip.combat.EntityCombatTracker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitBrain implements EntityBrain {
   private static final String BUKKIT_PACKAGE = BukkitBrain.class.getPackage().getName() + ".";
   final Mob m;
   static final ChipUtil w = ChipUtil.getWrapper();

   BukkitBrain(@NotNull Mob m) {
      this.m = m;
   }

   @Nullable
   public static EntityBrain getBrain(@Nullable Mob m) {
      if (m == null) {
         return null;
      } else {
         return (EntityBrain)(m instanceof Villager ? new BukkitVillagerBrain((Villager)m) : new BukkitBrain(m));
      }
   }

   @Nullable
   public static DragonBrain getBrain(@Nullable EnderDragon d) {
      return d == null ? null : new BukkitDragonBrain(d);
   }

   public static void registerSensor(@NotNull Sensor<?> sensor) throws IllegalArgumentException {
      if (isSensorRegistered(sensor.getKey())) {
         throw new IllegalArgumentException("Sensor is already registered with key: " + sensor.getKey());
      } else {
         w.registerSensor(sensor);
      }
   }

   public static boolean isSensorRegistered(@NotNull Sensor<?> sensor) {
      return isSensorRegistered(sensor.getKey());
   }

   public static boolean isSensorRegistered(@NotNull NamespacedKey key) {
      return w.existsSensor(key);
   }

   @Nullable
   public static Sensor<?> getRegisteredSensor(@NotNull NamespacedKey key) {
      return !isSensorRegistered(key) ? null : w.getSensor(key);
   }

   @NotNull
   public EntityAI getGoalAI() {
      return new BukkitAI(this.m, false);
   }

   @NotNull
   public AttributeInstance getAttributeInstance(@NotNull Attribute a) {
      return w.getAttributeInstance(this.m, a);
   }

   @NotNull
   public EntityAI getTargetAI() {
      return new BukkitAI(this.m, true);
   }

   @NotNull
   public EntityNavigation createNavigation() {
      return w.getNavigation(this.m);
   }

   @NotNull
   public EntityController getController() {
      return w.getController(this.m);
   }

   @NotNull
   public EntityScheduleManager getScheduleManager() {
      return w.getManager(this.m);
   }

   public EntityBehavior getBehaviors() {
      try {
         Constructor<? extends EntityBehavior> constr = null;
         Class<?> entityClass = this.m.getType().getEntityClass();
         String var3 = this.m.getType().name().toLowerCase();
         byte var4 = -1;
         switch(var3.hashCode()) {
         case -2082881686:
            if (var3.equals("ender_dragon")) {
               var4 = 2;
            }
            break;
         case -988357053:
            if (var3.equals("piglin")) {
               var4 = 4;
            }
            break;
         case -795021275:
            if (var3.equals("warden")) {
               var4 = 1;
            }
            break;
         case -565119405:
            if (var3.equals("axolotl")) {
               var4 = 3;
            }
            break;
         case 3151780:
            if (var3.equals("frog")) {
               var4 = 0;
            }
            break;
         case 92905881:
            if (var3.equals("allay")) {
               var4 = 5;
            }
            break;
         case 94426294:
            if (var3.equals("camel")) {
               var4 = 6;
            }
         }

         Class camel;
         switch(var4) {
         case 0:
            camel = Class.forName(BUKKIT_PACKAGE + "BukkitFrogBehavior").asSubclass(FrogBehavior.class);
            constr = camel.getDeclaredConstructor(entityClass);
            break;
         case 1:
            camel = Class.forName(BUKKIT_PACKAGE + "BukkitWardenBehavior").asSubclass(WardenBehavior.class);
            constr = camel.getDeclaredConstructor(entityClass);
            break;
         case 2:
            camel = Class.forName(BUKKIT_PACKAGE + "BukkitDragonBehavior").asSubclass(DragonBehavior.class);
            constr = camel.getDeclaredConstructor(entityClass);
            break;
         case 3:
            camel = Class.forName(BUKKIT_PACKAGE + "BukkitAxolotlBehavior").asSubclass(AxolotlBehavior.class);
            constr = camel.getDeclaredConstructor(entityClass);
            break;
         case 4:
            camel = Class.forName(BUKKIT_PACKAGE + "BukkitPiglinBehavior").asSubclass(PiglinBehavior.class);
            constr = camel.getDeclaredConstructor(entityClass);
            break;
         case 5:
            camel = Class.forName(BUKKIT_PACKAGE + "BukkitAllayBehavior").asSubclass(AllayBehavior.class);
            constr = camel.getDeclaredConstructor(entityClass);
            break;
         case 6:
            camel = Class.forName(BUKKIT_PACKAGE + "BukkitCamelBehavior").asSubclass(CamelBehavior.class);
            constr = camel.getDeclaredConstructor(entityClass);
         }

         constr.setAccessible(true);
         return (EntityBehavior)constr.newInstance(this.m);
      } catch (NoSuchMethodException | ClassNotFoundException var6) {
      } catch (Exception var7) {
         ChipUtil.printStackTrace(var7);
      }

      if (this.m instanceof Villager) {
         return new BukkitVillagerBehavior((Villager)this.m);
      } else {
         return (EntityBehavior)(this.m instanceof Creature ? new BukkitCreatureBehavior((Creature)this.m) : new BukkitEntityBehavior(this.m));
      }
   }

   @NotNull
   public EntityBody getBody() {
      return w.getBody(this.m);
   }

   @NotNull
   public EntityCombatTracker getCombatTracker() {
      return w.getCombatTracker(this.m);
   }

   @NotNull
   public EntitySenses getSenses() {
      return w.getSenses(this.m);
   }

   public <T> void setMemory(@NotNull Memory<T> memory, @Nullable T value) throws IllegalArgumentException {
      if (value == null) {
         this.removeMemory(memory);
      }

      Object old = this.getMemory(memory);
      w.setMemory(this.m, memory, value);
      MemoryChangeEvent event = new MemoryChangeEvent(this, (EntityMemory)memory, old, value);
      Bukkit.getPluginManager().callEvent(event);
   }

   public <T> void setMemory(@NotNull Memory<T> memory, @Nullable T value, long expire) throws IllegalArgumentException {
      if (value == null) {
         this.removeMemory(memory);
      }

      Object old = this.getMemory(memory);
      w.setMemory(this.m, memory, value, expire);
      MemoryChangeEvent event = new MemoryChangeEvent(this, (EntityMemory)memory, old, value);
      Bukkit.getPluginManager().callEvent(event);
   }

   @Nullable
   public <T> T getMemory(@NotNull Memory<T> memory) {
      return w.getMemory(this.m, memory);
   }

   public long getExpiration(@NotNull Memory<?> memory) {
      return w.getExpiry(this.m, memory);
   }

   public boolean containsMemory(@NotNull Memory<?> memory) {
      return w.contains(this.m, memory);
   }

   public void removeMemory(@NotNull Memory<?> memory) {
      w.removeMemory(this.m, memory);
   }

   public MemoryStatus getMemoryStatus(@NotNull Memory<?> memory) {
      return w.getMemoryStatus(this.m, memory);
   }

   public boolean isInRestriction() {
      return w.isRestricted(this.m);
   }

   public void setRestrictionArea(Location center, int radius) {
      RestrictionSetEvent event = new RestrictionSetEvent(this, this.getRestrictionArea(), center, this.getRestrictionRadius(), radius);
      Bukkit.getPluginManager().callEvent(event);
      Location newCenter = event.getNewCenter();
      int newRadius = event.getNewRadius();
      w.restrictTo(this.m, newCenter.getX(), newCenter.getY(), newCenter.getZ(), newRadius);
   }

   public void clearRestrictionArea() {
      w.clearRestriction(this.m);
   }

   public Location getRestrictionArea() {
      return w.getRestriction(this.m);
   }

   public boolean hasRestriction() {
      return w.hasRestriction(this.m);
   }

   public int getRestrictionRadius() {
      return w.getRestrictionRadius(this.m);
   }

   public boolean canSee(@Nullable Entity en) {
      return w.canSee(this.m, en);
   }

   @NotNull
   public Mob getEntity() {
      return this.m;
   }
}
