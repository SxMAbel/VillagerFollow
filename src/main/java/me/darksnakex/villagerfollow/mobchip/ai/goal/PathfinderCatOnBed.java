package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Cat;
import org.jetbrains.annotations.NotNull;

public final class PathfinderCatOnBed extends Pathfinder implements SpeedModifier, Ranged {
   private double speedMod;
   private int range;

   public PathfinderCatOnBed(@NotNull Cat cat, double speedMod) {
      this(cat, speedMod, 4);
   }

   public PathfinderCatOnBed(@NotNull Cat cat, double speedMod, int range) {
      super(cat);
      this.speedMod = speedMod;
      this.range = range;
   }

   @NotNull
   public Cat getEntity() {
      return (Cat)this.entity;
   }

   public float getRange() {
      return (float)this.range;
   }

   public void setRange(float range) throws IllegalArgumentException {
      if (range > 2.14748365E9F) {
         throw new IllegalArgumentException("Must be an integer");
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
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.JUMPING};
   }

   public String getInternalName() {
      return "PathfinderGoalCatSitOnBed";
   }
}
