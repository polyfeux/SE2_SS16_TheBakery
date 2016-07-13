package org.hbrs.se2.process.control;

import com.vaadin.ui.UI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.gui.ui.MyUI;
import org.hbrs.se2.model.objects.dto.User;
import org.hbrs.se2.model.objects.dto.Bestellung;
import org.hbrs.se2.process.control.exceptions.DatabaseException;
import org.hbrs.se2.process.control.exceptions.NoSuchUserOrPassword;
import org.hbrs.se2.services.db.JDBCConnection;
import org.hbrs.se2.services.util.Views;

/**
 *
 * @author Felix
 */
public class LoginControl {
    
    public static void checkAuthentication(String login, String password) throws NoSuchUserOrPassword, DatabaseException {
        ResultSet rs = null;
        
        try {
            // DB-Zugriff
            PreparedStatement st = JDBCConnection.getInstance().getPreparedStatement(""
                    + "SELECT * "
                    + "FROM db_testbakery.t_benutzer "
                    + "WHERE login = ? "
                    + "AND password = ?");
            st.setString(1, login);
            st.setString(2, password);
            rs = st.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler im SQL-Befehl! Bitte den Sklaven benachrichtigen!");
        }
        
        User user = null;
        
        try {
            if (rs.next()) {
                user = new User();
                user.setLogin(rs.getString(1));
                user.setVorname(rs.getString(4));
            } else {
                // Fehlerfall:
                throw new NoSuchUserOrPassword();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            JDBCConnection.getInstance().closeConnection();
        }
        
        ((MyUI) UI.getCurrent()).setUser(user);
        ((MyUI) UI.getCurrent()).setWarenkorb(new Bestellung());
        UI.getCurrent().getNavigator().navigateTo(Views.START);
        
    }
    
    public static void logoutUser() {
        UI.getCurrent().getPage().setLocation("/theBakery_Shoppingv01");
        UI.getCurrent().getSession().close();
    }
}
