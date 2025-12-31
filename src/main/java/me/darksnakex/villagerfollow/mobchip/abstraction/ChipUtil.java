package me.darksnakex.villagerfollow.mobchip.abstraction;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import me.darksnakex.villagerfollow.mobchip.EntityBody;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.Attribute;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.AttributeInstance;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import me.darksnakex.villagerfollow.mobchip.ai.controller.EntityController;
import me.darksnakex.villagerfollow.mobchip.ai.enderdragon.CustomPhase;
import me.darksnakex.villagerfollow.mobchip.ai.enderdragon.DragonPhase;
import me.darksnakex.villagerfollow.mobchip.ai.goal.CustomPathfinder;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import me.darksnakex.villagerfollow.mobchip.ai.goal.WrappedPathfinder;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.EntityGossipContainer;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Memory;
import me.darksnakex.villagerfollow.mobchip.ai.memories.MemoryStatus;
import me.darksnakex.villagerfollow.mobchip.ai.navigation.EntityNavigation;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.EntityScheduleManager;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.Schedule;
import me.darksnakex.villagerfollow.mobchip.ai.sensing.EntitySenses;
import me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor;
import me.darksnakex.villagerfollow.mobchip.combat.EntityCombatTracker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Villager;
import org.bukkit.entity.EnderDragon.Phase;

public interface ChipUtil {
   String CLASS_TAG = "==";

   void addCustomPathfinder(CustomPathfinder var1, int var2, boolean var3);

   Set<WrappedPathfinder> getGoals(Mob var1, boolean var2);

   Collection<WrappedPathfinder> getRunningGoals(Mob var1, boolean var2);

   void setFlag(Mob var1, Pathfinder.PathfinderFlag var2, boolean var3, boolean var4);

   void addPathfinder(Pathfinder var1, int var2, boolean var3);

   void removePathfinder(Pathfinder var1, boolean var2);

   default void clearPathfinders(Mob mob, boolean target) {
      this.getGoals(mob, target).forEach((w) -> {
         this.removePathfinder(w.getPathfinder(), target);
      });
   }

   default void addPathfinders(Collection<WrappedPathfinder> c, boolean target) {
      Iterator var3 = c.iterator();

      while(var3.hasNext()) {
         WrappedPathfinder p = (WrappedPathfinder)var3.next();
         this.addPathfinder(p.getPathfinder(), p.getPriority(), target);
      }

   }

   BehaviorResult runBehavior(Mob var1, String var2, Object... var3);

   BehaviorResult runBehavior(Mob var1, String var2, String var3, Object... var4);

   EntityController getController(Mob var1);

   EntityNavigation getNavigation(Mob var1);

   EntityBody getBody(Mob var1);

   EntityScheduleManager getManager(Mob var1);

   EntityGossipContainer getGossipContainer(Villager var1);

   EntityCombatTracker getCombatTracker(Mob var1);

   void setCustomPhase(EnderDragon var1, CustomPhase var2);

   void knockback(EnderDragon var1, List<Entity> var2);

   <T> void setMemory(Mob var1, Memory<T> var2, T var3);

   void setMemory(Mob var1, String var2, Object var3);

   <T> void setMemory(Mob var1, Memory<T> var2, T var3, long var4);

   <T> T getMemory(Mob var1, Memory<T> var2);

   long getExpiry(Mob var1, Memory<?> var2);

   boolean contains(Mob var1, Memory<?> var2);

   void removeMemory(Mob var1, Memory<?> var2);

   boolean isRestricted(Mob var1);

   void clearRestriction(Mob var1);

   void restrictTo(Mob var1, double var2, double var4, double var6, int var8);

   Location getRestriction(Mob var1);

   int getRestrictionRadius(Mob var1);

   boolean hasRestriction(Mob var1);

   boolean canSee(Mob var1, Entity var2);

   Schedule getDefaultSchedule(String var1);

   Attribute registerAttribute(NamespacedKey var1, double var2, double var4, double var6, boolean var8);

   boolean existsAttribute(NamespacedKey var1);

   Attribute getAttribute(NamespacedKey var1);

   Attribute getDefaultAttribute(String var1);

   AttributeInstance getAttributeInstance(Mob var1, Attribute var2);

   DragonPhase fromBukkit(EnderDragon var1, Phase var2);

   DragonPhase getCurrentPhase(EnderDragon var1);

   void registerMemory(Memory<?> var1);

   boolean existsMemory(Memory<?> var1);

   void registerSensor(Sensor<?> var1);

   boolean existsSensor(NamespacedKey var1);

   Sensor<?> getSensor(NamespacedKey var1);

   EntitySenses getSenses(Mob var1);

   MemoryStatus getMemoryStatus(Mob var1, Memory<?> var2);

   EnderCrystal getNearestCrystal(EnderDragon var1);

   default void updateActivities(Creature c) {
   }

   default BehaviorResult hearNoteblock(Creature c, Location loc) {
      return BehaviorResult.STOPPED;
   }

   default BehaviorResult setDisturbanceLocation(Creature c, Location loc) {
      return BehaviorResult.STOPPED;
   }

   default void updateGoals(Map<Integer, Pathfinder> goals, Map<Integer, Boolean> target) {
      try {
         Iterator var3 = goals.entrySet().iterator();

         while(var3.hasNext()) {
            Entry<Integer, Pathfinder> e = (Entry)var3.next();
            this.addPathfinder((Pathfinder)e.getValue(), (Integer)e.getKey(), (Boolean)target.get(e.getKey()));
         }
      } catch (ClassCastException var5) {
         Bukkit.getLogger().severe("[MobChip] Invalid Projectile Source");
         printStackTrace(var5);
      }

   }

   static Class<?>[] getArgTypes(Object... args) {
      Class<?>[] types = new Class[args.length];

      for(int i = 0; i < args.length; ++i) {
         try {
            types[i] = (Class)args[i].getClass().getDeclaredField("TYPE").get((Object)null);
         } catch (ReflectiveOperationException var4) {
            types[i] = args[i].getClass();
         }
      }

      return types;
   }

   static String bukkitToCraftBukkit() {
      String bukkit = Bukkit.getServer().getBukkitVersion().split("-")[0];
      byte var2 = -1;
      switch(bukkit.hashCode()) {
      case 1505564:
         if (bukkit.equals("1.21")) {
            var2 = 6;
         }
         break;
      case 1446847518:
         if (bukkit.equals("1.20.1")) {
            var2 = 0;
         }
         break;
      case 1446847519:
         if (bukkit.equals("1.20.2")) {
            var2 = 1;
         }
         break;
      case 1446847520:
         if (bukkit.equals("1.20.3")) {
            var2 = 2;
         }
         break;
      case 1446847521:
         if (bukkit.equals("1.20.4")) {
            var2 = 3;
         }
         break;
      case 1446847522:
         if (bukkit.equals("1.20.5")) {
            var2 = 4;
         }
         break;
      case 1446847523:
         if (bukkit.equals("1.20.6")) {
            var2 = 5;
         }
         break;
      case 1446848479:
         if (bukkit.equals("1.21.1")) {
            var2 = 7;
         }
         break;
      case 1446848480:
         if (bukkit.equals("1.21.2")) {
            var2 = 8;
         }
         break;
      case 1446848481:
         if (bukkit.equals("1.21.3")) {
            var2 = 9;
         }
         break;
      case 1446848482:
         if (bukkit.equals("1.21.4")) {
            var2 = 10;
         }
         break;
      case 1446848483:
         if (bukkit.equals("1.21.5")) {
            var2 = 11;
         }
         break;
      case 1446848484:
         if (bukkit.equals("1.21.6")) {
            var2 = 12;
         }
         break;
      case 1446848485:
         if (bukkit.equals("1.21.7")) {
            var2 = 13;
         }
         break;
      case 1446848486:
         if (bukkit.equals("1.21.8")) {
            var2 = 14;
         }
         break;
      case 1446848487:
         if (bukkit.equals("1.21.9")) {
            var2 = 15;
         }
         break;
      case 1902629937:
         if (bukkit.equals("1.21.10")) {
            var2 = 16;
         }
      }

      switch(var2) {
      case 0:
         return "1_20_R1";
      case 1:
         return "1_20_R2";
      case 2:
      case 3:
         return "1_20_R3";
      case 4:
      case 5:
         return "1_20_R4";
      case 6:
      case 7:
         return "1_21_R1";
      case 8:
      case 9:
         return "1_21_R2";
      case 10:
         return "1_21_R3";
      case 11:
         return "1_21_R4";
      case 12:
      case 13:
      case 14:
         return "1_21_R5";
      case 15:
      case 16:
         return "1_21_R6";
      default:
         throw new IllegalStateException("Unsupported version: " + bukkit);
      }
   }

   static String getServerVersion() {
      try {
         return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);
      } catch (IndexOutOfBoundsException var1) {
         return bukkitToCraftBukkit();
      }
   }

   static ChipUtil getWrapper() {
      String pkg = ChipUtil.class.getPackage().getName() + ".v" + getServerVersion();

      try {
         Constructor<? extends ChipUtil> constr = Class.forName(pkg + ".ChipUtil" + getServerVersion()).asSubclass(ChipUtil.class).getDeclaredConstructor();
         constr.setAccessible(true);
         return (ChipUtil)constr.newInstance();
      } catch (ReflectiveOperationException var2) {
         throw new IllegalStateException("Invalid Version: " + getServerVersion() + " (Could not load " + pkg + ".ChipUtil" + getServerVersion() + ")", var2);
      }
   }

   static void printStackTrace(Throwable e) {
      Bukkit.getLogger().severe(e.getClass().getName() + ": " + e.getMessage());
      StackTraceElement[] var1 = e.getStackTrace();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         StackTraceElement s = var1[var3];
         Bukkit.getLogger().severe(s.toString());
      }

      if (e.getCause() != null) {
         Bukkit.getLogger().severe("Caused by:");
         printStackTrace(e.getCause());
      }

   }
}
