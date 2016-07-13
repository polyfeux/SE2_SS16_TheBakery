package org.hbrs.se2.junit.DAOtest;

import org.hbrs.se2.junit.data.DataProdukt;
import org.hbrs.se2.junit.data.DataUser;
import org.hbrs.se2.model.dao.ProduktDAO;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.exceptions.KeineProdukteException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author J
 */
// DONE: DELETE PRODUCT FROM DAO NEEDED! 
public class JUnitProduktTest {

    public static ProduktDAO daoProdukt;

    @Test
    public void testInstanceNotNull() {

        daoProdukt = ProduktDAO.getInstance();
        assertNotNull(daoProdukt);
    }

    @Test
    public void testGetProdukt() throws KeineProdukteException {

        ProduktDAO daoProdukt = ProduktDAO.getInstance();

//        assertTrue(daoProdukt.getProdukte("VP Sales").size() > 0);
//        assertFalse(daoProdukt.getProdukte("Existiert nicht").size() > 0);
//        assertTrue(daoProdukt.getProdukte("se2").size() == 6);
    }

    @Test
    public void testGetProdukteFromShop() throws KeineProdukteException {

        User user3 = DataUser.getUser3();
        ProduktDAO daoProdukt = ProduktDAO.getInstance();
        assertEquals(1, daoProdukt.getProdukteFromShop("test se2", user3).size());
        assertEquals(1, daoProdukt.getProdukteFromShop("test2 se2", user3).size());
        assertTrue(daoProdukt.getProdukteFromShop("test se2", user3).size() > 0);
        assertFalse(daoProdukt.getProdukteFromShop("test se2", user3).isEmpty());

    }

    @Test
    public void testGetAddProduktZuShop() throws KeineProdukteException {

        User user3 = DataUser.getUser3();
        ProduktDAO daoProdukt = ProduktDAO.getInstance();
        DataProdukt dataProdukt = new DataProdukt();

//        daoProdukt.addProduktZuShop(DataProdukt.getDataProdukt1(), user3);
        assertEquals(2, daoProdukt.getProdukteFromShop("se2 test aus DAO", user3).size());
        assertTrue(daoProdukt.getProdukteFromShop("se2 test aus DAO", user3).size() > 0);
        assertFalse(daoProdukt.getProdukteFromShop("se2 test aus DAO", user3).isEmpty());

    }

}
