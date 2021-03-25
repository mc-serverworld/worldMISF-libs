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

public class UserPhoenixPlayerData {

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Long PlayTime;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double Residence_Total_Size;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Integer Residence_Total_Amount;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double Residence_Max_Size;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Integer Residence_Max_Amount;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Integer Home_Max_Amount;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String LastLocation_Server;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String LastLocation_World;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double LastLocation_X;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double LastLocation_Y;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double LastLocation_Z;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Float LastLocation_Yaw;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String LogoutLocation_Server;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String LogoutLocation_World;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double LogoutLocation_X;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double LogoutLocation_Y;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double LogoutLocation_Z;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Float LogoutLocation_Yaw;

}