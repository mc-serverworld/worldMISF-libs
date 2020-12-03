/*
 *
 *  * WorldMISF - cms of mc-serverworld
 *  * Copyright (C) 2019-2020 mc-serverworld
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
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.serverworld.worldDataBase.jsondata.phoenix.UserPhoenixHome;
import com.serverworld.worldDataBase.jsondata.phoenix.UserPhoenixPlayerData;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

public class UserPhoenixPlayerDataInquirer {

    public static boolean setUp(UUID uuid){
        try {
            if(joinbefore(uuid)) {
                if(getDataClassVersion(uuid)<1){
                    setDataClassVersion(uuid,1);
                    return false;//update code here
                }
                return true;
            }
            UserPhoenixPlayerData userPhoenixPlayerData = new UserPhoenixPlayerData();
            userPhoenixPlayerData.setPlayTime(0L);
            userPhoenixPlayerData.setResidence_Total_Size(0D);
            userPhoenixPlayerData.setResidence_Total_Amount(0);
            userPhoenixPlayerData.setResidence_Max_Size(10000D);
            userPhoenixPlayerData.setResidence_Max_Amount(3);
            userPhoenixPlayerData.setHome_Max_Amount(3);
            userPhoenixPlayerData.setLastLocation_Server("none");
            userPhoenixPlayerData.setLastLocation_World("none");
            userPhoenixPlayerData.setLastLocation_X(0d);
            userPhoenixPlayerData.setLastLocation_Y(0d);
            userPhoenixPlayerData.setLastLocation_Z(0d);
            userPhoenixPlayerData.setLastLocation_Yaw(0f);
            userPhoenixPlayerData.setLogoutLocation_Server("none");
            userPhoenixPlayerData.setLogoutLocation_World("none");
            userPhoenixPlayerData.setLogoutLocation_X(0d);
            userPhoenixPlayerData.setLogoutLocation_Y(0d);
            userPhoenixPlayerData.setLogoutLocation_Z(0d);
            userPhoenixPlayerData.setLogoutLocation_Yaw(0f);
            Gson gson = new GsonBuilder().serializeNulls().create();
            String stg = gson.toJson(userPhoenixPlayerData,UserPhoenixPlayerData.class);

            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "INSERT INTO worldDataBase_UserPhoenixPlayerData (PlayerUUID, version, playerdata) VALUES ('%PlayerUUID%', '2', '%PlayerData%');";
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            executeString = executeString.replace("%PlayerData%",stg);
            statement.execute(executeString);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean joinbefore(UUID uuid){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_UserPhoenixPlayerData WHERE PlayerUUID = '%PlayerUUID%';";
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
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

    public static UserPhoenixPlayerData getDataClass(UUID uuid){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_UserPhoenixPlayerData WHERE PlayerUUID = '%PlayerUUID%';";
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            ResultSet rs = statement.executeQuery(executeString);
            rs.next();
            Gson gson = new GsonBuilder().serializeNulls().create();
            UserPhoenixPlayerData playerdataclass = gson.fromJson(rs.getString("playerdata"), UserPhoenixPlayerData.class);
            statement.close();
            return playerdataclass;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean setDataClass(UUID uuid, UserPhoenixPlayerData userPhoenixPlayerData){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "UPDATE worldDataBase_UserPhoenixPlayerData SET playerdata = '%PlayerData%' WHERE PlayerUUID = '%PlayerUUID%';";
            Gson gson = new GsonBuilder().serializeNulls().create();
            String stg = gson.toJson(userPhoenixPlayerData,UserPhoenixPlayerData.class);
            executeString = executeString.replace("%PlayerData%",stg);
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            statement.execute(executeString);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean addHome(UUID uuid, UserPhoenixHome userPhoenixHome){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_UserPhoenixPlayerData WHERE PlayerUUID = '%PlayerUUID%';";
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            ResultSet rs = statement.executeQuery(executeString);
            rs.next();
            Gson gson = new GsonBuilder().serializeNulls().create();
            ArrayList<UserPhoenixHome> userPhoenixHomes = gson.fromJson(rs.getString("playerhome"), new TypeToken<ArrayList<UserPhoenixHome>>(){}.getType());
            userPhoenixHomes.removeIf(stuff -> stuff.getHome_Name().equals(userPhoenixHome.getHome_Name()));
            userPhoenixHomes.add(userPhoenixHome);
            if(getDataClass(uuid).getHome_Max_Amount()>userPhoenixHomes.size()){
                statement.close();
                return false;
            }
            executeString = "UPDATE worldDataBase_UserPhoenixPlayerData SET playerhome = '%HomeData%' WHERE PlayerUUID = '%PlayerUUID%';";
            String stg = gson.toJson(userPhoenixHomes,new TypeToken<ArrayList<UserPhoenixHome>>(){}.getType());
            executeString = executeString.replace("%HomeData%",stg);
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            statement.execute(executeString);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static UserPhoenixHome getHome(UUID uuid, String homeName){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_UserPhoenixPlayerData WHERE PlayerUUID = '%PlayerUUID%';";
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            ResultSet rs = statement.executeQuery(executeString);
            rs.next();
            Gson gson = new GsonBuilder().serializeNulls().create();
            ArrayList<UserPhoenixHome> userPhoenixHomes = gson.fromJson(rs.getString("playerhome"), new TypeToken<ArrayList<UserPhoenixHome>>(){}.getType());
            if(userPhoenixHomes.size()==0){
                statement.close();
                return null;
            }
            for (UserPhoenixHome stuff:userPhoenixHomes) {
                if(stuff.getHome_Name().equals(homeName)){
                    statement.close();
                    return stuff;
                }
            }
            statement.close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean delHome(UUID uuid, String homeName){
        try {
            if(!hasHome(uuid,homeName))
                return false;
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_UserPhoenixPlayerData WHERE PlayerUUID = '%PlayerUUID%';";
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            ResultSet rs = statement.executeQuery(executeString);
            rs.next();
            Gson gson = new GsonBuilder().serializeNulls().create();
            ArrayList<UserPhoenixHome> userPhoenixHomes = gson.fromJson(rs.getString("playerhome"), new TypeToken<ArrayList<UserPhoenixHome>>(){}.getType());
            userPhoenixHomes.removeIf(stuff -> stuff.getHome_Name().equals(homeName));
            executeString = "UPDATE worldDataBase_UserPhoenixPlayerData SET playerhome = '%HomeData%' WHERE PlayerUUID = '%PlayerUUID%';";
            String stg = gson.toJson(userPhoenixHomes,new TypeToken<ArrayList<UserPhoenixHome>>(){}.getType());
            executeString = executeString.replace("%HomeData%",stg);
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            statement.execute(executeString);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean hasHome(UUID uuid, String homeName){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_UserPhoenixPlayerData WHERE PlayerUUID = '%PlayerUUID%';";
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            ResultSet rs = statement.executeQuery(executeString);
            rs.next();
            Gson gson = new GsonBuilder().serializeNulls().create();
            ArrayList<UserPhoenixHome> userPhoenixHomes = gson.fromJson(rs.getString("playerhome"), new TypeToken<ArrayList<UserPhoenixHome>>(){}.getType());

            if(userPhoenixHomes.size()==0){
                statement.close();
                return false;
            }

            for (UserPhoenixHome stuff:userPhoenixHomes) {
                if(stuff.getHome_Name().equals(homeName)){
                    statement.close();
                    return true;
                }
            }

            statement.close();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static int getDataClassVersion(UUID uuid){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "SELECT * FROM worldDataBase_UserPhoenixPlayerData WHERE PlayerUUID = '%PlayerUUID%';";
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            ResultSet rs = statement.executeQuery(executeString);
            rs.next();
            int ver = rs.getInt("version");
            statement.close();
            return ver;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Boolean setDataClassVersion(UUID uuid, int version){
        try {
            Statement statement = ConnectionManager.getConnection().createStatement();
            String executeString = "UPDATE worldDataBase_UserPhoenixPlayerData SET version = %Version%'' WHERE PlayerUUID = '%PlayerUUID%';";
            executeString = executeString.replace("%Version%",String.valueOf(version));
            executeString = executeString.replace("%PlayerUUID%",uuid.toString());
            statement.execute(executeString);
            statement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}