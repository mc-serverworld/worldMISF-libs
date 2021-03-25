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

package com.serverworld.worldSocketX.paperspigot.socket;

import com.serverworld.worldSocket.paperspigot.events.MessagecomingEvent;
import com.serverworld.worldSocket.paperspigot.worldSocket;

import java.util.concurrent.ConcurrentLinkedQueue;

public class eventsender {
    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
    public eventsender(worldSocket worldsocket){
        worldsocket.getServer().getScheduler().scheduleSyncRepeatingTask(worldsocket, new Runnable() {
            @Override
            public void run() {
                synchronized (queue) {
                    if (!queue.isEmpty()) {
                        for (String stuff : queue) {
                            MessagecomingEvent messagecomingEvent = new MessagecomingEvent(stuff);
                            worldsocket.getServer().getPluginManager().callEvent(messagecomingEvent);
                        }
                        queue.clear();
                    }
                }
            }
        }, 0L, 20L);
    }
    public void addeventqueue(String message){
        queue.add(message);
    }

}
