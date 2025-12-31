package me.darksnakex.villagerfollow.mobchip.abstraction.v1_20_R3;

import com.google.common.collect.ImmutableList;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.Activity;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.EntityScheduleManager;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.Schedule;
import net.minecraft.world.entity.EntityInsentient;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class EntityScheduleManager1_20_R3 implements EntityScheduleManager {
   private final EntityInsentient nmsMob;

   public EntityScheduleManager1_20_R3(Mob m) {
      this.nmsMob = ChipUtil1_20_R3.toNMS(m);
   }

   @Nullable
   public Schedule getCurrentSchedule() {
      return ChipUtil1_20_R3.fromNMS(this.nmsMob.dO().c());
   }

   public void setSchedule(@NotNull Schedule s) {
      this.nmsMob.dO().a(ChipUtil1_20_R3.toNMS(s));
   }

   @NotNull
   public Set<Activity> getActiveActivities() {
      return (Set)this.nmsMob.dO().d().stream().map(ChipUtil1_20_R3::fromNMS).collect(Collectors.toSet());
   }

   public void setDefaultActivity(@NotNull Activity a) {
      this.nmsMob.dO().b(ChipUtil1_20_R3.toNMS(a));
   }

   public void useDefaultActivity() {
      this.nmsMob.dO().f();
   }

   public void setRunningActivity(@NotNull Activity a) {
      this.nmsMob.dO().a(ChipUtil1_20_R3.toNMS(a));
   }

   @Nullable
   public Activity getRunningActivity() {
      return this.nmsMob.dO().g().isPresent() ? ChipUtil1_20_R3.fromNMS((net.minecraft.world.entity.schedule.Activity)this.nmsMob.dO().g().get()) : null;
   }

   public boolean isRunning(@NotNull Activity a) {
      return this.nmsMob.dO().c(ChipUtil1_20_R3.toNMS(a));
   }

   public int size() {
      return this.nmsMob.dO().e().size();
   }

   public boolean isEmpty() {
      return this.nmsMob.dO().e().isEmpty();
   }

   @Nullable
   public Consumer<Mob> put(@NotNull Activity key, Consumer<Mob> value) {
      this.nmsMob.dO().a(ChipUtil1_20_R3.toNMS(key), 0, ImmutableList.of(ChipUtil1_20_R3.toNMS(value)));
      return value;
   }

   public void clear() {
      this.nmsMob.dO().h();
   }
}
