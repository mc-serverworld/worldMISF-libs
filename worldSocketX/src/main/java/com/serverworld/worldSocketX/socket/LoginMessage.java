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

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.UUID;

public class LoginMessage {
    @Getter(AccessLevel.PUBLIC) UUID UUID;
    @Getter(AccessLevel.PUBLIC) int ProtocolVersion;

    public LoginMessage(@Nullable UUID UUID, @Nullable int ProtocolVersion){
        this.UUID = UUID;
        this.ProtocolVersion = ProtocolVersion;
    }

    public LoginMessage(String msg){

    }

    public String getLoginJson(){
        Gson gson = new Gson();
        return gson.toJson(this,LoginMessage.class);
    }

}