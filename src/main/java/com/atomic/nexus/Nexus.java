package com.atomic.nexus;

import com.atomic.nexus.commands.util.Command;
import com.atomic.nexus.commands.util.CommandManager;
import com.atomic.nexus.util.HeartBeat;
import com.atomic.nexus.util.Input;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Dante Pasionek created: com.atomic.nexus on Jul. 25, 2014 *
 */

public class Nexus {

    /**
     * The current alive instance of Nexus.
     */

    private static Nexus instance = null;

    /**
     * List<Command> - List of all registered commands.
     */
    private static List<Command> commands = new ArrayList<>();


    /**
     * Main method, initializes Nexus
     * @param args
     */
    public static void main(String[] args) {
        try {
            instance = Nexus.class.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        register();

        Input i = new Input("Nexus", 300, 300);
        i.createWindow();
        i.display();

        new HeartBeat();


    }

    /**
     * Nulls the current instance of Nexus, used for program termination.
     */
    public void nullInstance() {
        instance = null;
    }


    /**
     * @return Nexus - Returns current instance of Nexus that was set in the main
     */
    public static Nexus getInstance() {
        return instance;
    }

    /**
     * Deprecated - CommandManager handles this need.
     * @return List<Command> returns a list of all registered commands. Not needed.
     */
    @Deprecated
    public static List<Command> getAllRegisteredCommands() {
        return commands;
    }

    /**
     * Registers all commands. Command classes implement the class command#util#Command.java
     * Method searches for those classes, and then registeres them in the CommandManager.java class
     */
    private static void register() {
        Reflections reflections = new Reflections("com.atomic.nexus.commands");
        Set<Class<? extends Command>> cmds = reflections.getSubTypesOf(Command.class);

        for(Class<? extends Command> cc : cmds) {
            try { commands.add(cc.newInstance()); }
            catch (Exception e) { e.printStackTrace(); }
        }

        for(Command c : commands) {
            CommandManager.registerCommand(c);
            System.out.println("Registered Command: " + c.getName() + "  |  Location: (" +c.getClass()+ ")");
        }
    }

    /**
     * Because test.
     */
    public static void test() {

    }

    /**
     * Ping method the HeartBeat class uses to check if the program is responding.
     * @return String - Pong!
     */
    public static String ping() {
        return "pong";
    }
}
