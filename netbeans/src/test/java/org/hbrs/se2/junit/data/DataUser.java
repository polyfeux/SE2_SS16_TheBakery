package org.hbrs.se2.junit.data;

import org.hbrs.se2.model.objects.dto.User;

/**
 *
 * @author J
 */
public class DataUser {

    public static final String LOGIN1 = "test";
    public static final String VORNAME1 = "test";
    public static final String LOGIN2 = "dan";
    public static final String VORNAME2 = "dan";
    public static final String LOGIN3 = "joe";
    public static final String VORNAME3 = "joe";

    public static final User getUser1() {
        User temp = new User();
        temp.setLogin(LOGIN1);
        temp.setVorname(VORNAME1);
        return temp;
    }

    public static final User getUser2() {
        User temp = new User();
        temp.setLogin(LOGIN2);
        temp.setVorname(VORNAME2);
        return temp;
    }

    public static final User getUser3() {
        User temp = new User();
        temp.setLogin(LOGIN3);
        temp.setVorname(VORNAME3);
        return temp;
    }

}
