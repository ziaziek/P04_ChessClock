/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.events;

import com.mycompany.myapp.Game;

/**
 *
 * @author Przemo
 */
public class PlayerChangedEvent {
    
    private final Game currentGame;

    public Game getCurrentGame() {
        return currentGame;
    }
    
    public PlayerChangedEvent(final Game g){
        this.currentGame=g;
    }
}
