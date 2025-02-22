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
//import model.eFrom.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.StyledEditorKit;
import model.connDB;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class formPerbaikanKomputer {

//<editor-fold defaultstate="collapsed" desc="get">
    private String Unit;
    private String noForm;
    private String KepalaUnit;
    private boolean ApproveUser, ApproveManger, ApproveUser2;
    private String Probleam;
    private String device;
    private String User;
    private String Npp;
    private String Departemen, lokasiKerja, prioritas, konfirmasi;
    private boolean validasi, periksa, ganti;
    private String Status, jumlah, jumlahContent;
//</editor-fold>
    private String tipe, nama, keterangan, subtipe, hnama, acceptedBy, doneBy, tglTerima, jamTerima, tglSelesai, jamSelesai, isDone, isPurposed ;
    private Boolean isChecked, isChanged;
    private String tipeLain, subtipeLain, usrNotify, usrEdp;

    
    public formPerbaikanKomputer() {
    }

    public void insertFormPerbaikanKomputer() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_insertPerbaikanKomputerHD (?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getLokasiKerja().trim());
            cStmt.setString(2, getDepartemen().trim());
            cStmt.setString(3, getUnit().trim());
            cStmt.setString(4, getKepalaUnit().trim());
            cStmt.setBoolean(5, isApproveUser());
            cStmt.setString(6, getProbleam().trim());
            cStmt.setString(7, getDevice().trim());
            cStmt.setString(8, getUser().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP" 
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }

    public void InsertPetugasTerimaPerbaikanKomputer() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_PerbaikanKomnputer_InsertPetugasTerima (?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUser().trim());
            cStmt.setString(2, getNoForm().trim());
            cStmt.setString(3, getPrioritas().trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }

    public void InsertUserApprovePerbaikanKomputer() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_PerbaikanKomnputer_InsertUserApprove (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUser().trim());
            cStmt.setString(2, getNoForm().trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }

    public void InsertManagerApprovePerbaikanKomputer() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_PerbaikanKomnputer_InsertManagerApprove (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setBoolean(1, true);
            cStmt.setString(2, getNoForm().trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }

    public void otomatisUserApproveEformPerbaikan() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL otomatisUserApproveEformPerbaikan}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);          
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }

    public void InsertPetugasSelesaiPerbaikanKomputer() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL sp_eform_perbaikanKomputer_insertPetugasSelesai (?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getUser().trim());
            cStmt.setString(3, getTipe().trim());
            cStmt.setString(4, getSubtipe().trim());
            cStmt.setString(5, getNama().trim());
            cStmt.setString(6, getHnama().trim());
            cStmt.setString(7, getKeterangan().trim());
            cStmt.setBoolean(8, isPeriksa());
            cStmt.setBoolean(9, isGanti());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }

    public void PetugasSimpanPerbaikanKomputer() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL sp_eform_perbaikanKomputer_PetugasSimpan (?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getUser().trim());
            cStmt.setString(3, getTipe().trim());
            cStmt.setString(4, getSubtipe().trim());
            cStmt.setString(5, getNama().trim());
            cStmt.setString(6, getHnama().trim());
            cStmt.setString(7, getKeterangan().trim());
            cStmt.setBoolean(8, isPeriksa());
            cStmt.setBoolean(9, isGanti());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }

    public void UpdatePetugasSelesaiPerbaikanKomputer() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL sp_eform_perbaikanKomputer_UpdatePetugasSelesai (?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getUser().trim());
            cStmt.setString(3, getTipe().trim());
            cStmt.setString(4, getSubtipe().trim());
            cStmt.setString(5, getNama().trim());
            cStmt.setString(6, getHnama().trim());
            cStmt.setString(7, getKeterangan().trim());
            cStmt.setBoolean(8, isPeriksa());
            cStmt.setBoolean(9, isGanti());
            cStmt.executeUpdate();

            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }

    // mengupdate statusEdit menjadi 1.
    public boolean updateStatusContent() {
        boolean berhasil = true;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL sp_eform_perbaikanKomputer_UpdateDisableAll (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getUser().trim());
            cStmt.executeUpdate();

            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
            berhasil = false;
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
        return berhasil;
    }

    public String view_perbaikanKomputer_after_insert() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_perbaikanKomputer_view_form (?,?,?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getProbleam());
            cStmt.setString(2, getUser());
            cStmt.setString(3, getDevice());
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
                obj.put("usrInsert2", rs.getString(19).trim());
                obj.put("tglinsert", rs.getString(21).trim());
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
            cStmt.setString(1, getNoForm().trim());
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
    
    public String rejectEFormDiajukan(String noform, String uname) {
        CallableStatement cStmt = null;
        ResultSet rs = null;
        JSONObject obj;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject result = new JSONObject(); // Untuk menyimpan hasil dalam format JSON

        try {
            connDB tes = new connDB();
            conn = tes.getConnection();

            // Panggil stored procedure
            cStmt = conn.prepareCall(
                    "{CALL sp_reject_submittedeform (?,?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // Set parameter input
            cStmt.setString(1, noform.trim());
            cStmt.setString(2, uname.trim());

            // Eksekusi query
            cStmt.executeQuery();

            rs = cStmt.getResultSet();
            while (rs.next()) {
                System.out.println(rs);
                obj = new JSONObject();
//                   unit,kepalaUnit,approveUser,Probleam,device,NoForm,
//       tglTerima,jamterima,terimaOleh,tglSelesai,jamSelesai,diselesaikanOleh,
//       ApproveUser2, mgrApprove,usrInsert,usrUpdate
                obj.put("approveUser", rs.getBoolean("approveUser"));
                prDt.add(obj);
            }
            cStmt.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(formPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
            result.put("error", ex.getMessage()); // Simpan error ke JSON jika terjadi
        } finally {
            // Pastikan untuk menutup semua resource yang digunakan
            if (cStmt != null) {
                try {
                    cStmt.close();
                } catch (SQLException e) {
                    // Ignore
                }
                cStmt = null;
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Ignore
                }
                conn = null;
            }
        }

        // Mengembalikan hasil sebagai JSON string
        return prDt.toJSONString();
    }
    
    public void view_pegawaiStruktural(File name) {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray arrList = new JSONArray();
        JSONObject objPeg;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL eform_struktural}",
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
                    objPeg.put("value", rs.getString(1).trim());
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

    public String view_perbaikanKomputer_userDepartemen() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL eform_perbaikanKomputer_userDepartmen (?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUser());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
//                   unit,kepalaUnit,approveUser,Probleam,device,NoForm,
//       tglTerima,jamterima,terimaOleh,tglSelesai,jamSelesai,diselesaikanOleh,
//       ApproveUser2, mgrApprove,usrInsert,usrUpdate
                obj.put("departement", rs.getString(2).trim());
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

    public String view_all_perbaikan_komputer_by_user() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_viewall_perbaikankomputer(?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUser().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("NoForm", rs.getString("NoForm").trim());
                obj.put("Probleam", rs.getString("Probleam").trim());
                obj.put("tglInsert", rs.getString("tglinsert").trim());
                obj.put("Status", rs.getString("Status").trim());
                obj.put("jabatan", rs.getString("jabatan").trim());
                obj.put("orderby", rs.getString("orderby").trim());
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

    public String view_perbaikanKomputer_view_detail_bynoform() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_perbaikanKomputer_view_detail_bynoform (?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
//                   noform,tipe,subtipe,Hnama,nama,keterangan,diperiksa,penggantian
                obj.put("noform", rs.getString(1).trim());
                obj.put("tipe", rs.getString(2).trim());
                obj.put("subtipe", rs.getString(3).trim());
                obj.put("Hnama", rs.getString(4).trim());
                obj.put("nama", rs.getString(5).trim());
                obj.put("keterangan", rs.getString(6).trim());
                obj.put("diperiksa", rs.getString(7).trim());
                obj.put("penggantian", rs.getString(8).trim());

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
    
    public String view_all_perbaikan_komputer_by_user_new() {
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_eform_viewall_perbaikankomputer_by_user_new (?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUser().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("NoForm",               rs.getString("NoForm").trim());
                obj.put("Probleam",             rs.getString("Probleam").trim());
                obj.put("tglInsert",            rs.getString("tglinsert").trim());
                obj.put("Status",               rs.getString("Status").trim());
                obj.put("Status2",              rs.getString("Status2").trim());
                obj.put("jabatan",              rs.getString("jabatan").trim());
                obj.put("orderby",              rs.getString("orderby").trim());
                obj.put("lokasiKerja",          rs.getString("lokasiKerja").trim());
                obj.put("unit",                 rs.getString("unit").trim());
                obj.put("departemen",           rs.getString("departemen").trim());
                obj.put("device",               rs.getString("device").trim());
                obj.put("tglTerima",            rs.getString("tglTerima"));
                obj.put("jamTerima",            rs.getObject("jamTerima"));
                obj.put("tglSelesai",           rs.getString("tglSelesai"));
                obj.put("jamSelesai",           rs.getObject("jamSelesai"));
                obj.put("tglfull",              rs.getDate("tglfull"));
                obj.put("kepalaUnit",           rs.getString("kepalaUnit").trim());
                obj.put("terimaOleh",           rs.getString("terimaOleh").trim());
                obj.put("diselesaikanOleh",     rs.getObject("diselesaikanOleh"));
                obj.put("ApproveUser2",         rs.getBoolean("ApproveUser2"));
                obj.put("mgrApprove",           rs.getBoolean("mgrApprove"));
                obj.put("tglApproveUser2",      rs.getDate("tglApproveUser2"));
                obj.put("tglApproveMgr",        rs.getDate("tglApproveMgr"));
                obj.put("prioritas",            rs.getObject("prioritas"));
                obj.put("usrApprove2",          rs.getObject("usrApprove2"));
                obj.put("usrInsert",            rs.getObject("usrInsert"));
                obj.put("userFullName",         rs.getObject("userName"));
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
    
    public JSONArray view_by_noform(String noform) {
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_view_by_noform (?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, noform);
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("NoForm",               rs.getObject("NoForm"));
                obj.put("Probleam",             rs.getObject("Probleam"));
                obj.put("tglInsert",            rs.getObject("tglinsert"));
                obj.put("lokasiKerja",          rs.getObject("lokasiKerja"));
                obj.put("device",               rs.getObject("device"));
                obj.put("tglTerima",            rs.getObject("tglTerima"));
                obj.put("tglSelesai",           rs.getObject("tglSelesai"));
                obj.put("jamTerima",            rs.getObject("jamTerima"));
                obj.put("jamSelesai",           rs.getObject("jamSelesai"));
                obj.put("kepalaUnit",           rs.getObject("kepalaUnit"));
                obj.put("terimaOleh",           rs.getObject("terimaOleh"));
                obj.put("diselesaikanOleh",     rs.getObject("diselesaikanOleh"));
                obj.put("ApproveUser2",         rs.getObject("ApproveUser2"));
                obj.put("tglApproveUser2",      rs.getObject("tglApproveUser2"));
                obj.put("mgrApprove",           rs.getObject("mgrApprove"));
                obj.put("tglApproveMgr",        rs.getObject("tglApproveMgr"));
                obj.put("prioritas",            rs.getObject("prioritas"));
                obj.put("usrApprove2",          rs.getObject("usrApprove2"));
                obj.put("unit",                 rs.getObject("unit"));
                obj.put("departemen",           rs.getObject("departemen"));
                obj.put("usrInsert",            rs.getObject("usrInsert"));
                obj.put("approveUser",          rs.getObject("approveUser"));
                obj.put("usrUpdate",            rs.getObject("usrUpdate"));
                obj.put("tglUpdatePetugas",     rs.getObject("tglUpdatePetugas"));
                obj.put("ptgsUpdate",           rs.getObject("ptgsUpdate"));
                obj.put("usrEdp",               rs.getObject("usrEdp"));
                obj.put("usrNotify",            rs.getObject("usrNotify"));
                
                obj.put("keterangan",           rs.getObject("keterangan"));
                obj.put("nama",                 rs.getObject("nama"));
                obj.put("tipe",                 rs.getObject("tipe"));
                obj.put("counter",              rs.getObject("counter"));
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
        return prDt;
    }
    
    public JSONArray view_all_tokens() {
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_view_all_token}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("counter",               rs.getObject("counter"));
                obj.put("npp",                       rs.getObject("npp"));
                obj.put("user_name",           rs.getObject("user_name"));
                obj.put("fcm_token",           rs.getObject("fcm_token"));
                obj.put("updated_at",         rs.getObject("updated_at"));
                obj.put("is_active",           rs.getObject("is_active"));
                obj.put("firebase_token", rs.getObject("firebase_token"));
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
        return prDt;
    }
    
    public String view_submitted_eform_byedp(String dept) 
    {
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL view_submitted_eform (?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, dept.trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("NoForm",               rs.getString("NoForm").trim());
                obj.put("Probleam",             rs.getString("Probleam").trim());
                obj.put("tglInsert",            rs.getString("tglinsert").trim());
                obj.put("Status",               rs.getString("Status").trim());
                obj.put("jabatan",              rs.getString("jabatan").trim());
                obj.put("orderby",              rs.getString("orderby").trim());
                obj.put("lokasiKerja",          rs.getString("lokasiKerja").trim());
                obj.put("unit",                 rs.getString("unit").trim());
                obj.put("departemen",           rs.getString("departemen").trim());
                obj.put("device",               rs.getString("device").trim());
                obj.put("tglTerima",            rs.getString("tglTerima"));
                obj.put("jamTerima",            rs.getObject("jamTerima"));
                obj.put("tglSelesai",           rs.getString("tglSelesai"));
                obj.put("jamSelesai",           rs.getObject("jamSelesai"));
                obj.put("tglfull",              rs.getDate("tglfull"));
                obj.put("kepalaUnit",           rs.getString("kepalaUnit").trim());
                obj.put("terimaOleh",           rs.getString("terimaOleh").trim());
                obj.put("diselesaikanOleh",     rs.getObject("diselesaikanOleh"));
                obj.put("ApproveUser2",         rs.getBoolean("ApproveUser2"));
                obj.put("mgrApprove",           rs.getBoolean("mgrApprove"));
                obj.put("tglApproveUser2",      rs.getDate("tglApproveUser2"));
                obj.put("tglApproveMgr",        rs.getDate("tglApproveMgr"));
                obj.put("prioritas",            rs.getObject("prioritas"));
                obj.put("usrApprove2",          rs.getObject("usrApprove2"));
                obj.put("usrInsert",            rs.getObject("usrInsert"));
                obj.put("usrNotify",            rs.getObject("usrNotify"));
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
    
    
    
    public JSONArray getTokenFirebase(String uname, String npp, String token) {
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
    
    public void insertFormPerbaikanKomputerByEdp() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_insertPerbaikanKomputerHD_toJapri_ByEDP (?,?,?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getLokasiKerja().trim());
            cStmt.setString(2, getDepartemen().trim());
            cStmt.setString(3, getUnit().trim());
            cStmt.setString(4, getKepalaUnit().trim());
            cStmt.setBoolean(5, isApproveUser());
            cStmt.setString(6, getProbleam().trim());
            cStmt.setString(7, getDevice().trim());
            cStmt.setString(8, getUser().trim());
            cStmt.setString(9, getAcceptedBy().trim());
            cStmt.setString(10, getDoneBy().trim());
            cStmt.setString(11, getPrioritas().trim());
            cStmt.setString(12, getIsDone().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP" 
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public void insertPetugasSimpanPerbaikanKomputerByEdp() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_perbaikanKomputer_PetugasSimpan_fromJapriByEDP (?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getUser().trim());
            cStmt.setString(3, getDevice().trim());
            cStmt.setString(4, getKeterangan().trim());
            cStmt.setBoolean(5, getIsChecked());
            cStmt.setBoolean(6, getIsChanged());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public void FormDiselesaikanByEDP() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL sp_eform_perbaikanKomputer_PetugasSelesai_fromJapriByEDP (?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getUser().trim());
            cStmt.setString(3, getDevice().trim());
            cStmt.setString(4, getKeterangan().trim());
            cStmt.setBoolean(5, getIsChecked());
            cStmt.setBoolean(6, getIsChanged());
            cStmt.setString(7, getPrioritas().trim());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
            
    public void simpanPerbaikanDT() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL simpanPerbaikanDT (?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getHnama().trim());
            cStmt.setString(3, getNama().trim());
            cStmt.setString(4, getKeterangan().trim());
            cStmt.setBoolean(5, getIsChecked());
            cStmt.setBoolean(6, getIsChanged());
            cStmt.setString(7, getUser().trim());
            cStmt.setString(8, getPrioritas().trim());
            cStmt.setString(9, getTipe().trim());
            cStmt.setString(10, getSubtipe().trim());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public void insertDataPerbaikan() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL insertDataPerbaikan (?,?,?,?,?, ?,?,?,?,?, ?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getTipe().trim());
            cStmt.setString(3, getSubtipe().trim());
            cStmt.setString(4, getHnama().trim());
            cStmt.setString(5, getNama().trim());
            cStmt.setString(6, getKeterangan().trim());
            cStmt.setBoolean(7, getIsChecked());
            cStmt.setBoolean(8, getIsChanged());
            cStmt.setString(9, getUser().trim());
            cStmt.setString(10, getKonfirmasi().trim());
            cStmt.setString(11, getPrioritas().trim());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public void updateDataPerbaikan() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL updateDataPerbaikan (?,?,?,?,?, ?,?,?,?,?, ?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getTipe().trim());
            cStmt.setString(3, getSubtipe().trim());
            cStmt.setString(4, getHnama().trim());
            cStmt.setString(5, getNama().trim());
            cStmt.setString(6, getKeterangan().trim());
            cStmt.setBoolean(7, getIsChecked());
            cStmt.setBoolean(8, getIsChanged());
            cStmt.setString(9, getUser().trim());
            cStmt.setString(10, getKonfirmasi().trim());
            cStmt.setString(11, getPrioritas().trim());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public void selesaiUpdateDataPerbaikan() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL selesaiUpdateDataPerbaikan (?,?,?,?,?, ?,?,?,?,?, ?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getTipe().trim());
            cStmt.setString(3, getSubtipe().trim());
            cStmt.setString(4, getHnama().trim());
            cStmt.setString(5, getNama().trim());
            cStmt.setString(6, getKeterangan().trim());
            cStmt.setBoolean(7, getIsChecked());
            cStmt.setBoolean(8, getIsChanged());
            cStmt.setString(9, getUser().trim());
            cStmt.setString(10, getKonfirmasi().trim());
            cStmt.setString(11, getPrioritas().trim());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public void selesaiInsertDataPerbaikan() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL selesaiInsertDataPerbaikan (?,?,?,?,?, ?,?,?,?,?, ?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getTipe().trim());
            cStmt.setString(3, getSubtipe().trim());
            cStmt.setString(4, getHnama().trim());
            cStmt.setString(5, getNama().trim());
            cStmt.setString(6, getKeterangan().trim());
            cStmt.setBoolean(7, getIsChecked());
            cStmt.setBoolean(8, getIsChanged());
            cStmt.setString(9, getUser().trim());
            cStmt.setString(10, getKonfirmasi().trim());
            cStmt.setString(11, getPrioritas().trim());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public void deleteDataPerbaikan() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL deleteDataPerbaikan (?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getTipe().trim());
            cStmt.setString(3, getSubtipe().trim());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public void FormDisimpanByEDP() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    //@noform , @user , @tipe, @subtipe,@nama, @Hnama , @keterangan , @diperiksab , @penggantian
                    "{CALL sp_eform_perbaikanKomputer_PetugasSimpanPerbaikan_fromJapriByEDP (?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.setString(2, getUser().trim());
            cStmt.setString(3, getDevice().trim());
            cStmt.setString(4, getKeterangan().trim());
            cStmt.setBoolean(5, getIsChecked());
            cStmt.setBoolean(6, getIsChanged());
            cStmt.setString(7, getPrioritas().trim());

            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public void InsertPetugasTerimaPerbaikanKomputerNew() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_PerbaikanKomnputer_InsertPetugasTerima_new (?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUser().trim());
            cStmt.setString(2, getNoForm().trim());
            cStmt.setString(3, getPrioritas().trim());
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public String view_last_insertedform() {
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_eform_view_lastinserted_perbaikankomputer_by_user_new }", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
//            cStmt.setString(1, eform);
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("NoForm",       rs.getString("NoForm").trim());
//                obj.put("Probleam",     rs.getString("Probleam").trim());
//                obj.put("tglInsert",    rs.getString("tglinsert").trim());
//                obj.put("Status",       rs.getString("Status").trim());
//                obj.put("jabatan",      rs.getString("jabatan").trim());
//                obj.put("orderby",      rs.getString("orderby").trim());
//                obj.put("lokasiKerja",  rs.getString("lokasiKerja").trim());
//                obj.put("device",       rs.getString("device").trim());
//                obj.put("tglTerima",    rs.getString("tglTerima").trim());
//                obj.put("jamTerima",    rs.getString("jamTerima").trim());
//                obj.put("tglSelesai",   rs.getString("tglSelesai").trim());
//                obj.put("jamSelesai",   rs.getString("jamSelesai").trim());
//                obj.put("tglfull",      rs.getString("tglfull").trim());
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
    
    public String select_bagian(){
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_eform_getbagian(?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNpp());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("namaBagian",       rs.getString("nmBagian").trim());
                obj.put("kdParent",     rs.getString("KdParent").trim());
                obj.put("kdBagian",    rs.getString("kdBagian").trim());
                obj.put("kdLevel",       rs.getString("kdlevel").trim());
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
    
    public String select_kanit(){
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();
        JSONObject obj;
        try {
            connDB tes  = new connDB();
            conn        = tes.getConnection();
            cStmt       = conn.prepareCall(
                    "{CALL sp_eform_getkanit(?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNpp());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("kanit",       rs.getString("nama1").trim());
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

    public void select_jumlahcontent(String noform) {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_perbaikanKomputer_jumlahcontent (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, (noform.trim()));

            rs = cStmt.executeQuery();
            rs = cStmt.getResultSet();
            if (rs.first()) {
                rs.beforeFirst();
                while (rs.next()) {
//                1. Nobukti	2. noreg	 3. content 	
                    setJumlahContent(rs.getString(1));
                }
            } else {
                setJumlahContent("");
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
    }
    
    public void insertFormPerbaikanKomputerByEdp2() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_insert2 (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getLokasiKerja().trim());
            cStmt.setString(2, getDepartemen().trim());
            cStmt.setString(3, getUnit().trim());
            cStmt.setString(4, getKepalaUnit().trim());
            cStmt.setBoolean(5, isApproveUser());
            cStmt.setString(6, getProbleam().trim());
            cStmt.setString(7, getDevice().trim());
            cStmt.setString(8, getUser().trim());
            cStmt.setString(9, getAcceptedBy().trim());
            cStmt.setString(10, getDoneBy().trim());
            cStmt.setString(11, getPrioritas().trim());
            cStmt.setString(12, getIsDone().trim());
            cStmt.setString(13, getIsPurposed().trim());
            cStmt.setString(14, getUsrEdp().trim());
            cStmt.setString(15, getUsrNotify().trim());
            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP" 
            cStmt.executeUpdate();
            cStmt.close();
            conn.close();
        } catch (SQLException ex) {
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
    }
    
    public JSONArray inserEformPK() {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
                
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_insert2 (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getLokasiKerja().trim());
            cStmt.setString(2, getDepartemen().trim());
            cStmt.setString(3, getUnit().trim());
            cStmt.setString(4, getKepalaUnit().trim());
            cStmt.setBoolean(5, isApproveUser());
            cStmt.setString(6, getProbleam().trim());
            cStmt.setString(7, getDevice().trim());
            cStmt.setString(8, getUser().trim());
            cStmt.setString(9, getAcceptedBy().trim());
            cStmt.setString(10, getDoneBy().trim());
            cStmt.setString(11, getPrioritas().trim());
            cStmt.setString(12, getIsDone().trim());
            cStmt.setString(13, getIsPurposed().trim());
            cStmt.setString(14, getUsrEdp().trim());
            cStmt.setString(15, getUsrNotify().trim());
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
   
    
    public JSONArray terimaUser(String uname, String eformId) {
        ResultSet rs = null;
        JSONArray result = new JSONArray();
        CallableStatement cStmt = null;

        try (Connection conn = new connDB().getConnection()) { // Try-with-resources
            cStmt = conn.prepareCall(
                    "{CALL sp_terimaUser (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            // Set parameter
            cStmt.setString(1, eformId);
            cStmt.setString(2, uname);

            // Execute query
            rs = cStmt.executeQuery();
            JSONObject obj;
            int cols = rs.getMetaData().getColumnCount();

            // Process result set
            while (rs.next()) {
                obj = new JSONObject();
                for (int i = 1; i <= cols; i++) {
                    String fieldname = rs.getMetaData().getColumnName(i).trim();
                    int dataType = rs.getMetaData().getColumnType(i);

                    // Map SQL types to JSON
                    if (dataType == Types.BIT || dataType == Types.BOOLEAN) {
                        obj.put(fieldname, rs.getBoolean(i));
                    } else if (dataType == Types.INTEGER) {
                        obj.put(fieldname, rs.getInt(i));
                    } else {
                        obj.put(fieldname, rs.getObject(i) != null ? rs.getString(i) : null);
                    }
                }
                result.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, "SQL Error: " + ex.getMessage(), ex);
        } catch (Exception ex) {
            Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, "Unexpected Error: " + ex.getMessage(), ex);
        } finally {
            // Close CallableStatement
            if (cStmt != null) {
                try {
                    cStmt.close();
                } catch (SQLException e) {
                    Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, "Error closing CallableStatement", e);
                }
            }
            // Close ResultSet
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    Logger.getLogger(presensi.class.getName()).log(Level.SEVERE, "Error closing ResultSet", e);
                }
            }
        }

        return result;
    }

    
    public JSONArray readAfterInsertPK() {
        ResultSet rs            = null;
        CallableStatement cStmt = null;
        Connection conn         = null;
        JSONArray prDt          = new JSONArray();

        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL view_by_noform(?)}", 
                    ResultSet.TYPE_SCROLL_SENSITIVE, 
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();

            // Get metadata to fetch column names and types dynamically
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Process each row in the result set
            while (rs.next()) {
                JSONObject obj = new JSONObject();
                for (int i = 1; i <= columnCount; i++) { // Columns are 1-indexed in JDBC
                    String columnName = metaData.getColumnName(i);
                    Object value = rs.getObject(i); // Fetch column value as Object
                    // Include null values explicitly in the JSON
                    obj.put(columnName, value != null ? value : "");
                }
                prDt.add(obj);
            }

        } catch (SQLException ex) {
            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (cStmt != null) cStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return prDt; // Return the JSONArray containing all rows
    }

    
    
//<editor-fold defaultstate="collapsed" desc="Notifikasi Perbaikan Komputer">

//    public String notif_Perbaikan_komputer() {
//        ResultSet rs = null;
//        CallableStatement cStmt = null;
//        Connection conn = null;
//        JSONArray prDt = new JSONArray();
//        JSONObject obj;
//        try {
//            ConnDB tes = new ConnDB();
//            conn = tes.getConnection();
//            cStmt = conn.prepareCall(
//                    "{CALL sp_eform_notification_perbaikankomputer  (?)}",
//                    ResultSet.TYPE_SCROLL_SENSITIVE,
//                    ResultSet.CONCUR_UPDATABLE);
//            cStmt.setString(1, getUser().trim());
//            //default insert status Edit diisi "0", Lihat SP "spwecare_insertSOAP"
//            cStmt.executeQuery();
//            rs = cStmt.getResultSet();
//
//            while (rs.next()) {
//                obj = new JSONObject();
//                obj.put(rs.getString(1).trim(), rs.getString(2));
//                prDt.add(obj);
//            }
//          
//            cStmt.close();
//            conn.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(formProgramData.class.getName()).log(Level.SEVERE, null, ex);
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
//        return prDt.toJSONString();
//    }
    public String notif_Perbaikan_komputer() {
        String viewallnojo = "";
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_notification_perbaikankomputer  (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUser().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            viewallnojo = "{";
            while (rs.next()) {
                if (rs.isLast()) {
                    viewallnojo = viewallnojo + "\"" + rs.getString(1).trim() + "\":\"" + rs.getString(2).trim() + "\"";
                } else {
                    viewallnojo = viewallnojo + "\"" + rs.getString(1).trim() + "\":\"" + rs.getString(2).trim() + "\",";
                }
            }
            viewallnojo = viewallnojo + "}";
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
        return viewallnojo;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Get/Set">

    public String getNoForm() {
        return noForm;
    }

    public void setNoForm(String noForm) {
        this.noForm = noForm;
    }

    /**
     * @return the Unit
     */
    public String getUnit() {
        return Unit;
    }

    /**
     * @param Unit the Unit to set
     */
    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    /**
     * @return the KepalaUnit
     */
    public String getKepalaUnit() {
        return KepalaUnit;
    }

    /**
     * @param KepalaUnit the KepalaUnit to set
     */
    public void setKepalaUnit(String KepalaUnit) {
        this.KepalaUnit = KepalaUnit;
    }

    /**
     * @return the ApproveUser
     */
    public boolean isApproveUser() {
        return ApproveUser;
    }

    /**
     * @param ApproveUser the ApproveUser to set
     */
    public void setApproveUser(boolean ApproveUser) {
        this.ApproveUser = ApproveUser;
    }

    /**
     * @return the Probleam
     */
    public String getProbleam() {
        return Probleam;
    }

    /**
     * @param Probleam the Probleam to set
     */
    public void setProbleam(String Probleam) {
        this.Probleam = Probleam;
    }

    /**
     * @return the device
     */
    public String getDevice() {
        return device;
    }

    /**
     * @param device the device to set
     */
    public void setDevice(String device) {
        this.device = device;
    }

//</editor-fold>
    /**
     * @return the User
     */
    public String getUser() {
        return User;
    }

    /**
     * @param User the User to set
     */
    public void setUser(String User) {
        this.User = User;
    }
   
    public String getNpp() {
        return Npp;
    }

    public void setNpp(String Npp) {
        this.Npp = Npp;
    }
    
    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String done) {
        this.isDone = done;
    }
    
    public String getIsPurposed() {
        return isPurposed;
    }

    public void setIsPurposed(String purposed) {
        this.isPurposed = purposed;
    }
    
    public String getTipe() {
        return tipe;
    }
    
    public void setTipe(String tipe) {
        this.tipe = tipe;
    }
    
    public String getTipeLain() {
        return tipeLain;
    }
    
    public void setTipeLain(String tipe) {
        this.tipeLain = tipe;
    }
    
    public String getSubtipeLain() {
        return subtipeLain;
    }
    
    public void setSubtipeLain(String subtipe) {
        this.subtipeLain = subtipe;
    }
    
    public String getKonfirmasi() {
        return konfirmasi;
    }
    
    public void setKonfirmasi(String confirm) {
        this.konfirmasi = confirm;
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

    /**
     * @return the keterangan
     */
    public String getKeterangan() {
        return keterangan;
    }

    /**
     * @param keterangan the keterangan to set
     */
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    /**
     * @return the validasi
     */
    public boolean isValidasi() {
        return validasi;
    }

    /**
     * @param validasi the validasi to set
     */
    public void setValidasi(boolean validasi) {
        this.validasi = validasi;
    }

    /**
     * @return the subtipe
     */
    public String getSubtipe() {
        return subtipe;
    }

    /**
     * @param subtipe the subtipe to set
     */
    public void setSubtipe(String subtipe) {
        this.subtipe = subtipe;
    }

    /**
     * @return the hnama
     */
    public String getHnama() {
        return hnama;
    }

    /**
     * @param hnama the hnama to set
     */
    public void setHnama(String hnama) {
        this.hnama = hnama;
    }

    /**
     * @return the periksa
     */
    public boolean isPeriksa() {
        return periksa;
    }

    /**
     * @param periksa the periksa to set
     */
    public void setPeriksa(boolean periksa) {
        this.periksa = periksa;
    }

    /**
     * @return the ganti
     */
    public boolean isGanti() {
        return ganti;
    }

    /**
     * @param ganti the ganti to set
     */
    public void setGanti(boolean ganti) {
        this.ganti = ganti;
    }

    /**
     * @return the ApproveManger
     */
    public boolean isApproveManger() {
        return ApproveManger;
    }

    /**
     * @param ApproveManger the ApproveManger to set
     */
    public void setApproveManger(boolean ApproveManger) {
        this.ApproveManger = ApproveManger;
    }

    /**
     * @return the ApproveUser2
     */
    public boolean isApproveUser2() {
        return ApproveUser2;
    }

    /**
     * @param ApproveUser2 the ApproveUser2 to set
     */
    public void setApproveUser2(boolean ApproveUser2) {
        this.ApproveUser2 = ApproveUser2;
    }

    /**
     * @return the Departemen
     */
    public String getDepartemen() {
        return Departemen;
    }

    /**
     * @param Departemen the Departemen to set
     */
    public void setDepartemen(String Departemen) {
        this.Departemen = Departemen;
    }

    /**
     * @return the lokasiKerja
     */
    public String getLokasiKerja() {
        return lokasiKerja;
    }

    /**
     * @param lokasiKerja the lokasiKerja to set
     */
    public void setLokasiKerja(String lokasiKerja) {
        this.lokasiKerja = lokasiKerja;
    }

    /**
     * @return the prioritas
     */
    public String getPrioritas() {
        return prioritas;
    }

    /**
     * @param prioritas the prioritas to set
     */
    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }
    
    public void setIsChanged(Boolean task) {
        this.isChanged = task;
    }

    
    public Boolean getIsChanged() {
        return isChanged;
    }
    
    public void setIsChecked(Boolean task) {
        this.isChecked = task;
    }

    
    public Boolean getIsChecked() {
        return isChecked;
    }
    
    public void setDoneBy(String user) {
        this.doneBy = user;
    }
    
    public String getDoneBy() {
        return doneBy;
    }
    
    public void setUsrEdp(String user) {
        this.usrEdp = user;
    }
    
    public String getUsrEdp() {
        return usrEdp;
    }
    
    public void setUsrNotify(String user) {
        this.usrNotify = user;
    }
    
    public String getUsrNotify() {
        return usrNotify;
    }
    
    public String getTglTerima() {
        return tglTerima;
    }
    
    public void setTglTerima(String tgl) {
        this.tglTerima = tgl;
    }
    
    public String getTglSelesai() {
        return tglSelesai;
    }
    
    public void setTglSelesai(String tgl) {
        this.tglSelesai = tgl;
    }
    
    public String getJamSelesai() {
        return jamSelesai;
    }
    
    public void setJamSelesai(String jam) {
        this.jamSelesai = jam;
    }
    
    public String getJamTerima() {
        return jamTerima;
    }
    
    public void setJamTerima(String jam) {
        this.jamTerima = jam;
    }
    
     public String getAcceptedBy() {
        return acceptedBy;
    }
    
    public void setAcceptedBy(String user) {
        this.acceptedBy = user;
    }
    

    
    

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the jumlah
     */
    public String getJumlah() {
        return jumlah;
    }

    /**
     * @param jumlah the jumlah to set
     */
    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    /**
     * @return the jumlahContent
     */
    public String getJumlahContent() {
        return jumlahContent;
    }

    /**
     * @param jumlahContent the jumlahContent to set
     */
    public void setJumlahContent(String jumlahContent) {
        this.jumlahContent = jumlahContent;
    }

}
