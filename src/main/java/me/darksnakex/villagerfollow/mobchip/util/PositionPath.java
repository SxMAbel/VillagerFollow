package me.darksnakex.villagerfollow.mobchip.util;

import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PositionPath {
   private final List<Position> positions;
   private final Location targetLocation;
   private int nextPositionIndex;

   public PositionPath(@Nullable Iterable<? extends Position> positions, @NotNull Location targetLocation) throws IllegalArgumentException {
      if (positions != null) {
         this.positions.addAll((Collection)ImmutableList.copyOf(positions).stream().filter(Objects::nonNull).collect(Collectors.toList()));
      }

      this.targetLocation = targetLocation;
   }

   @Nullable
   public World getWorld() {
      return this.targetLocation.getWorld();
   }

   public int getNextPositionIndex() {
      return this.nextPositionIndex;
   }

   public Position advance() throws IllegalStateException {
      if (this.isReached()) {
         throw new IllegalStateException("Path has no more positions!");
      } else {
         ++this.nextPositionIndex;
         return (Position)this.positions.get(this.nextPositionIndex);
      }
   }

   public void addPosition(@NotNull Position position) throws IllegalArgumentException {
      this.positions.add(position);
   }

   public void addPosition(@NotNull Position position, int index) throws IllegalArgumentException {
      if (index < 0) {
         throw new IllegalArgumentException("Index cannot be negative!");
      } else if (position == null) {
         throw new IllegalArgumentException("Position cannot be null!");
      } else {
         this.positions.add(index, position);
      }
   }

   @Nullable
   public Position getEndPosition() {
      return (Position)this.positions.get(this.positions.size() - 1);
   }

   @Nullable
   public Position getPosition(int index) {
      return (Position)this.positions.get(index);
   }

   @NotNull
   public Position getCurrentPosition() {
      return (Position)this.positions.get(this.nextPositionIndex);
   }

   @NotNull
   public List<Position> getPositions() {
      return ImmutableList.copyOf(this.positions);
   }

   @NotNull
   public Location getTargetLocation() {
      return this.targetLocation;
   }

   public boolean isReached() {
      return this.nextPositionIndex >= this.positions.size();
   }

   public boolean notStarted() {
      return this.nextPositionIndex == 0;
   }
}
