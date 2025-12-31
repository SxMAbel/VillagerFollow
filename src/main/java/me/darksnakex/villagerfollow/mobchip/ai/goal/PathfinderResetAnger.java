package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderResetAnger extends Pathfinder {
   private boolean alertOthers;

   public PathfinderResetAnger(@NotNull Mob m) {
      this(m, true);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public PathfinderResetAnger(@NotNull Mob m, boolean alertOthers) {
      super(m);
      this.alertOthers = alertOthers;
   }

   public boolean isAlertingOthers() {
      return this.alertOthers;
   }

   public void setAlertOthers(boolean alert) {
      this.alertOthers = alert;
   }

   public String getInternalName() {
      return "PathfinderGoalUniversalAngerReset";
   }
}
