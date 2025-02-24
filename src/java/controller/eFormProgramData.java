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
import model.Eformprogramdata;
import model.helper;
import model.stdfield;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author aldi
 */
public class eFormProgramData extends HttpServlet {

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
        
        String data = "[]";
        
//        String valProgramBaru = request.getParameter("val_programbaru").trim();
//        String valJenisPermintaan = request.getParameter("val_jenispermintaan").trim();
//        String valNamaProgram = request.getParameter("val_namaprogram").trim();
//        String valKetNamaprogram = request.getParameter("val_ketnamaprogram").trim();
//        String valKeterangan = request.getParameter("val_keterangan").trim();
//        String valUser = request.getParameter("val_user").trim();
//        String valJabatan = request.getParameter("val_jabatan").trim();
//        String valKodeLokasi = request.getParameter("kdlokasi").trim();
//        String valKdUnit = request.getParameter("kdunit").trim();
//        String valKdDepartemen = request.getParameter("kddepartemen").trim();
//        
        //nomortelp m_Eformprogramdata = new nomortelp();
        Eformprogramdata m_Eformprogramdata = new Eformprogramdata();
        stdfield m_stdfield = new stdfield();

        try {
            if (param.containsKey("formBaruS")) {
                
                String valProgramBaru = request.getParameter("val_programbaru").trim();
                String valJenisPermintaan = request.getParameter("val_jenispermintaan").trim();
                String valNamaProgram = request.getParameter("val_namaprogram").trim();
                String valKetNamaprogram = request.getParameter("val_ketnamaprogram").trim();
                String valKeterangan = request.getParameter("val_keterangan").trim();
                String valUser = request.getParameter("val_user").trim();
                String valJabatan = request.getParameter("val_jabatan").trim();
                String valKodeLokasi = request.getParameter("kdlokasi").trim();
                String valKdUnit = request.getParameter("kdunit").trim();
                String valKdDepartemen = request.getParameter("kddepartemen").trim();
        
//                m_Eformprogramdata.setKategoriBaru(valProgramBaru);
//                m_Eformprogramdata.setJenisProgram(valJenisPermintaan);
//                m_Eformprogramdata.setNamaProgram(valNamaProgram);
//                m_Eformprogramdata.setKetNamaProgram(valKetNamaprogram);
//                m_Eformprogramdata.setKeteranganForm(valKeterangan);
//                m_Eformprogramdata.setUserMinta(valUser);
//                m_Eformprogramdata.setJabatan(valJabatan);
//                m_Eformprogramdata.setKdlock(valKodeLokasi);
//                m_Eformprogramdata.setKdUnit(valKdUnit);
//                m_Eformprogramdata.setKdDept(valKdDepartemen);
                
                if (m_Eformprogramdata.insertFormProgramData(valProgramBaru, valJenisPermintaan, valNamaProgram, valKetNamaprogram, valKeterangan, valUser, valJabatan, valKodeLokasi, valKdUnit, valKdDepartemen)) {
                    obj.put("msg", "Sukses");
                    result.add(obj);
                }
            } else if (param.containsKey("lokasi")) {
//                String kdjenis = request.getParameter("kdjenis").trim();
                result = m_Eformprogramdata.selectLokasiKerja();
            } else if (param.containsKey("telpSaringUrut") && param.containsKey("saring") && param.containsKey("cari")) {
                String saring = request.getParameter("saring").trim();
                String cari = request.getParameter("cari").trim();
                result = m_Eformprogramdata.openTelpSaringUrutCari(saring, cari);
            } else if (param.containsKey("kdfield")) {
                String kdfield = request.getParameter("kdfield").trim();
                result = m_stdfield.selectStdField(kdfield);
            } else if (param.containsKey("cuti") && param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_Eformprogramdata.selectOneCuti(nopegawai);
            } else if (param.containsKey("tglcuti") && param.containsKey("npp")) {
                String nopegawai = request.getParameter("npp").trim();
                result = m_Eformprogramdata.selectTglCuti(nopegawai);
            } else if (param.containsKey("tglmerah")) {
                result = m_Eformprogramdata.viewTglMerah();
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
//        Map param = request.getParameterMap();
        JSONObject param = getJSONBody(request);
        Eformprogramdata m_Eformprogramdata = new Eformprogramdata();

        try {
            if (param.containsKey("formBaru")) {
                
                String npp = param.get("npp").toString().trim();
                String valProgramBaru = param.get("val_programbaru").toString().trim();
                String valJenisPermintaan = param.get("val_jenispermintaan").toString().trim();
                String valNamaProgram = param.get("val_namaprogram").toString().trim();
                String valKetNamaprogram = param.get("val_ketnamaprogram").toString().trim();
                String valKeterangan = param.get("val_keterangan").toString().trim();
//                String valUser = "edp.aldi";
//                String valJabatan = "edp";
                String valKodeLokasi = param.get("kdlokasi").toString().trim();
                String valKdUnit = param.get("kdunit").toString().trim();
                String valKdDepartemen = param.get("kddepartemen").toString().trim();
        
//                m_Eformprogramdata.setKategoriBaru(valProgramBaru);
//                m_Eformprogramdata.setJenisProgram(valJenisPermintaan);
//                m_Eformprogramdata.setNamaProgram(valNamaProgram);
//                m_Eformprogramdata.setKetNamaProgram(valKetNamaprogram);
//                m_Eformprogramdata.setKeteranganForm(valKeterangan);
//                m_Eformprogramdata.setUserMinta(valUser);
//                m_Eformprogramdata.setJabatan(valJabatan);
//                m_Eformprogramdata.setKdlock(valKodeLokasi);
//                m_Eformprogramdata.setKdUnit(valKdUnit);
//                m_Eformprogramdata.setKdDept(valKdDepartemen);
                if (m_Eformprogramdata.selec_user_eform(npp)){
                    String valUser = m_Eformprogramdata.getNama();
                    String valJabatan = m_Eformprogramdata.getJabatan();
                    if (m_Eformprogramdata.insertFormProgramData(valProgramBaru, valJenisPermintaan, valNamaProgram, valKetNamaprogram, valKeterangan, valUser, valJabatan, valKodeLokasi, valKdUnit, valKdDepartemen)) {
                        obj.put("msg", "Sukses");
                        result.add(obj);
                    }
                }
            }
            if (param.containsKey("tanggal") && param.containsKey("nopegawai") && param.containsKey("jnscuti") && param.containsKey("khusus")) {
                String tanggal = param.get("tanggal").toString();
                String nopegawai = param.get("nopegawai").toString();
                String jnscuti = param.get("jnscuti").toString();
                String khusus = param.get("khusus").toString();
                //result = m_Eformprogramdata.insertAbsensi(nopegawai, tanggal, jnscuti, khusus);
            } else if (param.containsKey("counterAbs") && param.containsKey("nopegawai") && param.containsKey("update")) {
                String counter = param.get("counterAbs").toString();
                String nopegawai = param.get("nopegawai").toString();
//                if (m_Eformprogramdata.updateValidAbsensi(counter, nopegawai)) {
//                    obj.put("msg", "Sukses");
//                    result.add(obj);
//                }
            } else if (param.containsKey("counterAbs") && param.containsKey("nopegawai") && param.containsKey("delete")) {
                String counter = param.get("counterAbs").toString();
                String nopegawai = param.get("nopegawai").toString();
//                if (m_Eformprogramdata.deleteAbsensi(counter, nopegawai)) {
//                    obj.put("msg", "Sukses");
//                    result.add(obj);
//                }
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

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                strNumber = strNumber.substring(0, strNumber.indexOf('.'));
                strNumber = strNumber.replaceAll("[^0-9]", "");
                return Double.parseDouble(strNumber);
            } catch (NumberFormatException e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }

}

