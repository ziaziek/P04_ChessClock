/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.connections.domain;

import com.codename1.xml.Element;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Przemo
 */
public class SaveResponse extends AbstractResponse<List<String>> {

    private List<String> tokens = new ArrayList<>();

    public List<String> getTokens() {
        return tokens;
    }

    @Override
    public List<String> getResult() {
        return tokens;
    }

    @Override
    public void extractResultsFromBody(Element element) {
        Element el = element.getChildAt(0).getChildAt(0);
            List<Element> v = el.getChildrenByTagName("return");
            for (Element element1 : v) {
                tokens.add(element1.getChildAt(0).getText());
            }
    }
}
