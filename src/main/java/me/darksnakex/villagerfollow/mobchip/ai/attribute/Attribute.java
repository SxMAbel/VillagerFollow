package me.darksnakex.villagerfollow.mobchip.ai.attribute;

import org.bukkit.Keyed;

public interface Attribute extends Keyed {
   double getMinValue();

   double getMaxValue();

   double getDefaultValue();

   boolean isClientSide();
}
