package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.Internal;

public abstract class CustomPathfinder extends Pathfinder {
   protected CustomPathfinder(@NotNull Mob m) {
      super(m);
   }

   @NotNull
   public abstract Pathfinder.PathfinderFlag[] getFlags();

   public abstract boolean canStart();

   public abstract void start();

   public abstract void tick();

   public void stop() {
   }

   public boolean canInterrupt() {
      return true;
   }

   public boolean canContinueToUse() {
      return this.canStart();
   }

   /** @deprecated */
   @Internal
   public String getInternalName() {
      return "CustomPathfinder";
   }
}
