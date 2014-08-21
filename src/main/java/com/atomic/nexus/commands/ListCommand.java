package com.atomic.nexus.commands;

import com.atomic.nexus.commands.util.Command;
import com.atomic.nexus.commands.util.CommandManager;
import com.atomic.nexus.enums.Authority;
import com.atomic.nexus.exceptions.CommandSyntaxException;
import com.atomic.nexus.util.Input;
import org.reflections.Reflections;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Dante Pasionek created: com.atomic.nexus.commands on Aug. 09, 2014 *
 */
public class ListCommand implements Command {

    String[] help = {"List all commands!", "Command Usage: /list [command]"};
    String[] aliases = {"help"};

    List<String> list = new ArrayList<>();
    int size = 0;
    Input i = null;


    public void execute(String[] args) throws CommandSyntaxException {
        i = Input.getCurrentInstance();
        Reflections reflections = new Reflections("com.atomic.nexus.commands");
        Set<Class<? extends Command>> cmds = reflections.getSubTypesOf(Command.class);

        i.output("=== Nexus Commands with Help and Aliases ===");
        String buffer = "  ";

        for(Class<? extends Command> cc : cmds) {
            try {
                Command c = cc.newInstance();
                String N = System.getProperty("line.separator");
                list.add(c.getName());

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if(args.length == 1) {
            String s = args[0];
            getHelpFor(s);
            return;
        }

        size = CommandManager.size();

        i.output("There is a total of " + size + " commands!");
        i.output("Use /help <command> for help with a particular command!");
        String a = null;

        for(int ii = 0; ii < list.size(); ii++) {
            a += list.get(ii);
            if(ii != list.size() - 1) a += ", ";
        }

        a = a.replaceAll("null", "");
        i.output("Commands: " + a);
        a = null;

    }



    public void getHelpFor(String arg) {
        Command c = CommandManager.getCommand(arg);
        if(c == null) {
            i.output("The command \"" + arg + "\" isn't known. Type /list for a command list!");
            return;
        }

        i.output("Help for Command: " + c.getName());
        i.output("Extended Help: " + Arrays.toString(c.getHelp()));
        i.output("Aliases: " + Arrays.toString(c.getAliases()));
        i.output("Minimum Authority: " + c.getAuthority());
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
        return "list";
    }
}
