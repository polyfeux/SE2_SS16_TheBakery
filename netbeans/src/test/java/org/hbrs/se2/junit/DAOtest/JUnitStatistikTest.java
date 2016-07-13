package org.hbrs.se2.junit.DAOtest;

import java.util.ArrayList;
import org.hbrs.se2.model.dao.StatistikDAO;
import org.hbrs.se2.model.objects.dto.StatistikEintrag;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitStatistikTest {

    private static StatistikDAO daoStatistik;

    @Test
    public void testInstanceNotNull() {

        daoStatistik = StatistikDAO.getInstance();

        assertNotNull(daoStatistik);

    }

    @Test
    public void testGetAltersStruktur() {

        daoStatistik = StatistikDAO.getInstance();

        ArrayList<StatistikEintrag> liste = daoStatistik.getAltersStruktur();

        assertNotNull(liste);
        assertTrue(liste.size() == 5);
        assertEquals("unter 18", liste.get(0).getName());
        assertEquals("18 bis 25", liste.get(1).getName());
        assertEquals("25 bis 29", liste.get(2).getName());
        assertEquals("30 bis 39", liste.get(3).getName());
        assertEquals("über 41", liste.get(4).getName());

        System.out.println("ausgabe" + liste.get(1).getName());

    }
}
