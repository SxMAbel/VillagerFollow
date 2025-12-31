package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import me.darksnakex.villagerfollow.mobchip.ai.schedule.Updatable;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

public interface FrogBehavior extends CreatureBehavior, Updatable {
   @NotNull
   BehaviorResult shootTongue(Sound var1, Sound var2);

   @NotNull
   BehaviorResult croak();
}
