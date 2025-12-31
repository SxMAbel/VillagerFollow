package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R1;

import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.server.level.WorldServer;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.level.World;

final class Sensor1_21_R1 extends Sensor<EntityLiving> {
   private final me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> s;

   public Sensor1_21_R1(me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> s) {
      this.s = s;
   }

   protected void a(WorldServer level, EntityLiving en) {
      this.s.run(ChipUtil1_21_R1.fromNMS((World)level), ChipUtil1_21_R1.fromNMS(en));
   }

   public Set<MemoryModuleType<?>> a() {
      return (Set)this.s.required().stream().map(ChipUtil1_21_R1::toNMS).collect(Collectors.toSet());
   }

   public me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> getSensor() {
      return this.s;
   }
}
