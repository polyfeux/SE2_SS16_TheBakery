package org.hbrs.se2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.model.objects.dto.Shop;
import org.hbrs.se2.model.objects.dto.User;

/**
 *
 * @author Felix
 */
public class ShopDAO extends AbstractDAO {

    private static ShopDAO dao;

    private ShopDAO() {

    }

    public static ShopDAO getInstance() {
        if (dao == null) {
            dao = new ShopDAO();
        }
        return dao;
    }

    public Shop shopErstellen(User user) {
        String login = user.getLogin();
        String vorname = user.getVorname();

        PreparedStatement st1 = this.getPreparedStatement(
                "INSERT INTO db_testbakery.t_ztbenrolid "
                + "VALUES (DEFAULT, ?, ?)");

        // Rolle dem Nutzer hinzufügen
        try {
            st1.setString(1, login);
            // 1: Nutzer, 2: Verkäufer, 3: Admin
            st1.setInt(2, 2);
            st1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        PreparedStatement st2 = this.getPreparedStatement(
                "INSERT INTO db_testbakery.t_shop "
                + "VALUES (DEFAULT, ?, ?, ?, ?)"); // Shop tatsächlich erstellen
        try {
            st2.setString(1, "Shop von " + vorname);
            st2.setString(2, login);
            st2.setInt(3, 4); // Shop-Kategorie
            st2.setString(4, "einNeuerShop");
            st2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return getShopVonUser(user);
    }

    public boolean shopLoeschen(User user) {
        String login = user.getLogin();

        // Rolle von dem Nutzer entfernen
        PreparedStatement st1 = this.getPreparedStatement(
                "DELETE FROM db_testbakery.t_ztbenrolid "
                + "WHERE t_ztbenrolid.login = ? "
                + "AND t_ztbenrolid.rollenid = ?");

        try {
            st1.setString(1, login);
            // 1: Nutzer, 2: Verkäufer, 3: Admin
            st1.setInt(2, 2);
            st1.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        // Shop tatsächlich löschen
        PreparedStatement st2 = this.getPreparedStatement(
                "DELETE FROM db_testbakery.t_shop "
                + "WHERE t_shop.login = ?");
        try {
            st2.setString(1, login);
            st2.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

    public Shop getShopVonUser(User user) {
        String login = user.getLogin();

        // gerade erstellten Shop zurückgeben
        PreparedStatement st = this.getPreparedStatement(
                "SELECT shopid, shopname, login, shopkategorieid, shopbeschreibung, eroeffnungsdatum, adressid "
                + "FROM db_testbakery.t_shop "
                + "WHERE login = ?");
        ResultSet rs = null;

        try {
            st.setString(1, login);
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        Shop shop = null;
        try {
            while (rs.next()) {
                shop = new Shop();
                shop.setId(rs.getInt(1));
                shop.setShopname(rs.getString(2));
                shop.setLogin(rs.getString(3));
                shop.setShopkategorieid(rs.getInt(4));
                shop.setShopbeschreibung(rs.getString(5));
                shop.setEroeffnungsdatum(rs.getDate(6));
                shop.setAdressid(rs.getInt(7));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return shop;
    }

    public User getUserVonShop(Shop shop) {
        String login = shop.getLogin();

        PreparedStatement st = this.getPreparedStatement("SELECT login, vorname "
                + "FROM db_testbakery.t_benutzer"
                + "WHERE login = ?");
        ResultSet rs = null;

        try {
            st.setString(1, login);
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) {
            return null;
        }

        User user = null;

        try {
            user = new User();
            user.setLogin(rs.getString(1));
            user.setVorname(rs.getString(2));
            // Rollen werden direkt im Code mit user.hasRole() abgefragt
        } catch (SQLException ex) {
            Logger.getLogger(ShopDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }
}
