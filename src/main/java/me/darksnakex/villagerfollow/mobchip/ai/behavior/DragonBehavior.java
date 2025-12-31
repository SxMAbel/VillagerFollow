package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import java.util.List;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DragonBehavior extends EntityBehavior {
   @NotNull
   BehaviorResult naturalKnockback(@Nullable List<Entity> var1);
}
