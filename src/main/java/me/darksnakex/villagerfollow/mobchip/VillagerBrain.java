package me.darksnakex.villagerfollow.mobchip;

import me.darksnakex.villagerfollow.mobchip.ai.gossip.EntityGossipContainer;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;

public interface VillagerBrain extends EntityBrain {
   @NotNull
   EntityGossipContainer getGossipContainer();

   @NotNull
   Villager getEntity();
}
