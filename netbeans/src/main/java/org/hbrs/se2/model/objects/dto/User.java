package org.hbrs.se2.model.objects.dto;

import java.io.Serializable;
import java.util.List;
import org.hbrs.se2.model.dao.RoleDAO;

/**
 *
 * @author Felix
 */
public class User implements Serializable {

    private String vorname = null;
    private String login = null;
    private List<Role> roles = null;

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean hasRole(String role) {
        // Lazy Load Pattern
        if (this.roles == null) {
            getRoles();
        }

        for (Role r : roles) {
            if (r.getBezeichnung().equals(role)) {
                return true;
            }
        }
        return false;
    }

    private void getRoles() {
        this.roles = RoleDAO.getInstance().getRolesForUser(this);
    }
}
