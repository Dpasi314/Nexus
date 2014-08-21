package com.atomic.nexus.exceptions;

/**
 * Dante Pasionek created: com.atomic.nexus.exceptions on Aug. 08, 2014 *
 */
public class CommandSyntaxException extends Exception {

    public CommandSyntaxException(String message) {
        super("[Nexus] " + message);
    }
}
