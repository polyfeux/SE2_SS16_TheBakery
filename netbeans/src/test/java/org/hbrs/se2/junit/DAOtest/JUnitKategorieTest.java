package org.hbrs.se2.junit.DAOtest;

import java.util.List;
import org.hbrs.se2.model.dao.KategorieDAO;
import org.hbrs.se2.model.objects.dto.Kategorie;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
//DONE (MEHR TESTS: EINTRÄGE DER LIST ÜBERPRÜFEN)
public class JUnitKategorieTest {

    private KategorieDAO daoKategorie;

    @Before
    public void setUp() {
        daoKategorie = KategorieDAO.getInstance();
    }

    @Test
    public void testInstanceNotNull() {

        assertNotNull(daoKategorie);
    }

    @Test
    public void testGetKategorien() {
        List<Kategorie> liste = daoKategorie.getKategorien();
        assertTrue(liste.size() == 7);
    }

    @Test
    public void testKategorieName() {

        List<Kategorie> liste = daoKategorie.getKategorien();

        assertEquals("BuerobedarfUndSchreibwaren", liste.get(0).getName());
        assertEquals("Lernmateriealien", liste.get(1).getName());
        assertEquals("PC_Tablet", liste.get(2).getName());
        assertEquals("Software", liste.get(3).getName());
        assertEquals("Diverses_Sonstiges", liste.get(4).getName());
        assertEquals("Hardware", liste.get(5).getName());
        assertEquals("SportUndFreizeit", liste.get(6).getName());

    }
}
