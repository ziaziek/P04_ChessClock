/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.connections.domain;

import com.codename1.xml.Element;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Przemo
 */
public class JoinResponse extends AbstractResponse<Map<String, String>> {

    public static final String PLAYER_KEY = "player";
    public static final String TIME_KEY = "time";

    private Map<String, String> map;

    @Override
    public void extractResultsFromBody(Element element) throws Exception {
        System.out.println("Element: " + element);
        if (element != null && !element.isEmpty()) {
            try {
                Element v = element.getChildAt(0).getChildAt(0).getChildAt(0);
                String s = v.getChildAt(0).getText();
                if (s.indexOf(",") > 0) {
                    map = new HashMap<>();
                    map.put(PLAYER_KEY, s.substring(0, s.indexOf(",")));
                    map.put(TIME_KEY, s.substring(s.indexOf(",") + 1));
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                throw new Exception("This game cannot be joined.");
            }

        }

    }

    @Override
    public Map<String, String> getResult() {
        return map;
    }
}
