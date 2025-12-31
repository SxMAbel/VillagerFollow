package me.darksnakex.villagerfollow.mobchip.bukkit;

import java.util.function.Function;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.CreatureBehavior;
import org.bukkit.Bukkit;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

class BukkitCreatureBehavior extends BukkitEntityBehavior implements CreatureBehavior {
   final Creature m;

   BukkitCreatureBehavior(Creature c) {
      super(c);
      this.m = c;
   }

   @NotNull
   public BehaviorResult panic(float speedMod) {
      return this.run("AnimalPanic", new Object[]{speedMod});
   }

   @NotNull
   public BehaviorResult followTemptation(@NotNull Function<LivingEntity, Float> speedModifier) {
      notNull(speedModifier, "Speed Modifier cannot be null");
      return this.run("FollowTemptation", new Object[]{(f) -> {
         return (Float)speedModifier.apply((LivingEntity)Bukkit.getEntity(f));
      }});
   }

   @NotNull
   public BehaviorResult tryFindWater(int range, float speedMod) {
      return this.run("TryFindWater", new Object[]{range, speedMod});
   }
}
