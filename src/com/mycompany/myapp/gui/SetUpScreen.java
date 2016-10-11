/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.Game;
import com.mycompany.myapp.connections.WebServiceConnection;
import com.mycompany.myapp.connections.domain.SaveRequest;
import com.mycompany.myapp.connections.domain.SaveResponse;
import com.mycompany.myapp.events.IGameSavedListener;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Przemo
 */
public class SetUpScreen extends Form {

    private final Game game;
    private Set<IGameSavedListener> listeners = new HashSet<>();

    public Set<IGameSavedListener> getListeners() {
        return listeners;
    }

    public void setListeners(Set<IGameSavedListener> listeners) {
        this.listeners = listeners;
    }

    /*
    Graphical data holders
     */
    TextField time_h, time_min, time_s, add_min, add_s, moves, per_min;

    /*
    Graphical data holders
     */

    public SetUpScreen(final Game g) {
        this.game = g;
        init();
    }

    private void init() {
        this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        add(BorderLayout.NORTH, new Label("Setting up new game"));
        add(BorderLayout.CENTER, getMainSetUpPanel());
        Button bOK = new Button("OK");
        bOK.addActionListener((ActionListener) (ActionEvent evt) -> {
            saveGame();
            for (IGameSavedListener gl : listeners) {
                gl.gameSaved(game);
            }
        });
        add(BorderLayout.SOUTH, bOK);
    }

    private Component getMainSetUpPanel() {
        TableLayout tl = new TableLayout(5, 4);
        //Container settingsContainer = new Container(new GridLayout(3,1));
        Container timeSettingContainer = new Container(new TableLayout(6, 4));
        Container movesHelper = new Container(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER));
        time_h = new TextField("0");
        time_h.setColumns(1);
        time_min = new TextField("0");
        time_min.setColumns(2);
        time_s = new TextField("0");
        time_s.setColumns(2);
        add_min = new TextField(1);
        add_min.setText("0");
        add_s = new TextField(2);
        add_s.setText("0");
        moves = new TextField(2);
        moves.setText("0");
        per_min = new TextField(2);
        per_min.setText("0");
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
        long t0 = 1000 * (Integer.parseInt(time_h.getText()) * 3600 + Integer.parseInt(time_min.getText()) * 60 + Integer.parseInt(time_s.getText()));
        int add = 1000 * (Integer.parseInt(add_min.getText()) * 60 + Integer.parseInt(add_s.getText()));
        game.setTimeForGame(t0, add);
        game.getPace().setMoves(Integer.parseInt(moves.getText()));
        game.getPace().setPer_time(Long.parseLong(per_min.getText()));
        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        WebServiceConnection con;
        try {
            SaveRequest sreq = new SaveRequest(t0);
            con = new WebServiceConnection(sreq.getRequest());
            con.getConnectionRequest().setDisposeOnCompletion(dlg);
            NetworkManager.getInstance().addToQueueAndWait(con.getConnectionRequest());
            System.out.println(con.getResponseBody());
            SaveResponse resp = new SaveResponse(con.getResponseBody());
            game.setGameToken(resp.getTokens().get(0));
            game.setPlayerToken(resp.getTokens().get(1));
        } catch (UnsupportedEncodingException ex) {

        }

    }

}
