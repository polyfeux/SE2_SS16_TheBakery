package org.hbrs.se2.junit.DTOtest;

import org.hbrs.se2.junit.data.DataProdukt;
import org.hbrs.se2.model.objects.dto.BestellPosition;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitBestellPosition {

    private static BestellPosition testBestellposition;

    @Before
    public void setUp() {
        testBestellposition = new BestellPosition(DataProdukt.getDataProdukt1(), 3);

    }
    
    @Test
    public void testGetMethoden(){
       assertEquals(3,testBestellposition.getMenge());
       assertEquals("se2 test aus DAO", testBestellposition.getProdukt().getName());
    }
}