package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Mob;
import org.bukkit.projectiles.ProjectileSource;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRangedAttack extends Pathfinder implements SpeedModifier, Ranged {
   private double speedMod;
   private float range;
   private int aMin;
   private int aMax;

   public PathfinderRangedAttack(@NotNull ProjectileSource m, double speedMod) {
      this(m, speedMod, 6.0F);
   }

   public PathfinderRangedAttack(@NotNull ProjectileSource m, double speedMod, float range) {
      this(m, speedMod, range, 100);
   }

   public PathfinderRangedAttack(@NotNull ProjectileSource m, double speedMod, float range, int interval) {
      this(m, speedMod, range, interval, interval);
   }

   public PathfinderRangedAttack(@NotNull ProjectileSource m, double speedMod, float range, int attackMin, int attackMax) {
      super(m instanceof Mob ? (Mob)m : null);
      this.speedMod = speedMod;
      this.range = range;
      this.aMax = attackMax;
      this.aMin = attackMin;
   }

   public int getMinAttackInterval() {
      return this.aMin;
   }

   public int getMaxAttackInterval() {
      return this.aMax;
   }

   public void setMinAttackInterval(int min) {
      this.aMin = min;
   }

   public void setMaxAttackInterval(int max) {
      this.aMax = max;
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

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalArrowAttack";
   }
}
