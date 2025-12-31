package me.darksnakex.villagerfollow.mobchip.bukkit;

import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.VillagerBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.memories.EntityMemory;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;

class BukkitVillagerBehavior extends BukkitCreatureBehavior implements VillagerBehavior {
   final Villager m;

   BukkitVillagerBehavior(Villager v) {
      super(v);
      this.m = v;
   }

   @NotNull
   public BehaviorResult harvestFarmland() {
      return this.run("BehaviorFarm", new Object[0]);
   }

   @NotNull
   public BehaviorResult showTrades(int minDuration, int maxDuration) {
      return this.run("BehaviorTradePlayer", new Object[]{minDuration, maxDuration});
   }

   @NotNull
   public BehaviorResult resetProfession() {
      wrapper.removeMemory(this.m, EntityMemory.JOB_SITE);
      return this.run("BehaviorProfession", new Object[0]);
   }

   @NotNull
   public BehaviorResult giftHero(int duration) {
      return this.run("BehaviorVillageHeroGift", new Object[]{duration});
   }

   @NotNull
   public BehaviorResult celebrateSurvivedRaid(int minDuration, int maxDuration) {
      return this.run("BehaviorCelebrate", new Object[]{minDuration, maxDuration});
   }

   @NotNull
   public BehaviorResult findJobSite(float speedMod) {
      return this.run("BehaviorPotentialJobSite", new Object[]{speedMod});
   }

   @NotNull
   public BehaviorResult findNearestVillage(int minDistance, float speedMod) {
      wrapper.removeMemory(this.m, EntityMemory.WALKING_TARGET);
      return this.run("BehaviorNearestVillage", new Object[]{speedMod, minDistance});
   }

   @NotNull
   public BehaviorResult workAtJob() {
      return this.run("BehaviorWork", new Object[0]);
   }

   @NotNull
   public BehaviorResult useBonemeal() {
      wrapper.removeMemory(this.m, EntityMemory.WALKING_TARGET);
      wrapper.removeMemory(this.m, EntityMemory.LOOKING_TARGET);
      return this.run("BehaviorBonemeal", new Object[0]);
   }
}
