package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRandomStrollToVillage extends PathfinderRandomStroll {
   public PathfinderRandomStrollToVillage(@NotNull Creature c) {
      super(c);
   }

   public PathfinderRandomStrollToVillage(@NotNull Creature c, double speedMod) {
      super(c, speedMod);
   }

   public String getInternalName() {
      return "PathfinderGoalStrollVillage";
   }
}
