package me.darksnakex.villagerfollow.mobchip.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Position implements Cloneable {
   private int x;
   private int y;
   private int z;

   public Position(@NotNull Entity en) {
      this(en.getLocation());
   }

   public Position(@NotNull Location loc) {
      this(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
   }

   public Position(double x, double y, double z) {
      this((int)Math.floor(x), (int)Math.floor(y), (int)Math.floor(z));
   }

   public Position(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public double distance(@NotNull Position node) {
      return Math.sqrt(this.distanceSquared(node));
   }

   public double distance(@NotNull Location loc) {
      return this.distance(new Position(loc));
   }

   public double distance(@NotNull Entity en) {
      return this.distance(en.getLocation());
   }

   public double distanceSquared(@NotNull Position node) {
      int x = this.x - node.x;
      int y = this.y - node.y;
      int z = this.z - node.z;
      return (double)(x * x + y * y + z * z);
   }

   public double distanceSquared(@NotNull Location loc) {
      return this.distanceSquared(new Position(loc));
   }

   public double distanceSquared(@NotNull Entity en) {
      return this.distance(en.getLocation());
   }

   public double distanceSquared(double x, double y, double z) {
      return (new Position(x, y, z)).distanceSquared(this);
   }

   public double distanceManhattan(@NotNull Position node) {
      double x = (double)Math.abs(this.x - node.x);
      double y = (double)Math.abs(this.y - node.y);
      double z = (double)Math.abs(this.z - node.z);
      return x + y + z;
   }

   public double distanceManhattan(@NotNull Location loc) {
      return this.distanceManhattan(new Position(loc));
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public int getZ() {
      return this.z;
   }

   @NotNull
   public Position add(int x, int y, int z) {
      this.x += x;
      this.y += y;
      this.z += z;
      return this;
   }

   @NotNull
   public Position remove(int x, int y, int z) {
      this.x -= x;
      this.y -= y;
      this.z -= z;
      return this;
   }

   @NotNull
   public Position add(@NotNull Position node) {
      return this.add(node.x, node.y, node.z);
   }

   @NotNull
   public Position add(@NotNull Location loc) {
      return this.add(new Position(loc));
   }

   @NotNull
   public Position add(@NotNull Entity en) {
      return this.add(en.getLocation());
   }

   @NotNull
   public Position remove(@NotNull Location loc) {
      return this.remove(new Position(loc));
   }

   @NotNull
   public Position remove(@NotNull Entity en) {
      return this.remove(en.getLocation());
   }

   @NotNull
   public Position remove(@NotNull Position node) {
      return this.remove(node.x, node.y, node.z);
   }

   @NotNull
   public Location toLocation(@Nullable World w) {
      return new Location(w, (double)this.x, (double)this.y, (double)this.z);
   }

   @NotNull
   public Vector toVector() {
      return new Vector(this.x, this.y, this.z);
   }

   @NotNull
   public Position setX(int x) {
      this.x = x;
      return this;
   }

   @NotNull
   public Position setY(int y) {
      this.y = y;
      return this;
   }

   @NotNull
   public Position setZ(int z) {
      this.z = z;
      return this;
   }

   public Position clone() {
      return new Position(this.x, this.y, this.z);
   }
}
