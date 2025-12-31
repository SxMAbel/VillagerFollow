package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Zombie;
import org.jetbrains.annotations.NotNull;

public final class PathfinderZombieAttack extends PathfinderMeleeAttack {
   public PathfinderZombieAttack(@NotNull Zombie m) {
      this(m, 1.0D);
   }

   public PathfinderZombieAttack(@NotNull Zombie m, double speedMod) {
      this(m, speedMod, true);
   }

   public PathfinderZombieAttack(@NotNull Zombie m, double speedMod, boolean see) {
      super(m, speedMod, see);
   }

   public String getInternalName() {
      return "PathfinderGoalZombieAttack";
   }
}
