package com.atomic.nexus.commands.util;


import java.util.Arrays;

/**
 * Dante Pasionek created: com.atomic.nexus.commands.util on Aug. 08, 2014 *
 */
public class CommandHelper {

    public CommandHelper() { }

    private static boolean hasArgs(String command) {
        String[] args = command.split(" ");
        if(args.length >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static String[] getArgs(String command) {

        command = command.replaceAll("/[a-zA-Z]* ", "");
        String[] args = command.split(" ");
        if(args.length == 1) {
            args = null;
        }

        System.out.println(Arrays.toString(args));
        return args;

    }


    public static String getRoot(String command) {
        String[] args = command.split(" ");

        return args[0];
    }
}
