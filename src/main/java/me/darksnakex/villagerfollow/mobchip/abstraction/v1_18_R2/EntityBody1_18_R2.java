package me.darksnakex.villagerfollow.mobchip.abstraction.v1_18_R2;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import me.darksnakex.villagerfollow.mobchip.EntityBody;
import me.darksnakex.villagerfollow.mobchip.ai.animation.EntityAnimation;
import me.darksnakex.villagerfollow.mobchip.util.Position;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.world.EnumHand;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EnumMainHand;
import net.minecraft.world.entity.IEntitySelector;
import net.minecraft.world.entity.ai.control.ControllerMove;
import net.minecraft.world.level.block.Blocks;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class EntityBody1_18_R2 implements EntityBody {
   private final EntityInsentient nmsMob;
   private final Mob m;

   public EntityBody1_18_R2(Mob m) {
      this.m = m;
      this.nmsMob = ChipUtil1_18_R2.toNMS(m);
   }

   private void update() {
      PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(this.nmsMob.ae(), this.nmsMob.ai(), true);
      Iterator var2 = this.m.getWorld().getPlayers().iterator();

      while(var2.hasNext()) {
         Player p = (Player)var2.next();
         ((CraftPlayer)p).getHandle().b.a(packet);
      }

   }

   public boolean isLeftHanded() {
      return this.nmsMob.ft();
   }

   public void setLeftHanded(boolean leftHanded) {
      this.nmsMob.t(leftHanded);
   }

   public boolean canBreatheUnderwater() {
      return this.nmsMob.dx();
   }

   public boolean shouldDiscardFriction() {
      return this.nmsMob.dR();
   }

   public void setDiscardFriction(boolean discard) {
      this.nmsMob.p(discard);
   }

   public EntityBody.InteractionResult interact(@NotNull Player p, @Nullable EntityBody.InteractionHand hand) {
      EnumHand h;
      if (hand == EntityBody.InteractionHand.OFF_HAND) {
         h = EnumHand.b;
      } else {
         h = EnumHand.a;
      }

      EntityBody.InteractionResult var10000;
      switch(this.nmsMob.a(ChipUtil1_18_R2.toNMS(p), h)) {
      case a:
         var10000 = EntityBody.InteractionResult.SUCCESS;
         break;
      case b:
         var10000 = EntityBody.InteractionResult.CONSUME;
         break;
      case c:
         var10000 = EntityBody.InteractionResult.CONSUME_PARTIAL;
         break;
      case e:
         var10000 = EntityBody.InteractionResult.FAIL;
         break;
      default:
         var10000 = EntityBody.InteractionResult.PASS;
      }

      return var10000;
   }

   public boolean isSensitiveToWater() {
      return this.nmsMob.eE();
   }

   public boolean isAffectedByPotions() {
      return this.nmsMob.eX();
   }

   public boolean isBlocking() {
      return this.nmsMob.eT();
   }

   public float getArmorCoverPercentage() {
      return this.nmsMob.eu();
   }

   public void useItem(@Nullable EntityBody.InteractionHand hand) {
      if (hand != null) {
         EnumHand h;
         if (hand == EntityBody.InteractionHand.OFF_HAND) {
            h = EnumHand.b;
         } else {
            h = EnumHand.a;
         }

         this.nmsMob.c(h);
      }
   }

   public boolean isUsingItem() {
      return this.nmsMob.eM();
   }

   public boolean isFireImmune() {
      return this.nmsMob.aP();
   }

   public boolean isSwinging() {
      return this.nmsMob.aF;
   }

   public boolean canRideUnderwater() {
      return this.nmsMob.bH();
   }

   public boolean isInvisibleTo(@Nullable Player p) {
      return this.nmsMob.c(ChipUtil1_18_R2.toNMS(p));
   }

   @NotNull
   public EntityBody.InteractionHand getMainHand() {
      return this.nmsMob.eL() == EnumMainHand.a ? EntityBody.InteractionHand.OFF_HAND : EntityBody.InteractionHand.MAIN_HAND;
   }

   public List<ItemStack> getDefaultDrops() {
      return new ArrayList(this.nmsMob.drops);
   }

   public void setDefaultDrops(@Nullable ItemStack... drops) {
      this.nmsMob.drops = new ArrayList(Arrays.asList(drops));
   }

   public boolean isInCombat() {
      return this.nmsMob.bR.e();
   }

   public float getFlyingSpeed() {
      return this.nmsMob.bb;
   }

   public void setFlyingSpeed(float speed) throws IllegalArgumentException {
      if (!(speed < 0.0F) && !(speed > 1.0F)) {
         this.nmsMob.bb = speed;
      } else {
         throw new IllegalArgumentException("Flying speed must be between 0.0F and 1.0F");
      }
   }

   public boolean isForcingDrops() {
      return this.nmsMob.forceDrops;
   }

   public void setForcingDrops(boolean drop) {
      this.nmsMob.forceDrops = drop;
   }

   public boolean isMoving() {
      double x = this.nmsMob.dc() - this.nmsMob.t;
      double z = this.nmsMob.di() - this.nmsMob.v;
      return x * x + z * z > 2.500000277905201E-7D;
   }

   public float getBodyRotation() {
      return this.nmsMob.aX;
   }

   public void setBodyRotation(float rotation) {
      this.nmsMob.aX = EntityBody.normalizeRotation(rotation);
   }

   public float getHeadRotation() {
      return this.nmsMob.aZ;
   }

   public void setHeadRotation(float rotation) {
      this.nmsMob.aZ = EntityBody.normalizeRotation(rotation);
   }

   public Set<? extends Entity> getCollideExemptions() {
      return (Set)this.nmsMob.collidableExemptions.stream().map(Bukkit::getEntity).filter(Objects::nonNull).collect(Collectors.toSet());
   }

   public void addCollideExemption(@NotNull Entity en) throws IllegalArgumentException {
      this.nmsMob.collidableExemptions.add(en.getUniqueId());
   }

   public void removeCollideExemption(@NotNull Entity en) throws IllegalArgumentException {
      this.nmsMob.collidableExemptions.remove(en.getUniqueId());
   }

   public int getDroppedExperience() {
      return this.nmsMob.expToDrop;
   }

   public void setDroppedExperience(int exp) throws IllegalArgumentException {
      if (exp < 0) {
         throw new IllegalArgumentException("Experience cannot be negative");
      } else {
         this.nmsMob.expToDrop = exp;
      }
   }

   public void playAnimation(@NotNull EntityAnimation anim) {
      PacketPlayOutAnimation pkt;
      Iterator var3;
      Player p;
      switch(anim) {
      case SPAWN:
         this.nmsMob.L();
         break;
      case DAMAGE:
         this.nmsMob.bA();
         break;
      case CRITICAL_DAMAGE:
         pkt = new PacketPlayOutAnimation(this.nmsMob, 4);
         var3 = ChipUtil1_18_R2.fromNMS(this.nmsMob).getWorld().getPlayers().iterator();

         while(var3.hasNext()) {
            p = (Player)var3.next();
            ChipUtil1_18_R2.toNMS(p).b.a(pkt);
         }

         return;
      case MAGICAL_CRITICAL_DAMAGE:
         pkt = new PacketPlayOutAnimation(this.nmsMob, 5);
         var3 = ChipUtil1_18_R2.fromNMS(this.nmsMob).getWorld().getPlayers().iterator();

         while(var3.hasNext()) {
            p = (Player)var3.next();
            ChipUtil1_18_R2.toNMS(p).b.a(pkt);
         }
      }

   }

   public float getAnimationSpeed() {
      return this.nmsMob.aS;
   }

   public void setAnimationSpeed(float speed) throws IllegalArgumentException {
      if (speed < 0.0F) {
         throw new IllegalArgumentException("Animation speed cannot be negative");
      } else {
         this.nmsMob.aS = speed;
      }
   }

   public boolean hasVerticalCollision() {
      return this.nmsMob.A;
   }

   public void setVerticalCollision(boolean collision) {
      this.nmsMob.A = collision;
   }

   public boolean hasHorizontalCollision() {
      return this.nmsMob.z;
   }

   public void setHorizontalCollision(boolean collision) {
      this.nmsMob.z = collision;
   }

   public float getWalkDistance() {
      return this.nmsMob.I;
   }

   public float getMoveDistance() {
      return this.nmsMob.J;
   }

   public float getFlyDistance() {
      return this.nmsMob.K;
   }

   public boolean isImmuneToExplosions() {
      return this.nmsMob.cC();
   }

   public boolean isPeacefulCompatible() {
      try {
         Method m = EntityInsentient.class.getDeclaredMethod("P");
         m.setAccessible(true);
         return (Boolean)m.invoke(this.nmsMob);
      } catch (Exception var2) {
         return false;
      }
   }

   public boolean isInBubbleColumn() {
      return this.nmsMob.s.a_(this.nmsMob.cW()).a(Blocks.lq);
   }

   public boolean isInvulnerableTo(@Nullable DamageCause cause) {
      return this.nmsMob.b(ChipUtil1_18_R2.toNMS(cause));
   }

   public int getMaxFallDistance() {
      return this.nmsMob.cj();
   }

   public boolean isPushableBy(@Nullable Entity entity) {
      return IEntitySelector.a(ChipUtil1_18_R2.toNMS(entity)).test(ChipUtil1_18_R2.toNMS(entity));
   }

   public float getYaw() {
      return this.nmsMob.dn();
   }

   public void setYaw(float rotation) {
      this.nmsMob.o(EntityBody.normalizeRotation(rotation));
   }

   public float getPitch() {
      return this.nmsMob.do();
   }

   public void setPitch(float rotation) {
      this.nmsMob.p(EntityBody.normalizeRotation(rotation));
   }

   public float getMaxUpStep() {
      return this.nmsMob.P;
   }

   public void setMaxUpStep(float maxUpStep) {
      this.nmsMob.P = maxUpStep;
   }

   public Position getLastLavaContact() {
      BlockPosition p = this.nmsMob.lastLavaContact;
      return p == null ? null : new Position(p.u(), p.v(), p.w());
   }

   public void setRiptideTicks(int ticks) {
      if (ticks < 0) {
         throw new IllegalArgumentException("Riptide ticks cannot be negative");
      } else {
         try {
            Field f = EntityLiving.class.getDeclaredField("bC");
            f.setAccessible(true);
            f.setInt(this.nmsMob, ticks);
            if (!this.nmsMob.s.x) {
               Method setFlags = EntityLiving.class.getDeclaredMethod("c", Integer.TYPE, Boolean.TYPE);
               setFlags.setAccessible(true);
               setFlags.invoke(this.nmsMob, 4, true);
            }
         } catch (ReflectiveOperationException var7) {
            Bukkit.getLogger().severe(var7.getMessage());
            StackTraceElement[] var3 = var7.getStackTrace();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               StackTraceElement ste = var3[var5];
               Bukkit.getLogger().severe(ste.toString());
            }
         }

         this.update();
      }
   }

   public int getRiptideTicks() {
      try {
         Field f = EntityLiving.class.getDeclaredField("bC");
         f.setAccessible(true);
         return f.getInt(this.nmsMob);
      } catch (ReflectiveOperationException var2) {
         return 0;
      }
   }

   @NotNull
   public Mob getEntity() {
      return this.m;
   }

   public boolean shouldRenderFrom(double x, double y, double z) {
      return this.nmsMob.j(x, y, z);
   }

   public boolean shouldRenderFromSqr(double dist) {
      return this.nmsMob.a(dist);
   }

   public void sendTo(@NotNull Player p) {
      Packet<?> packet = this.nmsMob.S();
      ((CraftPlayer)p).getHandle().b.a(packet);
   }

   public void resetFallDistance() {
      this.nmsMob.m();
   }

   public boolean isInUnloadedChunk() {
      return this.nmsMob.cR();
   }

   public void naturalKnockback(double force, double xForce, double zForce) {
      this.nmsMob.p(force, xForce, zForce);
   }

   public void eat(@NotNull ItemStack item) {
      this.nmsMob.a(ChipUtil1_18_R2.toNMS(this.m.getWorld()), ChipUtil1_18_R2.toNMS(item));
   }

   public void setRotation(float yaw, float pitch) {
      try {
         if (this.m instanceof Slime) {
            ControllerMove moveControl = this.nmsMob.A();
            Method setRotation = moveControl.getClass().getDeclaredMethod("a", Float.TYPE, Boolean.TYPE);
            setRotation.setAccessible(true);
            setRotation.invoke(moveControl, yaw, true);
         } else {
            this.m.setRotation(yaw, pitch);
         }
      } catch (ReflectiveOperationException var8) {
         Bukkit.getLogger().severe(var8.getMessage());
         StackTraceElement[] var4 = var8.getStackTrace();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            StackTraceElement ste = var4[var6];
            Bukkit.getLogger().severe(ste.toString());
         }
      }

   }

   public int getHurtTime() {
      return this.nmsMob.aK;
   }

   public void setHurtTime(int hurtTime) {
      this.nmsMob.aK = hurtTime;
   }

   public int getHurtDuration() {
      return this.nmsMob.aL;
   }

   public void setHurtDuration(int hurtDuration) {
      this.nmsMob.aL = hurtDuration;
   }

   public int getDeathTime() {
      return this.nmsMob.aN;
   }

   public void setDeathTime(int deathTime) {
      this.nmsMob.aN = deathTime;
   }

   public float getForwardSpeed() {
      return this.nmsMob.bq;
   }

   public void setForwardSpeed(float speed) {
      this.nmsMob.u(speed);
   }

   public float getSidewaysSpeed() {
      return this.nmsMob.bo;
   }

   public void setSidewaysSpeed(float speed) {
      this.nmsMob.w(speed);
   }

   public float getUpwardSpeed() {
      return this.nmsMob.bp;
   }

   public void setUpwardSpeed(float speed) {
      this.nmsMob.v(speed);
   }
}
