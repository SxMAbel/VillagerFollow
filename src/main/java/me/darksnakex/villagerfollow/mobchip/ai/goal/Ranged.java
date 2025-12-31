package me.darksnakex.villagerfollow.mobchip.ai.goal;

public interface Ranged {
   int DEFAULT_ATTACK_INTERVAL = 100;
   float DEFAULT_ATTACK_RANGE = 6.0F;
   float DEFAULT_LOOK_RANGE = 5.0F;

   float getRange();

   void setRange(float var1);
}
