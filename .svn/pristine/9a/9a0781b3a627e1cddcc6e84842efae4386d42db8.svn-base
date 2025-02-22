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
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rizal.Fathurrahman
 */
public class PermissionModel {
    private String npp, role, userUpdate, menu_id;
    private Boolean canCreate, canRead, canUpdate, canDelete;
    
    public Boolean changeRoleToStaff() {
        CallableStatement cStmt = null;
        Connection conn = null;
        Boolean res = true;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_changeRoleToStaff (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNpp().trim());
            cStmt.setString(2, getUserUpdate().trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            res = false;
            Logger.getLogger(formPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        return res;
    }
    
    public Boolean changeRoleToLeader() {
        CallableStatement cStmt = null;
        Connection conn = null;
        Boolean res = true;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_changeRoleToLeader (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNpp().trim());
            cStmt.setString(2, getUserUpdate().trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            res = false;
            Logger.getLogger(formPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        return res;
    }
    
    public Boolean changeRoleToAdministrator() {
        CallableStatement cStmt = null;
        Connection conn = null;
        Boolean res = true;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_changeRoleToAdministrator (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNpp().trim());
            cStmt.setString(2, getUserUpdate().trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            res = false;
            Logger.getLogger(formPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        return res;
    }
    
    public Boolean addAccessToRole() {
        CallableStatement cStmt = null;
        Connection conn = null;
        Boolean res = true;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_addAccessToRole (?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNpp().trim());
            cStmt.setString(2, getUserUpdate().trim());
            cStmt.setString(3, getMenuID().trim());
            cStmt.setString(4, getRole().trim());
            cStmt.setBoolean(5, getCanCreate());
            cStmt.setBoolean(6, getCanRead());
            cStmt.setBoolean(7, getCanUpdate());
            cStmt.setBoolean(8, getCanDelete());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            res = false;
            Logger.getLogger(formPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
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
        return res;
    }
    
    public JSONArray findUsersFromPermission(String npp) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_findUsers (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, npp);
//            cStmt.setString(2, nopegawai.trim());
            rs = cStmt.executeQuery();
            JSONObject obj = null;
            int cols = rs.getMetaData().getColumnCount();
            String fieldname;
            int dataType;
            while (rs.next()) {
                int i = 1;
                obj = new JSONObject();
                while (i <= cols) {
                    fieldname = rs.getMetaData().getColumnName(i).trim();
                    dataType = rs.getMetaData().getColumnType(i);
                    if (dataType == Types.BIT || dataType == Types.BOOLEAN) {
                        obj.put(fieldname, rs.getBoolean(i));
                    } else if (dataType == Types.INTEGER) {
                        obj.put(fieldname, rs.getInt(i));
                    } else {
                        if (rs.getObject(i) != null) {
                            obj.put(fieldname, rs.getString(i));
                        } else {
                            obj.put(fieldname, null);
                        }
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(usercredentials.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            // Always make sure result sets and statements are closed,
            // and the connection is returned to the pool
            if (rs != null) {
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
    
    
    public void setCanCreate(Boolean val){
        this.canCreate = val;
    }
    
    public Boolean getCanCreate(){
        return this.canCreate;
    }
    
    public void setCanRead(Boolean val){
        this.canRead = val;
    }
    
    public Boolean getCanRead(){
        return this.canRead;
    }
    
    public void setCanUpdate(Boolean val){
        this.canUpdate = val;
    }
    
    public Boolean getCanUpdate(){
        return this.canUpdate;
    }
    
    public void setCanDelete(Boolean val){
        this.canDelete = val;
    }
    
    public Boolean getCanDelete(){
        return this.canDelete;
    }
    
    public void setMenuID(String menu_id){
        this.menu_id = menu_id;
    }
    
    public String getMenuID(){
        return this.menu_id;
    }
    
    public void setUserUpdate(String usr){
        this.userUpdate = usr;
    }
    
    public String getUserUpdate(){
        return this.userUpdate;
    }
    
    public void setRole(String role){
        this.role = role;
    }
    
    public String getRole(){
        return this.role;
    }
    
    public void setNpp(String npp){
        this.npp = npp;
    }
    
    public String getNpp(){
        return this.npp;
    }
}
