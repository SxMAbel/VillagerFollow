package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R3;

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

final class EntityScheduleManager1_21_R3 implements EntityScheduleManager {
   private final EntityInsentient nmsMob;

   public EntityScheduleManager1_21_R3(Mob m) {
      this.nmsMob = ChipUtil1_21_R3.toNMS(m);
   }

   @Nullable
   public Schedule getCurrentSchedule() {
      return ChipUtil1_21_R3.fromNMS(this.nmsMob.eb().c());
   }

   public void setSchedule(@NotNull Schedule s) {
      this.nmsMob.eb().a(ChipUtil1_21_R3.toNMS(s));
   }

   @NotNull
   public Set<Activity> getActiveActivities() {
      return (Set)this.nmsMob.eb().d().stream().map(ChipUtil1_21_R3::fromNMS).collect(Collectors.toSet());
   }

   public void setDefaultActivity(@NotNull Activity a) {
      this.nmsMob.eb().b(ChipUtil1_21_R3.toNMS(a));
   }

   public void useDefaultActivity() {
      this.nmsMob.eb().f();
   }

   public void setRunningActivity(@NotNull Activity a) {
      this.nmsMob.eb().a(ChipUtil1_21_R3.toNMS(a));
   }

   @Nullable
   public Activity getRunningActivity() {
      return this.nmsMob.eb().g().isPresent() ? ChipUtil1_21_R3.fromNMS((net.minecraft.world.entity.schedule.Activity)this.nmsMob.eb().g().get()) : null;
   }

   public boolean isRunning(@NotNull Activity a) {
      return this.nmsMob.eb().c(ChipUtil1_21_R3.toNMS(a));
   }

   public int size() {
      return this.nmsMob.eb().e().size();
   }

   public boolean isEmpty() {
      return this.nmsMob.eb().e().isEmpty();
   }

   @Nullable
   public Consumer<Mob> put(@NotNull Activity key, Consumer<Mob> value) {
      this.nmsMob.eb().a(ChipUtil1_21_R3.toNMS(key), 0, ImmutableList.of(ChipUtil1_21_R3.toNMS(value)));
      return value;
   }

   public void clear() {
      this.nmsMob.eb().h();
   }
}
