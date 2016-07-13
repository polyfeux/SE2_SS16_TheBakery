package org.hbrs.se2.process.control;

import org.hbrs.se2.model.dao.ProduktDAO;
import org.hbrs.se2.model.dao.ShopDAO;
import org.hbrs.se2.model.objects.dto.Produkt;
import org.hbrs.se2.model.objects.dto.Shop;
import org.hbrs.se2.model.objects.dto.User;

/**
 *
 * @author Felix
 */
public class ShopControl {

    public static Shop shopErstellen(User user) {
        return ShopDAO.getInstance().shopErstellen(user);
    }

    public static Shop getShopVonUser(User user) {
        return ShopDAO.getInstance().getShopVonUser(user);
    }

    public static User getUserVonShop(Shop shop) {
        return ShopDAO.getInstance().getUserVonShop(shop);
    }

    public static void addProduktZuShop(Produkt produkt, User user) {
        ProduktDAO.getInstance().addProduktZuShop(produkt, user);
    }
    
}
