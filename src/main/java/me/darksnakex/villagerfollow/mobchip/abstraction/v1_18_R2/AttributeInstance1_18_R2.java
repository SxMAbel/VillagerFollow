package me.darksnakex.villagerfollow.mobchip.abstraction.v1_18_R2;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.stream.Collectors;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.Attribute;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_18_R2.attribute.CraftAttributeInstance;
import org.jetbrains.annotations.NotNull;

final class AttributeInstance1_18_R2 implements AttributeInstance {
   private final AttributeModifiable handle;
   private final Attribute a;

   public AttributeInstance1_18_R2(Attribute a, AttributeModifiable handle) {
      this.a = a;
      this.handle = handle;
   }

   @NotNull
   public Attribute getGenericAttribute() {
      return this.a;
   }

   public double getBaseValue() {
      return this.handle.b();
   }

   public void setBaseValue(double v) {
      this.handle.a(v);
   }

   @NotNull
   public Collection<AttributeModifier> getModifiers() {
      return (Collection)this.handle.c().stream().map(CraftAttributeInstance::convert).collect(Collectors.toSet());
   }

   public void addModifier(@NotNull AttributeModifier mod) {
      Preconditions.checkArgument(mod != null, "modifier");
      this.handle.c(CraftAttributeInstance.convert(mod));
   }

   public void removeModifier(@NotNull AttributeModifier mod) {
      Preconditions.checkArgument(mod != null, "modifier");
      this.handle.d(CraftAttributeInstance.convert(mod));
   }

   public double getValue() {
      return this.handle.f();
   }

   public double getDefaultValue() {
      return this.handle.a().a();
   }
}
