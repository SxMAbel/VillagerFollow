package me.darksnakex.villagerfollow.mobchip.abstraction.v1_20_R2;

import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.level.World;

final class Sensor1_20_R2 extends Sensor<EntityLiving> {
   private final me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> s;

   public Sensor1_20_R2(me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> s) {
      this.s = s;
   }

   protected void a(WorldServer level, EntityLiving en) {
      this.s.run(ChipUtil1_20_R2.fromNMS((World)level), ChipUtil1_20_R2.fromNMS(en));
   }

   public Set<MemoryModuleType<?>> a() {
      return (Set)this.s.required().stream().map(ChipUtil1_20_R2::toNMS).collect(Collectors.toSet());
   }

   public me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> getSensor() {
      return this.s;
   }
}
