package me.darksnakex.villagerfollow.mobchip.bosses;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Minion<T extends Mob> {
   private final Class<T> entityClazz;
   private double health;
   private final Map<EquipmentSlot, ItemStack> equipment;
   private final Map<AttributeInstance, Double> attributes;

   public Minion(@NotNull Class<T> clazz, double health, @Nullable Map<EquipmentSlot, ItemStack> equipment, @Nullable Map<AttributeInstance, Double> attributes) throws IllegalArgumentException {
      if (health <= 0.0D) {
         throw new IllegalArgumentException("Health cannot be less than or equal to 0");
      } else {
         this.entityClazz = clazz;
         this.health = health;
         if (attributes != null) {
            this.attributes = attributes;
         } else {
            this.attributes = new HashMap();
         }

         if (equipment != null) {
            this.equipment = equipment;
         } else {
            this.equipment = new EnumMap(EquipmentSlot.class);
         }

      }
   }

   public Minion(@NotNull Class<T> clazz, double health, @Nullable Map<EquipmentSlot, ItemStack> equipment) throws IllegalArgumentException {
      this(clazz, health, equipment, (Map)null);
   }

   public Minion(@NotNull Class<T> clazz, double health) throws IllegalArgumentException {
      this(clazz, health, (Map)null);
   }

   public Minion(@NotNull Class<T> clazz) throws IllegalArgumentException {
      this(clazz, 20.0D);
   }

   public Class<T> getEntityClass() {
      return this.entityClazz;
   }

   @Nullable
   public T spawn(@Nullable Location loc) {
      if (loc == null) {
         return null;
      } else {
         T mob = (Mob)loc.getWorld().spawn(loc, this.entityClazz);
         mob.setHealth(this.health);
         mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(this.health);
         Iterator var3 = this.equipment.keySet().iterator();

         while(var3.hasNext()) {
            EquipmentSlot s = (EquipmentSlot)var3.next();
            switch(s) {
            case HAND:
               mob.getEquipment().setItemInMainHand((ItemStack)this.equipment.get(s));
               break;
            case OFF_HAND:
               mob.getEquipment().setItemInOffHand((ItemStack)this.equipment.get(s));
               break;
            case FEET:
               mob.getEquipment().setBoots((ItemStack)this.equipment.get(s));
               break;
            case LEGS:
               mob.getEquipment().setLeggings((ItemStack)this.equipment.get(s));
               break;
            case CHEST:
               mob.getEquipment().setChestplate((ItemStack)this.equipment.get(s));
               break;
            case HEAD:
               mob.getEquipment().setHelmet((ItemStack)this.equipment.get(s));
            }
         }

         var3 = this.attributes.keySet().iterator();

         while(var3.hasNext()) {
            AttributeInstance a = (AttributeInstance)var3.next();
            a.setBaseValue((Double)this.attributes.get(a));
         }

         return mob;
      }
   }

   @Nullable
   public T spawn(@Nullable Boss<?> b) {
      if (b == null) {
         return null;
      } else {
         return b.getMob() == null ? null : this.spawn(b.getMob().getLocation());
      }
   }

   @Nullable
   public T spawn(@Nullable Entity en) {
      return en == null ? null : this.spawn(en.getLocation());
   }

   @NotNull
   public Map<AttributeInstance, Double> getAttributes() {
      return this.attributes;
   }

   public double getHealth() {
      return this.health;
   }

   public void setHealth(double health) {
      this.health = health;
   }

   public void addAttribute(@NotNull AttributeInstance inst, double value) {
      this.attributes.put(inst, value);
   }

   public void removeAttribute(@NotNull AttributeInstance inst) {
      this.attributes.remove(inst);
   }

   @NotNull
   public Map<EquipmentSlot, ItemStack> getEquipment() {
      return this.equipment;
   }

   public void setItem(@NotNull EquipmentSlot slot, @Nullable ItemStack item) {
      if (item != null) {
         this.equipment.put(slot, item);
      }
   }

   public void setHelmet(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.HEAD, item);
   }

   public void setChestplate(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.CHEST, item);
   }

   public void setLeggings(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.LEGS, item);
   }

   public void setBoots(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.FEET, item);
   }

   public void setMainhand(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.HAND, item);
   }

   public void setOffhand(@Nullable ItemStack item) {
      this.setItem(EquipmentSlot.OFF_HAND, item);
   }
}
