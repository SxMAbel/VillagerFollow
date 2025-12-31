package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import me.darksnakex.villagerfollow.mobchip.ai.schedule.Updatable;

public interface BreezeBehavior extends CreatureBehavior, Updatable {
   void longJump();

   void shoot();

   void slide();
}
