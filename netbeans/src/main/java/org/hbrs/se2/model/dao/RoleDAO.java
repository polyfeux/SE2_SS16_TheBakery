package org.hbrs.se2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.model.objects.dto.Role;
import org.hbrs.se2.model.objects.dto.User;

/**
 *
 * @author Felix
 */
public class RoleDAO extends AbstractDAO {

    private static RoleDAO dao = null;

    private RoleDAO() {

    }

    public static RoleDAO getInstance() {
        if (dao == null) {
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<Role> getRolesForUser(User u) {
        PreparedStatement st = this.getPreparedStatement(
                "SELECT ztr.login, r.rollenbezeichnung "
                + "FROM db_testbakery.t_ztbenrolid ztr "
                + "INNER JOIN db_testbakery.t_rollen r "
                + "ON ztr.rollenid = r.rollenid "
                + "WHERE ztr.login = ?");

        ResultSet rs = null;

        try {
            st.setString(1, u.getLogin());
            rs = st.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs == null) {
            return null;
        }

        List<Role> liste = new ArrayList<>();
        Role rolle = null;

        try {
            while (rs.next()) {
                rolle = new Role();
                rolle.setBezeichnung(rs.getString(2));
                liste.add(rolle);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return liste;
    }
}
