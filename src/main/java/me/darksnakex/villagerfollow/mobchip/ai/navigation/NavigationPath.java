package me.darksnakex.villagerfollow.mobchip.ai.navigation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import me.darksnakex.villagerfollow.mobchip.ai.SpeedModifier;
import me.darksnakex.villagerfollow.mobchip.util.Position;
import org.jetbrains.annotations.Nullable;

public interface NavigationPath extends Iterable<Position>, SpeedModifier {
   boolean isDone();

   default boolean containsAll(Collection<? extends Position> coll) {
      AtomicBoolean state = new AtomicBoolean(true);
      Iterator var3 = coll.iterator();

      while(var3.hasNext()) {
         Position n = (Position)var3.next();
         if (!this.contains(n)) {
            state.set(false);
            break;
         }
      }

      return state.get();
   }

   boolean contains(@Nullable Position var1);

   boolean isEmpty();

   void advance() throws IllegalArgumentException;

   Position[] toArray();

   int indexOf(@Nullable Position var1);

   int lastIndexOf(@Nullable Position var1);

   default boolean containsAll(@Nullable Position... nodes) {
      return this.containsAll((Collection)Arrays.asList(nodes));
   }
}
