package me.darksnakex.villagerfollow.mobchip.ai.enderdragon;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.entity.EnderDragon;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 0, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"},
   d2 = {"phases", "Lme/darksnakex/villagerfollow/mobchip/ai/enderdragon/DragonPhases;", "Lorg/bukkit/entity/EnderDragon;", "getPhases", "(Lorg/bukkit/entity/EnderDragon;)Lme/gamercoder215/mobchip/ai/enderdragon/DragonPhases;", "mobchip-bukkit"}
)
public final class ExtensionsKt {
   @NotNull
   public static final DragonPhases getPhases(@NotNull EnderDragon $this$phases) {
      Intrinsics.checkNotNullParameter($this$phases, "<this>");
      int $i$f$getPhases = false;
      return new DragonPhases($this$phases);
   }
}
