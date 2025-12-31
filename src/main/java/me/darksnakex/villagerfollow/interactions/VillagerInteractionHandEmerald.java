package me.darksnakex.villagerfollow.interactions;

import java.util.Iterator;
import me.darksnakex.villagerfollow.VillagerFollow;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class VillagerInteractionHandEmerald implements Listener {
   private static VillagerFollow plugin;
   private static BukkitTask task;
   public static double radius;

   public VillagerInteractionHandEmerald(VillagerFollow plugin) {
      VillagerInteractionHandEmerald.plugin = plugin;
   }

   @EventHandler
   public void villagerFollowsPlayer(PlayerItemHeldEvent event) {
      FileConfiguration config = plugin.getConfig();
      String villagerFollow = config.getString("Config.villager-follow");
      String followPlayer = config.getString("Config.villager-follow-player");
      if ("true".equals(villagerFollow) && "true".equals(followPlayer)) {
         final Player player = event.getPlayer();
         ItemStack item = player.getInventory().getItem(event.getNewSlot());
         final World world = player.getWorld();
         if (item != null && item.getType() == Material.EMERALD) {
            final double radius = config.getDouble("Config.villager-follow-radius");
            final double velocidad = config.getDouble("Config.villager-follow-speed");
            if (task == null || task.isCancelled()) {
               task = (new BukkitRunnable() {
                  public void run() {
                     Location target = player.getLocation();
                     Iterator var2 = world.getNearbyEntities(target, radius, radius, radius).iterator();

                     while(var2.hasNext()) {
                        Entity entity = (Entity)var2.next();
                        if (entity.getType() == EntityType.VILLAGER && !entity.hasMetadata("paid")) {
                           Villager villager = (Villager)entity;
                           VillagerInteraction.followThing(villager, target, velocidad);
                        }
                     }

                  }
               }).runTaskTimer(plugin, 0L, 20L);
            }
         } else if (task != null) {
            task.cancel();
         }
      }

   }

   public static void onPluginReload() {
      if (task != null) {
         task.cancel();
         task = null;
      }

   }
}
