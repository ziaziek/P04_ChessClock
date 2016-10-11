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
public abstract class AbstractRequest {
    
    protected String requestScaffold1 = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:tns=\"http://services/\">" 
                                       +"   <soap:Header/>" 
                                       +"   <soap:Body>" ;
    protected String requestScaffold2 ="   </soap:Body>" 
                                       +"</soap:Envelope>";
    
    public abstract String getRequestBody();
    
    public String getRequest(){
        return requestScaffold1+getRequestBody()+requestScaffold2;
    }
}
