package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.Probable;
import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRandomStrollLand extends PathfinderRandomStroll implements Probable {
   public static final float DEFAULT_PROBABILITY = 0.001F;
   private float probability;

   public PathfinderRandomStrollLand(@NotNull Creature c) {
      this(c, 1.0D);
   }

   public PathfinderRandomStrollLand(@NotNull Creature c, double speedMod) {
      this(c, speedMod, 0.001F);
   }

   public PathfinderRandomStrollLand(@NotNull Creature c, double speedMod, float probability) {
      super(c, speedMod);
      this.probability = probability;
   }

   public float getProbability() {
      return this.probability;
   }

   public void setProbability(float prob) {
      this.probability = prob;
   }

   public String getInternalName() {
      return "PathfinderGoalRandomStrollLand";
   }
}
