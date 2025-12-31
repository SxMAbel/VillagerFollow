package me.darksnakex.villagerfollow.mobchip.abstraction.v1_18_R1;

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

final class EntityScheduleManager1_18_R1 implements EntityScheduleManager {
   private final EntityInsentient nmsMob;

   public EntityScheduleManager1_18_R1(Mob m) {
      this.nmsMob = ChipUtil1_18_R1.toNMS(m);
   }

   @Nullable
   public Schedule getCurrentSchedule() {
      return ChipUtil1_18_R1.fromNMS(this.nmsMob.dt().b());
   }

   public void setSchedule(@NotNull Schedule s) {
      this.nmsMob.dt().a(ChipUtil1_18_R1.toNMS(s));
   }

   @NotNull
   public Set<Activity> getActiveActivities() {
      return (Set)this.nmsMob.dt().c().stream().map(ChipUtil1_18_R1::fromNMS).collect(Collectors.toSet());
   }

   public void setDefaultActivity(@NotNull Activity a) {
      this.nmsMob.dt().b(ChipUtil1_18_R1.toNMS(a));
   }

   public void useDefaultActivity() {
      this.nmsMob.dt().e();
   }

   public void setRunningActivity(@NotNull Activity a) {
      this.nmsMob.dt().a(ChipUtil1_18_R1.toNMS(a));
   }

   @Nullable
   public Activity getRunningActivity() {
      return this.nmsMob.dt().f().isPresent() ? ChipUtil1_18_R1.fromNMS((net.minecraft.world.entity.schedule.Activity)this.nmsMob.dt().f().get()) : null;
   }

   public boolean isRunning(@NotNull Activity a) {
      return this.nmsMob.dt().c(ChipUtil1_18_R1.toNMS(a));
   }

   public int size() {
      return this.nmsMob.dt().d().size();
   }

   public boolean isEmpty() {
      return this.nmsMob.dt().d().isEmpty();
   }

   @Nullable
   public Consumer<Mob> put(@NotNull Activity key, Consumer<Mob> value) {
      this.nmsMob.dt().a(ChipUtil1_18_R1.toNMS(key), 0, ImmutableList.of(ChipUtil1_18_R1.toNMS(value)));
      return value;
   }

   public void clear() {
      this.nmsMob.dt().g();
   }
}
