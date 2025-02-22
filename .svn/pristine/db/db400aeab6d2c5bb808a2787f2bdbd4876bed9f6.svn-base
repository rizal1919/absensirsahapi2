/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.helper;
import model.keyFinder;
import model.profile;
import model.stdfield;
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
public class Profile extends HttpServlet {

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

        profile m_profile = new profile();
        stdfield m_stdfield = new stdfield();

        try {
            if (param.containsKey("pkb")) {
                int port = request.getLocalPort();
                String addr = request.getServerName();
                String filename = "pkb.pdf";
                String fileUrl = helper.getFileUrl(port, addr, filename);
                obj.put("fileUrl", fileUrl);
                obj.put("fileName", filename);
                result.add(obj);
            } else if (param.containsKey("gaji") && param.containsKey("nopegawai") && param.containsKey("bulan") && param.containsKey("tahun")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                String bulan = request.getParameter("bulan").trim();
                String tahun = request.getParameter("tahun").trim();
                int port = request.getLocalPort();
                String addr = request.getServerName();
                String filename = bulan + "_" + tahun + "_" + nopegawai + ".pdf";
                String fileUrl = helper.getSlipUrl(port, addr, nopegawai, filename);
                obj.put("fileUrl", fileUrl);
                obj.put("fileName", filename);
                result.add(obj);
            } else if (param.containsKey("profile") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                int port = request.getLocalPort();
                String addr = request.getServerName();
                String server = helper.getUrl(port, addr, nopegawai);
                result = m_profile.SelectOneProfilePicture(nopegawai, server);
            } else if (param.containsKey("diri") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_profile.SelectOneProfileDiri(nopegawai);
            } else if (param.containsKey("keluarga") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_profile.SelectAllProfileKeluarga(nopegawai);
            } else if (param.containsKey("anak") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_profile.SelectAllProfileAnak(nopegawai);
            } else if (param.containsKey("pilihanak") && param.containsKey("nopegawai") && param.containsKey("counter")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                String counter = request.getParameter("counter").trim();
                result = m_profile.SelectOneProfileAnak(counter, nopegawai);
            } else if (param.containsKey("kerja") && param.containsKey("nopegawai") && param.containsKey("tetap")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                Boolean tetap = StringParseBool(request.getParameter("tetap").trim());
                result = m_profile.SelectOneProfileKerja(nopegawai, tetap);
            } else if (param.containsKey("lampiran") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();

                result = m_profile.SelectAllProfileLampiran(nopegawai);

                int i = 0;

                while (i < result.size()) {

                    JSONObject objLampiran = (JSONObject) result.get(i);
                    String dir = objLampiran.get("dir").toString();
                    String link = objLampiran.get("mylink").toString();

                    int port = request.getLocalPort();
                    String addr = request.getServerName();

                    URL url = new URL("http://" + link);
                    String FILE_NAME = FilenameUtils.getName(url.getPath());

                    File upOne = helper.getUpOne();
                    String uploadPath = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_lampiran" + File.separator + nopegawai;
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) {
                        uploadDir.mkdir();
                    }

                    copyFromURLUsingApache(new URL("http://100.100.100.103:5758/dokpegawai/" + nopegawai + "/" + FILE_NAME.replace(" ", "%20")), new File(uploadPath, FILE_NAME));

                    String imageLampiran = helper.getLampiranUrl(port, addr, nopegawai, FILE_NAME);

                    objLampiran.put("mylink", imageLampiran);

                    i++;
                }
            } else if (param.containsKey("counterLampiran") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                String counter = request.getParameter("counterLampiran").trim();
                result = m_profile.SelectOneProfileLampiran(counter, nopegawai);

                int i = 0;

                while (i < result.size()) {

                    obj = (JSONObject) result.get(i);

                    String link = getValJson(result, "namadok");
                    URL url = new URL("http://" + link);
                    String FILE_NAME = FilenameUtils.getName(url.getPath());

                    int port = request.getLocalPort();
                    String addr = request.getServerName();
                    String namadok = helper.getLampiranUrl(port, addr, nopegawai, FILE_NAME);

                    obj.put("namadok", namadok);

                    i++;
                }

            } else if (param.containsKey("kdfield")) {
                String kdfield = request.getParameter("kdfield").trim();
                result = m_stdfield.selectStdField(kdfield);
            } else if (param.containsKey("levelBagian")) {
                String levelBagian = request.getParameter("levelBagian").trim();
                result = m_profile.selectAllLokasiKerja(levelBagian);
            } else if (param.containsKey("jabatan")) {
                result = m_profile.selectAllJabatan();
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
        JSONObject param = getJSONBody(request);

        profile m_profile = new profile();

        try {

            if (param.containsKey("diri")) {
                String npp = param.get("npp").toString().trim();
                String nama = param.get("nama").toString().trim();
                String noktp = param.get("noktp").toString().trim();
                String nonpwp = param.get("nonpwp").toString().trim();
                String tmplahir = param.get("tmplahir").toString().trim();
                String tgllahir = param.get("tgllahir").toString().trim();
                String alamatskr = param.get("alamatskr").toString().trim();
                String alamatktp = param.get("alamatktp").toString().trim();
                String notelp = param.get("notelp").toString().trim();
                String nohp = param.get("nohp").toString().trim();
                String tglmenikah = param.get("tglmenikah").toString().trim();
                String norm = param.get("norm").toString().trim();
                String faskesbpjs = param.get("faskesbpjs").toString().trim();
                String stkawin = param.get("stkawin").toString().trim();
                String goldarah = param.get("goldarah").toString().trim();
                String rhesus = param.get("rhesus").toString().trim();
                String agama = param.get("agama").toString().trim();
                String kelamin = param.get("kelamin").toString().trim();
                if (m_profile.SelectOne(npp).size() > 0) {
                    if (m_profile.simpanDataDiri(npp, nama, noktp, nonpwp, tmplahir, tgllahir, alamatskr, alamatktp, notelp, nohp, tglmenikah, norm, faskesbpjs, stkawin, goldarah, rhesus, agama, kelamin)) {
                        obj.put("msg", "Sukses");
                        result.add(obj);
                    }
                }
            } else if (param.containsKey("kerja")) {
                String npp = param.get("npp").toString().trim();
                String lokasi = param.get("lokasi").toString().trim();
                String jabatan = param.get("jabatan").toString().trim();
                String tglmulai = param.get("tglmulai").toString().trim();
                if (m_profile.SelectOne(npp).size() > 0) {
                    if (m_profile.simpanDataKerja(npp, lokasi, jabatan, tglmulai)) {
                        obj.put("msg", "Sukses");
                        result.add(obj);
                    }
                }
            } else if (param.containsKey("keluarga")) {
                String npp = param.get("npp").toString().trim();
                String nmpasangan = param.get("nmpasangan").toString().trim();
                String noktppasangan = param.get("noktppasangan").toString().trim();
                String tmplahirpasangan = param.get("tmplahirpasangan").toString().trim();
                String tgllahirpasangan = param.get("tgllahirpasangan").toString().trim();
                String pekerjaanpasangan = param.get("pekerjaanpasangan").toString().trim();
                String jabatanpasangan = param.get("jabatanpasangan").toString().trim();
                String nmperusahaanpasangan = param.get("nmperusahaanpasangan").toString().trim();
                String alamatperusahaanpasangan = param.get("alamatperusahaanpasangan").toString().trim();
                String nmayahkandung = param.get("nmayahkandung").toString().trim();
                String noktpayahkandung = param.get("noktpayahkandung").toString().trim();
                String nmibukandung = param.get("nmibukandung").toString().trim();
                String noktpibukandung = param.get("noktpibukandung").toString().trim();
                String nmayahmertua = param.get("nmayahmertua").toString().trim();
                String noktpayahmertua = param.get("noktpayahmertua").toString().trim();
                String nmibumertua = param.get("nmibumertua").toString().trim();
                String noktpibumertua = param.get("noktpibumertua").toString().trim();
                String tmplahirayahkandung = param.get("tmplahirayahkandung").toString().trim();
                String tgllahirayahkandung = param.get("tgllahirayahkandung").toString().trim();
                String tmplahiribukandung = param.get("tmplahiribukandung").toString().trim();
                String tgllahiribukandung = param.get("tgllahiribukandung").toString().trim();
                String tmplahirayahmertua = param.get("tmplahirayahmertua").toString().trim();
                String tgllahirayahmertua = param.get("tgllahirayahmertua").toString().trim();
                String tmplahiribumertua = param.get("tmplahiribumertua").toString().trim();
                String tgllahiribumertua = param.get("tgllahiribumertua").toString().trim();
                if (m_profile.SelectOne(npp).size() > 0) {
                    if (m_profile.simpanDataKeluarga(npp, nmpasangan, noktppasangan, tmplahirpasangan, tgllahirpasangan, pekerjaanpasangan, jabatanpasangan, nmperusahaanpasangan, alamatperusahaanpasangan, nmayahkandung, noktpayahkandung, nmibukandung, noktpibukandung, nmayahmertua, noktpayahmertua, nmibumertua, noktpibumertua, tmplahirayahkandung, tgllahirayahkandung, tmplahiribukandung, tgllahiribukandung, tmplahirayahmertua, tgllahirayahmertua, tmplahiribumertua, tgllahiribumertua)) {
                        obj.put("msg", "Sukses");
                        result.add(obj);
                    }
                }
            } else if (param.containsKey("anak")) {
                String npp = param.get("npp").toString().trim();
                String counter = param.get("counter").toString().trim();
                String noktp = param.get("noktp").toString().trim();
                String nama = param.get("nama").toString().trim();
                String tmplahir = param.get("tmplahir").toString().trim();
                String tgllahir = param.get("tgllahir").toString().trim();
                String norm = param.get("norm").toString().trim();
                String isdelete = param.get("isdelete").toString().trim();
                String kdseks = param.get("kdseks").toString().trim();
                if (m_profile.simpanDataAnak(npp, counter, noktp, nama, tmplahir, tgllahir, norm, isdelete, kdseks)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            } else if (param.containsKey("delete")) {
                String url = param.get("url").toString().trim();
                String npp = param.get("npp").toString().trim();
                String counter = param.get("counter").toString().trim();
                String delete = param.get("delete").toString().trim();

                if (m_profile.deleteDokumen(counter, npp, delete)) {

                    File storeFile = new File(url);
                    storeFile.delete();

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

    public static void copyFile(String from, String to) throws IOException {
        Path src = Paths.get(from);
        Path dest = Paths.get(to);
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyFileToDirectoryUsingApache(File from, File to) throws IOException {
        FileUtils.copyFileToDirectory(from, to, true);
    }

    public static void copyFileUsingApache(File from, File to) throws IOException {
        FileUtils.copyFile(from, to);
    }

    public static void copyFromURLUsingApache(URL from, File to) throws IOException {
        FileUtils.copyURLToFile(from, to);
    }

}


