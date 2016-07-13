package org.hbrs.se2.junit.DAOtest;

import org.hbrs.se2.junit.data.DataUser;
import org.hbrs.se2.model.dao.RoleDAO;
import org.hbrs.se2.model.objects.dto.User;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author J
 */
//DONE
public class JUnitRoleTest {

    public static RoleDAO daoRole;

    @Test
    public void testInstanceNotNull() {
        daoRole = RoleDAO.getInstance();
        assertNotNull(daoRole);
    }

    @Test
    public void testRolesForUser() {
        User user = DataUser.getUser1();
        User user2 = DataUser.getUser2();
        RoleDAO daoRole = RoleDAO.getInstance();

//        assertEquals(2, daoRole.getRolesForUser(user).size());
//        assertEquals("nutzer", daoRole.getRolesForUser(user).get(0).getBezeichnung());
//        assertEquals("verkaeufer", daoRole.getRolesForUser(user).get(1).getBezeichnung());

        assertEquals(1, daoRole.getRolesForUser(user2).size());
        assertEquals("nutzer", daoRole.getRolesForUser(user2).get(0).getBezeichnung());
        assertNotEquals("verkaeufer", daoRole.getRolesForUser(user2).get(0).getBezeichnung());
    }
}
