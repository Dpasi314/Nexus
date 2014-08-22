package com.atomic.nexus.commands;

import com.atomic.nexus.Nexus;
import com.atomic.nexus.commands.util.CommandManager;
import com.atomic.nexus.enums.Authority;
import com.atomic.nexus.commands.util.Command;
import com.atomic.nexus.exceptions.CommandSyntaxException;
import com.atomic.nexus.util.Input;

/**
 * Dante Pasionek created: com.atomic.nexus.commands on Aug. 08, 2014 *
 */
public class StopCommand implements Command {

    String[] help = {"Shuts down Nexus", "Command Usage: /stop"};
    String[] aliases = {"shutdown", "terminate", "die", "end", "done"};
    boolean debug = false;
    int exit = 0;

    public void execute(String[] args) throws CommandSyntaxException {
        if(args.length > 2) {
            throw new CommandSyntaxException("Command Syntax - shutdown [-d, -e] [int exit code]", this.getClass());
        }

        if(args.length >= 1 && args.length < 3) {
            if(args[0].equalsIgnoreCase("-d")) {
                debug = true;
            }

            if(args[0].equalsIgnoreCase("-e")) {
                exit = Integer.parseInt(args[1]);
            }
        }
        Input i = Input.getCurrentInstance();

        if(debug) System.out.println("Unregistering all commands... (Process 1)");

        CommandManager.unregisterAll();

        if(debug) System.out.println("Process 1 - Finished");


        if(debug) System.out.println("Getting Nexus instance, and nullifing... (Process 2)");
        Nexus nexus = Nexus.getInstance();
        nexus.nullInstance();

        if(debug) System.out.println("Process 2 - Finished");

        if(debug) System.out.println("Shutting down... (Process 3)");
        System.exit(exit);
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
        return "stop";
    }


}
