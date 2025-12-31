package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.entity.Animals;
import org.jetbrains.annotations.NotNull;

public final class PathfinderBreed extends Pathfinder implements SpeedModifier {
   private double speedMod;

   public PathfinderBreed(@NotNull Animals m) {
      this(m, 1.0D);
   }

   public PathfinderBreed(@NotNull Animals m, double speedMod) {
      super(m);
      this.speedMod = speedMod;
   }

   @NotNull
   public Animals getEntity() {
      return (Animals)this.entity;
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
      return "PathfinderGoalBreed";
   }
}
