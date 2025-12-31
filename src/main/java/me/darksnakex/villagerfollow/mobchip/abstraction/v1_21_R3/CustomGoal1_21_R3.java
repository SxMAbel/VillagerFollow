package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R3;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Objects;
import java.util.stream.Stream;
import me.darksnakex.villagerfollow.mobchip.ai.goal.CustomPathfinder;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoal.Type;

final class CustomGoal1_21_R3 extends PathfinderGoal {
   private final CustomPathfinder p;

   public CustomGoal1_21_R3(CustomPathfinder p) {
      this.p = p;
      EnumSet<Type> set = EnumSet.noneOf(Type.class);
      Stream var10000 = Arrays.stream(p.getFlags()).map(ChipUtil1_21_R3::toNMS);
      Objects.requireNonNull(set);
      var10000.forEach(set::add);
      this.a(set);
   }

   public boolean b() {
      return this.p.canStart();
   }

   public boolean c() {
      return this.p.canContinueToUse();
   }

   public boolean U_() {
      return this.p.canInterrupt();
   }

   public void d() {
      this.p.start();
   }

   public void a() {
      this.p.tick();
   }

   public void e() {
      this.p.stop();
   }

   public CustomPathfinder getPathfinder() {
      return this.p;
   }
}
