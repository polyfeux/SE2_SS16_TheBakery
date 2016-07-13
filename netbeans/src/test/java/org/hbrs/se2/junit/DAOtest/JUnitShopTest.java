package org.hbrs.se2.junit.DAOtest;

import org.hbrs.se2.junit.data.DataUser;
import org.hbrs.se2.model.dao.ShopDAO;
import org.hbrs.se2.model.objects.dto.Shop;
import org.hbrs.se2.model.objects.dto.User;
import org.junit.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author J
 */
// 75% TESTED
public class JUnitShopTest {

    @Test
    public void Test10_InstanceNotNull() {
        ShopDAO daoShop = ShopDAO.getInstance();
        assertNotNull(daoShop);
    }

    @Test //DATENBANK DAO
    public void Test11_Erstellen() {
        User user = DataUser.getUser1();
        ShopDAO daoShop = ShopDAO.getInstance();
        Shop temp = daoShop.shopErstellen(user);
        assertEquals("Shop von test", temp.getShopname());
    }

    @Test
    public void Test12_Loeschen() {
        User user = DataUser.getUser1();
        ShopDAO daoShop = ShopDAO.getInstance();
        assertTrue(daoShop.shopLoeschen(user));
    }

    @Test
    public void Test13_getShopVonUser() {

        User user3 = DataUser.getUser3();
        Shop temp3 = ShopDAO.getInstance().getShopVonUser(user3);
        User user2 = DataUser.getUser2();
        Shop temp2 = ShopDAO.getInstance().getShopVonUser(user2);

        assertNotNull(temp3);
        assertNull(temp2);
    }

//    @Test
//    //NullPointerEx
//    public void Test14_getUserVonShop() {
//        
//        User user3 = DataUser.getUser3();
//        Shop shopUser3 = ShopDAO.getInstance().getShopVonUser(user3);
//              
//        
//        assertEquals("joeffrey", ShopDAO.getInstance().getUserVonShop(shopUser3).getLogin());
//     
//        
//
//    }
}
