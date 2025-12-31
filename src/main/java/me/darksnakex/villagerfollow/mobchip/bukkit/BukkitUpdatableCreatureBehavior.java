package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.ai.schedule.Updatable;
import org.bukkit.entity.Creature;

class BukkitUpdatableCreatureBehavior extends BukkitCreatureBehavior implements Updatable {
   BukkitUpdatableCreatureBehavior(Creature c) {
      super(c);
   }

   public void updateActivities() {
      wrapper.updateActivities(this.m);
   }
}
