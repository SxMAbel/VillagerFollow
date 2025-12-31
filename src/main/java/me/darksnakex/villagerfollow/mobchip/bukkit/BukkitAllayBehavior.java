package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.ai.behavior.AllayBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import org.bukkit.Location;
import org.bukkit.entity.Allay;
import org.jetbrains.annotations.NotNull;

class BukkitAllayBehavior extends BukkitUpdatableCreatureBehavior implements AllayBehavior {
   final Allay m;

   BukkitAllayBehavior(Allay m) {
      super(m);
      this.m = m;
   }

   @NotNull
   public BehaviorResult hearNoteblock(@NotNull Location loc) {
      return wrapper.hearNoteblock(this.m, loc);
   }
}
