package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R6;

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
import org.bukkit.craftbukkit.v1_21_R6.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class EntityBody1_21_R6 implements EntityBody {
   private final EntityInsentient nmsMob;
   private final Mob m;

   public EntityBody1_21_R6(Mob m) {
      this.m = m;
      this.nmsMob = ChipUtil1_21_R6.toNMS(m);
   }

   private void update() {
      PacketPlayOutEntityMetadata packet = new PacketPlayOutEntityMetadata(this.nmsMob.az(), this.nmsMob.aC().b());
      Iterator var2 = this.m.getWorld().getPlayers().iterator();

      while(var2.hasNext()) {
         Player p = (Player)var2.next();
         ((CraftPlayer)p).getHandle().g.b(packet);
      }

   }

   public boolean isLeftHanded() {
      return this.nmsMob.gy();
   }

   public void setLeftHanded(boolean leftHanded) {
      this.nmsMob.v(leftHanded);
   }

   public boolean canBreatheUnderwater() {
      return this.nmsMob.et();
   }

   public boolean shouldDiscardFriction() {
      return this.nmsMob.eL();
   }

   public void setDiscardFriction(boolean discard) {
      this.nmsMob.r(discard);
   }

   public EntityBody.InteractionResult interact(@NotNull Player p, @Nullable EntityBody.InteractionHand hand) {
      EnumHand h;
      if (hand == EntityBody.InteractionHand.OFF_HAND) {
         h = EnumHand.b;
      } else {
         h = EnumHand.a;
      }

      EnumInteractionResult result = this.nmsMob.a(ChipUtil1_21_R6.toNMS(p), h);
      if (result == EnumInteractionResult.d) {
         return EntityBody.InteractionResult.FAIL;
      } else if (result == EnumInteractionResult.c) {
         return EntityBody.InteractionResult.CONSUME;
      } else {
         return result == EnumInteractionResult.e ? EntityBody.InteractionResult.PASS : EntityBody.InteractionResult.SUCCESS;
      }
   }

   public boolean isSensitiveToWater() {
      return this.nmsMob.fG();
   }

   public boolean isAffectedByPotions() {
      return this.nmsMob.ga();
   }

   public boolean isBlocking() {
      return this.nmsMob.fV();
   }

   public float getArmorCoverPercentage() {
      return this.nmsMob.fs();
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
      return this.nmsMob.fO();
   }

   public boolean isFireImmune() {
      return this.nmsMob.bt();
   }

   public boolean isSwinging() {
      return this.nmsMob.bo;
   }

   public boolean canRideUnderwater() {
      return false;
   }

   public boolean isInvisibleTo(@Nullable Player p) {
      return this.nmsMob.e(ChipUtil1_21_R6.toNMS(p));
   }

   @NotNull
   public EntityBody.InteractionHand getMainHand() {
      return this.nmsMob.as() == EnumMainHand.a ? EntityBody.InteractionHand.OFF_HAND : EntityBody.InteractionHand.MAIN_HAND;
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
         return inCombatF.getBoolean(this.nmsMob.ck);
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
      double x = this.nmsMob.dK() - this.nmsMob.Y;
      double z = this.nmsMob.dQ() - this.nmsMob.aa;
      return x * x + z * z > 2.500000277905201E-7D;
   }

   public float getBodyRotation() {
      return this.nmsMob.bB;
   }

   public void setBodyRotation(float rotation) {
      this.nmsMob.bB = EntityBody.normalizeRotation(rotation);
   }

   public float getHeadRotation() {
      return this.nmsMob.bD;
   }

   public void setHeadRotation(float rotation) {
      this.nmsMob.bD = EntityBody.normalizeRotation(rotation);
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
         this.nmsMob.Y();
         break;
      case DAMAGE:
         this.nmsMob.q(1.0F);
         break;
      case CRITICAL_DAMAGE:
         pkt = new PacketPlayOutAnimation(this.nmsMob, 4);
         var3 = ChipUtil1_21_R6.fromNMS(this.nmsMob).getWorld().getPlayers().iterator();

         while(var3.hasNext()) {
            p = (Player)var3.next();
            ChipUtil1_21_R6.toNMS(p).g.b(pkt);
         }

         return;
      case MAGICAL_CRITICAL_DAMAGE:
         pkt = new PacketPlayOutAnimation(this.nmsMob, 5);
         var3 = ChipUtil1_21_R6.fromNMS(this.nmsMob).getWorld().getPlayers().iterator();

         while(var3.hasNext()) {
            p = (Player)var3.next();
            ChipUtil1_21_R6.toNMS(p).g.b(pkt);
         }
      }

   }

   public float getAnimationSpeed() {
      return 0.0F;
   }

   public void setAnimationSpeed(float speed) throws IllegalArgumentException {
   }

   public boolean hasVerticalCollision() {
      return this.nmsMob.ae;
   }

   public void setVerticalCollision(boolean collision) {
      this.nmsMob.ae = collision;
   }

   public boolean hasHorizontalCollision() {
      return this.nmsMob.ad;
   }

   public void setHorizontalCollision(boolean collision) {
      this.nmsMob.ad = collision;
   }

   public float getWalkDistance() {
      return this.nmsMob.al;
   }

   public float getMoveDistance() {
      return this.nmsMob.al;
   }

   public float getFlyDistance() {
      return this.nmsMob.am;
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
      return this.nmsMob.an().a_(this.nmsMob.dF()).a(Blocks.oa);
   }

   public boolean isInvulnerableTo(@Nullable DamageCause cause) {
      return this.nmsMob.a(this.nmsMob.an().getMinecraftWorld(), ChipUtil1_21_R6.toNMS((DamageCause)cause, (Entity)this.m));
   }

   public int getMaxFallDistance() {
      return this.nmsMob.cR();
   }

   public boolean isPushableBy(@Nullable Entity entity) {
      return IEntitySelector.a(ChipUtil1_21_R6.toNMS(entity)).test(ChipUtil1_21_R6.toNMS(entity));
   }

   public float getYaw() {
      return this.nmsMob.dX();
   }

   public void setYaw(float rotation) {
      this.nmsMob.v(EntityBody.normalizeRotation(rotation));
   }

   public float getPitch() {
      return this.nmsMob.dZ();
   }

   public void setPitch(float rotation) {
      this.nmsMob.w(EntityBody.normalizeRotation(rotation));
   }

   public float getMaxUpStep() {
      return this.nmsMob.eb();
   }

   public void setMaxUpStep(float maxUpStep) {
      this.nmsMob.fp().a(GenericAttributes.C).a((double)maxUpStep);
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
            if (!this.nmsMob.an().D_()) {
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
      EntityTracker tracked = (EntityTracker)((WorldServer)this.nmsMob.an()).n().a.J.get(this.nmsMob.az());
      Packet<?> packet = this.nmsMob.a(tracked.b);
      ((CraftPlayer)p).getHandle().g.b(packet);
   }

   public void resetFallDistance() {
      this.nmsMob.l();
   }

   public boolean isInUnloadedChunk() {
      return this.nmsMob.dy();
   }

   public void naturalKnockback(double force, double xForce, double zForce) {
      this.nmsMob.o(force, xForce, zForce);
   }

   public void eat(@NotNull ItemStack item) {
      this.nmsMob.an().a((net.minecraft.world.entity.Entity)null, this.nmsMob.dK(), this.nmsMob.dM(), this.nmsMob.dQ(), SoundEffects.lv, this.nmsMob.dw(), 1.0F, 1.0F);
      this.nmsMob.c(GameEvent.m);
   }

   public void setRotation(float yaw, float pitch) {
      try {
         if (this.m instanceof Slime) {
            ControllerMove moveControl = this.nmsMob.Q();
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
      return this.nmsMob.bt;
   }

   public void setHurtTime(int hurtTime) {
      this.nmsMob.bt = hurtTime;
   }

   public int getHurtDuration() {
      return this.nmsMob.bu;
   }

   public void setHurtDuration(int hurtDuration) {
      this.nmsMob.bu = hurtDuration;
   }

   public int getDeathTime() {
      return this.nmsMob.bv;
   }

   public void setDeathTime(int deathTime) {
      this.nmsMob.bv = deathTime;
   }

   public float getForwardSpeed() {
      return this.nmsMob.bO;
   }

   public void setForwardSpeed(float speed) {
      this.nmsMob.G(speed);
   }

   public float getSidewaysSpeed() {
      return this.nmsMob.bM;
   }

   public void setSidewaysSpeed(float speed) {
      this.nmsMob.I(speed);
   }

   public float getUpwardSpeed() {
      return this.nmsMob.bN;
   }

   public void setUpwardSpeed(float speed) {
      this.nmsMob.H(speed);
   }
}
