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

import com.google.gson.JsonObject;

public class socketloginer {
    private String name;
    private String password;
    private String apiverison;

    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setApiverison(String apiverison){
        this.apiverison = apiverison;
    }
    public String createmessage(){
        JsonObject jsonmsg = new JsonObject();
        jsonmsg.addProperty("name", name);
        jsonmsg.addProperty("password", password);
        //jsonmsg.addProperty("apiversion", apiverison);
        return (jsonmsg.toString());
    }
}
