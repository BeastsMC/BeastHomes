package com.beastsmc.KablooieKablam.BeastHomes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class sethomeCommandExecutor implements CommandExecutor {
    BeastHomes plugin = new BeastHomes();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length > 1) {
            sender.sendMessage(ChatColor.RED + "Use /sethome or /sethome [name]");
            return true;
        }
        if (args.length == 1) {
            if (BeastHomes.mysql.getSetHomes(player) >= plugin.getMaxHomes(player)) {
                sender.sendMessage(ChatColor.RED + "You have already set your maximum of " + plugin.getMaxHomes(player) + " homes.");
                return true;
            }
            //make sure args[0] is only alphanumeric characters
            return true;
        }
        return true;
    }

}
