package com.atomic.nexus.commands.util;

import com.atomic.nexus.enums.Authority;
import com.atomic.nexus.exceptions.CommandSyntaxException;

/**
 * Dante Pasionek created: com.atomic.nexus.commands on Aug. 08, 2014 *
 */
public interface Command {

    public void execute(String... args) throws CommandSyntaxException;

    public String[] getHelp();

    public String[] getAliases();

    public Authority getAuthority();

    public String getName();

}
