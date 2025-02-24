/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.helper;
import model.keyFinder;
import model.login;
import model.setvar;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author fadhil
 */
public class Login extends HttpServlet {

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
        JSONObject obj = new JSONObject();
        Map param = request.getParameterMap();

        login m_login = new login();
        setvar m_setvar = new setvar();

        try {
            if (param.containsKey("namauser")) {
                String namauser = request.getParameter("namauser").trim();
                result = m_login.loginBaru(namauser);
            } else if (param.containsKey("username") && param.containsKey("password")) {
                String username = request.getParameter("username").trim();
                String password = request.getParameter("password").trim();
                result = m_login.Login(username, password);
            } else if (param.containsKey("nopegawai") && param.containsKey("pin")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                String pin = request.getParameter("pin").trim();
                result = m_login.selectPin(nopegawai, pin);

                if (result.size() > 0) {
                    String counterUser = getValJson(result, "counterUser");
                    m_login.updateTgl(counterUser, nopegawai);
                } else {
                    m_login.updateHitung(nopegawai);
                }

            } else if (param.containsKey("versi")) {
                result = m_login.cekVersi();
            } else if (param.containsKey("radius")) {
                Integer radius = Integer.parseInt(m_setvar.selectSetvar("RS_", "radiusjapri"));
                obj.put("radius", radius);
                result.add(obj);
            } else if (param.containsKey("banner")) {

                int port = request.getLocalPort();
                String addr = request.getServerName();
                String server = helper.getBannerUrl(port, addr);

                result = m_login.openBanner(server);
            } else if (param.containsKey("promo")) {

                int port = request.getLocalPort();
                String addr = request.getServerName();
                String server = helper.getPromoUrl(port, addr);

                result = m_login.openPromo(server);
            } else if (param.containsKey("ads")) {

                int port = request.getLocalPort();
                String addr = request.getServerName();
                String server = helper.getPromoUrl(port, addr);

                result = m_login.openAds(server);
            } else if (param.containsKey("video")) {

                int port = request.getLocalPort();
                String addr = request.getServerName();
                String server = helper.getPromoUrl(port, addr);

                result = m_login.openVideo(server);
            } else if (param.containsKey("info")) {

                int port = request.getLocalPort();
                String addr = request.getServerName();
                String server = helper.getInfoUrl(port, addr);

                JSONArray arr = m_login.openInfoEndi();

                int i = 0;

                while (i < arr.size()) {
                    JSONObject objInfo = (JSONObject) arr.get(i);
                    String link = objInfo.get("fileSurat").toString();

                    URL url = new URL(link);
                    String FILE_NAME = FilenameUtils.getName(url.getPath());

                    File upOne = helper.getUpOne();
                    String uploadPath = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_file";
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    FileUtils.copyURLToFile(url, new File(uploadDir, FILE_NAME));

                    i++;
                }

                result = m_login.openInfo(server);
            } else if (param.containsKey("nopegawai") && param.containsKey("salahpin")) {
                result = m_login.salahPin(request.getParameter("nopegawai").trim());
            } else if (param.containsKey("promoSaringUrut") && param.containsKey("saring") && param.containsKey("urut") && param.containsKey("cari")) {

                int port = request.getLocalPort();
                String addr = request.getServerName();
                String server = helper.getPromoUrl(port, addr);
                String saring = request.getParameter("saring").trim();
                String urut = request.getParameter("urut").trim();
                String cari = request.getParameter("cari").trim();
                result = m_login.openPromoSaringUrutCari(server, saring, urut, cari);

            }  else if (param.containsKey("menu") && param.containsKey("ispegawai")) {

                int port = request.getLocalPort();
                String addr = request.getServerName();
                String server = helper.getMenuUrl(port, addr);
                Boolean ispegawai = Boolean.parseBoolean(request.getParameter("ispegawai").trim());

                result = m_login.openMenu(server, ispegawai);
            } else if (param.containsKey("selectUpdatelastActive") && param.containsKey("nopegawai")) {
                result = m_login.selectUpdateLastActive(request.getParameter("nopegawai").trim());
            }

        } catch (IOException ex) {
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

        login m_login = new login();

        try {
            if (param.containsKey("nopegawai") && param.containsKey("pin") && param.containsKey("deviceSerial") && param.containsKey("deviceName")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                String pin = request.getParameter("pin").trim();
                String deviceSerial = request.getParameter("deviceSerial").trim();
                String deviceName = request.getParameter("deviceName").trim();
                if (m_login.insertPin(nopegawai, pin, deviceSerial, deviceName)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            } else if (param.containsKey("salahpin")) {
                String nopegawai = request.getParameter("salahpin").trim();
                if (m_login.updateHitung(nopegawai)) {
                    obj.put("msg", "Pin Tidak Sesuai");
                    result.add(obj);
                }
            } else if (param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                if (m_login.updateLastActive(nopegawai)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            } else if (param.containsKey("logout")) {
                String nopegawai = request.getParameter("logout").trim();
                if (m_login.updateLogout(nopegawai)) {
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
                Logger.getLogger(Login.class
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


