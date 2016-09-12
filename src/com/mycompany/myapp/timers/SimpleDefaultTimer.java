/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.timers;

import com.mycompany.myapp.Game;

/**
 *
 * @author Przemo
 */
public class SimpleDefaultTimer implements Runnable{

    private Game game;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    @Override
    public void run() {
        game.addTimeForPlayer(game.getX(), -game.getInterval());
    }
    
}
