/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connDB;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author endra
 */
public class formHakAkses {

    private String noForm,
            namaPegawai,
            noPegawai,
            unitKerja,
            jabatanKerja,
            userName,
            aksesFungsional,
            jnsPermohonan,
            mutasi,
            fasilitas,
            userMinta, jabatan, approveLevel,
            kdlock,
            kdUnit,
            kdDept;
    private String prioritas;
    private String alasanTolak;
    private String catatan;

    public formHakAkses() {
    }

    public formHakAkses(String noForm, String namaPegawai, String noPegawai, String unitKerja, String jabatanKerja, String userName, String aksesFungsional, String jnsPermohonan, String mutasi, String fasilitas, String userMinta, String jabatan, String approveLevel) {
        this.noForm = noForm;
        this.namaPegawai = namaPegawai;
        this.noPegawai = noPegawai;
        this.unitKerja = unitKerja;
        this.jabatanKerja = jabatanKerja;
        this.userName = userName;
        this.aksesFungsional = aksesFungsional;
        this.jnsPermohonan = jnsPermohonan;
        this.mutasi = mutasi;
        this.fasilitas = fasilitas;
        this.userMinta = userMinta;
        this.jabatan = jabatan;
        this.approveLevel = approveLevel;
    }

    public void getListPegawai(File name) {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray arrList = new JSONArray();
        JSONObject objPeg;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_view_semua_pegawai}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            FileWriter fileWriter = new FileWriter(name);
            //formating sesuai ajax data
            try ( // Always wrap FileWriter in BufferedWriter.
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                while (rs.next()) {
                    objPeg = new JSONObject();
                    objPeg.put("value", rs.getString(2).trim());
                    objPeg.put("data", rs.getString(1).trim());
                    objPeg.put("kdLokasi", rs.getString(3).trim());
                    if (rs.getString(4) == null) {
                        objPeg.put("nmLokasiKerja", "");
                    } else {
                        objPeg.put("nmLokasiKerja", rs.getString(4).trim());
                    }
                    if (rs.getString(5) == null) {
                        objPeg.put("kdUnitKerja", "");
                    } else {
                        objPeg.put("kdUnitKerja", rs.getString(5).trim());
                    }
                    if (rs.getString(6) == null) {
                        objPeg.put("nmUnitKerja", "");
                    } else {
                        objPeg.put("nmUnitKerja", rs.getString(6).trim());
                    }
                    if (rs.getString(7) == null) {
                        objPeg.put("kdDepartemen", "");
                    } else {
                        objPeg.put("kdDepartemen", rs.getString(7).trim());
                    }
                    objPeg.put("departemen", rs.getString(8).trim());
                    arrList.add(objPeg);
                }
                bufferedWriter.write(arrList.toJSONString());
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(formHakAkses.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public void getListLokasiKerja(File name) {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray arrList = new JSONArray();
        JSONObject objPeg;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_view_lokasi_unit_departemen_kerja}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            FileWriter fileWriter = new FileWriter(name);
            //formating sesuai ajax data
            try ( // Always wrap FileWriter in BufferedWriter.
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                while (rs.next()) {
                    objPeg = new JSONObject();
                    //Kode	nmlokasiKerja	kdUnitKerja	nmunitKerja	kdDepartemen	departemen

                    objPeg.put("data", rs.getString(1).trim());
                    objPeg.put("value", rs.getString(2).trim());
                    objPeg.put("kdUnitKerja", rs.getString(3).trim());
                    objPeg.put("nmunitKerja", rs.getString(4).trim());
                    objPeg.put("kdDepartemen", rs.getString(5).trim());
                    objPeg.put("departemen", rs.getString(6).trim());
                    arrList.add(objPeg);
                }
                bufferedWriter.write(arrList.toJSONString());
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(formHakAkses.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public void tolak_hak_akses() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_tolak (?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getUserMinta().trim());
            cStmt.setString(3, getApproveLevel().trim());
            cStmt.setString(4, getAlasanTolak().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP"
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public void insert_hak_akses_baru() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_insert_baru (?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNamaPegawai().trim());
            cStmt.setString(2, getNoPegawai().trim());
            cStmt.setString(3, getJabatanKerja().trim());
            cStmt.setString(4, getUserName().trim());
            cStmt.setString(5, getAksesFungsional().trim());
            cStmt.setString(6, getJnsPermohonan().trim());
            cStmt.setString(7, getMutasi().trim());
            cStmt.setString(8, getFasilitas().trim());
            cStmt.setString(9, getUserMinta().trim());
            cStmt.setString(10, getJabatan().trim());
            cStmt.setString(11, getKdlock().trim());
            cStmt.setString(12, getKdUnit().trim());
            cStmt.setString(13, getKdDept().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP" 
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public void update_hak_akses() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_update_form (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getNamaPegawai().trim());
            cStmt.setString(3, getNoPegawai().trim());
            cStmt.setString(4, getJabatanKerja().trim());
            cStmt.setString(5, getUserName().trim());
            cStmt.setString(6, getAksesFungsional().trim());
            cStmt.setString(7, getJnsPermohonan().trim());
            cStmt.setString(8, getMutasi().trim());
            cStmt.setString(9, getFasilitas().trim());
            cStmt.setString(10, getUserMinta().trim());
            cStmt.setString(11, getJabatan().trim());
            cStmt.setString(12, getKdlock().trim());
            cStmt.setString(13, getKdUnit().trim());
            cStmt.setString(14, getKdDept().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP" 
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public String view_hak_akses_after_insert() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_view_form (?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUserMinta().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("noForm", rs.getString(1));
                obj.put("nmpegawai", rs.getString(2));
                obj.put("nopegawai", rs.getString(3));
                obj.put("jabatan", rs.getString(4));
                obj.put("username", rs.getString(5));
                obj.put("aksesfungsional", rs.getString(6));
                obj.put("jnspermohonan", rs.getString(7));
                obj.put("mutasi", rs.getString(8));
                obj.put("fasilitas", rs.getString(9));
                obj.put("userMinta", rs.getString(10));
                obj.put("tglMinta", rs.getString(11));
                obj.put("userApproval1", rs.getString(12));
                obj.put("tglApproval1", rs.getString(13));
                obj.put("userApproval2", rs.getString(14));
                obj.put("tglApproval2", rs.getString(15));
                obj.put("userApproval3", rs.getString(16));
                obj.put("tglApproval3", rs.getString(17));
                obj.put("userApproval4", rs.getString(18));
                obj.put("tglApproval4", rs.getString(19));
                obj.put("tglTerima", rs.getString(20));
                obj.put("userTerima", rs.getString(21));
                obj.put("tglSelesai", rs.getString(22));
                obj.put("userSelesai", rs.getString(23));
                obj.put("proses", rs.getString(24));
                obj.put("userKonfirm", rs.getString(25));
                obj.put("kdlock", rs.getString(26));
                obj.put("kdUnit", rs.getString(27));
                obj.put("kdDept", rs.getString(28));
                obj.put("nmLock", rs.getString(29));
                obj.put("nmUnit", rs.getString(30));
                obj.put("nmDept", rs.getString(31));
                obj.put("jbtn1", rs.getString(32));
                obj.put("jbtn2", rs.getString(33));
                obj.put("jbtn3", rs.getString(34));
                obj.put("jbtn4", rs.getString(35));
                obj.put("jbtn5", rs.getString(36));
                obj.put("tglStatus", rs.getString(37));
                obj.put("userStatus", rs.getString(38));
                obj.put("alasanTolak", rs.getString(39));
                obj.put("catatan1", rs.getString(40));
                obj.put("catatan2", rs.getString(41));
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

    public String view_all_hak_akses_by_user() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_view_all_hak_akses_by_user (?,?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUserMinta().trim());
            cStmt.setString(2, getJabatan().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("noForm", rs.getString(1));
                obj.put("nmpegawai", rs.getString(2));
                obj.put("nopegawai", rs.getString(3));
                obj.put("jabatan", rs.getString(4));
                obj.put("username", rs.getString(5));
                obj.put("aksesfungsional", rs.getString(6));
                obj.put("jnspermohonan", rs.getString(7));
                obj.put("mutasi", rs.getString(8));
                obj.put("fasilitas", rs.getString(9));
                obj.put("userMinta", rs.getString(10));
                obj.put("tglMinta", rs.getString(11));
                obj.put("userApproval1", rs.getString(12));
                obj.put("tglApproval1", rs.getString(13));
                obj.put("userApproval2", rs.getString(14));
                obj.put("tglApproval2", rs.getString(15));
                obj.put("userApproval3", rs.getString(16));
                obj.put("tglApproval3", rs.getString(17));
                obj.put("userApproval4", rs.getString(18));
                obj.put("tglApproval4", rs.getString(19));
                obj.put("tglTerima", rs.getString(20));
                obj.put("userTerima", rs.getString(21));
                obj.put("tglSelesai", rs.getString(22));
                obj.put("userSelesai", rs.getString(23));
                obj.put("proses", rs.getString(24));
                obj.put("userKonfirm", rs.getString(25));
                obj.put("kdlock", rs.getString(26));
                obj.put("kdUnit", rs.getString(27));
                obj.put("kdDept", rs.getString(28));
                obj.put("nmLock", rs.getString(29));
                obj.put("nmUnit", rs.getString(30));
                obj.put("nmDept", rs.getString(31));
                obj.put("jbtn1", rs.getString(32));
                obj.put("jbtn2", rs.getString(33));
                obj.put("jbtn3", rs.getString(34));
                obj.put("jbtn4", rs.getString(35));
                obj.put("jbtn5", rs.getString(36));
                obj.put("tglStatus", rs.getString(37));
                obj.put("userStatus", rs.getString(38));
                obj.put("alasanTolak", rs.getString(39));
                obj.put("catatan1", rs.getString(40));
                obj.put("catatan2", rs.getString(41));

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

    public String view_hak_akses_after_approve() {
        //
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_view_form_approve (?,?,?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getUserMinta().trim());
            cStmt.setString(3, getApproveLevel().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            obj = new JSONObject();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("noForm", rs.getString(1));
                obj.put("nmpegawai", rs.getString(2));
                obj.put("nopegawai", rs.getString(3));
                obj.put("jabatan", rs.getString(4));
                obj.put("username", rs.getString(5));
                obj.put("aksesfungsional", rs.getString(6));
                obj.put("jnspermohonan", rs.getString(7));
                obj.put("mutasi", rs.getString(8));
                obj.put("fasilitas", rs.getString(9));
                obj.put("userMinta", rs.getString(10));
                obj.put("tglMinta", rs.getString(11));
                obj.put("userApproval1", rs.getString(12));
                obj.put("tglApproval1", rs.getString(13));
                obj.put("userApproval2", rs.getString(14));
                obj.put("tglApproval2", rs.getString(15));
                obj.put("userApproval3", rs.getString(16));
                obj.put("tglApproval3", rs.getString(17));
                obj.put("userApproval4", rs.getString(18));
                obj.put("tglApproval4", rs.getString(19));
                obj.put("tglTerima", rs.getString(20));
                obj.put("userTerima", rs.getString(21));
                obj.put("tglSelesai", rs.getString(22));
                obj.put("userSelesai", rs.getString(23));
                obj.put("proses", rs.getString(24));
                obj.put("userKonfirm", rs.getString(25));
                obj.put("kdlock", rs.getString(26));
                obj.put("kdUnit", rs.getString(27));
                obj.put("kdDept", rs.getString(28));
                obj.put("nmLock", rs.getString(29));
                obj.put("nmUnit", rs.getString(30));
                obj.put("nmDept", rs.getString(31));
                obj.put("jbtn1", rs.getString(32));
                obj.put("jbtn2", rs.getString(33));
                obj.put("jbtn3", rs.getString(34));
                obj.put("jbtn4", rs.getString(35));
                obj.put("jbtn5", rs.getString(36));
                obj.put("tglStatus", rs.getString(37));
                obj.put("userStatus", rs.getString(38));
                obj.put("alasanTolak", rs.getString(39));
                obj.put("catatan1", rs.getString(40));
                obj.put("catatan2", rs.getString(41));
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

//<editor-fold defaultstate="collapsed" desc="Notifikasi Hak Akses">
    public String notif_hak_akses() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_view_all_notif (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUserMinta().trim());
            cStmt.setString(2, getJabatan().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP"
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            obj = new JSONObject();
            while (rs.next()) {
                obj.put(rs.getString(2).trim(), rs.getString(1));
            }
            prDt.add(obj);
            cStmt.close();
            conn.close();
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
//</editor-fold>

    public void approve_hak_akses() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_approve (?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getUserMinta().trim());
            cStmt.setString(3, getApproveLevel().trim());
            cStmt.setString(4, getCatatan().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP" 
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public void terima_hak_akses() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_terima (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getUserMinta().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP" 
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    public void selesai_program_data() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_hakAkses_selesai (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getUserMinta().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP" 
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    //<editor-fold defaultstate="collapsed" desc="Set/Get">
    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }

    public void setNamaPegawai(String namaPegawai) {
        this.namaPegawai = namaPegawai;
    }

    public String getNoPegawai() {
        return noPegawai;
    }

    public void setNoPegawai(String noPegawai) {
        this.noPegawai = noPegawai;
    }

    public String getUnitKerja() {
        return unitKerja;
    }

    public void setUnitKerja(String unitKerja) {
        this.unitKerja = unitKerja;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAksesFungsional() {
        return aksesFungsional;
    }

    public void setAksesFungsional(String aksesFungsional) {
        this.aksesFungsional = aksesFungsional;
    }

    public String getJnsPermohonan() {
        return jnsPermohonan;
    }

    public void setJnsPermohonan(String jnsPermohonan) {
        this.jnsPermohonan = jnsPermohonan;
    }

    public String getMutasi() {
        return mutasi;
    }

    public void setMutasi(String mutasi) {
        this.mutasi = mutasi;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getUserMinta() {
        return userMinta;
    }

    public void setUserMinta(String userMinta) {
        this.userMinta = userMinta;
    }

    public String getNoForm() {
        return noForm;
    }

    public void setNoForm(String noForm) {
        this.noForm = noForm;
    }

    public String getJabatanKerja() {
        return jabatanKerja;
    }

    public void setJabatanKerja(String jabatanKerja) {
        this.jabatanKerja = jabatanKerja;
    }

    public String getApproveLevel() {
        return approveLevel;
    }

    public void setApproveLevel(String approveLevel) {
        this.approveLevel = approveLevel;
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
//</editor-fold>

    public String getAlasanTolak() {
        return alasanTolak;
    }

    public void setAlasanTolak(String alasanTolak) {
        this.alasanTolak = alasanTolak;
    }
}
