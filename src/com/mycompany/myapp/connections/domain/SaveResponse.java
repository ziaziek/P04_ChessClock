/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.connections.domain;

import com.codename1.xml.Element;
import com.codename1.xml.XMLParser;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Przemo
 */
public class SaveResponse {

    private List<String> tokens = new ArrayList<>();

    public List<String> getTokens() {
        return tokens;
    }

    public SaveResponse(String resp) {
        XMLParser parser = new XMLParser();
        try {
            Element e = parser.parse(new InputStreamReader(new ByteArrayInputStream(resp.getBytes("UTF-8"))));
            Element el = e.getChildAt(0).getChildAt(0);
            tokens = el.getChildrenByTagName("return");
        } catch (UnsupportedEncodingException ex) {

        }

    }

    public SaveResponse(Element resp) {
        Element el = resp.getChildAt(0).getChildAt(0);
            List<Element> v = el.getChildrenByTagName("return");
            for (Element element : v) {
                tokens.add(element.getChildAt(0).getText());
            }

    }
}
