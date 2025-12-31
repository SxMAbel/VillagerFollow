package me.darksnakex.villagerfollow.mobchip.bukkit;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import me.darksnakex.villagerfollow.mobchip.ai.EntityAI;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import me.darksnakex.villagerfollow.mobchip.ai.goal.WrappedPathfinder;
import me.darksnakex.villagerfollow.mobchip.bukkit.events.pathfinder.PathfinderAddEvent;
import me.darksnakex.villagerfollow.mobchip.bukkit.events.pathfinder.PathfinderClearEvent;
import me.darksnakex.villagerfollow.mobchip.bukkit.events.pathfinder.PathfinderRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class BukkitAI implements EntityAI {
   private final Set<WrappedPathfinder> goals = new HashSet();
   private final boolean target;
   private final Mob m;
   private static final ChipUtil wrapper = ChipUtil.getWrapper();

   BukkitAI(Mob m, boolean target) {
      this.target = target;
      this.m = m;
      this.updateMap();
   }

   private void updateMap() {
      this.goals.clear();
      this.goals.addAll(wrapper.getGoals(this.m, this.target));
   }

   private void updateAI() {
      wrapper.clearPathfinders(this.m, this.target);
      wrapper.addPathfinders(this.goals, this.target);
   }

   public int size() {
      return this.goals.size();
   }

   public boolean isEmpty() {
      return this.goals.isEmpty();
   }

   public boolean contains(Object o) {
      if (!(o instanceof WrappedPathfinder)) {
         return false;
      } else {
         WrappedPathfinder p = (WrappedPathfinder)o;
         return wrapper.getGoals(this.m, this.target).contains(p);
      }
   }

   @NotNull
   public Mob getEntity() {
      return this.m;
   }

   public boolean contains(@NotNull Pathfinder value) {
      return ((List)wrapper.getGoals(this.m, this.target).stream().map(WrappedPathfinder::getPathfinder).collect(Collectors.toList())).contains(value);
   }

   public Pathfinder put(@NotNull Pathfinder p, int priority) {
      this.add(new WrappedPathfinder(p, priority));
      return p;
   }

   public void putAll(@NotNull Map<? extends Pathfinder, Integer> map) {
      map.forEach(this::put);
   }

   public boolean remove(@NotNull Pathfinder p) {
      Iterator var2 = wrapper.getGoals(this.m, this.target).iterator();

      WrappedPathfinder w;
      do {
         if (!var2.hasNext()) {
            return false;
         }

         w = (WrappedPathfinder)var2.next();
      } while(!w.getPathfinder().equals(p));

      return this.remove((Object)w);
   }

   public boolean isRunning(@NotNull Pathfinder p) {
      return ((List)wrapper.getRunningGoals(this.m, this.target).stream().map(WrappedPathfinder::getPathfinder).collect(Collectors.toList())).contains(p);
   }

   @NotNull
   public Iterator<WrappedPathfinder> iterator() {
      return this.goals.iterator();
   }

   @NotNull
   public WrappedPathfinder[] toArray() {
      return (WrappedPathfinder[])this.goals.toArray(new WrappedPathfinder[0]);
   }

   @NotNull
   public <T> T[] toArray(@NotNull T[] a) {
      return a;
   }

   public boolean add(WrappedPathfinder p) {
      boolean success = this.goals.add(p);
      PathfinderAddEvent event = new PathfinderAddEvent(this, p.getPathfinder(), this.target, p.getPriority());
      Bukkit.getPluginManager().callEvent(event);
      this.updateAI();
      return success;
   }

   public boolean remove(Object key) {
      if (!(key instanceof WrappedPathfinder)) {
         return false;
      } else {
         WrappedPathfinder p = (WrappedPathfinder)key;
         boolean success = this.goals.remove(key);
         PathfinderRemoveEvent event = new PathfinderRemoveEvent(this, p.getPathfinder(), this.target);
         Bukkit.getPluginManager().callEvent(event);
         this.updateAI();
         return success;
      }
   }

   public boolean containsAll(@NotNull Collection<?> c) {
      return this.goals.containsAll(c);
   }

   public boolean addAll(@NotNull Collection<? extends WrappedPathfinder> c) {
      AtomicBoolean success = new AtomicBoolean(true);
      c.forEach((w) -> {
         if (!this.add(w)) {
            success.set(false);
         }

      });
      return success.get();
   }

   public boolean retainAll(@NotNull Collection<?> c) {
      return this.goals.retainAll(c);
   }

   public boolean removeAll(@NotNull Collection<?> c) {
      return this.goals.removeAll(c);
   }

   public void clear() {
      this.goals.clear();
      this.updateAI();
      PathfinderClearEvent event = new PathfinderClearEvent(this, this.target);
      Bukkit.getPluginManager().callEvent(event);
   }

   @NotNull
   public Set<WrappedPathfinder> getRunningGoals() {
      return new HashSet(wrapper.getRunningGoals(this.m, this.target));
   }

   public void disableFlag(@Nullable Pathfinder.PathfinderFlag flag) {
      wrapper.setFlag(this.m, flag, this.target, false);
   }

   public void enableFlag(@Nullable Pathfinder.PathfinderFlag flag) {
      wrapper.setFlag(this.m, flag, this.target, true);
   }
}
