package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.ai.behavior.AxolotlBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import org.bukkit.entity.Axolotl;
import org.jetbrains.annotations.NotNull;

class BukkitAxolotlBehavior extends BukkitUpdatableCreatureBehavior implements AxolotlBehavior {
   final Axolotl m;

   public BukkitAxolotlBehavior(Axolotl a) {
      super(a);
      this.m = a;
   }

   @NotNull
   public BehaviorResult playDead() {
      return wrapper.runBehavior(this.m, "PlayDead", "net.minecraft.world.entity.animal.axolotl");
   }
}
