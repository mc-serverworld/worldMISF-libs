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

package com.serverworld.worldDataBase.paperspigot;

import com.serverworld.worldDataBase.config.worldDataBaseConfig;
import com.serverworld.worldDataBase.api.query.ConnectionManager;
import com.serverworld.worldDataBase.api.query.ServerResidenceInquirer;
import com.serverworld.worldDataBase.paperspigot.utils.DebugMessage;
import com.serverworld.worldDataBase.storge.MariaDB;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperSpigotworldDataBase extends JavaPlugin {
    private static PaperSpigotworldDataBase paperSpigotworldDataBase;

    @Override
    public void onLoad() {
        paperSpigotworldDataBase = this;
        LoadConfig();
        setSQL();
    }

    @Override
    public void onEnable() {
        syncConnection();
    }

    public void setSQL(){
        switch (worldDataBaseConfig.getDataBaseType().toLowerCase()){
            default:
                DebugMessage.sendInfo(ChatColor.RED + "Not supported this database type");
            case "mariadb": {
                DebugMessage.sendInfo(ChatColor.YELLOW + "Using mariadb");
                MariaDB mariaDB = new MariaDB();
            }
        }
    }

    public void syncConnection() {
        PaperSpigotworldDataBase.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(PaperSpigotworldDataBase.getInstance(), new Runnable() {
            @Override
            public void run() {
                switch (worldDataBaseConfig.getDataBaseType().toLowerCase()){
                    default:
                       return;
                    case "mariadb": ConnectionManager.setConnection(MariaDB.getConnection());
                }
                ServerResidenceInquirer.isExist("check connection");
            }
        }, 60L, 300*20);
    }

    public static PaperSpigotworldDataBase getInstance(){
        return paperSpigotworldDataBase;
    }

    public void LoadConfig(){
        saveDefaultConfig();
        reloadConfig();

        worldDataBaseConfig.setApiVersion(getConfig().getInt("configinfo.api-version"));
        worldDataBaseConfig.setDebug(getConfig().getBoolean("configinfo.debug"));

        worldDataBaseConfig.setSet(getConfig().getString("database.set"));
        worldDataBaseConfig.setDataBaseType(getConfig().getString("database.type"));
        worldDataBaseConfig.setHost(getConfig().getString("database.host"));
        worldDataBaseConfig.setPort(getConfig().getInt("database.port"));
        worldDataBaseConfig.setDataBase(getConfig().getString("database.database"));
        worldDataBaseConfig.setUserName(getConfig().getString("database.username"));
        worldDataBaseConfig.setPassword(getConfig().getString("database.password"));
    }
}
