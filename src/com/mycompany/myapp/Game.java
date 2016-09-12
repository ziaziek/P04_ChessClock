/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Przemo
 */
public class Game {

    final Runnable tc; //procedure that the timer will run
    int x = 0, added = 0, semiMovesCounter = 0, movesCounter = 1;
    long interval = 1000;
    final Timer timer;
    long[] ptime = new long[2]; //array of players' remaining times

    public long getInterval() {
        return interval;
    }

    public int getX() {
        return x;
    }

    public Game(Runnable timerCounter) {
        this.tc = timerCounter;
        this.timer = new Timer();
    }

    public Game(Runnable timerCounter, long timerPeriod) {
        this(timerCounter);
        interval = timerPeriod;
    }

    public void addTimeForPlayer(int x, long time) {
        ptime[x] += time;
    }

    public long getPlayerTime(int x){
        return ptime[x];
    }
    
    public void setTimeForGame(long totaalTime, int added) {
        for (int i = 0; i < ptime.length; i++) {
            ptime[i] = totaalTime;
            this.added = added;
        }
    }

    public void onPush() {
        ptime[x] += added;
        x = (x + 1) % 2;
        semiMovesCounter++;
        movesCounter = semiMovesCounter % 2;
    }

    public void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                tc.run();
                updateUI();
            }
        }, 0, interval);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void updateUI() {
        //TODO:Implement properly when building GUI
        for (int i = 0; i < ptime.length; i++) {
            System.out.println("Player " + i + " time:" + ptime[i]);
            System.out.println("Moves: " + movesCounter);
        }
    }
}
