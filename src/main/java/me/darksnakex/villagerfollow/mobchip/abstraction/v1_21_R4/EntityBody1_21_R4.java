package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R4;

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
import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import me.darksnakex.villagerfollow.mobchip.ai.animation.EntityAnimation;
import me.darksnakex.villagerfollow.mobchip.util.Position;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;
import net.minecraft.network.protocol.game.PacketPlayOutEntityMetadata;
import net.minecraft.server.level.WorldServer;
import net.minecraft.server.level.PlayerChunkMap.EntityTracker;
import net.minecraft.sounds.SoundEffects;
import net.minecraft.world.EnumHand;
import net.minecraft.world.EnumInteractionResult;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EnumMainHand;
import net.minecraft.world.entity.IEntitySelector;
import net.minecraft.world.entity.ai.attributes.GenericAttributes;
import net.minecraft.world.entity.ai.control.ControllerMove;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_21_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class EntityBody1_21_R4 implements EntityBody {
   private final EntityInsentient nmsMob;
   private final Mob m;

   public EntityBody1_21_R4(Mob m) {
      this.m = m;
      this.nmsMob = ChipUtil1_21_R4.toNMS(m);
   }

   private void update() {
      PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(this.nmsMob.ao(), this.nmsMob.ar().b());
      Iterator var2 = this.m.getWorld().getPlayers().iterator();

      while(var2.hasNext()) {
         Player p = (Player)var2.next();
         ((CraftPlayer)p).getHandle().f.b(packet);
      }

   }

   public boolean isLeftHanded() {
      return this.nmsMob.gk();
   }

   public void setLeftHanded(boolean leftHanded) {
      this.nmsMob.u(leftHanded);
   }

   public boolean canBreatheUnderwater() {
      return this.nmsMob.ef();
   }

   public boolean shouldDiscardFriction() {
      return this.nmsMob.ex();
   }

   public void setDiscardFriction(boolean discard) {
      this.nmsMob.q(discard);
   }

   public EntityBody.InteractionResult interact(@NotNull Player p, @Nullable EntityBody.InteractionHand hand) {
      EnumHand h;
      if (hand == EntityBody.InteractionHand.OFF_HAND) {
         h = EnumHand.b;
      } else {
         h = EnumHand.a;
      }

      EnumInteractionResult result = this.nmsMob.a(ChipUtil1_21_R4.toNMS(p), h);
      if (result == EnumInteractionResult.d) {
         return EntityBody.InteractionResult.FAIL;
      } else if (result == EnumInteractionResult.c) {
         return EntityBody.InteractionResult.CONSUME;
      } else {
         return result == EnumInteractionResult.e ? EntityBody.InteractionResult.PASS : EntityBody.InteractionResult.SUCCESS;
      }
   }

   public boolean isSensitiveToWater() {
      return this.nmsMob.fr();
   }

   public boolean isAffectedByPotions() {
      return this.nmsMob.fL();
   }

   public boolean isBlocking() {
      return this.nmsMob.fG();
   }

   public float getArmorCoverPercentage() {
      return this.nmsMob.fd();
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
      return this.nmsMob.fz();
   }

   public boolean isFireImmune() {
      return this.nmsMob.bh();
   }

   public boolean isSwinging() {
      return this.nmsMob.aI;
   }

   public boolean canRideUnderwater() {
      return false;
   }

   public boolean isInvisibleTo(@Nullable Player p) {
      return this.nmsMob.d(ChipUtil1_21_R4.toNMS(p));
   }

   @NotNull
   public EntityBody.InteractionHand getMainHand() {
      return this.nmsMob.fy() == EnumMainHand.a ? EntityBody.InteractionHand.OFF_HAND : EntityBody.InteractionHand.MAIN_HAND;
   }

   public List<ItemStack> getDefaultDrops() {
      return this.nmsMob.drops;
   }

   public void setDefaultDrops(@Nullable ItemStack... drops) {
      this.nmsMob.drops = new ArrayList(Arrays.asList(drops));
   }

   public boolean isInCombat() {
      try {
         Field inCombatF = CombatTracker.class.getDeclaredField("i");
         inCombatF.setAccessible(true);
         return inCombatF.getBoolean(this.nmsMob.bG);
      } catch (ReflectiveOperationException var2) {
         ChipUtil.printStackTrace(var2);
         return false;
      }
   }

   public float getFlyingSpeed() {
      return 0.0F;
   }

   public void setFlyingSpeed(float speed) throws IllegalArgumentException {
   }

   public boolean isForcingDrops() {
      return this.nmsMob.forceDrops;
   }

   public void setForcingDrops(boolean drop) {
      this.nmsMob.forceDrops = drop;
   }

   public boolean isMoving() {
      double x = this.nmsMob.dA() - this.nmsMob.K;
      double z = this.nmsMob.dG() - this.nmsMob.M;
      return x * x + z * z > 2.500000277905201E-7D;
   }

   public float getBodyRotation() {
      return this.nmsMob.aV;
   }

   public void setBodyRotation(float rotation) {
      this.nmsMob.aV = EntityBody.normalizeRotation(rotation);
   }

   public float getHeadRotation() {
      return this.nmsMob.aX;
   }

   public void setHeadRotation(float rotation) {
      this.nmsMob.aX = EntityBody.normalizeRotation(rotation);
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
         this.nmsMob.T();
         break;
      case DAMAGE:
         this.nmsMob.q(1.0F);
         break;
      case CRITICAL_DAMAGE:
         pkt = new PacketPlayOutAnimation(this.nmsMob, 4);
         var3 = ChipUtil1_21_R4.fromNMS(this.nmsMob).getWorld().getPlayers().iterator();

         while(var3.hasNext()) {
            p = (Player)var3.next();
            ChipUtil1_21_R4.toNMS(p).f.b(pkt);
         }

         return;
      case MAGICAL_CRITICAL_DAMAGE:
         pkt = new PacketPlayOutAnimation(this.nmsMob, 5);
         var3 = ChipUtil1_21_R4.fromNMS(this.nmsMob).getWorld().getPlayers().iterator();

         while(var3.hasNext()) {
            p = (Player)var3.next();
            ChipUtil1_21_R4.toNMS(p).f.b(pkt);
         }
      }

   }

   public float getAnimationSpeed() {
      return 0.0F;
   }

   public void setAnimationSpeed(float speed) throws IllegalArgumentException {
   }

   public boolean hasVerticalCollision() {
      return this.nmsMob.Q;
   }

   public void setVerticalCollision(boolean collision) {
      this.nmsMob.Q = collision;
   }

   public boolean hasHorizontalCollision() {
      return this.nmsMob.P;
   }

   public void setHorizontalCollision(boolean collision) {
      this.nmsMob.P = collision;
   }

   public float getWalkDistance() {
      return this.nmsMob.X;
   }

   public float getMoveDistance() {
      return this.nmsMob.X;
   }

   public float getFlyDistance() {
      return this.nmsMob.Y;
   }

   public boolean isImmuneToExplosions() {
      return this.nmsMob.a((Explosion)null);
   }

   public boolean isPeacefulCompatible() {
      try {
         Method m = EntityInsentient.class.getDeclaredMethod("Q");
         m.setAccessible(true);
         return (Boolean)m.invoke(this.nmsMob);
      } catch (Exception var2) {
         return false;
      }
   }

   public boolean isInBubbleColumn() {
      return this.nmsMob.cU().a_(this.nmsMob.dv()).a(Blocks.nJ);
   }

   public boolean isInvulnerableTo(@Nullable DamageCause cause) {
      return this.nmsMob.a(this.nmsMob.dV().getMinecraftWorld(), ChipUtil1_21_R4.toNMS((DamageCause)cause, (Entity)this.m));
   }

   public int getMaxFallDistance() {
      return this.nmsMob.cE();
   }

   public boolean isPushableBy(@Nullable Entity entity) {
      return IEntitySelector.a(ChipUtil1_21_R4.toNMS(entity)).test(ChipUtil1_21_R4.toNMS(entity));
   }

   public float getYaw() {
      return this.nmsMob.dL();
   }

   public void setYaw(float rotation) {
      this.nmsMob.w(EntityBody.normalizeRotation(rotation));
   }

   public float getPitch() {
      return this.nmsMob.dN();
   }

   public void setPitch(float rotation) {
      this.nmsMob.x(EntityBody.normalizeRotation(rotation));
   }

   public float getMaxUpStep() {
      return this.nmsMob.dP();
   }

   public void setMaxUpStep(float maxUpStep) {
      this.nmsMob.fa().a(GenericAttributes.B).a((double)maxUpStep);
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
            if (!this.nmsMob.cU().C) {
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
      EntityTracker tracked = (EntityTracker)((WorldServer)this.nmsMob.dV()).m().a.L.get(this.nmsMob.ao());
      Packet<?> packet = this.nmsMob.a(tracked.b);
      ((CraftPlayer)p).getHandle().f.b(packet);
   }

   public void resetFallDistance() {
      this.nmsMob.j();
   }

   public boolean isInUnloadedChunk() {
      return this.nmsMob.do();
   }

   public void naturalKnockback(double force, double xForce, double zForce) {
      this.nmsMob.p(force, xForce, zForce);
   }

   public void eat(@NotNull ItemStack item) {
      this.nmsMob.dV().a((net.minecraft.world.entity.Entity)null, this.nmsMob.dA(), this.nmsMob.dC(), this.nmsMob.dG(), SoundEffects.kH, this.nmsMob.dm(), 1.0F, 1.0F);
      this.nmsMob.a(GameEvent.m);
   }

   public void setRotation(float yaw, float pitch) {
      try {
         if (this.m instanceof Slime) {
            ControllerMove moveControl = this.nmsMob.K();
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
      return this.nmsMob.aN;
   }

   public void setHurtTime(int hurtTime) {
      this.nmsMob.aN = hurtTime;
   }

   public int getHurtDuration() {
      return this.nmsMob.aO;
   }

   public void setHurtDuration(int hurtDuration) {
      this.nmsMob.aO = hurtDuration;
   }

   public int getDeathTime() {
      return this.nmsMob.aP;
   }

   public void setDeathTime(int deathTime) {
      this.nmsMob.aP = deathTime;
   }

   public float getForwardSpeed() {
      return this.nmsMob.bi;
   }

   public void setForwardSpeed(float speed) {
      this.nmsMob.G(speed);
   }

   public float getSidewaysSpeed() {
      return this.nmsMob.bg;
   }

   public void setSidewaysSpeed(float speed) {
      this.nmsMob.I(speed);
   }

   public float getUpwardSpeed() {
      return this.nmsMob.bh;
   }

   public void setUpwardSpeed(float speed) {
      this.nmsMob.H(speed);
   }
}
