package me.darksnakex.villagerfollow.mobchip.ai.memories;

import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import org.bukkit.Keyed;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public interface Memory<T> extends Keyed {
   @NotNull
   Class<T> getBukkitClass();

   public static final class WalkingTarget implements SpeedModifier {
      private final Location loc;
      private float speedMod;
      private int distance;

      public WalkingTarget(@NotNull Location loc, float speedMod, int distance) {
         this.loc = loc;
         this.speedMod = speedMod;
         this.distance = distance;
      }

      public WalkingTarget(@NotNull Location loc, int distance) {
         this(loc, 1.5F, distance);
      }

      @NotNull
      public Location getLocation() {
         return this.loc;
      }

      public double getSpeedModifier() {
         return (double)this.speedMod;
      }

      public int getDistance() {
         return this.distance;
      }

      public void setDistance(int distance) {
         this.distance = distance;
      }

      public void setSpeedModifier(double mod) throws IllegalArgumentException {
         if (mod > 3.4028234663852886E38D) {
            throw new IllegalArgumentException("Float Speed Modifier");
         } else {
            this.speedMod = (float)mod;
         }
      }
   }
}
