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

package com.serverworld.worldSocketX.paperspigot.util;

import com.serverworld.worldSocket.paperspigot.worldSocket;
import net.md_5.bungee.api.ChatColor;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class checker {
    static worldSocket worldsocket;
    static ConcurrentLinkedQueue<String> list = new ConcurrentLinkedQueue<>();
    public checker(worldSocket worldsocket){
        this.worldsocket = worldsocket;
        worldsocket.getServer().getScheduler().scheduleSyncRepeatingTask(worldsocket, new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    list.add("CHECKING:" + new Date().getTime());
                    messager.sendmessage("CONNECTCHECK");
                    if (worldsocket.config.debug())
                        worldsocket.getLogger().info("checking connection");

                    if (!list.isEmpty()) {
                        if(list.size()>3){
                            worldsocket.reconnect();
                            list.clear();
                        }
                    }
                }
            }
        }, 0L, worldsocket.config.checkrate());
    }

    public void clearlist(){
        synchronized (list) {
            list.clear();
            if (worldsocket.config.debug())
                worldsocket.getLogger().info(ChatColor.GREEN + "connection checked");
        }
    }
}
