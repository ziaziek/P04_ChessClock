/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.mycompany.myapp.Game;
import com.mycompany.myapp.connections.domain.AbstractResponse;
import com.mycompany.myapp.connections.domain.JoinRequest;
import com.mycompany.myapp.connections.domain.JoinResponse;
import com.mycompany.myapp.events.IGameSavedListener;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Przemo
 */
public class JoinGameScreen extends ChessClockForm {
    
    private TextField gameTokenTextField;
    private final Game game;
    private Set<IGameSavedListener> listeners = new HashSet<>();

    public Set<IGameSavedListener> getListeners() {
        return listeners;
    }
    
    public JoinGameScreen(final Game game){
        this.game=game;
        initForm();
    }

    @Override
    protected void initForm() {
        this.setLayout(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        this.add(BorderLayout.NORTH, new Label("Enter Game Token"));
        gameTokenTextField = new TextField(7);
        this.add(BorderLayout.CENTER, gameTokenTextField);
        Button bOK = new Button("OK");
        this.add(BorderLayout.SOUTH, bOK);
        bOK.addActionListener((ActionListener) (ActionEvent evt) -> {
            JoinRequest jr = new JoinRequest(gameTokenTextField.getText());
            try {
                makeConnectionWithGameUpdate(jr, game, new JoinResponse());
            } catch (Exception ex) {
                Dialog.show("Game locked", ex.getMessage(), "OK", "Cancel");
                //Logger.getLogger(JoinGameScreen.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    protected void updateGameFromResponse(AbstractResponse resp) {
        if(resp instanceof JoinResponse){
            JoinResponse jr = (JoinResponse)resp;
            game.setGameToken(gameTokenTextField.getText());
            game.setPlayerToken(jr.getResult().get(JoinResponse.PLAYER_KEY));
            game.setTimeForGame(Long.parseLong(jr.getResult().get(JoinResponse.TIME_KEY)), 0); //TODO: Implement the added param as well.
            for(IGameSavedListener l: listeners){
                l.gameSaved(game);
            }
        }
    }
}
