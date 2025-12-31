package me.darksnakex.villagerfollow.mobchip.abstraction.v1_19_R3;

import java.util.Arrays;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.EntityGossipContainer;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.GossipType;
import net.minecraft.world.entity.ai.gossip.Reputation;
import org.bukkit.craftbukkit.v1_19_R3.entity.CraftVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class EntityGossipContainer1_19_R3 implements EntityGossipContainer {
   private final Reputation handle;
   private final Villager entity;

   public EntityGossipContainer1_19_R3(Villager v) {
      this.entity = v;
      this.handle = ((CraftVillager)v).getHandle().gn();
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
         return Arrays.asList(types).contains(ChipUtil1_19_R3.fromNMS(g));
      });
   }

   public void put(@NotNull Entity en, @NotNull GossipType type, int maxCap) throws IllegalArgumentException {
      this.handle.a(en.getUniqueId(), ChipUtil1_19_R3.toNMS(type), maxCap);
   }

   public void remove(@NotNull Entity en, @NotNull GossipType type) throws IllegalArgumentException {
      this.handle.a(en.getUniqueId(), ChipUtil1_19_R3.toNMS(type));
   }

   public void removeAll(@NotNull GossipType type) throws IllegalArgumentException {
      this.handle.a(ChipUtil1_19_R3.toNMS(type));
   }
}
