package me.darksnakex.villagerfollow.mobchip.ai.goal;

import java.util.function.Predicate;
import org.bukkit.Sound;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PathfinderUseItem extends Pathfinder implements Conditional<Mob> {
   private Sound finishSound;
   private ItemStack item;
   private Predicate<Mob> requirements;

   public PathfinderUseItem(@NotNull Mob m, @NotNull ItemStack item, @NotNull Predicate<Mob> requirements) throws IllegalArgumentException {
      this(m, item, requirements, (Sound)null);
   }

   public PathfinderUseItem(@NotNull Mob m, @NotNull ItemStack item, @NotNull Predicate<Mob> requirements, @Nullable Sound finishSound) throws IllegalArgumentException {
      this.item = item;
      this.requirements = requirements;
      this.finishSound = finishSound == null ? Sound.ENTITY_PLAYER_BURP : finishSound;
   }

   @NotNull
   public Sound getFinishSound() {
      return this.finishSound;
   }

   public void setFinishSound(@Nullable Sound s) {
      this.finishSound = s == null ? Sound.ENTITY_PLAYER_BURP : s;
   }

   @NotNull
   public ItemStack getItem() {
      return this.item;
   }

   public void setItem(@NotNull ItemStack i) throws IllegalArgumentException {
      this.item = i;
   }

   @NotNull
   public Predicate<Mob> getCondition() {
      return this.requirements;
   }

   public void setCondition(@NotNull Predicate<Mob> req) throws IllegalArgumentException {
      this.requirements = req;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public String getInternalName() {
      return "PathfinderGoalUseItem";
   }
}
