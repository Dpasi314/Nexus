package com.atomic.nexus;

import com.atomic.nexus.commands.util.Command;
import com.atomic.nexus.commands.util.CommandManager;
import com.atomic.nexus.util.Input;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Dante Pasionek created: com.atomic.nexus on Jul. 25, 2014 *
 */

public class Nexus {

    private static Nexus instance = null;

    /**
     * Register all commands here first!
     */
    private static List<Command> commands = new ArrayList<>();

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


    }
    public void nullInstance() {
        instance = null;
    }


    public static Nexus getInstance() {
        return instance;
    }

    public static List<Command> getAllRegisteredCommands() {
        return commands;
    }

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

    public static void test() {

    }
}
