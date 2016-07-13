package org.hbrs.se2.model.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.process.control.exceptions.DatabaseException;
import org.hbrs.se2.services.db.JDBCConnection;

/**
 *
 * @author Felix
 */
public class AbstractDAO {

    protected Statement getStatement() {
        Statement st = null;
        try {
            st = JDBCConnection.getInstance().getStatement();
        } catch (DatabaseException ex) {
            Logger.getLogger(ProduktDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }

    protected PreparedStatement getPreparedStatement(String sql) {
        PreparedStatement pst = null;
        try {
            pst = JDBCConnection.getInstance().getPreparedStatement(sql);
        } catch (DatabaseException ex) {
            Logger.getLogger(ProduktDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pst;
    }

}
