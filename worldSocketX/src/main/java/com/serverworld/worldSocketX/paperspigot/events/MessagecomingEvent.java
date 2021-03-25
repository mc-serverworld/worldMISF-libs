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

package com.serverworld.worldSocketX.paperspigot.events;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.json.JSONObject;

public class MessagecomingEvent extends Event{

    private static final HandlerList HANDLERS = new HandlerList();
    final String msg;

    private String sender;
    private String receiver;
    private String type;
    private String channel;
    private String message;

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public MessagecomingEvent(String msg) {
        this.msg = msg;
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonmsg = jsonParser.parse(msg).getAsJsonObject();
        JSONObject json = new JSONObject(msg);
        sender = json.getString("sender");
        receiver = json.getString("receiver");
        type = json.getString("type");
        channel = json.getString("channel");
        message = json.getString("message");
    }


    public String getSender() {
        return this.sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getType() {
        return type;
    }

    public String getChannel() {
        return channel;
    }

    public String getMessage() {
        return message;
    }

}
