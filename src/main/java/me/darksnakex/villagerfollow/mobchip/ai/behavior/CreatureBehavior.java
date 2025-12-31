package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import java.util.function.Function;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface CreatureBehavior extends EntityBehavior {
   @NotNull
   BehaviorResult panic(float var1);

   @NotNull
   default BehaviorResult panic() {
      return this.panic(1.5F);
   }

   @NotNull
   BehaviorResult followTemptation(@NotNull Function<LivingEntity, Float> var1) throws IllegalArgumentException;

   @NotNull
   default BehaviorResult followTemptation(float speedMod) {
      return this.followTemptation((c) -> {
         return speedMod;
      });
   }

   @NotNull
   default BehaviorResult followTemptation() {
      return this.followTemptation(1.0F);
   }

   @NotNull
   BehaviorResult tryFindWater(int var1, float var2);

   @NotNull
   default BehaviorResult tryFindWater(int range) {
      return this.tryFindWater(range, 1.0F);
   }
}
