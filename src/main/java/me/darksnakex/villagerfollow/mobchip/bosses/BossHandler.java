package me.darksnakex.villagerfollow.mobchip.bosses;

import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public final class BossHandler {
   private final Plugin plugin;
   private final Boss<?> boss;

   BossHandler(Boss<?> boss, Plugin p) {
      this.plugin = p;
      this.boss = boss;
      new BossHandler.BossEvents(this);
   }

   public Plugin getPlugin() {
      return this.plugin;
   }

   public Boss<?> getBoss() {
      return this.boss;
   }

   private static final class BossEvents implements Listener {
      private final Boss<?> boss;

      private BossEvents(BossHandler handler) {
         this.boss = handler.boss;
         Bukkit.getPluginManager().registerEvents(this, handler.plugin);
      }

      @EventHandler
      public void onDamageDefensive(EntityDamageEvent e) {
         if (e.getEntity() instanceof Mob) {
            Mob m = (Mob)e.getEntity();
            if (this.boss.getMob().getUniqueId().equals(m.getUniqueId())) {
               this.boss.onDamageDefensive(e);
            }

         }
      }

      @EventHandler
      public void onDamageOffensive(EntityDamageByEntityEvent e) {
         if (e.getDamager() instanceof Mob) {
            Mob m = (Mob)e.getEntity();
            if (this.boss.getMob().getUniqueId().equals(m.getUniqueId())) {
               this.boss.onDamageOffensive(e);
            }

         }
      }

      @EventHandler
      public void onDeath(EntityDeathEvent e) {
         if (e.getEntity() instanceof Mob) {
            Mob m = (Mob)e.getEntity();
            if (this.boss.getMob().getUniqueId().equals(m.getUniqueId())) {
               this.boss.onDeath(e);
               Iterator var3 = this.boss.getDrops().iterator();

               while(var3.hasNext()) {
                  ItemStack i = (ItemStack)var3.next();
                  m.getWorld().dropItemNaturally(m.getLocation(), i);
               }

               if (this.boss.getDeathSound() != null) {
                  m.getWorld().playSound(m.getLocation(), this.boss.getDeathSound(), 3.0F, 1.0F);
               }
            }

         }
      }

      // $FF: synthetic method
      BossEvents(BossHandler x0, Object x1) {
         this(x0);
      }
   }
}
