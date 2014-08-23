package com.atomic.nexus.commands.util;


import java.util.Arrays;

/**
 * Dante Pasionek created: com.atomic.nexus.commands.util on Aug. 08, 2014 *
 */
public class CommandHelper {

    public CommandHelper() { }

    /**
     * Returns a boolean (If the command has args or not)
     * @param command
     * @return boolean
     */
    public static boolean hasArgs(String command) {
        String[] args = command.split(" ");
        if(args.length >= 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns String array of all the arguments in a command
     * Excludes the command itself; easier for the command class to parse.
     * @param command
     * @return String[]
     */
    public static String[] getArgs(String command) {
        String[] x = {};
        command = command.replaceAll("/[a-zA-Z]* ", "");
        command = command.replaceAll("/[a-zA-Z]*", " ");
        String[] args = command.split(" ");
        if(args.length == 0) {
            args = x;
        }

        return args;

    }


    /**
     * Returns the root command; no arguments
     * @param command
     * @return String
     */
    public static String getRoot(String command) {
        String[] args = command.split(" ");

        return args[0];
    }
}
