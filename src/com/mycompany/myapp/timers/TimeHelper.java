/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.timers;

/**
 *
 * @author Przemo
 */
public class TimeHelper {
    
    public static String milisToTime(long pt){
            int s = (int) (pt/1000);
            int h = (int)(s/3600);
            s-=h*3600;
            int min = (int)(s/60);
            s-=min*60;
            int seconds=s;
            String sh= String.valueOf(h);
            String sm = String.valueOf(min);
            String ss = String.valueOf(seconds);
            
            return new StringBuilder().append(sh.length()<2 ? "0"+sh : sh).append(":").append(sm.length()<2? "0"+sm:sm).append(":")
                    .append(ss.length()<2?"0"+ss:ss).toString();
    }
}
