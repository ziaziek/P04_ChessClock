/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.mycompany.myapp.Game;
import com.mycompany.myapp.events.IPlayerChangedListener;
import com.mycompany.myapp.events.ITimerUpdateEventListener;
import com.mycompany.myapp.timers.SimpleDefaultTimer;
import com.mycompany.myapp.timers.TimeHelper;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;


/**
 *
 * @author Przemo
 */

public class MainScreen extends Container implements ITimerUpdateEventListener, ActionListener<ActionEvent>{
    
    private final Set<IPlayerChangedListener> playerChangedListeners = new HashSet<>();
    private SimpleDefaultTimer timer = new SimpleDefaultTimer();
    private Timer time;
    public Set<IPlayerChangedListener> getPlayerChangedListeners() {
        return playerChangedListeners;
    }

    private Label whiteTime, blackTime;
    private Label moves;
    private final Game game;

    public MainScreen(final Game game, final SimpleDefaultTimer timer) {
        this.game = game;
        this.timer=timer;
        timer.getGameListeners().add(this);
        buildScreen();
        Display.getInstance().setBuiltinSoundsEnabled(true);
        Display.getInstance().setScreenSaverEnabled(true);
    }

    private void buildScreen() {
        if (game != null) {
            setLayout(new LayeredLayout());
            TableLayout tl = new TableLayout(3, 1);
            tl.setGrowHorizontally(true);
            Container c = new Container(tl);
            moves = new Label(String.valueOf(game.getMovesCounter()));
            moves.setUnselectedStyle(UIManager.getInstance().getComponentStyle("Tab"));
            c.add(tl.createConstraint().heightPercentage(10), moves);
            whiteTime = new Label(TimeHelper.milisToTime(game.getPlayerTime(0)));
            blackTime = new Label(TimeHelper.milisToTime(game.getPlayerTime(1)));
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

    }


    public void stopGame(){
        game.stop();
        time.cancel();
    }
    
    @Override
    public void update(int interv) {
       game.addTimeForPlayer(game.getX(), interv);
       whiteTime.setText(TimeHelper.milisToTime(game.getPlayerTime(0)));
       blackTime.setText(TimeHelper.milisToTime(game.getPlayerTime(1)));
       moves.setText(String.valueOf(game.getMovesCounter()));
       whiteTime.repaint();
       blackTime.repaint();
       moves.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(!game.isRunning()){
            game.start();
            try{
               time=new Timer();
               time.schedule(timer, 0, timer.getInterval()); 
            } catch(Exception ex){
                System.out.println(ex.getMessage());
            }
        }
        if(evt.getSource().getClass()==Button.class)
            for(IPlayerChangedListener pl : playerChangedListeners){
                pl.onPlayerChanged(null);
            }
        new Thread(){
            @Override
            public void run() {
                final Media tapSound;
                try {
                    tapSound = MediaManager.createMedia(com.codename1.ui.util.Resources.getGlobalResources().getData("Pat1.wav"), "audio/wav");
                    tapSound.play();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                
            }
            
        }.start();
    }

}
