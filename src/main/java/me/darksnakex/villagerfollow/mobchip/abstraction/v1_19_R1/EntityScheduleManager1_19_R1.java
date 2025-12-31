package me.darksnakex.villagerfollow.mobchip.abstraction.v1_19_R1;

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

final class EntityScheduleManager1_19_R1 implements EntityScheduleManager {
   private final EntityInsentient nmsMob;

   public EntityScheduleManager1_19_R1(Mob m) {
      this.nmsMob = ChipUtil1_19_R1.toNMS(m);
   }

   @Nullable
   public Schedule getCurrentSchedule() {
      return ChipUtil1_19_R1.fromNMS(this.nmsMob.dy().b());
   }

   public void setSchedule(@NotNull Schedule s) {
      this.nmsMob.dy().a(ChipUtil1_19_R1.toNMS(s));
   }

   @NotNull
   public Set<Activity> getActiveActivities() {
      return (Set)this.nmsMob.dy().c().stream().map(ChipUtil1_19_R1::fromNMS).collect(Collectors.toSet());
   }

   public void setDefaultActivity(@NotNull Activity a) {
      this.nmsMob.dy().b(ChipUtil1_19_R1.toNMS(a));
   }

   public void useDefaultActivity() {
      this.nmsMob.dy().e();
   }

   public void setRunningActivity(@NotNull Activity a) {
      this.nmsMob.dy().a(ChipUtil1_19_R1.toNMS(a));
   }

   @Nullable
   public Activity getRunningActivity() {
      return this.nmsMob.dy().f().isPresent() ? ChipUtil1_19_R1.fromNMS((net.minecraft.world.entity.schedule.Activity)this.nmsMob.dy().f().get()) : null;
   }

   public boolean isRunning(@NotNull Activity a) {
      return this.nmsMob.dy().c(ChipUtil1_19_R1.toNMS(a));
   }

   public int size() {
      return this.nmsMob.dy().d().size();
   }

   public boolean isEmpty() {
      return this.nmsMob.dy().d().isEmpty();
   }

   @Nullable
   public Consumer<Mob> put(@NotNull Activity key, Consumer<Mob> value) {
      this.nmsMob.dy().a(ChipUtil1_19_R1.toNMS(key), 0, ImmutableList.of(ChipUtil1_19_R1.toNMS(value)));
      return value;
   }

   public void clear() {
      this.nmsMob.dy().g();
   }
}
