package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import java.util.function.Predicate;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Raider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PathfinderNearestHealableRaider<T extends LivingEntity> extends PathfinderNearestAttackableTarget<T> {
   public PathfinderNearestHealableRaider(@NotNull Raider m, @NotNull Class<T> filter) throws IllegalArgumentException {
      this(m, filter, true);
   }

   public PathfinderNearestHealableRaider(@NotNull Raider m, @NotNull Class<T> filter, boolean mustSee) throws IllegalArgumentException {
      this(m, filter, mustSee, (Predicate)null);
   }

   public PathfinderNearestHealableRaider(@NotNull Raider m, @NotNull Class<T> filter, boolean mustSee, @Nullable Predicate<LivingEntity> conditions) throws IllegalArgumentException {
      super(m, filter, 500, mustSee, false, conditions);
   }

   public String getInternalName() {
      return "PathfinderGoalNearestHealableRaider";
   }
}
