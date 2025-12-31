package me.darksnakex.villagerfollow.mobchip.ai.goal;

import java.util.Arrays;
import java.util.function.Predicate;
import org.bukkit.Difficulty;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

public final class PathfinderBreakDoor extends Pathfinder implements Conditional<Difficulty> {
   public static final int DEFAULT_DOOR_BREAK_TIME = 240;
   private int breakTime;
   private Predicate<Difficulty> validDiffs;

   public PathfinderBreakDoor(@NotNull Mob m, Difficulty... validDifficulties) {
      this(m, 240, (Difficulty[])validDifficulties);
   }

   public PathfinderBreakDoor(@NotNull Mob m, int breakTime, Difficulty... validDifficulties) throws IllegalArgumentException {
      this(m, breakTime, (d) -> {
         return Arrays.asList(validDifficulties).contains(d);
      });
   }

   public PathfinderBreakDoor(@NotNull Mob m, int breakTime, Predicate<Difficulty> validDiffs) throws IllegalArgumentException {
      super(m);
      if (breakTime <= 0) {
         throw new IllegalArgumentException("Break Time must be greater than 0");
      } else {
         this.breakTime = breakTime;
         this.validDiffs = validDiffs;
      }
   }

   public int getBreakTime() {
      return this.breakTime;
   }

   @NotNull
   public Predicate<Difficulty> getCondition() {
      return this.validDiffs;
   }

   public void setBreakTime(int time) throws IllegalArgumentException {
      if (time <= 0) {
         throw new IllegalArgumentException("Break Time must be greater than 0");
      } else {
         this.breakTime = time;
      }
   }

   public void setCondition(@NotNull Predicate<Difficulty> condition) {
      this.validDiffs = condition;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public String getInternalName() {
      return "PathfinderGoalBreakDoor";
   }
}
