package me.darksnakex.villagerfollow.mobchip.bukkit.events.pathfinder;

import me.darksnakex.villagerfollow.mobchip.ai.EntityAI;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import org.jetbrains.annotations.Nullable;

public class PathfinderRemoveEvent extends PathfinderEvent {
   public PathfinderRemoveEvent(@Nullable EntityAI ai, @Nullable Pathfinder pathfinder, boolean target) {
      super(ai, pathfinder, target);
   }
}
