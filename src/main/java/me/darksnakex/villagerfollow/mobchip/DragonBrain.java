package me.darksnakex.villagerfollow.mobchip;

import me.darksnakex.villagerfollow.mobchip.ai.enderdragon.CustomPhase;
import me.darksnakex.villagerfollow.mobchip.ai.enderdragon.DragonPhase;
import org.bukkit.entity.EnderCrystal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface DragonBrain extends EntityBrain {
   void setCustomPhase(@NotNull CustomPhase var1) throws IllegalArgumentException;

   @Nullable
   EnderCrystal getNearestCrystal();

   @NotNull
   DragonPhase getCurrentPhase();
}
