package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import java.util.function.Predicate;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Memory;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public interface EntityBehavior {
   @NotNull
   BehaviorResult backupIfClose(int var1, float var2);

   @NotNull
   default BehaviorResult backupIfClose(int minDistance) {
      return this.backupIfClose(minDistance, 1.0F);
   }

   @NotNull
   BehaviorResult passiveIf(@NotNull Memory<?> var1, int var2) throws IllegalArgumentException;

   @NotNull
   BehaviorResult eraseIf(@NotNull Predicate<Mob> var1, @NotNull Memory<?> var2) throws IllegalArgumentException;

   @NotNull
   BehaviorResult moveToWantedItem(int var1, float var2, boolean var3);

   @NotNull
   default BehaviorResult moveToWantedItem(int minDist, float speedMod) {
      return this.moveToWantedItem(minDist, speedMod, true);
   }

   @NotNull
   default BehaviorResult moveToWantedItem(int minDist) {
      return this.moveToWantedItem(minDist, 1.0F);
   }

   @NotNull
   BehaviorResult jumpOnBed(float var1);

   @NotNull
   default BehaviorResult jumpOnBed() {
      return this.jumpOnBed(1.0F);
   }

   @NotNull
   BehaviorResult meleeAttack(int var1);

   @NotNull
   default BehaviorResult meleeAttack() {
      return this.meleeAttack(60);
   }

   @NotNull
   BehaviorResult wakeUp();

   @NotNull
   BehaviorResult ringBell();

   @NotNull
   BehaviorResult reactToBell();

   @NotNull
   BehaviorResult interactWithDoor();

   @NotNull
   BehaviorResult sleep();

   @NotNull
   BehaviorResult socializeAtBell();
}
