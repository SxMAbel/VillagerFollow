package me.darksnakex.villagerfollow.mobchip.ai.memories;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 0, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002¨\u0006\u0003"},
   d2 = {"register", "", "Lme/darksnakex/villagerfollow/mobchip/ai/memories/Memory;", "mobchip-bukkit"}
)
public final class ExtensionsKt {
   public static final boolean register(@NotNull Memory<?> $this$register) throws IllegalStateException {
      Intrinsics.checkNotNullParameter($this$register, "<this>");
      return EntityMemories.registerMemory($this$register);
   }
}
