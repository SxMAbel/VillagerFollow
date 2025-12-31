package me.darksnakex.villagerfollow.mobchip.bukkit.events;

import me.darksnakex.villagerfollow.mobchip.EntityBrain;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RestrictionSetEvent extends BrainEvent {
   private final Location oldLoc;
   private Location newLoc;
   private final int oldRadius;
   private int newRadius;

   public RestrictionSetEvent(@NotNull EntityBrain brain, @Nullable Location oldLoc, @Nullable Location newLoc, int oldRadius, int newRadius) {
      super(brain);
      this.oldLoc = oldLoc;
      this.newLoc = newLoc;
      this.oldRadius = oldRadius;
      this.newRadius = newRadius;
   }

   @Nullable
   public Location getOldCenter() {
      return this.oldLoc;
   }

   public int getOldRadius() {
      return this.oldRadius;
   }

   public int getNewRadius() {
      return this.newRadius;
   }

   public void setNewRadius(int radius) {
      this.newRadius = radius;
   }

   @Nullable
   public Location getNewCenter() {
      return this.newLoc;
   }

   public void setNewCenter(@Nullable Location loc) {
      this.newLoc = loc == null ? this.newLoc : loc;
   }
}
