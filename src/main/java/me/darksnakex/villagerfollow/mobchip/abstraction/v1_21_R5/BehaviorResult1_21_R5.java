package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R5;

import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

final class BehaviorResult1_21_R5 extends BehaviorResult {
   private final BehaviorControl b;
   private final EntityLiving mob;
   private final WorldServer l;

   public <T extends EntityLiving> BehaviorResult1_21_R5(BehaviorControl<T> b, T mob) {
      this.b = b;
      this.mob = mob;
      this.l = ChipUtil1_21_R5.toNMS(Bukkit.getWorld(mob.ai().getWorld().getUID()));
      b.e(this.l, mob, 0L);
   }

   @NotNull
   public BehaviorResult.Status getStatus() {
      return ChipUtil1_21_R5.fromNMS(this.b.a());
   }

   public void stop() {
      this.b.g(this.l, this.mob, 0L);
   }
}
