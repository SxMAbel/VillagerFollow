package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.DragonBrain;
import me.darksnakex.villagerfollow.mobchip.ai.enderdragon.CustomPhase;
import me.darksnakex.villagerfollow.mobchip.ai.enderdragon.DragonPhase;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

class BukkitDragonBrain extends BukkitBrain implements DragonBrain {
   final EnderDragon m;

   BukkitDragonBrain(@NotNull EnderDragon dragon) {
      super((Mob)dragon);
      this.m = dragon;
   }

   public void setCustomPhase(@NotNull CustomPhase phase) throws IllegalArgumentException {
      w.setCustomPhase(this.m, phase);
   }

   public EnderCrystal getNearestCrystal() {
      return w.getNearestCrystal(this.m);
   }

   @NotNull
   public DragonPhase getCurrentPhase() {
      return w.getCurrentPhase(this.m);
   }
}
