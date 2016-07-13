package org.hbrs.se2.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.model.objects.dto.StatistikEintrag;

/**
 *
 * @author Felix
 */
public class StatistikDAO extends AbstractDAO {

    private static StatistikDAO dao = null;

    private StatistikDAO() {

    }

    public static StatistikDAO getInstance() {
        if (dao == null) {
            dao = new StatistikDAO();
        }
        return dao;
    }

    public ArrayList<StatistikEintrag> getAltersStruktur() {
        Statement st = this.getStatement();
        ResultSet rs = null;

        ArrayList<StatistikEintrag> altersStruktur = new ArrayList<>();

        try {
            rs = st.executeQuery("SELECT anzahlunter18 FROM db_testbakery.unter18");
            while (rs.next()) {
                altersStruktur.add(new StatistikEintrag("unter 18", rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistikDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) {
            return null;
        }

        try {
            rs = st.executeQuery("SELECT anzahl1825 FROM db_testbakery.von18bis25");
            while (rs.next()) {
                altersStruktur.add(new StatistikEintrag("18 bis 25", rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistikDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = st.executeQuery("SELECT anzahl2529 FROM db_testbakery.von25bis29");
            while (rs.next()) {
                altersStruktur.add(new StatistikEintrag("25 bis 29", rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistikDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = st.executeQuery("SELECT anzahl3039 FROM db_testbakery.von30bis39");
            while (rs.next()) {
                altersStruktur.add(new StatistikEintrag("30 bis 39", rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistikDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            rs = st.executeQuery("SELECT anzahlueber41 FROM db_testbakery.ueber41");
            while (rs.next()) {
                altersStruktur.add(new StatistikEintrag("über 41", rs.getInt(1)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistikDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return altersStruktur;
    }
}
