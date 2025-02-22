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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import model.modules.Apps;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * @author endra 1.
 * @author rico
 */
public class Global {

    private String kdfield, kduser, nmkduser, keterangan;
    private boolean aktif;

    public Global() {

    }

    public String getToday() {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd", new Locale("ID", "ID"));
        return sdfDay.format(new Date());
    }

    public String getYYYYmm() {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMM", new Locale("ID", "ID"));
        return sdfDay.format(new Date());
    }

    public String getFormatedDate(String Date) {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd", new Locale("ID", "ID"));
        return sdfDay.format(new Date(Date));
    }

    public String getBeforeToday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMMdd", new Locale("ID", "ID"));
        return sdfDay.format(cal.getTime());
    }

    public String getLastMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH, -1);
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyyMM", new Locale("ID", "ID"));
        return sdfDay.format(cal.getTime());
    }

    public boolean getOpenLewatHari() {
        boolean Open = false;
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Context initCtx;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL spwecare_view_open_lewat_hari }",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            while (rs.next()) {
                if (rs.getString(1).equals("1")) {
                    Open = true;
                }
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
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

        return Open;
    }

    public String viewListEform(String userid) {
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        JSONArray arrApps = new JSONArray();
        JSONObject objApps;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL listEform (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, userid);
            rs = cStmt.executeQuery();
            while (rs.next()) {
                objApps = new JSONObject();
                objApps.put("modulID", rs.getString(1).trim());
                arrApps.add(objApps);
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Apps.class.getName()).log(Level.SEVERE, null, ex);
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
        return arrApps.toJSONString();
    }

    public String getKdfield() {
        return kdfield;
    }

    public void setKdfield(String kdfield) {
        this.kdfield = kdfield;
    }

    public String getKduser() {
        return kduser;
    }

    public void setKduser(String kduser) {
        this.kduser = kduser;
    }

    public String getNmkduser() {
        return nmkduser;
    }

    public void setNmkduser(String nmkduser) {
        this.nmkduser = nmkduser;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }

    public Global[] viewkodefield(String kdField) {
        Global kodefieldview[] = null;
        ResultSet rs = null;
        CallableStatement cStmt = null;
        Connection conn = null;
        try {
            connDB tes = new connDB();
            conn = tes.getConnection();
            cStmt = conn.prepareCall(
                    "{CALL sprs_StdField_Detil_SelectByKdfield (?)}",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            cStmt.setString(1, kdField);
            cStmt.executeQuery();
            rs = cStmt.getResultSet();
            int i = 0;
            rs.last();
            kodefieldview = new Global[rs.getRow()];
            rs.beforeFirst();
            while (rs.next()) {
                kodefieldview[i] = new Global();
                kodefieldview[i].setKdfield(rs.getString(1));
                kodefieldview[i].setKduser(rs.getString(2));
                kodefieldview[i].setNmkduser(rs.getString(3));
                kodefieldview[i].setKeterangan(rs.getString(4));
                kodefieldview[i].setAktif(rs.getBoolean(5));
                i++;
            }
            cStmt.close();
            rs.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Global.class.getName()).log(Level.SEVERE, null, ex);
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
        return kodefieldview;
    }

    public static void main(String[] args) throws SQLException {
        Global tes = new Global();

//        Global temp[] = tes.viewkodefield("SHIFT");
//        for (int i = 0; i < temp.length; i++) {
//                    + temp[i].getKduser() + " "
//                    + temp[i].getNmkduser() + " "
//                    + temp[i].getKeterangan() + " "
//                    + temp[i].aktif
//            );
//        }
    }
}
