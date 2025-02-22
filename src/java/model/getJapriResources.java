/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rizal.Fathurrahman
 */
@WebServlet(name = "getJapriResources", urlPatterns = {"/getJapriResources"})
public class getJapriResources extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public JSONArray selectDevices() {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_japri_getdevices}",
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
    
    public JSONArray selectAllTeam() {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_japri_getteamit}",
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
    
    public JSONArray selectAllAuthorizedUsers() {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_japri_getauthorizedusers}",
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
        
    public JSONArray getPerbaikanKomputer(String uname) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
//                    "{CALL sp_eform_viewall_perbaikankomputer_by_user_new(?)}",
                    "{CALL sp_viewall_eformPerbaikanKomputer_accepted_2nd(?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, uname);
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
    
    public JSONArray getFormData(String noform) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_eform_perbaikanKomputer_FormCurrentData_fromJapriByEDP (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, noform);
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
    
    public JSONArray getAllStatistics(String uname) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_getStatisticforjapri (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, uname);
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
    
    public JSONArray isKanit(String npp, String username, String fullname, String lokasi, String unit, String dept) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_eform_findKanitStructure_fromJapriByEDP (?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
//            cStmt.setString(1, npp);
            
//            cStmt.setString(3, fullname);
            cStmt.setString(1, lokasi);
            cStmt.setString(2, unit);
            cStmt.setString(3, dept);
            cStmt.setString(4, username);
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
    
    public JSONArray getPerbaikanKomputerSubmitted(String uname) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_viewall_eformPerbaikanKomputer_submitted_2nd(?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, uname);
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
    
    public JSONArray getMappingUser() {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_japri_queryMapping}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
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
    
    public JSONArray getUserDept(String kdBagian) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_user_departemen (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, kdBagian);
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
    
    public JSONArray getTotalConfirmationUser(String uname) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_konfirmasiUser (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, uname);
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
    
    public JSONArray getMappingBagian() {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_japri_queryBagian}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
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
    
    public JSONArray getTokenNotification(String uname, String npp, String token) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_tokens (?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, uname);
            cStmt.setString(2, npp);
            cStmt.setString(3, token);
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
    
    public JSONArray getTokenByNpp(String npp) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_tokens_bynpp (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, npp);
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
    
    public JSONArray getTokenByUsername(String uname) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_tokens_byuname (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, uname);
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
    
    public JSONArray getFirebaseTokenNotification(String uname, String npp, String token) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_firebase_tokens (?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, uname);
            cStmt.setString(2, npp);
            cStmt.setString(3, token);
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
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
