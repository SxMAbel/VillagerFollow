package me.darksnakex.villagerfollow.mobchip.ai.navigation;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import me.darksnakex.villagerfollow.mobchip.util.Position;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

public interface EntityNavigation extends SpeedModifier {
   /** @deprecated */
   @Deprecated
   EntityNavigation recompute();

   EntityNavigation addPoint(@NotNull Position var1);

   default EntityNavigation addPoint(@NotNull Location point) {
      return this.addPoint(new Position(point));
   }

   default EntityNavigation addPoint(int x, int y, int z) {
      return this.addPoint(new Position(x, y, z));
   }

   default EntityNavigation addPoint(@NotNull Entity en) {
      return this.addPoint(en.getLocation());
   }

   EntityNavigation addPoint(int var1, @NotNull Position var2);

   default EntityNavigation addPoint(int index, @NotNull Location point) {
      return this.addPoint(index, new Position(point));
   }

   default EntityNavigation addPoint(int index, @NotNull Entity en) {
      return this.addPoint(index, en.getLocation());
   }

   EntityNavigation removePoint(@NotNull Position var1);

   EntityNavigation removePoint(int var1);

   default EntityNavigation removePoint(@NotNull Location point) {
      return this.removePoint(new Position(point));
   }

   default EntityNavigation removePoint(int x, int y, int z) {
      return this.removePoint(new Position(x, y, z));
   }

   EntityNavigation setRange(int var1);

   int getRange();

   NavigationPath buildPath() throws IllegalArgumentException;
}
