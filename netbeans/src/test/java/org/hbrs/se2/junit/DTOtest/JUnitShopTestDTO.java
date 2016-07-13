package org.hbrs.se2.junit.DTOtest;

import org.hbrs.se2.model.objects.dto.Shop;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author J
 */
public class JUnitShopTestDTO {

    @Test //DTO
    public void Test10_GetMethoden() {
        Shop shopTest = new Shop();

        shopTest.setShopname("Shop1");
        shopTest.setLogin("testUser");
        shopTest.setShopkategorieid(4);
        shopTest.setShopbeschreibung("Dienstleistungen wie Nachhilfe");
        shopTest.setAdressid(1);

        assertEquals("Shop1", shopTest.getShopname());
        assertEquals("testUser", shopTest.getLogin());
        assertEquals(4, shopTest.getShopkategorieid());
        assertEquals("Dienstleistungen wie Nachhilfe", shopTest.getShopbeschreibung());
        assertEquals(1, shopTest.getAdressid());
    }

}
