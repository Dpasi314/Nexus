package com.atomic.nexus.commands;

import com.atomic.nexus.enums.Authority;
import com.atomic.nexus.commands.util.Command;
import com.atomic.nexus.util.HeartBeat;

/**
 * Dante Pasionek created: com.atomic.nexus.commands on Aug. 08, 2014 *
 */
public class BeginCommand implements Command {

    String[] help = {"Begins Nexus!", "No command to start, simple say 'Hello'!"};
    String[] aliases = {"begin", "hello", "hi"};

    boolean toggle = false;

    public void execute(String[] args) {
        /* OK! WOO! LET'S DO SOMETHING HERE! */

    }

    public String[] getHelp() {
        return help;
    }

    public String[] getAliases() {
        return aliases;
    }

    public Authority getAuthority() {
        return Authority.USER;
    }

    public String getName() {
        return "start";
    }
}
