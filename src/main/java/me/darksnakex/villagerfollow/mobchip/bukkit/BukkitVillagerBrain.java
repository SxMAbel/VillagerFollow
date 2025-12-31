package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.VillagerBrain;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.EntityGossipContainer;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;

class BukkitVillagerBrain extends BukkitBrain implements VillagerBrain {
   final Villager v;

   BukkitVillagerBrain(@NotNull Villager v) {
      super(v);
      this.v = v;
   }

   @NotNull
   public EntityGossipContainer getGossipContainer() {
      return w.getGossipContainer(this.v);
   }

   @NotNull
   public Villager getEntity() {
      return this.v;
   }
}
