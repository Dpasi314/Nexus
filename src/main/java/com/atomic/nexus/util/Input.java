package com.atomic.nexus.util;

import com.atomic.nexus.commands.util.Command;
import com.atomic.nexus.commands.util.CommandHelper;
import com.atomic.nexus.commands.util.CommandManager;
import com.atomic.nexus.exceptions.CommandSyntaxException;
import com.atomic.nexus.exceptions.UnknownCommandException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

/**
 * Dante Pasionek created: com.atomic.nexus.util on Jul. 25, 2014 *
 */
public class Input extends JFrame implements KeyListener {

    /**
     * The JFrame for the GUI
     */
    JFrame frame = null;

    /**
     * TextAreas for the User to type and Nexus to respond.
     */
    JTextArea textArea = null, userInput = null;

    /**
     * Panels where the TextAreas will be located
     */
    JPanel upper = null, lower = null;

    /**
     * The title before Nexus' TextArea
     */
    String buffer = "                                               \n\n";

    /**
     * The label before Nexus' TextArea
     */
    JLabel textLabel = new JLabel(buffer + "This is where Nexus will respond!");

    /**
     * Current instance of the Input class; Used to call the Output method since this will never be null
     */
    private static Input currentInstance = null;

    /**
     * Width, Height
     */
    int w, h;

    /**
     * Initialization of the GUI
     * @param windowName
     * @param width
     * @param height
     */
    public Input(String windowName, int width, int height) {

        frame = new JFrame(windowName);

        textArea = new JTextArea(20, 50);
        userInput = new JTextArea(2, 25);

        upper = new JPanel();
        lower = new JPanel();

        textArea.addKeyListener(this);
        textArea.setEditable(false);

        textArea.setAutoscrolls(true);

        userInput.addKeyListener(this);

        w = width;
        h = height;

        currentInstance = this;


    }

    /**
     * Returns current instance to call the Output method
     * @return Input
     */
    public static Input getCurrentInstance() {
        return currentInstance;
    }

    /**
     * Creates the window and the GUI
     */
    public void createWindow() {
        frame.getContentPane().add(upper, "North");
        frame.getContentPane().add(textLabel);
        frame.getContentPane().add(lower, "South");

        upper.add(userInput);
        lower.add(textArea);

        frame.setSize(w, h);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();

        userInput.setSize(200, 200);
        textArea.setSize(500, 500);

        userInput.setLineWrap(true);
        textArea.setLineWrap(true);

        //userInput.append(getContent(0));
        //textArea.append(getContent(1));
    }

    /**
     * Displays the GUI
     */
    public void display() {
        frame.setVisible(true);
    }

    /**
     * Reads and parses the input of the UserArea
     * Currently only contains commands and whatnot.
     * @throws UnknownCommandException
     */
    public void read() throws UnknownCommandException {

        /**
         * General rule that if the message begins with a slash, it's most likely going to be a command
         */
        if(userInput.getText().contains("/")) {
           /* Commands are going to be the whole line.
            * Then we clear the text because we may want another command
            * Finally we get any arguments, and then parse the commands
            */
            String command = userInput.getText();

            userInput.setText("");

            String[] args = CommandHelper.getArgs(command);

            String Icommand = CommandHelper.getRoot(command).replace("/", "");

            Command c = CommandManager.getCommand(Icommand);

            if(c == null) {
                c = CommandManager.getCommandFromAlias(Icommand);
                if(c == null) {
                    throw new UnknownCommandException("The command \"" + command + "\" is unknown. Type /list for a command list!", this.getClass());
                }
            }

            try { c.execute((args)); }
            catch (CommandSyntaxException e) { e.printStackTrace(); }
        }
    }

    /**
     * Outputs things into the Nexus text area.
     * @param string
     */
    public void output(String string) {
        textArea.append(string + System.getProperty("line.separator"));
    }

    /**
     * Sends an error to the Nexus console
     * @param string
     * @param clazz
     */
    public void err(String string, Class clazz) {
        textArea.append("\n === Nexus Error Information (Start) ===\n");
        textArea.append(string + System.getProperty("line.separator"));
        textArea.append("Error at: " + clazz.getClass().getName() + "\n");
        textArea.append("=== Nexus Error Information (End) === \n");
        textArea.append("\n");
    }


    /**
     * java.awt.event#KeyListener required methods
     */

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_ENTER) {
            try {
                read();
                e.consume();
            } catch (UnknownCommandException ee) {
                ee.printStackTrace();
                e.consume();
            }
        }
        if(code == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    /**
     * Not needed anymore!
     * @param area
     * @return
     */
    @Deprecated
    public String getContent(int area) {
        /**
         * 0 = USER
         * 1 = Nexus
         */
        switch (area) {
            case 0:
                return "This is the area where you can respond to Nexus' questions! Go ahead and type \"Hello!\" to start\n\r";
            case 1:
                return "This is the area where Nexus will return any results he finds regarding your symptoms!\n\r";
            default:
                return "Wat";
        }

    }

}
