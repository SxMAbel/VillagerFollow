package me.darksnakex.villagerfollow.mobchip.ai.enderdragon;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class CustomPhase implements DragonPhase {
   protected final EnderDragon dragon;
   protected final NamespacedKey key;

   public CustomPhase(@NotNull EnderDragon dragon, @NotNull NamespacedKey key) throws IllegalArgumentException {
      this.dragon = dragon;
      this.key = key;
   }

   @NotNull
   public final NamespacedKey getKey() {
      return this.key;
   }

   @NotNull
   public final EnderDragon getDragon() {
      return this.dragon;
   }

   public void stop() {
   }

   public void start() {
   }

   public void serverTick() {
   }

   public void clientTick() {
   }

   public void onCrystalDestroyed(EnderCrystal c, DamageCause cause, @Nullable Player p) {
      DragonPhase.super.onCrystalDestroyed(c, cause, p);
   }

   public float onDamage(DamageCause cause, float amount) {
      return DragonPhase.super.onDamage(cause, amount);
   }

   public boolean isSitting() {
      return false;
   }

   public float getFlyingSpeed() {
      return 0.6F;
   }
}
