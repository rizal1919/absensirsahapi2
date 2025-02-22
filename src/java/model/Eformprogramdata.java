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
 * @author aldi
 */

public class Eformprogramdata {
    
    private String uname, passwd, nama;
    private String npp;
    private boolean admin;
    
    //<editor-fold defaultstate="collapsed" desc="priv var">
    private String COUNTERs,
            noForm,
            kategoriBaru,
            JenisProgram,
            NamaProgram,
            ketNamaProgram,
            keteranganForm,
            keteranganTindakLanjut,
            userMinta,
            kdlock,
            kdUnit,
            kdDept;
    private String prioritas;
    private String jabatan;
    private String approveLevel;
    private String alasanTolak;
    private String catatan;
    private String programmer;

    //<editor-fold defaultstate="collapsed" desc="Open">
//    public void insertFormProgramData_test() {
//        CallableStatement cStmt = null;
//        Connection conn = null;
//        try {
//            connDB tes = new connDB();
//            conn = tes.getConnection();
//            cStmt = conn.prepareCall(
//                    "{CALL sp_eform_programData_insert_baru (?,?,?,?,?,?,?,?,?,?,?)}",
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            cStmt.setString(1, getKategoriBaru().trim());
//            cStmt.setString(2, getJenisProgram().trim());
//            cStmt.setString(3, getNamaProgram().trim());
//            cStmt.setString(4, getKetNamaProgram().trim());
//            cStmt.setString(5, getKeteranganForm().trim());
//            cStmt.setString(6, getUserMinta().trim());
//            cStmt.setString(7, getJabatan().trim());
//            cStmt.setString(8, getKdlock().trim());
//            cStmt.setString(9, getKdUnit().trim());
//            cStmt.setString(10, getKdDept().trim());
//            cStmt.setString(11, getProgrammer().trim());
//            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP"
//            cStmt.executeUpdate();
//            cStmt.close();
//            conn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(Eformprogramdata.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            // Always make sure result sets and statements are closed,
//            // and the connection is returned to the pool
//            if (cStmt != null) {
//                try {
//                    cStmt.close();
//                } catch (SQLException e) {
//                }
//                cStmt = null;
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                }
//                conn = null;
//            }
//        }
//    }
    
//    public JSONArray insertFormProgramData() {
//        ResultSet rs = null;
//        JSONArray result = new JSONArray();
//        CallableStatement cStmt = null;
//        Connection conn = null;
//        try {
//            connDB tes = new connDB();
//            conn = tes.getConnection();
//            cStmt = conn.prepareCall(
//                    "{CALL sp_eform_programData_insert_baru (?,?,?,?,?,?,?,?,?,?)}",
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            cStmt.setString(1, getKategoriBaru().trim());
//            cStmt.setString(2, getJenisProgram().trim());
//            cStmt.setString(3, getNamaProgram().trim());
//            cStmt.setString(4, getKetNamaProgram().trim());
//            cStmt.setString(5, getKeteranganForm().trim());
//            cStmt.setString(6, getUserMinta().trim());
//            cStmt.setString(7, getJabatan().trim());
//            cStmt.setString(8, getKdlock().trim());
//            cStmt.setString(9, getKdUnit().trim());
//            cStmt.setString(10, getKdDept().trim());
//            //cStmt.setString(11, getProgrammer().trim());
//            rs = cStmt.executeQuery();
//            JSONObject obj = null;
//            int cols = rs.getMetaData().getColumnCount();
//            String fieldname;
//            int dataType;
//            while (rs.next()) {
//                int i = 1;
//                obj = new JSONObject();
//                while (i <= cols) {
//                    fieldname = rs.getMetaData().getColumnName(i).trim();
//                    dataType = rs.getMetaData().getColumnType(i);
//                    switch (dataType) {
//                        case Types.BIT:
//                            obj.put(fieldname, rs.getBoolean(i));
//                            break;
//                        case Types.BOOLEAN:
//                            obj.put(fieldname, rs.getBoolean(i));
//                            break;
//                        case Types.INTEGER:
//                            obj.put(fieldname, rs.getInt(i));
//                            break;
//                        default:
//                            if (rs.getObject(i) != null) {
//                                obj.put(fieldname, rs.getString(i).trim());
//                            } else {
//                                obj.put(fieldname, null);
//                            }
//                            break;
//                    }
//                    i++;
//                }
//                result.add(obj);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Eformprogramdata.class.getName()).log(Level.SEVERE, null, ex);
//
//        } finally {
//            // Always make sure result sets and statements are closed,
//            // and the connection is returned to the pool
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                }
//                rs = null;
//            }
//            if (cStmt != null) {
//                try {
//                    cStmt.close();
//                } catch (SQLException e) {
//                }
//                cStmt = null;
//            }
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                }
//                conn = null;
//            }
//        }
//        return result;
//    }

    public boolean insertFormProgramData(
            String programbaru, 
            String jenisPermin, 
            String namaProgram, 
            String ketNama, 
            String ketRangan, 
            String user, 
            String jabatan, 
            String lokasi, 
            String unit, 
            String Departemen) {
        boolean result = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_insert_baru_japri (?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, programbaru.trim());
            cStmt.setString(2, jenisPermin.trim());
            cStmt.setString(3, namaProgram.trim());
            cStmt.setString(4, ketNama.trim());
            cStmt.setString(5, ketRangan.trim());
            cStmt.setString(6, user.trim());
            cStmt.setString(7, jabatan.trim());
            cStmt.setString(8, lokasi.trim());
            cStmt.setString(9, unit.trim());
            cStmt.setString(10, Departemen.trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            result = false;
            Logger.getLogger(Eformprogramdata.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public boolean selec_user_eform(String nopegawai) {

        boolean login = false;
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL speform_login_japri(?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, nopegawai.trim());
//            cStmt.setString(2, getPasswd());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            //1. UserID	2. Password	3. kdpoli	4. kddokter
            if (rs.next()) {
                setUname(rs.getString(1).trim());
                setAdmin(rs.getBoolean(3));
                if (!"".equals(rs.getString(4).trim())) {
                    login = true;
                }
                setNpp(rs.getString(4).trim());
                setJabatan(rs.getString(5).trim());
                setNama(rs.getString(1).trim());
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
        return login;
    }
    
    public JSONArray selectLokasiKerja() {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL lokasi_kerja_japri}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
//            cStmt.setString(1, kdjenis.trim());
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
            Logger.getLogger(Eformprogramdata.class.getName()).log(Level.SEVERE, null, ex);

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
    
    public JSONArray openTelpSaringUrutCari(String saring, String cari) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL spjapri_select_telpSaringUrutCari (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, saring.trim());
            cStmt.setString(2, cari.trim());
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
            Logger.getLogger(Eformprogramdata.class.getName()).log(Level.SEVERE, null, ex);

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
            Logger.getLogger(Eformprogramdata.class.getName()).log(Level.SEVERE, null, ex);

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
            Logger.getLogger(Eformprogramdata.class.getName()).log(Level.SEVERE, null, ex);

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
            Logger.getLogger(Eformprogramdata.class.getName()).log(Level.SEVERE, null, ex);

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
    
    //<editor-fold defaultstate="collapsed" desc="Set/Get">
    public String getAlasanTolak() {
        return alasanTolak;
    }

    public void setAlasanTolak(String alasanTolak) {
        this.alasanTolak = alasanTolak;
    }

    public String getCOUNTERs() {
        return COUNTERs;
    }

    public void setCOUNTERs(String COUNTERs) {
        this.COUNTERs = COUNTERs;
    }

    public String getNoForm() {
        return noForm;
    }

    public void setNoForm(String noForm) {
        this.noForm = noForm;
    }

    public String getKategoriBaru() {
        return kategoriBaru;
    }

    public void setKategoriBaru(String kategoriBaru) {
        this.kategoriBaru = kategoriBaru;
    }

    public String getJenisProgram() {
        return JenisProgram;
    }

    public void setJenisProgram(String JenisProgram) {
        this.JenisProgram = JenisProgram;
    }

    public String getNamaProgram() {
        return NamaProgram;
    }

    public void setNamaProgram(String NamaProgram) {
        this.NamaProgram = NamaProgram;
    }

    public String getKetNamaProgram() {
        return ketNamaProgram;
    }

    public void setKetNamaProgram(String ketNamaProgram) {
        this.ketNamaProgram = ketNamaProgram;
    }

    public String getKeteranganForm() {
        return keteranganForm;
    }

    public void setKeteranganForm(String keteranganForm) {
        this.keteranganForm = keteranganForm;
    }

    public String getUserMinta() {
        return userMinta;
    }

    public void setUserMinta(String userMinta) {
        this.userMinta = userMinta;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getApproveLevel() {
        return approveLevel;
    }

    public void setApproveLevel(String approveLevel) {
        this.approveLevel = approveLevel;
    }

    public String getKeteranganTindakLanjut() {
        return keteranganTindakLanjut;
    }

    public void setKeteranganTindakLanjut(String keteranganTindakLanjut) {
        this.keteranganTindakLanjut = keteranganTindakLanjut;
    }

    public String getKdlock() {
        return kdlock;
    }

    public void setKdlock(String kdlock) {
        this.kdlock = kdlock;
    }

    public String getKdUnit() {
        return kdUnit;
    }

    public void setKdUnit(String kdUnit) {
        this.kdUnit = kdUnit;
    }

    public String getKdDept() {
        return kdDept;
    }

    public void setKdDept(String kdDept) {
        this.kdDept = kdDept;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }
//</editor-fold>

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    /**
     * @return the programmer
     */
    public String getProgrammer() {
        return programmer;
    }

    /**
     * @param programmer the programmer to set
     */
    public void setProgrammer(String programmer) {
        this.programmer = programmer;
    }
    
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNpp() {
        return npp;
    }

    public void setNpp(String npp) {
        this.npp = npp;
    }

    /**
     * @return the nama
     */
    public String getNama() {
        return nama;
    }

    /**
     * @param nama the nama to set
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

//</editor-fold>
}




