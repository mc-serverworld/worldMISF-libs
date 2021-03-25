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

import java.util.ArrayList;
import java.util.UUID;

public class ServerResidenceData {
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String Server;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String World;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String ResidenceName;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Long CreateTime;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Long XYSize;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Boolean AllowGlobalTeleport;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String OwnerName;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private UUID OwnerUUID;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private ArrayList<UUID> AllowedTpPlayers;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double TeleportLocation_X;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double TeleportLocation_Y;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Double TeleportLocation_Z;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private Float TeleportLocation_Yaw;

}
