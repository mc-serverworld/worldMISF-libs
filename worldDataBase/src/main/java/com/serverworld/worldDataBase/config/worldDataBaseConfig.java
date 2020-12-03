package com.serverworld.worldDataBase.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class worldDataBaseConfig {
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static int ApiVersion;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static boolean Debug;

    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String DataBaseType;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Host;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static int Port;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String DataBase;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String UserName;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Password;
}
