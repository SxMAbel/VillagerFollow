package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.ai.behavior.CamelBehavior;
import org.bukkit.entity.Camel;

class BukkitCamelBehavior extends BukkitUpdatableCreatureBehavior implements CamelBehavior {
   final Camel m;

   public BukkitCamelBehavior(Camel m) {
      super(m);
      this.m = m;
   }

   public void sit(int minimalPoseTicks) {
      wrapper.runBehavior(this.m, "CamelAi$RandomSitting", "net.minecraft.world.entity.animal.camel", minimalPoseTicks);
   }
}
