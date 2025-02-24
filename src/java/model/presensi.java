/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
public class presensi {

    //<editor-fold defaultstate="collapsed" desc="Insert">
    public boolean insertPresensi(String sn, String pin) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_insert_absen_att_log_new (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, sn.trim());
            cStmt.setString(2, pin.trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public JSONArray insertPresensiMasuk(String nopegawai, Boolean masuk, Boolean pulang, Boolean lembur, String keterangan, Float latitude, Float longitude) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_insert_presensi_masuk (?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, nopegawai.trim());
            cStmt.setBoolean(2, masuk);
            cStmt.setBoolean(3, pulang);
            cStmt.setBoolean(4, lembur);
            cStmt.setString(5, keterangan.trim());
            cStmt.setFloat(6, latitude);
            cStmt.setFloat(7, longitude);
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
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
            
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
    
    public JSONArray insertPresensiPulang(String counter, String nopegawai, Boolean masuk, Boolean pulang, Boolean lembur, String keterangan, Float latitude, Float longitude) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_insert_presensi_pulang (?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counter.trim());
            cStmt.setString(2, nopegawai.trim());
            cStmt.setBoolean(3, masuk);
            cStmt.setBoolean(4, pulang);
            cStmt.setBoolean(5, lembur);
            cStmt.setString(6, keterangan.trim());
            cStmt.setFloat(7, latitude);
            cStmt.setFloat(8, longitude);
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
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
            
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
    
    public boolean insertPresensiFoto(String counterAtt, String nopegawai, byte[] swafoto, String jnsfoto, String nmafoto) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_insert_presensi_foto (?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterAtt.trim());
            cStmt.setString(2, nopegawai.trim());
            cStmt.setBytes(3, (byte[]) swafoto);
            cStmt.setString(4, jnsfoto.trim());
            cStmt.setString(5, nmafoto.trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
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
    
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Open">
    public JSONArray selectJadwalDinas(String nopegawai, String counterAtt) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_select_one_presensi_jadwaldinas(?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, nopegawai.trim());
            cStmt.setString(2, counterAtt.trim());
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
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
            
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
    
    public JSONArray selectPresensiJadwalDinas(String counterPasangan, String noPegawai) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_select_presensi_jadwaldinas(?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterPasangan.trim());
            cStmt.setString(2, noPegawai.trim());
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
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
            
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
    
    public JSONArray selectPresensiMasukByNpp(String nopegawai) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_select_masuk_presensi_by_npp(?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, nopegawai);
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
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
            
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
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Update">
    public boolean updateValidPresensi(String counterAtt, String nopegawai) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_update_presensi_validasi (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterAtt.trim());
            cStmt.setString(2, nopegawai.trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean updateLemburPresensi(String counterAtt, String nopegawai, Boolean lembur, String keterangan) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_update_presensi_keterangan (?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterAtt.trim());
            cStmt.setString(2, nopegawai.trim());
            cStmt.setBoolean(3, lembur);
            cStmt.setString(4, keterangan.trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
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
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Delete">
    public boolean deletePresensi(String counterAtt, String nopegawai) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_delete_presensi (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterAtt.trim());
            cStmt.setString(2, nopegawai.trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, null, ex);
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
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="checkInValidation">
    public JSONArray checkInValidation(String npp, String counter) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL spjapri_checkin_validation (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counter.trim());
            cStmt.setString(2, npp.trim());
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="checkOutValidation">
    public JSONArray checkOutValidation(String npp, String counter) {
        ResultSet rs            = null;
        JSONArray result        = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn         = null;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL spjapri_checkout_validation (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counter.trim());
            cStmt.setString(2, npp.trim());
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

    //</editor-fold>

}
