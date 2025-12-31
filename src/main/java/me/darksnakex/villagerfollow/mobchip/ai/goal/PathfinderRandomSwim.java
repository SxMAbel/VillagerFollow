package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRandomSwim extends PathfinderRandomStroll {
   public static final int DEFAULT_INTERVAL = 120;

   public PathfinderRandomSwim(@NotNull Creature c) {
      this(c, 120);
   }

   public PathfinderRandomSwim(@NotNull Creature c, int interval) {
      super(c, 1.0D, interval);
   }

   public PathfinderRandomSwim(@NotNull Creature c, double speedMod) {
      this(c, speedMod, 120);
   }

   public PathfinderRandomSwim(@NotNull Creature c, double speedMod, int interval) {
      super(c, speedMod, interval);
   }

   public String getInternalName() {
      return "PathfinderGoalRandomSwim";
   }
}
