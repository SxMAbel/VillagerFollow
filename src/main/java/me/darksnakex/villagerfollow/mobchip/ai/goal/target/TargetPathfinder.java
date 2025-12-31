package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public abstract class TargetPathfinder extends Pathfinder implements Targeting {
   private boolean mustSee;
   private boolean reach;

   protected TargetPathfinder(@NotNull Mob m, boolean see) {
      this(m, see, false);
   }

   protected TargetPathfinder(@NotNull Mob m, boolean see, boolean reach) {
      super(m);
      this.mustSee = see;
      this.reach = reach;
   }

   public boolean mustSee() {
      return this.mustSee;
   }

   public void setSee(boolean see) {
      this.mustSee = see;
   }

   public boolean mustReach() {
      return this.reach;
   }

   public void setReach(boolean reach) {
      this.reach = reach;
   }
}
