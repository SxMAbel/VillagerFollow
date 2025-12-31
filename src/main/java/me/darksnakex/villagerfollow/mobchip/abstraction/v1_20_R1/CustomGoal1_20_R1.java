package me.darksnakex.villagerfollow.mobchip.abstraction.v1_20_R1;

import me.darksnakex.villagerfollow.mobchip.ai.goal.CustomPathfinder;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;

final class CustomGoal1_20_R1 extends PathfinderGoal {
   private final CustomPathfinder p;

   public CustomGoal1_20_R1(CustomPathfinder p) {
      this.p = p;
   }

   public boolean a() {
      return this.p.canStart();
   }

   public boolean b() {
      return this.p.canContinueToUse();
   }

   public boolean J_() {
      return this.p.canInterrupt();
   }

   public void c() {
      this.p.start();
   }

   public void e() {
      this.p.tick();
   }

   public void d() {
      this.p.stop();
   }

   public CustomPathfinder getPathfinder() {
      return this.p;
   }
}
