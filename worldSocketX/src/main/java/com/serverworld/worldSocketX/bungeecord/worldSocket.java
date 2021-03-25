/*
 *
 *  * WorldMISF-lib: library and basic component of mc-serverworld
 *  * Copyright (C) 2020-2021 mc-serverworld
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

package com.serverworld.worldSocketX.bungeecord;

import com.serverworld.worldSocket.bungeecord.Listeners.Messagecoming;
import com.serverworld.worldSocket.bungeecord.commands.*;
import com.serverworld.worldSocket.bungeecord.socket.SSLsocketserver;
import com.serverworld.worldSocket.bungeecord.socket.socketserver;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class worldSocket extends Plugin {
    //config
    public static Configuration configuration;
    public worldSocketXConfig config;
    private static worldSocket worldSocket;

    //public socketserver socketserver;
    private SSLsocketserver SSLsocketserver;

    @Override
    public void onEnable() {
        worldSocket = this;
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
        config = new worldSocketconfig(this);
        if(config.useSSL()){
            SSLsocketserver = new SSLsocketserver(this);
            SSLsocketserver.start();
        }else {
            socketserver = new socketserver(this);
            socketserver.start();
        }
        //getProxy().getPluginManager().registerCommand(this,new worldSocketcommands(this));
        new Messagecoming(this);
    }
    public void sendmessage(String msg){
        if(config.useSSL()){
            SSLsocketserver.sendmessage(msg);
        }else {
            socketserver.sendmessage(msg);
        }
    }
    public static worldSocket getInstance(){return worldSocket;}
}
