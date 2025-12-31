package me.darksnakex.villagerfollow.mobchip.abstraction.v1_17_R1;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Lifecycle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import me.darksnakex.villagerfollow.mobchip.EntityBody;
import me.darksnakex.villagerfollow.mobchip.abstraction.ChipUtil;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.Attribute;
import me.darksnakex.villagerfollow.mobchip.ai.attribute.AttributeInstance;
import me.darksnakex.villagerfollow.mobchip.ai.behavior.BehaviorResult;
import me.darksnakex.villagerfollow.mobchip.ai.controller.EntityController;
import me.darksnakex.villagerfollow.mobchip.ai.enderdragon.CustomPhase;
import me.darksnakex.villagerfollow.mobchip.ai.enderdragon.DragonPhase;
import me.darksnakex.villagerfollow.mobchip.ai.goal.CustomPathfinder;
import me.darksnakex.villagerfollow.mobchip.ai.goal.Pathfinder;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderAvoidEntity;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderBeg;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderBreakDoor;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderBreathAir;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderBreed;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderCatOnBed;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderCatOnBlock;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderDolphinJump;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderEatTile;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderFindWater;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderFleeSun;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderFloat;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderFollowBoat;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderFollowMob;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderFollowOwner;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderFollowParent;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderLeapAtTarget;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderLlamaFollowCaravan;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderLookAtEntity;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderLookAtTradingPlayer;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderMeleeAttack;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderMoveThroughVillage;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderMoveToBlock;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderMoveToRaid;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderMoveTowardsRestriction;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderMoveTowardsTarget;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderOcelotAttack;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderOfferFlower;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderOpenDoor;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderPanic;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRandomLook;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRandomStroll;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRandomStrollFlying;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRandomStrollInVillage;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRandomStrollLand;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRandomStrollThroughVillage;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRandomStrollToVillage;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRandomSwim;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRangedAttack;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRangedBowAttack;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRangedCrossbowAttack;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRemoveBlock;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderResetAnger;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRestrictSun;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderRideShoulder;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderSit;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderSkeletonTrap;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderSwellCreeper;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderTameHorse;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderTempt;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderTradePlayer;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderUseItem;
import me.darksnakex.villagerfollow.mobchip.ai.goal.PathfinderZombieAttack;
import me.darksnakex.villagerfollow.mobchip.ai.goal.WrappedPathfinder;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.PathfinderDefendVillage;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.PathfinderHurtByTarget;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.PathfinderNearestAttackableTarget;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.PathfinderNearestAttackableTargetRaider;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.PathfinderNearestHealableRaider;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.PathfinderOwnerHurtByTarget;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.PathfinderOwnerHurtTarget;
import me.darksnakex.villagerfollow.mobchip.ai.goal.target.PathfinderWildTarget;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.EntityGossipContainer;
import me.darksnakex.villagerfollow.mobchip.ai.gossip.GossipType;
import me.darksnakex.villagerfollow.mobchip.ai.memories.EntityMemory;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Memory;
import me.darksnakex.villagerfollow.mobchip.ai.memories.MemoryStatus;
import me.darksnakex.villagerfollow.mobchip.ai.memories.Unit;
import me.darksnakex.villagerfollow.mobchip.ai.navigation.EntityNavigation;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.EntityScheduleManager;
import me.darksnakex.villagerfollow.mobchip.ai.schedule.Schedule;
import me.darksnakex.villagerfollow.mobchip.ai.sensing.EntitySenses;
import me.darksnakex.villagerfollow.mobchip.combat.CombatEntry;
import me.darksnakex.villagerfollow.mobchip.combat.CombatLocation;
import me.darksnakex.villagerfollow.mobchip.combat.EntityCombatTracker;
import me.darksnakex.villagerfollow.mobchip.util.Position;
import net.minecraft.core.BlockPosition;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.IPosition;
import net.minecraft.core.IRegistry;
import net.minecraft.core.IRegistryWritable;
import net.minecraft.resources.MinecraftKey;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.EntityPlayer;
import net.minecraft.server.level.WorldServer;
import net.minecraft.sounds.SoundEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityCreature;
import net.minecraft.world.entity.EntityInsentient;
import net.minecraft.world.entity.EntityLiving;
import net.minecraft.world.entity.EntityTameableAnimal;
import net.minecraft.world.entity.IEntityAngerable;
import net.minecraft.world.entity.ai.attributes.AttributeBase;
import net.minecraft.world.entity.ai.attributes.AttributeMapBase;
import net.minecraft.world.entity.ai.attributes.AttributeModifiable;
import net.minecraft.world.entity.ai.attributes.AttributeRanged;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.Behavior.Status;
import net.minecraft.world.entity.ai.goal.PathfinderGoal;
import net.minecraft.world.entity.ai.goal.PathfinderGoalArrowAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalAvoidTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalBeg;
import net.minecraft.world.entity.ai.goal.PathfinderGoalBowShoot;
import net.minecraft.world.entity.ai.goal.PathfinderGoalBreakDoor;
import net.minecraft.world.entity.ai.goal.PathfinderGoalBreath;
import net.minecraft.world.entity.ai.goal.PathfinderGoalBreed;
import net.minecraft.world.entity.ai.goal.PathfinderGoalCatSitOnBed;
import net.minecraft.world.entity.ai.goal.PathfinderGoalCrossbowAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalDoorInteract;
import net.minecraft.world.entity.ai.goal.PathfinderGoalDoorOpen;
import net.minecraft.world.entity.ai.goal.PathfinderGoalEatTile;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFishSchool;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFleeSun;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFloat;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFollowBoat;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFollowEntity;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFollowOwner;
import net.minecraft.world.entity.ai.goal.PathfinderGoalFollowParent;
import net.minecraft.world.entity.ai.goal.PathfinderGoalJumpOnBlock;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLeapAtTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLlamaFollow;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalLookAtTradingPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMeleeAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMoveThroughVillage;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.world.entity.ai.goal.PathfinderGoalMoveTowardsTarget;
import net.minecraft.world.entity.ai.goal.PathfinderGoalNearestVillage;
import net.minecraft.world.entity.ai.goal.PathfinderGoalOcelotAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoalOfferFlower;
import net.minecraft.world.entity.ai.goal.PathfinderGoalPanic;
import net.minecraft.world.entity.ai.goal.PathfinderGoalPerch;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRaid;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomFly;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomLookaround;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStroll;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomStrollLand;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRandomSwim;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRemoveBlock;
import net.minecraft.world.entity.ai.goal.PathfinderGoalRestrictSun;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSelector;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSit;
import net.minecraft.world.entity.ai.goal.PathfinderGoalStrollVillage;
import net.minecraft.world.entity.ai.goal.PathfinderGoalStrollVillageGolem;
import net.minecraft.world.entity.ai.goal.PathfinderGoalSwell;
import net.minecraft.world.entity.ai.goal.PathfinderGoalTame;
import net.minecraft.world.entity.ai.goal.PathfinderGoalTempt;
import net.minecraft.world.entity.ai.goal.PathfinderGoalTradeWithPlayer;
import net.minecraft.world.entity.ai.goal.PathfinderGoalUseItem;
import net.minecraft.world.entity.ai.goal.PathfinderGoalWater;
import net.minecraft.world.entity.ai.goal.PathfinderGoalWaterJump;
import net.minecraft.world.entity.ai.goal.PathfinderGoalZombieAttack;
import net.minecraft.world.entity.ai.goal.PathfinderGoal.Type;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalDefendVillage;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestAttackableTargetWitch;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalNearestHealableRaider;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalOwnerHurtByTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalOwnerHurtTarget;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalRandomTargetNonTamed;
import net.minecraft.world.entity.ai.goal.target.PathfinderGoalUniversalAngerReset;
import net.minecraft.world.entity.ai.gossip.ReputationType;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryTarget;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.ai.targeting.PathfinderTargetCondition;
import net.minecraft.world.entity.ambient.EntityAmbient;
import net.minecraft.world.entity.ambient.EntityBat;
import net.minecraft.world.entity.animal.EntityAnimal;
import net.minecraft.world.entity.animal.EntityBee;
import net.minecraft.world.entity.animal.EntityCat;
import net.minecraft.world.entity.animal.EntityChicken;
import net.minecraft.world.entity.animal.EntityCod;
import net.minecraft.world.entity.animal.EntityCow;
import net.minecraft.world.entity.animal.EntityDolphin;
import net.minecraft.world.entity.animal.EntityFish;
import net.minecraft.world.entity.animal.EntityFishSchool;
import net.minecraft.world.entity.animal.EntityFox;
import net.minecraft.world.entity.animal.EntityGolem;
import net.minecraft.world.entity.animal.EntityIronGolem;
import net.minecraft.world.entity.animal.EntityMushroomCow;
import net.minecraft.world.entity.animal.EntityOcelot;
import net.minecraft.world.entity.animal.EntityPanda;
import net.minecraft.world.entity.animal.EntityParrot;
import net.minecraft.world.entity.animal.EntityPerchable;
import net.minecraft.world.entity.animal.EntityPig;
import net.minecraft.world.entity.animal.EntityPolarBear;
import net.minecraft.world.entity.animal.EntityPufferFish;
import net.minecraft.world.entity.animal.EntityRabbit;
import net.minecraft.world.entity.animal.EntitySalmon;
import net.minecraft.world.entity.animal.EntitySheep;
import net.minecraft.world.entity.animal.EntitySnowman;
import net.minecraft.world.entity.animal.EntitySquid;
import net.minecraft.world.entity.animal.EntityTropicalFish;
import net.minecraft.world.entity.animal.EntityTurtle;
import net.minecraft.world.entity.animal.EntityWaterAnimal;
import net.minecraft.world.entity.animal.EntityWolf;
import net.minecraft.world.entity.animal.axolotl.AxolotlAi;
import net.minecraft.world.entity.animal.horse.EntityHorse;
import net.minecraft.world.entity.animal.horse.EntityHorseAbstract;
import net.minecraft.world.entity.animal.horse.EntityHorseDonkey;
import net.minecraft.world.entity.animal.horse.EntityHorseMule;
import net.minecraft.world.entity.animal.horse.EntityHorseSkeleton;
import net.minecraft.world.entity.animal.horse.EntityHorseZombie;
import net.minecraft.world.entity.animal.horse.EntityLlama;
import net.minecraft.world.entity.animal.horse.EntityLlamaTrader;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderCrystal;
import net.minecraft.world.entity.boss.enderdragon.EntityEnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.AbstractDragonController;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerCharge;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerDying;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerFly;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerHold;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerHover;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerLandedAttack;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerLandedFlame;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerLandedSearch;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerLanding;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerLandingFly;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerManager;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerPhase;
import net.minecraft.world.entity.boss.enderdragon.phases.DragonControllerStrafe;
import net.minecraft.world.entity.boss.enderdragon.phases.IDragonController;
import net.minecraft.world.entity.boss.wither.EntityWither;
import net.minecraft.world.entity.item.EntityItem;
import net.minecraft.world.entity.monster.EntityBlaze;
import net.minecraft.world.entity.monster.EntityCaveSpider;
import net.minecraft.world.entity.monster.EntityCreeper;
import net.minecraft.world.entity.monster.EntityDrowned;
import net.minecraft.world.entity.monster.EntityEnderman;
import net.minecraft.world.entity.monster.EntityEndermite;
import net.minecraft.world.entity.monster.EntityEvoker;
import net.minecraft.world.entity.monster.EntityGhast;
import net.minecraft.world.entity.monster.EntityGiantZombie;
import net.minecraft.world.entity.monster.EntityGuardian;
import net.minecraft.world.entity.monster.EntityGuardianElder;
import net.minecraft.world.entity.monster.EntityIllagerAbstract;
import net.minecraft.world.entity.monster.EntityIllagerIllusioner;
import net.minecraft.world.entity.monster.EntityMagmaCube;
import net.minecraft.world.entity.monster.EntityMonster;
import net.minecraft.world.entity.monster.EntityPhantom;
import net.minecraft.world.entity.monster.EntityPigZombie;
import net.minecraft.world.entity.monster.EntityPillager;
import net.minecraft.world.entity.monster.EntityRavager;
import net.minecraft.world.entity.monster.EntityShulker;
import net.minecraft.world.entity.monster.EntitySilverfish;
import net.minecraft.world.entity.monster.EntitySkeleton;
import net.minecraft.world.entity.monster.EntitySkeletonStray;
import net.minecraft.world.entity.monster.EntitySkeletonWither;
import net.minecraft.world.entity.monster.EntitySlime;
import net.minecraft.world.entity.monster.EntitySpider;
import net.minecraft.world.entity.monster.EntityStrider;
import net.minecraft.world.entity.monster.EntityVex;
import net.minecraft.world.entity.monster.EntityVindicator;
import net.minecraft.world.entity.monster.EntityWitch;
import net.minecraft.world.entity.monster.EntityZoglin;
import net.minecraft.world.entity.monster.EntityZombie;
import net.minecraft.world.entity.monster.EntityZombieHusk;
import net.minecraft.world.entity.monster.EntityZombieVillager;
import net.minecraft.world.entity.monster.IRangedEntity;
import net.minecraft.world.entity.monster.hoglin.EntityHoglin;
import net.minecraft.world.entity.monster.piglin.EntityPiglin;
import net.minecraft.world.entity.monster.piglin.EntityPiglinBrute;
import net.minecraft.world.entity.npc.EntityVillager;
import net.minecraft.world.entity.npc.EntityVillagerAbstract;
import net.minecraft.world.entity.npc.EntityVillagerTrader;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.player.EntityHuman;
import net.minecraft.world.entity.raid.EntityRaider;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.entity.schedule.ScheduleBuilder;
import net.minecraft.world.item.crafting.RecipeItemStack;
import net.minecraft.world.level.IBlockAccess;
import net.minecraft.world.level.pathfinder.PathPoint;
import net.minecraft.world.phys.Vec3D;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_17_R1.CraftServer;
import org.bukkit.craftbukkit.v1_17_R1.CraftSound;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftCreature;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEnderDragon;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftItem;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftLivingEntity;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftMob;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftMagicNumbers;
import org.bukkit.craftbukkit.v1_17_R1.util.CraftNamespacedKey;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.entity.AbstractSkeleton;
import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Cat;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cod;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Dolphin;
import org.bukkit.entity.Donkey;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Fish;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.Goat;
import org.bukkit.entity.Golem;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.Hoglin;
import org.bukkit.entity.Horse;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Husk;
import org.bukkit.entity.Illager;
import org.bukkit.entity.Illusioner;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.Item;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Mule;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Ocelot;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.PufferFish;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Raider;
import org.bukkit.entity.Ravager;
import org.bukkit.entity.Salmon;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Shulker;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.SizedFireball;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.SkeletonHorse;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Snowman;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Strider;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.TraderLlama;
import org.bukkit.entity.Trident;
import org.bukkit.entity.TropicalFish;
import org.bukkit.entity.Turtle;
import org.bukkit.entity.Vex;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.entity.WaterMob;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zoglin;
import org.bukkit.entity.Zombie;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.entity.EnderDragon.Phase;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.entity.minecart.CommandMinecart;
import org.bukkit.entity.minecart.ExplosiveMinecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.entity.minecart.PoweredMinecart;
import org.bukkit.entity.minecart.SpawnerMinecart;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

final class ChipUtil1_17_R1 implements ChipUtil {
   public static final Map<Class<? extends LivingEntity>, Class<? extends EntityLiving>> BUKKIT_NMS_MAP = ImmutableMap.builder().put(LivingEntity.class, EntityLiving.class).put(Mob.class, EntityInsentient.class).put(Tameable.class, EntityTameableAnimal.class).put(AbstractHorse.class, EntityHorseAbstract.class).put(AbstractVillager.class, EntityVillagerAbstract.class).put(Animals.class, EntityAnimal.class).put(Ambient.class, EntityAmbient.class).put(Axolotl.class, net.minecraft.world.entity.animal.axolotl.Axolotl.class).put(Bat.class, EntityBat.class).put(Bee.class, EntityBee.class).put(Blaze.class, EntityBlaze.class).put(Cat.class, EntityCat.class).put(CaveSpider.class, EntityCaveSpider.class).put(Chicken.class, EntityChicken.class).put(Cod.class, EntityCod.class).put(Cow.class, EntityCow.class).put(Creeper.class, EntityCreeper.class).put(Dolphin.class, EntityDolphin.class).put(Donkey.class, EntityHorseDonkey.class).put(Drowned.class, EntityDrowned.class).put(ElderGuardian.class, EntityGuardianElder.class).put(EnderDragon.class, EntityEnderDragon.class).put(Enderman.class, EntityEnderman.class).put(Endermite.class, EntityEndermite.class).put(Evoker.class, EntityEvoker.class).put(Fish.class, EntityFish.class).put(Fox.class, EntityFox.class).put(Ghast.class, EntityGhast.class).put(Giant.class, EntityGiantZombie.class).put(Goat.class, net.minecraft.world.entity.animal.goat.Goat.class).put(Golem.class, EntityGolem.class).put(Guardian.class, EntityGuardian.class).put(Hoglin.class, EntityHoglin.class).put(Horse.class, EntityHorse.class).put(HumanEntity.class, EntityHuman.class).put(Husk.class, EntityZombieHusk.class).put(Illager.class, EntityIllagerAbstract.class).put(Illusioner.class, EntityIllagerIllusioner.class).put(IronGolem.class, EntityIronGolem.class).put(Llama.class, EntityLlama.class).put(MagmaCube.class, EntityMagmaCube.class).put(Mule.class, EntityHorseMule.class).put(MushroomCow.class, EntityMushroomCow.class).put(Ocelot.class, EntityOcelot.class).put(Panda.class, EntityPanda.class).put(Parrot.class, EntityParrot.class).put(Phantom.class, EntityPhantom.class).put(Pig.class, EntityPig.class).put(PigZombie.class, EntityPigZombie.class).put(Piglin.class, EntityPiglin.class).put(PiglinBrute.class, EntityPiglinBrute.class).put(Pillager.class, EntityPillager.class).put(Player.class, EntityHuman.class).put(PolarBear.class, EntityPolarBear.class).put(PufferFish.class, EntityPufferFish.class).put(Rabbit.class, EntityRabbit.class).put(Raider.class, EntityRaider.class).put(Ravager.class, EntityRavager.class).put(Salmon.class, EntitySalmon.class).put(Sheep.class, EntitySheep.class).put(Shulker.class, EntityShulker.class).put(Silverfish.class, EntitySilverfish.class).put(Skeleton.class, EntitySkeleton.class).put(SkeletonHorse.class, EntityHorseSkeleton.class).put(Slime.class, EntitySlime.class).put(Snowman.class, EntitySnowman.class).put(Spider.class, EntitySpider.class).put(Squid.class, EntitySquid.class).put(Stray.class, EntitySkeletonStray.class).put(Strider.class, EntityStrider.class).put(TraderLlama.class, EntityLlamaTrader.class).put(TropicalFish.class, EntityTropicalFish.class).put(Turtle.class, EntityTurtle.class).put(Vex.class, EntityVex.class).put(Villager.class, EntityVillager.class).put(Vindicator.class, EntityVindicator.class).put(WanderingTrader.class, EntityVillagerTrader.class).put(WaterMob.class, EntityWaterAnimal.class).put(Witch.class, EntityWitch.class).put(Wither.class, EntityWither.class).put(WitherSkeleton.class, EntitySkeletonWither.class).put(Wolf.class, EntityWolf.class).put(Zoglin.class, EntityZoglin.class).put(Zombie.class, EntityZombie.class).put(ZombieHorse.class, EntityHorseZombie.class).put(ZombieVillager.class, EntityZombieVillager.class).build();

   public static ItemStack fromNMS(net.minecraft.world.item.ItemStack item) {
      return CraftItemStack.asBukkitCopy(item);
   }

   public void addCustomPathfinder(CustomPathfinder p, int priority, boolean target) {
      Mob m = p.getEntity();
      EntityInsentient mob = toNMS(m);
      PathfinderGoalSelector s = target ? mob.bQ : mob.bP;
      PathfinderGoal g = custom(p);
      Set<Type> nms = getFlags(g);
      Pathfinder.PathfinderFlag[] flags = p.getFlags() == null ? new Pathfinder.PathfinderFlag[0] : p.getFlags();
      Pathfinder.PathfinderFlag[] var10 = flags;
      int var11 = flags.length;

      for(int var12 = 0; var12 < var11; ++var12) {
         Pathfinder.PathfinderFlag f = var10[var12];
         EnumSet<Type> nmsFlags = nms == null ? EnumSet.allOf(Type.class) : EnumSet.copyOf(nms);
         nmsFlags.add(toNMS(f));
         g.a(nmsFlags);
      }

      s.a(priority, g);
   }

   public Set<WrappedPathfinder> getGoals(Mob m, boolean target) {
      EntityInsentient mob = toNMS(m);
      PathfinderGoalSelector s = target ? mob.bQ : mob.bP;
      Set<WrappedPathfinder> pF = new HashSet();
      s.c().forEach((w) -> {
         pF.add(new WrappedPathfinder(this.fromNMS(w.j()), w.h()));
      });
      return pF;
   }

   public Collection<WrappedPathfinder> getRunningGoals(Mob m, boolean target) {
      EntityInsentient mob = toNMS(m);
      PathfinderGoalSelector s = target ? mob.bQ : mob.bP;
      Collection<WrappedPathfinder> l = new HashSet();
      s.d().forEach((w) -> {
         l.add(new WrappedPathfinder(this.fromNMS(w.j()), w.h()));
      });
      return l;
   }

   public void setFlag(Mob m, Pathfinder.PathfinderFlag flag, boolean target, boolean value) {
      EntityInsentient mob = toNMS(m);
      PathfinderGoalSelector s = target ? mob.bQ : mob.bP;
      if (value) {
         s.b(toNMS(flag));
      } else {
         s.a(toNMS(flag));
      }

   }

   public static Class<? extends EntityLiving> toNMS(Class<? extends LivingEntity> clazz) {
      if (BUKKIT_NMS_MAP.containsKey(clazz)) {
         return (Class)BUKKIT_NMS_MAP.get(clazz);
      } else {
         Class nms = null;

         String var10000;
         try {
            var10000 = EntityCreature.class.getPackageName();
            nms = Class.forName(var10000 + "." + clazz.getSimpleName()).asSubclass(EntityLiving.class);
         } catch (ClassNotFoundException var5) {
            try {
               if (nms == null) {
                  var10000 = EntityCreature.class.getPackageName();
                  nms = Class.forName(var10000 + ".Entity" + clazz.getSimpleName()).asSubclass(EntityLiving.class);
               }
            } catch (ClassNotFoundException var4) {
            }
         }

         if (nms == null) {
            throw new AssertionError("Could not convert " + clazz.getName() + " to NMS class");
         } else {
            return nms;
         }
      }
   }

   public static net.minecraft.world.item.ItemStack toNMS(ItemStack i) {
      return CraftItemStack.asNMSCopy(i);
   }

   public static SoundEffect toNMS(Sound s) {
      return CraftSound.getSoundEffect(s);
   }

   public static PathfinderGoal toNMS(Pathfinder b) {
      Mob mob = b.getEntity();
      EntityInsentient m = toNMS(mob);
      String name = b.getInternalName().startsWith("PathfinderGoal") ? b.getInternalName().replace("PathfinderGoal", "") : b.getInternalName();
      byte var5 = -1;
      switch(name.hashCode()) {
      case -2082980744:
         if (name.equals("MoveThroughVillage")) {
            var5 = 24;
         }
         break;
      case -2043394768:
         if (name.equals("LookAtTradingPlayer")) {
            var5 = 22;
         }
         break;
      case -2018211691:
         if (name.equals("BowShoot")) {
            var5 = 3;
         }
         break;
      case -1949985978:
         if (name.equals("AvoidTarget")) {
            var5 = 0;
         }
         break;
      case -1946824169:
         if (name.equals("HurtByTarget")) {
            var5 = 53;
         }
         break;
      case -1811199316:
         if (name.equals("RandomStrollLand")) {
            var5 = 36;
         }
         break;
      case -1553014250:
         if (name.equals("NearestAttackableTargetWitch")) {
            var5 = 55;
         }
         break;
      case -1552166701:
         if (name.equals("LookAtPlayer")) {
            var5 = 21;
         }
         break;
      case -1344974194:
         if (name.equals("NearestVillage")) {
            var5 = 27;
         }
         break;
      case -1268053103:
         if (name.equals("ArrowAttack")) {
            var5 = 1;
         }
         break;
      case -1045162654:
         if (name.equals("FollowOwner")) {
            var5 = 16;
         }
         break;
      case -747379341:
         if (name.equals("OwnerHurtTarget")) {
            var5 = 58;
         }
         break;
      case -536655626:
         if (name.equals("DefendVillage")) {
            var5 = 52;
         }
         break;
      case -510391834:
         if (name.equals("RandomTargetNonTamed")) {
            var5 = 59;
         }
         break;
      case -509658694:
         if (name.equals("CatSitOnBed")) {
            var5 = 7;
         }
         break;
      case -485095825:
         if (name.equals("RandomLookaround")) {
            var5 = 34;
         }
         break;
      case -455269869:
         if (name.equals("UniversalAngerReset")) {
            var5 = 51;
         }
         break;
      case -390283999:
         if (name.equals("RandomStroll")) {
            var5 = 35;
         }
         break;
      case -382685106:
         if (name.equals("StrollVillage")) {
            var5 = 41;
         }
         break;
      case -354337764:
         if (name.equals("MoveTowardsTarget")) {
            var5 = 26;
         }
         break;
      case -299992570:
         if (name.equals("EatTile")) {
            var5 = 10;
         }
         break;
      case -120983506:
         if (name.equals("ZombieAttack")) {
            var5 = 50;
         }
         break;
      case -34110287:
         if (name.equals("FollowBoat")) {
            var5 = 14;
         }
         break;
      case 66660:
         if (name.equals("Beg")) {
            var5 = 2;
         }
         break;
      case 83134:
         if (name.equals("Sit")) {
            var5 = 40;
         }
         break;
      case 2539434:
         if (name.equals("Raid")) {
            var5 = 32;
         }
         break;
      case 2599141:
         if (name.equals("Tame")) {
            var5 = 44;
         }
         break;
      case 64448852:
         if (name.equals("Breed")) {
            var5 = 6;
         }
         break;
      case 67973692:
         if (name.equals("Float")) {
            var5 = 13;
         }
         break;
      case 76880471:
         if (name.equals("Panic")) {
            var5 = 30;
         }
         break;
      case 77003298:
         if (name.equals("Perch")) {
            var5 = 31;
         }
         break;
      case 80297889:
         if (name.equals("Swell")) {
            var5 = 43;
         }
         break;
      case 80692992:
         if (name.equals("Tempt")) {
            var5 = 45;
         }
         break;
      case 83350775:
         if (name.equals("Water")) {
            var5 = 48;
         }
         break;
      case 105543981:
         if (name.equals("BreakDoor")) {
            var5 = 4;
         }
         break;
      case 106785232:
         if (name.equals("RestrictSun")) {
            var5 = 39;
         }
         break;
      case 114436284:
         if (name.equals("MeleeAttack")) {
            var5 = 23;
         }
         break;
      case 474057777:
         if (name.equals("NearestAttackableTarget")) {
            var5 = 54;
         }
         break;
      case 579064978:
         if (name.equals("CrossbowAttack")) {
            var5 = 8;
         }
         break;
      case 591537067:
         if (name.equals("TradeWithPlayer")) {
            var5 = 46;
         }
         break;
      case 854846590:
         if (name.equals("StrollVillageGolem")) {
            var5 = 42;
         }
         break;
      case 883789862:
         if (name.equals("LlamaFollow")) {
            var5 = 20;
         }
         break;
      case 889064550:
         if (name.equals("FleeSun")) {
            var5 = 12;
         }
         break;
      case 978358314:
         if (name.equals("OwnerHurtByTarget")) {
            var5 = 57;
         }
         break;
      case 1176401040:
         if (name.equals("RandomFly")) {
            var5 = 33;
         }
         break;
      case 1266260792:
         if (name.equals("DoorOpen")) {
            var5 = 9;
         }
         break;
      case 1347302985:
         if (name.equals("RemoveBlock")) {
            var5 = 38;
         }
         break;
      case 1441363392:
         if (name.equals("JumpOnBlock")) {
            var5 = 18;
         }
         break;
      case 1461450764:
         if (name.equals("FishSchool")) {
            var5 = 11;
         }
         break;
      case 1516319002:
         if (name.equals("UseItem")) {
            var5 = 47;
         }
         break;
      case 1519699752:
         if (name.equals("OcelotAttack")) {
            var5 = 28;
         }
         break;
      case 1646176076:
         if (name.equals("LeapAtTarget")) {
            var5 = 19;
         }
         break;
      case 1662055351:
         if (name.equals("OfferFlower")) {
            var5 = 29;
         }
         break;
      case 1665275668:
         if (name.equals("FollowEntity")) {
            var5 = 15;
         }
         break;
      case 1789520325:
         if (name.equals("WaterJump")) {
            var5 = 49;
         }
         break;
      case 1894852641:
         if (name.equals("MoveTowardsRestriction")) {
            var5 = 25;
         }
         break;
      case 1968126939:
         if (name.equals("FollowParent")) {
            var5 = 17;
         }
         break;
      case 1997911168:
         if (name.equals("Breath")) {
            var5 = 5;
         }
         break;
      case 2109091339:
         if (name.equals("RandomSwim")) {
            var5 = 37;
         }
         break;
      case 2132742007:
         if (name.equals("NearestHealableRaider")) {
            var5 = 56;
         }
      }

      Object var10000;
      switch(var5) {
      case 0:
         PathfinderAvoidEntity p = (PathfinderAvoidEntity)b;
         Predicate<LivingEntity> avoidP = p.getAvoidPredicate() == null ? (en) -> {
            return true;
         } : (en) -> {
            return p.getAvoidPredicate().test(en);
         };
         Predicate<LivingEntity> avoidingP = p.getAvoidingPredicate() == null ? (en) -> {
            return true;
         } : (en) -> {
            return p.getAvoidingPredicate().test(en);
         };
         var10000 = new PathfinderGoalAvoidTarget((EntityCreature)m, toNMS(p.getFilter()), (en) -> {
            return avoidP.test(fromNMS(en));
         }, p.getMaxDistance(), p.getSpeedModifier(), p.getSprintModifier(), (en) -> {
            return avoidingP.test(fromNMS(en));
         });
         break;
      case 1:
         PathfinderRangedAttack p = (PathfinderRangedAttack)b;
         var10000 = new PathfinderGoalArrowAttack((IRangedEntity)m, p.getSpeedModifier(), p.getMinAttackInterval(), p.getMaxAttackInterval(), p.getRange());
         break;
      case 2:
         PathfinderBeg p = (PathfinderBeg)b;
         var10000 = new PathfinderGoalBeg((EntityWolf)m, p.getRange());
         break;
      case 3:
         PathfinderRangedBowAttack p = (PathfinderRangedBowAttack)b;
         var10000 = new PathfinderGoalBowShoot((EntityMonster)m, p.getSpeedModifier(), p.getInterval(), p.getRange());
         break;
      case 4:
         PathfinderBreakDoor p = (PathfinderBreakDoor)b;
         var10000 = new PathfinderGoalBreakDoor(m, p.getBreakTime(), (d) -> {
            return p.getCondition().test(fromNMS(d));
         });
         break;
      case 5:
         var10000 = new PathfinderGoalBreath((EntityCreature)m);
         break;
      case 6:
         PathfinderBreed p = (PathfinderBreed)b;
         var10000 = new PathfinderGoalBreed((EntityAnimal)m, p.getSpeedModifier());
         break;
      case 7:
         PathfinderCatOnBed p = (PathfinderCatOnBed)b;
         var10000 = new PathfinderGoalCatSitOnBed((EntityCat)m, p.getSpeedModifier(), Math.min((int)p.getRange(), 1));
         break;
      case 8:
         PathfinderRangedCrossbowAttack p = (PathfinderRangedCrossbowAttack)b;
         var10000 = new PathfinderGoalCrossbowAttack((EntityMonster)m, p.getSpeedModifier(), p.getRange());
         break;
      case 9:
         PathfinderOpenDoor p = (PathfinderOpenDoor)b;
         var10000 = new PathfinderGoalDoorOpen(m, p.mustClose());
         break;
      case 10:
         var10000 = new PathfinderGoalEatTile(m);
         break;
      case 11:
         var10000 = new PathfinderGoalFishSchool((EntityFishSchool)m);
         break;
      case 12:
         PathfinderFleeSun p = (PathfinderFleeSun)b;
         var10000 = new PathfinderGoalFleeSun((EntityCreature)m, p.getSpeedModifier());
         break;
      case 13:
         var10000 = new PathfinderGoalFloat(m);
         break;
      case 14:
         var10000 = new PathfinderGoalFollowBoat((EntityCreature)m);
         break;
      case 15:
         PathfinderFollowMob p = (PathfinderFollowMob)b;
         var10000 = new PathfinderGoalFollowEntity(m, p.getSpeedModifier(), p.getStopDistance(), p.getRange());
         break;
      case 16:
         PathfinderFollowOwner p = (PathfinderFollowOwner)b;
         var10000 = new PathfinderGoalFollowOwner((EntityTameableAnimal)m, p.getSpeedModifier(), p.getStartDistance(), p.getStopDistance(), p.canFly());
         break;
      case 17:
         PathfinderFollowParent p = (PathfinderFollowParent)b;
         var10000 = new PathfinderGoalFollowParent((EntityAnimal)m, p.getSpeedModifier());
         break;
      case 18:
         PathfinderCatOnBlock p = (PathfinderCatOnBlock)b;
         var10000 = new PathfinderGoalJumpOnBlock((EntityCat)m, p.getSpeedModifier());
         break;
      case 19:
         PathfinderLeapAtTarget p = (PathfinderLeapAtTarget)b;
         var10000 = new PathfinderGoalLeapAtTarget(m, p.getHeight());
         break;
      case 20:
         PathfinderLlamaFollowCaravan p = (PathfinderLlamaFollowCaravan)b;
         var10000 = new PathfinderGoalLlamaFollow((EntityLlama)m, p.getSpeedModifier());
         break;
      case 21:
         PathfinderLookAtEntity<?> p = (PathfinderLookAtEntity)b;
         var10000 = new PathfinderGoalLookAtPlayer(m, toNMS(p.getFilter()), p.getRange(), p.getProbability(), p.isHorizontal());
         break;
      case 22:
         PathfinderLookAtTradingPlayer p = (PathfinderLookAtTradingPlayer)b;
         var10000 = new PathfinderGoalLookAtTradingPlayer((EntityVillagerAbstract)m);
         break;
      case 23:
         PathfinderMeleeAttack p = (PathfinderMeleeAttack)b;
         var10000 = new PathfinderGoalMeleeAttack((EntityCreature)m, p.getSpeedModifier(), p.mustSee());
         break;
      case 24:
         PathfinderMoveThroughVillage p = (PathfinderMoveThroughVillage)b;
         var10000 = new PathfinderGoalMoveThroughVillage((EntityCreature)m, p.getSpeedModifier(), p.mustBeNight(), p.getMinDistance(), p.canUseDoors());
         break;
      case 25:
         PathfinderMoveTowardsRestriction p = (PathfinderMoveTowardsRestriction)b;
         var10000 = new PathfinderGoalMoveTowardsRestriction((EntityCreature)m, p.getSpeedModifier());
         break;
      case 26:
         PathfinderMoveTowardsTarget p = (PathfinderMoveTowardsTarget)b;
         var10000 = new PathfinderGoalMoveTowardsTarget((EntityCreature)m, p.getSpeedModifier(), p.getRange());
         break;
      case 27:
         PathfinderRandomStrollThroughVillage p = (PathfinderRandomStrollThroughVillage)b;
         var10000 = new PathfinderGoalNearestVillage((EntityCreature)m, p.getInterval());
         break;
      case 28:
         var10000 = new PathfinderGoalOcelotAttack(m);
         break;
      case 29:
         var10000 = new PathfinderGoalOfferFlower((EntityIronGolem)m);
         break;
      case 30:
         PathfinderPanic p = (PathfinderPanic)b;
         var10000 = new PathfinderGoalPanic((EntityCreature)m, p.getSpeedModifier());
         break;
      case 31:
         var10000 = new PathfinderGoalPerch((EntityPerchable)m);
         break;
      case 32:
         var10000 = new PathfinderGoalRaid((EntityRaider)m);
         break;
      case 33:
         PathfinderRandomStrollFlying p = (PathfinderRandomStrollFlying)b;
         var10000 = new PathfinderGoalRandomFly((EntityCreature)m, p.getSpeedModifier());
         break;
      case 34:
         var10000 = new PathfinderGoalRandomLookaround(m);
         break;
      case 35:
         PathfinderRandomStroll p = (PathfinderRandomStroll)b;
         var10000 = new PathfinderGoalRandomStroll((EntityCreature)m, p.getSpeedModifier(), p.getInterval());
         break;
      case 36:
         PathfinderRandomStrollLand p = (PathfinderRandomStrollLand)b;
         var10000 = new PathfinderGoalRandomStrollLand((EntityCreature)m, p.getSpeedModifier(), p.getProbability());
         break;
      case 37:
         PathfinderRandomSwim p = (PathfinderRandomSwim)b;
         var10000 = new PathfinderGoalRandomSwim((EntityCreature)m, p.getSpeedModifier(), p.getInterval());
         break;
      case 38:
         PathfinderRemoveBlock p = (PathfinderRemoveBlock)b;
         var10000 = new PathfinderGoalRemoveBlock(CraftMagicNumbers.getBlock(p.getBlock()), (EntityCreature)m, p.getSpeedModifier(), p.getVerticalSearchRange());
         break;
      case 39:
         var10000 = new PathfinderGoalRestrictSun((EntityCreature)m);
         break;
      case 40:
         var10000 = new PathfinderGoalSit((EntityTameableAnimal)m);
         break;
      case 41:
         PathfinderRandomStrollToVillage p = (PathfinderRandomStrollToVillage)b;
         var10000 = new PathfinderGoalStrollVillage((EntityCreature)m, p.getSpeedModifier(), true);
         break;
      case 42:
         PathfinderRandomStrollInVillage p = (PathfinderRandomStrollInVillage)b;
         var10000 = new PathfinderGoalStrollVillageGolem((EntityCreature)m, p.getSpeedModifier());
         break;
      case 43:
         var10000 = new PathfinderGoalSwell((EntityCreeper)m);
         break;
      case 44:
         PathfinderTameHorse p = (PathfinderTameHorse)b;
         var10000 = new PathfinderGoalTame((EntityHorseAbstract)m, p.getSpeedModifier());
         break;
      case 45:
         PathfinderTempt p = (PathfinderTempt)b;
         var10000 = new PathfinderGoalTempt((EntityCreature)m, p.getSpeedModifier(), RecipeItemStack.a(p.getItems().stream().map(CraftItemStack::asNMSCopy)), true);
         break;
      case 46:
         var10000 = new PathfinderGoalTradeWithPlayer((EntityVillagerAbstract)m);
         break;
      case 47:
         PathfinderUseItem p = (PathfinderUseItem)b;
         var10000 = new PathfinderGoalUseItem(m, toNMS(p.getItem()), toNMS(p.getFinishSound()), (e) -> {
            return p.getCondition().test(fromNMS(e));
         });
         break;
      case 48:
         var10000 = new PathfinderGoalWater((EntityCreature)m);
         break;
      case 49:
         PathfinderDolphinJump p = (PathfinderDolphinJump)b;
         var10000 = new PathfinderGoalWaterJump((EntityDolphin)m, p.getInterval());
         break;
      case 50:
         PathfinderZombieAttack p = (PathfinderZombieAttack)b;
         var10000 = new PathfinderGoalZombieAttack((EntityZombie)m, p.getSpeedModifier(), p.mustSee());
         break;
      case 51:
         PathfinderResetAnger p = (PathfinderResetAnger)b;
         var10000 = new PathfinderGoalUniversalAngerReset((EntityInsentient)((IEntityAngerable)m), p.isAlertingOthers());
         break;
      case 52:
         var10000 = new PathfinderGoalDefendVillage((EntityIronGolem)m);
         break;
      case 53:
         PathfinderHurtByTarget p = (PathfinderHurtByTarget)b;
         List<Class<? extends EntityLiving>> classes = new ArrayList();
         p.getIgnoring().stream().map(EntityType::getEntityClass).forEach((c) -> {
            classes.add(toNMS(c.asSubclass(LivingEntity.class)));
         });
         var10000 = new PathfinderGoalHurtByTarget((EntityCreature)m, (Class[])classes.toArray(new Class[0]));
         break;
      case 54:
         PathfinderNearestAttackableTarget p = (PathfinderNearestAttackableTarget)b;
         var10000 = new PathfinderGoalNearestAttackableTarget(m, toNMS(p.getFilter()), p.getInterval(), p.mustSee(), p.mustReach(), (t) -> {
            return p.getCondition().test(fromNMS(t));
         });
         break;
      case 55:
         PathfinderNearestAttackableTargetRaider p = (PathfinderNearestAttackableTargetRaider)b;
         var10000 = new PathfinderGoalNearestAttackableTargetWitch((EntityRaider)m, toNMS(p.getFilter()), p.getInterval(), p.mustSee(), p.mustReach(), (l) -> {
            return p.getCondition().test(fromNMS(l));
         });
         break;
      case 56:
         PathfinderNearestHealableRaider p = (PathfinderNearestHealableRaider)b;
         var10000 = new PathfinderGoalNearestHealableRaider((EntityRaider)m, toNMS(p.getFilter()), p.mustSee(), (l) -> {
            return p.getCondition().test(fromNMS(l));
         });
         break;
      case 57:
         var10000 = new PathfinderGoalOwnerHurtByTarget((EntityTameableAnimal)m);
         break;
      case 58:
         var10000 = new PathfinderGoalOwnerHurtTarget((EntityTameableAnimal)m);
         break;
      case 59:
         PathfinderWildTarget p = (PathfinderWildTarget)b;
         var10000 = new PathfinderGoalRandomTargetNonTamed((EntityTameableAnimal)m, toNMS(p.getFilter()), p.mustSee(), (l) -> {
            return p.getCondition().test(fromNMS(l));
         });
         break;
      default:
         if (b instanceof CustomPathfinder) {
            CustomPathfinder p = (CustomPathfinder)b;
            var10000 = custom(p);
         } else {
            var10000 = null;
         }
      }

      return (PathfinderGoal)var10000;
   }

   public void addPathfinder(Pathfinder b, int priority, boolean target) {
      Mob mob = b.getEntity();
      EntityInsentient m = toNMS(mob);
      PathfinderGoalSelector s = target ? m.bQ : m.bP;
      PathfinderGoal g = toNMS(b);
      if (g != null) {
         s.a(priority, g);
      }
   }

   public void removePathfinder(Pathfinder b, boolean target) {
      Mob mob = b.getEntity();
      EntityInsentient m = toNMS(mob);
      PathfinderGoalSelector s = target ? m.bQ : m.bP;
      PathfinderGoal g = toNMS(b);
      if (g != null) {
         s.a(g);
      }
   }

   public void clearPathfinders(Mob mob, boolean target) {
      EntityInsentient m = toNMS(mob);
      PathfinderGoalSelector s = target ? m.bQ : m.bP;
      s.a();
   }

   public static BehaviorResult.Status fromNMS(Status status) {
      return status == Status.a ? BehaviorResult.Status.STOPPED : BehaviorResult.Status.RUNNING;
   }

   public static LivingEntity fromNMS(EntityLiving l) {
      return (LivingEntity)l.getBukkitEntity();
   }

   public BehaviorResult runBehavior(Mob m, String behaviorName, Object... args) {
      return this.runBehavior(m, behaviorName, Behavior.class.getPackage().getName(), args);
   }

   public BehaviorResult runBehavior(Mob m, String behaviorName, String packageName, Object... args) {
      EntityInsentient nms = toNMS(m);

      for(int i = 0; i < args.length; ++i) {
         Object o = args[i];
         if (o instanceof Profession) {
            args[i] = toNMS((Profession)o);
         }

         if (o instanceof Memory) {
            args[i] = toNMS((Memory)o);
         }

         if (o instanceof Predicate) {
            args[i] = (obj) -> {
               return obj instanceof EntityInsentient ? ((Predicate)o).test(fromNMS((EntityInsentient)obj)) : ((Predicate)o).test(obj);
            };
         }

         if (o instanceof Function) {
            args[i] = (obj) -> {
               return obj instanceof EntityLiving ? ((Function)o).apply(fromNMS((EntityLiving)obj)) : ((Function)o).apply(obj);
            };
         }
      }

      try {
         Class<?> bClass = Class.forName(packageName + "." + behaviorName);
         Constructor<?> c = bClass.getConstructor(ChipUtil.getArgTypes(args));
         Behavior<? super EntityLiving> b = (Behavior)c.newInstance(args);
         return new BehaviorResult1_17_R1(b, nms);
      } catch (Exception var9) {
         ChipUtil.printStackTrace(var9);
         return null;
      }
   }

   public static EntityPlayer toNMS(Player p) {
      return ((CraftPlayer)p).getHandle();
   }

   public Attribute getDefaultAttribute(String s) {
      return new Attribute1_17_R1((AttributeRanged)IRegistry.al.get(new MinecraftKey(s)));
   }

   public static Activity toNMS(me.darksnakex.villagerfollow.mobchip.ai.schedule.Activity a) {
      return (Activity)IRegistry.au.get(new MinecraftKey(a.getKey().getKey()));
   }

   public static me.darksnakex.villagerfollow.mobchip.ai.schedule.Activity fromNMS(Activity a) {
      MinecraftKey key = IRegistry.au.getKey(a);
      return key == null ? null : me.darksnakex.villagerfollow.mobchip.ai.schedule.Activity.getByKey(NamespacedKey.minecraft(key.getKey()));
   }

   public static Schedule fromNMS(net.minecraft.world.entity.schedule.Schedule s) {
      Schedule.Builder b = Schedule.builder();

      for(int i = 0; i < 24000; ++i) {
         if (s.a(i) != null) {
            me.darksnakex.villagerfollow.mobchip.ai.schedule.Activity a = fromNMS(s.a(i));
            b.addActivity(i, a);
         }
      }

      return b.build();
   }

   public static AbstractDragonController toNMS(final CustomPhase c) {
      return new AbstractDragonController(toNMS(c.getDragon())) {
         public DragonControllerPhase<? extends IDragonController> getControllerPhase() {
            try {
               Method create = DragonControllerPhase.class.getDeclaredMethod("a");
               create.setAccessible(true);
               return (DragonControllerPhase)create.invoke((Object)null, this.getClass(), c.getKey().getKey());
            } catch (Exception var2) {
               return DragonControllerPhase.k;
            }
         }

         public void d() {
            c.start();
         }

         public void e() {
            c.stop();
         }

         public boolean a() {
            return c.isSitting();
         }

         public void b() {
            c.clientTick();
         }

         public void c() {
            c.serverTick();
         }

         public void a(EntityEnderCrystal crystal, BlockPosition pos, DamageSource s, EntityHuman p) {
            EnderCrystal bCrystal = (EnderCrystal)crystal.getBukkitEntity();
            c.onCrystalDestroyed(bCrystal, ChipUtil1_17_R1.fromNMS(s), p == null ? null : Bukkit.getPlayer(p.getUniqueID()));
         }

         public Vec3D g() {
            Location l = c.getTargetLocation();
            return new Vec3D(l.getX(), l.getY(), l.getZ());
         }

         public float f() {
            return c.getFlyingSpeed();
         }

         public float a(DamageSource s, float damage) {
            return c.onDamage(ChipUtil1_17_R1.fromNMS(s), damage);
         }
      };
   }

   public void setCustomPhase(EnderDragon a, CustomPhase c) {
      EntityEnderDragon nmsMob = toNMS(a);
      AbstractDragonController nmsPhase = toNMS(c);

      try {
         (new DragonControllerManager(nmsMob)).setControllerPhase(nmsPhase.getControllerPhase());
      } catch (IndexOutOfBoundsException var6) {
      }

   }

   public static net.minecraft.world.entity.schedule.Schedule toNMS(Schedule s) {
      ScheduleBuilder b = new ScheduleBuilder(new net.minecraft.world.entity.schedule.Schedule());

      for(int i = 0; i < 24000; ++i) {
         if (s.contains(i)) {
            Activity a = toNMS(s.get(i));
            b.a(i, a);
         }
      }

      return b.a();
   }

   public static <T extends EntityLiving> Behavior<T> toNMS(final Consumer<Mob> en) {
      return new Behavior<T>(Collections.emptyMap()) {
         protected void d(WorldServer var0, T m, long var2) {
            if (m instanceof EntityInsentient) {
               en.accept(ChipUtil1_17_R1.fromNMS((EntityInsentient)m));
            }
         }
      };
   }

   public Schedule getDefaultSchedule(String key) {
      return fromNMS((net.minecraft.world.entity.schedule.Schedule)IRegistry.at.get(new MinecraftKey(key)));
   }

   public EntityScheduleManager getManager(Mob m) {
      return new EntityScheduleManager1_17_R1(m);
   }

   public EntityController getController(Mob m) {
      return new EntityController1_17_R1(m);
   }

   public EntityNavigation getNavigation(Mob m) {
      return new EntityNavigation1_17_R1(m);
   }

   public EntityBody getBody(Mob m) {
      return new EntityBody1_17_R1(m);
   }

   public static DamageSource toNMS(DamageCause c) {
      DamageSource var10000;
      switch(c) {
      case FIRE:
         var10000 = DamageSource.a;
         break;
      case LIGHTNING:
         var10000 = DamageSource.b;
         break;
      case FIRE_TICK:
         var10000 = DamageSource.c;
         break;
      case SUFFOCATION:
         var10000 = DamageSource.f;
         break;
      case LAVA:
         var10000 = DamageSource.d;
         break;
      case HOT_FLOOR:
         var10000 = DamageSource.e;
         break;
      case CRAMMING:
         var10000 = DamageSource.g;
         break;
      case DROWNING:
         var10000 = DamageSource.h;
         break;
      case STARVATION:
         var10000 = DamageSource.i;
         break;
      case CONTACT:
         var10000 = DamageSource.j;
         break;
      case MAGIC:
         var10000 = DamageSource.o;
         break;
      case FALL:
         var10000 = DamageSource.k;
         break;
      case FLY_INTO_WALL:
         var10000 = DamageSource.l;
         break;
      case VOID:
         var10000 = DamageSource.m;
         break;
      case WITHER:
         var10000 = DamageSource.p;
         break;
      case FALLING_BLOCK:
         var10000 = DamageSource.r;
         break;
      case DRAGON_BREATH:
         var10000 = DamageSource.s;
         break;
      case FREEZE:
         var10000 = DamageSource.v;
         break;
      case DRYOUT:
         var10000 = DamageSource.t;
         break;
      default:
         var10000 = DamageSource.n;
      }

      return var10000;
   }

   public static EntityItem toNMS(Item i) {
      return (EntityItem)((CraftItem)i).getHandle();
   }

   public static EntityLiving toNMS(LivingEntity en) {
      return ((CraftLivingEntity)en).getHandle();
   }

   public static Object toNMS(String key, Object value) {
      Object nmsValue;
      if (value instanceof Location) {
         Location l = (Location)value;
         if (!key.equals("nearest_bed") && !key.equals("celebrate_location") && !key.equals("nearest_repellent")) {
            nmsValue = GlobalPos.create(toNMS(l.getWorld()).getDimensionKey(), new BlockPosition(l.getX(), l.getY(), l.getZ()));
         } else {
            nmsValue = new BlockPosition(l.getX(), l.getY(), l.getZ());
         }
      } else {
         ArrayList s;
         int var16;
         int var17;
         if (value instanceof Location[]) {
            Location[] ls = (Location[])value;
            s = new ArrayList();
            Location[] var15 = ls;
            var16 = ls.length;

            for(var17 = 0; var17 < var16; ++var17) {
               Location l = var15[var17];
               s.add(GlobalPos.create(toNMS(l.getWorld()).getDimensionKey(), new BlockPosition(l.getX(), l.getY(), l.getZ())));
            }

            nmsValue = s;
         } else if (value instanceof Player) {
            Player p = (Player)value;
            if (key.equals("liked_player")) {
               nmsValue = p.getUniqueId();
            } else {
               nmsValue = toNMS(p);
            }
         } else if (value instanceof Memory.WalkingTarget) {
            Memory.WalkingTarget t = (Memory.WalkingTarget)value;
            nmsValue = new MemoryTarget(toNMS(t.getLocation()), (float)t.getSpeedModifier(), t.getDistance());
         } else if (value instanceof LivingEntity) {
            LivingEntity l = (LivingEntity)value;
            nmsValue = toNMS(l);
         } else if (value instanceof Entity) {
            Entity e = (Entity)value;
            if (key.equals("angry_at")) {
               nmsValue = e.getUniqueId();
            } else {
               nmsValue = toNMS(e);
            }
         } else if (value instanceof Block[]) {
            Block[] b = (Block[])value;
            Object s;
            if (key.equals("doors_to_close")) {
               s = new HashSet();
            } else {
               s = new ArrayList();
            }

            Block[] var21 = b;
            var16 = b.length;

            for(var17 = 0; var17 < var16; ++var17) {
               Block bl = var21[var17];
               Location l = bl.getLocation();
               ((Collection)s).add(GlobalPos.create(toNMS(l.getWorld()).getDimensionKey(), new BlockPosition(l.getX(), l.getY(), l.getZ())));
            }

            nmsValue = s;
         } else if (value instanceof Villager[]) {
            Villager[] vs = (Villager[])value;
            s = new ArrayList();
            Villager[] var22 = vs;
            var16 = vs.length;

            for(var17 = 0; var17 < var16; ++var17) {
               Villager v = var22[var17];
               s.add(toNMS((Creature)v));
            }

            nmsValue = s;
         } else if (value instanceof Player[]) {
            Player[] ps = (Player[])value;
            s = new ArrayList();
            Player[] var23 = ps;
            var16 = ps.length;

            for(var17 = 0; var17 < var16; ++var17) {
               Player p = var23[var17];
               s.add(toNMS(p));
            }

            nmsValue = s;
         } else if (value instanceof LivingEntity[]) {
            LivingEntity[] ls = (LivingEntity[])value;
            s = new ArrayList();
            LivingEntity[] var24 = ls;
            var16 = ls.length;

            for(var17 = 0; var17 < var16; ++var17) {
               LivingEntity l = var24[var17];
               s.add(toNMS(l));
            }

            nmsValue = s;
         } else if (value instanceof DamageCause) {
            DamageCause c = (DamageCause)value;
            nmsValue = toNMS(c);
         } else if (value instanceof Unit) {
            nmsValue = net.minecraft.util.Unit.a;
         } else {
            nmsValue = value;
         }
      }

      return nmsValue;
   }

   public static Object fromNMS(Mob m, String key, Object nmsValue) {
      Object value = nmsValue;
      BlockPosition p;
      if (nmsValue instanceof GlobalPos) {
         GlobalPos l = (GlobalPos)nmsValue;
         p = l.getBlockPosition();
         World w = ((net.minecraft.world.level.World)((CraftServer)Bukkit.getServer()).getHandle().getServer().l.d(IRegistry.Q).a(l.getDimensionManager())).getWorld();
         if (w == null) {
            w = (World)Bukkit.getWorlds().get(0);
         }

         value = new Location((World)w, (double)p.getX(), (double)p.getY(), (double)p.getZ());
      } else if (nmsValue instanceof BlockPosition) {
         BlockPosition p = (BlockPosition)nmsValue;
         value = new Location(m.getWorld(), (double)p.getX(), (double)p.getY(), (double)p.getZ());
      } else if (nmsValue instanceof List) {
         List<?> li = (List)nmsValue;
         byte var17 = -1;
         switch(key.hashCode()) {
         case -1628939567:
            if (key.equals("nearest_players")) {
               var17 = 2;
            }
            break;
         case -560796428:
            if (key.equals("secondary_job_site")) {
               var17 = 0;
            }
            break;
         case -340440256:
            if (key.equals("visible_villager_babies")) {
               var17 = 3;
            }
            break;
         case 3357043:
            if (key.equals("mobs")) {
               var17 = 4;
            }
            break;
         case 65415702:
            if (key.equals("interactable_doors")) {
               var17 = 1;
            }
         }

         ArrayList vl;
         switch(var17) {
         case 0:
         case 1:
            vl = new ArrayList();
            li.forEach((ox) -> {
               vl.add((Location)fromNMS(m, key, ox));
            });
            value = vl.toArray(new Location[0]);
            break;
         case 2:
            vl = new ArrayList();
            li.forEach((ox) -> {
               vl.add(Bukkit.getPlayer(((EntityHuman)ox).getUniqueID()));
            });
            value = vl.toArray(new Player[0]);
            break;
         case 3:
            vl = new ArrayList();
            li.forEach((ox) -> {
               vl.add((Villager)fromNMS((EntityLiving)ox));
            });
            value = vl.toArray(new Villager[0]);
            break;
         case 4:
            vl = new ArrayList();
            li.forEach((ox) -> {
               vl.add(fromNMS((EntityLiving)ox));
            });
            value = vl.toArray(new LivingEntity[0]);
         }
      } else if (nmsValue instanceof EntityHuman) {
         EntityHuman p = (EntityHuman)nmsValue;
         value = Bukkit.getPlayer(p.getUniqueID());
      } else if (nmsValue instanceof MemoryTarget) {
         MemoryTarget t = (MemoryTarget)nmsValue;
         p = t.a().b();
         value = new Memory.WalkingTarget(new Location(m.getWorld(), (double)p.getX(), (double)p.getY(), (double)p.getZ()), t.b(), t.c());
      } else if (nmsValue instanceof EntityLiving) {
         EntityLiving l = (EntityLiving)nmsValue;
         value = Bukkit.getEntity(l.getUniqueID());
      } else if (nmsValue instanceof Set) {
         Set<?> s = (Set)nmsValue;
         if (key.equals("doors_to_close")) {
            List<Block> l = new ArrayList();
            s.forEach((ox) -> {
               l.add((Block)fromNMS(m, key, ox));
            });
            value = l.toArray(new Block[0]);
         }
      } else if (nmsValue instanceof DamageSource) {
         DamageSource c = (DamageSource)nmsValue;
         value = fromNMS(c);
      } else if (nmsValue instanceof net.minecraft.util.Unit) {
         value = Unit.INSTANCE;
      } else if (nmsValue instanceof Optional) {
         Optional<?> o = (Optional)nmsValue;
         value = fromNMS(m, key, o.orElse((Object)null));
      } else {
         value = nmsValue;
      }

      return value;
   }

   public static DamageCause fromNMS(DamageSource c) {
      String var1 = c.y;
      byte var2 = -1;
      switch(var1.hashCode()) {
      case -1818196485:
         if (var1.equals("fallingStalactite")) {
            var2 = 19;
         }
         break;
      case -1664218659:
         if (var1.equals("flyIntoWall")) {
            var2 = 13;
         }
         break;
      case -1368035283:
         if (var1.equals("cactus")) {
            var2 = 9;
         }
         break;
      case -1323055677:
         if (var1.equals("dryout")) {
            var2 = 21;
         }
         break;
      case -1266402665:
         if (var1.equals("freeze")) {
            var2 = 22;
         }
         break;
      case -1185129221:
         if (var1.equals("inFire")) {
            var2 = 0;
         }
         break;
      case -1184630641:
         if (var1.equals("inWall")) {
            var2 = 5;
         }
         break;
      case -1019324333:
         if (var1.equals("dragonBreath")) {
            var2 = 20;
         }
         break;
      case -1013354315:
         if (var1.equals("onFire")) {
            var2 = 2;
         }
         break;
      case -952114995:
         if (var1.equals("outOfWorld")) {
            var2 = 14;
         }
         break;
      case -892483455:
         if (var1.equals("starve")) {
            var2 = 8;
         }
         break;
      case -787569677:
         if (var1.equals("wither")) {
            var2 = 16;
         }
         break;
      case -313702465:
         if (var1.equals("hotFloor")) {
            var2 = 4;
         }
         break;
      case 3135355:
         if (var1.equals("fall")) {
            var2 = 12;
         }
         break;
      case 3314400:
         if (var1.equals("lava")) {
            var2 = 3;
         }
         break;
      case 92975308:
         if (var1.equals("anvil")) {
            var2 = 17;
         }
         break;
      case 95858744:
         if (var1.equals("drown")) {
            var2 = 7;
         }
         break;
      case 103655853:
         if (var1.equals("magic")) {
            var2 = 15;
         }
         break;
      case 556467782:
         if (var1.equals("fallingBlock")) {
            var2 = 18;
         }
         break;
      case 850365567:
         if (var1.equals("lightningBolt")) {
            var2 = 1;
         }
         break;
      case 1154993023:
         if (var1.equals("stalagmite")) {
            var2 = 11;
         }
         break;
      case 1534373134:
         if (var1.equals("sweetBerryBush")) {
            var2 = 10;
         }
         break;
      case 1716778928:
         if (var1.equals("cramming")) {
            var2 = 6;
         }
      }

      DamageCause var10000;
      switch(var2) {
      case 0:
         var10000 = DamageCause.FIRE;
         break;
      case 1:
         var10000 = DamageCause.LIGHTNING;
         break;
      case 2:
         var10000 = DamageCause.FIRE_TICK;
         break;
      case 3:
         var10000 = DamageCause.LAVA;
         break;
      case 4:
         var10000 = DamageCause.HOT_FLOOR;
         break;
      case 5:
         var10000 = DamageCause.SUFFOCATION;
         break;
      case 6:
         var10000 = DamageCause.CRAMMING;
         break;
      case 7:
         var10000 = DamageCause.DROWNING;
         break;
      case 8:
         var10000 = DamageCause.STARVATION;
         break;
      case 9:
      case 10:
      case 11:
         var10000 = DamageCause.CONTACT;
         break;
      case 12:
         var10000 = DamageCause.FALL;
         break;
      case 13:
         var10000 = DamageCause.FLY_INTO_WALL;
         break;
      case 14:
         var10000 = DamageCause.VOID;
         break;
      case 15:
         var10000 = DamageCause.MAGIC;
         break;
      case 16:
         var10000 = DamageCause.WITHER;
         break;
      case 17:
      case 18:
      case 19:
         var10000 = DamageCause.FALLING_BLOCK;
         break;
      case 20:
         var10000 = DamageCause.DRAGON_BREATH;
         break;
      case 21:
         var10000 = DamageCause.DRYOUT;
         break;
      case 22:
         var10000 = DamageCause.FREEZE;
         break;
      default:
         var10000 = DamageCause.CUSTOM;
      }

      return var10000;
   }

   public MemoryStatus getMemoryStatus(Mob mob, Memory<?> m) {
      EntityInsentient nms = toNMS(mob);
      MemoryModuleType<?> nmsM = toNMS(m);
      if (nms.getBehaviorController().a(nmsM, net.minecraft.world.entity.ai.memory.MemoryStatus.a)) {
         return MemoryStatus.PRESENT;
      } else {
         return nms.getBehaviorController().a(nmsM, net.minecraft.world.entity.ai.memory.MemoryStatus.b) ? MemoryStatus.ABSENT : MemoryStatus.REGISTERED;
      }
   }

   public void setMemory(Mob mob, String memoryKey, Object value) {
      EntityInsentient nms = toNMS(mob);
      MemoryModuleType type = (MemoryModuleType)IRegistry.ar.get(new MinecraftKey(memoryKey));
      Object nmsValue = toNMS(memoryKey, value);
      nms.getBehaviorController().setMemory(type, nmsValue);
   }

   public <T> void setMemory(Mob mob, Memory<T> m, T value) {
      if (value == null) {
         this.removeMemory(mob, m);
      } else {
         EntityInsentient nms = toNMS(mob);
         MemoryModuleType type = toNMS(m);
         String key = IRegistry.ar.getKey(type).getKey();
         Object nmsValue = toNMS(key, value);
         nms.getBehaviorController().setMemory(type, nmsValue);
      }
   }

   public <T> void setMemory(Mob mob, Memory<T> m, T value, long durationTicks) {
      if (value == null) {
         this.removeMemory(mob, m);
      } else {
         EntityInsentient nms = toNMS(mob);
         MemoryModuleType type = toNMS(m);
         String key = IRegistry.ar.getKey(type).getKey();
         Object nmsValue = toNMS(key, value);
         nms.getBehaviorController().a(type, nmsValue, durationTicks);
      }
   }

   public <T> T getMemory(Mob mob, Memory<T> m) {
      EntityInsentient nms = toNMS(mob);
      MemoryModuleType type = toNMS(m);
      String key = IRegistry.ar.getKey(type).getKey();
      return m.getBukkitClass().cast(fromNMS(mob, key, nms.getBehaviorController().getMemory(type)));
   }

   public long getExpiry(Mob mob, Memory<?> m) {
      EntityInsentient nms = toNMS(mob);
      MemoryModuleType type = toNMS(m);
      return nms.getBehaviorController().d(type);
   }

   public boolean contains(Mob mob, Memory<?> m) {
      EntityInsentient nms = toNMS(mob);
      MemoryModuleType type = toNMS(m);
      return nms.getBehaviorController().hasMemory(type);
   }

   public void removeMemory(Mob mob, Memory<?> m) {
      EntityInsentient nms = toNMS(mob);
      MemoryModuleType<?> type = toNMS(m);
      nms.getBehaviorController().removeMemory(type);
   }

   public boolean isRestricted(Mob m) {
      EntityInsentient nms = toNMS(m);
      return nms.fh();
   }

   public void clearRestriction(Mob m) {
      EntityInsentient nms = toNMS(m);
      nms.fk();
   }

   public void restrictTo(Mob m, double x, double y, double z, int radius) {
      EntityInsentient nms = toNMS(m);
      nms.a(new BlockPosition(x, y, z), radius);
   }

   public Location getRestriction(Mob m) {
      EntityInsentient nms = toNMS(m);
      BlockPosition c = nms.fi();
      return new Location(m.getWorld(), (double)c.getX(), (double)c.getY(), (double)c.getZ());
   }

   public int getRestrictionRadius(Mob m) {
      EntityInsentient nms = toNMS(m);
      return (int)nms.fj() < 0 ? Integer.MAX_VALUE : (int)nms.fj();
   }

   public boolean hasRestriction(Mob m) {
      EntityInsentient nms = toNMS(m);
      return nms.fl();
   }

   public boolean canSee(Mob m, Entity en) {
      EntityInsentient nms = toNMS(m);
      return nms.getEntitySenses().a(toNMS(en));
   }

   public static net.minecraft.world.entity.Entity toNMS(Entity en) {
      return ((CraftEntity)en).getHandle();
   }

   public static VillagerProfession toNMS(Profession p) {
      VillagerProfession var10000;
      switch(p) {
      case FARMER:
         var10000 = VillagerProfession.f;
         break;
      case FISHERMAN:
         var10000 = VillagerProfession.g;
         break;
      case LIBRARIAN:
         var10000 = VillagerProfession.j;
         break;
      case WEAPONSMITH:
         var10000 = VillagerProfession.o;
         break;
      case TOOLSMITH:
         var10000 = VillagerProfession.n;
         break;
      case BUTCHER:
         var10000 = VillagerProfession.c;
         break;
      case FLETCHER:
         var10000 = VillagerProfession.h;
         break;
      case MASON:
         var10000 = VillagerProfession.k;
         break;
      case CLERIC:
         var10000 = VillagerProfession.e;
         break;
      case ARMORER:
         var10000 = VillagerProfession.b;
         break;
      case NITWIT:
         var10000 = VillagerProfession.l;
         break;
      case SHEPHERD:
         var10000 = VillagerProfession.m;
         break;
      case CARTOGRAPHER:
         var10000 = VillagerProfession.d;
         break;
      case LEATHERWORKER:
         var10000 = VillagerProfession.i;
         break;
      default:
         var10000 = VillagerProfession.a;
      }

      return var10000;
   }

   public static <T extends Entity> Class<? extends T> fromNMS(Class<? extends net.minecraft.world.entity.Entity> clazz, Class<T> cast) {
      try {
         String name = clazz.getSimpleName();
         if (name.contains("Entity")) {
            name = name.replace("Entity", "");
         }

         byte var5 = -1;
         switch(name.hashCode()) {
         case -2018562359:
            if (name.equals("Living")) {
               var5 = 1;
            }
            break;
         case -1901885695:
            if (name.equals("Player")) {
               var5 = 25;
            }
            break;
         case -1898564173:
            if (name.equals("Potion")) {
               var5 = 29;
            }
            break;
         case -1813869257:
            if (name.equals("ProjectileThrowable")) {
               var5 = 30;
            }
            break;
         case -1706397252:
            if (name.equals("SkeletonStray")) {
               var5 = 17;
            }
            break;
         case -1604554070:
            if (name.equals("Lightning")) {
               var5 = 2;
            }
            break;
         case -1575865515:
            if (name.equals("MinecartAbstract")) {
               var5 = 32;
            }
            break;
         case -1506058053:
            if (name.equals("MinecartMobSpawner")) {
               var5 = 37;
            }
            break;
         case -1449231425:
            if (name.equals("Insentient")) {
               var5 = 3;
            }
            break;
         case -1305110587:
            if (name.equals("MinecartFurnace")) {
               var5 = 35;
            }
            break;
         case -1254283584:
            if (name.equals("SkeletonWither")) {
               var5 = 18;
            }
            break;
         case -1128579659:
            if (name.equals("MinecartCommandBlock")) {
               var5 = 34;
            }
            break;
         case -852294105:
            if (name.equals("MinecartTNT")) {
               var5 = 38;
            }
            break;
         case -705059751:
            if (name.equals("HorseDonkey")) {
               var5 = 11;
            }
            break;
         case -428700629:
            if (name.equals("GiantZombie")) {
               var5 = 13;
            }
            break;
         case -176504182:
            if (name.equals("IllagerIllusioner")) {
               var5 = 15;
            }
            break;
         case -102566993:
            if (name.equals("SkeletonAbstract")) {
               var5 = 16;
            }
            break;
         case -95505765:
            if (name.equals("TameableAnimal")) {
               var5 = 4;
            }
            break;
         case -75256765:
            if (name.equals("HorseZombie")) {
               var5 = 10;
            }
            break;
         case 0:
            if (name.equals("")) {
               var5 = 0;
            }
            break;
         case 70086925:
            if (name.equals("Human")) {
               var5 = 24;
            }
            break;
         case 304121258:
            if (name.equals("FireballFireball")) {
               var5 = 26;
            }
            break;
         case 374958718:
            if (name.equals("HorseMule")) {
               var5 = 8;
            }
            break;
         case 456218772:
            if (name.equals("VillagerTrader")) {
               var5 = 23;
            }
            break;
         case 546705836:
            if (name.equals("ZombieVillager")) {
               var5 = 20;
            }
            break;
         case 718380717:
            if (name.equals("FishingHook")) {
               var5 = 28;
            }
            break;
         case 787135193:
            if (name.equals("GuardianElder")) {
               var5 = 14;
            }
            break;
         case 801235880:
            if (name.equals("VillagerAbstract")) {
               var5 = 22;
            }
            break;
         case 840843303:
            if (name.equals("MinecartHopper")) {
               var5 = 36;
            }
            break;
         case 1269213358:
            if (name.equals("MinecartChest")) {
               var5 = 33;
            }
            break;
         case 1333027498:
            if (name.equals("ThrownTrident")) {
               var5 = 31;
            }
            break;
         case 1451119974:
            if (name.equals("Villager")) {
               var5 = 21;
            }
            break;
         case 1461450764:
            if (name.equals("FishSchool")) {
               var5 = 6;
            }
            break;
         case 1477867987:
            if (name.equals("WaterAnimal")) {
               var5 = 12;
            }
            break;
         case 1600081803:
            if (name.equals("ZombieHusk")) {
               var5 = 19;
            }
            break;
         case 1608127231:
            if (name.equals("HorseAbstract")) {
               var5 = 7;
            }
            break;
         case 1739765484:
            if (name.equals("Fireworks")) {
               var5 = 27;
            }
            break;
         case 1902975786:
            if (name.equals("HorseSkeleton")) {
               var5 = 9;
            }
            break;
         case 1965718044:
            if (name.equals("Animal")) {
               var5 = 5;
            }
         }

         Class var10000;
         switch(var5) {
         case 0:
            var10000 = Entity.class;
            break;
         case 1:
            var10000 = LivingEntity.class;
            break;
         case 2:
            var10000 = LightningStrike.class;
            break;
         case 3:
            var10000 = Mob.class;
            break;
         case 4:
            var10000 = Tameable.class;
            break;
         case 5:
            var10000 = Animals.class;
            break;
         case 6:
            var10000 = Fish.class;
            break;
         case 7:
            var10000 = AbstractHorse.class;
            break;
         case 8:
            var10000 = Mule.class;
            break;
         case 9:
            var10000 = SkeletonHorse.class;
            break;
         case 10:
            var10000 = ZombieHorse.class;
            break;
         case 11:
            var10000 = Donkey.class;
            break;
         case 12:
            var10000 = WaterMob.class;
            break;
         case 13:
            var10000 = Giant.class;
            break;
         case 14:
            var10000 = ElderGuardian.class;
            break;
         case 15:
            var10000 = Illusioner.class;
            break;
         case 16:
            var10000 = AbstractSkeleton.class;
            break;
         case 17:
            var10000 = Stray.class;
            break;
         case 18:
            var10000 = WitherSkeleton.class;
            break;
         case 19:
            var10000 = Husk.class;
            break;
         case 20:
            var10000 = ZombieVillager.class;
            break;
         case 21:
            var10000 = Villager.class;
            break;
         case 22:
            var10000 = AbstractVillager.class;
            break;
         case 23:
            var10000 = WanderingTrader.class;
            break;
         case 24:
            var10000 = HumanEntity.class;
            break;
         case 25:
            var10000 = Player.class;
            break;
         case 26:
            var10000 = SizedFireball.class;
            break;
         case 27:
            var10000 = Firework.class;
            break;
         case 28:
            var10000 = FishHook.class;
            break;
         case 29:
            var10000 = ThrownPotion.class;
            break;
         case 30:
            var10000 = ThrowableProjectile.class;
            break;
         case 31:
            var10000 = Trident.class;
            break;
         case 32:
            var10000 = Minecart.class;
            break;
         case 33:
            var10000 = StorageMinecart.class;
            break;
         case 34:
            var10000 = CommandMinecart.class;
            break;
         case 35:
            var10000 = PoweredMinecart.class;
            break;
         case 36:
            var10000 = HopperMinecart.class;
            break;
         case 37:
            var10000 = SpawnerMinecart.class;
            break;
         case 38:
            var10000 = ExplosiveMinecart.class;
            break;
         default:
            var10000 = Class.forName("org.bukkit.entity." + name).asSubclass(Entity.class);
         }

         Class<? extends Entity> bukkit = var10000;
         return bukkit.asSubclass(cast);
      } catch (ClassNotFoundException var6) {
         return cast;
      }
   }

   public static EntityInsentient toNMS(Mob m) {
      return ((CraftMob)m).getHandle();
   }

   public static EntityType[] getEntityTypes(Class<?>... nms) {
      List<EntityType> types = new ArrayList();
      Class[] var2 = nms;
      int var3 = nms.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Class<?> c = var2[var4];
         Class<? extends Entity> bukkit = fromNMS(c, Entity.class);
         EntityType[] var7 = EntityType.values();
         int var8 = var7.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            EntityType t = var7[var9];
            if (t.getEntityClass() != null && t.getEntityClass().isAssignableFrom(bukkit)) {
               types.add(t);
            }
         }
      }

      return (EntityType[])types.toArray(new EntityType[0]);
   }

   public static EnumDifficulty toNMS(Difficulty d) {
      EnumDifficulty var10000;
      switch(d) {
      case PEACEFUL:
         var10000 = EnumDifficulty.a;
         break;
      case EASY:
         var10000 = EnumDifficulty.b;
         break;
      case NORMAL:
         var10000 = EnumDifficulty.c;
         break;
      case HARD:
         var10000 = EnumDifficulty.d;
         break;
      default:
         throw new IncompatibleClassChangeError();
      }

      return var10000;
   }

   public static Difficulty fromNMS(EnumDifficulty d) {
      Difficulty var10000;
      switch(d) {
      case a:
         var10000 = Difficulty.PEACEFUL;
         break;
      case b:
         var10000 = Difficulty.EASY;
         break;
      case c:
         var10000 = Difficulty.NORMAL;
         break;
      case d:
         var10000 = Difficulty.HARD;
         break;
      default:
         throw new IncompatibleClassChangeError();
      }

      return var10000;
   }

   public static EntityCreature toNMS(Creature c) {
      return ((CraftCreature)c).getHandle();
   }

   public static Type toNMS(Pathfinder.PathfinderFlag f) {
      Type var10000;
      switch(f) {
      case MOVEMENT:
         var10000 = Type.a;
         break;
      case JUMPING:
         var10000 = Type.c;
         break;
      case TARGETING:
         var10000 = Type.d;
         break;
      case LOOKING:
         var10000 = Type.b;
         break;
      default:
         throw new IncompatibleClassChangeError();
      }

      return var10000;
   }

   public static Pathfinder.PathfinderFlag fromNMS(Type f) {
      Pathfinder.PathfinderFlag var10000;
      switch(f) {
      case a:
         var10000 = Pathfinder.PathfinderFlag.MOVEMENT;
         break;
      case c:
         var10000 = Pathfinder.PathfinderFlag.JUMPING;
         break;
      case d:
         var10000 = Pathfinder.PathfinderFlag.TARGETING;
         break;
      case b:
         var10000 = Pathfinder.PathfinderFlag.LOOKING;
         break;
      default:
         throw new IncompatibleClassChangeError();
      }

      return var10000;
   }

   public static float getFloat(PathfinderGoal o, String name) {
      Float obj = (Float)getObject(o, name, Float.class);
      return obj == null ? 0.0F : obj;
   }

   public static double getDouble(PathfinderGoal o, String name) {
      Double obj = (Double)getObject(o, name, Double.class);
      return obj == null ? 0.0D : obj;
   }

   public static boolean getBoolean(PathfinderGoal o, String name) {
      Boolean obj = (Boolean)getObject(o, name, Boolean.class);
      return obj != null && obj;
   }

   public static int getInt(PathfinderGoal o, String name) {
      Integer obj = (Integer)getObject(o, name, Integer.class);
      return obj == null ? 0 : obj;
   }

   public static <T> T getObject(PathfinderGoal o, String name, Class<T> cast) {
      try {
         Class clazz = o.getClass();

         while(clazz.getSuperclass() != null) {
            try {
               Field f = clazz.getDeclaredField(name);
               f.setAccessible(true);
               return cast.cast(f.get(o));
            } catch (ClassCastException | NoSuchFieldException var5) {
               if (!PathfinderGoal.class.isAssignableFrom(clazz.getSuperclass())) {
                  break;
               }

               clazz = clazz.getSuperclass();
            }
         }
      } catch (Exception var6) {
         ChipUtil.printStackTrace(var6);
      }

      return null;
   }

   public static Mob fromNMS(EntityInsentient m) {
      return (Mob)m.getBukkitEntity();
   }

   public static World fromNMS(net.minecraft.world.level.World l) {
      return l.getWorld();
   }

   public static WorldServer toNMS(World w) {
      return ((CraftWorld)w).getHandle();
   }

   public static BlockPosition toNMS(Location l) {
      return new BlockPosition(l.getX(), l.getY(), l.getZ());
   }

   public static List<ItemStack> fromNMS(RecipeItemStack in) {
      return (List)Arrays.stream(in.a()).map(CraftItemStack::asBukkitCopy).collect(Collectors.toList());
   }

   public static Sound fromNMS(SoundEffect s) {
      return CraftSound.getBukkit(s);
   }

   public static Mob getEntity(PathfinderGoal g) {
      boolean ignoreNonFinal = g instanceof PathfinderGoalDoorInteract;

      try {
         for(Class clazz = g.getClass(); clazz.getSuperclass() != null; clazz = clazz.getSuperclass()) {
            Field[] var3 = clazz.getDeclaredFields();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
               Field f = var3[var5];
               f.setAccessible(true);
               if (EntityInsentient.class.isAssignableFrom(f.getType()) && (ignoreNonFinal || Modifier.isFinal(f.getModifiers()))) {
                  return fromNMS((EntityInsentient)f.get(g));
               }
            }

            if (!PathfinderGoal.class.isAssignableFrom(clazz.getSuperclass())) {
               break;
            }
         }

         return null;
      } catch (Exception var7) {
         ChipUtil.printStackTrace(var7);
         return null;
      }
   }

   public static Object invoke(PathfinderGoal g, String method, Object... args) {
      try {
         Method m = g.getClass().getDeclaredMethod(method);
         m.setAccessible(true);
         return m.invoke(g, args);
      } catch (Exception var4) {
         ChipUtil.printStackTrace(var4);
         return null;
      }
   }

   public static Set<Type> getFlags(long backingSet) {
      Set<Type> flags = new HashSet();
      Type[] var3 = Type.values();
      int var4 = var3.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         Type flag = var3[var5];
         if ((backingSet & 1L << flag.ordinal()) != 0L) {
            flags.add(flag);
         }
      }

      return flags;
   }

   public static Set<Type> getFlags(PathfinderGoal g) {
      try {
         Method getFlags = PathfinderGoal.class.getDeclaredMethod("getFlags");
         getFlags.setAccessible(true);
         Object optimizedSmallEnumSet = getFlags.invoke(g);
         Method backingSetM = optimizedSmallEnumSet.getClass().getDeclaredMethod("getBackingSet");
         backingSetM.setAccessible(true);
         long backingSet = (Long)backingSetM.invoke(optimizedSmallEnumSet);
         return getFlags(backingSet);
      } catch (NoSuchMethodException var8) {
         try {
            Method obfGetFlags = PathfinderGoal.class.getDeclaredMethod("j");
            obfGetFlags.setAccessible(true);
            return (Set)obfGetFlags.invoke(g);
         } catch (NoSuchMethodException var6) {
            throw new AssertionError("Could not find flags", var6);
         } catch (ReflectiveOperationException var7) {
            ChipUtil.printStackTrace(var7);
            return null;
         }
      } catch (ReflectiveOperationException var9) {
         ChipUtil.printStackTrace(var9);
         return null;
      }
   }

   public static PathfinderGoal custom(CustomPathfinder p) {
      CustomGoal1_17_R1 g = new CustomGoal1_17_R1(p);
      EnumSet<Type> set = EnumSet.noneOf(Type.class);
      Stream var10000 = Arrays.stream(p.getFlags()).map(ChipUtil1_17_R1::toNMS);
      Objects.requireNonNull(set);
      var10000.forEach(set::add);
      g.a(set);
      return g;
   }

   public static CustomPathfinder custom(final PathfinderGoal g) {
      return new CustomPathfinder(getEntity(g)) {
         @NotNull
         public Pathfinder.PathfinderFlag[] getFlags() {
            Set<Type> nms = ChipUtil1_17_R1.getFlags(g);
            Pathfinder.PathfinderFlag[] flags = new Pathfinder.PathfinderFlag[nms.size()];
            int i = 0;

            for(Iterator var4 = nms.iterator(); var4.hasNext(); ++i) {
               Type f = (Type)var4.next();
               flags[i] = ChipUtil1_17_R1.fromNMS(f);
            }

            return flags;
         }

         public boolean canStart() {
            return g.a();
         }

         public void start() {
            g.c();
         }

         public void tick() {
            g.e();
         }

         public void stop() {
            g.d();
         }

         public boolean canInterrupt() {
            return g.C_();
         }

         public String getInternalName() {
            return g.getClass().getSimpleName();
         }
      };
   }

   public static BlockPosition getPosWithBlock(net.minecraft.world.level.block.Block block, BlockPosition bp, IBlockAccess g) {
      if (g.getType(bp).a(block)) {
         return bp;
      } else {
         BlockPosition[] bp1 = new BlockPosition[]{new BlockPosition(bp.down()), bp.f(), bp.g(), bp.d(), bp.e(), new BlockPosition(bp.up())};
         BlockPosition[] var4 = bp1;
         int var5 = bp1.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            BlockPosition bps = var4[var6];
            if (g.getType(bps).a(block)) {
               return bps;
            }
         }

         return null;
      }
   }

   public static Location fromNMS(BlockPosition p, World w) {
      return new Location(w, (double)p.getX(), (double)p.getY(), (double)p.getZ());
   }

   private Pathfinder fromNMS(PathfinderGoal g) {
      if (g instanceof CustomGoal1_17_R1) {
         CustomGoal1_17_R1 custom = (CustomGoal1_17_R1)g;
         return custom.getPathfinder();
      } else {
         Mob m = getEntity(g);
         String name = g.getClass().getSimpleName();
         if (name.startsWith("PathfinderGoal")) {
            name = name.replace("PathfinderGoal", "");
            byte var5 = -1;
            switch(name.hashCode()) {
            case -2082980744:
               if (name.equals("MoveThroughVillage")) {
                  var5 = 26;
               }
               break;
            case -2043394768:
               if (name.equals("LookAtTradingPlayer")) {
                  var5 = 24;
               }
               break;
            case -2018211691:
               if (name.equals("BowShoot")) {
                  var5 = 3;
               }
               break;
            case -1949985978:
               if (name.equals("AvoidTarget")) {
                  var5 = 0;
               }
               break;
            case -1946824169:
               if (name.equals("HurtByTarget")) {
                  var5 = 57;
               }
               break;
            case -1811199316:
               if (name.equals("RandomStrollLand")) {
                  var5 = 38;
               }
               break;
            case -1597381388:
               if (name.equals("GotoTarget")) {
                  var5 = 28;
               }
               break;
            case -1553014250:
               if (name.equals("NearestAttackableTargetWitch")) {
                  var5 = 54;
               }
               break;
            case -1552166701:
               if (name.equals("LookAtPlayer")) {
                  var5 = 23;
               }
               break;
            case -1344974194:
               if (name.equals("NearestVillage")) {
                  var5 = 27;
               }
               break;
            case -1268053103:
               if (name.equals("ArrowAttack")) {
                  var5 = 1;
               }
               break;
            case -1045162654:
               if (name.equals("FollowOwner")) {
                  var5 = 17;
               }
               break;
            case -747379341:
               if (name.equals("OwnerHurtTarget")) {
                  var5 = 59;
               }
               break;
            case -536655626:
               if (name.equals("DefendVillage")) {
                  var5 = 56;
               }
               break;
            case -510391834:
               if (name.equals("RandomTargetNonTamed")) {
                  var5 = 60;
               }
               break;
            case -509658694:
               if (name.equals("CatSitOnBed")) {
                  var5 = 7;
               }
               break;
            case -485095825:
               if (name.equals("RandomLookaround")) {
                  var5 = 36;
               }
               break;
            case -455269869:
               if (name.equals("UniversalAngerReset")) {
                  var5 = 50;
               }
               break;
            case -390283999:
               if (name.equals("RandomStroll")) {
                  var5 = 37;
               }
               break;
            case -382685106:
               if (name.equals("StrollVillage")) {
                  var5 = 44;
               }
               break;
            case -354337764:
               if (name.equals("MoveTowardsTarget")) {
                  var5 = 31;
               }
               break;
            case -299992570:
               if (name.equals("EatTile")) {
                  var5 = 11;
               }
               break;
            case -120983506:
               if (name.equals("ZombieAttack")) {
                  var5 = 52;
               }
               break;
            case -34110287:
               if (name.equals("FollowBoat")) {
                  var5 = 15;
               }
               break;
            case 66660:
               if (name.equals("Beg")) {
                  var5 = 2;
               }
               break;
            case 83134:
               if (name.equals("Sit")) {
                  var5 = 43;
               }
               break;
            case 2539434:
               if (name.equals("Raid")) {
                  var5 = 29;
               }
               break;
            case 2599141:
               if (name.equals("Tame")) {
                  var5 = 47;
               }
               break;
            case 64448852:
               if (name.equals("Breed")) {
                  var5 = 6;
               }
               break;
            case 67973692:
               if (name.equals("Float")) {
                  var5 = 14;
               }
               break;
            case 76880471:
               if (name.equals("Panic")) {
                  var5 = 34;
               }
               break;
            case 77003298:
               if (name.equals("Perch")) {
                  var5 = 35;
               }
               break;
            case 80297889:
               if (name.equals("Swell")) {
                  var5 = 46;
               }
               break;
            case 80692992:
               if (name.equals("Tempt")) {
                  var5 = 48;
               }
               break;
            case 83350775:
               if (name.equals("Water")) {
                  var5 = 12;
               }
               break;
            case 105543981:
               if (name.equals("BreakDoor")) {
                  var5 = 4;
               }
               break;
            case 106785232:
               if (name.equals("RestrictSun")) {
                  var5 = 42;
               }
               break;
            case 114436284:
               if (name.equals("MeleeAttack")) {
                  var5 = 25;
               }
               break;
            case 375164042:
               if (name.equals("HorseTrap")) {
                  var5 = 19;
               }
               break;
            case 474057777:
               if (name.equals("NearestAttackableTarget")) {
                  var5 = 53;
               }
               break;
            case 579064978:
               if (name.equals("CrossbowAttack")) {
                  var5 = 8;
               }
               break;
            case 591537067:
               if (name.equals("TradeWithPlayer")) {
                  var5 = 49;
               }
               break;
            case 854846590:
               if (name.equals("StrollVillageGolem")) {
                  var5 = 45;
               }
               break;
            case 883789862:
               if (name.equals("LlamaFollow")) {
                  var5 = 22;
               }
               break;
            case 889064550:
               if (name.equals("FleeSun")) {
                  var5 = 13;
               }
               break;
            case 978358314:
               if (name.equals("OwnerHurtByTarget")) {
                  var5 = 58;
               }
               break;
            case 1176401040:
               if (name.equals("RandomFly")) {
                  var5 = 40;
               }
               break;
            case 1266260792:
               if (name.equals("DoorOpen")) {
                  var5 = 9;
               }
               break;
            case 1347302985:
               if (name.equals("RemoveBlock")) {
                  var5 = 41;
               }
               break;
            case 1441363392:
               if (name.equals("JumpOnBlock")) {
                  var5 = 21;
               }
               break;
            case 1516319002:
               if (name.equals("UseItem")) {
                  var5 = 51;
               }
               break;
            case 1519699752:
               if (name.equals("OcelotAttack")) {
                  var5 = 32;
               }
               break;
            case 1646176076:
               if (name.equals("LeapAtTarget")) {
                  var5 = 20;
               }
               break;
            case 1662055351:
               if (name.equals("OfferFlower")) {
                  var5 = 33;
               }
               break;
            case 1665275668:
               if (name.equals("FollowEntity")) {
                  var5 = 16;
               }
               break;
            case 1789520325:
               if (name.equals("WaterJump")) {
                  var5 = 10;
               }
               break;
            case 1894852641:
               if (name.equals("MoveTowardsRestriction")) {
                  var5 = 30;
               }
               break;
            case 1968126939:
               if (name.equals("FollowParent")) {
                  var5 = 18;
               }
               break;
            case 1997911168:
               if (name.equals("Breath")) {
                  var5 = 5;
               }
               break;
            case 2109091339:
               if (name.equals("RandomSwim")) {
                  var5 = 39;
               }
               break;
            case 2132742007:
               if (name.equals("NearestHealableRaider")) {
                  var5 = 55;
               }
            }

            Object var10000;
            switch(var5) {
            case 0:
               var10000 = new PathfinderAvoidEntity((Creature)m, fromNMS((Class)getObject(g, "f", Class.class), LivingEntity.class), getFloat(g, "c"), getDouble(g, "i"), getDouble(g, "j"), (en) -> {
                  return ((Predicate)getObject(g, "g", Predicate.class)).test(toNMS(en));
               }, (en) -> {
                  return ((Predicate)getObject(g, "h", Predicate.class)).test(toNMS(en));
               });
               break;
            case 1:
               var10000 = new PathfinderRangedAttack(m, getDouble(g, "e"), getFloat(g, "i"), getInt(g, "g"), getInt(g, "h"));
               break;
            case 2:
               var10000 = new PathfinderBeg((Wolf)m, getFloat(g, "d"));
               break;
            case 3:
               var10000 = new PathfinderRangedBowAttack(m, getDouble(g, "b"), (float)Math.sqrt((double)getFloat(g, "d")), getInt(g, "c"));
               break;
            case 4:
               var10000 = new PathfinderBreakDoor(m, getInt(g, "i"), (d) -> {
                  return ((Predicate)getObject(g, "h", Predicate.class)).test(toNMS(d));
               });
               break;
            case 5:
               var10000 = new PathfinderBreathAir((Creature)m);
               break;
            case 6:
               var10000 = new PathfinderBreed((Animals)m, getDouble(g, "g"));
               break;
            case 7:
               var10000 = new PathfinderCatOnBed((Cat)m, getDouble(g, "b"), getInt(g, "l"));
               break;
            case 8:
               var10000 = new PathfinderRangedCrossbowAttack((Pillager)m, getDouble(g, "d"), (float)Math.sqrt((double)getFloat(g, "e")));
               break;
            case 9:
               var10000 = new PathfinderOpenDoor(m, getBoolean(g, "a"));
               break;
            case 10:
               var10000 = new PathfinderDolphinJump((Dolphin)m, getInt(g, "c"));
               break;
            case 11:
               var10000 = new PathfinderEatTile(m);
               break;
            case 12:
               var10000 = new PathfinderFindWater((Creature)m);
               break;
            case 13:
               var10000 = new PathfinderFleeSun((Creature)m, getDouble(g, "e"));
               break;
            case 14:
               var10000 = new PathfinderFloat(m);
               break;
            case 15:
               var10000 = new PathfinderFollowBoat((Creature)m);
               break;
            case 16:
               var10000 = new PathfinderFollowMob(m, getDouble(g, "d"), getFloat(g, "g"), getFloat(g, "i"));
               break;
            case 17:
               var10000 = new PathfinderFollowOwner((Tameable)m, getDouble(g, "h"), getFloat(g, "l"), getFloat(g, "k"), getBoolean(g, "n"));
               break;
            case 18:
               var10000 = new PathfinderFollowParent((Animals)m, getDouble(g, "f"));
               break;
            case 19:
               var10000 = new PathfinderSkeletonTrap((SkeletonHorse)m);
               break;
            case 20:
               var10000 = new PathfinderLeapAtTarget(m, getFloat(g, "c"));
               break;
            case 21:
               var10000 = new PathfinderCatOnBlock((Cat)m, getDouble(g, "b"));
               break;
            case 22:
               var10000 = new PathfinderLlamaFollowCaravan((Llama)m, getDouble(g, "b"));
               break;
            case 23:
               var10000 = new PathfinderLookAtEntity(m, fromNMS((Class)getObject(g, "f", Class.class), LivingEntity.class), getFloat(g, "d"), getFloat(g, "e"), getBoolean(g, "i"));
               break;
            case 24:
               var10000 = new PathfinderLookAtTradingPlayer((AbstractVillager)m);
               break;
            case 25:
               var10000 = new PathfinderMeleeAttack((Creature)m, getDouble(g, "b"), getBoolean(g, "c"));
               break;
            case 26:
               var10000 = new PathfinderMoveThroughVillage((Creature)m, (BooleanSupplier)getObject(g, "b", BooleanSupplier.class), getDouble(g, "b"), getInt(g, "g"), getBoolean(g, "e"));
               break;
            case 27:
               var10000 = new PathfinderRandomStrollThroughVillage((Creature)m, getInt(g, "b"));
               break;
            case 28:
               var10000 = new PathfinderMoveToBlock((Creature)m, (l) -> {
                  return (Boolean)invoke(g, "a", toNMS(l.getWorld()), toNMS(l));
               }, getDouble(g, "b"), getInt(g, "l"), getInt(g, "m"));
               break;
            case 29:
               var10000 = new PathfinderMoveToRaid((Raider)m);
               break;
            case 30:
               var10000 = new PathfinderMoveTowardsRestriction((Creature)m, getDouble(g, "e"));
               break;
            case 31:
               var10000 = new PathfinderMoveTowardsTarget((Creature)m, getDouble(g, "f"), getFloat(g, "g"));
               break;
            case 32:
               var10000 = new PathfinderOcelotAttack(m);
               break;
            case 33:
               var10000 = new PathfinderOfferFlower((IronGolem)m);
               break;
            case 34:
               var10000 = new PathfinderPanic((Creature)m, getDouble(g, "c"));
               break;
            case 35:
               var10000 = new PathfinderRideShoulder((Parrot)m);
               break;
            case 36:
               var10000 = new PathfinderRandomLook(m);
               break;
            case 37:
               var10000 = new PathfinderRandomStroll((Creature)m, getDouble(g, "f"), getInt(g, "g"));
               break;
            case 38:
               var10000 = new PathfinderRandomStrollLand((Creature)m, getDouble(g, "f"), getFloat(g, "j"));
               break;
            case 39:
               var10000 = new PathfinderRandomSwim((Creature)m, getDouble(g, "f"), getInt(g, "g"));
               break;
            case 40:
               var10000 = new PathfinderRandomStrollFlying((Creature)m, getDouble(g, "f"));
               break;
            case 41:
               var10000 = new PathfinderRemoveBlock((Creature)m, CraftMagicNumbers.getMaterial((net.minecraft.world.level.block.Block)getObject(g, "g", net.minecraft.world.level.block.Block.class)), getDouble(g, "b"));
               break;
            case 42:
               var10000 = new PathfinderRestrictSun((Creature)m);
               break;
            case 43:
               var10000 = new PathfinderSit((Tameable)m);
               break;
            case 44:
               var10000 = new PathfinderRandomStrollToVillage((Creature)m, getDouble(g, "f"));
               break;
            case 45:
               var10000 = new PathfinderRandomStrollInVillage((Creature)m, getDouble(g, "f"));
               break;
            case 46:
               var10000 = new PathfinderSwellCreeper((Creeper)m);
               break;
            case 47:
               var10000 = new PathfinderTameHorse((AbstractHorse)m);
               break;
            case 48:
               var10000 = new PathfinderTempt((Creature)m, getDouble(g, "e"), fromNMS((RecipeItemStack)getObject(g, "m", RecipeItemStack.class)));
               break;
            case 49:
               var10000 = new PathfinderTradePlayer((AbstractVillager)m);
               break;
            case 50:
               var10000 = new PathfinderResetAnger(m, getBoolean(g, "c"));
               break;
            case 51:
               var10000 = new PathfinderUseItem(m, fromNMS((net.minecraft.world.item.ItemStack)getObject(g, "b", net.minecraft.world.item.ItemStack.class)), (en) -> {
                  return ((Predicate)getObject(g, "c", Predicate.class)).test(toNMS(en));
               }, fromNMS((SoundEffect)getObject(g, "d", SoundEffect.class)));
               break;
            case 52:
               var10000 = new PathfinderZombieAttack((Zombie)m, getDouble(g, "b"), getBoolean(g, "c"));
               break;
            case 53:
               var10000 = new PathfinderNearestAttackableTarget(m, fromNMS((Class)getObject(g, "a", Class.class), LivingEntity.class), getInt(g, "b"), getBoolean(g, "f"), getBoolean(g, "d"));
               break;
            case 54:
               var10000 = new PathfinderNearestAttackableTargetRaider((Raider)m, fromNMS((Class)getObject(g, "a", Class.class), LivingEntity.class), getInt(g, "b"), true, true, (l) -> {
                  return ((PathfinderTargetCondition)getObject(g, "d", PathfinderTargetCondition.class)).a((EntityLiving)null, toNMS(l));
               });
               break;
            case 55:
               var10000 = new PathfinderNearestHealableRaider((Raider)m, fromNMS((Class)getObject(g, "a", Class.class), LivingEntity.class), true, (l) -> {
                  return ((PathfinderTargetCondition)getObject(g, "d", PathfinderTargetCondition.class)).a((EntityLiving)null, toNMS(l));
               });
               break;
            case 56:
               var10000 = new PathfinderDefendVillage((IronGolem)m);
               break;
            case 57:
               var10000 = new PathfinderHurtByTarget((Creature)m, getEntityTypes((Class[])getObject(g, "i", Class[].class)));
               break;
            case 58:
               var10000 = new PathfinderOwnerHurtByTarget((Tameable)m);
               break;
            case 59:
               var10000 = new PathfinderOwnerHurtTarget((Tameable)m);
               break;
            case 60:
               var10000 = new PathfinderWildTarget((Tameable)m, fromNMS((Class)getObject(g, "a", Class.class), LivingEntity.class), getBoolean(g, "f"), (l) -> {
                  return ((PathfinderTargetCondition)getObject(g, "d", PathfinderTargetCondition.class)).a((EntityLiving)null, toNMS(l));
               });
               break;
            default:
               var10000 = custom(g);
            }

            return (Pathfinder)var10000;
         } else {
            return custom(g);
         }
      }
   }

   public Attribute registerAttribute(NamespacedKey key, double defaultV, double min, double max, boolean client) {
      if (this.existsAttribute(key)) {
         return null;
      } else {
         DedicatedServer server = ((CraftServer)Bukkit.getServer()).getServer();
         IRegistryWritable<AttributeBase> writable = server.getCustomRegistry().b(IRegistry.y);
         ResourceKey<AttributeBase> nmsKey = ResourceKey.a(IRegistry.y, toNMS(key));
         Attribute1_17_R1 att = new Attribute1_17_R1(key, defaultV, min, max, client);
         writable.a(nmsKey, att, Lifecycle.stable());
         return att;
      }
   }

   public boolean existsAttribute(NamespacedKey key) {
      return IRegistry.al.c(toNMS(key));
   }

   public static MinecraftKey toNMS(NamespacedKey key) {
      return CraftNamespacedKey.toMinecraft(key);
   }

   public Attribute getAttribute(NamespacedKey key) {
      AttributeBase a = (AttributeBase)IRegistry.al.get(toNMS(key));
      return !(a instanceof AttributeRanged) ? null : new Attribute1_17_R1((AttributeRanged)a);
   }

   @NotNull
   private AttributeInstance1_17_R1 getOrCreateInstance(Mob m, Attribute a) {
      EntityInsentient nms = toNMS(m);
      AttributeMapBase map = nms.getAttributeMap();
      AttributeBase nmsA = (AttributeBase)IRegistry.al.get(toNMS(a.getKey()));
      AttributeModifiable handle = toNMS(m).getAttributeInstance(nmsA);
      if (handle != null) {
         return new AttributeInstance1_17_R1(a, handle);
      } else {
         try {
            Field attributesF = AttributeMapBase.class.getDeclaredField("b");
            attributesF.setAccessible(true);
            Map<AttributeBase, AttributeModifiable> attributes = (Map)attributesF.get(map);
            handle = new AttributeModifiable(nmsA, (ignored) -> {
            });
            attributes.put(nmsA, handle);
            return new AttributeInstance1_17_R1(a, handle);
         } catch (ReflectiveOperationException var9) {
            ChipUtil.printStackTrace(var9);
            throw new RuntimeException("Failed to create AttributeInstance");
         }
      }
   }

   public AttributeInstance getAttributeInstance(Mob m, Attribute a) {
      AttributeBase var10000 = (AttributeBase)IRegistry.al.get(toNMS(a.getKey()));
      return this.getOrCreateInstance(m, a);
   }

   public static ReputationType toNMS(GossipType t) {
      return ReputationType.a(t.getKey().getKey());
   }

   public static GossipType fromNMS(ReputationType t) {
      return GossipType.getByKey(NamespacedKey.minecraft(t.i));
   }

   public EntityGossipContainer getGossipContainer(Villager v) {
      return new EntityGossipContainer1_17_R1(v);
   }

   public static Entity fromNMS(net.minecraft.world.entity.Entity en) {
      return en.getBukkitEntity();
   }

   public static CombatEntry fromNMS(Mob m, net.minecraft.world.damagesource.CombatEntry en) {
      return new CombatEntry(m, fromNMS(en.a()), en.b(), en.d(), en.c(), en.j(), en.g() == null ? null : CombatLocation.getByKey(NamespacedKey.minecraft(en.g())), en.i() == null ? null : fromNMS(en.i()));
   }

   public static net.minecraft.world.damagesource.CombatEntry toNMS(CombatEntry en) {
      return new net.minecraft.world.damagesource.CombatEntry(toNMS(en.getCause()), en.getCombatTime(), en.getHealthBeforeDamage(), en.getDamage(), en.getLocation().getKey().getKey(), en.getFallDistance());
   }

   public EntityCombatTracker getCombatTracker(Mob m) {
      return new EntityCombatTracker1_17_R1(m);
   }

   public void knockback(EnderDragon a, List<Entity> list) {
      EntityEnderDragon nmsMob = toNMS(a);

      try {
         Method m = EntityEnderDragon.class.getDeclaredMethod("a", List.class);
         m.setAccessible(true);
         m.invoke(nmsMob, list.stream().map(ChipUtil1_17_R1::toNMS).collect(Collectors.toList()));
      } catch (Exception var5) {
         ChipUtil.printStackTrace(var5);
      }

   }

   public static EntityEnderDragon toNMS(EnderDragon dragon) {
      return ((CraftEnderDragon)dragon).getHandle();
   }

   public static Location fromNMS(IPosition p, World w) {
      return new Location(w, p.getX(), p.getY(), p.getZ());
   }

   public DragonPhase fromBukkit(EnderDragon d, Phase phase) {
      EntityEnderDragon nms = toNMS(d);
      Object var10000;
      switch(phase) {
      case CIRCLING:
         var10000 = new DragonControllerHold(nms);
         break;
      case STRAFING:
         var10000 = new DragonControllerStrafe(nms);
         break;
      case FLY_TO_PORTAL:
         var10000 = new DragonControllerLandingFly(nms);
         break;
      case LAND_ON_PORTAL:
         var10000 = new DragonControllerLanding(nms);
         break;
      case LEAVE_PORTAL:
         var10000 = new DragonControllerFly(nms);
         break;
      case BREATH_ATTACK:
         var10000 = new DragonControllerLandedFlame(nms);
         break;
      case SEARCH_FOR_BREATH_ATTACK_TARGET:
         var10000 = new DragonControllerLandedSearch(nms);
         break;
      case ROAR_BEFORE_ATTACK:
         var10000 = new DragonControllerLandedAttack(nms);
         break;
      case CHARGE_PLAYER:
         var10000 = new DragonControllerCharge(nms);
         break;
      case DYING:
         var10000 = new DragonControllerDying(nms);
         break;
      default:
         var10000 = new DragonControllerHover(nms);
      }

      IDragonController i = var10000;
      return new DragonPhase1_17_R1(d, (IDragonController)i);
   }

   public DragonPhase getCurrentPhase(EnderDragon dragon) {
      return new DragonPhase1_17_R1(dragon, toNMS(dragon).getDragonControllerManager().a());
   }

   public void updateActivities(Creature c) {
      EntityCreature nms = toNMS(c);
      if (c instanceof Axolotl) {
         AxolotlAi.a((net.minecraft.world.entity.animal.axolotl.Axolotl)nms);
      }

   }

   public static MemoryModuleType<?> toNMS(Memory<?> mem) {
      return (MemoryModuleType)IRegistry.ar.get(mem instanceof EntityMemory ? new MinecraftKey(mem.getKey().getKey()) : new MinecraftKey(mem.getKey().getNamespace(), mem.getKey().getKey()));
   }

   public void registerMemory(Memory<?> m) {
      DedicatedServer server = ((CraftServer)Bukkit.getServer()).getServer();
      IRegistryWritable<MemoryModuleType<?>> writable = server.getCustomRegistry().b(IRegistry.F);
      ResourceKey<MemoryModuleType<?>> nmsKey = ResourceKey.a(IRegistry.F, toNMS(m.getKey()));
      writable.a(nmsKey, toNMS(m), Lifecycle.stable());
   }

   public boolean existsMemory(Memory<?> m) {
      return m instanceof EntityMemory ? true : IRegistry.ar.c(new MinecraftKey(m.getKey().getNamespace(), m.getKey().getKey()));
   }

   public static Sensor<?> toNMS(me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> s) {
      return (Sensor)(s instanceof SensorDefault1_17_R1 ? ((SensorDefault1_17_R1)s).getHandle() : new Sensor1_17_R1(s));
   }

   public static SensorType<?> toNMSType(me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> s) {
      try {
         Constructor<SensorType> c = SensorType.class.getConstructor(Supplier.class);
         c.setAccessible(true);
         Supplier<Sensor<?>> sup = () -> {
            return toNMS(s);
         };
         return (SensorType)c.newInstance(sup);
      } catch (ReflectiveOperationException var6) {
         Bukkit.getLogger().severe(var6.getMessage());
         StackTraceElement[] var2 = var6.getStackTrace();
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            StackTraceElement st = var2[var4];
            Bukkit.getLogger().severe(st.toString());
         }

         return null;
      }
   }

   public static me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> fromNMS(Sensor<?> type) {
      return (me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor)(type instanceof Sensor1_17_R1 ? ((Sensor1_17_R1)type).getSensor() : new SensorDefault1_17_R1(type));
   }

   public static NamespacedKey fromNMS(MinecraftKey loc) {
      return new NamespacedKey(loc.getNamespace(), loc.getKey());
   }

   public static Memory<?> fromNMS(MemoryModuleType<?> memory) {
      return EntityMemory.getByKey(fromNMS(IRegistry.ar.getKey(memory)));
   }

   public static MemoryModuleType<?> getMemory(NamespacedKey key) {
      return (MemoryModuleType)IRegistry.ar.get(new MinecraftKey(key.getNamespace(), key.getKey()));
   }

   public void registerSensor(me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> s) {
      DedicatedServer server = ((CraftServer)Bukkit.getServer()).getServer();
      IRegistryWritable<SensorType<?>> writable = server.getCustomRegistry().b(IRegistry.G);
      ResourceKey<SensorType<?>> nmsKey = ResourceKey.a(IRegistry.G, toNMS(s.getKey()));
      writable.a(nmsKey, toNMSType(s), Lifecycle.stable());
   }

   public boolean existsSensor(NamespacedKey key) {
      return IRegistry.as.c(new MinecraftKey(key.getNamespace(), key.getKey()));
   }

   public me.darksnakex.villagerfollow.mobchip.ai.sensing.Sensor<?> getSensor(NamespacedKey key) {
      return fromNMS(((SensorType)IRegistry.as.get(toNMS(key))).a());
   }

   public EntitySenses getSenses(Mob m) {
      return new EntitySenses1_17_R1(m);
   }

   public EnderCrystal getNearestCrystal(EnderDragon d) {
      EntityEnderDragon nms = toNMS(d);
      return nms.bY == null ? null : (EnderCrystal)nms.bY.getBukkitEntity();
   }

   public static Position fromNMS(PathPoint point) {
      return new Position(point.a, point.b, point.c);
   }
}
