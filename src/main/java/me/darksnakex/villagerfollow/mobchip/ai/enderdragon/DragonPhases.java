package me.darksnakex.villagerfollow.mobchip.ai.enderdragon;

import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EnderDragon.Phase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DragonPhases {
   private final EnderDragon d;
   public final DragonPhase BREATH_ATTACK;
   public final DragonPhase CHARGE_PLAYER;
   public final DragonPhase CIRCLING;
   public final DragonPhase DYING;
   public final DragonPhase FLY_TO_PORTAL;
   public final DragonPhase HOVER;
   public final DragonPhase LAND_ON_PORTAL;
   public final DragonPhase LEAVE_PORTAL;
   public final DragonPhase ROAR_BEFORE_ATTACK;
   public final DragonPhase SEARCH_FOR_BREATH_ATTACK_TARGET;
   public final DragonPhase STRAFING;
   private static final ChipUtil w = ChipUtil.getWrapper();

   public DragonPhases(@NotNull EnderDragon d) {
      this.d = d;
   }

   public static DragonPhase fromBukkit(@NotNull EnderDragon dragon, @Nullable Phase phase) throws IllegalArgumentException {
      return phase == null ? null : w.fromBukkit(dragon, phase);
   }

   private DragonPhase fromBukkit(@Nullable Phase phase) {
      return phase == null ? null : w.fromBukkit(this.d, phase);
   }
}
