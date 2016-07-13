package org.hbrs.se2.junit.DAOtest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.process.control.exceptions.DatabaseException;
import org.hbrs.se2.services.db.JDBCConnection;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author J
 */
//DONE
public class JUnitJDBCConnectionTest {

    private static JDBCConnection jdbcconn;
    private static Statement statement;
    private static PreparedStatement preStatement;

    @Before
    public void setUp() {
        try {
            jdbcconn = JDBCConnection.getInstance();
        } catch (DatabaseException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(JUnitJDBCConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testInstanceNotNull() {
        assertNotNull(jdbcconn);
    }

// deaktiviert, weil lokal in Netbeans anscheinend eine Endlosschleife aufgerufen wird
//    @Test
//    public void testJDBCConnection() {
//        try {
//            Statement st = jdbcconn.getStatement();
//            ResultSet rs = st.executeQuery("SELECT * FROM db_thebakery.t_benutzer");
//            //assertTrue(rs.next());
//        } catch (SQLException | DatabaseException ex) {
//            System.err.println(ex.getMessage());
//            Logger.getLogger(JUnitJDBCConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    @Test
    public void testStatement() {

        try {
            statement = jdbcconn.getStatement();
        } catch (DatabaseException ex) {
            Logger.getLogger(JUnitJDBCConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(statement);
    }

    @Test
    public void testPreparedStatementWork() {
        try {
            preStatement = jdbcconn.getPreparedStatement("SELECT * FROM db_thebakery.t_benutzer "
                    + "WHERE login = ?");
            preStatement.setString(1, "joeffrey");
            ResultSet rs = preStatement.executeQuery();
            assertTrue(rs.next());
            assertNotNull(preStatement);
            preStatement.clearBatch();
        } catch (SQLException | DatabaseException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(JUnitJDBCConnectionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
