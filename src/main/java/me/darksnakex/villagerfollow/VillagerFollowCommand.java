package me.darksnakex.villagerfollow;

import me.darksnakex.villagerfollow.updater.UpdateChecker;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class VillagerFollowCommand implements CommandExecutor {
   private final VillagerFollow plugin;

   public VillagerFollowCommand(VillagerFollow plugin) {
      this.plugin = plugin;
   }

   public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
      FileConfiguration messagesConfig = this.plugin.getMessages();
      if (args.length > 0 && commandSender.hasPermission("villagerfollow.use")) {
         if (args[0].equalsIgnoreCase("version") && commandSender.hasPermission("villagerfollow.version")) {
            commandSender.sendMessage(this.plugin.nombre + "By DarkSnakeX - Version: " + this.plugin.version);
            (new UpdateChecker(this.plugin, 111553)).getVersion((version) -> {
               if (!this.plugin.version.equalsIgnoreCase(version)) {
                  TextComponent message = new TextComponent(this.plugin.nombre + messagesConfig.getString("version-command.update-available"));
                  message.setClickEvent(new ClickEvent(Action.OPEN_URL, "https://modrinth.com/plugin/villagerfollow"));
                  message.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder("Click here to download")).create()));
                  commandSender.spigot().sendMessage(message);
                  if (commandSender instanceof Player) {
                     ((Player)commandSender).playSound(((Player)commandSender).getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0F, 1.0F);
                  }
               }

            });
            return true;
         }

         if (args[0].equalsIgnoreCase("enable") && commandSender.hasPermission("villagerfollow.enable")) {
            commandSender.sendMessage(this.plugin.nombre + messagesConfig.getString("enable-command"));
            this.plugin.getConfig().set("Config.villager-follow", true);
            this.plugin.registerConfig();
            return true;
         }

         if (args[0].equalsIgnoreCase("disable") && commandSender.hasPermission("villagerfollow.disable")) {
            commandSender.sendMessage(this.plugin.nombre + messagesConfig.getString("disable-command"));
            this.plugin.getConfig().set("Config.villager-follow", false);
            this.plugin.registerConfig();
            return true;
         }

         if (args[0].equalsIgnoreCase("reload") && commandSender.hasPermission("villagerfollow.reload")) {
            this.plugin.registerConfig();
            this.plugin.reloadConfig();
            this.plugin.reloadMessages();
            this.plugin.registerprefix();
            commandSender.sendMessage(this.plugin.nombre + messagesConfig.getString("reload-command"));
            return true;
         }

         if (args[0].equalsIgnoreCase("help") && commandSender.hasPermission("villagerfollow.help")) {
            commandSender.sendMessage(this.plugin.nombre + "\n" + ChatColor.GREEN + "/vf reload: " + ChatColor.GRAY + messagesConfig.getString("help-command.message-reload") + "\n" + ChatColor.GREEN + "/vf enable: " + ChatColor.GRAY + messagesConfig.getString("help-command.message-enable") + "\n" + ChatColor.GREEN + "/vf disable: " + ChatColor.GRAY + messagesConfig.getString("help-command.message-disable") + "\n" + ChatColor.GREEN + "/vf version: " + ChatColor.GRAY + messagesConfig.getString("help-command.message-version"));
            return true;
         }
      }

      return false;
   }
}
