package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R5;

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

final class EntityScheduleManager1_21_R5 implements EntityScheduleManager {
   private final EntityInsentient nmsMob;

   public EntityScheduleManager1_21_R5(Mob m) {
      this.nmsMob = ChipUtil1_21_R5.toNMS(m);
   }

   @Nullable
   public Schedule getCurrentSchedule() {
      return ChipUtil1_21_R5.fromNMS(this.nmsMob.eh().c());
   }

   public void setSchedule(@NotNull Schedule s) {
      this.nmsMob.eh().a(ChipUtil1_21_R5.toNMS(s));
   }

   @NotNull
   public Set<Activity> getActiveActivities() {
      return (Set)this.nmsMob.eh().d().stream().map(ChipUtil1_21_R5::fromNMS).collect(Collectors.toSet());
   }

   public void setDefaultActivity(@NotNull Activity a) {
      this.nmsMob.eh().b(ChipUtil1_21_R5.toNMS(a));
   }

   public void useDefaultActivity() {
      this.nmsMob.eh().f();
   }

   public void setRunningActivity(@NotNull Activity a) {
      this.nmsMob.eh().a(ChipUtil1_21_R5.toNMS(a));
   }

   @Nullable
   public Activity getRunningActivity() {
      return this.nmsMob.eh().g().isPresent() ? ChipUtil1_21_R5.fromNMS((net.minecraft.world.entity.schedule.Activity)this.nmsMob.eh().g().get()) : null;
   }

   public boolean isRunning(@NotNull Activity a) {
      return this.nmsMob.eh().c(ChipUtil1_21_R5.toNMS(a));
   }

   public int size() {
      return this.nmsMob.eh().e().size();
   }

   public boolean isEmpty() {
      return this.nmsMob.eh().e().isEmpty();
   }

   @Nullable
   public Consumer<Mob> put(@NotNull Activity key, Consumer<Mob> value) {
      this.nmsMob.eh().a(ChipUtil1_21_R5.toNMS(key), 0, ImmutableList.of(ChipUtil1_21_R5.toNMS(value)));
      return value;
   }

   public void clear() {
      this.nmsMob.eh().h();
   }
}
