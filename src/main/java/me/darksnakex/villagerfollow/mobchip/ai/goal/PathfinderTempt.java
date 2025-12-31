package me.darksnakex.villagerfollow.mobchip.ai.goal;

import com.google.common.collect.ImmutableList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class PathfinderTempt extends Pathfinder implements SpeedModifier {
   private Predicate<ItemStack> predicate;
   private Set<ItemStack> items;
   private double speedMod;

   public PathfinderTempt(@NotNull Creature m, @NotNull ItemStack... items) throws IllegalArgumentException {
      this(m, 1.5D, items);
   }

   public PathfinderTempt(@NotNull Creature m, double speedMod, @NotNull ItemStack... items) throws IllegalArgumentException {
      this(m, speedMod, (Iterable)Arrays.asList(items));
   }

   public PathfinderTempt(@NotNull Creature m, double speedMod, @NotNull Iterable<? extends ItemStack> items) throws IllegalArgumentException {
      super(m);
      if (!items.iterator().hasNext()) {
         throw new IllegalArgumentException("items cannot be empty");
      } else {
         this.items = new HashSet(ImmutableList.copyOf(items));
         this.speedMod = speedMod;
         Set var10001 = this.items;
         Objects.requireNonNull(var10001);
         this.predicate = var10001::contains;
      }
   }

   public PathfinderTempt(@NotNull Creature m, double speedMod, @NotNull Predicate<ItemStack> predicate) {
      super(m);
      this.speedMod = speedMod;
      this.predicate = predicate;
   }

   @NotNull
   public Set<ItemStack> getItems() {
      if (this.items == null) {
         this.items = new HashSet();
         Material[] var1 = Material.values();
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            Material mat = var1[var3];
            ItemStack item = new ItemStack(mat);
            if (this.predicate.test(item)) {
               this.items.add(item);
            }
         }

         Set var10001 = this.items;
         Objects.requireNonNull(var10001);
         this.predicate = var10001::contains;
      }

      return this.items;
   }

   public Predicate<ItemStack> getPredicate() {
      return this.predicate;
   }

   public void setPredicate(Predicate<ItemStack> predicate) {
      this.predicate = predicate;
      this.items = null;
   }

   public void addItems(@NotNull ItemStack... items) throws IllegalArgumentException {
      this.addItems((Iterable)Arrays.asList(items));
   }

   public void addItems(@NotNull Iterable<? extends ItemStack> items) throws IllegalArgumentException {
      this.getItems().addAll(ImmutableList.copyOf(items));
   }

   public void removeItems(@NotNull ItemStack... items) throws IllegalArgumentException {
      this.removeItems((Iterable)Arrays.asList(items));
   }

   public void removeItems(@NotNull Iterable<? extends ItemStack> items) throws IllegalArgumentException {
      ImmutableList var10000 = ImmutableList.copyOf(items);
      Set var10001 = this.getItems();
      Objects.requireNonNull(var10001);
      var10000.forEach(var10001::remove);
   }

   public void setItems(@NotNull Iterable<? extends ItemStack> items) throws IllegalArgumentException {
      if (!items.iterator().hasNext()) {
         throw new IllegalArgumentException("items cannot be empty");
      } else {
         this.items = new HashSet(ImmutableList.copyOf(items));
         Set var10001 = this.items;
         Objects.requireNonNull(var10001);
         this.predicate = var10001::contains;
      }
   }

   public void setItems(@NotNull ItemStack... items) throws IllegalArgumentException {
      this.setItems((Iterable)Arrays.asList(items));
   }

   public double getSpeedModifier() {
      return this.speedMod;
   }

   public void setSpeedModifier(double mod) {
      this.speedMod = mod;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[]{Pathfinder.PathfinderFlag.MOVEMENT, Pathfinder.PathfinderFlag.LOOKING};
   }

   public String getInternalName() {
      return "PathfinderGoalTempt";
   }
}
