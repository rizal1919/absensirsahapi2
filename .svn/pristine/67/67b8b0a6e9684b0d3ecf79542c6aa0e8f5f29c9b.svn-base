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
 * @author fadhil
 */
public class acara {

    //<editor-fold defaultstate="collapsed" desc="Open">
    public JSONArray openListAcara(String nopegawai, String tanggal) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_open_list_acara (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, nopegawai.trim());
            cStmt.setString(2, tanggal.trim());
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
                        default:
                            if (rs.getObject(i) != null) {
                                obj.put(fieldname, rs.getString(i));
                            } else {
                                obj.put(fieldname, null);
                            }   break;
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(acara.class.getName()).log(Level.SEVERE, null, ex);

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

    public JSONArray openListAcaraBulanan(String npp, String bln, String thn) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_japri_open_list_acara_bulanan (?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, npp.trim());
            cStmt.setString(2, bln.trim());
            cStmt.setString(3, thn.trim());
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
                        default:
                            if (rs.getObject(i) != null) {
                                obj.put(fieldname, rs.getString(i));
                            } else {
                                obj.put(fieldname, null);
                            }   break;
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(acara.class.getName()).log(Level.SEVERE, null, ex);

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

    public JSONArray selectOneUndangan(Integer counterUndangan, String nopegawai) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_select_one_undangan (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setInt(1, counterUndangan);
            cStmt.setString(2, nopegawai.trim());
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
                        default:
                            if (rs.getObject(i) != null) {
                                obj.put(fieldname, rs.getString(i).trim());
                            } else {
                                obj.put(fieldname, null);
                            }   break;
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(acara.class.getName()).log(Level.SEVERE, null, ex);

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

    //<editor-fold defaultstate="collapsed" desc="Insert">
    public JSONArray insertAcara(String nopegawai, Float latitude, Float longitude, Integer counterUndangan, String keterangan) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_insert_absen_acara (?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, nopegawai.trim());
            cStmt.setFloat(2, latitude);
            cStmt.setFloat(3, longitude);
            cStmt.setInt(4, counterUndangan);
            cStmt.setString(5, keterangan.trim());
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
                        default:
                            if (rs.getObject(i) != null) {
                                obj.put(fieldname, rs.getString(i).trim());
                            } else {
                                obj.put(fieldname, null);
                            }   break;
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(acara.class.getName()).log(Level.SEVERE, null, ex);

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

    public boolean insertAcaraFoto(String counterFoto, String nopegawai, byte[] foto, String jnsfoto, String nmafoto) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_insert_absen_acara_foto (?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterFoto.trim());
            cStmt.setString(2, nopegawai.trim());
            cStmt.setBytes(3, (byte[]) foto);
            cStmt.setString(4, jnsfoto.trim());
            cStmt.setString(5, nmafoto.trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(acara.class.getName()).log(Level.SEVERE, null, ex);
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

    //<editor-fold defaultstate="collapsed" desc="Update">
    public JSONArray updateAcara(String counterAcr, String nopegawai) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_update_absen_acara (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterAcr.trim());
            cStmt.setString(2, nopegawai.trim());
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
                        default:
                            if (rs.getObject(i) != null) {
                                obj.put(fieldname, rs.getString(i).trim());
                            } else {
                                obj.put(fieldname, null);
                            }   break;
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(acara.class.getName()).log(Level.SEVERE, null, ex);

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
