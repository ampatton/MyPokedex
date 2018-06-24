/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weatherapplication2;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Austin M. Patton
 */
public class FXMLDocumentControllerIT {
    
    public FXMLDocumentControllerIT() {
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

    /**
     * Test of updateTime method, of class FXMLDocumentController.
     */
    
    @Test
    public void testUpdateTime() throws InterruptedException{
        System.out.println("updateTime");
        //FXMLDocumentController instance = new FXMLDocumentController();
        //instance.updateTime();
        
        
        LocalDateTime timePoint = LocalDateTime.now();
        int seconds;
        int seconds2;
        int minutes;
        int hours;
        // TODO review the generated test code and remove the default call to fail.
        seconds = timePoint.getSecond();
        minutes = timePoint.getMinute();
        hours = timePoint.getHour();
        
        assertNotNull(seconds);
        assertNotNull(minutes);
        assertNotNull(hours);
        
        Thread.sleep(1000);
        timePoint = LocalDateTime.now();
        seconds2 = timePoint.getSecond();
        
        assertNotNull(seconds2);
        assertNotSame(seconds, seconds2);//checks to make sure that it is not recieving the same time since its a second later
        
    }

    /**
     * Test of initialize method, of class FXMLDocumentController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL url = null;
        ResourceBundle rb = null;
        FXMLDocumentController instance = new FXMLDocumentController();
        //instance.initialize(url, rb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
