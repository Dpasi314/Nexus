package com.atomic.nexus.commands.util;

import com.atomic.nexus.enums.Authority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Dante Pasionek created: com.atomic.nexus.commands.util on Aug. 08, 2014 *
 */
public class CommandManager {

    public static HashMap<String, Command> map = new HashMap<>();
    public static HashMap<String, Command> calias = new HashMap<>();

    public static void registerCommand(Command command) {
        map.put(command.getName(), command);
        for(String alias : command.getAliases()) {
            calias.put(alias, command);
        }

    }

    public static void unregisterCommand(String name) {
        map.remove(name);
    }

    public static void unregisterAll() {
        map.clear();
    }

    public static Command getCommand(String name) {
        return (map.get(name) != null) ? map.get(name) : null;
    }

    public static Command getCommandFromAlias(String alias) {
        return (calias.get(alias) != null) ? calias.get(alias) : null;
    }

    public static boolean checkAuth(Authority auth, Command command) {
        return (command.getAuthority().equals(auth));
    }

    public static boolean checkAuth(Authority auth, String name) {
        Command c = map.get(name);
        return (c.getAuthority().equals(auth));
    }

    public static boolean validate(String command, String alias) {
        Command c = getCommand(command);
        String[] aliases = c.getAliases();
        for(int i = 0; i < aliases.length; i++) {
            if(alias.equalsIgnoreCase(aliases[i])) return true;
        }

        return false;
    }

    public static int size() {
        return map.size();
    }



}
