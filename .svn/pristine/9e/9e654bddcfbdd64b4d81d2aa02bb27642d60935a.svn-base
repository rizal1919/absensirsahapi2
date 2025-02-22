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
 * @author matius
 */
public class wecarePasien {
    //<editor-fold defaultstate="collapsed" desc="Field">

    String kdDokter;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="GET/SET">
    public String getKdDokter() {
        return kdDokter;
    }

    public void setKdDokter(String kdDokter) {
        this.kdDokter = kdDokter;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Open">
    public JSONArray loginDokter(String uname, String pwd) {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray dtArr = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL spwecare_cekNamePassword (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, uname.trim());
            cStmt.setString(2, pwd.trim());
            cStmt.executeQuery();
            rs = cStmt.executeQuery();
            int cols = rs.getMetaData().getColumnCount();
            String a;
            while (rs.next()) {
                int i = 1;
                obj = new JSONObject();
                while (i <= cols) {
                    a = rs.getMetaData().getColumnName(i++).trim();
                    if (rs.getString(a) == null) {
                        obj.put(a, "");
                    } else {
                        obj.put(a, rs.getString(a).trim());
                    }
                }
                dtArr.add(obj);
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(wecarePasien.class.getName()).log(Level.SEVERE, null, ex);
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
        return dtArr;
    }

    public JSONArray getListPasien(String tgl) {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray dtArr = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL spwecare_view_pasien_per_dokter3 (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getKdDokter());
            cStmt.setString(2, tgl);
            cStmt.executeQuery();
            rs = cStmt.executeQuery();
            int cols = rs.getMetaData().getColumnCount();
            String a;
            while (rs.next()) {
                int i = 1;
                obj = new JSONObject();
                while (i <= cols) {
                    a = rs.getMetaData().getColumnName(i++).trim();
                    if (rs.getString(a) == null) {
                        obj.put(a, "");
                    } else {
                        obj.put(a, rs.getString(a).trim());
                    }
                }
                dtArr.add(obj);
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(wecarePasien.class.getName()).log(Level.SEVERE, null, ex);
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
        return dtArr;
    }
    
    public JSONArray getListPasienAdmin(String tgl) {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray dtArr = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL spwecare_view_pasien_per_dokter_admin2 (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, tgl.trim());
            cStmt.executeQuery();
            rs = cStmt.executeQuery();
            int cols = rs.getMetaData().getColumnCount();
            String a;
            while (rs.next()) {
                int i = 1;
                obj = new JSONObject();
                while (i <= cols) {
                    a = rs.getMetaData().getColumnName(i++).trim();
                    if (rs.getString(a) == null) {
                        obj.put(a, "");
                    } else {
                        obj.put(a, rs.getString(a).trim());
                    }
                }
                dtArr.add(obj);
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(wecarePasien.class.getName()).log(Level.SEVERE, null, ex);
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
        return dtArr;
    }

//</editor-fold>
}
