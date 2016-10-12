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
 * @param <T>
 */
public abstract class AbstractResponse<T>  {
    
    public abstract T getResult();
    
    public abstract void extractResultsFromBody(Element element) throws Exception;
    
    
}
