package me.darksnakex.villagerfollow.mobchip.abstraction.v1_19_R3;

import me.darksnakex.villagerfollow.mobchip.ai.enderdragon.DragonPhase;
import net.minecraft.core.IPosition;
import net.minecraft.world.entity.boss.enderdragon.phases.IDragonController;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.jetbrains.annotations.NotNull;

final class DragonPhase1_19_R3 implements DragonPhase {
   private final EnderDragon dragon;
   private final IDragonController handle;

   public DragonPhase1_19_R3(EnderDragon dragon, IDragonController handle) {
      this.dragon = dragon;
      this.handle = handle;
   }

   @NotNull
   public EnderDragon getDragon() {
      return this.dragon;
   }

   @NotNull
   public Location getTargetLocation() {
      return ChipUtil1_19_R3.fromNMS((IPosition)this.handle.g(), (World)this.dragon.getWorld());
   }

   public void start() {
      this.handle.d();
   }

   public void stop() {
      this.handle.e();
   }

   public void clientTick() {
      this.handle.b();
   }

   public void serverTick() {
      this.handle.c();
   }

   public boolean isSitting() {
      return this.handle.a();
   }

   public float getFlyingSpeed() {
      return this.handle.f();
   }

   @NotNull
   public NamespacedKey getKey() {
      return NamespacedKey.minecraft(this.handle.toString().split(" ")[0].toLowerCase());
   }
}
