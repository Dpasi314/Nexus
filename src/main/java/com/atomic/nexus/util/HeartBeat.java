package com.atomic.nexus.util;


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

    public HeartBeat() {
        run();
    }

    public void run() {
        timer = new Timer("HeartBeat");
        listener = new Timer("Listener");
        timer.scheduleAtFixedRate(new Beat(), 0, 10000);
        listener.scheduleAtFixedRate(new Listen(), 0, 10500);
    }

    public static void beat() {
        alive = true;
        beats++;
        System.out.println("Nexus sent a HeartBeat!");
    }

    public static int getBeats() {
        return beats;
    }

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

class Listen extends TimerTask {
    public void run() {
        HeartBeat.check();
    }
}

class Beat extends TimerTask {
    public void run() {
        HeartBeat.beat();
    }
}
