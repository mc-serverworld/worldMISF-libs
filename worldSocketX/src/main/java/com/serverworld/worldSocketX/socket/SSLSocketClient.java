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

package com.serverworld.worldSocketX.socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.serverworld.worldSocketX.config.worldSocketXConfig;
import com.serverworld.worldSocketX.paperspigot.PaperSpigotworldSocketX;
import com.serverworld.worldSocketX.paperspigot.util.DebugMessage;
import com.serverworld.worldSocketX.paperspigot.util.messager;
import net.md_5.bungee.api.ChatColor;
import org.json.JSONObject;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SSLSocketClient {
    private sender sender = new sender();


    static SSLSocket socket;
    //private static Scanner in;
    //private static PrintWriter out;

    //static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();

    public void startConnect(){
        Connecter connecter = new Connecter();
        connecter.start();
    }

    class Connecter extends Thread{
        @Override
        public void run() {
            try {
                SSLSocketKey socketKey = new SSLSocketKey();
                socketKey.initialization();

                socket = (SSLSocket) (socketKey.getCtx().getSocketFactory().createSocket(worldSocketXConfig.getHost(),worldSocketXConfig.getPort());
                socket.setSoTimeout(300);
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                DebugMessage.sendInfo("Loading login message...");
                LoginMessage loginMessage = new LoginMessage(worldSocketXConfig.getUUID(),0);
                out.println(loginMessage.getLoginJson());
                DebugMessage.sendInfo("Login with: " + loginMessage.UUID);
                DebugMessage.sendInfo("ProtocolVersion: " + 0);
                while (true){
                    //in = new Scanner(socket.getInputStream());
                    if (in.hasNextLine()) {
                        String message = in.nextLine();
                        DebugMessage.sendInfoIfDebug("----Message receive----\n" + message + "\n----------------------");
                        if(message.equals("ACCEPTED")){
                            DebugMessage.sendInfo(ChatColor.GREEN + "Connected to server!");
                        }else if(message.equals("ERROR:UUID_USED")) {
                            DebugMessage.sendWarring(ChatColor.RED + "The UUID has been used!");
                        }else if(message.equals("CHECK:ONLINE")){
                            worldsocket.checker.clearlist();
                        } else {
                            JsonParser jsonParser = new JsonParser();
                            JsonObject jsonmsg = jsonParser.parse(message).getAsJsonObject();
                            JSONObject json = new JSONObject(message);
                            if(worldsocket.config.debug())
                                worldsocket.getLogger().info(json.toString());
                            if(json.getString("receiver").toLowerCase().equals(worldsocket.config.name().toLowerCase()) && !json.getString("type").toLowerCase().equals("socketapi")){
                                worldsocket.eventsender.addeventqueue(message);
                                if(worldsocket.config.debug()){
                                    worldsocket.getLogger().info("Event Fired");
                                    //worldsocket.getLogger().info("Event Fired " + "message: " + "sender: " + json.getString("sender") + " receiver: " + json.getString("receiver") + " channel: " + json.getString("channel") + " type: " + json.getString("type"));
                                }
                            }else if(json.getString("receiver").toLowerCase().equals("all")&& !json.getString("type").toLowerCase().equals("socketapi")){
                                worldsocket.eventsender.addeventqueue(message);
                                if(worldsocket.config.debug()){
                                    worldsocket.getLogger().info("Event Fired");
                                    //worldsocket.getLogger().info("Event Fired " + "message: " + "sender: " + json.getString("sender") + " receiver: " + json.getString("receiver") + " channel: " + json.getString("channel") + " type: " + json.getString("type"));
                                }


                            }else if(json.getString("type").toLowerCase().equals("socketapi")){

                            }else
                                if (worldsocket.config.debug())
                                worldsocket.getLogger().info(ChatColor.YELLOW + "Unknow message");
                        }

                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                DebugMessage.sendWarring(ChatColor.RED + "Error while connect to socket server");
            }
        }
    }

    public void sendmessage(String message){
        queue.add(message);
        sender = new sender();
        sender.start();
    }

    private class sender extends Thread {
        public void run() {
            try {
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                    synchronized (queue) {
                        if (!queue.isEmpty()) {
                            for (String stuff : queue) {
                                //TODO create event
                                out.println(stuff);
                                out.flush();
                                if (worldsocket.config.debug()) {
                                    worldsocket.getLogger().info("send: " + stuff);
                                }
                            }
                            queue.clear();
                        }
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void checker(){
        PaperSpigotworldSocketX.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(PaperSpigotworldSocketX.getInstance(), new Runnable() {
            @Override
            public void run() {
                synchronized (list) {
                    list.add("CHECKING:" + new Date().getTime());
                    messager.sendmessage("CONNECTCHECK");
                    if (worldsocket.config.debug())
                        worldsocket.getLogger().info("checking connection");

                    if (!list.isEmpty()) {
                        if(list.size()>3){
                            worldsocket.reconnect();
                            list.clear();
                        }
                    }
                }
            }
        }, 0L, worldsocket.config.checkrate());
    }


}
