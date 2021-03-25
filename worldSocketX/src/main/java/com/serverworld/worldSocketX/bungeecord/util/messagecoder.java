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

package com.serverworld.worldSocketX.bungeecord.util;

import com.google.gson.JsonObject;

public class messagecoder {
    private String sender;
    private String receiver;
    private String type;
    private String channel;
    private String message;

    public void setSender(String sender){
        this.sender=sender;
    }
    public void setReceiver(String receiver){
        this.receiver=receiver;
    }
    public void setType(String type){
        this.type=type;
    }
    public void setChannel(String channel){
        this.channel=channel;
    }
    public void setMessage(String message){
        this.message=message;
    }
    public String createmessage(){
        if(this.sender.isEmpty()) {return null;}
        JsonObject jsonmsg = new JsonObject();
        jsonmsg.addProperty("sender", sender);
        jsonmsg.addProperty("receiver", receiver);
        jsonmsg.addProperty("type", type);
        jsonmsg.addProperty("channel", channel);
        jsonmsg.addProperty("message", message);
        return (jsonmsg.toString());
    }
}
