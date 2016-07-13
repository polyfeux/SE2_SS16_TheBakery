package org.hbrs.se2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.model.objects.dto.Produkt;
import org.hbrs.se2.model.objects.dto.Shop;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.process.control.ShopControl;
import org.hbrs.se2.process.control.exceptions.KeineProdukteException;

/**
 *
 * @author Felix
 */
public class ProduktDAO extends AbstractDAO {

    private static ProduktDAO dao = null;

    private ProduktDAO() {
    }

    public static ProduktDAO getInstance() {
        if (dao == null) {
            dao = new ProduktDAO();
        }
        return dao;
    }

    public Produkt getProduktById(int id) throws KeineProdukteException {
        PreparedStatement st = this.getPreparedStatement(
                "SELECT produktid, preis, produktbezeichnung, produktkategorieid, shopid, produktbeschreibung "
                + "FROM db_testbakery.t_produkte "
                + "WHERE produktid = ?");
        ResultSet rs = null;

        try {
            st.setInt(1, id);
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProduktDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        Produkt produkt = null;

        try {
            while (rs.next()) {
                produkt = new Produkt();
                produkt.setId(rs.getInt(1));
                // workaround, weil "12.34" nicht als Double gespeichert werden kann
                produkt.setPreis(Double.parseDouble(rs.getString(2)));
                produkt.setName(rs.getString(3));
                produkt.setBeschreibung(rs.getString(4));
                produkt.setShopId(rs.getInt(5));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduktDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return produkt;
    }

    public List<Produkt> getProdukte(String suche) throws KeineProdukteException {
        PreparedStatement st = this.getPreparedStatement("SELECT produktid, preis, produktbezeichnung, produktbeschreibung, shopid "
                + "FROM db_testbakery.t_produkte "
                + "WHERE produktbezeichnung ILIKE ? "
                + "LIMIT 100");
        ResultSet rs = null;

        try {
            if (suche.isEmpty()) {
                st.setString(1, "%");
            } else {
                st.setString(1, "%" + suche + "%");
            }
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProduktDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) {
            throw new KeineProdukteException();
        }

        List<Produkt> liste = new ArrayList();
        Produkt p = null;

        try {
            while (rs.next()) {
                p = new Produkt();
                p.setId(rs.getInt(1));
                // workaround, weil "12.34" nicht als Double gespeichert werden kann
                p.setPreis(Double.parseDouble(rs.getString(2)));
                p.setName(rs.getString(3));
                p.setBeschreibung(rs.getString(4));
                p.setShopId(rs.getInt(5));
                liste.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduktDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public List<Produkt> getProdukteFromShop(String suche, User user) throws KeineProdukteException {
        PreparedStatement st = this.getPreparedStatement("SELECT p.produktid, p.preis, p.produktbezeichnung, p.produktbeschreibung "
                + "FROM db_testbakery.t_produkte p "
                + "INNER JOIN db_testbakery.t_shop s "
                + "ON p.shopid = s.shopid "
                + "WHERE produktbezeichnung ILIKE ? "
                + "AND s.login = ? ");
        ResultSet rs = null;

        String login = user.getLogin();

        if (suche.isEmpty()) {
            suche = "";
        }

        try {
            st.setString(1, "%" + suche + "%");
            st.setString(2, login);
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProduktDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) {
            throw new KeineProdukteException();
        }

        List<Produkt> liste = new ArrayList();
        Produkt p = null;

        try {
            while (rs.next()) {
                p = new Produkt();
                p.setId(rs.getInt(1));
                // workaround, weil "12.34" nicht als Double gespeichert werden kann
                p.setPreis(Double.parseDouble(rs.getString(2)));
                p.setName(rs.getString(3));
                p.setBeschreibung(rs.getString(4));
                liste.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduktDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }

    public void addProduktZuShop(Produkt p, User user) {
        PreparedStatement st = this.getPreparedStatement(
                "INSERT INTO db_testbakery.t_produkte "
                + "VALUES (DEFAULT, ?, ?, ?, ?, ?)");

        Shop shop = ShopControl.getShopVonUser(user);
        p.setShopId(shop.getId());

        // produktid(int), preis(charvar), produktbezeichnung(charvar), produktkategorieid(charvar), shopid(bigint), produktbeschreibung(charvar)
        try {
            st.setString(1, String.valueOf(p.getPreis()));
            st.setString(2, p.getName());
            st.setString(3, String.valueOf(p.getKategorieId()));
            st.setInt(4, p.getShopId());
            st.setString(5, p.getBeschreibung());
            
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProduktDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
