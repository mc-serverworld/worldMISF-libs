package com.serverworld.worldSocketX.socket;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public class ServerInfo {
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String SERVER_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String SERVER_TRUST_KEY_STORE_FILE;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String SERVER_KEY_STORE_PASSWORD;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private String SERVER_TRUST_KEY_STORE_PASSWORD;
}
