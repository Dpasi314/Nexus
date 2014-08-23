package com.atomic.nexus.exceptions;

import com.atomic.nexus.util.Input;

/**
 * Dante Pasionek created: com.atomic.nexus.exceptions on Aug. 08, 2014 *
 */
public class CommandSyntaxException extends Exception {

    /**
     * Exception for a command with Invalid Command Syntax.
     * @param message
     * @param clazz
     */
    public CommandSyntaxException(String message, Class clazz) {
        super("[Nexus] " + message);
        Input i = Input.getCurrentInstance();
        i.err("ERROR: " + message, clazz);
    }
}
