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
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.absensi;
import model.acara;
import model.helper;
import model.presensi;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author fadhil
 */
public class Unggah extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        JSONArray result = new JSONArray();
        Map param = request.getParameterMap();

        presensi m_presensi = new presensi();
        absensi m_absensi = new absensi();
        acara m_acara = new acara();

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        String nopegawai = "";
        String jnsfoto = "";
        String foto = "";
        String fileName = "";
        byte[] imgBytes = null;

        if (isMultipart) {

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Configure a repository (to ensure a secure temp location is used)
            ServletContext servletContext = this.getServletConfig().getServletContext();
            File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                // Parse the request
                List<FileItem> items = upload.parseRequest(request);

                // Process the uploaded items
                Iterator<FileItem> iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = iter.next();
                    InputStream stream = item.getInputStream();

                    if (item.isFormField()) {
                        String name = item.getFieldName();
                        String value = item.getString();

                        if ("nopegawai".equalsIgnoreCase(name.trim()) && name.trim() != null) {
                            nopegawai = value.trim();
                        } else if ("jnsfoto".equalsIgnoreCase(name.trim()) && name.trim() != null) {
                            jnsfoto = value.trim();
                        }

                    } else {
//                        String fieldName = item.getFieldName();
//                        String fileName = item.getName();
//                        String contentType = item.getContentType();
//                        boolean isInMemory = item.isInMemory();
//                        long sizeInBytes = item.getSize();
                    }

                    File upOne = helper.getUpOne();
                    String uploadPath = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_foto" + File.separator + nopegawai;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                }

            } catch (FileUploadException ex) {
                Logger.getLogger(Unggah.class.getName()).log(Level.SEVERE, null, ex);
                result = new JSONArray();
                result.add(helper.setErrorMessage(ex.getMessage()));
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

        } else {
            nopegawai = request.getParameter("nopegawai").trim();
            jnsfoto = request.getParameter("jnsfoto").trim();
            foto = request.getParameter("foto").trim();

            File upOne = helper.getUpOne();
            String uploadPath = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_foto" + File.separator + nopegawai;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            fileName = (helper.getNowDate("yyyyMMdd")) + "_" + nopegawai + "_" + System.currentTimeMillis() + ".jpeg";
            File outputFile = new File(uploadPath + File.separator + fileName);

            imgBytes = Base64.getMimeDecoder().decode(foto);
            FileUtils.writeByteArrayToFile(outputFile, imgBytes);

        }

        out.write(result.toJSONString());
        System.out.println(result.toJSONString());

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
