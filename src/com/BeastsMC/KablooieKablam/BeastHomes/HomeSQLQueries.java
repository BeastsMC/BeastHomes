package com.BeastsMC.KablooieKablam.BeastHomes;

/**
 * Created by Zane on 12/28/14.
 */
public enum HomeSQLQueries {
    HOME_TABLE_DEFINITION("CREATE TABLE IF NOT EXISTS homes (uuid CHAR(36) NOT NULL, name VARCHAR(255), x DOUBLE NOT NULL, y DOUBLE NOT NULL, z DOUBLE NOT NULL, yaw DOUBLE NOT NULL, pitch DOUBLE NOT NULL, world VARCHAR(255) NOT NULL, PRIMARY KEY(uuid))");

    public String query;

    HomeSQLQueries(String query) {
        this.query = query;
    }
}