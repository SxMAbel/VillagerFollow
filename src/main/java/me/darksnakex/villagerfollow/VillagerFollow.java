package me.darksnakex.villagerfollow;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import me.darksnakex.villagerfollow.bukkit.Metrics;
import me.darksnakex.villagerfollow.interactions.VillagerInteractionDropEmerald;
import me.darksnakex.villagerfollow.interactions.VillagerInteractionGiveEmerald;
import me.darksnakex.villagerfollow.interactions.VillagerInteractionHandEmerald;
import me.darksnakex.villagerfollow.updater.NotifyAdmin;
import me.darksnakex.villagerfollow.updater.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class VillagerFollow extends JavaPlugin {
   public String rutaConfig;
   private YamlConfiguration messages = null;
   private File messagesFile = null;
   public final PluginDescriptionFile pdffile = this.getDescription();
   public final String version;
   public String nombre;
   public static boolean isSpigot = true;

   public VillagerFollow() {
      this.version = this.pdffile.getVersion();
      this.nombre = ChatColor.YELLOW + "[" + ChatColor.GREEN + this.pdffile.getName() + ChatColor.YELLOW + "] " + ChatColor.GRAY;
   }

   public void onEnable() {
      this.registerprefix();
      Bukkit.getConsoleSender().sendMessage(this.nombre + "has been enabled");
      this.registerMessages();
      this.registrosdecomandos();
      this.registrareventos();
      this.registerConfig();
      this.checkMissingConfigKeys();
      if (isRunningOnSpigot()) {
         Bukkit.getConsoleSender().sendMessage(this.nombre + "Using Spigot version...");
      } else {
         Bukkit.getConsoleSender().sendMessage(this.nombre + "Using Paper version...");
         isSpigot = false;
      }

      new Metrics(this, 19294);
      (new UpdateChecker(this, 111553)).getVersion((version) -> {
         if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
            Bukkit.getConsoleSender().sendMessage(this.nombre + "There is not a new update available.");
         } else {
            Bukkit.getConsoleSender().sendMessage(this.nombre + "There is a new update available.");
         }

      });
   }

   public static boolean isRunningOnSpigot() {
      return !Bukkit.getServer().getName().equalsIgnoreCase("Paper");
   }

   public void onDisable() {
      Bukkit.getConsoleSender().sendMessage(this.nombre + "has been disabled");
   }

   public void registrosdecomandos() {
      this.getCommand("villagerfollow").setExecutor(new VillagerFollowCommand(this));
   }

   public void registrareventos() {
      PluginManager pm = this.getServer().getPluginManager();
      pm.registerEvents(new VillagerInteractionHandEmerald(this), this);
      pm.registerEvents(new NotifyAdmin(this), this);
      pm.registerEvents(new VillagerInteractionDropEmerald(this), this);
      pm.registerEvents(new VillagerInteractionGiveEmerald(this), this);
   }

   public void registerConfig() {
      VillagerInteractionHandEmerald.onPluginReload();
      VillagerInteractionDropEmerald.onPluginReload2();
      File config = new File(this.getDataFolder(), "config.yml");
      this.rutaConfig = config.getPath();
      if (!config.exists()) {
         this.getConfig().options().copyDefaults(true);
         this.saveConfig();
      }

   }

   public FileConfiguration getMessages() {
      if (this.messages == null) {
         this.reloadMessages();
      }

      return this.messages;
   }

   public void reloadMessages() {
      if (this.messages == null) {
         this.messagesFile = new File(this.getDataFolder(), "messages.yml");
      }

      this.messages = YamlConfiguration.loadConfiguration(this.messagesFile);

      try {
         Reader defConfigStream = new InputStreamReader(this.getResource("messages.yml"), "UTF8");
         if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            this.messages.setDefaults(defConfig);
         }
      } catch (UnsupportedEncodingException var3) {
         var3.printStackTrace();
      }

   }

   public void saveMessages() {
      try {
         this.messages.save(this.messagesFile);
      } catch (IOException var2) {
         var2.printStackTrace();
      }

   }

   public void registerMessages() {
      this.messagesFile = new File(this.getDataFolder(), "messages.yml");
      if (!this.messagesFile.exists()) {
         this.saveResource("messages.yml", false);
      }

   }

   public void registerprefix() {
      FileConfiguration messagesConfig = this.getMessages();
      this.nombre = ChatColor.translateAlternateColorCodes('&', messagesConfig.getString("prefix") + " §7");
   }

   private void checkMissingConfigKeys() {
      FileConfiguration config = this.getConfig();
      boolean modificado = false;
      if (!config.isSet("Config.check-update")) {
         config.set("Config.check-update", true);
         modificado = true;
      }

      if (!config.isSet("Config.villager-follow")) {
         config.set("Config.villager-follow", true);
         modificado = true;
      }

      if (!config.isSet("Config.villager-follow-player")) {
         config.set("Config.villager-follow-player", true);
         modificado = true;
      }

      if (!config.isSet("Config.villager-follow-radius")) {
         config.set("Config.villager-follow-radius", 12);
         modificado = true;
      }

      if (!config.isSet("Config.villager-allow-goto-emerald")) {
         config.set("Config.villager-allow-goto-emerald", true);
         modificado = true;
      }

      if (!config.isSet("Config.villager-allow-catch-emerald")) {
         config.set("Config.villager-allow-catch-emerald", true);
         modificado = true;
      }

      if (!config.isSet("Config.villager-pay-follow")) {
         config.set("Config.villager-pay-follow", true);
         modificado = true;
      }

      if (!config.isSet("Config.villager-pay-follow-time")) {
         config.set("Config.villager-pay-follow-time", 120);
         modificado = true;
      }

      if (!config.isSet("Config.villager-allow-catch-emerald-heal")) {
         config.set("Config.villager-allow-catch-emerald-heal", true);
         modificado = true;
      }

      if (modificado) {
         this.saveConfig();
      }

   }
}
