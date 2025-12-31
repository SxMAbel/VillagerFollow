package me.darksnakex.villagerfollow.interactions;

import java.lang.reflect.Method;
import me.darksnakex.villagerfollow.VillagerFollow;
import me.darksnakex.villagerfollow.mobchip.ai.controller.EntityController;
import me.darksnakex.villagerfollow.mobchip.bukkit.BukkitBrain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Villager;

public class VillagerInteraction {
   public static void followThing(Villager villager, Location location, double velocidad) {
      double locvil = villager.getLocation().distance(location);
      if (locvil > 25.0D) {
         teleportToNearestGround(villager, location);
      } else if (!(locvil < 3.0D)) {
         if (!VillagerFollow.isSpigot) {
            try {
               Class<?> paperPathfinderClass = Class.forName("com.destroystokyo.paper.entity.PaperPathfinder");
               Method getPathfinderMethod = villager.getClass().getMethod("getPathfinder");
               Object pathfinder = getPathfinderMethod.invoke(villager);
               Method moveToMethod = paperPathfinderClass.getMethod("moveTo", Location.class, Double.TYPE);
               moveToMethod.invoke(pathfinder, location, velocidad);
            } catch (ClassNotFoundException var10) {
               Bukkit.getLogger().warning("Class PaperPathfinder not found. If you see this please report it");
            } catch (NoSuchMethodException var11) {
               Bukkit.getLogger().warning("Method moveTo not found. If you see this please report it");
            } catch (Exception var12) {
               Bukkit.getLogger().warning("Unknown error. If you see this please report it");
               System.out.println(var12.getMessage());
            }
         } else {
            EntityController controller = BukkitBrain.getBrain((Mob)villager).getController();
            controller.moveTo(location, velocidad);
            controller.lookAt(location);
         }
      }

   }

   private static void teleportToNearestGround(Villager villager, Location location) {
      Location groundLocation = location.clone();

      while(!groundLocation.getBlock().getType().isSolid()) {
         groundLocation.subtract(0.0D, 1.0D, 0.0D);
         if (groundLocation.getY() < 0.0D) {
            break;
         }
      }

      villager.teleport(groundLocation.add(0.0D, 1.0D, 0.0D));
   }
}
