/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.myapp.Game;
import com.mycompany.myapp.timers.SimpleDefaultTimer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Przemo
 */
public class SimpleTests {
    
    
    private Game game;
    private SimpleDefaultTimer timer;
    
    public SimpleTests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test(){
        SimpleDefaultTimer t = new SimpleDefaultTimer();
        Game g = new Game(t);
        t.setGame(g);
        g.setTimeForGame(10*60*1000, 0); //10 minutes per player
        Assert.assertEquals(0, g.getX());
        Assert.assertEquals(10*60*1000, g.getPlayerTime(0));
        Assert.assertEquals(10*60*1000, g.getPlayerTime(1));
        g.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            
        }
        g.onPush();
        Assert.assertEquals(10*60*1000 - 2000, g.getPlayerTime(0));
        Assert.assertEquals(10*60*1000, g.getPlayerTime(1));
        Assert.assertEquals(1, g.getX());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            
        }
        g.onPush();
        Assert.assertEquals(10*60*1000 - 2000, g.getPlayerTime(0));
        Assert.assertEquals(10*60*1000-4000, g.getPlayerTime(1));
        Assert.assertEquals(0, g.getX());
        g.stop();
    }
}
