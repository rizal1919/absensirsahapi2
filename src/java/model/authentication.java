/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author fadhil
 */
public class authentication {
    
    public boolean cekKey(String key){
        ResultSet rs=null;
        CallableStatement cStmt = null;
        Connection conn = null;
        boolean result=false;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "call sp_apikey_cekkey(?);",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, key);
            rs=cStmt.executeQuery();
            if(rs.next()){
                if(rs.getInt(1)>0){
                    result=true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(authentication.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                }
                rs = null;
            }
            if (cStmt != null) {
                try {
                    cStmt.close();
                } catch (SQLException e) {
                }
                cStmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
                conn = null;
            }
        }
        return result;
    }
    
    public boolean validateRequest(HttpServletRequest request){
        boolean valid=false;
        try{
            valid=this.cekKey(request.getHeader("key"));
        }catch(Exception ex){
            valid=false;
        }
        return valid;
    }
    
}
