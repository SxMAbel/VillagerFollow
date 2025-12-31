package me.darksnakex.villagerfollow.mobchip.combat;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CombatEntry {
   private final Mob owner;
   private final DamageCause cause;
   private final int ticks;
   private final float health;
   private final float damage;
   private final CombatLocation location;
   private final float lastFallDistance;
   private final Entity attacker;

   private CombatEntry(@NotNull CombatEntry.Builder builder) {
      this.owner = builder.owner;
      this.cause = builder.cause;
      this.ticks = builder.ticks;
      this.health = builder.health;
      this.damage = builder.damage;
      this.location = builder.location;
      this.lastFallDistance = builder.fall;
      this.attacker = builder.attacker;
   }

   public CombatEntry(@NotNull Mob owner, @NotNull DamageCause cause, int ticks, float health, float damage) throws IllegalArgumentException {
      this(owner, cause, ticks, health, damage, 0.0F);
   }

   public CombatEntry(@NotNull Mob owner, @NotNull DamageCause cause, int ticks, float health, float damage, float fall) throws IllegalArgumentException {
      this(owner, cause, ticks, health, damage, fall, (CombatLocation)null);
   }

   public CombatEntry(@NotNull Mob owner, @NotNull DamageCause cause, int ticks, float health, float damage, float fall, @Nullable CombatLocation combatLocation) throws IllegalArgumentException {
      this(owner, cause, ticks, health, damage, fall, combatLocation, (Entity)null);
   }

   public CombatEntry(@NotNull Mob owner, @NotNull DamageCause cause, int ticks, float health, float damage, float fall, @Nullable CombatLocation combatLocation, @Nullable Entity attacker) throws IllegalArgumentException {
      if (ticks >= 0 && !(health < 0.0F) && !(damage < 0.0F) && !(fall < 0.0F)) {
         this.owner = owner;
         this.cause = cause;
         this.ticks = ticks;
         this.health = health;
         this.damage = damage;
         this.location = combatLocation;
         this.lastFallDistance = fall;
         this.attacker = attacker;
      } else {
         throw new IllegalArgumentException("Ticks, Health, Damage Amount, and Fall Damage cannot be less than 0");
      }
   }

   @NotNull
   public DamageCause getCause() {
      return this.cause;
   }

   /** @deprecated */
   @Deprecated
   public int getCombatTime() {
      return this.ticks;
   }

   @NotNull
   public Mob getOwner() {
      return this.owner;
   }

   public float getDamage() {
      return this.damage;
   }

   /** @deprecated */
   @Deprecated
   public float getHealthBeforeDamage() {
      return this.health;
   }

   public float getHealthAfterDamage() {
      return this.health - this.damage;
   }

   @Nullable
   public CombatLocation getLocation() {
      return this.location;
   }

   public boolean hasLocation() {
      return this.location != null;
   }

   /** @deprecated */
   @Deprecated
   @Nullable
   public Entity getAttacker() {
      return this.attacker;
   }

   public boolean hasAttacker() {
      return this.attacker != null;
   }

   public float getFallDistance() {
      return this.lastFallDistance;
   }

   public static CombatEntry.Builder builder() {
      return new CombatEntry.Builder();
   }

   // $FF: synthetic method
   CombatEntry(CombatEntry.Builder x0, Object x1) {
      this(x0);
   }

   public static final class Builder {
      Mob owner;
      DamageCause cause;
      int ticks;
      float health;
      float damage;
      CombatLocation location;
      float fall;
      Entity attacker;

      Builder() {
      }

      @NotNull
      public CombatEntry.Builder setOwner(@NotNull Mob owner) throws IllegalArgumentException {
         this.owner = owner;
         return this;
      }

      @NotNull
      public CombatEntry.Builder setCause(@NotNull DamageCause cause) throws IllegalArgumentException {
         this.cause = cause;
         return this;
      }

      @NotNull
      public CombatEntry.Builder setCombatDuration(int ticks) throws IllegalArgumentException {
         if (ticks < 0) {
            throw new IllegalArgumentException("Combat Duration cannot be less than 0");
         } else {
            this.ticks = ticks;
            return this;
         }
      }

      @NotNull
      public CombatEntry.Builder setHealthBeforeDamage(float health) throws IllegalArgumentException {
         if (health < 0.0F) {
            throw new IllegalArgumentException("Health cannot be less than 0");
         } else {
            this.health = health;
            return this;
         }
      }

      @NotNull
      public CombatEntry.Builder setDamage(float damage) throws IllegalArgumentException {
         if (damage < 0.0F) {
            throw new IllegalArgumentException("Damage cannot be less than 0");
         } else {
            this.damage = damage;
            return this;
         }
      }

      @NotNull
      public CombatEntry.Builder setLocation(@Nullable CombatLocation location) {
         this.location = location;
         return this;
      }

      @NotNull
      public CombatEntry.Builder setFallDistance(float fall) throws IllegalArgumentException {
         if (fall < 0.0F) {
            throw new IllegalArgumentException("Fall Distance cannot be less than 0");
         } else {
            this.fall = fall;
            return this;
         }
      }

      @NotNull
      public CombatEntry.Builder setAttacker(@Nullable Entity attacker) {
         this.attacker = attacker;
         return this;
      }

      @NotNull
      public CombatEntry build() throws IllegalStateException {
         if (this.owner == null) {
            throw new IllegalStateException("Owner of CombatEntry cannot be null");
         } else if (this.cause == null) {
            throw new IllegalStateException("DamageCause cannot be null");
         } else if (this.ticks < 0) {
            throw new IllegalStateException("Combat Duration cannot be less than 0");
         } else if (this.health < 0.0F) {
            throw new IllegalStateException("Health cannot be less than 0");
         } else if (this.damage < 0.0F) {
            throw new IllegalStateException("Damage cannot be less than 0");
         } else if (this.fall < 0.0F) {
            throw new IllegalStateException("Fall Distance cannot be less than 0");
         } else {
            return new CombatEntry(this);
         }
      }
   }
}
