package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R3;

import me.darksnakex.villagerfollow.mobchip.ai.attribute.Attribute;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.attributes.AttributeRanged;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_21_R3.util.CraftNamespacedKey;
import org.jetbrains.annotations.NotNull;

final class Attribute1_21_R3 extends AttributeRanged implements Attribute {
   private final NamespacedKey key;
   private final double defaultV;
   private final double min;
   private final double max;

   public Attribute1_21_R3(AttributeRanged a) {
      super(a.c(), a.a(), a.d(), a.e());
      this.key = BuiltInRegistries.s.b(a) == null ? NamespacedKey.minecraft(a.c()) : CraftNamespacedKey.fromMinecraft(BuiltInRegistries.s.b(a));
      this.defaultV = a.a();
      this.min = a.d();
      this.max = a.e();
   }

   public Attribute1_21_R3(NamespacedKey key, double defaultV, double min, double max, boolean clientSide) {
      super("attribute.name." + key.getKey().toLowerCase(), defaultV, min, max);
      this.key = key;
      this.min = min;
      this.defaultV = defaultV;
      this.max = max;
      this.a(clientSide);
   }

   public double d() {
      return this.min;
   }

   public double a() {
      return this.defaultV;
   }

   public double e() {
      return this.max;
   }

   public boolean isClientSide() {
      return this.b();
   }

   @NotNull
   public NamespacedKey getKey() {
      return this.key;
   }
}
