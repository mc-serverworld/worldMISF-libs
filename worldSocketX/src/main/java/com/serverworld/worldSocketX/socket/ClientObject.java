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

package com.serverworld.worldSocketX.socket;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

//SSLServer socket will create new instance when new client connect;
//each client will have unique id;
public class ClientObject {
    public ClientObject(UUID uuid,PrintWriter printWriter,int ProtocolVersion){
        this.UUID = uuid;
        this.Printer = printWriter;
    }
    @Getter(AccessLevel.PUBLIC) private UUID UUID;
    //@Getter(AccessLevel.PUBLIC) private UUID ProxyUUID;
    @Getter(AccessLevel.PUBLIC) private PrintWriter Printer;
    @Setter(AccessLevel.PUBLIC) @Getter(AccessLevel.PUBLIC) private ArrayList<String> Channels;
    @Getter(AccessLevel.PUBLIC) private int ProtocolVersion;

    public void addChannel(String channel){//add listen channel
        Channels.add(channel);
    }
    public void removeChannel(String channel){//add listen channel
        Channels.remove(channel);
    }
    public void reSet(String channel){//add listen channel
        Channels.clear(); }
}
