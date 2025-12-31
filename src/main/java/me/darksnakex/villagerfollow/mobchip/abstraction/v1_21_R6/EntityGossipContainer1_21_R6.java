package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R6;

import java.util.Arrays;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.EntityGossipContainer;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.GossipType;
import net.minecraft.world.entity.ai.gossip.Reputation;
import org.bukkit.craftbukkit.v1_21_R6.entity.CraftVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.ReputationEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class EntityGossipContainer1_21_R6 implements EntityGossipContainer {
   private final Reputation handle;
   private final Villager entity;

   public EntityGossipContainer1_21_R6(Villager v) {
      this.entity = v;
      this.handle = ((CraftVillager)v).getHandle().hd();
   }

   @NotNull
   public Villager getEntity() {
      return this.entity;
   }

   public void decay() {
      this.handle.b();
   }

   public int getReputation(@NotNull Entity en, @Nullable GossipType... types) throws IllegalArgumentException {
      return this.handle.a(en.getUniqueId(), (g) -> {
         return Arrays.asList(types).contains(ChipUtil1_21_R6.fromNMS(g));
      });
   }

   public void put(@NotNull Entity en, @NotNull GossipType type, int maxCap) throws IllegalArgumentException {
      this.handle.add(en.getUniqueId(), ChipUtil1_21_R6.toNMS(type), maxCap, ReputationEvent.UNSPECIFIED);
   }

   public void remove(@NotNull Entity en, @NotNull GossipType type) throws IllegalArgumentException {
      this.handle.remove(en.getUniqueId(), ChipUtil1_21_R6.toNMS(type), ReputationEvent.UNSPECIFIED);
   }

   public void removeAll(@NotNull GossipType type) throws IllegalArgumentException {
      this.handle.remove(ChipUtil1_21_R6.toNMS(type), ReputationEvent.UNSPECIFIED);
   }
}
