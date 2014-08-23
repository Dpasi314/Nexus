package com.atomic.nexus.commands.util;

import com.atomic.nexus.enums.Authority;
import com.atomic.nexus.exceptions.CommandSyntaxException;

/**
 * Dante Pasionek created: com.atomic.nexus.commands on Aug. 08, 2014 *
 */
public interface Command {

    /**
     * Will execute the command - called in the util#Input.java class
     * @param args - Command arguments
     * @throws CommandSyntaxException
     */
    public void execute(String... args) throws CommandSyntaxException;

    /**
     * Returns String array of an extended help list.
     * @return String[]
     */
    public String[] getHelp();

    /**
     * Returns String array of an alias list
     * @return String[]
     */
    public String[] getAliases();

    /**
     * Returns the minimum authority needed to execute command
     * @return Authority
     */
    public Authority getAuthority();

    /**
     * Returns the name of the command which will the command will be registered under
     * CommandManager handles this portion.
     * @return String
     */
    public String getName();

}
