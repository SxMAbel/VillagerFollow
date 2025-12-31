package me.darksnakex.villagerfollow.mobchip.ai;

import java.util.Map;
import java.util.Set;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import me.darksnakex.villagerfollow.mobchip.ai.goal.WrappedPathfinder;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityAI extends Set<WrappedPathfinder> {
   @NotNull
   Mob getEntity();

   boolean contains(@NotNull Pathfinder var1);

   Pathfinder put(@NotNull Pathfinder var1, int var2);

   void putAll(@NotNull Map<? extends Pathfinder, Integer> var1);

   boolean remove(@NotNull Pathfinder var1);

   boolean isRunning(@NotNull Pathfinder var1);

   @NotNull
   Set<WrappedPathfinder> getRunningGoals();

   void disableFlag(@Nullable Pathfinder.PathfinderFlag var1);

   void enableFlag(@Nullable Pathfinder.PathfinderFlag var1);
}
