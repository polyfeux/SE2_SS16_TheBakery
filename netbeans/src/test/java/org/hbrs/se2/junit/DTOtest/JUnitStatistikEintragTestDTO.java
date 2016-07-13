package org.hbrs.se2.junit.DTOtest;

import org.hbrs.se2.model.objects.dto.StatistikEintrag;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitStatistikEintragTestDTO {
    
    private static StatistikEintrag testEintrag;
    
    @Before
    public void setUp(){
        testEintrag = new StatistikEintrag("test Statistik",2);
        
    }
    
    @Test
    public void testGetMehtoden(){
        assertEquals("test Statistik", testEintrag.getName());
        assertEquals(2, testEintrag.getAnzahl());
    }
    
    
}