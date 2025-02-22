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
public class absensi {
    
    private String npp, startDate, endDate;

    //<editor-fold defaultstate="collapsed" desc="Insert">
    public JSONArray insertAbsensi(String nopegawai, String tanggal, String jnscuti, String khusus) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_insert_absensi (?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, nopegawai.trim());
            cStmt.setString(2, tanggal.trim());
            cStmt.setString(3, jnscuti.trim());
            cStmt.setString(4, khusus.trim());
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
            Logger.getLogger(absensi.class.getName()).log(Level.SEVERE, null, ex);

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

    public boolean insertAbsensiFoto(String counterAbs, String nopegawai, byte[] foto, String jnsfoto, String nmafoto) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_insert_absensi_foto (?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterAbs.trim());
            cStmt.setString(2, nopegawai.trim());
            cStmt.setBytes(3, (byte[]) foto);
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

    //<editor-fold defaultstate="collapsed" desc="Update">
    public boolean updateValidAbsensi(String counterAbs, String nopegawai) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_update_absensi_validasi (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterAbs.trim());
            cStmt.setString(2, nopegawai.trim());
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
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Delete">
    public boolean deleteAbsensi(String counterAbs, String nopegawai) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_delete_absensi (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterAbs.trim());
            cStmt.setString(2, nopegawai.trim());
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
//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Open">
    public JSONArray selectHistoryPresensiPegawai(String npp, String initialDate, String endDate) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_listView_historyPresensi(?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, npp.trim());
            cStmt.setString(2, initialDate.trim());
            cStmt.setString(3, endDate.trim());
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
            Logger.getLogger(absensi.class.getName()).log(Level.SEVERE, null, ex);

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

    public JSONArray selectOneAbsensi(String counterAbs, String nopegawai) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_select_one_absensi(?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, counterAbs.trim());
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
            Logger.getLogger(absensi.class.getName()).log(Level.SEVERE, null, ex);

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

    public JSONArray selectOneCuti(String nopegawai) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_cek_cuti_pegawai(?)}",
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
            Logger.getLogger(absensi.class.getName()).log(Level.SEVERE, null, ex);

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

    public JSONArray selectTglCuti(String nopegawai) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_japri_absensi_tanggal(?)}",
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
            Logger.getLogger(absensi.class.getName()).log(Level.SEVERE, null, ex);

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

    public JSONArray viewTglMerah() {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_view_tglmerah}",
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
                            obj.put(fieldname, rs.getBoolean(i));
                            break;
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
                            }
                            break;
                    }
                    i++;
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(absensi.class.getName()).log(Level.SEVERE, null, ex);

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
    
    public String view_perbaikanKomputer_after_insert_bynoform() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_perbaikanKomputer_view_form_bynoform (?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNpp().trim());
            cStmt.setString(2, getStartDate().trim());
            cStmt.setString(3, getEndDate().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
//                   unit,kepalaUnit,approveUser,Probleam,device,NoForm,
//       tglTerima,jamterima,terimaOleh,tglSelesai,jamSelesai,diselesaikanOleh,
//       ApproveUser2, mgrApprove,usrInsert,usrUpdate
                obj.put("unit", rs.getString(1).trim());
                obj.put("kepalaUnit", rs.getString(2).trim());
                obj.put("approveUser", rs.getString(3).trim());
                obj.put("Probleam", rs.getString(4).trim());
                obj.put("device", rs.getString(5).trim());
                obj.put("NoForm", rs.getString(6).trim());
                obj.put("tglTerima", rs.getString(7).trim());
                obj.put("jamterima", rs.getString(8).trim());
                obj.put("terimaOleh", rs.getString(9).trim());
                obj.put("tglSelesai", rs.getString(10).trim());
                obj.put("jamSelesai", rs.getString(11).trim());
                obj.put("diselesaikanOleh", rs.getString(12).trim());
                obj.put("ApproveUser2", rs.getString(13).trim());
                obj.put("mgrApprove", rs.getString(14).trim());
                obj.put("usrInsert", rs.getString(15).trim());
                obj.put("usrUpdate", rs.getString(16).trim());
                obj.put("usrApprove2", rs.getString(17).trim());
                obj.put("manager", rs.getString(18).trim());
                obj.put("usrInsert2", rs.getString(19).trim());
                obj.put("lokasiKerja", rs.getString(20).trim());
                obj.put("departemen", rs.getString(21).trim());
                obj.put("prioritas", rs.getString(22).trim());
                obj.put("tglinsert", rs.getString(23).trim());
                obj.put("tglapproveuser2", rs.getString(24).trim());
                obj.put("tglapprovemgr", rs.getString(25).trim());
                prDt.add(obj);
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void setStartDate(String start){
        this.startDate = start;
    }
    
    public String getStartDate(){
        return this.startDate;
    }
    
    public void setEndDate(String start){
        this.endDate = start;
    }
    
    public String getEndDate(){
        return this.endDate;
    }
    
    public void setNpp(String start){
        this.npp = start;
    }
    
    public String getNpp(){
        return this.npp;
    }

//</editor-fold>
}




