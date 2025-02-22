/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import model.formPerbaikanKomputer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rizal.Fathurrahman
 */
@WebServlet(name = "eformPK", urlPatterns = {"/eformPK"})
public class eformPK extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    


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
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // 1️⃣ Baca file service account JSON
            String path = getServletContext().getRealPath("/WEB-INF/config/service_accounts.json");
            JSONObject credentials = readJsonFile(path);

            // 2️⃣ Ambil nilai penting dari JSON
            String clientEmail = (String) credentials.get("client_email");
            String privateKey = (String) credentials.get("private_key");
            String tokenUri = (String) credentials.get("token_uri");
//
            // 3️⃣ Buat JWT dan tukarkan dengan token akses
            String jwt = JwtGenerator.createJwt(clientEmail, privateKey, tokenUri);
            String accessToken = fetchAccessToken(tokenUri, jwt);
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("access_token", accessToken);
            jsonResponse.put("expires_in", 3600);

            PrintWriter out = response.getWriter();
            out.print(jsonResponse.toJSONString());
            out.flush();
            
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", e.getMessage()); // Menampilkan alasan utama error
            errorResponse.put("error", e.getLocalizedMessage());

            PrintWriter out = response.getWriter();
            out.print(errorResponse.toJSONString());
            out.flush();
        }
    }
    
    // Fungsi membaca JSON file
    private static JSONObject readJsonFile(String path) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader reader = new FileReader(path);
        return (JSONObject) parser.parse(reader);
    }
   

    // Fungsi kirim HTTP POST ke Google OAuth
    private static String fetchAccessToken(String tokenUri, String jwt) throws IOException, ParseException {
        URL url = new URL(tokenUri);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setConnectTimeout(15000); // 15 detik
        conn.setReadTimeout(15000);
        conn.setDoOutput(true);

        // Kirim request body
        String body = "grant_type=urn:ietf:params:oauth:grant-type:jwt-bearer&assertion=" + jwt;
        try (OutputStream os = conn.getOutputStream()) {
            os.write(body.getBytes(StandardCharsets.UTF_8));
        }

        StringBuilder response;
        try ( // Baca response
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
        }

        // Ambil access token dari JSON
        JSONObject jsonResponse = (JSONObject) new JSONParser().parse(response.toString());
        return (String) jsonResponse.get("access_token");
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
//        processRequest(request, response);
        response.setContentType("application/json");
        Map<String, Object> responseData = new HashMap<>();
        PrintWriter out = response.getWriter();

        String aksi = request.getParameter("actionForm");
        formPerbaikanKomputer japri = new formPerbaikanKomputer();
        
        // Membaca data JSON dari request
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        try {
            switch (aksi) {
                case "formBaru":
                    // <editor-fold defaultstate="collapsed" desc="formBaru">
                    // Parsing JSON menggunakan JSONParser
                    JSONParser parser = new JSONParser();
                    JSONObject jsonObject = (JSONObject) parser.parse(sb.toString());

                    // Mengambil nilai JSON
                    String uname            = (String) jsonObject.get("uname");
                    JSONObject dataObject   = (JSONObject) jsonObject.get("data");

                    // Validasi data
                    // <editor-fold defaultstate="collapsed" desc="loop">
                    if (dataObject != null) {
                        String txtLokasiKerja       = (String) dataObject.get("txtLokasiKerja");
                        String txtDepartemen        = (String) dataObject.get("txtDepartemen");
                        String txtUnit              = (String) dataObject.get("txtUnit");
                        String txtKplunit           = (String) dataObject.get("txtKplunit");
                        String txtMasalah           = (String) dataObject.get("txtMasalah");
                        String txtAlat              = (String) dataObject.get("txtAlat");
                        String isDone               = (String) dataObject.get("isDone");
                        String ddlPrioritas         = (String) dataObject.get("ddlPrioritas");
                        String acceptedBy           = (String) dataObject.get("acceptedBy");
                        String doneBy               = (String) dataObject.get("doneBy");
                        String usrEdp               = (String) dataObject.get("usrEdp");
                        String usrNotify            = (String) dataObject.get("usrNotify");
                        String otherUnit            = (String) dataObject.get("hasPurposes");

                        // Mengatur nilai pada objek `japri`
                        japri.setLokasiKerja(txtLokasiKerja);
                        japri.setDepartemen(txtDepartemen);
                        japri.setUnit(txtUnit);
                        japri.setKepalaUnit(txtKplunit);
                        japri.setApproveUser(Boolean.TRUE);
                        japri.setProbleam(txtMasalah);
                        japri.setDevice(txtAlat);
                        japri.setUser(usrEdp);
                        japri.setAcceptedBy(acceptedBy);
                        japri.setDoneBy(doneBy);
                        japri.setPrioritas(ddlPrioritas);
                        japri.setIsDone(isDone);
                        japri.setIsPurposed(otherUnit);
                        japri.setUsrEdp(usrEdp);
                        japri.setUsrNotify(usrNotify);
                        

                        // Simpan data ke database
                        JSONArray resultFromInsert = japri.inserEformPK();
                        JSONArray resultFromGet;
                        String newCreatedFormId;
                        
                        if(resultFromInsert.size() > 0)
                        {
                            JSONObject resultObject = (JSONObject) resultFromInsert.get(0); 
                            // Extract needed values from the JSONObject
                            newCreatedFormId  = (String) resultObject.get("NoForm");
                            japri.setNoForm(newCreatedFormId);
                            
                            resultFromGet = japri.readAfterInsertPK();
                            
                            // Tambahkan ke respons
                            responseData.put("status", "success");
                            responseData.put("message", "Data berhasil disimpan");
                            responseData.put("result", resultFromGet);
                        }
                        
                        
                    } else {
                        responseData.put("status", "error");
                        responseData.put("message", "Data tidak valid");
                    }
                    // </editor-fold>
                    break;
                    // </editor-fold>
                    
                case "terimaUser":
                    // Mengambil nilai JSON
                    String user_name            = request.getParameter("uname");
                    String eform_id             = request.getParameter("noform");
//                   
                    responseData.put("uname", user_name);
                    responseData.put("eformId", eform_id);
                    
                   
                    // Simpan data ke database
                    JSONArray resultFromInsert = japri.terimaUser(user_name, eform_id);

                    if(resultFromInsert.size() > 0)
                    {
                        // Tambahkan ke respons
                        responseData.put("status", "success");
                        responseData.put("message", "Data berhasil diupdate");
                        responseData.put("result", resultFromInsert);
                    }
                    
                    else{
                        // Tambahkan ke respons
                        responseData.put("status", "failed");
                        responseData.put("message", "Data gagal diupdate");
                        responseData.put("result", resultFromInsert);
                    }
                    
                    
                    break;

                default:
                    responseData.put("status", "error");
                    responseData.put("message", "Aksi tidak dikenali");
                    break;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            responseData.put("status", "error");
            responseData.put("message", "Kesalahan parsing JSON: " + e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
            responseData.put("status", "error");
            responseData.put("message", "Terjadi kesalahan: " + e.getMessage());
            responseData.put("s", "Terjadi kesalahan: " + e.getLocalizedMessage());
            responseData.put("d", "Terjadi kesalahan: " + e.getCause());
        } finally {
            // Konversi map menjadi JSON menggunakan Gson
            String jsonResponse = new Gson().toJson(responseData);
            out.write(jsonResponse);
            out.flush();
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
