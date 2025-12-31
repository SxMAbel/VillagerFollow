package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import me.darksnakex.villagerfollow.mobchip.ai.schedule.Updatable;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface AllayBehavior extends CreatureBehavior, Updatable {
   @NotNull
   BehaviorResult hearNoteblock(@NotNull Location var1);
}
