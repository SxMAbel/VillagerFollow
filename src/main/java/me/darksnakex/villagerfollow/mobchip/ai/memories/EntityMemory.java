package me.darksnakex.villagerfollow.mobchip.ai.memories;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import me.darksnakex.villagerfollow.mobchip.util.PositionPath;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EntityMemory<T> implements Memory<T> {
   public static final EntityMemory<Boolean> NOT_ADMIRING = new EntityMemory(Boolean.class, "admiring_disabled");
   public static final EntityMemory<Location> CELEBRATING_LOCATION = new EntityMemory(Location.class, "celebrate_location");
   public static final EntityMemory<Location> HOME = new EntityMemory(Location.class, "home");
   public static final EntityMemory<Location> JOB_SITE = new EntityMemory(Location.class, "job_site");
   public static final EntityMemory<Location> POSSIBLE_JOB_SITE = new EntityMemory(Location.class, "potential_job_site");
   public static final EntityMemory<Location> MEETING_POINT = new EntityMemory(Location.class, "meeting_point");
   public static final EntityMemory<Location[]> SECONDARY_JOB_SITE = new EntityMemory(Location[].class, "secondary_job_site");
   public static final EntityMemory<LivingEntity[]> NEAREST_LIVING_ENTITIES = new EntityMemory(LivingEntity[].class, "mobs");
   public static final EntityMemory<LivingEntity[]> NEAREST_VISIBLE_LIVING_ENTITIES = new EntityMemory(LivingEntity[].class, "visible_mobs");
   public static final EntityMemory<Villager[]> VISIBLE_VILLAGER_BABIES = new EntityMemory(Villager[].class, "visible_villager_babies");
   public static final EntityMemory<Player[]> NEAREST_PLAYERS = new EntityMemory(Player[].class, "nearest_players");
   public static final EntityMemory<Player> NEAREST_VISIBLE_PLAYER = new EntityMemory(Player.class, "nearest_visible_player");
   public static final EntityMemory<Player> NEAREST_VISIBLE_ATTACKING_PLAYER = new EntityMemory(Player.class, "nearest_visible_targetable_player");
   public static final EntityMemory<Memory.WalkingTarget> WALKING_TARGET = new EntityMemory(Memory.WalkingTarget.class, "walk_target");
   public static final EntityMemory<Location> LOOKING_TARGET = new EntityMemory(Location.class, "look_target");
   public static final EntityMemory<LivingEntity> ATTACK_TARGET = new EntityMemory(LivingEntity.class, "attack_target");
   public static final EntityMemory<Boolean> ATTACK_COOLING_DOWN = new EntityMemory(Boolean.class, "attack_cooling_down");
   public static final EntityMemory<LivingEntity> INTERACTION_TARGET = new EntityMemory(LivingEntity.class, "interaction_target");
   public static final EntityMemory<Ageable> BREEDING_TARGET = new EntityMemory(Ageable.class, "breed_target");
   public static final EntityMemory<Entity> RIDING_TARGET = new EntityMemory(Entity.class, "ride_target");
   public static final EntityMemory<Block[]> INTERACTABLE_DOORS = new EntityMemory(Block[].class, "interactable_doors");
   public static final EntityMemory<Block[]> DOORS_TO_CLOSE = new EntityMemory(Block[].class, "doors_to_close");
   public static final EntityMemory<Location> NEAREST_BED = new EntityMemory(Location.class, "nearest_ved");
   public static final EntityMemory<DamageCause> LAST_HURT_CAUSE = new EntityMemory(DamageCause.class, "hurt_by");
   public static final EntityMemory<LivingEntity> LAST_HURT_ENTITY = new EntityMemory(LivingEntity.class, "hurt_by_entity");
   public static final EntityMemory<LivingEntity> AVOID_TARGET = new EntityMemory(LivingEntity.class, "avoid_target");
   public static final EntityMemory<LivingEntity> NEAREST_HOSTILE = new EntityMemory(LivingEntity.class, "nearest_hostile");
   public static final EntityMemory<LivingEntity> NEAREST_ATTACKABLE = new EntityMemory(LivingEntity.class, "nearest_attackable");
   public static final EntityMemory<Location> HIDING_PLACE = new EntityMemory(Location.class, "hiding_place");
   public static final EntityMemory<Long> HEARD_BELL_DURATION = new EntityMemory(Long.class, "heard_bell_time");
   public static final EntityMemory<Long> LAST_FAILURE_WALK_TARGET = new EntityMemory(Long.class, "cant_reach_walk_target_since");
   public static final EntityMemory<Boolean> DETECTED_GOLEM = new EntityMemory(Boolean.class, "golem_detected_recently");
   public static final EntityMemory<Long> LAST_SLEPT = new EntityMemory(Long.class, "last_slept");
   public static final EntityMemory<Long> LAST_WOKEN = new EntityMemory(Long.class, "last_woken");
   public static final EntityMemory<Long> LAST_WORKED = new EntityMemory(Long.class, "last_worked_at_poi");
   public static final EntityMemory<Ageable> NEAREST_VISIBLE_ADULT = new EntityMemory(Ageable.class, "nearest_visible_adult");
   public static final EntityMemory<Integer> TICKS_PLAY_DEAD = new EntityMemory(Integer.class, "play_dead_ticks");
   public static final EntityMemory<Player> TEMPTING_PLAYER = new EntityMemory(Player.class, "tempting_player");
   public static final EntityMemory<Integer> TEMPTING_COOLDOWN = new EntityMemory(Integer.class, "temptation_cooldown_ticks");
   public static final EntityMemory<Boolean> IS_TEMPTED = new EntityMemory(Boolean.class, "is_tempted");
   public static final EntityMemory<Integer> LONG_JUMP_COOLDOWN = new EntityMemory(Integer.class, "long_jump_cooldown_ticks");
   public static final EntityMemory<Boolean> IS_LONG_JUMP = new EntityMemory(Boolean.class, "long_jump_mid_jump");
   public static final EntityMemory<Boolean> HAS_HUNTING_COOLDOWN = new EntityMemory(Boolean.class, "has_hunting_cooldown");
   public static final EntityMemory<Integer> RAM_COOLDOWN = new EntityMemory(Integer.class, "ram_cooldown_ticks");
   public static final EntityMemory<Boolean> IS_PANICKING = new EntityMemory(Boolean.class, "is_panicking");
   public static final EntityMemory<Entity> ANGRY_AT = new EntityMemory(Entity.class, "angry_at");
   public static final EntityMemory<Boolean> UNIVERSAL_ANGER = new EntityMemory(Boolean.class, "universal_anger");
   public static final EntityMemory<Boolean> IS_ADMIRING_ITEM = new EntityMemory(Boolean.class, "admiring_item");
   public static final EntityMemory<Integer> TIME_TO_REACH_ADMIRING_ITEM = new EntityMemory(Integer.class, "time_trying_to_reach_admire_item");
   public static final EntityMemory<Boolean> DISABLE_WALKING_TO_ADMIRING_ITEM = new EntityMemory(Boolean.class, "disable_walk_to_admire_item");
   public static final EntityMemory<Boolean> ADMIRING_DISABLED = new EntityMemory(Boolean.class, "admiring_disabled");
   public static final EntityMemory<Boolean> HAS_HUNTED_RECENTLY = new EntityMemory(Boolean.class, "hunted_recently");
   public static final EntityMemory<Location> CELEBRATION_LOCATION = new EntityMemory(Location.class, "celebration_location");
   public static final EntityMemory<Boolean> DANCING = new EntityMemory(Boolean.class, "dancing");
   public static final EntityMemory<Player> NEAREST_NONGOLD_PLAYER = new EntityMemory(Player.class, "nearest_visible_baby_hoglin");
   public static final EntityMemory<LivingEntity> NEAREST_ZOMBIFIED = new EntityMemory(LivingEntity.class, "nearest_visible_zombified");
   public static final EntityMemory<Integer> ADULT_PIGLIN_COUNT = new EntityMemory(Integer.class, "visible_adult_piglin_count");
   public static final EntityMemory<Integer> ADULT_HOGLIN_COUNT = new EntityMemory(Integer.class, "visible_adult_hoglin_count");
   public static final EntityMemory<Player> NEAREST_TEMPTED_PLAYER = new EntityMemory(Player.class, "nearest_player_holding_wanted_item");
   public static final EntityMemory<Location> NEAREST_REPELLENT = new EntityMemory(Location.class, "nearest_repellent");
   public static final EntityMemory<Boolean> ATE_RECENTLY = new EntityMemory(Boolean.class, "ate_recently");
   public static final EntityMemory<Boolean> PACIFIED = new EntityMemory(Boolean.class, "pacified");
   public static final EntityMemory<Item> NEAREST_WANTED_ITEM = new EntityMemory(Item.class, "nearest_visible_wanted_item");
   public static final EntityMemory<Player> LIKED_PLAYER = new EntityMemory(Player.class, "liked_player");
   public static final EntityMemory<LivingEntity> ROAR_TARGET = new EntityMemory(LivingEntity.class, "roar_target");
   public static final EntityMemory<Location> LIKED_NOTEBLOCK = new EntityMemory(Location.class, "liked_noteblock");
   public static final EntityMemory<Integer> TICKS_ITEM_PICKUP_COOLDOWN = new EntityMemory(Integer.class, "item_pickup_cooldown_ticks");
   public static final EntityMemory<Item> NEAREST_VISIBLE_WANTED_ITEM = new EntityMemory(Item.class, "nearest_visible_wanted_item");
   public static final EntityMemory<PositionPath> PATH = new EntityMemory(PositionPath.class, "path");
   public static final EntityMemory<Location> DISTURBANCE_LOCATION = new EntityMemory(Location.class, "disturbance_location");
   public static final EntityMemory<Unit> IS_SNIFFING = new EntityMemory(Unit.class, "is_sniffing");
   public static final EntityMemory<Unit> IS_EMERGING = new EntityMemory(Unit.class, "is_emerging");
   public static final EntityMemory<Unit> ROAR_SOUND_DELAY = new EntityMemory(Unit.class, "roar_sound_delay");
   public static final EntityMemory<Unit> DIG_COOLDOWN = new EntityMemory(Unit.class, "dig_cooldown");
   public static final EntityMemory<Unit> ROAR_SOUND_COOLDOWN = new EntityMemory(Unit.class, "roar_sound_cooldown");
   public static final EntityMemory<Unit> SNIFF_COOLDOWN = new EntityMemory(Unit.class, "sniff_cooldown");
   public static final EntityMemory<Unit> TOUCH_COOLDOWN = new EntityMemory(Unit.class, "touch_cooldown");
   public static final EntityMemory<Unit> VIBRATION_COOLDOWN = new EntityMemory(Unit.class, "vibration_cooldown");
   public static final EntityMemory<Unit> SONIC_BOOM_COOLDOWN = new EntityMemory(Unit.class, "sonic_boom_cooldown");
   public static final EntityMemory<Unit> SONIC_BOOM_SOUND_COOLDOWN = new EntityMemory(Unit.class, "sonic_boom_sound_cooldown");
   public static final EntityMemory<Unit> SONIC_BOOM_SOUND_DELAY = new EntityMemory(Unit.class, "sonic_boom_sound_delay");
   public static final EntityMemory<Integer> LIKED_NOTEBLOCK_COOLDOWN_TICKS = new EntityMemory(Integer.class, "liked_noteblock_cooldown_ticks");
   public static final EntityMemory<Integer> ITEM_PICKUP_COOLDOWN_TICKS = new EntityMemory(Integer.class, "item_pickup_cooldown_ticks");
   public static final EntityMemory<Location[]> SNIFFER_SNIFFING_TARGET = new EntityMemory(Location[].class, "sniffer_sniffing_target");
   public static final EntityMemory<Boolean> SNIFFER_DIGGING = new EntityMemory(Boolean.class, "sniffer_digging");
   public static final EntityMemory<Boolean> SNIFFER_HAPPY = new EntityMemory(Boolean.class, "sniffer_happy");
   public static final EntityMemory<Unit> BREEZE_JUMP_COOLDOWN = new EntityMemory(Unit.class, "breeze_jump_cooldown");
   public static final EntityMemory<Unit> BREEZE_SHOOT = new EntityMemory(Unit.class, "breeze_shoot");
   public static final EntityMemory<Unit> BREEZE_SHOOT_CHARGING = new EntityMemory(Unit.class, "breeze_shoot_charging");
   public static final EntityMemory<Unit> BREEZE_SHOOT_RECOVERING = new EntityMemory(Unit.class, "breeze_shoot_recovering");
   public static final EntityMemory<Unit> BREEZE_SHOOT_COOLDOWN = new EntityMemory(Unit.class, "breeze_shoot_cooldown");
   public static final EntityMemory<Unit> BREEZE_JUMP_INHALING = new EntityMemory(Unit.class, "breeze_jump_inhaling");
   public static final EntityMemory<Location> BREEZE_JUMP_TARGET = new EntityMemory(Location.class, "breeze_jump_target");
   private final Class<T> bukkit;
   private final String key;

   private EntityMemory(Class<T> bukkit, String key) {
      this.bukkit = bukkit;
      this.key = key;
   }

   @NotNull
   public Class<T> getBukkitClass() {
      return this.bukkit;
   }

   @NotNull
   public NamespacedKey getKey() {
      return NamespacedKey.minecraft(this.key);
   }

   @Nullable
   public static EntityMemory<?> getByKey(@NotNull NamespacedKey key) {
      EntityMemory[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         EntityMemory<?> mem = var1[var3];
         if (mem.getKey().equals(key)) {
            return mem;
         }
      }

      return null;
   }

   @NotNull
   public static EntityMemory<?>[] values() {
      return (EntityMemory[])Arrays.stream(EntityMemory.class.getDeclaredFields()).filter((f) -> {
         return EntityMemory.class.isAssignableFrom(f.getType());
      }).filter((f) -> {
         return Modifier.isStatic(f.getModifiers()) && Modifier.isFinal(f.getModifiers());
      }).map((f) -> {
         f.setAccessible(true);

         try {
            return (EntityMemory)f.get((Object)null);
         } catch (IllegalAccessException var2) {
            throw new RuntimeException(var2);
         }
      }).toArray((x$0) -> {
         return new EntityMemory[x$0];
      });
   }
}
