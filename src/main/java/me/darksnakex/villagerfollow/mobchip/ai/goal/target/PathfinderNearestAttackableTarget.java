package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import java.util.function.Predicate;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Conditional;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Repeated;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PathfinderNearestAttackableTarget<T extends LivingEntity> extends TargetPathfinder implements Filtering<T>, Conditional<LivingEntity>, Repeated {
   private Class<T> filter;
   private Predicate<LivingEntity> conditions;
   private int interval;

   public PathfinderNearestAttackableTarget(@NotNull Mob m, @NotNull Class<T> filter) throws IllegalArgumentException {
      this(m, filter, 10);
   }

   public PathfinderNearestAttackableTarget(@NotNull Mob m, @NotNull Class<T> filter, int interval) throws IllegalArgumentException {
      this(m, filter, interval, true, true);
   }

   public PathfinderNearestAttackableTarget(@NotNull Mob m, @NotNull Class<T> filter, int interval, boolean mustSee, boolean reach) throws IllegalArgumentException {
      this(m, filter, interval, mustSee, reach, (Predicate)null);
   }

   public PathfinderNearestAttackableTarget(@NotNull Mob m, @NotNull Class<T> filter, int interval, boolean mustSee, boolean reach, @Nullable Predicate<LivingEntity> conditions) throws IllegalArgumentException {
      this.filter = filter;
      this.interval = interval;
      this.conditions = conditions == null ? (e) -> {
         return true;
      } : conditions;
   }

   public void setFilter(@NotNull Class<T> clazz) throws IllegalArgumentException {
      this.filter = clazz;
   }

   public Class<T> getFilter() {
      return this.filter;
   }

   public int getInterval() {
      return this.interval;
   }

   public void setInterval(int interval) throws IllegalArgumentException {
      if (interval < 1) {
         throw new IllegalArgumentException("Must be greater than 0");
      } else {
         this.interval = interval;
      }
   }

   @NotNull
   public Predicate<LivingEntity> getCondition() {
      return this.conditions;
   }

   public void setCondition(@NotNull Predicate<LivingEntity> condition) {
      this.conditions = condition;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.TARGETING};
   }

   public String getInternalName() {
      return "PathfinderGoalNearestAttackableTarget";
   }
}
