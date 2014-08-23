package com.atomic.nexus.enums;

/**
 * Dante Pasionek created: com.atomic.nexus.enums on Aug. 08, 2014 *
 */
public enum Authority {

    /**
     * Dante Pasionek - ONLY
     */
    ADMINISTRATOR,

    /**
     * Any user
     */
    USER,

    /**
     * Nexus - ONLY
     */
    NEXUS_ONLY;

    /**
     * For login purposes
     */
    Authority currentAuth = USER;

    /**
     * Essentially logs in
     * @param auth
     */
    public void setAuthority(Authority auth) {
        currentAuth = auth;
    }

    /**
     * Returns the current user's authority.
     * @return Authority
     */
    public Authority getAuth() {
        return currentAuth;
    }
}
