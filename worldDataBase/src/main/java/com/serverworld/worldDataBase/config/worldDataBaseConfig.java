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

package com.serverworld.worldDataBase.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class worldDataBaseConfig {
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static int ApiVersion;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static boolean Debug;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String DataBaseType;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Host;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static int Port;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String DataBase;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String UserName;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Password;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Set;
}
