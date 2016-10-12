/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.connections.domain;

import com.codename1.xml.Element;
import com.mycompany.myapp.Game;

/**
 *
 * @author Przemo
 */
public class StatusResponse extends AbstractResponse<Game> {

    private Game game;
    
    @Override
    public Game getResult() {
        return game;
    }

    @Override
    public void extractResultsFromBody(Element element) {
        System.out.println(""+element.toString());
    }
    
}
