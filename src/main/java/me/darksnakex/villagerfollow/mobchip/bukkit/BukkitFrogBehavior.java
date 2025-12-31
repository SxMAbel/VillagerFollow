package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.FrogBehavior;
import org.bukkit.Sound;
import org.bukkit.entity.Frog;
import org.jetbrains.annotations.NotNull;

class BukkitFrogBehavior extends BukkitUpdatableCreatureBehavior implements FrogBehavior {
   final Frog m;

   public BukkitFrogBehavior(Frog f) {
      super(f);
      this.m = f;
   }

   @NotNull
   public BehaviorResult shootTongue(Sound tongueSound, Sound eatSound) {
      return wrapper.runBehavior(this.m, "ShootTongue", "net.minecraft.world.entity.animal.frog", tongueSound, eatSound);
   }

   @NotNull
   public BehaviorResult croak() {
      return this.run("Croak", new Object[0]);
   }
}
