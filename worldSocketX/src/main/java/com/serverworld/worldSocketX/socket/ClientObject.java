package com.serverworld.worldSocketX.socket;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

//SSLServer socket will create new instance when new client connect;
//each client will have unique id;
public class ClientObject {
    public ClientObject(UUID uuid,PrintWriter printWriter,int ProtocolVersion){
        this.UUID = uuid;
        this.Printer = printWriter;
    }
    @Getter(AccessLevel.PUBLIC) private UUID UUID;
    //@Getter(AccessLevel.PUBLIC) private UUID ProxyUUID;
    @Getter(AccessLevel.PUBLIC) private PrintWriter Printer;
    @Setter(AccessLevel.PUBLIC) @Getter(AccessLevel.PUBLIC) private ArrayList<String> Channels;
    @Getter(AccessLevel.PUBLIC) private int ProtocolVersion;

    public void addChannel(String channel){//add listen channel
        Channels.add(channel);
    }
    public void removeChannel(String channel){//add listen channel
        Channels.remove(channel);
    }
    public void reSet(String channel){//add listen channel
        Channels.clear(); }
}
