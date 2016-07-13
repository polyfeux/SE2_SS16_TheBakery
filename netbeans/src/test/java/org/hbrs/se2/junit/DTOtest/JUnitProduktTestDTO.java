package org.hbrs.se2.junit.DTOtest;

import org.hbrs.se2.model.objects.dto.Produkt;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitProduktTestDTO {
    
    private static Produkt testProdukt;
    
    @Before
    public void setUp(){
        testProdukt = new Produkt(100, 10);
        
        testProdukt.setStatus("Test Status");
        testProdukt.setName("Test Name");
        testProdukt.setKategorieId(14);
        testProdukt.setShopId(14);
        testProdukt.setBeschreibung("Test Beschreibung");
        testProdukt.setPreis(100);
    }
    
    @Test
    public void testGetMehtoden(){
        assertEquals("Test Beschreibung",testProdukt.getBeschreibung());
        assertEquals("Test Name", testProdukt.getName());
        assertEquals(14,testProdukt.getShopId());
        assertEquals(14, testProdukt.getKategorieId());
        assertEquals("Test Beschreibung", testProdukt.getBeschreibung());
   
        
    }
}