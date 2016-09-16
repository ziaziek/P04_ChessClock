/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.myapp.timers.TimeHelper;
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
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
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
        int h = 15; int m = 37; int s = 10;
        long v = h*3600+m*60+s;
        String ss = TimeHelper.milisToTime(v*1000);
        System.out.println(v);
        System.out.println(ss);
        String[] k = ss.split(":");
        Assert.assertTrue(h==Integer.valueOf(k[0]));
        Assert.assertTrue(m==Integer.valueOf(k[1]));
        Assert.assertTrue(s==Integer.valueOf(k[2]));
    }
}
