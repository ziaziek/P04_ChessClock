/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.Game;
import com.mycompany.myapp.events.IGameChangedStateEventListener;
import com.mycompany.myapp.events.IPlayerChangedListener;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Przemo
 */
public class MainScreen extends Container implements IGameChangedStateEventListener, ActionListener<ActionEvent>{
    
    private final Set<IPlayerChangedListener> playerChangedListeners = new HashSet<>();

    public Set<IPlayerChangedListener> getPlayerChangedListeners() {
        return playerChangedListeners;
    }
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
        whiteTime = new Label("10");
        blackTime = new Label("10");
        whiteTime.setUnselectedStyle(UIManager.getInstance().getComponentStyle("whiteTime"));
        blackTime.setUnselectedStyle(UIManager.getInstance().getComponentStyle("blackTime"));
        c.add(tl.createConstraint().widthPercentage(100).heightPercentage(28), whiteTime);
        c.add(tl.createConstraint().widthPercentage(100).heightPercentage(28), blackTime);
        add(c);
        
        Button vb = new Button("");
        vb.addActionListener(this);
        vb.setUnselectedStyle(UIManager.getInstance().getComponentStyle("ibutton"));
        add(vb);
    }

    @Override
    public void update(Game game) {
        System.out.println("com.mycompany.myapp.gui.MainScreen.update()");
       whiteTime.setText(String.valueOf(game.getPlayerTime(0)));
       blackTime.setText(String.valueOf(game.getPlayerTime(1)));
       whiteTime.repaint();
       blackTime.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource().getClass()==Button.class)
            for(IPlayerChangedListener pl : playerChangedListeners){
                pl.onPlayerChanged(null);
            }
    }
}
