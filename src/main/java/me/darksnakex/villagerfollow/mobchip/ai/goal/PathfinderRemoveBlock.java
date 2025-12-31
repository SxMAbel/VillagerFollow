package me.darksnakex.villagerfollow.mobchip.ai.goal;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Creature;
import org.jetbrains.annotations.NotNull;

public final class PathfinderRemoveBlock extends Pathfinder implements SpeedModifier {
   private double speedMod;
   private Material toRemove;
   private int verticalSearchRange;

   public PathfinderRemoveBlock(@NotNull Creature c, @NotNull Block remove) {
      this(c, remove, 1.0D);
   }

   public PathfinderRemoveBlock(@NotNull Creature c, @NotNull Block remove, double speedMod) {
      this(c, (Block)remove, speedMod, 1);
   }

   public PathfinderRemoveBlock(@NotNull Creature c, @NotNull Block remove, double speedMod, int verticalSearchRange) {
      if (!remove.getWorld().getUID().equals(c.getWorld().getUID())) {
         throw new IllegalArgumentException("Differing worlds: Creature[" + c.getWorld().getName() + "] not matching Block[" + remove.getWorld().getName() + "]");
      } else if (verticalSearchRange < 1) {
         throw new IllegalArgumentException("Vertical search range must be positive");
      } else {
         this.toRemove = remove.getType();
         this.speedMod = speedMod;
      }
   }

   public PathfinderRemoveBlock(@NotNull Creature c, @NotNull Material remove) throws IllegalArgumentException {
      this(c, remove, 1.0D);
   }

   public PathfinderRemoveBlock(@NotNull Creature c, @NotNull Material remove, double speedMod) throws IllegalArgumentException {
      this(c, (Material)remove, speedMod, 1);
   }

   public PathfinderRemoveBlock(@NotNull Creature c, @NotNull Material remove, double speedMod, int verticalSearchRange) throws IllegalArgumentException {
      if (verticalSearchRange < 1) {
         throw new IllegalArgumentException("Vertical search range must be positive");
      } else {
         this.toRemove = remove;
         this.speedMod = speedMod;
         this.verticalSearchRange = verticalSearchRange;
      }
   }

   @NotNull
   public Material getBlock() {
      return this.toRemove;
   }

   public void setBlock(@NotNull Block remove) throws IllegalArgumentException {
      if (!remove.getWorld().getName().equals(this.entity.getWorld().getName())) {
         throw new IllegalArgumentException("Differing worlds: Creature[" + this.entity.getWorld().getName() + "] not matching Block[" + remove.getWorld().getName() + "]");
      } else {
         this.setBlock(remove.getType());
      }
   }

   public void setBlock(@NotNull Material remove) throws IllegalArgumentException {
      this.toRemove = remove;
   }

   public double getSpeedModifier() {
      return this.speedMod;
   }

   public void setSpeedModifier(double mod) {
      this.speedMod = mod;
   }

   public int getVerticalSearchRange() {
      return this.verticalSearchRange;
   }

   public void setVerticalSearchRange(int verticalSearchRange) {
      this.verticalSearchRange = verticalSearchRange;
   }

   @NotNull
   public Pathfinder.PathfinderFlag[] getFlags() {
      return new Pathfinder.PathfinderFlag[0];
   }

   public String getInternalName() {
      return "PathfinderGoalRemoveBlock";
   }
}
