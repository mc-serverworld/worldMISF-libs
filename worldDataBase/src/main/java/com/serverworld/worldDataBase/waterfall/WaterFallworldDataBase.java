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

package com.serverworld.worldDataBase.waterfall;

import com.serverworld.worldDataBase.api.query.ConnectionManager;
import com.serverworld.worldDataBase.api.query.ServerResidenceInquirer;
import com.serverworld.worldDataBase.storge.MariaDB;
import com.serverworld.worldDataBase.waterfall.Listeners.PlayerLogin;
import com.serverworld.worldDataBase.waterfall.commands.SignCommand;
import com.serverworld.worldDataBase.config.worldDataBaseConfig;
import com.serverworld.worldDataBase.waterfall.uitls.DebugMessage;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class WaterFallworldDataBase extends Plugin {

    private File file;
    public static Configuration configuration;
    public static Connection connection;
    private static WaterFallworldDataBase waterFallworldDataBase;
    @Override
    public void onEnable() {
        LoadConfig();
        waterFallworldDataBase = this;
        new PlayerLogin(this,this);
        getProxy().getPluginManager().registerCommand(this,new SignCommand(this));
        syncConnection();
    }

    public void LoadConfig(){
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        worldDataBaseConfig.setApiVersion(configuration.getInt("configinfo.api-version"));
        worldDataBaseConfig.setDebug(configuration.getBoolean("configinfo.debug"));
        worldDataBaseConfig.setDataBaseType(configuration.getString("database.type"));
        worldDataBaseConfig.setHost(configuration.getString("database.host"));
        worldDataBaseConfig.setPort(configuration.getInt("database.port"));
        worldDataBaseConfig.setDataBase(configuration.getString("database.database"));
        worldDataBaseConfig.setUserName(configuration.getString("database.username"));
        worldDataBaseConfig.setPassword(configuration.getString("database.password"));
    }

    public void setSQL(){
        switch (worldDataBaseConfig.getDataBaseType().toLowerCase()){
            default:
                DebugMessage.sendInfo(ChatColor.RED + "Not supported this database type");
            case "mariadb": {
                DebugMessage.sendInfo(ChatColor.YELLOW + "Using mariadb");
                MariaDB mariaDB = new MariaDB();
            }
        }
    }

    public void syncConnection() {
        WaterFallworldDataBase.getInstance().getProxy().getScheduler().schedule(WaterFallworldDataBase.getInstance(), new Runnable() {
            @Override
            public void run() {
                switch (worldDataBaseConfig.getDataBaseType().toLowerCase()){
                    default:{
                        DebugMessage.sendInfo(ChatColor.RED + "Not supported this database type");
                        return;
                    }
                    case "mariadb": {
                        DebugMessage.sendInfo(ChatColor.YELLOW + "Using mariadb");
                        ConnectionManager.setConnection(MariaDB.getConnection());
                    }
                }
                ServerResidenceInquirer.isExist("check connection");
            }
        }, 0, 5, TimeUnit.MINUTES);
    }

    public static WaterFallworldDataBase getInstance(){
        return waterFallworldDataBase;
    }
}
