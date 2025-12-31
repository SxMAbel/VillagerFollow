package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R2;

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

final class EntityScheduleManager1_21_R2 implements EntityScheduleManager {
   private final EntityInsentient nmsMob;

   public EntityScheduleManager1_21_R2(Mob m) {
      this.nmsMob = ChipUtil1_21_R2.toNMS(m);
   }

   @Nullable
   public Schedule getCurrentSchedule() {
      return ChipUtil1_21_R2.fromNMS(this.nmsMob.ec().c());
   }

   public void setSchedule(@NotNull Schedule s) {
      this.nmsMob.ec().a(ChipUtil1_21_R2.toNMS(s));
   }

   @NotNull
   public Set<Activity> getActiveActivities() {
      return (Set)this.nmsMob.ec().d().stream().map(ChipUtil1_21_R2::fromNMS).collect(Collectors.toSet());
   }

   public void setDefaultActivity(@NotNull Activity a) {
      this.nmsMob.ec().b(ChipUtil1_21_R2.toNMS(a));
   }

   public void useDefaultActivity() {
      this.nmsMob.ec().f();
   }

   public void setRunningActivity(@NotNull Activity a) {
      this.nmsMob.ec().a(ChipUtil1_21_R2.toNMS(a));
   }

   @Nullable
   public Activity getRunningActivity() {
      return this.nmsMob.ec().g().isPresent() ? ChipUtil1_21_R2.fromNMS((net.minecraft.world.entity.schedule.Activity)this.nmsMob.ec().g().get()) : null;
   }

   public boolean isRunning(@NotNull Activity a) {
      return this.nmsMob.ec().c(ChipUtil1_21_R2.toNMS(a));
   }

   public int size() {
      return this.nmsMob.ec().e().size();
   }

   public boolean isEmpty() {
      return this.nmsMob.ec().e().isEmpty();
   }

   @Nullable
   public Consumer<Mob> put(@NotNull Activity key, Consumer<Mob> value) {
      this.nmsMob.ec().a(ChipUtil1_21_R2.toNMS(key), 0, ImmutableList.of(ChipUtil1_21_R2.toNMS(value)));
      return value;
   }

   public void clear() {
      this.nmsMob.ec().h();
   }
}
