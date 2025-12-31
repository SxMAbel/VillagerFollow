package me.darksnakex.villagerfollow.mobchip.abstraction.v1_18_R1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import me.darksnakex.villagerfollow.mobchip.combat.CombatEntry;
import me.darksnakex.villagerfollow.mobchip.combat.EntityCombatTracker;
import net.minecraft.world.damagesource.CombatTracker;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class EntityCombatTracker1_18_R1 implements EntityCombatTracker {
   private final CombatTracker handle;
   private final Mob m;

   public EntityCombatTracker1_18_R1(Mob m) {
      this.m = m;
      this.handle = ChipUtil1_18_R1.toNMS(m).ej();
   }

   @NotNull
   public String getCurrentDeathMessage() {
      return this.handle.b().getString();
   }

   @Nullable
   public CombatEntry getLatestEntry() {
      return this.handle.i() == null ? null : ChipUtil1_18_R1.fromNMS(this.m, this.handle.i());
   }

   @NotNull
   public List<CombatEntry> getCombatEntries() {
      ArrayList entries = new ArrayList();

      try {
         Field f = CombatTracker.class.getDeclaredField("c");
         f.setAccessible(true);
         Stream var10000 = ((List)f.get(this.handle)).stream().map((en) -> {
            return ChipUtil1_18_R1.fromNMS(this.m, en);
         });
         Objects.requireNonNull(entries);
         var10000.forEach(entries::add);
      } catch (Exception var3) {
         ChipUtil.printStackTrace(var3);
      }

      return entries;
   }

   public void recordEntry(@NotNull CombatEntry entry) {
      try {
         Field f = CombatTracker.class.getDeclaredField("c");
         f.setAccessible(true);
         Object entries = f.get(this.handle);
         Method m = List.class.getMethod("add", Object.class);
         m.invoke(entries, ChipUtil1_18_R1.toNMS(entry));
      } catch (Exception var5) {
         ChipUtil.printStackTrace(var5);
      }

   }

   public int getCombatDuration() {
      return this.handle.f();
   }

   public boolean isTakingDamage() {
      return this.handle.d();
   }

   public boolean isInCombat() {
      return this.handle.e();
   }

   public boolean hasLastDamageCancelled() {
      return ChipUtil1_18_R1.toNMS(this.m).lastDamageCancelled;
   }
}
