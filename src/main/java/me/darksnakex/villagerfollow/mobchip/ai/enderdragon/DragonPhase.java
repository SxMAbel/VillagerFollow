package me.darksnakex.villagerfollow.mobchip.ai.enderdragon;

import org.bukkit.Keyed;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DragonPhase extends Keyed {
   @NotNull
   EnderDragon getDragon();

   @NotNull
   Location getTargetLocation();

   void start();

   void stop();

   void clientTick();

   void serverTick();

   boolean isSitting();

   float getFlyingSpeed();

   default void onCrystalDestroyed(EnderCrystal c, DamageCause cause, @Nullable Player p) {
   }

   default float onDamage(DamageCause cause, float amount) {
      return amount;
   }
}
