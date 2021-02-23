package com.serverworld.worldSocketX.socket;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

public class LoginMessage {
    public UUID UUID;
    public int ProtocolVersion;
    public LoginMessage(UUID UUID, int ProtocolVersion){
        this.UUID = UUID;
        this.ProtocolVersion = ProtocolVersion;
    }

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this,LoginMessage.class);
    }

}