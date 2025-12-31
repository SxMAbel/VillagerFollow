package me.darksnakex.villagerfollow.interactions;

import java.util.Iterator;
import java.util.Objects;
import me.darksnakex.villagerfollow.VillagerFollow;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class VillagerInteractionDropEmerald implements Listener {
   private static VillagerFollow plugin;
   private static BukkitTask task;

   public VillagerInteractionDropEmerald(VillagerFollow plugin) {
      VillagerInteractionDropEmerald.plugin = plugin;
   }

   public static void onPluginReload2() {
      if (task != null) {
         task.cancel();
         task = null;
      }

   }

   @EventHandler
   private static void villagerFollowsEmerald(PlayerDropItemEvent event) {
      final FileConfiguration config = plugin.getConfig();
      if (Objects.equals(config.getString("Config.villager-allow-goto-emerald"), "true") && Objects.equals(config.getString("Config.villager-follow"), "true")) {
         final Location playerLocation = event.getPlayer().getLocation();
         VillagerInteractionHandEmerald.radius = config.getDouble("Config.villager-follow-radius");
         final double velocidad = config.getDouble("Config.villager-follow-speed");
         if (task == null || task.isCancelled()) {
            task = (new BukkitRunnable() {
               public void run() {
                  Iterator var1 = playerLocation.getWorld().getEntities().iterator();

                  while(true) {
                     Entity entity;
                     do {
                        do {
                           if (!var1.hasNext()) {
                              return;
                           }

                           entity = (Entity)var1.next();
                        } while(!entity.getType().equals(EntityType.VILLAGER));
                     } while(entity.hasMetadata("paid"));

                     Villager villager = (Villager)entity;
                     Location villagerLocation = villager.getLocation();
                     Iterator var5 = villagerLocation.getWorld().getNearbyEntities(villagerLocation, VillagerInteractionHandEmerald.radius, VillagerInteractionHandEmerald.radius, VillagerInteractionHandEmerald.radius).iterator();

                     while(var5.hasNext()) {
                        Entity itemEntity = (Entity)var5.next();
                        if (itemEntity instanceof Item && ((Item)itemEntity).getItemStack().getType() == Material.EMERALD) {
                           double distance = villagerLocation.distance(itemEntity.getLocation());
                           if (distance <= VillagerInteractionHandEmerald.radius) {
                              VillagerInteraction.followThing(villager, itemEntity.getLocation(), velocidad);
                           }

                           if (distance <= 2.0D && Objects.equals(config.getString("Config.villager-allow-catch-emerald"), "true")) {
                              ItemStack esmeralda = new ItemStack(Material.EMERALD, 1);
                              villager.getEquipment().setItemInMainHand(esmeralda);
                              itemEntity.remove();
                              if (Objects.equals(config.getString("Config.villager-allow-catch-emerald-heal"), "true")) {
                                 villager.setHealth(20.0D);
                                 villager.playEffect(EntityEffect.VILLAGER_HEART);
                              }
                           }
                        }
                     }
                  }
               }
            }).runTaskTimer(plugin, 0L, 20L);
         }
      }

   }
}
