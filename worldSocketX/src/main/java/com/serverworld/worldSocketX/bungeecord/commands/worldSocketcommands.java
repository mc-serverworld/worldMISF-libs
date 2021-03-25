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

package com.serverworld.worldSocketX.bungeecord.commands;

import com.serverworld.worldSocket.bungeecord.*;
import com.serverworld.worldSocket.bungeecord.util.messagecoder;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class worldSocketcommands extends Command{
    worldSocket worldsocket;

    public worldSocketcommands(worldSocket worldSocket){
        super("worldsocket");
        this.worldsocket = worldSocket;

    }
    public void execute(CommandSender commandSender, String[] strings) {
        messagecoder messagecoder=new messagecoder();
        messagecoder.setSender("worldsocket");
        messagecoder.setReceiver("ALL");
        messagecoder.setType("TEST");
        messagecoder.setChannel("test");
        messagecoder.setMessage("test message測試");
        worldsocket.sendmessage(messagecoder.createmessage());
    }
}
