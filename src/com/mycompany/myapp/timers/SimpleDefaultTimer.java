/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.timers;

import com.mycompany.myapp.events.ITimerUpdateEventListener;
import java.util.HashSet;
import java.util.Set;
import java.util.TimerTask;

/**
 *
 * @author Przemo
 */
public class SimpleDefaultTimer extends TimerTask{

    private int interval=1000;

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
    private final Set<ITimerUpdateEventListener> gameListeners = new HashSet<>();

    public Set<ITimerUpdateEventListener> getGameListeners() {
        return gameListeners;
    }
    
    @Override
    public void run() {
        for(ITimerUpdateEventListener gl: gameListeners){
            gl.update(-interval);
        }
    }
    
}
