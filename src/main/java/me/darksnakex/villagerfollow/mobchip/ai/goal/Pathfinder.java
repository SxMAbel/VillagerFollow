package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public abstract class Pathfinder implements PathfinderInfo {
   protected final Mob entity;

   protected Pathfinder(@NotNull Mob entity) throws IllegalArgumentException {
      this.entity = entity;
   }

   @NotNull
   public Mob getEntity() {
      return this.entity;
   }

   @NotNull
   public abstract Pathfinder.PathfinderFlag[] getFlags();

   public static enum PathfinderFlag {
      MOVEMENT,
      TARGETING,
      LOOKING,
      JUMPING;

      // $FF: synthetic method
      private static Pathfinder.PathfinderFlag[] $values() {
         return new Pathfinder.PathfinderFlag[]{MOVEMENT, TARGETING, LOOKING, JUMPING};
      }
   }
}
