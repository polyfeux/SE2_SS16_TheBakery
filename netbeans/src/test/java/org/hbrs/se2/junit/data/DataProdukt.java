package org.hbrs.se2.junit.data;

import org.hbrs.se2.model.objects.dto.Produkt;

/**
 *
 * @author J
 */
public class DataProdukt {

    private static final String NAME = "se2 test aus DAO";
    private static final int SHOPiD = 14;

    public static final Produkt getDataProdukt1() {
        Produkt temp = new Produkt();
        temp.setName(NAME);
        temp.setShopId(14);

        return temp;
    }
}
