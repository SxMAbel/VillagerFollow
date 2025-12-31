package me.darksnakex.villagerfollow.mobchip.abstraction.v1_17_R1;

import me.darksnakex.villagerfollow.mobchip.ai.controller.EntityController;
import me.darksnakex.villagerfollow.mobchip.ai.controller.NaturalMoveType;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EnumMoveType;
import net.minecraft.world.entity.ai.control.ControllerJump;
import net.minecraft.world.entity.ai.control.ControllerLook;
import net.minecraft.world.entity.ai.control.ControllerMove;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Location;
import org.bukkit.entity.Mob;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

final class EntityController1_17_R1 implements EntityController {
   private final ControllerJump jumpC;
   private final ControllerMove moveC;
   private final ControllerLook lookC;
   private final Mob m;
   private final EntityInsentient nms;

   public EntityController1_17_R1(Mob m) {
      EntityInsentient nms = ChipUtil1_17_R1.toNMS(m);
      this.lookC = nms.getControllerLook();
      this.moveC = nms.getControllerMove();
      this.jumpC = nms.getControllerJump();
      this.m = m;
      this.nms = nms;
   }

   public EntityController jump() {
      this.jumpC.jump();
      this.jumpC.b();
      return this;
   }

   public boolean isLookingAtTarget() {
      Vector dir = this.m.getLocation().getDirection();
      int x = dir.getBlockX();
      int y = dir.getBlockY();
      int z = dir.getBlockZ();
      return this.lookC.e() == (double)x && this.lookC.f() == (double)y && this.lookC.g() == (double)z;
   }

   public EntityController moveTo(double x, double y, double z, double speedMod) {
      this.moveC.a(x, y, z, speedMod);
      this.moveC.a();
      this.nms.getNavigation().a(this.moveC.d(), this.moveC.e(), this.moveC.f(), this.moveC.c());
      this.nms.getNavigation().c();
      return this;
   }

   public EntityController naturalMoveTo(double x, double y, double z, NaturalMoveType type) {
      Vec3D vec = new Vec3D(x, y, z);
      EnumMoveType var10000;
      switch(type) {
      case PLAYER:
         var10000 = EnumMoveType.b;
         break;
      case PISTON:
         var10000 = EnumMoveType.c;
         break;
      case SHULKER_BOX:
         var10000 = EnumMoveType.d;
         break;
      case SHULKER:
         var10000 = EnumMoveType.e;
         break;
      default:
         var10000 = EnumMoveType.a;
      }

      EnumMoveType m = var10000;
      this.nms.move(m, vec);
      return this;
   }

   public EntityController strafe(float fwd, float right) {
      this.moveC.a(fwd, right);
      this.moveC.a();
      this.nms.getNavigation().a(this.moveC.d(), this.moveC.e(), this.moveC.f(), this.moveC.c());
      this.nms.getNavigation().c();
      return this;
   }

   public double getCurrentSpeedModifier() {
      return this.moveC.c();
   }

   public Location getTargetMoveLocation() {
      return new Location(this.m.getWorld(), this.moveC.d(), this.moveC.e(), this.moveC.f());
   }

   public Location getTargetLookLocation() {
      return new Location(this.m.getWorld(), this.lookC.e(), this.lookC.f(), this.lookC.g());
   }

   public EntityController lookAt(double x, double y, double z) {
      this.lookC.a(x, y, z);
      this.lookC.a();
      return this;
   }

   @NotNull
   public Vector getDeltaMovement() {
      Vec3D delta = this.nms.getMot();
      return new Vector(delta.b, delta.c, delta.d);
   }

   public void setDeltaMovement(@NotNull Vector delta) {
      Vec3D vec = new Vec3D(delta.getX(), delta.getY(), delta.getZ());
      this.nms.setMot(vec);
   }

   public void stop() {
      this.nms.getNavigation().o();
   }
}
