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

package com.serverworld.worldDataBase.paper;

import com.serverworld.worldDataBase.paper.utils.DebugMessage;
import net.md_5.bungee.api.ChatColor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PaperSQLDatabase {
    private PaperworldUserData paperworldUserData;
    private PaperworldUserDataConfig config;

    public static Connection connection;
    private static String host, database, username, password;
    private static int port;

    public PaperSQLDatabase(PaperworldUserData paperworldUserData){
        this.paperworldUserData = paperworldUserData;
        config = paperworldUserData.config;
        if(config.type().toLowerCase().equals("mysql")){
            DebugMessage.sendInfo("Using mysql");
            MYSQLlogin();
        }else if(config.type().toLowerCase().equals("mariadb")){
            DebugMessage.sendInfo("Using mariadb");
            MYSQLlogin();
        }else {
            DebugMessage.sendWarring(ChatColor.RED + "Not supported this database type");
        }

    }

    public void MYSQLlogin(){
        host = config.host();
        port = config.port();
        database = config.database();
        username = config.username();
        password = config.password();

        try {
            MYSQLopenConnection();
            Statement statement = connection.createStatement();
            //create database
            //useraccountdata
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `worlduserdata_useraccountdata` (`PlayerUUID` char(36), `version` INT, `accountdata` TEXT, `signed` BOOLEAN, PRIMARY KEY(PlayerUUID))");
            //userphoenixdata
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `worlduserdata_userphoenixplayerdata` (`PlayerUUID` char(36), `version` INT, `playerdata` TEXT, PRIMARY KEY(PlayerUUID))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `worlduserdata_ServerResidenceData` ( `id` BIGINT NOT NULL AUTO_INCREMENT , `ResidenceName` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , `CreateTime` BIGINT NOT NULL , `ResidenceData` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , `OwnerUUID` VARCHAR(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL , `Version` INT NOT NULL , PRIMARY KEY (`id`), INDEX (`ResidenceName`), INDEX (`CreateTime`), INDEX (`OwnerUUID`)) ENGINE = InnoDB;");

            statement.executeUpdate("ALTER TABLE `worlduserdata_userphoenixplayerdata` ADD COLUMN IF NOT EXISTS `playerhome` TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL AFTER `playerdata`;");
            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS `worldprofile_userlastlocation` (`PlayerUUID` char(36), `Server` varchar(8), PRIMARY KEY(PlayerUUID),INDEX (Lang))");
        }catch (Exception e){
            e.printStackTrace();
            DebugMessage.sendWarring(ChatColor.RED + "Error while connection to database");
        }
        try {
            MYSQLopenConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void MYSQLopenConnection() throws SQLException, ClassNotFoundException {
        if (connection != null && !connection.isClosed()) {
            DebugMessage.sendInfo(ChatColor.GREEN + "Connected to database!");
            return;
        }

        synchronized (this) {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database+"?autoReconnect=true&characterEncoding=utf-8", this.username, this.password);
        }
    }

    public static Connection getConnection(){
        try{
            if (connection != null && !connection.isClosed()) {
                return connection;
            }
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database+"?autoReconnect=true&characterEncoding=utf-8", username, password);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}