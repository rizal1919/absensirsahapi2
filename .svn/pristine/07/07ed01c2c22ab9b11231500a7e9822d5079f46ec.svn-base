/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

//<editor-fold defaultstate="collapsed" desc="import">
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.connDB;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//</editor-fold>

/**
 *
 * @author endra
 */
public class formProgramData {

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
    //</editor-fold>

    public formProgramData() {
    }

//<editor-fold defaultstate="collapsed" desc="Insert Baru">
    public void insertFormProgramData() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_insert_baru (?,?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getKategoriBaru().trim());
            cStmt.setString(2, getJenisProgram().trim());
            cStmt.setString(3, getNamaProgram().trim());
            cStmt.setString(4, getKetNamaProgram().trim());
            cStmt.setString(5, getKeteranganForm().trim());
            cStmt.setString(6, getUserMinta().trim());
            cStmt.setString(7, getJabatan().trim());
            cStmt.setString(8, getKdlock().trim());
            cStmt.setString(9, getKdUnit().trim());
            cStmt.setString(10, getKdDept().trim());
            cStmt.setString(11, getProgrammer().trim());
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Edit Form">
    public void editFormProgramData() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_update_form (?,?,?,?,?,?,?,?,?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getKategoriBaru().trim());
            cStmt.setString(2, getJenisProgram().trim());
            cStmt.setString(3, getNamaProgram().trim());
            cStmt.setString(4, getKetNamaProgram().trim());
            cStmt.setString(5, getKeteranganForm().trim());
            cStmt.setString(6, getUserMinta().trim());
            cStmt.setString(7, getJabatan().trim());
            cStmt.setString(8, getNoForm().trim());
            cStmt.setString(9, getKdlock().trim());
            cStmt.setString(10, getKdUnit().trim());
            cStmt.setString(11, getKdDept().trim());
            cStmt.setString(12, getProgrammer().trim());
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Terima program data">
    public void terima_program_data() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_terima (?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getUserMinta().trim());
            cStmt.setString(3, getPrioritas().trim());
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

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Prioritas">
    public void ubah_prioritas_program_data() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_update_prioritas (?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getPrioritas().trim());
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

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Batal Form">
    public void batal_program_data() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_batal_form (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Tindak Lanjut EDP">
    public void tindak_lanjut_program_data() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_tindak_lanjut (?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getKeteranganTindakLanjut());
            cStmt.setString(3, getUserMinta().trim());
            //cStmt.setString(4, getDokterForm, noForm);
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Konfirm User">
    public void konfirmasi_program_data() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_konfirmasi (?,?)}",
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Selesai Program Data">
    public void selesai_program_data() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_selesai (?,?)}",
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

//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Approve Program Data">
    public void approve_program_data() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_approve (?,?,?,?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getUserMinta().trim());
            cStmt.setString(3, getApproveLevel().trim());
            cStmt.setString(4, getCatatan().trim());
            cStmt.setString(5, getProgrammer().trim());
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

    public void tolak_program_data() {
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_tolak (?,?,?,?)}",
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Notifikasi Program data">
    public String notif_program_data() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_view_all_notif (?,?)}",
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
//            System.out.println(prDt.toJSONString());
            cStmt.close();

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

    public String view_program_data_after_approve() {
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
                    "{CALL sp_eform_programData_view_form_approve (?,?,?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getNoForm());
            cStmt.setString(2, getUserMinta().trim());
            cStmt.setString(3, getApproveLevel().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                //COUNTERs	noForm	kategoriBaru	katergoriPerubahan	isProgram	isData	keteranganForm	
                //keteranganFormEdp	userMinta	tglMinta	userApproval1	tglApproval1	
                //userApproval2	tglApproval2	userApproval3	tglApproval3	userApproval4	tglApproval4

                obj.put("noForm", rs.getString(1));
                obj.put("kategoriBaru", rs.getString(2));
                obj.put("JenisProgram", rs.getString(3));
                obj.put("NamaProgram", rs.getString(4));
                obj.put("ketNamaProgram", rs.getString(5));
                obj.put("keteranganForm", rs.getString(6));
                obj.put("userMinta", rs.getString(7));
                obj.put("tglMinta", rs.getString(8));
                obj.put("userApproval1", rs.getString(9));
                obj.put("tglApproval1", rs.getString(10));
                obj.put("userApproval2", rs.getString(11));
                obj.put("tglApproval2", rs.getString(12));
                obj.put("userApproval3", rs.getString(13));
                obj.put("tglApproval3", rs.getString(14));
                obj.put("userApproval4", rs.getString(15));
                obj.put("tglApproval4", rs.getString(16));
                obj.put("tglTerima", rs.getString(17));
                obj.put("userTerima", rs.getString(18));
                obj.put("keteranganFormEdp", rs.getString(19));
                obj.put("tglSelesai", rs.getString(20));
                obj.put("userSelesai", rs.getString(21));
                obj.put("tglKonfirmasi", rs.getString(22));
                obj.put("userKonfirmasi", rs.getString(23));
                obj.put("proses", rs.getString(24));
                // Tambahan
                obj.put("kdlock", rs.getString(25));
                obj.put("kdUnit", rs.getString(26));
                obj.put("kdDept", rs.getString(27));
                obj.put("nmLock", rs.getString(28));
                obj.put("nmUnit", rs.getString(29));
                obj.put("nmDept", rs.getString(30));
                obj.put("prioritas", rs.getString(31));
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
                obj.put("programmer", rs.getString(42));
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

    public String view_program_data_after_insert() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_view_form (?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUserMinta().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                //COUNTERs	noForm	kategoriBaru	katergoriPerubahan	isProgram	isData	keteranganForm	
                //keteranganFormEdp	userMinta	tglMinta	userApproval1	tglApproval1	
                //userApproval2	tglApproval2	userApproval3	tglApproval3	userApproval4	tglApproval4
                obj.put("noForm", rs.getString(1));
                obj.put("kategoriBaru", rs.getString(2));
                obj.put("JenisProgram", rs.getString(3));
                obj.put("NamaProgram", rs.getString(4));
                obj.put("ketNamaProgram", rs.getString(5));
                obj.put("keteranganForm", rs.getString(6));
                obj.put("userMinta", rs.getString(7));
                obj.put("tglMinta", rs.getString(8));
                obj.put("userApproval1", rs.getString(9));
                obj.put("tglApproval1", rs.getString(10));
                obj.put("userApproval2", rs.getString(11));
                obj.put("tglApproval2", rs.getString(12));
                obj.put("userApproval3", rs.getString(13));
                obj.put("tglApproval3", rs.getString(14));
                obj.put("userApproval4", rs.getString(15));
                obj.put("tglApproval4", rs.getString(16));
                obj.put("tglTerima", rs.getString(17));
                obj.put("userTerima", rs.getString(18));
                obj.put("keteranganFormEdp", rs.getString(19));
                obj.put("tglSelesai", rs.getString(20));
                obj.put("userSelesai", rs.getString(21));
                obj.put("tglKonfirmasi", rs.getString(22));
                obj.put("userKonfirmasi", rs.getString(23));
                obj.put("proses", rs.getString(24));
                // Tambahan
                obj.put("kdlock", rs.getString(25));
                obj.put("kdUnit", rs.getString(26));
                obj.put("kdDept", rs.getString(27));
                obj.put("nmLock", rs.getString(28));
                obj.put("nmUnit", rs.getString(29));
                obj.put("nmDept", rs.getString(30));
                obj.put("prioritas", rs.getString(31));
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
                obj.put("programmer", rs.getString(50));
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

    public String view_all_program_data_by_user() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_view_all_program_data_by_user (?,?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUserMinta().trim());
            cStmt.setString(2, getJabatan().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();

                obj.put("noForm", rs.getString(1));
                obj.put("kategoriBaru", rs.getString(2));
                obj.put("JenisProgram", rs.getString(3));
                obj.put("NamaProgram", rs.getString(4));
                obj.put("ketNamaProgram", rs.getString(5));
                obj.put("keteranganForm", rs.getString(6));
                obj.put("userMinta", rs.getString(7));
                obj.put("tglMinta", rs.getString(8));
                obj.put("userApproval1", rs.getString(9));
                obj.put("tglApproval1", rs.getString(10));
                obj.put("userApproval2", rs.getString(11));
                obj.put("tglApproval2", rs.getString(12));
                obj.put("userApproval3", rs.getString(13));
                obj.put("tglApproval3", rs.getString(14));
                obj.put("userApproval4", rs.getString(15));
                obj.put("tglApproval4", rs.getString(16));
                obj.put("tglTerima", rs.getString(17));
                obj.put("userTerima", rs.getString(18));
                obj.put("keteranganFormEdp", rs.getString(19));
                obj.put("tglSelesai", rs.getString(20));
                obj.put("userSelesai", rs.getString(21));
                obj.put("tglKonfirmasi", rs.getString(22));
                obj.put("userKonfirmasi", rs.getString(23));
                obj.put("proses", rs.getString(24));
                // Tambahan
                obj.put("kdlock", rs.getString(25));
                obj.put("kdUnit", rs.getString(26));
                obj.put("kdDept", rs.getString(27));
                obj.put("nmLock", rs.getString(28));
                obj.put("nmUnit", rs.getString(29));
                obj.put("nmDept", rs.getString(30));
                obj.put("prioritas", rs.getString(31));
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
                obj.put("programmer", rs.getString(10));
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

    public String view_all_eform_by_user() {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray prDt = new JSONArray();
        JSONObject obj;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sp_eform_programData_view_all_eform_by_user (?,?)}", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, getUserMinta().trim());
            cStmt.setString(2, getJabatan().trim());
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                obj = new JSONObject();
                obj.put("noForm", rs.getString(1));
                obj.put("posisi", rs.getString(2));
                obj.put("proses", rs.getString(3));
                obj.put("form", rs.getString(4));
                //tambahan
                obj.put("programmer",rs.getString(5));
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

}
