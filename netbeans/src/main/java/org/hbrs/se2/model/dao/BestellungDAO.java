package org.hbrs.se2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.model.objects.dto.BestellPosition;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.model.objects.dto.Bestellung;

/**
 *
 * @author Felix
 */
public class BestellungDAO extends AbstractDAO {

    private static BestellungDAO dao = null;

    private BestellungDAO() {

    }

    public static BestellungDAO getInstance() {
        if (dao == null) {
            dao = new BestellungDAO();
        }
        return dao;
    }

    public List<Bestellung> getBestellungen(User user) {
        List<Bestellung> liste = new ArrayList<>();
        String login = user.getLogin();

        PreparedStatement st1 = this.getPreparedStatement(
                "SELECT bestellid, login, bestelldatum, status "
                + "FROM db_testbakery.t_bestellung "
                + "WHERE login = ?");
        ResultSet rs = null;

        try {
            st1.setString(1, login);
            rs = st1.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BestellungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        Bestellung bestellung = null;
        try {
            while (rs.next()) {
                bestellung = new Bestellung();
                bestellung.setId(rs.getInt(1));
                bestellung.setLogin(rs.getString(2));
                bestellung.setDatum(rs.getString(3));
                bestellung.setStatus(rs.getString(4));
                liste.add(bestellung);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BestellungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement st2 = this.getPreparedStatement(
                "SELECT prowarid, bestellid, produktid, menge "
                + "FROM db_testbakery.t_ztprowar "
                + "WHERE bestellid = ?");

        for (Bestellung b : liste) {
            int bestId = b.getId();

            try {
                st2.setInt(1, bestId);
                rs = st2.executeQuery();
            } catch (SQLException ex) {
                Logger.getLogger(BestellungDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                while (rs.next()) {
                    b.addBestellPosition(ProduktDAO.getInstance().getProduktById(rs.getInt(3)), rs.getInt(4));
                }
            } catch (Exception e) {
                Logger.getLogger(BestellungDAO.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return liste;
    }

    public void setBestellung(Bestellung bestellung) {
        String login = bestellung.getLogin();
        String status = bestellung.getStatus();

        PreparedStatement st1 = this.getPreparedStatement(
                "INSERT INTO db_testbakery.t_bestellung "
                + "VALUES (DEFAULT, ?,CURRENT_TIMESTAMP, ?);");

        try {
            // t_bestellung fuellen:
            // bestellid, login, bestelldatum, status
            st1.setString(1, login);
            st1.setString(2, status);
            st1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BestellungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement st2 = this.getPreparedStatement(
                "INSERT INTO db_testbakery.t_ztprowar "
                + "VALUES(DEFAULT, ?, ?, ?)");
        int bestellID = getLetzteBestellIDforUser(login);

        try {
            // DEFAULT, bestellid, produktid, menge
            for (BestellPosition bP : bestellung.getProdukteVonWarenkorb()) {
                st2.setInt(1, bestellID);
                st2.setInt(2, bP.getProdukt().getId());
                st2.setInt(3, bP.getMenge());

                st2.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(BestellungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getLetzteBestellIDforUser(String login) {
        PreparedStatement st = this.getPreparedStatement(
                "SELECT bestellid "
                + "FROM db_testbakery.t_bestellung "
                + "WHERE login = ? "
                + "ORDER BY bestellid DESC LIMIT 1");
        ResultSet rs = null;

        int letzteID = -1;

        try {
            st.setString(1, login);
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BestellungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            while (rs.next()) {
                letzteID = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BestellungDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return letzteID;
    }
}
