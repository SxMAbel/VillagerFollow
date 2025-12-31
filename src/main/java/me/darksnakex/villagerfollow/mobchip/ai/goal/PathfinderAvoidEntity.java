package me.darksnakex.villagerfollow.mobchip.ai.goal;

import java.util.function.Predicate;
import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.Filtering;
import org.bukkit.entity.Creature;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PathfinderAvoidEntity<T extends LivingEntity> extends Pathfinder implements Filtering<T>, SpeedModifier {
   private double speedModifier;
   private float maxDistance;
   private double sprintModifier;
   private Class<T> filter;
   private Predicate<T> avoidPredicate;
   private Predicate<T> avoidingPredicate;

   public PathfinderAvoidEntity(@NotNull Creature m, @NotNull Class<T> filter) throws IllegalArgumentException {
      this(m, filter, 5.0F, 1.5D);
   }

   public PathfinderAvoidEntity(@NotNull Creature m, @NotNull Class<T> filter, float dist, double mod) throws IllegalArgumentException {
      this(m, filter, dist, mod, mod);
   }

   public PathfinderAvoidEntity(@NotNull Creature m, @NotNull Class<T> filter, float dist, double walkMod, double sprintMod) throws IllegalArgumentException {
      this(m, filter, dist, walkMod, sprintMod, (Predicate)null);
   }

   public PathfinderAvoidEntity(@NotNull Creature m, @NotNull Class<T> filter, float dist, double walkMod, double sprintMod, @Nullable Predicate<T> avoidPredicate) throws IllegalArgumentException {
      this(m, filter, dist, walkMod, sprintMod, avoidPredicate, (Predicate)null);
   }

   public PathfinderAvoidEntity(@NotNull Creature m, @NotNull Class<T> filter, float dist, double walkMod, double sprintMod, @Nullable Predicate<T> avoidPredicate, @Nullable Predicate<T> avoidingPredicate) throws IllegalArgumentException {
      super(m);
      this.filter = filter;
      this.speedModifier = walkMod;
      this.maxDistance = dist;
      this.sprintModifier = sprintMod;
      this.avoidPredicate = avoidPredicate;
      this.avoidingPredicate = avoidingPredicate;
   }

   public float getMaxDistance() {
      return this.maxDistance;
   }

   public void setMaxDistance(float dist) {
      this.maxDistance = dist;
   }

   public double getSpeedModifier() {
      return this.speedModifier;
   }

   public double getSprintModifier() {
      return this.sprintModifier;
   }

   public void setSprintModifier(double mod) {
      this.sprintModifier = mod;
   }

   @Nullable
   public Predicate<T> getAvoidPredicate() {
      return this.avoidPredicate;
   }

   public void setAvoidPredicate(@Nullable Predicate<T> avoidPredicate) {
      this.avoidPredicate = avoidPredicate;
   }

   @Nullable
   public Predicate<T> getAvoidingPredicate() {
      return this.avoidingPredicate;
   }

   public void setAvoidingPredicate(@Nullable Predicate<T> avoidingPredicate) {
      this.avoidingPredicate = avoidingPredicate;
   }

   public void setSpeedModifier(double mod) {
      this.speedModifier = mod;
   }

   public void setFilter(@NotNull Class<T> clazz) {
      this.filter = clazz;
   }

   @NotNull
   public Class<T> getFilter() {
      return this.filter;
   }

   @NotNull
   public Creature getEntity() {
      return (Creature)this.entity;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public String getInternalName() {
      return "PathfinderGoalAvoidTarget";
   }
}
