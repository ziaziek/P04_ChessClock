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
public class StatusRequest extends AbstractRequest {

    private final String gt;
    
    public StatusRequest(final String gameToken){
        this.gt=gameToken;
    }
    
    @Override
    public String getRequestBody() {
        return "<tns:status><gkey>"+gt+"</gkey></tns:status>";
    }
    
}
