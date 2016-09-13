/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.Game;

/**
 *
 * @author Przemo
 */
public class SetUpScreen extends Form {
   
   private Game game;
   
   /*
    Graphical data holders
   */
    TextField time_h, time_min, time_s, add_min, add_s, moves, per_min;
   /*
    Graphical data holders
   */
   
   public SetUpScreen(final Game g){
       this.game=g;
       init();
   } 
   
   private void init(){
       this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
       add(BorderLayout.NORTH, new Label("Setting up new game"));
       add(BorderLayout.CENTER, getMainSetUpPanel());
       Button bOK = new Button("OK");
       bOK.addActionListener((ActionListener) (ActionEvent evt) -> {
           saveGame();
       });
       add(BorderLayout.SOUTH,bOK);
   }

    private Component getMainSetUpPanel() {
        TableLayout tl = new TableLayout(5, 4);
        //Container settingsContainer = new Container(new GridLayout(3,1));
        Container timeSettingContainer = new Container(new TableLayout(6, 4));
        Container movesHelper = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        time_h = new TextField();
        time_h.setColumns(1);
        time_min = new TextField();
        time_min.setColumns(2);
        time_s = new TextField();
        time_s.setColumns(2);
        add_min=new TextField(1);
        add_s= new TextField(2);
        moves= new TextField(2);
        per_min = new TextField(2);
        //row 1
        timeSettingContainer.add(new Label(""));
        timeSettingContainer.add(new Label("h"));
        timeSettingContainer.add(new Label("min"));
        timeSettingContainer.add(new Label("sec"));
        //row 2
        timeSettingContainer.add(new Label("Time per player "));
        timeSettingContainer.add(time_h);
        timeSettingContainer.add(time_min);
        timeSettingContainer.add(time_s);
        //row 3
        timeSettingContainer.add(new Label(""));
        timeSettingContainer.add(new Label(""));
        timeSettingContainer.add(new Label("min"));
        timeSettingContainer.add(new Label("sec"));
        //row 4
        timeSettingContainer.add(new Label("Added per move "));
        timeSettingContainer.add(new Label(""));
        timeSettingContainer.add(add_min);
        timeSettingContainer.add(add_s);
        //row 5
        movesHelper.add(BorderLayout.WEST, new Label("Moves"));
        movesHelper.add(BorderLayout.EAST, moves);
        timeSettingContainer.add(movesHelper);
        timeSettingContainer.add(new Label("per "));
        timeSettingContainer.add(per_min);
        timeSettingContainer.add(new Label("min"));
        timeSettingContainer.add(new Label(""));
        return timeSettingContainer;
    }

    private void saveGame() {
        
    }
}
