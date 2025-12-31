package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import org.jetbrains.annotations.NotNull;

public interface VillagerBehavior extends CreatureBehavior {
   @NotNull
   BehaviorResult harvestFarmland();

   @NotNull
   BehaviorResult showTrades(int var1, int var2);

   @NotNull
   default BehaviorResult showTrades(int duration) {
      return this.showTrades(duration, duration);
   }

   @NotNull
   BehaviorResult resetProfession();

   @NotNull
   BehaviorResult giftHero(int var1);

   @NotNull
   BehaviorResult celebrateSurvivedRaid(int var1, int var2);

   @NotNull
   default BehaviorResult celebrateSurvivedRaid(int duration) {
      return this.celebrateSurvivedRaid(duration, duration);
   }

   @NotNull
   BehaviorResult findJobSite(float var1);

   default BehaviorResult findJobSite() {
      return this.findJobSite(1.5F);
   }

   @NotNull
   BehaviorResult findNearestVillage(int var1, float var2);

   @NotNull
   default BehaviorResult findNearestVillage(int minDistance) {
      return this.findNearestVillage(minDistance, 1.0F);
   }

   @NotNull
   BehaviorResult workAtJob();

   @NotNull
   BehaviorResult useBonemeal();
}
