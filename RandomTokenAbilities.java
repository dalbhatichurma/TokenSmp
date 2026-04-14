package com.randomtoken;

import com.randomtoken.abilities.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomTokenAbilities extends JavaPlugin implements Listener {
    private TokenManager tokenManager;
    private AbilityManager abilityManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        tokenManager = new TokenManager(this);
        abilityManager = new AbilityManager(this, tokenManager);
        
        getServer().getPluginManager().registerEvents(this, this);
        abilityManager.registerAbilities();
        
        getLogger().info("RandomTokenAbilities enabled!");
    }

    @Override
    public void onDisable() {
        tokenManager.saveData();
        abilityManager.cleanup();
        getLogger().info("RandomTokenAbilities disabled!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!tokenManager.hasToken(player.getUniqueId())) {
            Token token = tokenManager.getRandomToken();
            tokenManager.setToken(player.getUniqueId(), token);
            player.sendMessage(ChatColor.GOLD + "You received: " + token.getDisplayName());
            player.sendMessage(ChatColor.GRAY + token.getDescription());
        }
        abilityManager.applyPassiveEffects(player);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("token")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use this command!");
                return true;
            }
            Player player = (Player) sender;
            Token token = tokenManager.getRandomToken();
            tokenManager.setToken(player.getUniqueId(), token);
            player.sendMessage(ChatColor.GOLD + "You received: " + token.getDisplayName());
            player.sendMessage(ChatColor.GRAY + token.getDescription());
            abilityManager.applyPassiveEffects(player);
            return true;
        }

        if (command.getName().equalsIgnoreCase("mytoken")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use this command!");
                return true;
            }
            Player player = (Player) sender;
            Token token = tokenManager.getToken(player.getUniqueId());
            if (token == null) {
                player.sendMessage(ChatColor.RED + "You don't have a token yet!");
            } else {
                player.sendMessage(ChatColor.GOLD + "Your token: " + token.getDisplayName());
                player.sendMessage(ChatColor.GRAY + token.getDescription());
            }
            return true;
        }

        if (command.getName().equalsIgnoreCase("resettoken")) {
            if (!sender.hasPermission("randomtoken.admin")) {
                sender.sendMessage(ChatColor.RED + "You don't have permission!");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Usage: /resettoken <player>");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found!");
                return true;
            }
            tokenManager.removeToken(target.getUniqueId());
            sender.sendMessage(ChatColor.GREEN + "Reset token for " + target.getName());
            target.sendMessage(ChatColor.YELLOW + "Your token has been reset!");
            return true;
        }

        return false;
    }

    public TokenManager getTokenManager() {
        return tokenManager;
    }

    public AbilityManager getAbilityManager() {
        return abilityManager;
    }
}
