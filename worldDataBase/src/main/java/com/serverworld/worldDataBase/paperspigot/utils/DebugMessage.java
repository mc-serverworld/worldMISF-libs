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

package com.serverworld.worldDataBase.paperspigot.utils;

import com.serverworld.worldDataBase.config.worldDataBaseConfig;
import com.serverworld.worldDataBase.paperspigot.PaperSpigotworldDataBase;

public class DebugMessage {

    public static void sendInfo(String msg){
        PaperSpigotworldDataBase.getInstance().getLogger().info(msg);
    }
    public static void sendWarring(String msg){ PaperSpigotworldDataBase.getInstance().getLogger().warning(msg); }
    public static void sendInfoIfDebug(String msg){
        if(worldDataBaseConfig.isDebug())
            PaperSpigotworldDataBase.getInstance().getLogger().info(msg);
    }
    public static void sendWarringIfDebug(String msg){
        if(worldDataBaseConfig.isDebug())
            PaperSpigotworldDataBase.getInstance().getLogger().warning(msg);
    }
}
