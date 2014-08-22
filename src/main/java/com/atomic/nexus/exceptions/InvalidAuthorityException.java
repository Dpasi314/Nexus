package com.atomic.nexus.exceptions;

import com.atomic.nexus.util.Input;

/**
 * Dante Pasionek created: com.atomic.nexus.exceptions on Aug. 21, 2014 *
 */
public class InvalidAuthorityException extends Exception {

    public InvalidAuthorityException(String message, Class clazz) {
        super("[Nexus] " + message);
        Input i = Input.getCurrentInstance();
        i.err("ERROR: " + message, clazz);

    }
}
