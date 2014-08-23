package com.atomic.nexus.commands.util;

import com.atomic.nexus.enums.Authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Dante Pasionek created: com.atomic.nexus.commands.util on Aug. 08, 2014 *
 */
public class CommandManager {

    /**
     * HashMap which contains the [Name] of a command and then its [Class]
     * Used for registering all commands.
     */
    public static HashMap<String, Command> map = new HashMap<>();

    /**
     * HashMap which contains [Name (Alias)] of a command and then its [Class]
     * Used for registering all the alias commands.
     */
    public static HashMap<String, Command> calias = new HashMap<>();

    /**
     * Command used by #Nexus.java to register all the commands in the maps above.
     * @param command - Command class registered by main class.
     */

    public static void registerCommand(Command command) {
        map.put(command.getName(), command);
        for(String alias : command.getAliases()) {
            calias.put(alias, command);
        }

    }

    /**
     * Unregisters a certain command
     * Will be used to disable a command if needed.
     * @param name
     */
    public static void unregisterCommand(String name) {
        map.remove(name);
    }

    /**
     * Unregisters all commands
     * Used for program termination
     */
    public static void unregisterAll() {
        map.clear();
    }

    /**
     * Returns a command based on the name given.
     * @param name - Name given in the util#Input.java class.
     * @return Command
     */
    public static Command getCommand(String name) {
        return (map.get(name) != null) ? map.get(name) : null;
    }

    /**
     * Returns a command based on its alias
     * @param alias - Alias given in the util#Input.java class.
     * @return Command
     */
    public static Command getCommandFromAlias(String alias) {
        return (calias.get(alias) != null) ? calias.get(alias) : null;
    }

    /**
     * Returns true or false based on the permissions of the current user
     * @param auth - Authority to check.
     * @param command - Command class
     * @return boolean
     */
    public static boolean checkAuth(Authority auth, Command command) {
        return (command.getAuthority().equals(auth));
    }

    /**
     * Returns true or false based on the permissions of the current user
     * @param auth - Authority to check.
     * @param name - Command name
     * @return boolean
     */
    public static boolean checkAuth(Authority auth, String name) {
        Command c = map.get(name);
        return (c.getAuthority().equals(auth));
    }

    /**
     * Deprecated because it will never be used.
     * @param command - Command name
     * @param alias - Command alias
     * @return boolean
     */
    @Deprecated
    public static boolean validate(String command, String alias) {
        Command c = getCommand(command);
        String[] aliases = c.getAliases();
        for(int i = 0; i < aliases.length; i++) {
            if(alias.equalsIgnoreCase(aliases[i])) return true;
        }

        return false;
    }

    /**
     * Returns the current size of the registered command list.
     * @return int
     */
    public static int size() {
        return map.size();
    }



}
