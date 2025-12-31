package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import org.bukkit.entity.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PiglinBehavior extends CreatureBehavior {
   @NotNull
   BehaviorResult stopHunting();

   @NotNull
   BehaviorResult startAdmiring(@Nullable Item var1, int var2);

   @NotNull
   BehaviorResult startHunting();

   void setHuntingRadius(int var1) throws IllegalArgumentException;

   default void resetHuntingRadius() {
      this.setHuntingRadius(100);
   }

   @NotNull
   BehaviorResult stopAdmiring();
}
