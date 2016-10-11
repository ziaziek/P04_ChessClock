/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.connections;

import com.codename1.io.ConnectionRequest;
import com.codename1.xml.Element;
import com.codename1.xml.XMLParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 *
 * @author Przemo
 */
public class WebServiceConnection {
    
    private final String connectionUrl = "http://localhost:8080/GService/game";
    
    private ConnectionRequest req;
    
    private Element responseBody;
    
    public WebServiceConnection(final String inputParameter){
        req= new ConnectionRequest(connectionUrl){
            @Override
            protected void buildRequestBody(OutputStream os) throws IOException {
                os.write(inputParameter.getBytes("UTF-8"));
            }

            @Override
            protected void readResponse(InputStream input) throws IOException {
                synchronized(WebServiceConnection.this){
                XMLParser parser = new XMLParser();
                responseBody= parser.parse(new InputStreamReader(input));
                }
            }
            
            
                       
        };
        req.setContentType("text/xml");
        req.setPost(true);
    }
    
    
    public Element getResponseBody(){
        return responseBody;
    }
    
    public ConnectionRequest getConnectionRequest(){
        return req;
    }
}
