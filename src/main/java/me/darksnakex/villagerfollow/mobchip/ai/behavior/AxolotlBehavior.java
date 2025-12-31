package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import me.darksnakex.villagerfollow.mobchip.ai.schedule.Updatable;
import org.jetbrains.annotations.NotNull;

public interface AxolotlBehavior extends CreatureBehavior, Updatable {
   @NotNull
   BehaviorResult playDead();
}
