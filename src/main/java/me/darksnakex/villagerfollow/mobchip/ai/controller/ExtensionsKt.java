package me.darksnakex.villagerfollow.mobchip.ai.controller;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {2, 0, 0},
   k = 2,
   xi = 48,
   d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004\u001a$\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b\u001a.\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\b\b\u0002\u0010\u000b\u001a\u00020\t\u001a,\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\r\u001a\u00020\u000e\u001a\u001e\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u0010¨\u0006\u0012"},
   d2 = {"deltaMovement", "", "Lme/darksnakex/villagerfollow/mobchip/ai/controller/EntityController;", "delta", "Lkotlin/Function1;", "Lorg/bukkit/util/Vector;", "lookAt", "target", "Lkotlin/Triple;", "", "moveTo", "speedModifier", "naturalMoveTo", "type", "Lme/darksnakex/villagerfollow/mobchip/ai/controller/NaturalMoveType;", "strafe", "Lkotlin/Pair;", "", "mobchip-bukkit"}
)
public final class ExtensionsKt {
   public static final void deltaMovement(@NotNull EntityController $this$deltaMovement, @NotNull Function1<? super Vector, Unit> delta) {
      Intrinsics.checkNotNullParameter($this$deltaMovement, "<this>");
      Intrinsics.checkNotNullParameter(delta, "delta");
      Vector var10000 = $this$deltaMovement.getDeltaMovement();
      Intrinsics.checkNotNullExpressionValue(var10000, "getDeltaMovement(...)");
      Vector vector = var10000;
      delta.invoke(vector);
      $this$deltaMovement.setDeltaMovement(vector);
   }

   public static final void lookAt(@NotNull EntityController $this$lookAt, @NotNull Triple<Double, Double, Double> target) {
      Intrinsics.checkNotNullParameter($this$lookAt, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      double x = ((Number)target.component1()).doubleValue();
      double y = ((Number)target.component2()).doubleValue();
      double z = ((Number)target.component3()).doubleValue();
      $this$lookAt.lookAt(x, y, z);
   }

   public static final void moveTo(@NotNull EntityController $this$moveTo, @NotNull Triple<Double, Double, Double> target, double speedModifier) {
      Intrinsics.checkNotNullParameter($this$moveTo, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      double x = ((Number)target.component1()).doubleValue();
      double y = ((Number)target.component2()).doubleValue();
      double z = ((Number)target.component3()).doubleValue();
      $this$moveTo.moveTo(x, y, z, speedModifier);
   }

   // $FF: synthetic method
   public static void moveTo$default(EntityController var0, Triple var1, double var2, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 1.0D;
      }

      moveTo(var0, var1, var2);
   }

   public static final void naturalMoveTo(@NotNull EntityController $this$naturalMoveTo, @NotNull Triple<Double, Double, Double> target, @NotNull NaturalMoveType type) {
      Intrinsics.checkNotNullParameter($this$naturalMoveTo, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      Intrinsics.checkNotNullParameter(type, "type");
      double x = ((Number)target.component1()).doubleValue();
      double y = ((Number)target.component2()).doubleValue();
      double z = ((Number)target.component3()).doubleValue();
      $this$naturalMoveTo.naturalMoveTo(x, y, z, type);
   }

   public static final void strafe(@NotNull EntityController $this$strafe, @NotNull Pair<Float, Float> target) {
      Intrinsics.checkNotNullParameter($this$strafe, "<this>");
      Intrinsics.checkNotNullParameter(target, "target");
      float fwd = ((Number)target.component1()).floatValue();
      float right = ((Number)target.component2()).floatValue();
      $this$strafe.strafe(fwd, right);
   }
}
