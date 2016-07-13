package org.hbrs.se2.junit.DTOtest;

import org.hbrs.se2.model.objects.dto.Adresse;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitAdresseTest {
    
    private static Adresse testAdresse;

    
    @Before
    public void setUp(){
        testAdresse = new Adresse();
        testAdresse.setId(1);
        testAdresse.setStadt("Berlin");
        testAdresse.setOrtsteil("Moabit");
        testAdresse.setPlz("12345");
        testAdresse.setStrasse("Test Straße");
        testAdresse.setHausnr("10");
        testAdresse.setBundesland("NRW");
        testAdresse.setLand("Deutschland");
    }
    
    @Test
    public void testGetMethdoen(){
        
        
        assertEquals(1,testAdresse.getId());
        assertEquals("Berlin",testAdresse.getStadt());
        assertEquals("Moabit",testAdresse.getOrtsteil());
        assertEquals("12345",testAdresse.getPlz());
        assertEquals("Test Straße",testAdresse.getStrasse());
        assertEquals("10", testAdresse.getHausnr());
        assertEquals("NRW", testAdresse.getBundesland());
        assertEquals("Deutschland", testAdresse.getLand());
    }
}