/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.acara;
import model.helper;
import model.keyFinder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fadhil
 */
public class Acara extends HttpServlet {

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
        JSONObject obj = new JSONObject();
        JSONArray result = new JSONArray();
        Map param = request.getParameterMap();

        acara m_acara = new acara();

        try {

            if (param.containsKey("nopegawai") && param.containsKey("tanggal")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                String tanggal = request.getParameter("tanggal").trim();
                result = m_acara.openListAcara(nopegawai, tanggal);
            } else if (param.containsKey("counterUndangan") && param.containsKey("nopegawai")) {
                Integer counterUndangan = Integer.parseInt(request.getParameter("counterUndangan").trim());
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_acara.selectOneUndangan(counterUndangan, nopegawai);

                if (result.size() > 0) {
//                    JSONArray arr = new JSONArray();
                    int port = request.getLocalPort();
                    String addr = request.getServerName();

                    String fotoMasuk = getValJson(result, "urlDatang");
                    String imageUrlMasuk = "";
                    if (!fotoMasuk.trim().equals("")) {
                        imageUrlMasuk = helper.getImageUrl(port, addr, nopegawai, fotoMasuk);
                    }

                    String fotoPulang = getValJson(result, "urlPulang");
                    String imageUrlPulang = "";
                    if (!fotoPulang.trim().equals("")) {
                        imageUrlPulang = helper.getImageUrl(port, addr, nopegawai, fotoPulang);
                    }

                    obj.put("imageUrlMasuk", imageUrlMasuk);
                    obj.put("imageUrlPulang", imageUrlPulang);
                    result.add(obj);
                }
            }else if (param.containsKey("npp") && param.containsKey("bln")&& param.containsKey("thn")) {
                String npp = request.getParameter("npp").trim();
                String bln = request.getParameter("bln").trim();
                String thn = request.getParameter("thn").trim();
                result = m_acara.openListAcaraBulanan(npp, bln, thn);
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
        JSONArray result = new JSONArray();
        Map param = request.getParameterMap();

        acara m_acara = new acara();

        try {

            if (param.containsKey("nopegawai") && param.containsKey("latitude") && param.containsKey("longitude")
                    && param.containsKey("counterUndangan") && param.containsKey("keterangan")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                Float latitude = Float.parseFloat(request.getParameter("latitude"));
                Float longitude = Float.parseFloat(request.getParameter("longitude"));
                Integer counterUndangan = Integer.parseInt(request.getParameter("counterUndangan"));
                String keterangan = request.getParameter("keterangan").trim();

                JSONArray arr = m_acara.insertAcara(nopegawai, latitude, longitude, counterUndangan, keterangan);
                if (arr.size() > 0) {
                    String counterAcr = getValJson(arr, "counterAcr");
                    result = m_acara.updateAcara(counterAcr, nopegawai);
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

    public String getValJson(JSONArray JsonData, String Key) {
        String result = "";
        JSONParser parser = new JSONParser();
        keyFinder finder = new keyFinder();
        finder.setMatchKey(Key);
        while (!finder.isEnd()) {
            try {
                parser.parse(JsonData.toJSONString(), finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    result = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(Acara.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!result.equals("")) {
            return result;
        } else {
            return "";
        }
    }
}
