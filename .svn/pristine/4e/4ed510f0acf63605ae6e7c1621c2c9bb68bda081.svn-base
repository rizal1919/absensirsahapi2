/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author fadhil
 */
public class connDB {
    
    public Connection getConnection() {
        Context initCtx;
        Connection conn = null;
        try {
            initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) envCtx.lookup("jdbc/rsahuw");
            conn = ds.getConnection();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(connDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
}
