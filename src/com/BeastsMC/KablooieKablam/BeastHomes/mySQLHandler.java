package com.BeastsMC.KablooieKablam.BeastHomes;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Created by Zane on 12/24/14.
 * DO NOT CALL METHODS SYNCHRONOUSLY
 */
public class mySQLHandler {

    private static final int MAX_RETRIES = 3;

    private final BeastHomes beasthomes;
    private final String url;
    private final String username;
    private final String password;
    private Connection conn;

    private HashMap<HomeSQLQueries, PreparedStatement> pstmts;

    public mySQLHandler(BeastHomes beasthomes, String host, int i, String database, String username, String password) {
        this.beasthomes = beasthomes;
        this.username = username;
        this.password = password;
        this.url = String.format("jdbc:mysql://%s:%i/%s", host, i, database);

        if (!openConnection()) {
            beasthomes.getLogger().severe("Could not initialize connection to MySQL! Make sure the database info is correct.");
            beasthomes.getServer().getPluginManager().disablePlugin(beasthomes);
        }

        if (!createTables()) {
            beasthomes.getLogger().severe("Could not create tables! Make sure the database info is correct.");
            beasthomes.getServer().getPluginManager().disablePlugin(beasthomes);
        }

        if (!prepareStatements()) {
            beasthomes.getLogger().severe("Could not create tables! Make sure the database info is correct.");
            beasthomes.getServer().getPluginManager().disablePlugin(beasthomes);
        }

    }



    private boolean openConnection() {
        int tries = 0;
        boolean success = false;
        while (!success & tries < MAX_RETRIES) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                this.conn = DriverManager.getConnection(url, username, password);
                success = true;
            } catch (SQLException e) {
                tries++;
                beasthomes.getLogger().severe("Could not connect to database! SQLException");
                beasthomes.getLogger().severe("Reattempting connection. Current retries: " + tries + "; Max: " + MAX_RETRIES);
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                tries = MAX_RETRIES; //Exception will not be resolved by retrying
                beasthomes.getLogger().severe("Could not connect to database! Class not found");
                e.printStackTrace();
            }
        }
        return success;
    }

    private boolean createTables() {
        try {
            getPreparedStatement(HomeSQLQueries.HOME_TABLE_DEFINITION).executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean prepareStatements() {
        boolean ret = true;
        for (HomeSQLQueries type : HomeSQLQueries.values()) {
            ret = ret && prepareStatement(type);
        }
        return ret;
    }

    private boolean prepareStatement(HomeSQLQueries type) {
        try {
            if (conn.isClosed() && !openConnection()) {
                return false;
            }
            if (pstmts.containsKey(type)) {
                if (pstmts.get(type).isClosed()) {
                    PreparedStatement stmt = conn.prepareStatement(type.query);
                    pstmts.put(type, stmt);
                }
            } else {
                PreparedStatement stmt = conn.prepareStatement(type.query);
                pstmts.put(type, stmt);
            }
        } catch (SQLException e) {
            beasthomes.getLogger().severe("UNABLE TO PREPARE STATEMENT: " + type.query);
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private PreparedStatement getPreparedStatement(HomeSQLQueries type) {
        PreparedStatement stmt;
        try {
            if (pstmts.get(type).isClosed()) {
                prepareStatement(type);
            }
            return pstmts.get(type);
        } catch (SQLException e) {
            return null;
        }
    }

    //methods
    public int getSetHomes(Player player) {
        int sethomes = 0;
        //mySQL gets number of set homes
        return sethomes;
    }

    public Location getHomeLocation(Player player) {
        Location location = null;
        //mySQL gets world, XYZ, yaw, and pitch of home for player
        return location;
    }

    public Location getHomeLocation(Player player, String homename) {
        Location location = null;
        //mySQL gets world, XYZ, yaw, and pitch of home for player
        return location;
    }

    public List<String> getSetHomeNames(Player player) {
        List<String> sethomenames = new ArrayList<String>();
        //mySQL adds each home name to the list
        return sethomenames;
    }
}