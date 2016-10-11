/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.connections.domain;

import com.codename1.xml.Element;
import java.util.List;

/**
 *
 * @author Przemo
 */
public class NextResponse {
    
    private long opponentTime;

    public long getOpponentTime() {
        return opponentTime;
    }
            
    public NextResponse(final Element resp){
        Element el = resp.getChildAt(0).getChildAt(0);
        List<Element> v = el.getChildrenByTagName("return");
        opponentTime = Long.parseLong(v.get(0).getText());
    }
}
