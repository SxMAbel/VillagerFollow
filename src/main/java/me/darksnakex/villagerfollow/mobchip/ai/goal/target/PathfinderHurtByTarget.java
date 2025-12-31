package me.darksnakex.villagerfollow.mobchip.ai.goal.target;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

public final class PathfinderHurtByTarget extends TargetPathfinder {
   private final List<EntityType> ignoring;

   public PathfinderHurtByTarget(@NotNull Creature c, EntityType... ignore) {
      super(c, true, false);
      this.ignoring = new ArrayList(Arrays.asList(ignore));
   }

   @NotNull
   public List<EntityType> getIgnoring() {
      return this.ignoring;
   }

   public void setIgnoring(@NotNull EntityType... ignoring) {
      this.ignoring.addAll(Arrays.asList(ignoring));
   }

   public void addIgnore(@NotNull EntityType t) {
      this.ignoring.add(t);
   }

   public void removeIgnore(@NotNull EntityType t) {
      this.ignoring.remove(t);
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.TARGETING};
   }

   public String getInternalName() {
      return "PathfinderGoalHurtByTarget";
   }
}
