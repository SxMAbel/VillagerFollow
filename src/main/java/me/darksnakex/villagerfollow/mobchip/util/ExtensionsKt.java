package me.darksnakex.villagerfollow.mobchip.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 0, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u001a\u0010\u0000\u001a\u00070\u0001¢\u0006\u0002\b\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0086\u0002\u001a\u001a\u0010\u0004\u001a\u00070\u0001¢\u0006\u0002\b\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0086\u0002\u001a\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0086\u0002\u001a\u0015\u0010\u0007\u001a\u00020\u0006*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0086\u0002\u001a\u0012\u0010\b\u001a\u00070\u0001¢\u0006\u0002\b\u0002*\u00020\u0001H\u0086\u0002\u001a\u0012\u0010\t\u001a\u00070\u0001¢\u0006\u0002\b\u0002*\u00020\u0001H\u0086\u0002\u001a\u001c\u0010\n\u001a\t\u0018\u00010\u0001¢\u0006\u0002\b\u000b*\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086\u0002¨\u0006\u000f"},
   d2 = {"plus", "Lme/darksnakex/villagerfollow/mobchip/util/Position;", "Lorg/jetbrains/annotations/NotNull;", "other", "minus", "plusAssign", "", "minusAssign", "inc", "dec", "get", "Lorg/jetbrains/annotations/Nullable;", "Lme/darksnakex/villagerfollow/mobchip/util/PositionPath;", "index", "", "mobchip-base"}
)
public final class ExtensionsKt {
   @NotNull
   public static final Position plus(@NotNull Position $this$plus, @NotNull Position other) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      Position var10000 = $this$plus.clone().add(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
      return var10000;
   }

   @NotNull
   public static final Position minus(@NotNull Position $this$minus, @NotNull Position other) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      Position var10000 = $this$minus.clone().remove(other);
      Intrinsics.checkNotNullExpressionValue(var10000, "remove(...)");
      return var10000;
   }

   public static final void plusAssign(@NotNull Position $this$plusAssign, @NotNull Position other) {
      Intrinsics.checkNotNullParameter($this$plusAssign, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      $this$plusAssign.add(other);
   }

   public static final void minusAssign(@NotNull Position $this$minusAssign, @NotNull Position other) {
      Intrinsics.checkNotNullParameter($this$minusAssign, "<this>");
      Intrinsics.checkNotNullParameter(other, "other");
      $this$minusAssign.remove(other);
   }

   @NotNull
   public static final Position inc(@NotNull Position $this$inc) {
      Intrinsics.checkNotNullParameter($this$inc, "<this>");
      Position var10000 = $this$inc.add(1, 1, 1);
      Intrinsics.checkNotNullExpressionValue(var10000, "add(...)");
      return var10000;
   }

   @NotNull
   public static final Position dec(@NotNull Position $this$dec) {
      Intrinsics.checkNotNullParameter($this$dec, "<this>");
      Position var10000 = $this$dec.remove(1, 1, 1);
      Intrinsics.checkNotNullExpressionValue(var10000, "remove(...)");
      return var10000;
   }

   @Nullable
   public static final Position get(@NotNull PositionPath $this$get, int index) {
      Intrinsics.checkNotNullParameter($this$get, "<this>");
      return $this$get.getPosition(index);
   }
}
