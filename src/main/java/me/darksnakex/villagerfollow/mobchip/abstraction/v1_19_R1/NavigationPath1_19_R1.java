package me.darksnakex.villagerfollow.mobchip.abstraction.v1_19_R1;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import me.darksnakex.villagerfollow.mobchip.ai.navigation.NavigationPath;
import me.darksnakex.villagerfollow.mobchip.util.Position;
import net.minecraft.world.level.pathfinder.PathEntity;
import net.minecraft.world.level.pathfinder.PathPoint;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class NavigationPath1_19_R1 implements NavigationPath {
   private String name;
   private final Mob m;
   private final PathEntity handle;
   private double speedMod;
   private final List<Position> nodes = new ArrayList();

   NavigationPath1_19_R1(@NotNull PathEntity nms, @NotNull Mob m, double speedMod) {
      this.m = m;
      this.name = "bukkitpath";
      this.handle = nms;
      this.speedMod = speedMod;

      try {
         Field points = this.handle.getClass().getDeclaredField("a");
         points.setAccessible(true);
         List<PathPoint> pathPoints = (List)points.get(this.handle);
         this.nodes.addAll((Collection)pathPoints.stream().map(ChipUtil1_19_R1::fromNMS).collect(Collectors.toSet()));
      } catch (ReflectiveOperationException var7) {
         ChipUtil.printStackTrace(var7);
      }

   }

   public void advance() {
      if (this.isDone()) {
         throw new IllegalArgumentException("Path is already done");
      } else {
         PathPoint n = this.handle.h();
         (new EntityController1_19_R1(this.m)).moveTo((double)n.a, (double)n.b, (double)n.c, this.speedMod);
         this.getHandle().a();
      }
   }

   public String getName() {
      return this.name;
   }

   public void setName(@NotNull String name) {
      this.name = name;
   }

   public PathEntity getHandle() {
      return this.handle;
   }

   public boolean isDone() {
      return this.handle.c();
   }

   public int size() {
      return this.nodes.size();
   }

   public boolean isEmpty() {
      return this.nodes.isEmpty();
   }

   public boolean contains(@Nullable Position o) {
      return this.nodes.contains(o);
   }

   @NotNull
   public Iterator<Position> iterator() {
      return this.nodes.iterator();
   }

   @NotNull
   public Position[] toArray() {
      return (Position[])this.nodes.toArray(new Position[0]);
   }

   public int indexOf(@Nullable Position o) {
      return this.nodes.indexOf(o);
   }

   public int lastIndexOf(@Nullable Position o) {
      return this.nodes.lastIndexOf(o);
   }

   public double getSpeedModifier() {
      return this.speedMod;
   }

   public void setSpeedModifier(double mod) {
      this.speedMod = mod;
   }
}
