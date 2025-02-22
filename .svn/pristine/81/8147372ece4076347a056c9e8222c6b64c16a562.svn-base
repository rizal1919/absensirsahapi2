package model;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connDB;
import model.formPerbaikanKomputer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rizal.Fathurrahman
 */
public class Informasi {
    
    /* submitDocument */
    private String usrInsert, title, description, urlDokumen;
    
    /* updateDocument */
    private String savedFileName, oldTitle, newFileName;
    
    /* deleteDocument */
    private int aktif;
    private String usrUpdate;
    
    // ==== done_upload_to_live ====
    
    public Boolean submitToInformasi() {
        CallableStatement cStmt = null;
        Connection conn = null;
        Boolean res = true;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_submitToInformasi (?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUsrInsert().trim());
            cStmt.setString(2, getUrlDokumen().trim());
            cStmt.setString(3, getTitle().trim());
            cStmt.setString(4, getDescription().trim());
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
    
    public String deleteInformasi(){
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_deleteInformasi(?,?,?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getTitle());
            cStmt.setInt(2, getAktif());
            cStmt.setString(3, getUsrUpdate());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("judulInfo",       rs.getString("judulInfo").trim());
                obj.put("aktif",     rs.getString("aktif").trim());
                prDt.add(obj);
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
        return prDt.toJSONString();
    }
    
    public String updateToInformasi(){
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_updateToInformasi(?,?,?,?,?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUsrUpdate());
            cStmt.setString(2, getUrlDokumen());
            cStmt.setString(3, getOldTitle());
            cStmt.setString(4, getTitle());
            cStmt.setString(5, getDescription());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("judulInfo",       rs.getString("judulInfo").trim());
                obj.put("aktif",     rs.getString("aktif").trim());
                obj.put("counterInfo",     rs.getString("counterInfo").trim());
                obj.put("namaInfo",     rs.getString("namaInfo").trim());
                obj.put("keteranganInfo",     rs.getString("keteranganInfo").trim());
                obj.put("tglInsert",     rs.getString("tglInsert").trim());
                obj.put("usrInsert",     rs.getString("usrInsert").trim());
                obj.put("tglUpdate",     rs.getString("tglUpdate").trim());
                obj.put("usrUpdate",     rs.getString("usrUpdate").trim());
                prDt.add(obj);
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
        return prDt.toJSONString();
    }
    
    public String find_active_contributors(){
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_contributors_active}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("active_contributors",       rs.getString("active_contributors").trim());
                prDt.add(obj);
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
        return prDt.toJSONString();
        
    }
    
    public String find_unactive_contributors(){
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_contributors_unactive}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("unactive_contributors",       rs.getString("unactive_contributors").trim());
                prDt.add(obj);
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
        return prDt.toJSONString();
        
    }
    
    // ==== not_yet_upload_to_live ====
    
    public JSONArray findTopHighlight() {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_find_top_highlight}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
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
    
    public JSONArray removeSignHighlight() {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_remove_highlight (?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            
            // Get the parameters
            String oldTitle = getOldTitle();
            String newTitle = getTitle();
            String usrUpdate = getUsrUpdate();

            // Set the parameters for the stored procedure
            if (oldTitle == null || oldTitle.trim().isEmpty()) {
                cStmt.setNull(1, Types.VARCHAR); // Set null for the first parameter
            } else {
                cStmt.setString(1, oldTitle.trim());
            }

            if (newTitle == null || newTitle.trim().isEmpty()) {
                cStmt.setNull(2, Types.VARCHAR); // Set null for the second parameter
            } else {
                cStmt.setString(2, newTitle.trim());
            }

            if (usrUpdate == null || usrUpdate.trim().isEmpty()) {
                cStmt.setNull(3, Types.VARCHAR); // Set null for the third parameter
            } else {
                cStmt.setString(3, usrUpdate.trim());
            }

            // Execute the stored procedure
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
    
    public JSONArray removeTopHighlight() {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_remove_top_highlight (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getTitle().trim());
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
    
    public JSONArray getAllActiveInfo() {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_getall_active_info}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
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
    
    
    public String getUsrUpdate(){
        return this.usrUpdate;
    }
    
    public void setUsrUpdate(String usr){
        this.usrUpdate = usr;
    }
    
    public int getAktif(){
        return this.aktif;
    }
    
    public void setAktif(int status){
        this.aktif = status;
    }
    
    public String getNewFileName(){
        return this.newFileName;
    }
    
    public void setNewFileName(String fileName){
        this.newFileName = fileName;
    }
    
    public String getSavedFileName(){
        return this.savedFileName;
    }
    
    public void setSavedFileName(String fileName){
        this.savedFileName = fileName;
    }
    
    public String getUsrInsert(){
        return this.usrInsert;
    }
    
    public void setUsrInsert(String usr){
        this.usrInsert = usr;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getOldTitle(){
        return this.oldTitle;
    }
    
    public void setOldTitle(String title){
        this.oldTitle = title;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String des){
        this.description = des;
    }
    
    public String getUrlDokumen(){
        return this.urlDokumen;
    }
    
    public void setUrlDokumen(String url){
        this.urlDokumen = url;
    }
    
    
}
    
    
