package com.atomic.nexus.util;


import com.atomic.nexus.Nexus;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Dante Pasionek created: com.atomic.nexus.util on Aug. 21, 2014 *
 */
public class HeartBeat extends Thread {

    Timer timer;
    Timer listener;
    static int beats = 0;
    private static int fail = 0;
    static boolean alive = false;

    /**
     * Begins thread
     */
    public HeartBeat() {
        run();
    }

    /**
     * Initializes everything
     */
    public void run() {
        timer = new Timer("HeartBeat");
        listener = new Timer("Listener");
        timer.scheduleAtFixedRate(new Beat(), 0, 10000);
        listener.scheduleAtFixedRate(new Listen(), 0, 10500);
    }

    /**
     * "Beats" Nexus' heart to show the program is still alive.
     */
    public static void beat() {
        if(Nexus.ping().equals("ping")) {
             alive = true;
             beats++;
        } else {
            alive = false;
        }

    }

    /**
     * Returns the current amount of beats. Deprecated because I will never use.
     * @return int
     */
    @Deprecated
    public static int getBeats() {
        return beats;
    }

    /**
     * Checks to see if there was a heart beat .05 seconds ago.
     */
    public static void check() {
        if(!alive) {
            fail++;
            Input i = Input.getCurrentInstance();
            i.output("[WARNING]: Nexus failed to send a HeartBeat " + fail + " time(s)!!");
        } else {
            alive = false;
        }
    }
}

/**
 * Class to handler the listener timer
 */
class Listen extends TimerTask {
    public void run() {
        HeartBeat.check();
    }
}

/**
 * Timer to handle the beat
 */
class Beat extends TimerTask {
    public void run() {
        HeartBeat.beat();
    }
}
