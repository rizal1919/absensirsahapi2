/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Informasi;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
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
import model.helper;
import model.nomortelp;
import model.profile;
import model.setvar;
import model.stdfield;
import model.usercredentials;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author fadhil
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadDok extends HttpServlet {

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
        
        PrintWriter out             = response.getWriter();
        JSONArray result            = new JSONArray();
        Map param                   = request.getParameterMap();
        Informasi dataInfo          = new Informasi();
        usercredentials m_user      = new usercredentials();
        JSONObject obj              = new JSONObject();
        

        try {
            if (param.containsKey("get_all")) {
                result = dataInfo.getAllActiveInfo();
            } else if(param.containsKey("get_top_info_highlight")) {
                result = dataInfo.findTopHighlight();
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

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
//        ServletFileUpload upload = new ServletFileUpload(factory);

        setvar m_setvar = new setvar();
        profile m_profile = new profile();
        Informasi dataInfo = new Informasi();
        Map param = request.getParameterMap();
        String data = "[]";

        String UPLOAD_DIRECTORY = m_setvar.selectSetvar("PG_", "urlDok");
//        String UPLOAD_DIRECTORY = "\\\\100.100.100.117\\d$\\pdf-java-live\\dokpegawai";
        String viewUrl = m_setvar.selectSetvar("PG_", "urlViewDok");

        PrintWriter out = response.getWriter();
        JSONObject obj = new JSONObject();
        JSONArray result = new JSONArray();
        
        /**/
        if (param.containsKey("submitDokumen")) {
            
            // tangkap parameter dari android
            dataInfo.setUsrInsert(request.getParameter("usrInsert").trim());
            dataInfo.setTitle(request.getParameter("title").trim());
            dataInfo.setDescription(request.getParameter("description").trim());
//            
            //  Specify your upload directory
            File upOne = helper.getUpOne();
            String uploadPath = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_file";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            // Obtain the uploaded file part
            Part filePart = request.getPart("file"); // "file" is the name of the file input field in your HTML form

            // Extract filename from the uploaded file part
            String fileName = getSubmittedFileName(filePart); // Implement getSubmittedFileName method
            
            // Initialize file url
            int port        = request.getLocalPort();
            String addr     = request.getServerName();
            String filename = fileName.replace(" ", "%20");
            String fileUrl  = helper.getFileUrl(port, addr, filename);
            dataInfo.setUrlDokumen(fileUrl);
           
            
            // Create the destination file
            File destFile = new File(uploadDir + File.separator + fileName);

            // Copy the uploaded file to the destination directory
            try (InputStream fileContent = filePart.getInputStream();
                 OutputStream outputStream = new FileOutputStream(destFile)) {

                // Copy the contents of the fileContent to the outputStream
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileContent.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                
                // ketika proses upload berhasil, lanjutkan mengirim data informasi ke db
                if(dataInfo.submitToInformasi()){
                    obj.put("message", "Data berhasil diunggah");
                    result.add(obj);
                }

            } catch (IOException ex) {
                result = new JSONArray();
                result.add(helper.setErrorMessage(ex.getMessage()));
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }else if(param.containsKey("updateDocument")){
            
            /* Proses pengecekan apakah ada dokumen baru yang diupload (Jika Ada) */
            // Take the newFileName 
            dataInfo.setNewFileName(request.getParameter("newFileName").trim());
            
            if(!dataInfo.getNewFileName().equals("")){
                
                dataInfo.setSavedFileName(request.getParameter("savedFileName").trim());
                
                // Default
                String tomcatBase = System.getProperty("catalina.base");

                // Define the relative path to your file within the Tomcat base directory
                String relativeFilePath = "/webapps/ROOT/apk_file/" + dataInfo.getSavedFileName();

                // Construct the full file path
                String filePath = tomcatBase + relativeFilePath;

                // Execute if file already there than delete
                File file = new File(filePath);
                if (file.exists() && !file.isDirectory()) {
                    obj.put("existing", "File Exist");
                    if (file.delete()) {
                        obj.put("deleted", "File Successfully Deleted.");
                    } else {
                        obj.put("deleted", "File Unsuccessfully Deleted");
                    }
                } else {
                    obj.put("existing", "File Doesn't Exist");
                }

                /* Proses Update Dimulai */
                // tangkap parameter dari android
                dataInfo.setUsrUpdate(request.getParameter("usrUpdate").trim());
                dataInfo.setTitle(request.getParameter("title").trim());
                dataInfo.setOldTitle(request.getParameter("oldTitle").trim());
                dataInfo.setDescription(request.getParameter("description").trim());

                //  Specify your upload directory
                File upOne = helper.getUpOne();
                String uploadPath = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_file";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // Obtain the uploaded file part
                Part filePart = request.getPart("file"); // "file" is the name of the file input field in your HTML form

                // Extract filename from the uploaded file part
                String fileName = getSubmittedFileName(filePart); // Implement getSubmittedFileName method

                // Initialize file url
                int port        = request.getLocalPort();
                String addr     = request.getServerName();
                String filename = fileName.replace(" ", "%20");
                String fileUrl  = helper.getFileUrl(port, addr, filename);
                dataInfo.setUrlDokumen(fileUrl);

                // Create the destination file
                File destFile = new File(uploadDir + File.separator + fileName);

                // Copy the uploaded file to the destination directory
                try (InputStream fileContent = filePart.getInputStream();
                     OutputStream outputStream = new FileOutputStream(destFile)) {

                    // Copy the contents of the fileContent to the outputStream
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = fileContent.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    // ketika proses upload berhasil, lanjutkan mengupdate data informasi ke db
                    obj.put("message", "Data berhasil diupdate");
                    result.add(obj);
                    result.add(dataInfo.updateToInformasi());

                } catch (Exception ex) {
                    result = new JSONArray();
                    result.add(helper.setErrorMessage(ex.getMessage()));
                    System.out.println(ex.getMessage());
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }else{
                
                /* Proses Update Dimulai */
                // tangkap parameter dari android
                dataInfo.setUsrUpdate(request.getParameter("usrUpdate").trim());
                dataInfo.setTitle(request.getParameter("title").trim());
                dataInfo.setOldTitle(request.getParameter("oldTitle").trim());
                dataInfo.setDescription(request.getParameter("description").trim());
                dataInfo.setUrlDokumen(request.getParameter("urlDocument").trim());

                // Copy the uploaded file to the destination directory
                try {
                    // ketika proses berhasil, lanjutkan mengupdate data informasi ke db
                    // Write the JSON result to the response
                    data = dataInfo.updateToInformasi();
                    response.getWriter().write(data);
                    return;

                } catch (Exception ex) {
                    result = new JSONArray();
                    result.add(helper.setErrorMessage(ex.getMessage()));
                    System.out.println(ex.getMessage());
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                }
            }
            
            
        }else if(param.containsKey("changeStatusDocument")){
            
            // Take the data informations need
            dataInfo.setAktif(Integer.parseInt(request.getParameter("aktif")));
            dataInfo.setUsrUpdate(request.getParameter("usrUpdate").trim());
            dataInfo.setTitle(request.getParameter("title").trim());
            
            try{
                data = dataInfo.deleteInformasi();
                // Write the JSON result to the response
                response.getWriter().write(data);
                return;
                
            }catch (Exception ex) {
                result = new JSONArray();
                result.add(helper.setErrorMessage(ex.getMessage()));
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
            
            
        }else if(param.containsKey("active_contributors")){
            
            try{
                data = dataInfo.find_active_contributors();
                
            }catch (Exception ex) {
                result = new JSONArray();
                result.add(helper.setErrorMessage(ex.getMessage()));
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
            // Write the JSON result to the response
            response.getWriter().write(data);
            return;
            
            
            
        }else if(param.containsKey("unactive_contributors")){
            
            try{
                data = dataInfo.find_unactive_contributors();
                
            }catch (Exception ex) {
                result = new JSONArray();
                result.add(helper.setErrorMessage(ex.getMessage()));
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
            // Write the JSON result to the response
            response.getWriter().write(data);
            return;
            
            
        }
        
        /* return * from query */
        else if(param.containsKey("changeHighlight")){
            
            try{
                
                if(!request.getParameter("lastTopInfoTitle").trim().equals("")){
                    dataInfo.setOldTitle(request.getParameter("lastTopInfoTitle").trim());
                }
                
                dataInfo.setTitle(request.getParameter("newTopInfoTitle").trim());
                dataInfo.setUsrUpdate(request.getParameter("usrUpdate").trim());
                result = dataInfo.removeSignHighlight();
                
            }catch (Exception ex) {
                result = new JSONArray();
                result.add(helper.setErrorMessage(ex.getMessage()));
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
            // Write the JSON result to the response
            PrintWriter res = response.getWriter();
            res.print(result.toString());
            res.flush();
            
            // Optionally close the writer
            out.close();
          
        }
        
        /* return data if success */
        else if(param.containsKey("removeHighlight")){
            
            try{
                dataInfo.setTitle(request.getParameter("title").trim());
                result = dataInfo.removeTopHighlight();
                
            }catch (Exception ex) {
                result = new JSONArray();
                result.add(helper.setErrorMessage(ex.getMessage()));
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            
            // Write the JSON result to the response
            PrintWriter res = response.getWriter();
            res.print(result.toString());
            res.flush();
            
            // Optionally close the writer
            out.close();
          
        }
        
        /* return data if success */
        else if(param.containsKey("loadQR")){
            
            try {
                // Get the 'npp' parameter from the request
                String npp = request.getParameter("npp").trim();

                // Get the Tomcat base directory
                String tomcatBase = System.getProperty("catalina.base");

                // Define the relative path to your file within the Tomcat base directory
                String relativeFilePath = "/webapps/ROOT/barcode_makan/qrcode_" + npp + ".png";

                // Construct the full file path
                String filePath = tomcatBase + relativeFilePath;

                // Create the file object
                File file = new File(filePath);

                // Check if the file exists
                if (file.exists()) {
                    // File exists, return the full file path in the JSON response
                    obj.put("fullPath", filePath);
                } else {
                    // File doesn't exist, return a message
                    obj.put("fullPath", "File Doesn't Exist");
                }

                // Add the JSON object to the result
                result = new JSONArray();
                result.add(obj);

            } catch (Exception ex) {
                // Handle exceptions and return an error message
                result = new JSONArray();
                result.add(helper.setErrorMessage(ex.getMessage()));
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }

            // Write the JSON result to the response
            PrintWriter res = response.getWriter();
            res.print(result.toString());
            res.flush();

            // Optionally close the writer
            res.close(); // Ensure that 'res' is properly closed
          
        }
        
        else{
            try {

                String nopegawai = request.getParameter("nopegawai").trim();
                String counter = request.getParameter("counter").trim();
                String kdJenis = request.getParameter("kdJenis").trim();
                String nmJenis = request.getParameter("nmJenis").trim();
                String judul = request.getParameter("judul").trim();

                int port = request.getLocalPort();
                String addr = request.getServerName();

                File uploadDir = new File(UPLOAD_DIRECTORY + File.separator + nopegawai);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                File upOne = helper.getUpOne();
                String uploadPath = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_lampiran" + File.separator + nopegawai;
                File upDir = new File(uploadPath);
                if (!upDir.exists()) {
                    upDir.mkdir();
                }

                boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                byte[] imgBytes = null;
                String fileName = "";
                String urlDok = "";
                String viewDok = "";

                if (isMultipart) {
                    Part filePart = request.getPart("image");
                    fileName = nopegawai + "_" + nmJenis + "_" + judul + "_" + Paths.get(filePart.getSubmittedFileName()).getFileName().toString().trim();
                    InputStream fileContent = filePart.getInputStream();

                    urlDok = UPLOAD_DIRECTORY + File.separator + nopegawai + File.separator + fileName;
                    viewDok = viewUrl + "/" + nopegawai + "/" + fileName;

                    try {
    //                    imgBytes = IOUtils.toByteArray(fileContent);
    //                    filePart.write(urlDok);
                        filePart.write(uploadPath + File.separator + fileName);

    //                    String imageLampiran = "http://" + helper.getLampiranUrl(port, addr, nopegawai, fileName.replace(" ", "%20"));
                        String imageLampiran = "http://100.100.100.114:5758/apk_lampiran" + "/" + nopegawai + "/" + fileName.replace(" ", "%20"); //1907/1907_KTP%20IBU%20KANDUNG_coba_1907_4386296709582118108.jpg";
                        copyFromURLUsingApache(new URL(imageLampiran), new File(UPLOAD_DIRECTORY + File.separator + nopegawai, fileName));
    //                    copyInputStreamToFileUsingApache(fileContent, new File(urlDok));
    //                    FileUtils.copyToFile(fileContent, new File(urlDok));
                        if (m_profile.saveDokumen(nopegawai, counter, kdJenis, judul, urlDok, viewDok, "0")) {
                            obj.put("msg", "Sukses");
                            result.add(obj);
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(Upload.class.getName()).log(Level.SEVERE, null, ex);
                        result = new JSONArray();
                        result.add(helper.setErrorMessage(ex.getMessage()));
                        System.out.println(ex.getMessage());
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }

                } else {
                    String foto = request.getParameter("foto").trim();
                    fileName = nopegawai + "_" + nmJenis + "_" + judul + ".jpeg";

                    urlDok = UPLOAD_DIRECTORY + File.separator + nopegawai + File.separator + fileName;
                    viewDok = viewUrl + "/" + nopegawai + "/" + fileName;

                    File outputFile = new File(urlDok);

                    try {
                        imgBytes = Base64.getMimeDecoder().decode(foto);
                        FileUtils.writeByteArrayToFile(outputFile, imgBytes);

                        if (m_profile.saveDokumen(nopegawai, counter, kdJenis, judul, urlDok, viewDok, "0")) {
                            obj.put("msg", "Sukses");
                            result.add(obj);
                        }
                    } catch (IOException ex) {
                        result = new JSONArray();
                        result.add(helper.setErrorMessage(ex.getMessage()));
                        System.out.println(ex.getMessage());
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                }

    //            if (m_profile.saveDokumen(nopegawai, counter, kdJenis, judul, urlDok, viewDok, "0")) {
    //                obj.put("msg", "Sukses");
    //                result.add(obj);
    //            }
            } catch (IOException | ServletException ex) {
                result = new JSONArray();
                result.add(helper.setErrorMessage(ex.getMessage()));
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
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

    private void copyFromURLUsingApache(URL from, File to) throws IOException {
        FileUtils.copyURLToFile(from, to);
    }

    private void copyInputStreamToFileUsingApache(InputStream is, File to) throws IOException {
        FileUtils.copyInputStreamToFile(is, to);
    }
    
    // Helper method to get submitted file name
    private String getSubmittedFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}


