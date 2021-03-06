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

import com.serverworld.worldSocketX.api.ReceiverType;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.Getter;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;


public class MessageObject {

    @Getter(AccessLevel.PUBLIC) private String Message;
    @Getter(AccessLevel.PUBLIC) private String MessageType;
    @Getter(AccessLevel.PUBLIC) private String Sender;
    @Getter(AccessLevel.PUBLIC) private String Receiver;
    @Getter(AccessLevel.PUBLIC) private ReceiverType ReceiverType;
    @Getter(AccessLevel.PUBLIC) private Long Time;

    public UUID getSenderUUID(){ return UUID.fromString(Sender);}
    public UUID getReceiverUUID(){ return UUID.fromString(Receiver);}

    /*public MessageObject(String Message, UUID sender, UUID receiver) {
        this.Sender = sender.toString();
        this.Receiver = receiver.toString();
    }*/

    public MessageObject(String Message, String sender, String receiver, ReceiverType receiverType, @Nullable String messageType) {
        this.Message = Message;
        this.MessageType = messageType;
        this.Sender = sender;
        this.Receiver = receiver;
        this.ReceiverType = receiverType;
        this.Time = System.currentTimeMillis();
    }

    public HashCode getCRC32C(){
        return Hashing.crc32c().hashString(Message+Time, StandardCharsets.UTF_8);
    }

}
