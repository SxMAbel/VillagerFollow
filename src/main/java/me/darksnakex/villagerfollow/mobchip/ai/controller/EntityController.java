package me.darksnakex.villagerfollow.mobchip.ai.controller;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

public interface EntityController {
   EntityController jump();

   default EntityController lookAt(@NotNull Entity en) {
      return this.lookAt(en.getLocation());
   }

   default EntityController lookAt(@NotNull Location loc) {
      return this.lookAt(loc.getX(), loc.getY(), loc.getZ());
   }

   EntityController lookAt(double var1, double var3, double var5);

   default EntityController moveTo(@NotNull Entity en) {
      return this.moveTo(en, 1.0D);
   }

   default EntityController moveTo(@NotNull Entity en, double speedMod) {
      return this.moveTo(en.getLocation(), speedMod);
   }

   default EntityController moveTo(@NotNull Location loc) {
      return this.moveTo(loc, 1.0D);
   }

   default EntityController moveTo(@NotNull Location loc, double speedMod) {
      return this.moveTo(loc.getX(), loc.getY(), loc.getZ(), speedMod);
   }

   default EntityController moveTo(double x, double y, double z) {
      return this.moveTo(x, y, z, 1.0D);
   }

   EntityController moveTo(double var1, double var3, double var5, double var7);

   EntityController naturalMoveTo(double var1, double var3, double var5, NaturalMoveType var7);

   EntityController strafe(float var1, float var2);

   double getCurrentSpeedModifier();

   Location getTargetMoveLocation();

   Location getTargetLookLocation();

   boolean isLookingAtTarget();

   @NotNull
   Vector getDeltaMovement();

   void setDeltaMovement(@NotNull Vector var1);

   void stop();

   default void setDeltaMovement(double x, double y, double z) {
      this.setDeltaMovement(new Vector(x, y, z));
   }

   default double getDeltaMovementX() {
      return this.getDeltaMovement().getX();
   }

   default void setDeltaMovementX(double x) {
      this.setDeltaMovement(new Vector(x, this.getDeltaMovementY(), this.getDeltaMovementZ()));
   }

   default double getDeltaMovementY() {
      return this.getDeltaMovement().getY();
   }

   default void setDeltaMovementY(double y) {
      this.setDeltaMovement(new Vector(this.getDeltaMovementX(), y, this.getDeltaMovementZ()));
   }

   default double getDeltaMovementZ() {
      return this.getDeltaMovement().getZ();
   }

   default void setDeltaMovementZ(double z) {
      this.setDeltaMovement(new Vector(this.getDeltaMovementX(), this.getDeltaMovementY(), z));
   }
}
