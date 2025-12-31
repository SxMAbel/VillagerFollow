package me.darksnakex.villagerfollow.mobchip.abstraction.v1_21_R6;

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

final class EntityCombatTracker1_21_R6 implements EntityCombatTracker {
   private final CombatTracker handle;
   private final Mob m;

   public EntityCombatTracker1_21_R6(Mob m) {
      this.m = m;
      this.handle = ChipUtil1_21_R6.toNMS(m).fh();
   }

   @NotNull
   public String getCurrentDeathMessage() {
      return this.handle.a().getString();
   }

   @Nullable
   public CombatEntry getLatestEntry() {
      try {
         Field entriesF = CombatEntry.class.getDeclaredField("d");
         entriesF.setAccessible(true);
         List<net.minecraft.world.damagesource.CombatEntry> entries = (List)entriesF.get(this.handle);
         net.minecraft.world.damagesource.CombatEntry last = (net.minecraft.world.damagesource.CombatEntry)entries.getLast();
         return last == null ? null : ChipUtil1_21_R6.fromNMS(this.m, last);
      } catch (ReflectiveOperationException var4) {
         ChipUtil.printStackTrace(var4);
         return null;
      }
   }

   @NotNull
   public List<CombatEntry> getCombatEntries() {
      ArrayList entries = new ArrayList();

      try {
         Field f = CombatTracker.class.getDeclaredField("d");
         f.setAccessible(true);
         Stream var10000 = ((List)f.get(this.handle)).stream().map((en) -> {
            return ChipUtil1_21_R6.fromNMS(this.m, en);
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
         Field f = CombatTracker.class.getDeclaredField("d");
         f.setAccessible(true);
         Object entries = f.get(this.handle);
         Method m = List.class.getMethod("add", Object.class);
         m.invoke(entries, ChipUtil1_21_R6.toNMS(entry));
      } catch (Exception var5) {
         ChipUtil.printStackTrace(var5);
      }

   }

   public int getCombatDuration() {
      return this.handle.b();
   }

   public boolean isTakingDamage() {
      try {
         Field takingDamageF = CombatTracker.class.getDeclaredField("j");
         takingDamageF.setAccessible(true);
         return takingDamageF.getBoolean(this.handle);
      } catch (ReflectiveOperationException var2) {
         ChipUtil.printStackTrace(var2);
         return false;
      }
   }

   public boolean isInCombat() {
      try {
         Field inCombatF = CombatTracker.class.getDeclaredField("i");
         inCombatF.setAccessible(true);
         return inCombatF.getBoolean(this.handle);
      } catch (ReflectiveOperationException var2) {
         ChipUtil.printStackTrace(var2);
         return false;
      }
   }

   public boolean hasLastDamageCancelled() {
      return ChipUtil1_21_R6.toNMS(this.m).lastDamageCancelled;
   }
}
