package org.hbrs.se2.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.model.objects.dto.Kategorie;

/**
 *
 * @author Felix
 */
public class KategorieDAO extends AbstractDAO {

    private static KategorieDAO dao = null;

    private KategorieDAO() {

    }

    public static KategorieDAO getInstance() {
        if (dao == null) {
            dao = new KategorieDAO();
        }
        return dao;
    }

    public List<Kategorie> getKategorien() {
        Statement st = this.getStatement();
        ResultSet rs = null;

        try {
            rs = st.executeQuery("SELECT kategorieid, kategorielabel, kategoriename, kategoriebeschreibung "
                    + "FROM db_testbakery.t_kategorien");
        } catch (SQLException ex) {
            Logger.getLogger(KategorieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) {
            return null;
        }

        List<Kategorie> liste = new ArrayList();
        Kategorie pk = null;
        
        try {
            while(rs.next()) {
                pk = new Kategorie();
                pk.setId(rs.getInt(1));
                pk.setLabel(rs.getString(2));
                pk.setName(rs.getString(3));
                pk.setBeschreibung(rs.getString(4));
                liste.add(pk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategorieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;

    }
}
