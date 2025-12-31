package me.darksnakex.villagerfollow.mobchip.ai.goal;

import java.util.function.Predicate;
import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.Location;
import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PathfinderMoveToBlock extends Pathfinder implements SpeedModifier, Ranged {
   public static final int DEFAULT_RANGE = 25;
   private int range;
   private int vrange;
   private Predicate<Location> validBlock;
   private double speedMod;

   public PathfinderMoveToBlock(@NotNull Creature c) {
      this(c, (Predicate)null);
   }

   public PathfinderMoveToBlock(@NotNull Creature c, @Nullable Predicate<Location> validBlock) {
      this(c, validBlock, 1.5D);
   }

   public PathfinderMoveToBlock(@NotNull Creature c, @Nullable Predicate<Location> validBlock, double speedMod) {
      this(c, validBlock, speedMod, 25);
   }

   public PathfinderMoveToBlock(@NotNull Creature c, @Nullable Predicate<Location> validBlock, double speedMod, int range) {
      this(c, validBlock, speedMod, range, range);
   }

   public PathfinderMoveToBlock(@NotNull Creature c, @Nullable Predicate<Location> validBlock, double speedMod, int range, int vrange) {
      super(c);
      this.speedMod = speedMod;
      this.validBlock = validBlock == null ? (l) -> {
         return true;
      } : validBlock;
      this.range = range;
      this.vrange = vrange;
   }

   public float getRange() {
      return (float)this.range;
   }

   public float getVerticalRange() {
      return (float)this.vrange;
   }

   public void setVerticalRange(int vrange) {
      this.vrange = vrange;
   }

   public void setRange(float range) throws IllegalArgumentException {
      if (range > 2.14748365E9F) {
         throw new IllegalArgumentException("Range must be an integer");
      } else {
         this.range = (int)Math.floor((double)range);
      }
   }

   public double getSpeedModifier() {
      return this.speedMod;
   }

   public void setSpeedModifier(double mod) {
      this.speedMod = mod;
   }

   @NotNull
   public Predicate<Location> getValidBlock() {
      return this.validBlock;
   }

   public void setValidBlock(@Nullable Predicate<Location> valid) {
      this.validBlock = valid == null ? (l) -> {
         return true;
      } : valid;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.JUMPING};
   }

   public String getInternalName() {
      return "PathfinderGoalGotoTarget";
   }
}
