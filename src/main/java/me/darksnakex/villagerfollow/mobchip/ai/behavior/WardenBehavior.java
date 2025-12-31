package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import me.darksnakex.villagerfollow.mobchip.ai.schedule.Updatable;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface WardenBehavior extends CreatureBehavior, Updatable {
   @NotNull
   BehaviorResult setDisturbanceLocation(@NotNull Location var1);

   @NotNull
   BehaviorResult dig(int var1);

   @NotNull
   BehaviorResult emerge(int var1);

   @NotNull
   BehaviorResult roar();

   @NotNull
   BehaviorResult sonicBoom();

   @NotNull
   BehaviorResult sniff(int var1);
}
