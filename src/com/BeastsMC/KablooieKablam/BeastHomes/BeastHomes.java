package com.beastsmc.KablooieKablam.BeastHomes;


import java.sql.SQLException;


import org.bukkit.Bukkit;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.beastsmc.KablooieKablam.BeastHomes.mySQLHandler;


public class BeastHomes extends JavaPlugin {
    public static mySQLHandler mysql;

    public void onEnable() {
        this.saveDefaultConfig();
        setupMySQL();
    }

    private void setupMySQL() {
        this.mysql = new mySQLHandler(this,
                getConfig().getString("mysql.host"),
                (short) getConfig().getInt("mysql.port"),
                getConfig().getString("mysql.database"),
                getConfig().getString("mysql.username"),
                getConfig().getString("mysql.password")
        );
    }

    public int getMaxHomes(Player player) {
        int maxhomes = 0;
        if (player.hasPermission("ranks.adventurer")) {
            maxhomes = maxhomes + this.getConfig().getInt("adventurer");
        }
        if (player.hasPermission("ranks.hero")) {
            maxhomes = maxhomes + this.getConfig().getInt("hero");
            return maxhomes;
        }
        if (player.hasPermission("ranks.patron")) {
            maxhomes = maxhomes + this.getConfig().getInt("patron");
            return maxhomes;
        }
        if (player.hasPermission("ranks.sponsor")) {
            maxhomes = maxhomes + this.getConfig().getInt("sponsor");
            return maxhomes;
        }
        if (player.hasPermission("ranks.supporter")) {
            maxhomes = maxhomes + this.getConfig().getInt("supporter");
            return maxhomes;
        }
        return maxhomes;
    }
}
