package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import java.util.function.Predicate;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Tameable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PathfinderWildTarget<T extends LivingEntity> extends PathfinderNearestAttackableTarget<T> {
   public PathfinderWildTarget(@NotNull Tameable mob, @NotNull Class<T> filter) throws IllegalArgumentException {
      this(mob, filter, true);
   }

   public PathfinderWildTarget(@NotNull Tameable mob, @NotNull Class<T> filter, boolean see) throws IllegalArgumentException {
      this(mob, filter, see, (Predicate)null);
   }

   public PathfinderWildTarget(@NotNull Tameable mob, @NotNull Class<T> filter, boolean see, @Nullable Predicate<LivingEntity> conditions) throws IllegalArgumentException {
      super((Mob)mob, filter, 10, see, false, conditions);
   }

   public String getInternalName() {
      return "PathfinderGoalRandomTargetNonTamed";
   }
}
