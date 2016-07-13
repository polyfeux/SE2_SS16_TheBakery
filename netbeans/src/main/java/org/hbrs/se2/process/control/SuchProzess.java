package org.hbrs.se2.process.control;

import java.util.List;
import org.hbrs.se2.model.dao.ProduktDAO;
import org.hbrs.se2.model.objects.dto.Produkt;
import org.hbrs.se2.process.control.exceptions.KeineProdukteException;

/**
 *
 * @author Felix
 */
public class SuchProzess {
    private static SuchProzess prozess = null;
    
    private SuchProzess() {
        
    }
    
    public static SuchProzess getInstance() {
        if (prozess == null) {
            prozess = new SuchProzess();
        }
        return prozess;
    }
    
    public List<Produkt> sucheProdukt(String s) throws KeineProdukteException{
        return ProduktDAO.getInstance().getProdukte(s);
    }
}
