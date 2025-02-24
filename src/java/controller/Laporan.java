/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.helper;
import model.keyFinder;
import model.laporan;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fadhil
 */
public class Laporan extends HttpServlet {

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
        laporan m_laporan = new laporan();

        File upOne = helper.getUpOne();
        String UPLOAD_DIRECTORY = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_foto";

        try {
            if (param.containsKey("nopegawai") && param.containsKey("tanggal")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                String tanggal = request.getParameter("tanggal").trim(); //"20200205";
                result = m_laporan.openLaporanPegawai(nopegawai, tanggal);

                if (result.size() > 0) {
                    JSONArray arr = new JSONArray();
                    int port = request.getLocalPort();
                    String addr = request.getServerName();

                    String fotoMasuk = getValJson(result, "fotoMasuk");
                    String fotoPulang = getValJson(result, "fotoPulang");

                    String imageUrlMasuk = helper.getImageUrl(port, addr, nopegawai, fotoMasuk);
                    String imageUrlPulang = helper.getImageUrl(port, addr, nopegawai, fotoPulang);

//                File uploadDir = new File(UPLOAD_DIRECTORY);
//                if (!uploadDir.exists()) {
//                    uploadDir.mkdir();
//                }
//
//                String folder = UPLOAD_DIRECTORY + File.separator + nopegawai;
//                File dirDalam = new File(folder);
//                if (!dirDalam.exists()) {
//                    dirDalam.mkdir();
//                }
//
//                if (imageUrlMasuk.equals("")) {
//                    byte[] fileBytes = getValJson(result, "byteMasuk").getBytes();
//                    try (OutputStream targetFile = new FileOutputStream(new File(
//                            folder + File.separator + fotoMasuk))) {
//                        targetFile.write(fileBytes);
//                        targetFile.flush();
//                    }
//
//                    imageUrlMasuk = helper.getImgLapUrl(port, addr, nopegawai, fotoMasuk);
//                }
//
//                if (imageUrlPulang.equals("")) {
//                    byte[] fileBytes = getValJson(result, "bytePulang").getBytes();
//                    try (OutputStream targetFile = new FileOutputStream(new File(
//                            folder + File.separator + fotoPulang))) {
//                        targetFile.write(fileBytes);
//                        targetFile.flush();
//                    }
//
//                    imageUrlPulang = helper.getImgLapUrl(port, addr, nopegawai, fotoPulang);
//                }
                    obj.put("imageUrlMasuk", imageUrlMasuk);
                    obj.put("imageUrlPulang", imageUrlPulang);
                    result.add(obj);
                }
            } else if (param.containsKey("absensi") && param.containsKey("npp") && param.containsKey("tgl")) {
                String npp = request.getParameter("npp").trim();
                String tgl = request.getParameter("tgl").trim(); //"20200205";
                result = m_laporan.openLaporanAbsensi(npp, tgl);

                if (result.size() > 0) {
                    JSONArray arr = new JSONArray();
                    int port = request.getLocalPort();
                    String addr = request.getServerName();

                    String foto = getValJson(result, "nmafoto");

                    String imageUrlAbsensi = helper.getImageUrl(port, addr, npp, foto);

                    obj.put("imageUrlAbsensi", imageUrlAbsensi);
                    result.add(obj);
                }
            } else if (param.containsKey("makan") && param.containsKey("nip") && param.containsKey("tang")) {
                String npp = request.getParameter("nip").trim();
                String tgl = request.getParameter("tang").trim(); //"20200205";
                result = m_laporan.openLaporanMakan(npp, tgl);
            } else if (param.containsKey("npp") && param.containsKey("bulan") && param.containsKey("tahun")) {
                String npp = request.getParameter("npp").trim();
                String bulan = request.getParameter("bulan").trim();
                String tahun = request.getParameter("tahun").trim();

                int port = request.getLocalPort();
                String addr = request.getServerName();
                String server = helper.getUrl(port, addr, npp);

                result = m_laporan.openLaporanPresensiBulanan(npp, bulan, tahun, server);
            } else if (param.containsKey("laporan")) {
                String nopegawai = request.getParameter("nik").trim();
                String tanggal = request.getParameter("tgl").trim();
//                String server = "http://mobileapp.adihusada.co.id/";
                String server = "http://mobileapp.adihusada.com/";
//                String server = "http://mobileapp.adihusada.net/";

                result = m_laporan.openLaporanPresensiHarian(nopegawai, tanggal, server);
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
                Logger.getLogger(Laporan.class
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
