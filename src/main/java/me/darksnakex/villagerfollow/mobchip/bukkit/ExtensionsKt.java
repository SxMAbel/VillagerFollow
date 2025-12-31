package me.darksnakex.villagerfollow.mobchip.bukkit;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import me.darksnakex.villagerfollow.mobchip.DragonBrain;
import me.darksnakex.villagerfollow.mobchip.EntityBrain;
import me.darksnakex.villagerfollow.mobchip.VillagerBrain;
import me.darksnakex.villagerfollow.mobchip.ai.EntityAI;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.CreatureBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.DragonBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.EntityBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.VillagerBehavior;
import me.darksnakex.villagerfollow.mobchip.ai.controller.EntityController;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.EntityGossipContainer;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.EntityScheduleManager;
import me.darksnakex.villagerfollow.mobchip.ai.sensing.EntitySenses;
import me.darksnakex.villagerfollow.mobchip.combat.EntityCombatTracker;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Villager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {2, 0, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0006\"\u0016\u0010\u0000\u001a\u00020\u0007*\u00020\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\t\"\u0016\u0010\u0000\u001a\u00020\n*\u00020\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0003\u0010\f\"\u0016\u0010\r\u001a\u00020\u000e*\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u0016\u0010\u0011\u001a\u00020\u000e*\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010\"\u0016\u0010\u0013\u001a\u00020\u0014*\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\"\u0016\u0010\u0017\u001a\u00020\u0018*\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\"\u0016\u0010\u001b\u001a\u00020\u001c*\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\"\u0016\u0010\u001f\u001a\u00020 *\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010\"\"\u0016\u0010#\u001a\u00020$*\u00020\u00058Æ\u0002¢\u0006\u0006\u001a\u0004\b%\u0010&\"\u0016\u0010#\u001a\u00020'*\u00020(8Æ\u0002¢\u0006\u0006\u001a\u0004\b%\u0010)\"\u0016\u0010#\u001a\u00020**\u00020\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b%\u0010+\"\u0016\u0010#\u001a\u00020,*\u00020\u000b8Æ\u0002¢\u0006\u0006\u001a\u0004\b%\u0010-\"\u0016\u0010.\u001a\u00020/*\u00020\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b0\u00101¨\u00062"},
   d2 = {"brain", "Lme/darksnakex/villagerfollow/mobchip/EntityBrain;", "Lorg/bukkit/entity/Entity;", "getBrain", "(Lorg/bukkit/entity/Entity;)Lme/gamercoder215/mobchip/EntityBrain;", "Lorg/bukkit/entity/Mob;", "(Lorg/bukkit/entity/Mob;)Lme/gamercoder215/mobchip/EntityBrain;", "Lme/darksnakex/villagerfollow/mobchip/VillagerBrain;", "Lorg/bukkit/entity/Villager;", "(Lorg/bukkit/entity/Villager;)Lme/gamercoder215/mobchip/VillagerBrain;", "Lme/darksnakex/villagerfollow/mobchip/DragonBrain;", "Lorg/bukkit/entity/EnderDragon;", "(Lorg/bukkit/entity/EnderDragon;)Lme/gamercoder215/mobchip/DragonBrain;", "goalSelector", "Lme/darksnakex/villagerfollow/mobchip/ai/EntityAI;", "getGoalSelector", "(Lorg/bukkit/entity/Mob;)Lme/gamercoder215/mobchip/ai/EntityAI;", "targetSelector", "getTargetSelector", "controller", "Lme/darksnakex/villagerfollow/mobchip/ai/controller/EntityController;", "getController", "(Lorg/bukkit/entity/Mob;)Lme/gamercoder215/mobchip/ai/controller/EntityController;", "combatTracker", "Lme/darksnakex/villagerfollow/mobchip/combat/EntityCombatTracker;", "getCombatTracker", "(Lorg/bukkit/entity/Mob;)Lme/gamercoder215/mobchip/combat/EntityCombatTracker;", "senses", "Lme/darksnakex/villagerfollow/mobchip/ai/sensing/EntitySenses;", "getSenses", "(Lorg/bukkit/entity/Mob;)Lme/gamercoder215/mobchip/ai/sensing/EntitySenses;", "scheduleManager", "Lme/darksnakex/villagerfollow/mobchip/ai/schedule/EntityScheduleManager;", "getScheduleManager", "(Lorg/bukkit/entity/Mob;)Lme/gamercoder215/mobchip/ai/schedule/EntityScheduleManager;", "behavior", "Lme/darksnakex/villagerfollow/mobchip/ai/behavior/EntityBehavior;", "getBehavior", "(Lorg/bukkit/entity/Mob;)Lme/gamercoder215/mobchip/ai/behavior/EntityBehavior;", "Lme/darksnakex/villagerfollow/mobchip/ai/behavior/CreatureBehavior;", "Lorg/bukkit/entity/Creature;", "(Lorg/bukkit/entity/Creature;)Lme/gamercoder215/mobchip/ai/behavior/CreatureBehavior;", "Lme/darksnakex/villagerfollow/mobchip/ai/behavior/VillagerBehavior;", "(Lorg/bukkit/entity/Villager;)Lme/gamercoder215/mobchip/ai/behavior/VillagerBehavior;", "Lme/darksnakex/villagerfollow/mobchip/ai/behavior/DragonBehavior;", "(Lorg/bukkit/entity/EnderDragon;)Lme/gamercoder215/mobchip/ai/behavior/DragonBehavior;", "gossipContainer", "Lme/darksnakex/villagerfollow/mobchip/ai/gossip/EntityGossipContainer;", "getGossipContainer", "(Lorg/bukkit/entity/Villager;)Lme/gamercoder215/mobchip/ai/gossip/EntityGossipContainer;", "mobchip-bukkit"}
)
@SourceDebugExtension({"SMAP\nextensions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 extensions.kt\nme/gamercoder215/mobchip/bukkit/ExtensionsKt\n*L\n1#1,130:1\n33#1:131\n33#1:132\n33#1:133\n33#1:134\n33#1:135\n33#1:136\n33#1:137\n33#1:138\n33#1:139\n33#1:140\n40#1:141\n33#1:142\n47#1:143\n40#1:144\n33#1:145\n*S KotlinDebug\n*F\n+ 1 extensions.kt\nme/gamercoder215/mobchip/bukkit/ExtensionsKt\n*L\n25#1:131\n40#1:132\n54#1:133\n61#1:134\n68#1:135\n75#1:136\n82#1:137\n89#1:138\n98#1:139\n105#1:140\n112#1:141\n112#1:142\n119#1:143\n128#1:144\n128#1:145\n*E\n"})
public final class ExtensionsKt {
   @Nullable
   public static final EntityBrain getBrain(@NotNull Entity $this$brain) {
      Intrinsics.checkNotNullParameter($this$brain, "<this>");
      int $i$f$getBrain = false;
      if (!($this$brain instanceof Mob)) {
         return null;
      } else {
         Mob $this$brain$iv = (Mob)$this$brain;
         int $i$f$getBrain = false;
         EntityBrain var10000 = BukkitBrain.getBrain($this$brain$iv);
         Intrinsics.checkNotNull(var10000);
         return var10000;
      }
   }

   @NotNull
   public static final EntityBrain getBrain(@NotNull Mob $this$brain) {
      Intrinsics.checkNotNullParameter($this$brain, "<this>");
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$brain);
      Intrinsics.checkNotNull(var10000);
      return var10000;
   }

   @NotNull
   public static final VillagerBrain getBrain(@NotNull Villager $this$brain) {
      Intrinsics.checkNotNullParameter($this$brain, "<this>");
      int $i$f$getBrain = false;
      Mob $this$brain$iv = (Mob)$this$brain;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$brain$iv);
      Intrinsics.checkNotNull(var10000);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type me.gamercoder215.mobchip.VillagerBrain");
      return (VillagerBrain)var10000;
   }

   @NotNull
   public static final DragonBrain getBrain(@NotNull EnderDragon $this$brain) {
      Intrinsics.checkNotNullParameter($this$brain, "<this>");
      int $i$f$getBrain = false;
      DragonBrain var10000 = BukkitBrain.getBrain($this$brain);
      Intrinsics.checkNotNull(var10000);
      return var10000;
   }

   @NotNull
   public static final EntityAI getGoalSelector(@NotNull Mob $this$goalSelector) {
      Intrinsics.checkNotNullParameter($this$goalSelector, "<this>");
      int $i$f$getGoalSelector = false;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$goalSelector);
      Intrinsics.checkNotNull(var10000);
      EntityAI var4 = var10000.getGoalAI();
      Intrinsics.checkNotNullExpressionValue(var4, "getGoalAI(...)");
      return var4;
   }

   @NotNull
   public static final EntityAI getTargetSelector(@NotNull Mob $this$targetSelector) {
      Intrinsics.checkNotNullParameter($this$targetSelector, "<this>");
      int $i$f$getTargetSelector = false;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$targetSelector);
      Intrinsics.checkNotNull(var10000);
      EntityAI var4 = var10000.getTargetAI();
      Intrinsics.checkNotNullExpressionValue(var4, "getTargetAI(...)");
      return var4;
   }

   @NotNull
   public static final EntityController getController(@NotNull Mob $this$controller) {
      Intrinsics.checkNotNullParameter($this$controller, "<this>");
      int $i$f$getController = false;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$controller);
      Intrinsics.checkNotNull(var10000);
      EntityController var4 = var10000.getController();
      Intrinsics.checkNotNullExpressionValue(var4, "getController(...)");
      return var4;
   }

   @NotNull
   public static final EntityCombatTracker getCombatTracker(@NotNull Mob $this$combatTracker) {
      Intrinsics.checkNotNullParameter($this$combatTracker, "<this>");
      int $i$f$getCombatTracker = false;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$combatTracker);
      Intrinsics.checkNotNull(var10000);
      EntityCombatTracker var4 = var10000.getCombatTracker();
      Intrinsics.checkNotNullExpressionValue(var4, "getCombatTracker(...)");
      return var4;
   }

   @NotNull
   public static final EntitySenses getSenses(@NotNull Mob $this$senses) {
      Intrinsics.checkNotNullParameter($this$senses, "<this>");
      int $i$f$getSenses = false;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$senses);
      Intrinsics.checkNotNull(var10000);
      EntitySenses var4 = var10000.getSenses();
      Intrinsics.checkNotNullExpressionValue(var4, "getSenses(...)");
      return var4;
   }

   @NotNull
   public static final EntityScheduleManager getScheduleManager(@NotNull Mob $this$scheduleManager) {
      Intrinsics.checkNotNullParameter($this$scheduleManager, "<this>");
      int $i$f$getScheduleManager = false;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$scheduleManager);
      Intrinsics.checkNotNull(var10000);
      EntityScheduleManager var4 = var10000.getScheduleManager();
      Intrinsics.checkNotNullExpressionValue(var4, "getScheduleManager(...)");
      return var4;
   }

   @NotNull
   public static final EntityBehavior getBehavior(@NotNull Mob $this$behavior) {
      Intrinsics.checkNotNullParameter($this$behavior, "<this>");
      int $i$f$getBehavior = false;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$behavior);
      Intrinsics.checkNotNull(var10000);
      EntityBehavior var4 = var10000.getBehaviors();
      Intrinsics.checkNotNullExpressionValue(var4, "getBehaviors(...)");
      return var4;
   }

   @NotNull
   public static final CreatureBehavior getBehavior(@NotNull Creature $this$behavior) {
      Intrinsics.checkNotNullParameter($this$behavior, "<this>");
      int $i$f$getBehavior = false;
      Mob $this$brain$iv = (Mob)$this$behavior;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$brain$iv);
      Intrinsics.checkNotNull(var10000);
      EntityBehavior var4 = var10000.getBehaviors();
      Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type me.gamercoder215.mobchip.ai.behavior.CreatureBehavior");
      return (CreatureBehavior)var4;
   }

   @NotNull
   public static final VillagerBehavior getBehavior(@NotNull Villager $this$behavior) {
      Intrinsics.checkNotNullParameter($this$behavior, "<this>");
      int $i$f$getBehavior = false;
      int $i$f$getBrain = false;
      Mob $this$brain$iv$iv = (Mob)$this$behavior;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$brain$iv$iv);
      Intrinsics.checkNotNull(var10000);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type me.gamercoder215.mobchip.VillagerBrain");
      EntityBehavior var6 = ((VillagerBrain)var10000).getBehaviors();
      Intrinsics.checkNotNull(var6, "null cannot be cast to non-null type me.gamercoder215.mobchip.ai.behavior.VillagerBehavior");
      return (VillagerBehavior)var6;
   }

   @NotNull
   public static final DragonBehavior getBehavior(@NotNull EnderDragon $this$behavior) {
      Intrinsics.checkNotNullParameter($this$behavior, "<this>");
      int $i$f$getBehavior = false;
      int $i$f$getBrain = false;
      DragonBrain var10000 = BukkitBrain.getBrain($this$behavior);
      Intrinsics.checkNotNull(var10000);
      EntityBehavior var4 = var10000.getBehaviors();
      Intrinsics.checkNotNull(var4, "null cannot be cast to non-null type me.gamercoder215.mobchip.ai.behavior.DragonBehavior");
      return (DragonBehavior)var4;
   }

   @NotNull
   public static final EntityGossipContainer getGossipContainer(@NotNull Villager $this$gossipContainer) {
      Intrinsics.checkNotNullParameter($this$gossipContainer, "<this>");
      int $i$f$getGossipContainer = false;
      int $i$f$getBrain = false;
      Mob $this$brain$iv$iv = (Mob)$this$gossipContainer;
      int $i$f$getBrain = false;
      EntityBrain var10000 = BukkitBrain.getBrain($this$brain$iv$iv);
      Intrinsics.checkNotNull(var10000);
      Intrinsics.checkNotNull(var10000, "null cannot be cast to non-null type me.gamercoder215.mobchip.VillagerBrain");
      EntityGossipContainer var6 = ((VillagerBrain)var10000).getGossipContainer();
      Intrinsics.checkNotNullExpressionValue(var6, "getGossipContainer(...)");
      return var6;
   }
}
