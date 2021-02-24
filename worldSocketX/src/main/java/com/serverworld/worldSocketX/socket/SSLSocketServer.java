package com.serverworld.worldSocketX.socket;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.serverworld.worldSocketX.config.worldSocketXConfig;
import com.serverworld.worldSocketX.waterfall.WaterFallworldSocketX;
import com.serverworld.worldSocketX.waterfall.uitls.DebugMessage;
import net.md_5.bungee.api.ChatColor;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyStore;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SSLSocketServer extends Thread {
    static ConcurrentLinkedQueue<String> MessagesQueue = new ConcurrentLinkedQueue<String>();
    private static Set<ClientObject> Clients = new HashSet<>();
    private static Set<UUID> UUIDs = new HashSet<>();
    private sender sender;
    //private static Set<String> names = new HashSet<>();
    //private static Set<PrintWriter> writers = new HashSet<>();

    public SSLSocketServer() {

    }

    public void run() {
        try{
            SSLSocketKey socketKey = new SSLSocketKey();
            socketKey.setKEY_STORE_FILE(worldSocketXConfig.getSERVER_KEY_STORE_FILE());
            socketKey.setTRUST_KEY_STORE_FILE(worldSocketXConfig.getSERVER_TRUST_KEY_STORE_FILE());
            socketKey.setKEY_STORE_PASSWORD(worldSocketXConfig.getSERVER_KEY_STORE_PASSWORD());
            socketKey.setTRUST_KEY_STORE_PASSWORD(worldSocketXConfig.getSERVER_TRUST_KEY_STORE_PASSWORD());
            System.out.println(Ansi.colorize("SocketKet set", Attribute.GREEN_TEXT()));

            SSLServerSocket listener = (SSLServerSocket) socketKey.getCtx().getServerSocketFactory().createServerSocket(worldSocketXConfig.getPort());
            System.out.println(Ansi.colorize("Listener set", Attribute.GREEN_TEXT()));
            listener.setNeedClientAuth(true);//Request client trusted
            listener.setEnabledProtocols(new String[]{"TLSv1.2"});//Force TLS 1.2
            ExecutorService pool = Executors.newFixedThreadPool(worldSocketXConfig.getThreads());
            System.out.println(Ansi.colorize("Pool set using " + worldSocketXConfig.getThreads() + " Threads", Attribute.GREEN_TEXT()));
            while (true) {
                pool.execute(new Handler((SSLSocket) listener.accept()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static class Handler implements Runnable {
        private String LoginMessage;
        private SSLSocket socket;
        private Scanner in;
        private PrintWriter out;
        private ClientObject object;

        public Handler(SSLSocket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);

                while (true) {
                    LoginMessage = in.nextLine();
                    if (LoginMessage == null)
                        return;

                    synchronized (Clients) {
                        Gson gson = new Gson();
                        LoginMessage loginMessage = gson.fromJson(LoginMessage, com.serverworld.worldSocketX.socket.LoginMessage.class);
                        if(UUIDs.contains(loginMessage.UUID)){
                            out.println("ERROR:UUID_USED");
                            out.flush();
                            DebugMessage.sendWarring(ChatColor.YELLOW + "Opps! seem some one use the same UUID: " + loginMessage.UUID);
                            return;
                        }

                        object = new ClientObject(loginMessage.UUID,out,loginMessage.ProtocolVersion);
                        Clients.add(object);
                        break;
                    }
                }
                out.println("ACCEPTED");
                DebugMessage.sendInfo("Socket join: " + object.getUUID());
                //-------END LOGIN PROCESS---------
                while (true) {
                    String input = in.nextLine();

                    //-------START SOCKET FUNCTION---------

                    //Disconnect
                    if (input.equalsIgnoreCase("DISCONNECT"))
                        break;

                    //Connect check
                   else if(input.equalsIgnoreCase("CONNECTCHECK")){
                        out.println("CHECK:ONLINE");
                        DebugMessage.sendInfoIfDebug(object.getUUID() + " checking connection");
                   }

                    //Join channel
                    else if(input.startsWith("JOIN_CHANNEL:"))
                        object.addChannel(input.split(":")[1]);

                    //Leave channel
                    else if(input.startsWith("LEAVE_CHANNEL:"))
                        object.removeChannel(input.split(":")[1]);

                    //Get channel list
                    else if(input.equalsIgnoreCase("GET_CHANNELS_LIST"))
                        out.println(object.getChannels());//TODO NEED TEST

                    //TODO get client list
                    //TODO get channel client list

                    //-------END SOCKET FUNCTION---------

                    //TODO send message via UUID
                    //TODO send message via channel
                    //TODO send message to all client

                    //-------START MESSAGE SEND PROCESS---------

                    //-------END MESSAGE SEND PROCESS---------

                    /*if{
                        JsonParser jsonParser = new JsonParser();
                        JsonObject jsonmsg = jsonParser.parse(input).getAsJsonObject();
                        try {
                            if(jsonmsg.get("receiver").getAsString().toLowerCase().equals("proxy")){
                                worldSocket.getInstance().getProxy().getPluginManager().callEvent(new MessagecomingEvent(input));
                                if(worldSocket.getInstance().config.debug())
                                    worldSocket.getInstance().getLogger().info("Event send");
                            }

                            else if(jsonmsg.get("receiver").getAsString().toLowerCase().equals("all")){
                                worldSocket.getInstance().getProxy().getPluginManager().callEvent(new MessagecomingEvent(input));
                                if(worldSocket.getInstance().config.debug())
                                    worldSocket.getInstance().getLogger().info("Event send");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                        if(!jsonmsg.get("receiver").getAsString().toLowerCase().equals("proxy")){
                            if(worldSocket.getInstance().config.debug()){
                                worldSocket.getInstance().getLogger().info(name + " send message: " + input);
                                worldSocket.getInstance().getLogger().info("sent to " + writers.size() + " clients");
                            }
                            for (PrintWriter writer : writers) {
                                writer.println(input);
                                writer.flush();
                            }
                        }
                    }*/


                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (object != null) {
                    Clients.remove(object);
                    DebugMessage.sendInfo("Socket quit: " + object.getUUID());
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
        //End run
    }

    public void sendmessage(String message){
        MessagesQueue.add(message);
        sender = new sender();
        sender.start();
    }

    private class sender extends Thread {
        public void run() {
            try {
                synchronized (MessagesQueue) {
                    if (!MessagesQueue.isEmpty()) {
                        for (String stuff : MessagesQueue) {
                            for(ClientObject clients :Clients){
                                clients.getPrinter().println();
                            }
                            for (PrintWriter writer : writers) {// foreach client object
                                writer.println(stuff);
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
}