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

package com.serverworld.worldSocketX.paperspigot.socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.serverworld.worldSocket.paperspigot.worldSocket;
import net.md_5.bungee.api.ChatColor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

public class socketclient {
    private worldSocket worldsocket;
    private login loginer = new login();
    private receiver receiver = new receiver();
    private sender sender = new sender();

    static Socket socket;
    private static Scanner in;
    private static PrintWriter out;

    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<String>();
    private boolean shouldStop=false;

    public socketclient(worldSocket worldsocket){
        this.worldsocket=worldsocket;
    }
    public void startlogin(){
        loginer.start();
    }
    public void closesocket(){
        shouldStop=true;
    }

    class login extends Thread{
        @Override
        public void run() {
            try{
                socket = new Socket(worldsocket.config.host(), worldsocket.config.port());
                Scanner in = new Scanner(socket.getInputStream());
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                receiver.start();
                out.println(worldsocket.config.name());
            } catch (IOException e) {
                e.printStackTrace();
                worldsocket.getLogger().warning(ChatColor.RED + "Error while connect to socket server");
            }
        }
    }

    public void sendmessage(String message){
        queue.add(message);
        sender = new sender();
        sender.start();
    }
    private class receiver extends Thread {
        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                while (!shouldStop) {
                    synchronized (socket) {
                        if (in.hasNextLine()) {
                            String message = in.nextLine();
                            if (worldsocket.config.debug()) {worldsocket.getLogger().info("received: " + message);}
                            if(message.equals("ACCEPTED")){
                                worldsocket.getLogger().info(ChatColor.GREEN + "Connect to socket server!");
                            }else if(message.equals("ERROR:NAME_USED")) {
                                worldsocket.getLogger().warning(ChatColor.RED + "The name has been used!");
                            }else if(message.equals("ERROR:WRONG_PASSWORD")){
                                worldsocket.getLogger().warning(ChatColor.RED + "Wrong password!");
                            }else {
                                JsonParser jsonParser = new JsonParser();
                                JsonObject jsonmsg = jsonParser.parse(message).getAsJsonObject();
                                if(jsonmsg.get("receiver").getAsString().toLowerCase().equals(worldsocket.config.name()) && !jsonmsg.get("type").getAsString().toLowerCase().equals("socketapi")){
                                    worldsocket.eventsender.addeventqueue(message);
                                }else if(jsonmsg.get("receiver").getAsString().toLowerCase().equals("all")&& !jsonmsg.get("type").getAsString().toLowerCase().equals("socketapi")){
                                    worldsocket.eventsender.addeventqueue(message);
                                }else if(jsonmsg.get("type").getAsString().toLowerCase().equals("socketapi")){

                                }
                            }

                        }
                    }
                }
            } catch (IOException e) {e.printStackTrace();}
        }
    }

    private class sender extends Thread {
        public void run() {
            try {
                out = new PrintWriter(new PrintWriter(socket.getOutputStream()));
                        synchronized (queue) {
                            if(!queue.isEmpty()){
                                for (String stuff:queue) {
                                    //TODO create event
                                out.println(stuff);
                                out.flush();
                                if (worldsocket.config.debug()){worldsocket.getLogger().info("send: " + stuff);}
                                }
                                queue.clear();
                            }
                        }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
