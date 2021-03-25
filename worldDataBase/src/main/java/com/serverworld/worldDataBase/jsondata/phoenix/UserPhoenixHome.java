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

package com.serverworld.worldDataBase.jsondata.phoenix;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class UserPhoenixHome {
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String Home_Name;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String Home_Server;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String Home_World;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double Home_X;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double Home_Y;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double Home_Z;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Float Home_Yaw;
}
