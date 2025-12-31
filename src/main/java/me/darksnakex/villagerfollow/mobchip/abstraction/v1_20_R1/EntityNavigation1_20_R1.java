package me.darksnakex.villagerfollow.mobchip.abstraction.v1_20_R1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.darksnakex.villagerfollow.mobchip.ai.navigation.EntityNavigation;
import me.darksnakex.villagerfollow.mobchip.ai.navigation.NavigationPath;
import me.darksnakex.villagerfollow.mobchip.util.Position;
import net.minecraft.core.BlockPosition;
import net.minecraft.world.entity.ai.navigation.NavigationAbstract;
import net.minecraft.world.level.pathfinder.PathEntity;
import net.minecraft.world.level.pathfinder.PathPoint;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;

final class EntityNavigation1_20_R1 implements EntityNavigation {
   private final NavigationAbstract handle;
   private int speedMod;
   private int range;
   private final List<Position> points;
   private final Mob m;

   public EntityNavigation1_20_R1(Mob m) {
      this.handle = ChipUtil1_20_R1.toNMS(m).J();
      this.points = new ArrayList();
      this.speedMod = 1;
      this.range = Integer.MAX_VALUE;
      this.m = m;
   }

   public double getSpeedModifier() {
      return (double)this.speedMod;
   }

   public void setSpeedModifier(double mod) throws IllegalArgumentException {
      if (mod > 2.147483647E9D) {
         throw new IllegalArgumentException("Must be integer");
      } else {
         this.speedMod = (int)Math.floor(mod);
      }
   }

   public EntityNavigation recompute() {
      this.handle.i();
      return this;
   }

   public EntityNavigation addPoint(@NotNull Position point) {
      this.points.add(point);
      return this;
   }

   public EntityNavigation addPoint(int index, @NotNull Position point) {
      this.points.add(index, point);
      return this;
   }

   public EntityNavigation removePoint(@NotNull Position point) {
      this.points.remove(point);
      return this;
   }

   public EntityNavigation removePoint(int index) {
      this.points.remove(index);
      return this;
   }

   private List<PathPoint> toNodes() {
      List<PathPoint> nodes = new ArrayList();
      Iterator var2 = this.points.iterator();

      while(var2.hasNext()) {
         Position p = (Position)var2.next();
         nodes.add(new PathPoint(p.getX(), p.getY(), p.getZ()));
      }

      return nodes;
   }

   @NotNull
   public NavigationPath buildPath() {
      if (this.points.isEmpty()) {
         throw new IllegalArgumentException("Path is empty");
      } else {
         return new NavigationPath1_20_R1(new PathEntity(this.toNodes(), (BlockPosition)null, true), this.m, (double)this.speedMod);
      }
   }

   public EntityNavigation setRange(int range) {
      this.range = range;
      return this;
   }

   public int getRange() {
      return this.range;
   }
}
