package com.atomic.nexus.exceptions;

import com.atomic.nexus.util.Input;

/**
 * Dante Pasionek created: com.atomic.nexus.exceptions on Aug. 09, 2014 *
 */
public class UnknownCommandException extends Exception {

    /**
     * Exception to handle an unknown command
     * @param message
     * @param clazz
     */
    public UnknownCommandException(String message, Class clazz) {
        super("[Nexus] " + message);
        Input i = Input.getCurrentInstance();
        i.err("ERROR: " + message, clazz);
    }
}
