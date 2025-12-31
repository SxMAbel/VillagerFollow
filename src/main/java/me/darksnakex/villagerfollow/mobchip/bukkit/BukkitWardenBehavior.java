package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.WardenBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.memories.EntityMemory;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Unit;
import org.bukkit.Location;
import org.bukkit.entity.Warden;
import org.jetbrains.annotations.NotNull;

class BukkitWardenBehavior extends BukkitUpdatableCreatureBehavior implements WardenBehavior {
   final Warden m;

   public BukkitWardenBehavior(Warden m) {
      super(m);
      this.m = m;
   }

   BehaviorResult run(String behaviorName, Object... args) {
      return wrapper.runBehavior(this.m, behaviorName, "net.minecraft.world.entity.ai.behavior.warden", args);
   }

   @NotNull
   public BehaviorResult setDisturbanceLocation(@NotNull Location loc) {
      return wrapper.setDisturbanceLocation(this.m, loc);
   }

   @NotNull
   public BehaviorResult dig(int duration) {
      wrapper.removeMemory(this.m, EntityMemory.ATTACK_TARGET);
      wrapper.removeMemory(this.m, EntityMemory.WALKING_TARGET);
      return this.run("Digging", duration);
   }

   @NotNull
   public BehaviorResult emerge(int duration) {
      wrapper.setMemory(this.m, EntityMemory.IS_EMERGING, Unit.INSTANCE, 134L);
      wrapper.removeMemory(this.m, EntityMemory.WALKING_TARGET);
      return this.run("Emerging", duration);
   }

   @NotNull
   public BehaviorResult roar() {
      return this.run("Roar");
   }

   @NotNull
   public BehaviorResult sonicBoom() {
      return this.run("SonicBoom");
   }

   @NotNull
   public BehaviorResult sniff(int duration) {
      this.run("TryToSniff");
      return this.run("Sniffing", duration);
   }
}
