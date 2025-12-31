package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import java.util.function.Predicate;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Raider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PathfinderNearestAttackableTargetRaider<T extends LivingEntity> extends PathfinderNearestAttackableTarget<T> {
   public PathfinderNearestAttackableTargetRaider(@NotNull Raider m, @NotNull Class<T> filter) throws IllegalArgumentException {
      super(m, filter);
   }

   public PathfinderNearestAttackableTargetRaider(@NotNull Raider m, @NotNull Class<T> filter, int interval) throws IllegalArgumentException {
      super(m, filter, interval, true, true);
   }

   public PathfinderNearestAttackableTargetRaider(@NotNull Raider m, @NotNull Class<T> filter, int interval, boolean mustSee, boolean reach) throws IllegalArgumentException {
      super(m, filter, interval, mustSee, reach);
   }

   public PathfinderNearestAttackableTargetRaider(@NotNull Raider m, @NotNull Class<T> filter, int interval, boolean mustSee, boolean reach, @Nullable Predicate<LivingEntity> conditions) throws IllegalArgumentException {
      super(m, filter, interval, mustSee, reach, conditions);
   }

   public String getInternalName() {
      return "PathfinderGoalNearestAttackableTargetWitch";
   }
}
