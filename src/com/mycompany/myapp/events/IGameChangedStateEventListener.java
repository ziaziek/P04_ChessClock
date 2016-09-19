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
public interface IGameChangedStateEventListener {
    
    void update(final Game game);
    
}
