package com.serverworld.worldSocketX.socket;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

public class ClientObject {
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private UUID UUID;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private UUID ProxyUUID;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private PrintWriter Printer;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private ArrayList<String> Channels;
}
