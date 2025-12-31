package me.darksnakex.villagerfollow.mobchip;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import me.darksnakex.villagerfollow.mobchip.ai.animation.EntityAnimation;
import me.darksnakex.villagerfollow.mobchip.util.Position;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface EntityBody {
   boolean isLeftHanded();

   void setLeftHanded(boolean var1);

   boolean canBreatheUnderwater();

   boolean shouldDiscardFriction();

   void setDiscardFriction(boolean var1);

   EntityBody.InteractionResult interact(@NotNull Player var1, @Nullable EntityBody.InteractionHand var2);

   boolean isSensitiveToWater();

   boolean isAffectedByPotions();

   boolean isBlocking();

   float getArmorCoverPercentage();

   void useItem(@Nullable EntityBody.InteractionHand var1);

   boolean isUsingItem();

   boolean isFireImmune();

   boolean isSwinging();

   boolean canRideUnderwater();

   boolean isInvisibleTo(@Nullable Player var1);

   @NotNull
   EntityBody.InteractionHand getMainHand();

   List<ItemStack> getDefaultDrops();

   void setDefaultDrops(@Nullable ItemStack... var1);

   default void setDefaultDrops(@Nullable Iterable<ItemStack> drops) {
      if (drops != null) {
         this.setDefaultDrops((ItemStack[])ImmutableList.copyOf(drops).toArray(new ItemStack[0]));
      }
   }

   default void setDefaultDrops(@Nullable Material... drops) {
      if (drops != null) {
         ItemStack[] items = new ItemStack[drops.length];

         for(int i = 0; i < drops.length; ++i) {
            items[i] = new ItemStack(drops[i]);
         }

         this.setDefaultDrops(items);
      }
   }

   /** @deprecated */
   @Deprecated
   boolean isInCombat();

   float getFlyingSpeed();

   void setFlyingSpeed(float var1) throws IllegalArgumentException;

   boolean isForcingDrops();

   void setForcingDrops(boolean var1);

   boolean isMoving();

   static float normalizeRotation(float rotation) {
      return rotation > 360.0F ? rotation - (float)(360.0D * Math.floor((double)(rotation / 360.0F))) : rotation;
   }

   float getBodyRotation();

   void setBodyRotation(float var1);

   float getHeadRotation();

   void setHeadRotation(float var1);

   float getYaw();

   void setYaw(float var1);

   float getPitch();

   void setPitch(float var1);

   Set<? extends Entity> getCollideExemptions();

   void addCollideExemption(@NotNull Entity var1) throws IllegalArgumentException;

   default void addCollideExemptions(@NotNull Entity... entities) throws IllegalArgumentException {
      this.addCollideExemptions((Iterable)Arrays.asList(entities));
   }

   default void addCollideExemptions(@NotNull Iterable<Entity> entities) throws IllegalArgumentException {
      Iterator var2 = entities.iterator();

      while(var2.hasNext()) {
         Entity en = (Entity)var2.next();
         if (en == null) {
            throw new IllegalArgumentException("Collection cannot contain any null entities!");
         }

         this.addCollideExemption(en);
      }

   }

   void removeCollideExemption(@NotNull Entity var1) throws IllegalArgumentException;

   int getDroppedExperience();

   void setDroppedExperience(int var1) throws IllegalArgumentException;

   void playAnimation(@NotNull EntityAnimation var1);

   float getAnimationSpeed();

   void setAnimationSpeed(float var1) throws IllegalArgumentException;

   boolean hasVerticalCollision();

   void setVerticalCollision(boolean var1);

   boolean hasHorizontalCollision();

   void setHorizontalCollision(boolean var1);

   float getWalkDistance();

   float getMoveDistance();

   float getFlyDistance();

   boolean isImmuneToExplosions();

   boolean isPeacefulCompatible();

   boolean isInBubbleColumn();

   boolean isInvulnerableTo(@Nullable DamageCause var1);

   int getMaxFallDistance();

   boolean isPushableBy(@Nullable Entity var1);

   /** @deprecated */
   @Deprecated
   float getMaxUpStep();

   /** @deprecated */
   @Deprecated
   void setMaxUpStep(float var1);

   Position getLastLavaContact();

   void setRiptideTicks(int var1) throws IllegalArgumentException;

   default void stopRiptiding() {
      this.setRiptideTicks(0);
   }

   default void addRiptideTicks(int ticks) throws IllegalArgumentException {
      if (ticks < 0) {
         throw new IllegalArgumentException("Riptiding ticks cannot be negative!");
      } else {
         this.setRiptideTicks(this.getRiptideTicks() + ticks);
      }
   }

   int getRiptideTicks();

   default boolean isRiptiding() {
      return this.getRiptideTicks() == 0;
   }

   @NotNull
   Mob getEntity();

   boolean shouldRenderFrom(double var1, double var3, double var5);

   default boolean shouldRenderFrom(@NotNull Location l) {
      return !this.getEntity().getWorld().getUID().equals(l.getWorld().getUID()) ? false : this.shouldRenderFrom(l.getX(), l.getY(), l.getZ());
   }

   default boolean shouldRenderFrom(double dist) {
      return this.shouldRenderFromSqr(dist * dist);
   }

   boolean shouldRenderFromSqr(double var1);

   void sendTo(@NotNull Player var1) throws UnsupportedOperationException;

   void resetFallDistance();

   boolean isInUnloadedChunk();

   void naturalKnockback(double var1, double var3, double var5);

   void eat(@NotNull ItemStack var1);

   void setRotation(float var1, float var2);

   int getHurtTime();

   void setHurtTime(int var1);

   int getHurtDuration();

   void setHurtDuration(int var1);

   int getDeathTime();

   void setDeathTime(int var1);

   float getForwardSpeed();

   void setForwardSpeed(float var1);

   float getSidewaysSpeed();

   void setSidewaysSpeed(float var1);

   float getUpwardSpeed();

   void setUpwardSpeed(float var1);

   public static enum InteractionResult {
      SUCCESS,
      CONSUME,
      CONSUME_PARTIAL,
      PASS,
      FAIL;

      // $FF: synthetic method
      private static EntityBody.InteractionResult[] $values() {
         return new EntityBody.InteractionResult[]{SUCCESS, CONSUME, CONSUME_PARTIAL, PASS, FAIL};
      }
   }

   public static enum InteractionHand {
      MAIN_HAND,
      OFF_HAND;

      @NotNull
      public EquipmentSlot toEquipmentSlot() {
         return this == MAIN_HAND ? EquipmentSlot.HAND : EquipmentSlot.OFF_HAND;
      }

      @Nullable
      public static EntityBody.InteractionHand fromEquipmentSlot(@Nullable EquipmentSlot slot) {
         if (slot == null) {
            return null;
         } else {
            switch(slot) {
            case HAND:
               return MAIN_HAND;
            case OFF_HAND:
               return OFF_HAND;
            default:
               return null;
            }
         }
      }

      // $FF: synthetic method
      private static EntityBody.InteractionHand[] $values() {
         return new EntityBody.InteractionHand[]{MAIN_HAND, OFF_HAND};
      }
   }
}
