package org.hbrs.se2.junit.DAOtest;

import java.util.List;
import org.hbrs.se2.junit.data.DataUser;
import org.hbrs.se2.model.dao.BestellungDAO;
import org.hbrs.se2.model.objects.dto.Bestellung;
import org.hbrs.se2.model.objects.dto.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitBestellungTest {

    private static BestellungDAO daoBestellung;

    @Before
    public void setUp() {
        daoBestellung = BestellungDAO.getInstance();
    }

    @Test
    public void testInstanceNotNull() {
        assertNotNull(daoBestellung);

    }

    @Test
    public void testGetBestellungen() {
        User user3 = DataUser.getUser3();

        List<Bestellung> listeB = daoBestellung.getBestellungen(user3);

        assertNotNull(listeB);
        assertEquals("joeffrey", listeB.get(0).getLogin());
        assertEquals("Environmental Specialist", listeB.get(0).getProdukteVonWarenkorb().get(0).getProdukt().getName());
        assertEquals(3, listeB.get(0).getProdukteVonWarenkorb().get(0).getMenge());
        assertEquals(1, listeB.get(0).getProdukteVonWarenkorb().get(1).getMenge());
    }

    @Test
    public void testGetLetzteBesllIDforUser() {

        User user3 = DataUser.getUser3();

        assertNotNull(daoBestellung.getLetzteBestellIDforUser(user3.getLogin()));
        assertEquals(29, daoBestellung.getLetzteBestellIDforUser(user3.getLogin()));

    }

//    @Test
//    public void testSetBestellung(){
//        
//    }
}
