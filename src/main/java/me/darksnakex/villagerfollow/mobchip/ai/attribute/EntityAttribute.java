package me.darksnakex.villagerfollow.mobchip.ai.attribute;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EntityAttribute implements Attribute {
   private static final ChipUtil wrapper = ChipUtil.getWrapper();
   public static final Attribute GENERIC_MAX_HEALTH;
   public static final Attribute GENERIC_FOLLOW_RANGE;
   public static final Attribute GENERIC_KNOCKBACK_RESISTANCE;
   public static final Attribute GENERIC_MOVEMENT_SPEED;
   public static final Attribute GENERIC_FLYING_SPEED;
   public static final Attribute GENERIC_ATTACK_DAMAGE;
   public static final Attribute GENERIC_ATTACK_SPEED;
   public static final Attribute GENERIC_ARMOR;
   public static final Attribute GENERIC_ARMOR_TOUGHNESS;
   public static final Attribute GENERIC_ATTACK_KNOCKBACK;
   public static final Attribute GENERIC_LUCK;
   public static final Attribute ZOMBIE_SPAWN_REINFORCEMENTS;
   public static final Attribute HORSE_JUMP_STRENGTH;
   public static final Attribute MAX_ABSORPTION;
   public static final Attribute GRAVITY;
   public static final Attribute SCALE;
   public static final Attribute STEP_HEIGHT;
   public static final Attribute SAFE_FALL_DISTANCE;
   public static final Attribute FALL_DAMAGE_MULTIPLIER;
   public static final Attribute BLOCK_INTERACTION_RANGE;
   public static final Attribute ENTITY_INTERACTION_RANGE;
   public static final Attribute BLOCK_BREAK_SPEED;
   private Attribute handle;

   @Nullable
   public static Attribute fromBukkit(@Nullable org.bukkit.attribute.Attribute attribute) {
      return attribute == null ? null : new EntityAttribute(attribute);
   }

   private EntityAttribute(Attribute handle) {
      this.handle = handle;
   }

   private EntityAttribute(org.bukkit.attribute.Attribute bukkit) {
      try {
         Field[] var2 = EntityAttribute.class.getDeclaredFields();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Field f = var2[var4];
            if (Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers()) && f.getName().equals(bukkit.name())) {
               f.setAccessible(true);
               this.handle = (Attribute)f.get((Object)null);
               return;
            }
         }

      } catch (Exception var6) {
         throw new IllegalArgumentException(var6);
      }
   }

   public static EntityAttribute.Builder builder() {
      return new EntityAttribute.Builder();
   }

   public double getMinValue() {
      return this.handle.getMinValue();
   }

   public double getMaxValue() {
      return this.handle.getMaxValue();
   }

   public double getDefaultValue() {
      return this.handle.getDefaultValue();
   }

   @NotNull
   public NamespacedKey getKey() {
      return this.handle.getKey();
   }

   public boolean isClientSide() {
      return this.handle.isClientSide();
   }

   @Nullable
   public static EntityAttribute getAttribute(@Nullable NamespacedKey key) {
      if (key == null) {
         return null;
      } else {
         return !wrapper.existsAttribute(key) ? null : new EntityAttribute(wrapper.getAttribute(key));
      }
   }

   public static boolean exists(@Nullable NamespacedKey key) {
      return getAttribute(key) != null;
   }

   // $FF: synthetic method
   EntityAttribute(Attribute x0, Object x1) {
      this(x0);
   }

   static {
      GENERIC_MAX_HEALTH = wrapper.getDefaultAttribute("generic.max_health");
      GENERIC_FOLLOW_RANGE = wrapper.getDefaultAttribute("generic.follow_range");
      GENERIC_KNOCKBACK_RESISTANCE = wrapper.getDefaultAttribute("generic.knockback_resistance");
      GENERIC_MOVEMENT_SPEED = wrapper.getDefaultAttribute("generic.movement_speed");
      GENERIC_FLYING_SPEED = wrapper.getDefaultAttribute("generic.flying_speed");
      GENERIC_ATTACK_DAMAGE = wrapper.getDefaultAttribute("generic.attack_damage");
      GENERIC_ATTACK_SPEED = wrapper.getDefaultAttribute("generic.attack_speed");
      GENERIC_ARMOR = wrapper.getDefaultAttribute("generic.armor");
      GENERIC_ARMOR_TOUGHNESS = wrapper.getDefaultAttribute("generic.armor_toughness");
      GENERIC_ATTACK_KNOCKBACK = wrapper.getDefaultAttribute("generic.attack_knockback");
      GENERIC_LUCK = wrapper.getDefaultAttribute("generic.luck");
      ZOMBIE_SPAWN_REINFORCEMENTS = wrapper.getDefaultAttribute("zombie.spawn_reinforcements");
      HORSE_JUMP_STRENGTH = wrapper.getDefaultAttribute("horse.jump_strength");
      MAX_ABSORPTION = wrapper.getDefaultAttribute("generic.max_absorption");
      GRAVITY = wrapper.getDefaultAttribute("generic.gravity");
      SCALE = wrapper.getDefaultAttribute("generic.scale");
      STEP_HEIGHT = wrapper.getDefaultAttribute("generic.step_height");
      SAFE_FALL_DISTANCE = wrapper.getDefaultAttribute("generic.safe_fall_distance");
      FALL_DAMAGE_MULTIPLIER = wrapper.getDefaultAttribute("generic.fall_damage_multiplier");
      BLOCK_INTERACTION_RANGE = wrapper.getDefaultAttribute("player.block_interaction_range");
      ENTITY_INTERACTION_RANGE = wrapper.getDefaultAttribute("player.entity_interaction_range");
      BLOCK_BREAK_SPEED = wrapper.getDefaultAttribute("player.block_break_speed");
   }

   public static final class Builder {
      private NamespacedKey key;
      private double minValue;
      private double defaultValue;
      private double maxValue;
      private boolean clientSide;

      private Builder() {
         this.minValue = 1.0D;
         this.defaultValue = 1.0D;
         this.maxValue = 1024.0D;
         this.clientSide = true;
      }

      public EntityAttribute.Builder setKey(@NotNull NamespacedKey key) {
         this.key = key;
         return this;
      }

      public EntityAttribute.Builder setMinValue(double minValue) {
         this.minValue = minValue;
         return this;
      }

      public EntityAttribute.Builder setDefaultValue(double defaultValue) {
         this.defaultValue = defaultValue;
         return this;
      }

      public EntityAttribute.Builder setMaxValue(double maxValue) {
         this.maxValue = maxValue;
         return this;
      }

      public EntityAttribute.Builder setClientSide(boolean clientSide) {
         this.clientSide = clientSide;
         return this;
      }

      public EntityAttribute build() throws IllegalArgumentException, UnsupportedOperationException {
         if (!(this.minValue < 0.0D) && !(this.maxValue < 0.0D) && !(this.defaultValue < 0.0D)) {
            if (this.minValue > this.maxValue) {
               throw new IllegalArgumentException("Minimum must be less than maximum");
            } else if (!(this.defaultValue < this.minValue) && !(this.defaultValue > this.maxValue)) {
               if (this.key == null) {
                  throw new IllegalArgumentException("Key cannot be null");
               } else if (EntityAttribute.wrapper.existsAttribute(this.key)) {
                  throw new UnsupportedOperationException("Attribute already exists");
               } else {
                  return new EntityAttribute(EntityAttribute.wrapper.registerAttribute(this.key, this.minValue, this.defaultValue, this.maxValue, this.clientSide));
               }
            } else {
               throw new IllegalArgumentException("Default value must be between minimum and maximum");
            }
         } else {
            throw new IllegalArgumentException("Values cannot be negative");
         }
      }

      // $FF: synthetic method
      Builder(Object x0) {
         this();
      }
   }
}
