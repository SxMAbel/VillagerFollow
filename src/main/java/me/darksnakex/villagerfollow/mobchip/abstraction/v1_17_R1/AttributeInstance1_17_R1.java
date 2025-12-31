package me.darksnakex.villagerfollow.mobchip.abstraction.v1_17_R1;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.stream.Collectors;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.Attribute;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_17_R1.attribute.CraftAttributeInstance;
import org.jetbrains.annotations.NotNull;

final class AttributeInstance1_17_R1 implements AttributeInstance {
   private final AttributeModifiable handle;
   private final Attribute a;

   public AttributeInstance1_17_R1(Attribute a, AttributeModifiable handle) {
      this.a = a;
      this.handle = handle;
   }

   @NotNull
   public Attribute getGenericAttribute() {
      return this.a;
   }

   public double getBaseValue() {
      return this.handle.getBaseValue();
   }

   public void setBaseValue(double v) {
      this.handle.setValue(v);
   }

   @NotNull
   public Collection<AttributeModifier> getModifiers() {
      return (Collection)this.handle.getModifiers().stream().map(CraftAttributeInstance::convert).collect(Collectors.toSet());
   }

   public void addModifier(@NotNull AttributeModifier mod) {
      Preconditions.checkArgument(mod != null, "modifier");
      this.handle.addModifier(CraftAttributeInstance.convert(mod));
   }

   public void removeModifier(@NotNull AttributeModifier mod) {
      Preconditions.checkArgument(mod != null, "modifier");
      this.handle.removeModifier(CraftAttributeInstance.convert(mod));
   }

   public double getValue() {
      return this.handle.getValue();
   }

   public double getDefaultValue() {
      return this.handle.getAttribute().getDefault();
   }
}
