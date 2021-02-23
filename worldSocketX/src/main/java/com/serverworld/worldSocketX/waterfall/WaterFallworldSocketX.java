package com.serverworld.worldSocketX.waterfall;

import com.serverworld.worldSocketX.config.worldSocketXConfig;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.UUID;

public class WaterFallworldSocketX extends Plugin {
    private static WaterFallworldSocketX waterFallworldSocketX;
    private static Configuration configuration;
    @Override
    public void onEnable() {
        waterFallworldSocketX = this;

    }

    public void LoadConfig(){
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
            if(configuration.getString("client.uuid").isEmpty()){
                configuration.set("client.uuid", (UUID.randomUUID().toString()));
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration,new File(getDataFolder(), "config.yml"));
            }//RandomUUID when its null
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));

            worldSocketXConfig.setApiVersion(configuration.getInt("configinfo.api-version"));
            worldSocketXConfig.setDebug(configuration.getBoolean("configinfo.debug"));
            worldSocketXConfig.setPort(configuration.getInt("general.port"));
            //worldSocketXConfig.setPassword(configuration.getString("general.password"));

            worldSocketXConfig.setThreads(configuration.getInt("socketserver.threads"));

            worldSocketXConfig.setUUID(configuration.getString("client.uuid"));
            worldSocketXConfig.setHost(configuration.getString("client.host"));
            worldSocketXConfig.setCheckRate(configuration.getInt("client.check-rate"));

            worldSocketXConfig.setSERVER_KEY_STORE_FILE(configuration.getString("SSL.server.keyStore_file"));
            worldSocketXConfig.setSERVER_TRUST_KEY_STORE_FILE(configuration.getString("SSL.server.trustStore_file"));
            worldSocketXConfig.setSERVER_KEY_STORE_PASSWORD(configuration.getString("SSL.server.keyStorePassword"));
            worldSocketXConfig.setSERVER_TRUST_KEY_STORE_PASSWORD(configuration.getString("SSL.server.trustStorePassword"));

            worldSocketXConfig.setCLIENT_KEY_STORE_FILE(configuration.getString("SSL.client.keyStore_file"));
            worldSocketXConfig.setCLIENT_TRUST_KEY_STORE_FILE(configuration.getString("SSL.client.trustStore_file"));
            worldSocketXConfig.setCLIENT_KEY_STORE_PASSWORD(configuration.getString("SSL.client.keyStorePassword"));
            worldSocketXConfig.setCLIENT_TRUST_KEY_STORE_PASSWORD(configuration.getString("SSL.client.trustStorePassword"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static WaterFallworldSocketX getInstance(){return waterFallworldSocketX;}
}
