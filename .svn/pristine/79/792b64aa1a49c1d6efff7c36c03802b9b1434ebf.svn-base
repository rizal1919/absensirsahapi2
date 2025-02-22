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
import model.helper;
import model.pengajuan;
import model.stdfield;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fadhil
 */
public class Pengajuan extends HttpServlet {

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

        pengajuan m_pengajuan = new pengajuan();
        stdfield m_stdfield = new stdfield();

        try {
            if (param.containsKey("listpengajuan") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_pengajuan.selectAllPengajuan(nopegawai);
            } else if (param.containsKey("selectone") && param.containsKey("counterPengajuan")) {
                String counterPengajuan = request.getParameter("counterPengajuan").trim();
                result = m_pengajuan.selectOnePengajuan(counterPengajuan);
            } else if (param.containsKey("atasan") ) {
                result = m_pengajuan.selectAllAtasan();
            } else if (param.containsKey("kdfield")) {
                String kdfield = request.getParameter("kdfield").trim();
                result = m_stdfield.selectStdField(kdfield);
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

        pengajuan m_pengajuan = new pengajuan();

        try {
            if (param.containsKey("insertPengajuan")) {
                String jnsPengajuan = param.get("jnsPengajuan").toString().trim();
                String judulPengajuan = param.get("judulPengajuan").toString().trim();
                String keteranganPengajuan = param.get("keteranganPengajuan").toString().trim();
                String nopegawai = param.get("nopegawai").toString().trim();
                String nppatasan = param.get("nppatasan").toString().trim();
                if (m_pengajuan.insertPengajuan(jnsPengajuan, judulPengajuan, keteranganPengajuan, nopegawai, nppatasan)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            } else if (param.containsKey("updatePengajuan")) {
                String counterPengajuan = param.get("counterPengajuan").toString().trim();
                String statusPengajuan = param.get("statusPengajuan").toString().trim();
                if (m_pengajuan.updatePengajuan(counterPengajuan, statusPengajuan)) {
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

}
