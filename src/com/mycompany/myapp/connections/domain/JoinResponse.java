/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.connections.domain;

import com.codename1.xml.Element;

/**
 *
 * @author Przemo
 */
public class JoinResponse {
    
    private String playerToken;

    public String getPlayerToken() {
        return playerToken;
    }
    
    public JoinResponse(final Element el){
        Element el1 = el.getChildAt(0).getChildAt(0);
        playerToken = ((Element)(el1.getChildrenByTagName("return").get(0))).getText();
    }
}
