/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.timers;

import com.mycompany.myapp.Game;
import com.mycompany.myapp.events.IGameChangedStateEventListener;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Przemo
 */
public class SimpleDefaultTimer implements Runnable{

    private Game game;
    private final Set<IGameChangedStateEventListener> gameListeners = new HashSet<>();

    public Set<IGameChangedStateEventListener> getGameListeners() {
        return gameListeners;
    }
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    @Override
    public void run() {
        game.addTimeForPlayer(game.getX(), -game.getInterval());
        for(IGameChangedStateEventListener gl: gameListeners){
            gl.update(game);
        }
    }
    
}
