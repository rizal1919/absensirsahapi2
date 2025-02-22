/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import com.google.auth.oauth2.GoogleCredentials;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.formPerbaikanKomputer;
import model.nomortelp;
import model.helper;
import model.stdfield;
import model.presensi;
import model.usercredentials;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Rizal.Fathurrahman
 */

public class User extends HttpServlet {
    
//    private static final String SERVICE_ACCOUNT_PATH = "/WEB-INF/service-account-file.json";

    
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

        //nomortelp m_nomortelp = new nomortelp();
        nomortelp m_nomortelp   = new nomortelp();
        stdfield m_stdfield     = new stdfield();
        presensi m_presensi     = new presensi();
        usercredentials m_user  = new usercredentials();
        formPerbaikanKomputer eform = new formPerbaikanKomputer();
        JSONObject obj = new JSONObject();
        

        try {
            if(param.containsKey("getUserHierarchy")){
                String nopegawai = request.getParameter("npp").trim();
                result = m_user.selectUserHierarchy(nopegawai);
            } else if (param.containsKey("npp")) {
                String npp = request.getParameter("npp").trim();
                result = m_user.selectUser(npp);
            } else if (param.containsKey("kdfield")) {
                String kdfield = request.getParameter("kdfield").trim();
                result = m_stdfield.selectStdField(kdfield);
            } else if (param.containsKey("cuti") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_nomortelp.selectOneCuti(nopegawai);
            } else if (param.containsKey("tglcuti") && param.containsKey("npp")) {
                String nopegawai = request.getParameter("npp").trim();
                result = m_nomortelp.selectTglCuti(nopegawai);
            } else if (param.containsKey("tglmerah")) {
                result = m_nomortelp.viewTglMerah();
            } else if(param.containsKey("showResetter")){
                result = m_user.selectResetter();
            } else if(param.containsKey("resetPin")){
                String npp = request.getParameter("resetPin").trim();
                if(m_user.resetPin(npp)){
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }else{
                    obj.put("msg", "Gagal");
                    result.add(obj);
                }
            } else if(param.containsKey("findUserAuthorizedEform")){
                String npp = request.getParameter("findUserAuthorizedEform").trim();
                result = m_user.findUserEform(npp);
            } else if(param.containsKey("loadImagePromo")){
                result = m_user.loadImagePromo();
            } 
            else if(param.containsKey("getPrivateJsonFile"))
            {
                try {
                    // File path konstruktor
                    File upOne = helper.getUpOne();
                    String pathFile = upOne.getAbsolutePath() + File.separator + "webapps" + File.separator + "ROOT" + File.separator + "firebase" + File.separator;
//                    String filePath = "D:\\Users\\rizal.fathurrahman\\Downloads\\JAPRI-AH NOTIF CREDENTIALS\\japri-ah-cd39e-firebase-adminsdk-c94xe-d49bfac836.json";
//                    String filePath     = pathFile + "japri-ah-cd39e-firebase-adminsdk-c94xe-d49bfac836.json";
                    String filePath     = pathFile + "japri-ah-bb4c3-firebase-adminsdk-ze1wm-a76ab73e59.json";
                    String projectName  = "japri-ah-bb4c3";

                    // Baca file dan kirimkan sebagai response
                    File file = new File(filePath);
                    if (file.exists()) {
                        try (FileInputStream fis = new FileInputStream(file)) {
                            // Gunakan metode manual untuk membaca semua byte
                            byte[] fileContent = readAllBytes(fis);

                            // Tambahkan status, pesan, dan data file ke JSON
                            obj.put("status", "success");
                            obj.put("projectName", projectName);
                            obj.put("message", "File retrieved successfully");
                            obj.put("fileContent", new String(fileContent, StandardCharsets.UTF_8)); // Pastikan encoding sesuai
                            obj.put("additionalData", "You can add more data here");
                            result.add(obj);

                            response.setStatus(HttpServletResponse.SC_OK);
                        } catch (Exception e) {
                            obj.put("status", "error");
                            obj.put("message", "Failed to read file: " + e.getMessage());
                            result.add(obj);
                            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        obj.put("status", "error");
                        obj.put("message", "File not found");
                        result.add(obj);
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    }

                } catch (Exception e) {
                    // Menangani error dan menambahkan ke JSON response
                    e.printStackTrace();
                    obj.put("status", "error");
                    obj.put("message", "Failed to generate access token");
                    obj.put("error_detail", e.getMessage());
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
    
    private byte[] readAllBytes(FileInputStream fis) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int bytesRead;
        while ((bytesRead = fis.read(data)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        return buffer.toByteArray();
    }

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
