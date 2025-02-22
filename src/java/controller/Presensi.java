/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.presensi;
import model.helper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author fadhil
 */
public class Presensi extends HttpServlet {

    //<editor-fold defaultstate="collapsed" desc="GET">
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

        PrintWriter out = response.getWriter();
        JSONArray result = new JSONArray();
        Map param = request.getParameterMap();

        presensi m_presensi = new presensi();
        try {

            if (param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_presensi.selectPresensiMasukByNpp(nopegawai);
            } else if (param.containsKey("counterPasangan") && param.containsKey("npp")) {
                String counterPasangan = request.getParameter("counterPasangan").trim();
                String nopegawai = request.getParameter("npp").trim();
                result = m_presensi.selectPresensiJadwalDinas(counterPasangan, nopegawai);
            }

        } catch (Exception ex) {
            result = new JSONArray();
            result.add(helper.setErrorMessage(ex.getMessage()));
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        out.write(result.toJSONString());
        System.out.println(result.toJSONString());
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="POST">
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

        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        JSONArray result = new JSONArray();
        Map param = request.getParameterMap();

        presensi m_presensi = new presensi();
        try {

            if (param.containsKey("counterAtt") && param.containsKey("nopegawai")
                    && param.containsKey("masuk") && param.containsKey("pulang")
                    && param.containsKey("lembur") && param.containsKey("keterangan")
                    && param.containsKey("latitude") && param.containsKey("longitude")) {
                String counter = request.getParameter("counterAtt").trim();
                String nopegawai = request.getParameter("nopegawai").trim();
                Boolean masuk = StringParseBool(request.getParameter("masuk"));
                Boolean pulang = StringParseBool(request.getParameter("pulang"));
                Boolean lembur = StringParseBool(request.getParameter("lembur"));
                String keterangan = request.getParameter("keterangan").trim();
                Float latitude = Float.parseFloat(request.getParameter("latitude"));
                Float longitude = Float.parseFloat(request.getParameter("longitude"));
                if (masuk) {
                    result = m_presensi.insertPresensiMasuk(nopegawai, masuk, pulang, lembur, keterangan, latitude, longitude);
                } else {
                    result = m_presensi.insertPresensiPulang(counter, nopegawai, masuk, pulang, lembur, keterangan, latitude, longitude);
                }
            } else if (param.containsKey("counterAtt") && param.containsKey("nopegawai") && param.containsKey("update")) {
                String counter = request.getParameter("counterAtt").trim();
                String nopegawai = request.getParameter("nopegawai").trim();
                if (m_presensi.updateValidPresensi(counter, nopegawai)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            } else if (param.containsKey("counterAtt") && param.containsKey("nik") && param.containsKey("lembur") && param.containsKey("keterangan")) {
                String counterAtt = request.getParameter("counterAtt").trim();
                String nopegawai = request.getParameter("nik").trim();
                Boolean lembur = StringParseBool(request.getParameter("lembur").trim());
                String keterangan = request.getParameter("keterangan").trim();
                if (m_presensi.updateLemburPresensi(counterAtt, nopegawai, lembur, keterangan)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            } else if (param.containsKey("counterAtt") && param.containsKey("nopegawai") && param.containsKey("delete")) {
                String counter = request.getParameter("counterAtt").trim();
                String nopegawai = request.getParameter("nopegawai").trim();
                if (m_presensi.deletePresensi(counter, nopegawai)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            }

        } catch (Exception ex) {
            result = new JSONArray();
            result.add(helper.setErrorMessage(ex.getMessage()));
            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        out.write(result.toJSONString());
        System.out.println(result.toJSONString());
    }
//</editor-fold>

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

    boolean StringParseBool(String isNumberBoolean) {
        boolean retVal;
        if (isNumberBoolean.trim().equalsIgnoreCase("true") || isNumberBoolean.trim().equalsIgnoreCase("false")) {
            retVal = Boolean.parseBoolean(isNumberBoolean);
        } else {
            retVal = "1".equals(isNumberBoolean.trim());
        }
        return retVal;
    }

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                strNumber = strNumber.substring(0, strNumber.indexOf('.'));
                strNumber = strNumber.replaceAll("[^0-9]", "");
                return Double.parseDouble(strNumber);
            } catch (NumberFormatException e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }

}
