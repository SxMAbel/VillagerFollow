package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.entity.Wolf;
import org.jetbrains.annotations.NotNull;

public final class PathfinderBeg extends Pathfinder implements Ranged {
   private float lookRange;

   public PathfinderBeg(@NotNull Wolf w) {
      this(w, 5.0F);
   }

   public PathfinderBeg(@NotNull Wolf w, float lookRange) {
      super(w);
      this.lookRange = lookRange;
   }

   @NotNull
   public Wolf getEntity() {
      return (Wolf)this.entity;
   }

   public float getRange() {
      return this.lookRange;
   }

   public void setRange(float range) {
      this.lookRange = range;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalBeg";
   }
}
