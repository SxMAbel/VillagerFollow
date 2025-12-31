package me.darksnakex.villagerfollow.mobchip.ai.behavior;

import org.jetbrains.annotations.NotNull;

public abstract class BehaviorResult {
   public static final BehaviorResult STOPPED = new BehaviorResult() {
      @NotNull
      public BehaviorResult.Status getStatus() {
         return BehaviorResult.Status.STOPPED;
      }

      public void stop() {
      }
   };

   protected BehaviorResult() {
   }

   @NotNull
   public abstract BehaviorResult.Status getStatus();

   public abstract void stop();

   public static enum Status {
      STOPPED,
      RUNNING;

      // $FF: synthetic method
      private static BehaviorResult.Status[] $values() {
         return new BehaviorResult.Status[]{STOPPED, RUNNING};
      }
   }
}
