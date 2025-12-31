package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRandomStrollInVillage extends PathfinderRandomStroll {
   public PathfinderRandomStrollInVillage(@NotNull Creature c) {
      super(c);
   }

   public PathfinderRandomStrollInVillage(@NotNull Creature c, double speedMod) {
      super(c, speedMod);
   }

   public String getInternalName() {
      return "PathfinderGoalStrollVillageGolem";
   }
}
