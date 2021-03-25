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

package com.serverworld.worldSocketX.bungeecord.events;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.md_5.bungee.api.plugin.Event;

public class MessagecomingEvent extends Event {
    final String msg;

    private String sender;
    private String receiver;
    private String type;
    private String channel;
    private String message;

    public MessagecomingEvent(String msg) {
        this.msg = msg;
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonmsg = jsonParser.parse(msg).getAsJsonObject();
        sender = jsonmsg.get("sender").getAsString();
        receiver = jsonmsg.get("receiver").getAsString();
        type = jsonmsg.get("type").getAsString();
        channel = jsonmsg.get("channel").getAsString();
        message = jsonmsg.get("message").getAsString();
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
