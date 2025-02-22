/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.absensi;
import model.acara;
import model.helper;
import model.nomortelp;
import model.presensi;
import model.stdfield;
import model.usercredentials;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author fadhil
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class Upload extends HttpServlet {

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
        usercredentials m_user  = new usercredentials();
        JSONObject obj          = new JSONObject();

        try {
            if(param.containsKey("checkInValidation")){
                String nopegawai    = request.getParameter("npp").trim();
                String counterAtt   = request.getParameter("counter").trim();
                result = m_presensi.checkInValidation(nopegawai, counterAtt);
            } else if(param.containsKey("checkOutValidation")){
                String nopegawai    = request.getParameter("npp").trim();
                String counterAtt   = request.getParameter("counter").trim();
                result = m_presensi.checkOutValidation(nopegawai, counterAtt);
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
        absensi m_absensi = new absensi();
        acara m_acara = new acara();

        try {

            String nopegawai = request.getParameter("nopegawai").trim();
            String jnsfoto = request.getParameter("jnsfoto").trim();

            File upOne = helper.getUpOne();
            String uploadPath = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_foto" + File.separator + nopegawai;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            byte[] imgBytes = null;
            String fileName = "";

            if (isMultipart) {
                Part filePart = request.getPart("image");
                fileName = (helper.getNowDate("yyyyMMdd")) + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString().trim();
                InputStream fileContent = filePart.getInputStream();

                try {
                    imgBytes = IOUtils.toByteArray(fileContent);
                    filePart.write(uploadPath + File.separator + fileName);
                } catch (IOException ex) {
                    Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
                    result = new JSONArray();
                    result.add(helper.setErrorMessage(ex.getMessage()));
                    System.out.println(ex.getMessage());
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            } else {
                String foto = request.getParameter("foto").trim();
                fileName = (helper.getNowDate("yyyyMMdd")) + "_" + nopegawai + "_" + System.currentTimeMillis() + ".jpeg";
                File outputFile = new File(uploadPath + File.separator + fileName);

                try {
                    imgBytes = Base64.getMimeDecoder().decode(foto);
                    FileUtils.writeByteArrayToFile(outputFile, imgBytes);
                } catch (IOException ex) {
                    result = new JSONArray();
                    result.add(helper.setErrorMessage(ex.getMessage()));
                    System.out.println(ex.getMessage());
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }

            if (param.containsKey("counterAtt")) {
                
                String counterAtt = request.getParameter("counterAtt").trim();
                obj.put("counterAtt", counterAtt);
                
                if (m_presensi.insertPresensiFoto(counterAtt, nopegawai, imgBytes, jnsfoto, fileName)) {
                    int port = request.getLocalPort();
                    String addr = request.getServerName();
                    String imageUrl = helper.getImageUrl(port, addr, nopegawai, fileName);
                    obj.put("imageUrl", imageUrl);

                    if (m_presensi.updateValidPresensi(counterAtt, nopegawai)) {
                        obj.put("msg", "Sukses");
                        
                        
                    }

                    
                }
                
                
                
                result.add(obj);
                
                
            } else if (param.containsKey("counterAbs")) {
                String counterAbs = request.getParameter("counterAbs").trim();
                if (m_absensi.insertAbsensiFoto(counterAbs, nopegawai, imgBytes, jnsfoto, fileName)) {
                    int port = request.getLocalPort();
                    String addr = request.getServerName();
                    String imageUrl = helper.getImageUrl(port, addr, nopegawai, fileName);
                    obj.put("imageUrl", imageUrl);
                    
                    if (m_absensi.updateValidAbsensi(counterAbs, nopegawai)) {
                        obj.put("msg", "Sukses");
                    }
                    
                    result.add(obj);
                }
            } else if (param.containsKey("counterFoto")) {
                String counterFoto = request.getParameter("counterFoto").trim();
                if (m_acara.insertAcaraFoto(counterFoto, nopegawai, imgBytes, jnsfoto, fileName)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            }

        } catch (IOException | ServletException ex) {
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

}

