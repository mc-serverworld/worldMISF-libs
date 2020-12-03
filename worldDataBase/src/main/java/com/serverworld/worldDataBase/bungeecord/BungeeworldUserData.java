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

package com.serverworld.worldDataBase.bungeecord;

import com.serverworld.worldDataBase.bungeecord.Listeners.PlayerLogin;
import com.serverworld.worldDataBase.bungeecord.commands.SignCommand;
import com.serverworld.worldDataBase.api.query.ConnectionManager;
import com.serverworld.worldDataBase.api.query.ServerResidenceInquirer;
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

public class BungeeworldUserData extends Plugin {

    private File file;
    public static BungeeworldUserDataConfig config;
    private BungeeSQLDatabase database;
    public static Configuration configuration;
    public static Connection connection;
    private static BungeeworldUserData bungeeworldUserData;
    @Override
    public void onEnable() {
        setupconfig();
        bungeeworldUserData = this;
        database = new BungeeSQLDatabase(this);
        connection = database.connection;
        new PlayerLogin(this,this);
        getLogger().info("Yay! It loads!");
        getLogger().info("Helloworld");

        getProxy().getPluginManager().registerCommand(this,new SignCommand(this));
        syncConnection();
    }

    public void setupconfig(){
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
        config = new BungeeworldUserDataConfig(this);
    }

    public void syncConnection() {
        BungeeworldUserData.getInstance().getProxy().getScheduler().schedule(BungeeworldUserData.getInstance(), new Runnable() {
            @Override
            public void run() {
                ConnectionManager.setConnection(BungeeSQLDatabase.getConnection());

                ServerResidenceInquirer.isExist("check connection");
            }
        }, 0, 5, TimeUnit.MINUTES);
    }

    public static BungeeworldUserData getInstance(){
        return bungeeworldUserData;
    }
}
