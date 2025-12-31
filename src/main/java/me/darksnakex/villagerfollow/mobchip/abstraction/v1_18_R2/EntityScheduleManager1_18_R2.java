package me.darksnakex.villagerfollow.mobchip.abstraction.v1_18_R2;

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

final class EntityScheduleManager1_18_R2 implements EntityScheduleManager {
   private final EntityInsentient nmsMob;

   public EntityScheduleManager1_18_R2(Mob m) {
      this.nmsMob = ChipUtil1_18_R2.toNMS(m);
   }

   @Nullable
   public Schedule getCurrentSchedule() {
      return ChipUtil1_18_R2.fromNMS(this.nmsMob.du().b());
   }

   public void setSchedule(@NotNull Schedule s) {
      this.nmsMob.du().a(ChipUtil1_18_R2.toNMS(s));
   }

   @NotNull
   public Set<Activity> getActiveActivities() {
      return (Set)this.nmsMob.du().c().stream().map(ChipUtil1_18_R2::fromNMS).collect(Collectors.toSet());
   }

   public void setDefaultActivity(@NotNull Activity a) {
      this.nmsMob.du().b(ChipUtil1_18_R2.toNMS(a));
   }

   public void useDefaultActivity() {
      this.nmsMob.du().e();
   }

   public void setRunningActivity(@NotNull Activity a) {
      this.nmsMob.du().a(ChipUtil1_18_R2.toNMS(a));
   }

   @Nullable
   public Activity getRunningActivity() {
      return this.nmsMob.du().f().isPresent() ? ChipUtil1_18_R2.fromNMS((net.minecraft.world.entity.schedule.Activity)this.nmsMob.du().f().get()) : null;
   }

   public boolean isRunning(@NotNull Activity a) {
      return this.nmsMob.du().c(ChipUtil1_18_R2.toNMS(a));
   }

   public int size() {
      return this.nmsMob.du().d().size();
   }

   public boolean isEmpty() {
      return this.nmsMob.du().d().isEmpty();
   }

   @Nullable
   public Consumer<Mob> put(@NotNull Activity key, Consumer<Mob> value) {
      this.nmsMob.du().a(ChipUtil1_18_R2.toNMS(key), 0, ImmutableList.of(ChipUtil1_18_R2.toNMS(value)));
      return value;
   }

   public void clear() {
      this.nmsMob.du().g();
   }
}
