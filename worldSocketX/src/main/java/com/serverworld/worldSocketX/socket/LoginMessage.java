package com.serverworld.worldSocketX.socket;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class LoginMessage {
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static UUID UUID;
    @Getter(AccessLevel.PUBLIC) @Setter(AccessLevel.PUBLIC) private static String Password;
}
