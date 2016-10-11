/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.connections.domain;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author Przemo
 */
public class SaveRequest extends AbstractRequest {
    
    private long time;
    
    public SaveRequest(final long time) throws UnsupportedEncodingException{
        this.time=time;
    }

    @Override
    public String getRequestBody() {
        return "<tns:save><time>"+String.valueOf(time)+"</time></tns:save>";
    }
}
