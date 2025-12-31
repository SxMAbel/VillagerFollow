package me.darksnakex.villagerfollow.mobchip.bukkit;

import java.util.function.Predicate;
import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.EntityBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Memory;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

class BukkitEntityBehavior implements EntityBehavior {
   private final Mob m;
   static final ChipUtil wrapper = ChipUtil.getWrapper();

   BukkitEntityBehavior(Mob m) {
      this.m = m;
   }

   static void notNull(Object o, String message) {
      if (o == null) {
         throw new IllegalArgumentException(message);
      }
   }

   BehaviorResult run(String behaviorName, Object... args) {
      return wrapper.runBehavior(this.m, behaviorName, args);
   }

   @NotNull
   public BehaviorResult backupIfClose(int min, float speedMod) {
      return this.run("BehaviorRetreat", min, speedMod);
   }

   @NotNull
   public BehaviorResult passiveIf(@NotNull Memory<?> memory, int durationTicks) throws IllegalArgumentException {
      notNull(memory, "Memory cannot be null");
      return this.run("BehaviorPacify", memory, durationTicks);
   }

   @NotNull
   public BehaviorResult eraseIf(@NotNull Predicate<Mob> function, @NotNull Memory<?> memory) throws IllegalArgumentException {
      notNull(function, "Function cannot be null");
      notNull(memory, "Memory cannot be null");
      return this.run("BehaviorRemoveMemory", function, memory);
   }

   @NotNull
   public BehaviorResult moveToWantedItem(int minDist, float speedMod, boolean requireTarget) {
      return this.run("BehaviorFindAdmirableItem", speedMod, requireTarget, minDist);
   }

   @NotNull
   public BehaviorResult jumpOnBed(float speedMod) {
      return this.run("BehaviorBedJump", speedMod);
   }

   @NotNull
   public BehaviorResult meleeAttack(int cooldown) {
      return this.run("BehaviorAttack", cooldown);
   }

   @NotNull
   public BehaviorResult wakeUp() {
      return this.run("BehaviorWake");
   }

   @NotNull
   public BehaviorResult ringBell() {
      return this.run("BehaviorBellRing");
   }

   @NotNull
   public BehaviorResult reactToBell() {
      return this.run("BehaviorBellAlert");
   }

   @NotNull
   public BehaviorResult interactWithDoor() {
      return this.run("BehaviorInteractDoor");
   }

   @NotNull
   public BehaviorResult sleep() {
      return this.run("BehaviorSleep");
   }

   @NotNull
   public BehaviorResult socializeAtBell() {
      return this.run("BehaviorBell");
   }
}
