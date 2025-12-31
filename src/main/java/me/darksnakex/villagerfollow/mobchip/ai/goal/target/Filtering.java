package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

public interface Filtering<T extends LivingEntity> {
   void setFilter(@NotNull Class<T> var1) throws IllegalArgumentException;

   Class<T> getFilter();
}
