package me.darksnakex.villagerfollow.mobchip.combat;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityCombatTracker {
   @NotNull
   String getCurrentDeathMessage();

   @Nullable
   CombatEntry getLatestEntry();

   @NotNull
   List<CombatEntry> getCombatEntries();

   void recordEntry(@NotNull CombatEntry var1);

   int getCombatDuration();

   boolean isTakingDamage();

   boolean isInCombat();

   boolean hasLastDamageCancelled();
}
