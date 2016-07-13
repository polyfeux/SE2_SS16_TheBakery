package org.hbrs.se2.services.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hbrs.se2.process.control.exceptions.DatabaseException;
import org.hbrs.se2.services.util.Password;

/**
 *
 * @author Felix
 */
public class JDBCConnection {
    // Singleton-Instanz
    private static JDBCConnection jdbcconn = null;
    
    private String url = "jdbc:postgresql://unsere-db.de/";
    private String login = "username";
    private String password = Password.PASSWORD;
    private Connection conn;
    
    public static JDBCConnection getInstance() throws DatabaseException {
        if (jdbcconn == null) {
            jdbcconn = new JDBCConnection();
        }
        return jdbcconn;
    }
    
    private JDBCConnection() throws DatabaseException {
        this.initConnection();
    }
    
    public void initConnection() throws DatabaseException {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.openConnection();
    }
    
    public void openConnection() throws DatabaseException {
        try {
            Properties props = new Properties();
            props.setProperty("user", "username");
            props.setProperty("password", Password.PASSWORD);
            
            this.conn = DriverManager.getConnection(this.url, props);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            throw new DatabaseException("Fehler beim Zugriff auf die Datenbank! Sichere Verbindung vorhanden?!");
        }
    }
    
    public Statement getStatement() throws DatabaseException {
        try {
            if (this.conn.isClosed()) {
                this.openConnection();
            }
            
            return this.conn.createStatement();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public PreparedStatement getPreparedStatement(String sql) throws DatabaseException {
        try {
            if (this.conn.isClosed()) {
                this.openConnection();
            }
            
            return this.conn.prepareStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
