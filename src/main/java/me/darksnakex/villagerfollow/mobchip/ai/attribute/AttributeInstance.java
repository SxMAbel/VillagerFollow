package me.darksnakex.villagerfollow.mobchip.ai.attribute;

import org.jetbrains.annotations.NotNull;

public interface AttributeInstance extends org.bukkit.attribute.AttributeInstance {
   /** @deprecated */
   @Deprecated
   default org.bukkit.attribute.Attribute getAttribute() {
      return null;
   }

   @NotNull
   Attribute getGenericAttribute();
}
