package me.darksnakex.villagerfollow.mobchip.abstraction.v1_17_R1;

import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.behavior.Behavior;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

final class BehaviorResult1_17_R1 extends BehaviorResult {
   private final Behavior<? super EntityLiving> b;
   private final EntityInsentient mob;
   private final WorldServer l;

   public BehaviorResult1_17_R1(Behavior<? super EntityLiving> b, EntityInsentient mob) {
      this.b = b;
      this.mob = mob;
      this.l = ChipUtil1_17_R1.toNMS(Bukkit.getWorld(mob.t.getWorld().getUID()));
      b.e(this.l, mob, 0L);
   }

   @NotNull
   public BehaviorResult.Status getStatus() {
      return ChipUtil1_17_R1.fromNMS(this.b.a());
   }

   public void stop() {
      this.b.g(this.l, this.mob, 0L);
   }
}
