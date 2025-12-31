package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.jetbrains.annotations.ApiStatus.Internal;

public interface PathfinderInfo {
   default String getName() {
      return this.getClass().getSimpleName();
   }

   @Internal
   String getInternalName();
}
