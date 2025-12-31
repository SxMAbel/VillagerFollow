package me.darksnakex.villagerfollow.mobchip.abstraction.v1_20_R4;

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

final class EntityScheduleManager1_20_R4 implements EntityScheduleManager {
   private final EntityInsentient nmsMob;

   public EntityScheduleManager1_20_R4(Mob m) {
      this.nmsMob = ChipUtil1_20_R4.toNMS(m);
   }

   @Nullable
   public Schedule getCurrentSchedule() {
      return ChipUtil1_20_R4.fromNMS(this.nmsMob.dS().c());
   }

   public void setSchedule(@NotNull Schedule s) {
      this.nmsMob.dS().a(ChipUtil1_20_R4.toNMS(s));
   }

   @NotNull
   public Set<Activity> getActiveActivities() {
      return (Set)this.nmsMob.dS().d().stream().map(ChipUtil1_20_R4::fromNMS).collect(Collectors.toSet());
   }

   public void setDefaultActivity(@NotNull Activity a) {
      this.nmsMob.dS().b(ChipUtil1_20_R4.toNMS(a));
   }

   public void useDefaultActivity() {
      this.nmsMob.dS().f();
   }

   public void setRunningActivity(@NotNull Activity a) {
      this.nmsMob.dS().a(ChipUtil1_20_R4.toNMS(a));
   }

   @Nullable
   public Activity getRunningActivity() {
      return this.nmsMob.dS().g().isPresent() ? ChipUtil1_20_R4.fromNMS((net.minecraft.world.entity.schedule.Activity)this.nmsMob.dS().g().get()) : null;
   }

   public boolean isRunning(@NotNull Activity a) {
      return this.nmsMob.dS().c(ChipUtil1_20_R4.toNMS(a));
   }

   public int size() {
      return this.nmsMob.dS().e().size();
   }

   public boolean isEmpty() {
      return this.nmsMob.dS().e().isEmpty();
   }

   @Nullable
   public Consumer<Mob> put(@NotNull Activity key, Consumer<Mob> value) {
      this.nmsMob.dS().a(ChipUtil1_20_R4.toNMS(key), 0, ImmutableList.of(ChipUtil1_20_R4.toNMS(value)));
      return value;
   }

   public void clear() {
      this.nmsMob.dS().h();
   }
}
