package org.hbrs.se2.process.control;

import java.util.List;
import org.hbrs.se2.model.dao.KategorieDAO;
import org.hbrs.se2.model.objects.dto.Kategorie;

/**
 *
 * @author Felix
 */
public class KategorieControl {

    public static List<Kategorie> getProduktKategorien() {
        return KategorieDAO.getInstance().getKategorien();
    }
}
