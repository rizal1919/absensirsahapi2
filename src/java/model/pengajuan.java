/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
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
 * @author fadhil
 */
public class pengajuan {

    public JSONArray selectAllPengajuan(String nopegawai) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_japri_select_all_pengajuan (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, nopegawai.trim());
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

                    switch (dataType) {
                        case Types.BIT:
                        case Types.BOOLEAN:
                            obj.put(fieldname, rs.getBoolean(i));
                            break;
                        case Types.INTEGER:
                            obj.put(fieldname, rs.getInt(i));
                            break;
                        case Types.VARBINARY:
                            obj.put(fieldname, rs.getString(i));
                            break;
                        default:
                            if (rs.getObject(i) != null) {
                                obj.put(fieldname, rs.getString(i));
                            } else {
                                obj.put(fieldname, null);
                            }
                            break;
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pengajuan.class.getName()).log(Level.SEVERE, null, ex);

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
    
    public JSONArray selectAllAtasan() {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_japri_select_all_atasan}",
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

                    switch (dataType) {
                        case Types.BIT:
                        case Types.BOOLEAN:
                            obj.put(fieldname, rs.getBoolean(i));
                            break;
                        case Types.INTEGER:
                            obj.put(fieldname, rs.getInt(i));
                            break;
                        case Types.VARBINARY:
                            obj.put(fieldname, rs.getString(i));
                            break;
                        default:
                            if (rs.getObject(i) != null) {
                                obj.put(fieldname, rs.getString(i));
                            } else {
                                obj.put(fieldname, null);
                            }
                            break;
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pengajuan.class.getName()).log(Level.SEVERE, null, ex);

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

    public JSONArray selectOnePengajuan(String counterPengajuan) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_japri_select_one_pengajuan (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterPengajuan.trim());
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

                    switch (dataType) {
                        case Types.BIT:
                        case Types.BOOLEAN:
                            obj.put(fieldname, rs.getBoolean(i));
                            break;
                        case Types.INTEGER:
                            obj.put(fieldname, rs.getInt(i));
                            break;
                        case Types.VARBINARY:
                            obj.put(fieldname, rs.getString(i));
                            break;
                        default:
                            if (rs.getObject(i) != null) {
                                obj.put(fieldname, rs.getString(i));
                            } else {
                                obj.put(fieldname, null);
                            }
                            break;
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(pengajuan.class.getName()).log(Level.SEVERE, null, ex);

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

    public boolean insertPengajuan(String jenisPengajuan, String judulPengajuan, String keteranganPengajuan, String nopegawai, String nppatasan) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_japri_insert_pengajuan (?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, jenisPengajuan.trim());
            cStmt.setString(2, judulPengajuan.trim());
            cStmt.setString(3, keteranganPengajuan.trim());
            cStmt.setString(4, nopegawai.trim());
            cStmt.setString(5, nppatasan.trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(pengajuan.class.getName()).log(Level.SEVERE, null, ex);
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
        return result;
    }
    
    public boolean updatePengajuan(String counterPengajuan, String statusPengajuan) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_japri_update_pengajuan (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterPengajuan.trim());
            cStmt.setString(2, statusPengajuan.trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(absensi.class.getName()).log(Level.SEVERE, null, ex);
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
        return result;
    }

}
