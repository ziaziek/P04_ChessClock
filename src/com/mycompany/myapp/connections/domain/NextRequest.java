/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.connections.domain;

/**
 *
 * @author Przemo
 */
public class NextRequest extends AbstractRequest{

    final String pkey; final String gkey; final String ptime;
    
    public NextRequest(final String pkey, final String gkey, final String ptime){
        this.pkey=pkey;
        this.gkey=gkey;
        this.ptime=ptime;
    }
    
    @Override
    public String getRequestBody() {
        return "<tns:next><pkey>"+pkey+"</pkey><gkey>"+gkey+"</gkey><ptime>"+ptime+"</ptime></tns:next>";
    }
    
}
