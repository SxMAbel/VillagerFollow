package me.darksnakex.villagerfollow.mobchip.bukkit;

import java.util.List;
import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.DragonBehavior;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitDragonBehavior extends BukkitEntityBehavior implements DragonBehavior {
   final EnderDragon m;
   private static final ChipUtil wrapper = ChipUtil.getWrapper();

   public BukkitDragonBehavior(EnderDragon m) {
      super((Mob)m);
      this.m = m;
   }

   @NotNull
   public BehaviorResult naturalKnockback(@Nullable List<Entity> entities) {
      if (entities == null) {
         return BehaviorResult.STOPPED;
      } else {
         wrapper.knockback(this.m, entities);
         return BehaviorResult.STOPPED;
      }
   }
}
