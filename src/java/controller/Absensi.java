/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.absensi;
import model.helper;
import model.stdfield;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fadhil
 */
public class Absensi extends HttpServlet {

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

        absensi m_absensi = new absensi();
        stdfield m_stdfield = new stdfield();

        try {
            if (param.containsKey("nopegawai") && param.containsKey("initialDate") && param.containsKey("endDate")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                String initialDate = request.getParameter("initialDate").trim();
                String endDate = request.getParameter("endDate").trim();
                result = m_absensi.selectHistoryPresensiPegawai(nopegawai, initialDate, endDate);
            } else if (param.containsKey("counterAbs") && param.containsKey("nopegawai")) {
                String counterAbs = request.getParameter("counterAbs").trim();
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_absensi.selectOneAbsensi(counterAbs, nopegawai);
            } else if (param.containsKey("kdfield")) {
                String kdfield = request.getParameter("kdfield").trim();
                result = m_stdfield.selectStdField(kdfield);
            } else if (param.containsKey("cuti") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_absensi.selectOneCuti(nopegawai);
            } else if (param.containsKey("tglcuti") && param.containsKey("npp")) {
                String nopegawai = request.getParameter("npp").trim();
                result = m_absensi.selectTglCuti(nopegawai);
            } else if (param.containsKey("tglmerah")) {
                result = m_absensi.viewTglMerah();
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
        JSONObject param = getJSONBody(request);
        absensi m_absensi = new absensi();

        try {
            if (param.containsKey("tanggal") && param.containsKey("nopegawai") && param.containsKey("jnscuti") && param.containsKey("khusus")) {
                String tanggal = param.get("tanggal").toString();
                String nopegawai = param.get("nopegawai").toString();
                String jnscuti = param.get("jnscuti").toString();
                String khusus = param.get("khusus").toString();
                result = m_absensi.insertAbsensi(nopegawai, tanggal, jnscuti, khusus);
            } else if (param.containsKey("counterAbs") && param.containsKey("nopegawai") && param.containsKey("update")) {
                String counter = param.get("counterAbs").toString();
                String nopegawai = param.get("nopegawai").toString();
                if (m_absensi.updateValidAbsensi(counter, nopegawai)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            } else if (param.containsKey("counterAbs") && param.containsKey("nopegawai") && param.containsKey("delete")) {
                String counter = param.get("counterAbs").toString();
                String nopegawai = param.get("nopegawai").toString();
                if (m_absensi.deleteAbsensi(counter, nopegawai)) {
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

    private JSONObject getJSONBody(HttpServletRequest req) {
        JSONObject result = new JSONObject();
        String str, bodyString = "";
        try {
            BufferedReader br = req.getReader();
            while ((str = br.readLine()) != null) {
                bodyString += str;
            }
            result = (JSONObject) (new JSONParser()).parse(bodyString);
        } catch (IOException | ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
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

