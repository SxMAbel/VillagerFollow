package me.darksnakex.villagerfollow.mobchip.ai.goal;

import java.util.function.BooleanSupplier;
import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderMoveThroughVillage extends Pathfinder implements SpeedModifier, Ranged {
   public static final int DEFAULT_MIN_DISTANCE = 10;
   public static final BooleanSupplier DEFAULT_USE_DOORS = () -> {
      return true;
   };
   private boolean atNight;
   private double speedMod;
   private int minDistance;
   private BooleanSupplier canUseDoors;

   public PathfinderMoveThroughVillage(@NotNull Creature c) {
      this(c, DEFAULT_USE_DOORS);
   }

   public PathfinderMoveThroughVillage(@NotNull Creature c, boolean useDoors) {
      this(c, () -> {
         return useDoors;
      });
   }

   public PathfinderMoveThroughVillage(@NotNull Creature c, @NotNull BooleanSupplier useDoors) {
      this(c, useDoors, 1.0D);
   }

   public PathfinderMoveThroughVillage(@NotNull Creature c, @NotNull BooleanSupplier useDoors, double speedMod) {
      this(c, useDoors, speedMod, 10);
   }

   public PathfinderMoveThroughVillage(@NotNull Creature c, boolean useDoors, double speedMod) {
      this(c, () -> {
         return useDoors;
      }, speedMod, 10);
   }

   public PathfinderMoveThroughVillage(@NotNull Creature c, @NotNull BooleanSupplier useDoors, double speedMod, int minDistance) {
      this(c, useDoors, speedMod, minDistance, false);
   }

   public PathfinderMoveThroughVillage(@NotNull Creature c, boolean useDoors, double speedMod, int minDistance) {
      this(c, () -> {
         return useDoors;
      }, speedMod, minDistance, false);
   }

   public PathfinderMoveThroughVillage(@NotNull Creature c, boolean useDoors, double speedMod, int minDistance, boolean onlyAtNight) {
      this(c, () -> {
         return useDoors;
      }, speedMod, minDistance, onlyAtNight);
   }

   public PathfinderMoveThroughVillage(@NotNull Creature c, @NotNull BooleanSupplier useDoors, double speedMod, int minDistance, boolean onlyAtNight) {
      super(c);
      this.atNight = onlyAtNight;
      this.speedMod = speedMod;
      this.minDistance = minDistance;
      this.canUseDoors = useDoors;
   }

   public BooleanSupplier canUseDoors() {
      return this.canUseDoors;
   }

   public void setCanUseDoors(@NotNull BooleanSupplier b) {
      this.canUseDoors = b;
   }

   public void setCanUseDoors(boolean use) {
      this.canUseDoors = () -> {
         return use;
      };
   }

   public int getMinDistance() {
      return this.minDistance;
   }

   public void setMinDistance(int min) {
      this.minDistance = min;
   }

   public boolean mustBeNight() {
      return this.atNight;
   }

   public void setMustBeNight(boolean night) {
      this.atNight = night;
   }

   public double getSpeedModifier() {
      return this.speedMod;
   }

   public void setSpeedModifier(double mod) {
      this.speedMod = mod;
   }

   public float getRange() {
      return (float)this.minDistance;
   }

   public void setRange(float range) throws IllegalArgumentException {
      if (range > 2.14748365E9F) {
         throw new IllegalArgumentException("Range is an integer");
      } else {
         this.minDistance = (int)Math.floor((double)range);
      }
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public String getInternalName() {
      return "PathfinderGoalMoveThroughVillage";
   }
}
