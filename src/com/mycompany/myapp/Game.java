/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

/**
 *
 * @author Przemo
 */
public class Game {

    int x = -1, added = 0, semiMovesCounter = 0, movesCounter = 1;
    String gameToken, playerToken;

    public String getGameToken() {
        return gameToken;
    }

    public void setGameToken(String gameToken) {
        this.gameToken = gameToken;
    }

    public String getPlayerToken() {
        return playerToken;
    }

    public void setPlayerToken(String playerToken) {
        this.playerToken = playerToken;
    }
    
    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    private PaceSettings pace = new PaceSettings();

    public PaceSettings getPace() {
        return pace;
    }

    public void setPace(PaceSettings pace) {
        this.pace = pace;
    }
    
    public int getMovesCounter() {
        return movesCounter;
    }

    long[] ptime = new long[2]; //array of players' remaining times
    private boolean running;
    private boolean paused;

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isRunning() {
        return running;
    }

    public int getX() {
        return x;
    }

    public Game() {
        
    }

    public void addTimeForPlayer(int x, long time) {
        
        if(ptime[x]<=0){
            ptime[x]=0;
        } else {
           ptime[x] += time; 
        }
    }

    public long getPlayerTime(int x) {
        return ptime[x];
    }

    public void setTimeForGame(long totaalTime, int added) {
        for (int i = 0; i < ptime.length; i++) {
            ptime[i] = totaalTime;  
        }
        this.added = added;
    }

    public void onPush() {
        if (running) {
            x = (x + 1) % 2;
            ptime[x] += added;
            semiMovesCounter++;
            movesCounter = Math.max(1, (semiMovesCounter+1) / 2);
        } else {
            start();
        }
    }

    public void start() {
        paused=false;
        running = true;
    }

    public void stop() {
        running = false;
        paused=false;
    }
    
    public void pause(){
        running=true;
        paused=true;
    }
    
}
