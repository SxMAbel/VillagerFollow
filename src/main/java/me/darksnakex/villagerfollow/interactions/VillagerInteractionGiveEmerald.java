package me.darksnakex.villagerfollow.interactions;

import java.util.Objects;
import me.darksnakex.villagerfollow.VillagerFollow;
import org.bukkit.EntityEffect;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;

public class VillagerInteractionGiveEmerald implements Listener {
   private static VillagerFollow plugin;
   private static int timeallowed = 60;
   private static boolean cancel = false;

   public VillagerInteractionGiveEmerald(VillagerFollow plugin) {
      VillagerInteractionGiveEmerald.plugin = plugin;
   }

   @EventHandler
   private void playerGiveEmerald(final PlayerInteractEntityEvent event) {
      FileConfiguration config = plugin.getConfig();
      final FileConfiguration messagesConfig = plugin.getMessages();
      if (!Objects.equals(config.getString("Config.villager-pay-follow"), "false") && !Objects.equals(config.getString("Config.villager-follow"), "false") && event.getRightClicked() instanceof Villager) {
         final Villager villager = (Villager)event.getRightClicked();
         if (event.getPlayer().getInventory().getItemInMainHand().getType() == Material.EMERALD && !villager.hasMetadata("paid")) {
            ItemStack esmeralda = new ItemStack(Material.EMERALD);
            final double velocidad = config.getDouble("Config.villager-follow-speed");
            event.getPlayer().getInventory().removeItem(new ItemStack[]{esmeralda});
            villager.setMetadata("paid", new FixedMetadataValue(plugin, true));
            villager.setMetadata(event.getPlayer().getDisplayName(), new FixedMetadataValue(plugin, true));
            timeallowed = Math.abs(config.getInt("Config.villager-pay-follow-time"));
            event.getPlayer().sendMessage(plugin.nombre + messagesConfig.getString("villager-pay.start") + timeallowed + "s.");
            villager.playEffect(EntityEffect.VILLAGER_HAPPY);
            (new BukkitRunnable() {
               int timepassed = 0;

               public void run() {
                  if (this.timepassed < VillagerInteractionGiveEmerald.timeallowed && !VillagerInteractionGiveEmerald.cancel) {
                     VillagerInteraction.followThing(villager, event.getPlayer().getLocation(), velocidad);
                     ++this.timepassed;
                  } else {
                     event.getPlayer().sendMessage(VillagerInteractionGiveEmerald.plugin.nombre + messagesConfig.getString("villager-pay.end"));
                     villager.removeMetadata("paid", VillagerInteractionGiveEmerald.plugin);
                     villager.removeMetadata(event.getPlayer().getDisplayName(), VillagerInteractionGiveEmerald.plugin);
                     VillagerInteractionGiveEmerald.cancel = false;
                     this.cancel();
                  }
               }
            }).runTaskTimer(plugin, 0L, 20L);
         }
      }

   }

   @EventHandler
   private void playerCancelFollowPay(PlayerInteractEntityEvent event) {
      if (event.getRightClicked() instanceof Villager && event.getPlayer().isSneaking() && event.getRightClicked().hasMetadata("paid") && event.getRightClicked().hasMetadata(event.getPlayer().getDisplayName())) {
         cancel = true;
      }

   }
}
