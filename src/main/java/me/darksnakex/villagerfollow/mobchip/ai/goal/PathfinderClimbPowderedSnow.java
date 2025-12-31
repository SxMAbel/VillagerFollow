package me.darksnakex.villagerfollow.mobchip.ai.goal;

import org.bukkit.World;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderClimbPowderedSnow extends Pathfinder implements WorldSpecific {
   private World w;

   public PathfinderClimbPowderedSnow(@NotNull Mob m) {
      this(m, m.getWorld());
   }

   public PathfinderClimbPowderedSnow(@NotNull Mob m, @NotNull World w) throws IllegalArgumentException {
      this.w = w;
   }

   @NotNull
   public World getWorld() {
      return this.w;
   }

   public void setWorld(@NotNull World w) throws IllegalArgumentException {
      this.w = w;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.JUMPING};
   }

   public String getInternalName() {
      return "ClimbOnTopOfPowderSnowGoal";
   }
}
