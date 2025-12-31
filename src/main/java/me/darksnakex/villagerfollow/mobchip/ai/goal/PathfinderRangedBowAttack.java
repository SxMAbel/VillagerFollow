package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Mob;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRangedBowAttack extends Pathfinder implements SpeedModifier, Ranged, Repeated {
   private double speedMod;
   private float range;
   private int aInv;

   public PathfinderRangedBowAttack(@NotNull ProjectileSource m, double speedMod) {
      this(m, speedMod, 6.0F);
   }

   public PathfinderRangedBowAttack(@NotNull ProjectileSource m, double speedMod, float range) {
      this(m, speedMod, range, 100);
   }

   public PathfinderRangedBowAttack(@NotNull ProjectileSource m, double speedMod, float range, int interval) throws IllegalArgumentException {
      super((Mob)m);
      if (interval < 0) {
         throw new IllegalArgumentException("Interval must be greater than 0");
      } else {
         this.speedMod = speedMod;
         this.range = range;
         this.aInv = interval;
      }
   }

   public float getRange() {
      return this.range;
   }

   public void setRange(float range) {
      this.range = range;
   }

   public double getSpeedModifier() {
      return this.speedMod;
   }

   public void setSpeedModifier(double mod) {
      this.speedMod = mod;
   }

   public int getInterval() {
      return this.aInv;
   }

   public void setInterval(int interval) {
      if (interval < 0) {
         throw new IllegalArgumentException("Interval must be greater than 0");
      } else {
         this.aInv = interval;
      }
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.LOOKING, Pathfinder.PathfinderFlag.MOVEMENT};
   }

   public String getInternalName() {
      return "PathfinderGoalBowShoot";
   }
}
