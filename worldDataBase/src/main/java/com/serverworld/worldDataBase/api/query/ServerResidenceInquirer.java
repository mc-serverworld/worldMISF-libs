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

package com.serverworld.worldDataBase.api.query;

import com.google.gson.Gson;
import com.serverworld.worldDataBase.jsondata.phoenix.ServerResidenceData;

import java.sql.ResultSet;
import java.sql.Statement;

public class ServerResidenceInquirer {

    public static boolean isExist(String residenceName){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_ServerResidenceData WHERE ResidenceName = '%ResidenceName%';";
            executeString = executeString.replace("%ResidenceName%",residenceName);
            ResultSet rs = statement.executeQuery(executeString);
            Boolean exist = false;
            exist = rs.next();
            statement.close();
            return exist;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addDataClass(ServerResidenceData residenceData,int version){
        try {
            if(isExist(residenceData.getResidenceName()))
                return false;
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "INSERT INTO `worldDataBase_ServerResidenceData` (`id`, `ResidenceName`, `CreateTime`, `ResidenceData`, `OwnerUUID`, `Version`) VALUES (NULL, '%ResidenceName%', '%CreateTime%', '%ResidenceData%', '%OwnerUUID%', '%Version%');";
            Gson gson = new Gson();
            String stg = gson.toJson(residenceData,ServerResidenceData.class);
            executeString = executeString.replace("%ResidenceName%",residenceData.getResidenceName());
            executeString = executeString.replace("%CreateTime%",residenceData.getCreateTime().toString());
            executeString = executeString.replace("%ResidenceData%",stg);
            executeString = executeString.replace("%OwnerUUID%",residenceData.getOwnerUUID().toString());
            executeString = executeString.replace("%Version%",String.valueOf(version));
            statement.execute(executeString);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ServerResidenceData getDataClass(String residenceName){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_ServerResidenceData WHERE ResidenceName = '%ResidenceName%';";
            executeString = executeString.replace("%ResidenceName%",residenceName);
            ResultSet rs = statement.executeQuery(executeString);
            rs.next();
            Gson gson= new Gson();
            ServerResidenceData serverResidenceData = gson.fromJson(rs.getString("ResidenceData"), ServerResidenceData.class);
            statement.close();
            return serverResidenceData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean setDataClass(String residenceName, ServerResidenceData serverResidenceData){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "UPDATE worldDataBase_ServerResidenceData SET ResidenceData = '%ResidenceData%' WHERE ResidenceName = '%ResidenceName%';";
            Gson gson = new Gson();
            String stg = gson.toJson(serverResidenceData,ServerResidenceData.class);
            executeString = executeString.replace("%ResidenceData%",stg);
            executeString = executeString.replace("%ResidenceName%",residenceName);
            statement.execute(executeString);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static int getDataClassVersion(String residenceName){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_ServerResidenceData WHERE ResidenceName = '%ResidenceName%';";
            executeString = executeString.replace("%ResidenceName%",residenceName);
            statement.execute(executeString);
            ResultSet rs = statement.executeQuery(executeString);
            rs.next();
            int ver = rs.getInt("Version");
            statement.close();
            return ver;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean setDataClassVersion(String residenceName, int version){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "UPDATE worldDataBase_ServerResidenceData SET version = '%Version%' WHERE ResidenceName = '%ResidenceName%';";
            executeString = executeString.replace("%ResidenceName%",residenceName);
            executeString = executeString.replace("%Version%", String.valueOf(version));
            statement.execute(executeString);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
