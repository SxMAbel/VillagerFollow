package me.darksnakex.villagerfollow.mobchip.ai.gossip;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityGossipContainer {
   @NotNull
   Villager getEntity();

   void decay();

   int getReputation(@NotNull Entity var1, @Nullable GossipType... var2) throws IllegalArgumentException;

   default void put(@NotNull Entity en, @NotNull GossipType type) throws IllegalArgumentException {
      this.put(en, type, type.getDefaultMaxDecay());
   }

   void put(@NotNull Entity var1, @NotNull GossipType var2, int var3) throws IllegalArgumentException;

   void remove(@NotNull Entity var1, @NotNull GossipType var2) throws IllegalArgumentException;

   default void removeAll(@NotNull Entity en) throws IllegalArgumentException {
      GossipType[] var2 = GossipType.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         GossipType type = var2[var4];
         this.remove(en, type);
      }

   }

   void removeAll(@NotNull GossipType var1) throws IllegalArgumentException;
}
