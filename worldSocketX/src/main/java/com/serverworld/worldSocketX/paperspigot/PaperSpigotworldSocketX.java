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

package com.serverworld.worldSocketX.paperspigot;

import com.serverworld.worldSocket.paperspigot.commands.worldSocketCommands;
import com.serverworld.worldSocket.paperspigot.socket.SSLsocketclient;
import com.serverworld.worldSocket.paperspigot.socket.eventsender;
import com.serverworld.worldSocket.paperspigot.socket.socketclient;
import com.serverworld.worldSocket.paperspigot.util.checker;
import com.serverworld.worldSocket.paperspigot.util.messager;
import com.serverworld.worldSocketX.config.worldSocketXConfig;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;


public class PaperSpigotworldSocketX extends JavaPlugin {

    public worldSocketconfig config;
    public worldSocketCommands commands;
    public eventsender eventsender;
    public socketclient socketclient;
    public SSLsocketclient SSLsocketclient;
    public messager messager;
    public checker checker;
    private static PaperSpigotworldSocketX paperSpigotworldSocketX;


    public void onLoad() {
        config = new worldSocketconfig(this);
    }

    @Override
    public void onEnable() {
        paperSpigotworldSocketX = this;
        loadConfig();

        eventsender = new eventsender(this);
        if(config.useSSL()){
            SSLsocketclient = new SSLsocketclient(this);
            SSLsocketclient.startlogin();
        }else {
            socketclient = new socketclient(this);
            socketclient.startlogin();

        }

        messager = new messager(this);
        checker = new checker(this);
        commands = new worldSocketCommands(this);
    }
    public void sendmessage(String msg){
        if(config.useSSL()){
            SSLsocketclient.sendmessage(msg);
        }else {
            socketclient.sendmessage(msg);
        }
    }
    public void reconnect(){
        getLogger().warning(ChatColor.RED + "Can't connect to socket server!");
        getLogger().info(ChatColor.YELLOW + "Reconnecting now");
        if(config.useSSL()){
            this.SSLsocketclient = new SSLsocketclient(this);
            SSLsocketclient.startlogin();
        }else {
            this.socketclient = new socketclient(this);
            socketclient.startlogin();
        }

    }

    public void loadConfig(){
        saveDefaultConfig();
        reloadConfig();
        if(UUID.fromString(getConfig().getString("client.uuid"))instanceof UUID)
            getConfig().set("client.uuid",UUID.randomUUID());
        saveConfig();
        reloadConfig();
        worldSocketXConfig.setApiVersion(getConfig().getInt("configinfo.api-version"));
        worldSocketXConfig.setDebug(getConfig().getBoolean("configinfo.debug"));

        worldSocketXConfig.setPort(getConfig().getInt("general.port"));
        worldSocketXConfig.setThreads(getConfig().getInt("socketserver.threads"));

        worldSocketXConfig.setUUID(UUID.fromString(getConfig().getString("client.uuid")));
        worldSocketXConfig.setHost(getConfig().getString("client.host"));
        worldSocketXConfig.setCheckRate(getConfig().getInt("client.check-rate"));

        worldSocketXConfig.setKeyStoreFile(getConfig().getString("SSL.keyStore_file"));
        worldSocketXConfig.setTrustStoreFile(getConfig().getString("SSL.trustStore_file"));
        worldSocketXConfig.setKeyStorePassword(getConfig().getString("SSL.keyStorePassword"));
        worldSocketXConfig.setTrustStorePassword(getConfig().getString("SSL.trustStorePassword"));
    }

    public static PaperSpigotworldSocketX getInstance(){
        return paperSpigotworldSocketX;
    }
}
