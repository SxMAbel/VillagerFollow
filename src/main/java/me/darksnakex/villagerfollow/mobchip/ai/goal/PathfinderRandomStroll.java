package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public class PathfinderRandomStroll extends Pathfinder implements SpeedModifier, Repeated {
   private double speedMod;
   private int interval;

   public PathfinderRandomStroll(@NotNull Creature c) {
      this(c, 1.0D);
   }

   public PathfinderRandomStroll(@NotNull Creature c, double speedMod) {
      this(c, speedMod, 120);
   }

   public PathfinderRandomStroll(@NotNull Creature c, double speedMod, int interval) throws IllegalArgumentException {
      super(c);
      if (interval < 0) {
         throw new IllegalArgumentException("Interval must be greater than 0");
      } else {
         this.speedMod = speedMod;
         this.interval = interval;
      }
   }

   public int getInterval() {
      return this.interval;
   }

   public void setInterval(int interval) {
      if (interval < 0) {
         throw new IllegalArgumentException("Interval must be greater than 0");
      } else {
         this.interval = interval;
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
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public String getInternalName() {
      return "PathfinderGoalRandomStroll";
   }
}
