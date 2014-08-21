package com.atomic.nexus.exceptions;

/**
 * Dante Pasionek created: com.atomic.nexus.exceptions on Aug. 09, 2014 *
 */
public class UnknownCommandException extends Exception {

    public UnknownCommandException(String message) {
        super("[Nexus] " + message);
    }
}
