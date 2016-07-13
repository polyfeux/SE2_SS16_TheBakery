package org.hbrs.se2.junit.DTOtest;

import org.hbrs.se2.model.objects.dto.Kategorie;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitKategorieTestDTO {
    
    private static Kategorie testKategorie;
    
    @Before
    public void setUp(){
        testKategorie = new Kategorie();
        testKategorie.setId(100);
        testKategorie.setLabel("Test Label");
        testKategorie.setName("Test Name");
        testKategorie.setBeschreibung("Test Beschreibung");
    }
    
    @Test
    public void testGetMethoden(){
        assertEquals(100,testKategorie.getId());
        assertEquals("Test Label",testKategorie.getLabel());
        assertEquals("Test Name",testKategorie.getName());
        assertEquals("Test Beschreibung", testKategorie.getBeschreibung());
    }
}