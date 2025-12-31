package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.ai.behavior.BreezeBehavior;
import org.bukkit.entity.Breeze;

class BukkitBreezeBehavior extends BukkitUpdatableCreatureBehavior implements BreezeBehavior {
   final Breeze m;

   public BukkitBreezeBehavior(Breeze m) {
      super(m);
      this.m = m;
   }

   public void longJump() {
      wrapper.runBehavior(this.m, "LongJump", "net.minecraft.world.entity.monster.breeze");
   }

   public void shoot() {
      wrapper.runBehavior(this.m, "Shoot", "net.minecraft.world.entity.monster.breeze");
   }

   public void slide() {
      wrapper.runBehavior(this.m, "Slide", "net.minecraft.world.entity.monster.breeze");
   }
}
