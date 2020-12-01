package com.serverworld.worldSocketX.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class worldSocketXconfig {
    \    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_TRUST_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_KEY_STORE_PASSWORD;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String SERVER_TRUST_KEY_STORE_PASSWORD;
}
