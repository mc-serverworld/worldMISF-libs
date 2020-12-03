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

package com.serverworld.worldDataBase.paperspigot;

import com.serverworld.worldDataBase.paper.PaperSQLDatabase;
import com.serverworld.worldDataBase.paper.PaperworldUserDataConfig;
import com.serverworld.worldDataBase.api.query.ConnectionManager;
import com.serverworld.worldDataBase.api.query.ServerResidenceInquirer;
import org.bukkit.plugin.java.JavaPlugin;

public class PaperSpigotworldDataBase extends JavaPlugin {
    private static PaperSpigotworldDataBase paperworldUserData;
    public static PaperworldUserDataConfig config;

    @Override
    public void onLoad() {
        config = new PaperworldUserDataConfig(this);
        config.loadDefConfig();
        paperworldUserData = this;
        setSQL();
    }

    @Override
    public void onEnable() {
        syncConnection();
        //setup
        //setSQL();

    }

    public void setSQL(){
        PaperSQLDatabase paperSQLDatabase = new PaperSQLDatabase(this);
    }

    public void syncConnection() {
        PaperSpigotworldDataBase.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(PaperSpigotworldDataBase.getInstance(), new Runnable() {
            @Override
            public void run() {
                ConnectionManager.setConnection(PaperSQLDatabase.getConnection());

                ServerResidenceInquirer.isExist("check connection");
            }
        }, 60L, 300*20);
    }

    public static PaperSpigotworldDataBase getInstance(){
        return paperworldUserData;
    }
}
