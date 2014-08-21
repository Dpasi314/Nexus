package com.atomic.nexus.commands;

import com.atomic.nexus.enums.Authority;
import com.atomic.nexus.commands.util.Command;

/**
 * Dante Pasionek created: com.atomic.nexus.commands on Aug. 08, 2014 *
 */
public class BeginCommand implements Command {

    String[] help = {"Begins Nexus!", "No command to start, simple say 'Hello'!"};
    String[] aliases = {"begin", "hello", "hi"};

    public void execute(String[] args) {

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
