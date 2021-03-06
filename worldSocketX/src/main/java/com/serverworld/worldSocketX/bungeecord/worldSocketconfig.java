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

package com.serverworld.worldSocketX.bungeecord;

public class worldSocketXConfig {
    private worldSocketX plugin;

    public worldSocketXConfig(worldSocket i){
        plugin = i;
    }
    public void loadDefConfig(){ }

    //general
    public int port() {return plugin.configuration.getInt("general.port");}
    public String password() {return plugin.configuration.getString("general.password");}

    //server
    public int threads() {return plugin.configuration.getInt("socketserver.threads");}

    //client
    public String name() {return plugin.configuration.getString("client.name");}
    public String host() {return plugin.configuration.getString("client.host");}

    //configinfo
    public int api_version() {return plugin.configuration.getInt("configinfo.api-version");}
    public boolean debug() {return plugin.configuration.getBoolean("configinfo.debug");}

    //SSL
    public boolean useSSL() {return plugin.configuration.getBoolean("SSL.useSSL");}
    public boolean forceSSL() {return plugin.configuration.getBoolean("SSL.forceSSL");}
    public String server_keyStore_file() {return plugin.configuration.getString("SSL.server.keyStore_file");}
    public String server_trustStore_file() {return plugin.configuration.getString("SSL.server.trustStore_file");}
    public String server_keyStore_password() {return plugin.configuration.getString("SSL.server.keyStorePassword");}
    public String server_trustStore_password() {return plugin.configuration.getString("SSL.server.trustStorePassword");}

    public String client_keyStore_file() {return plugin.configuration.getString("SSL.client.keyStore_file");}
    public String client_trustStore_file() {return plugin.configuration.getString("SSL.client.trustStore_file");}
    public String client_keyStore_password() {return plugin.configuration.getString("SSL.client.keyStorePassword");}
    public String client_trustStore_password() {return plugin.configuration.getString("SSL.client.trustStorePassword");}
}
