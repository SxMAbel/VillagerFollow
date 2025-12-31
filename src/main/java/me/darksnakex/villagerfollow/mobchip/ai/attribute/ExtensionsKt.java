package me.darksnakex.villagerfollow.mobchip.ai.attribute;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 0, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"},
   d2 = {"mobChip", "Lme/darksnakex/villagerfollow/mobchip/ai/attribute/Attribute;", "Lorg/bukkit/attribute/Attribute;", "getMobChip", "(Lorg/bukkit/attribute/Attribute;)Lme/gamercoder215/mobchip/ai/attribute/Attribute;", "mobchip-bukkit"}
)
public final class ExtensionsKt {
   @Nullable
   public static final Attribute getMobChip(@NotNull org.bukkit.attribute.Attribute $this$mobChip) {
      Intrinsics.checkNotNullParameter($this$mobChip, "<this>");
      int $i$f$getMobChip = false;
      return EntityAttribute.fromBukkit($this$mobChip);
   }
}
