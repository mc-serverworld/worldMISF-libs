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

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String UUID;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Name;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Host;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static int CheckRate;


    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_TRUST_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_KEY_STORE_PASSWORD;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_TRUST_KEY_STORE_PASSWORD;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String CLIENT_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String CLIENT_TRUST_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String CLIENT_KEY_STORE_PASSWORD;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String CLIENT_TRUST_KEY_STORE_PASSWORD;
}
