package com.atomic.nexus.commands;

import com.atomic.nexus.commands.util.Command;
import com.atomic.nexus.enums.Authority;
import com.atomic.nexus.util.Input;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Dante Pasionek created: com.atomic.nexus.commands on Aug. 20, 2014 *
 */
public class BugCommand implements Command {

    String[] help = {"Reports a bug.", "/report [description of the issue]"};
    String[] aliases = {"bug", "breport", "bugreport"};

    /**
     * Created a bug report.
     * @param args - Command arguments
     */
    public void execute(String[] args) {

        String desc = null;
        for(int i = 0; i < args.length; i++) {
            desc += args[i] + " ";
        }

        String ID = "NEXUS-" + getRandom();

        File file = new File("reports/reports.data");
        if(!file.exists()) {
            try {
                file.getAbsoluteFile().createNewFile();
            } catch (IOException e) {
                System.out.println("ERROR: Couldn't create file!");
            }
        }


        PrintWriter p = null;
        try { p = new PrintWriter(new FileOutputStream(file, true)); }
        catch (Exception e) { e.printStackTrace(); }


        SimpleDateFormat sd = new SimpleDateFormat("EEE dd MMM YYYY - HH:mm:ss a (zzz)");
        Date date = new Date();

        p.print("\n---------------------------------\n");
        p.print("Report ID: " + ID + "\n");
        p.print("Report Date: " + sd.format(date) + "\n");
        p.print("Description: " + desc.replaceAll("null", "") + "\n");
        p.print("Status: WAITING" + "\n");
        p.print("---------------------------------" + "\n");
        p.close();

        Input i = Input.getCurrentInstance();
        i.output("=== Nexus Bug Report (" + ID + ") ===");
        i.output("The Bug Report, " + ID + ", was successfully created!");
        i.output("This report will be reviewed shortly! Thanks for making Nexus Better!");
        i.output("=== Nexus Bug Report (END) ===");
        i.output("\n");
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
        return "report";
    }

    private String getRandom() {
        Random rand = new Random();
        String[] i = new String[4];
        String f = null;
        i[0] = String.valueOf(rand.nextInt(9));
        i[1] = String.valueOf(rand.nextInt(9));
        i[2] = String.valueOf(rand.nextInt(9));
        i[3] = String.valueOf(rand.nextInt(9));

        for(int j = 0; j < i.length; j++) {
            f += i[j];
        }

        return f.replaceAll("null", "");
    }
}
