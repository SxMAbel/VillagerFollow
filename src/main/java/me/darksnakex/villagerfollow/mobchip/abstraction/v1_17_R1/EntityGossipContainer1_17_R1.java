package me.darksnakex.villagerfollow.mobchip.abstraction.v1_17_R1;

import java.util.Arrays;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.EntityGossipContainer;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.GossipType;
import net.minecraft.world.entity.ai.gossip.Reputation;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftVillager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class EntityGossipContainer1_17_R1 implements EntityGossipContainer {
   private final Reputation handle;
   private final Villager entity;

   public EntityGossipContainer1_17_R1(Villager v) {
      this.entity = v;
      this.handle = ((CraftVillager)v).getHandle().fT();
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
         return Arrays.asList(types).contains(ChipUtil1_17_R1.fromNMS(g));
      });
   }

   public void put(@NotNull Entity en, @NotNull GossipType type, int maxCap) throws IllegalArgumentException {
      this.handle.a(en.getUniqueId(), ChipUtil1_17_R1.toNMS(type), maxCap);
   }

   public void remove(@NotNull Entity en, @NotNull GossipType type) throws IllegalArgumentException {
      this.handle.a(en.getUniqueId(), ChipUtil1_17_R1.toNMS(type));
   }

   public void removeAll(@NotNull GossipType type) throws IllegalArgumentException {
      this.handle.a(ChipUtil1_17_R1.toNMS(type));
   }
}
