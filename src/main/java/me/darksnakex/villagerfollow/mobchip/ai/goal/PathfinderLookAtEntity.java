package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.Probable;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.Filtering;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderLookAtEntity<T extends LivingEntity> extends Pathfinder implements Filtering<T>, Probable, Ranged {
   public static final float DEFAULT_PROBABILITY = 0.02F;
   private float lookRange;
   private float probability;
   private Class<T> filterClass;
   private boolean horizontal;

   public PathfinderLookAtEntity(@NotNull Mob m, @NotNull Class<T> filter) {
      this(m, filter, 5.0F);
   }

   public PathfinderLookAtEntity(@NotNull Mob m, @NotNull Class<T> filter, float lookRange) {
      this(m, filter, lookRange, 0.02F);
   }

   public PathfinderLookAtEntity(@NotNull Mob m, @NotNull Class<T> filter, float lookRange, float probability) {
      this(m, filter, lookRange, probability, false);
   }

   public PathfinderLookAtEntity(@NotNull Mob m, @NotNull Class<T> filter, float lookRange, float probability, boolean horizontal) {
      super(m);
      this.filterClass = filter;
      this.lookRange = lookRange;
      this.probability = probability;
      this.horizontal = horizontal;
   }

   public float getRange() {
      return this.lookRange;
   }

   public void setRange(float range) {
      this.lookRange = range;
   }

   public float getProbability() {
      return this.probability;
   }

   public void setProbability(float prob) {
      this.probability = prob;
   }

   public void setFilter(@NotNull Class<T> clazz) {
      this.filterClass = clazz;
   }

   public Class<T> getFilter() {
      return this.filterClass;
   }

   public boolean isHorizontal() {
      return this.horizontal;
   }

   public void setHorizontal(boolean horizontal) {
      this.horizontal = horizontal;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalLookAtPlayer";
   }
}
