/*
 *
 *  * WorldMISF - cms of mc-serverworld
 *  * Copyright (C) 2019-2020 mc-serverworld
 *  *
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *  
 */

package com.serverworld.worldDataBase.storge;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.serverworld.worldDataBase.bungeecord.BungeeworldUserData;
import com.serverworld.worldDataBase.bungeecord.BungeeworldUserDataConfig;
import com.serverworld.worldDataBase.bungeecord.uitls.DebugMessage;
import com.serverworld.worldDataBase.config.worldDataBaseConfig;
import net.md_5.bungee.api.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class MariaDB {

    public static Connection connection;
    private static String host, database, username, password,set;
    private int port;

    public MariaDB(BungeeworldUserData bungeeworldUserData){
        switch (worldDataBaseConfig.getDataBaseType().toLowerCase()){
            default: System.out.println(Ansi.colorize("Not supported this database type",Attribute.RED_TEXT()));
            case "mariadb": System.out.println(Ansi.colorize("Using mariadb",Attribute.YELLOW_TEXT()));
        }

    }

    public void MariaDBlogin(){
        host = config.host();
        port = config.port();
        database = config.database();
        username = config.username();
        password = config.password();
        set="";

        try {
            MariaDBLopenConnection();
            Statement statement = connection.createStatement();
            //create database
            //useraccountdata
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `worlduserdata_useraccountdata` (`PlayerUUID` char(36), `version` INT, `accountdata` TEXT, `signed` BOOLEAN, PRIMARY KEY(PlayerUUID))");
            //userphoenixdata
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `worlduserdata_userphoenixplayerdata` (`PlayerUUID` char(36), `version` INT, `playerdata` TEXT, PRIMARY KEY(PlayerUUID))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `worlduserdata_ServerResidenceData` ( `id` BIGINT NOT NULL AUTO_INCREMENT , `ResidenceName` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , `CreateTime` BIGINT NOT NULL , `ResidenceData` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , `OwnerUUID` VARCHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , `Version` INT NOT NULL , PRIMARY KEY (`id`), INDEX (`ResidenceName`), INDEX (`CreateTime`), INDEX (`OwnerUUID`)) ENGINE = InnoDB;");

            statement.executeUpdate("ALTER TABLE `worlduserdata_userphoenixplayerdata` ADD COLUMN IF NOT EXISTS `playerhome` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL AFTER `playerdata`;");
        }catch (Exception e){
            e.printStackTrace();
            DebugMessage.sendWarring(ChatColor.RED + "Error while connection to database");
        }
        try {
            if(MariaDBLopenConnection())
                System.out.println(Ansi.colorize("Connected to database!", Attribute.GREEN_TEXT()));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean MariaDBLopenConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            DebugMessage.sendInfo(ChatColor.GREEN + "Connected to database!");
            return true;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return true;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database+"?" + set, this.username, this.password);
        }
    }

    public static Connection getConnection(){
        try {
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            Class.forName("com.mysql.jdbc.Driver");
            BungeeworldUserDataConfig config = BungeeworldUserData.config;
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + "/" + database+ "?" + set, username, password);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }


}