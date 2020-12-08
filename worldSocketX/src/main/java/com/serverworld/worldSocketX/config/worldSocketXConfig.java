package com.serverworld.worldSocketX.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class worldSocketXConfig {
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static int ApiVersion;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static boolean Debug;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static int Port;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Password;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static int Threads;


    general:
    port:
            18964
    password: "aba9cfde1fd706a0c83f373cd1c10e5f11faaa765e2229f9723f6f9b33e3506037820a9a0f1ed111d220e022b7a70c8ff88cb15e9db4905a0fbc5128335302fa"

    socketserver:
    threads: 100

    client:
    uuid: ""
    name: "server"
    host: "127.0.0.1"
    check-rate: 60

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_TRUST_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_KEY_STORE_PASSWORD;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_TRUST_KEY_STORE_PASSWORD;
}
