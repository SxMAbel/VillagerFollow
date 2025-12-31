package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

public interface WorldSpecific {
   IllegalArgumentException WORLD_NULL = new IllegalArgumentException("World cannot be null");

   @NotNull
   World getWorld();

   void setWorld(@NotNull World var1) throws IllegalArgumentException;
}
