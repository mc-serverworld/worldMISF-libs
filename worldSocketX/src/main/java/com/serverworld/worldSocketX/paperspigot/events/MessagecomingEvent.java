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
import com.serverworld.worldSocketX.api.ReceiverType;
import com.serverworld.worldSocketX.socket.MessageObject;
import lombok.AccessLevel;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.json.JSONObject;

import java.util.UUID;

public class MessagecomingEvent extends Event{

    private static final HandlerList HANDLERS = new HandlerList();
    final MessageObject messageObject;

    @Getter(AccessLevel.PUBLIC) private String Message;
    @Getter(AccessLevel.PUBLIC) private String Sender;
    @Getter(AccessLevel.PUBLIC) private String Receiver;
    @Getter(AccessLevel.PUBLIC) private ReceiverType ReceiverType;

    public UUID getSenderUUID(){ return UUID.fromString(Sender);}
    public UUID getReceiverUUID(){ return UUID.fromString(Receiver);}

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    public MessagecomingEvent(MessageObject messageObject) {
        this.messageObject = messageObject;
        this.Message = messageObject.getMessage();
        this.Receiver= messageObject.getReceiver();
        this.ReceiverType = messageObject.getReceiverType();
        this.Sender = messageObject.getSender();2
    }
}
