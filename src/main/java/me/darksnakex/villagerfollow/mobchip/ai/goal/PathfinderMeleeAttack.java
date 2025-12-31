package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.Targeting;
import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public class PathfinderMeleeAttack extends Pathfinder implements SpeedModifier, Targeting {
   private double speedMod;
   private boolean mustSee;

   public PathfinderMeleeAttack(@NotNull Creature c) {
      this(c, 1.5D);
   }

   public PathfinderMeleeAttack(@NotNull Creature c, double speedMod) {
      this(c, speedMod, true);
   }

   public PathfinderMeleeAttack(@NotNull Creature c, double speedMod, boolean see) throws IllegalArgumentException {
      super(c);
      this.speedMod = speedMod;
      this.mustSee = see;
   }

   public double getSpeedModifier() {
      return this.speedMod;
   }

   public void setSpeedModifier(double mod) {
      this.speedMod = mod;
   }

   public boolean mustSee() {
      return this.mustSee;
   }

   public void setSee(boolean see) {
      this.mustSee = see;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalMeleeAttack";
   }
}
