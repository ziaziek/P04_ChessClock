/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;

/**
 *
 * @author Przemo
 */
public class MainScreen extends Container {
    
    private Label whiteTime, blackTime;
    private Label moves;
    
    public MainScreen(){
        buildScreen();
        
    }
    
    private void buildScreen(){
        setLayout(new LayeredLayout());
        TableLayout tl = new TableLayout(3,1);
        tl.setGrowHorizontally(true);
        Container c = new Container(tl);
        moves = new Label("0");
        moves.setUnselectedStyle(UIManager.getInstance().getComponentStyle("Tab"));
        c.add(tl.createConstraint().heightPercentage(10), moves);
        whiteTime = new Label("00:00:00");
        blackTime = new Label("00:00:00");
        whiteTime.setUnselectedStyle(UIManager.getInstance().getComponentStyle("whiteTime"));
        blackTime.setUnselectedStyle(UIManager.getInstance().getComponentStyle("blackTime"));
        c.add(tl.createConstraint().widthPercentage(100).heightPercentage(28), whiteTime);
        c.add(tl.createConstraint().widthPercentage(100).heightPercentage(28), blackTime);
        add(c);
        
        Button vb = new Button("");
        vb.setUnselectedStyle(UIManager.getInstance().getComponentStyle("ibutton"));
        add(vb);
    }
}
