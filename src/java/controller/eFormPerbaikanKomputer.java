package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import controller.KeyFinder;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Global;
import model.formPerbaikanKomputer;
import model.getJapriResources;
import model.helper;
import model.getJapriResources;
import model.nomortelp;
import model.stdfield;
import model.usercredentials;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author rico
 */
@WebServlet(name = "eFormPerbaikanKomputer", urlPatterns = {"/eFormPerbaikanKomputer"})
public class eFormPerbaikanKomputer extends HttpServlet {

    private static final String SAVE_DIR = "Files";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        System.out.println(request);
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + File.separator + SAVE_DIR;
        HttpSession session = request.getSession(true);
        String aksi = request.getParameter("actionForm");
        String data = "[]";
        String notice = "[]";
        String message = "[]";
        String detail = "[]";
        String struktural = "";
        String departemnt = "[]";
        String npp = request.getParameter("npp");
        String Table = "[]";
        String user = request.getParameter("uname");
        String prio = request.getParameter("prioritas");
        String url = "";
        String url2 = "";
        boolean redirectTO = false;

//        if (session == null || session.getAttribute("uname") == null) {
//            redirectTO = false;
//        } else {
//            user = (String) session.getAttribute("uname");
//        }
        formPerbaikanKomputer eform    = new formPerbaikanKomputer();
        
        switch (aksi) {
            case "formBaru":
                data = request.getParameter("data");
                formPerbaikanKomputer pk = new formPerbaikanKomputer();
                pk.setLokasiKerja(getLokasiKerja(data));
                pk.setDepartemen(getDepartemen(data));
                pk.setUnit(getUnitKerja(data));
                pk.setKepalaUnit(getKepalaUnit(data));
                pk.setApproveUser(Boolean.TRUE);
                pk.setProbleam(getMasalah(data));
                pk.setUser(user.trim());
                pk.setDevice(getAlat(data));
                pk.insertFormPerbaikanKomputer();
                data = pk.view_perbaikanKomputer_after_insert();
//                System.out.println("data :" + data);
                Table = pk.view_all_perbaikan_komputer_by_user();
                break;

            case "terimaPetugas":
                data = request.getParameter("data");
                formPerbaikanKomputer terimaPetugas = new formPerbaikanKomputer();
                terimaPetugas.setNoForm(getNoFormPK(data));
                terimaPetugas.setUser(user);
                terimaPetugas.setPrioritas(getPrioritas(data));

                terimaPetugas.InsertPetugasTerimaPerbaikanKomputerNew();
                data = terimaPetugas.view_perbaikanKomputer_after_insert_bynoform();
                Table = terimaPetugas.view_all_perbaikan_komputer_by_user();
                break;
            case "petugasSelesai":
                //<editor-fold defaultstate="collapsed" desc="...">
                data = request.getParameter("data");
                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(data);

                    JSONArray array = (JSONArray) obj;
                    String tmp = array.get(1).toString();
                    JSONObject obj2 = (JSONObject) parser.parse(tmp);
                    obj2 = (JSONObject) parser.parse(tmp);
                    tmp = obj2.get("cpu").toString();

                    String cpuHeader = getCPUHeader(data);
                    String noform = getNoFormPK(data);

                    formPerbaikanKomputer dis3 = new formPerbaikanKomputer();
                    dis3.select_jumlahcontent(noform);
                    if (!"0".equals(dis3.getJumlahContent())) {
                        formPerbaikanKomputer pro = getProcesor(tmp.substring(1, tmp.length() - 1));
                        if (pro.isValidasi() == true) {
                            pro.setUser(user);
                            pro.setHnama(cpuHeader.trim());
                            pro.setNoForm(noform.trim());
                            pro.InsertPetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer mth = getMotherBoard(tmp.substring(1, tmp.length() - 1));
                        if (mth.isValidasi() == true) {
                            mth.setUser(user);
                            mth.setHnama(cpuHeader.trim());
                            mth.setNoForm(noform.trim());
                            mth.InsertPetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer mem = getMemori(tmp.substring(1, tmp.length() - 1));
                        if (mem.isValidasi() == true) {
                            mem.setUser(user);
                            mem.setHnama(cpuHeader.trim());
                            mem.setNoForm(noform.trim());
                            mem.InsertPetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer hdr = getHardisk(tmp.substring(1, tmp.length() - 1));
                        if (hdr.isValidasi() == true) {
                            hdr.setUser(user);
                            hdr.setHnama(cpuHeader.trim());
                            hdr.setNoForm(noform.trim());
                            hdr.InsertPetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer vga = getVga(tmp.substring(1, tmp.length() - 1));
                        if (vga.isValidasi() == true) {
                            vga.setUser(user);
                            vga.setHnama(cpuHeader.trim());
                            vga.setNoForm(noform.trim());
                            vga.InsertPetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer sc = getSoundCard(tmp.substring(1, tmp.length() - 1));
                        if (sc.isValidasi() == true) {
                            sc.setUser(user);
                            sc.setHnama(cpuHeader.trim());
                            sc.setNoForm(noform.trim());
                            sc.InsertPetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer net = getNetwork(tmp.substring(1, tmp.length() - 1));
                        if (net.isValidasi() == true) {
                            net.setUser(user);
                            net.setHnama(cpuHeader.trim());
                            net.setNoForm(noform.trim());
                            net.InsertPetugasSelesaiPerbaikanKomputer();
                        }

                        formPerbaikanKomputer pwrsply = getPowerSupply(tmp.substring(1, tmp.length() - 1));
                        if (pwrsply.isValidasi() == true) {
                            pwrsply.setUser(user);
                            pwrsply.setHnama(cpuHeader.trim());
                            pwrsply.setNoForm(noform.trim());
                            pwrsply.InsertPetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp2 = array.get(3).toString();
                        obj2 = (JSONObject) parser.parse(tmp2);
                        tmp2 = obj2.get("keyboard").toString();
                        formPerbaikanKomputer key = getKeyBoard(tmp2.substring(1, tmp2.length() - 1));
                        if (key.isValidasi() == true) {
                            key.setUser(user);
                            key.setHnama("");
                            key.setNoForm(noform.trim());
                            key.InsertPetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp3 = array.get(4).toString();
                        obj2 = (JSONObject) parser.parse(tmp3);
                        tmp3 = obj2.get("mouse").toString();
                        formPerbaikanKomputer mou = getMouse(tmp3.substring(1, tmp3.length() - 1));
                        if (mou.isValidasi() == true) {
                            mou.setUser(user);
                            mou.setHnama("");
                            mou.setNoForm(noform.trim());
                            mou.InsertPetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp4 = array.get(5).toString();
                        obj2 = (JSONObject) parser.parse(tmp4);
                        tmp4 = obj2.get("monitor").toString();
                        formPerbaikanKomputer mon = getMonitor(tmp4.substring(1, tmp4.length() - 1));
                        if (mon.isValidasi() == true) {
                            mon.setUser(user);
                            mon.setHnama("");
                            mon.setNoForm(noform.trim());
                            mon.InsertPetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp5 = array.get(6).toString();
                        obj2 = (JSONObject) parser.parse(tmp5);
                        tmp5 = obj2.get("printer").toString();
                        formPerbaikanKomputer pri = getPrinter(tmp5.substring(1, tmp5.length() - 1));
                        if (pri.isValidasi() == true) {
                            pri.setUser(user);
                            pri.setHnama("");
                            pri.setNoForm(noform.trim());
                            pri.InsertPetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp6 = array.get(7).toString();
                        obj2 = (JSONObject) parser.parse(tmp6);
                        tmp6 = obj2.get("speaker").toString();
                        formPerbaikanKomputer spea = getSpeaker(tmp6.substring(1, tmp6.length() - 1));
                        if (spea.isValidasi() == true) {
                            spea.setUser(user);
                            spea.setHnama("");
                            spea.setNoForm(noform.trim());
                            spea.InsertPetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp7 = array.get(8).toString();
                        obj2 = (JSONObject) parser.parse(tmp7);
                        tmp7 = obj2.get("UPS").toString();
//                    System.out.println("UPS :"+tmp7);
                        formPerbaikanKomputer ups = getUPS(tmp7.substring(1, tmp7.length() - 1));
                        if (ups.isValidasi() == true) {
                            ups.setUser(user);
                            ups.setHnama("");
                            ups.setNoForm(noform.trim());
                            ups.InsertPetugasSelesaiPerbaikanKomputer();
                        }
                        String tmp8 = array.get(9).toString();
                        obj2 = (JSONObject) parser.parse(tmp8);
                        tmp8 = obj2.get("Lain").toString();
                        formPerbaikanKomputer lain = getLain(tmp8.substring(1, tmp8.length() - 1));
                        if (lain.isValidasi() == true) {
                            lain.setUser(user);
                            lain.setHnama("");
                            lain.setNoForm(noform.trim());
                            lain.InsertPetugasSelesaiPerbaikanKomputer();
                        }
                    } else {

                        message = "silahkan simpan terlebih dahulu";
                    }

                    formPerbaikanKomputer sl = new formPerbaikanKomputer();
                    sl.setNoForm(noform);
                    sl.setUser(user);
                    data = sl.view_perbaikanKomputer_after_insert_bynoform();
                    Table = sl.view_all_perbaikan_komputer_by_user();

                } catch (ParseException ex) {
                    Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
                //</editor-fold>
            case "update":
                //<editor-fold defaultstate="collapsed" desc="...">
                data = request.getParameter("data");
                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(data);

                    JSONArray array = (JSONArray) obj;
                    String tmp = array.get(1).toString();
                    JSONObject obj2 = (JSONObject) parser.parse(tmp);
                    obj2 = (JSONObject) parser.parse(tmp);
                    tmp = obj2.get("cpu").toString();

                    String cpuHeader = getCPUHeader(data);
                    String noform = getNoFormPK(data);
                    formPerbaikanKomputer dis = new formPerbaikanKomputer();
                    dis.setNoForm(noform);
                    dis.setUser(user);
                    if (dis.updateStatusContent()) {
                        formPerbaikanKomputer proU = getProcesor(tmp.substring(1, tmp.length() - 1));
                        if (proU.isValidasi() == true) {
                            proU.setUser(user);
                            proU.setHnama(cpuHeader.trim());
                            proU.setNoForm(noform.trim());
                            proU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer mthU = getMotherBoard(tmp.substring(1, tmp.length() - 1));
                        if (mthU.isValidasi() == true) {
                            mthU.setUser(user);
                            mthU.setHnama(cpuHeader.trim());
                            mthU.setNoForm(noform.trim());
                            mthU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer memU = getMemori(tmp.substring(1, tmp.length() - 1));
                        if (memU.isValidasi() == true) {
                            memU.setUser(user);
                            memU.setHnama(cpuHeader.trim());
                            memU.setNoForm(noform.trim());
                            memU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer hdrU = getHardisk(tmp.substring(1, tmp.length() - 1));
                        if (hdrU.isValidasi() == true) {
                            hdrU.setUser(user);
                            hdrU.setHnama(cpuHeader.trim());
                            hdrU.setNoForm(noform.trim());
                            hdrU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer vgaU = getVga(tmp.substring(1, tmp.length() - 1));
                        if (vgaU.isValidasi() == true) {
                            vgaU.setUser(user);
                            vgaU.setHnama(cpuHeader.trim());
                            vgaU.setNoForm(noform.trim());
                            vgaU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer scU = getSoundCard(tmp.substring(1, tmp.length() - 1));
                        if (scU.isValidasi() == true) {
                            scU.setUser(user);
                            scU.setHnama(cpuHeader.trim());
                            scU.setNoForm(noform.trim());
                            scU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }
                        formPerbaikanKomputer netU = getNetwork(tmp.substring(1, tmp.length() - 1));
                        if (netU.isValidasi() == true) {
                            netU.setUser(user);
                            netU.setHnama(cpuHeader.trim());
                            netU.setNoForm(noform.trim());
                            netU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp2 = array.get(3).toString();
                        obj2 = (JSONObject) parser.parse(tmp2);
                        tmp2 = obj2.get("keyboard").toString();
                        formPerbaikanKomputer keyU = getKeyBoard(tmp2.substring(1, tmp2.length() - 1));
                        if (keyU.isValidasi() == true) {
                            keyU.setUser(user);
                            keyU.setHnama("");
                            keyU.setNoForm(noform.trim());
                            keyU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp3 = array.get(4).toString();
                        obj2 = (JSONObject) parser.parse(tmp3);
                        tmp3 = obj2.get("mouse").toString();
                        formPerbaikanKomputer mouU = getMouse(tmp3.substring(1, tmp3.length() - 1));
                        if (mouU.isValidasi() == true) {
                            mouU.setUser(user);
                            mouU.setHnama("");
                            mouU.setNoForm(noform.trim());
                            mouU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp4 = array.get(5).toString();
                        obj2 = (JSONObject) parser.parse(tmp4);
                        tmp4 = obj2.get("monitor").toString();
                        formPerbaikanKomputer monU = getMonitor(tmp4.substring(1, tmp4.length() - 1));
                        if (monU.isValidasi() == true) {
                            monU.setUser(user);
                            monU.setHnama("");
                            monU.setNoForm(noform.trim());
                            monU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp5 = array.get(6).toString();
                        obj2 = (JSONObject) parser.parse(tmp5);
                        tmp5 = obj2.get("printer").toString();
                        formPerbaikanKomputer priU = getPrinter(tmp5.substring(1, tmp5.length() - 1));
                        if (priU.isValidasi() == true) {
                            priU.setUser(user);
                            priU.setHnama("");
                            priU.setNoForm(noform.trim());
                            priU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp6 = array.get(7).toString();
                        obj2 = (JSONObject) parser.parse(tmp6);
                        tmp6 = obj2.get("speaker").toString();
                        formPerbaikanKomputer speaU = getSpeaker(tmp6.substring(1, tmp6.length() - 1));
                        if (speaU.isValidasi() == true) {
                            speaU.setUser(user);
                            speaU.setHnama("");
                            speaU.setNoForm(noform.trim());
                            speaU.UpdatePetugasSelesaiPerbaikanKomputer();
                        }

                        String tmp7 = array.get(8).toString();
                        obj2 = (JSONObject) parser.parse(tmp7);
                        tmp7 = obj2.get("UPS").toString();
                        formPerbaikanKomputer ups = getUPS(tmp7.substring(1, tmp7.length() - 1));
                        if (ups.isValidasi() == true) {
                            ups.setUser(user);
                            ups.setHnama("");
                            ups.setNoForm(noform.trim());
                            ups.UpdatePetugasSelesaiPerbaikanKomputer();
                        }
                        String tmp8 = array.get(9).toString();
                        obj2 = (JSONObject) parser.parse(tmp8);
                        tmp8 = obj2.get("Lain").toString();
                        formPerbaikanKomputer lain = getLain(tmp8.substring(1, tmp8.length() - 1));
                        if (lain.isValidasi() == true) {
                            lain.setUser(user);
                            lain.setHnama("");
                            lain.setNoForm(noform.trim());
                            lain.UpdatePetugasSelesaiPerbaikanKomputer();
                        }
                    }

                    formPerbaikanKomputer sl = new formPerbaikanKomputer();
                    sl.setNoForm(noform);
                    sl.setUser(user);
                    data = sl.view_perbaikanKomputer_after_insert_bynoform();
                    Table = sl.view_all_perbaikan_komputer_by_user();

                } catch (ParseException ex) {
                    Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                //</editor-fold>
            case "petugasSimpan":
                //<editor-fold defaultstate="collapsed" desc="...">
                data = request.getParameter("data");
                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(data);

                    JSONArray array = (JSONArray) obj;
                    String tmp = array.get(1).toString();
                    JSONObject obj2 = (JSONObject) parser.parse(tmp);
                    obj2 = (JSONObject) parser.parse(tmp);
                    tmp = obj2.get("cpu").toString();

                    String cpuHeader = getCPUHeader(data);
                    String noform = getNoFormPK(data);
                    formPerbaikanKomputer dis2 = new formPerbaikanKomputer();
                    dis2.select_jumlahcontent(noform);
                    if (!"0".equals(dis2.getJumlahContent())) {
                        dis2.setNoForm(noform);
                        dis2.setUser(user);
                        dis2.updateStatusContent();
                    }
                    formPerbaikanKomputer proS = getProcesor(tmp.substring(1, tmp.length() - 1));
                    if (proS.isValidasi() == true) {
                        proS.setUser(user);
                        proS.setHnama(cpuHeader.trim());
                        proS.setNoForm(noform.trim());
                        proS.PetugasSimpanPerbaikanKomputer();
                    }
                    formPerbaikanKomputer mthS = getMotherBoard(tmp.substring(1, tmp.length() - 1));
                    if (mthS.isValidasi() == true) {
                        mthS.setUser(user);
                        mthS.setHnama(cpuHeader.trim());
                        mthS.setNoForm(noform.trim());
                        mthS.PetugasSimpanPerbaikanKomputer();
                    }
                    formPerbaikanKomputer memS = getMemori(tmp.substring(1, tmp.length() - 1));
                    if (memS.isValidasi() == true) {
                        memS.setUser(user);
                        memS.setHnama(cpuHeader.trim());
                        memS.setNoForm(noform.trim());
                        memS.PetugasSimpanPerbaikanKomputer();
                    }
                    formPerbaikanKomputer hdrS = getHardisk(tmp.substring(1, tmp.length() - 1));
                    if (hdrS.isValidasi() == true) {
                        hdrS.setUser(user);
                        hdrS.setHnama(cpuHeader.trim());
                        hdrS.setNoForm(noform.trim());
                        hdrS.PetugasSimpanPerbaikanKomputer();
                    }
                    formPerbaikanKomputer vgaS = getVga(tmp.substring(1, tmp.length() - 1));
                    if (vgaS.isValidasi() == true) {
                        vgaS.setUser(user);
                        vgaS.setHnama(cpuHeader.trim());
                        vgaS.setNoForm(noform.trim());
                        vgaS.PetugasSimpanPerbaikanKomputer();
                    }
                    formPerbaikanKomputer scS = getSoundCard(tmp.substring(1, tmp.length() - 1));
                    if (scS.isValidasi() == true) {
                        scS.setUser(user);
                        scS.setHnama(cpuHeader.trim());
                        scS.setNoForm(noform.trim());
                        scS.PetugasSimpanPerbaikanKomputer();
                    }
                    formPerbaikanKomputer netS = getNetwork(tmp.substring(1, tmp.length() - 1));
                    if (netS.isValidasi() == true) {
                        netS.setUser(user);
                        netS.setHnama(cpuHeader.trim());
                        netS.setNoForm(noform.trim());
                        netS.PetugasSimpanPerbaikanKomputer();
                    }

                    formPerbaikanKomputer pwrsplyS = getPowerSupply(tmp.substring(1, tmp.length() - 1));
                    if (pwrsplyS.isValidasi() == true) {
                        pwrsplyS.setUser(user);
                        pwrsplyS.setHnama(cpuHeader.trim());
                        pwrsplyS.setNoForm(noform.trim());
                        pwrsplyS.PetugasSimpanPerbaikanKomputer();
                    }

                    String tmp2 = array.get(3).toString();
                    obj2 = (JSONObject) parser.parse(tmp2);
                    tmp2 = obj2.get("keyboard").toString();
                    formPerbaikanKomputer keyS = getKeyBoard(tmp2.substring(1, tmp2.length() - 1));
                    if (keyS.isValidasi() == true) {
                        keyS.setUser(user);
                        keyS.setHnama("");
                        keyS.setNoForm(noform.trim());
                        keyS.PetugasSimpanPerbaikanKomputer();
                    }

                    String tmp3 = array.get(4).toString();
                    obj2 = (JSONObject) parser.parse(tmp3);
                    tmp3 = obj2.get("mouse").toString();
                    formPerbaikanKomputer mouS = getMouse(tmp3.substring(1, tmp3.length() - 1));
                    if (mouS.isValidasi() == true) {
                        mouS.setUser(user);
                        mouS.setHnama("");
                        mouS.setNoForm(noform.trim());
                        mouS.PetugasSimpanPerbaikanKomputer();
                    }

                    String tmp4 = array.get(5).toString();
                    obj2 = (JSONObject) parser.parse(tmp4);
                    tmp4 = obj2.get("monitor").toString();
                    formPerbaikanKomputer monS = getMonitor(tmp4.substring(1, tmp4.length() - 1));
                    if (monS.isValidasi() == true) {
                        monS.setUser(user);
                        monS.setHnama("");
                        monS.setNoForm(noform.trim());
                        monS.PetugasSimpanPerbaikanKomputer();
                    }

                    String tmp5 = array.get(6).toString();
                    obj2 = (JSONObject) parser.parse(tmp5);
                    tmp5 = obj2.get("printer").toString();
                    formPerbaikanKomputer priS = getPrinter(tmp5.substring(1, tmp5.length() - 1));
                    if (priS.isValidasi() == true) {
                        priS.setUser(user);
                        priS.setHnama("");
                        priS.setNoForm(noform.trim());
                        priS.PetugasSimpanPerbaikanKomputer();
                    }

                    String tmp6 = array.get(7).toString();
                    obj2 = (JSONObject) parser.parse(tmp6);
                    tmp6 = obj2.get("speaker").toString();
                    formPerbaikanKomputer speaS = getSpeaker(tmp6.substring(1, tmp6.length() - 1));
                    if (speaS.isValidasi() == true) {
                        speaS.setUser(user);
                        speaS.setHnama("");
                        speaS.setNoForm(noform.trim());
                        speaS.PetugasSimpanPerbaikanKomputer();
                    }

                    String tmp7 = array.get(8).toString();
                    obj2 = (JSONObject) parser.parse(tmp7);
                    tmp7 = obj2.get("UPS").toString();
//                    System.out.println("UPS :"+tmp7);
                    formPerbaikanKomputer upsS = getUPS(tmp7.substring(1, tmp7.length() - 1));
                    if (upsS.isValidasi() == true) {
                        upsS.setUser(user);
                        upsS.setHnama("");
                        upsS.setNoForm(noform.trim());
                        upsS.PetugasSimpanPerbaikanKomputer();
                    }
                    String tmp8 = array.get(9).toString();
                    obj2 = (JSONObject) parser.parse(tmp8);
                    tmp8 = obj2.get("Lain").toString();
                    formPerbaikanKomputer lainS = getLain(tmp8.substring(1, tmp8.length() - 1));
                    if (lainS.isValidasi() == true) {
                        lainS.setUser(user);
                        lainS.setHnama("");
                        lainS.setNoForm(noform.trim());
                        lainS.PetugasSimpanPerbaikanKomputer();
                    }

                    formPerbaikanKomputer sl = new formPerbaikanKomputer();
                    sl.otomatisUserApproveEformPerbaikan();//!!update otomatis user yang tiga bulan terakhir tidak approve.!!
                    sl.setNoForm(noform);
                    sl.setUser(user);
                    data = sl.view_perbaikanKomputer_after_insert_bynoform();
                    Table = sl.view_all_perbaikan_komputer_by_user();

                } catch (ParseException ex) {
                    Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
                //</editor-fold>
            case "userApprove":
                //<editor-fold defaultstate="collapsed" desc="userApprove">
                data = request.getParameter("data");
                formPerbaikanKomputer up = new formPerbaikanKomputer();
                up.setNoForm(getNoFormPK(data));
                up.setUser(user);
                up.InsertUserApprovePerbaikanKomputer();
                data = up.view_perbaikanKomputer_after_insert_bynoform();
                Table = up.view_all_perbaikan_komputer_by_user();
                break;
                //</editor-fold>
            case "managerApprove":
                //<editor-fold defaultstate="collapsed" desc="manageApprove">
                data = request.getParameter("data");
                formPerbaikanKomputer mp = new formPerbaikanKomputer();
                mp.setNoForm(getNoFormPK(data));
                mp.setUser(user);

                mp.InsertManagerApprovePerbaikanKomputer();
                data = mp.view_perbaikanKomputer_after_insert_bynoform();
                Table = mp.view_all_perbaikan_komputer_by_user();

                break;
                //</editor-fold>
            case "bukaPerbaikanKomputer":
                //<editor-fold defaultstate="collapsed" desc="bukaPerbaikanKomputer">
                formPerbaikanKomputer bp    = new formPerbaikanKomputer();
                Global Day                  = new Global();
                bp.setUser(user);
                
                Table                       = bp.view_all_perbaikan_komputer_by_user();
                
//                struktural = bp.view_pegawaiStruktural();
                String listPengguna         = "listPegawaiStrukural" + Day.getYYYYmm() + ".json";
                String listPenggunaLama     = "listPegawaiStrukural" + Day.getLastMonth() + ".json";
                getListPegawaiStruktural(savePath, listPengguna, listPenggunaLama);
                
                departemnt                  = bp.view_perbaikanKomputer_userDepartemen();
//                System.out.println("struktural :" + struktural);
                String addr                 = request.getRequestURL().toString();
                String servelet             = request.getServletPath();
                url                         = addr.replace(servelet, "/") + session.getAttribute("listUnit");
                session.setAttribute("listPegawaiStruktural", SAVE_DIR + "/" + listPengguna);
                
                url2                        = addr.replace(servelet, "/") + session.getAttribute("listPegawaiStruktural");
                break;
                //</editor-fold>
            case "LoadDataForm":
                //<editor-fold defaultstate="collapsed" desc="LoadDataForm">
                data = request.getParameter("data");
                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(data);

                    formPerbaikanKomputer bpk = new formPerbaikanKomputer();
                    bpk.setNoForm(getNoFormPK(data));
                    data = bpk.view_perbaikanKomputer_after_insert_bynoform();
//                    System.out.println("data :" + data);
                    detail = bpk.view_perbaikanKomputer_view_detail_bynoform();
                    bpk.setUser(user);
                    Table = bpk.view_all_perbaikan_komputer_by_user();
                } catch (ParseException ex) {
                    Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                //</editor-fold>
            case "getNotif":
                //<editor-fold defaultstate="collapsed" desc="getNotif">
                formPerbaikanKomputer notif = new formPerbaikanKomputer();
                notif.setUser(user);
                notice = notif.notif_Perbaikan_komputer();
//                System.out.println("notice :" + notice);
                break;
                //</editor-fold>
            case "getPerbaikanKomputer":
                //<editor-fold defaultstate="collapsed" desc="getPerbaikanKomputer">
                formPerbaikanKomputer process    = new formPerbaikanKomputer();
                process.setUser(user);
                data = process.view_all_perbaikan_komputer_by_user_new();
                break;
                //</editor-fold>
            case "getBagian":
                //<editor-fold defaultstate="collapsed" desc="getBagian">
                formPerbaikanKomputer bagian    = new formPerbaikanKomputer();
                bagian.setNpp(npp);
                data = bagian.select_bagian();
                break;
                //</editor-fold>
            case "getKanit":
                //<editor-fold defaultstate="collapsed" desc="getKanit">
                formPerbaikanKomputer kanit    = new formPerbaikanKomputer();
                kanit.setNpp(npp);
                data = kanit.select_kanit();
                break;
                //</editor-fold>
            case "formBaruJapri":
                //<editor-fold defaultstate="collapsed" desc="formBaruJapri">
                data = request.getParameter("data");
                formPerbaikanKomputer jsonFromJapri = new formPerbaikanKomputer();
                jsonFromJapri.setUnit(getUnitKerja(data));
                jsonFromJapri.setKepalaUnit(getKepalaUnit(data));
                jsonFromJapri.setApproveUser(Boolean.TRUE);
                jsonFromJapri.setProbleam(getMasalah(data));
                jsonFromJapri.setDevice(getAlat(data));
                
                jsonFromJapri.setAcceptedBy(getAcceptedBy(data));
                jsonFromJapri.setDoneBy(getDoneBy(data));
                
                jsonFromJapri.setLokasiKerja(getLokasiKerja(data));
                jsonFromJapri.setDepartemen(getDepartemen(data));
                jsonFromJapri.setPrioritas(getPrioritas(data));
                jsonFromJapri.setIsDone(isDone(data));
                jsonFromJapri.setUser(user.trim());

                jsonFromJapri.insertFormPerbaikanKomputerByEdp();
                //jsonFromJapri.insertPetugasSimpanPerbaikanKomputerByEdp();
                data = jsonFromJapri.view_last_insertedform();
                //</editor-fold>
                break;
            case "formSimpanPerbaikanEDP":
                //<editor-fold defaultstate="collapsed" desc="formSimpanPerbaikanEDP">
                data = request.getParameter("data");
                formPerbaikanKomputer secInsert    = new formPerbaikanKomputer();
                secInsert.setNoForm(getNoFormPK(data));
                secInsert.setDevice(getAlat(data));
                secInsert.setKeterangan(getKeteranganPerbaikan(data));
                secInsert.setUser(user.trim());
                secInsert.setIsChecked(isChecked(data));
                secInsert.setIsChanged(isChanged(data));
                secInsert.insertPetugasSimpanPerbaikanKomputerByEdp();
                data = secInsert.view_last_insertedform();
                //</editor-fold>
                break;
            case "formSelesaiPerbaikanEDP":
                //<editor-fold defaultstate="collapsed" desc="formSelesaiPerbaikanEDP">
                data = request.getParameter("data");
                formPerbaikanKomputer selesai    = new formPerbaikanKomputer();
                selesai.setNoForm(getNoFormPK(data));
                selesai.setDevice(getAlat(data));
                selesai.setKeterangan(getKeteranganPerbaikan(data));
                selesai.setUser(user.trim());
                selesai.setIsChecked(isChecked(data));
                selesai.setIsChanged(isChanged(data));
                selesai.setPrioritas(getPrioritas(data));
                selesai.FormDiselesaikanByEDP();
                data = selesai.view_perbaikanKomputer_after_insert_bynoform();
                //</editor-fold>
                break;
            case "formSimpanPerbaikanEDPbeforeConfirmation":
                //<editor-fold defaultstate="collapsed" desc="formSimpanPerbaikanEDPbeforeConfirmation">
                data = request.getParameter("data");
                formPerbaikanKomputer saveData    = new formPerbaikanKomputer();
                saveData.setNoForm(getNoFormPK(data));
                saveData.setDevice(getAlat(data));
                saveData.setKeterangan(getKeteranganPerbaikan(data));
                saveData.setUser(user.trim());
                saveData.setIsChecked(isChecked(data));
                saveData.setIsChanged(isChanged(data));
                saveData.setPrioritas(getPrioritas(data));
                saveData.FormDisimpanByEDP();
                data = saveData.view_perbaikanKomputer_after_insert_bynoform();
                //</editor-fold>
                break;
            case "simpanPerbaikanDT":
                //<editor-fold defaultstate="collapsed" desc="simpanPerbaikanDT">
                data = request.getParameter("data");
                formPerbaikanKomputer save    = new formPerbaikanKomputer();
                save.setNoForm(getNoFormPK(data));
                save.setTipe(getTipeFromJson(data));
                save.setSubtipe(getSubTipeFromJson(data));
                save.setHnama(getHnama(data));
                save.setNama(getNama(data));
                save.setKeterangan(getKeteranganPerbaikan(data));
                save.setIsChecked(isChecked(data));
                save.setIsChanged(isChanged(data));
                save.setUser(user.trim());
                save.setPrioritas(getPrioritas(data));
                save.simpanPerbaikanDT();
                data = save.view_perbaikanKomputer_after_insert_bynoform();
                //</editor-fold>
                break;
            case "updateDataPerbaikan":
                //<editor-fold defaultstate="collapsed" desc="updateDataPerbaikan">
                data = request.getParameter("data");
                formPerbaikanKomputer update    = new formPerbaikanKomputer();
                update.setNoForm(getNoFormPK(data));
                update.setTipe(getTipeFromJson(data));
                update.setSubtipe(getSubTipeFromJson(data));
                update.setHnama(getHnama(data));
                update.setNama(getNama(data));
                update.setKeterangan(getKeteranganPerbaikan(data));
                update.setIsChecked(isChecked(data));
                update.setIsChanged(isChanged(data));
                update.setUser(user.trim());
                update.setKonfirmasi(getKonfirmasi(data));
                update.setPrioritas(getPrioritas(data));
                update.updateDataPerbaikan();
                data = update.view_perbaikanKomputer_after_insert_bynoform();
                //</editor-fold>
                break;
            case "insertDataPerbaikan":
                //<editor-fold defaultstate="collapsed" desc="insertDataPerbaikan">
                data = request.getParameter("data");
                formPerbaikanKomputer insert    = new formPerbaikanKomputer();
                insert.setNoForm(getNoFormPK(data));
                insert.setTipe(getTipeFromJson(data));
                insert.setSubtipe(getSubTipeFromJson(data));
                insert.setHnama(getHnama(data));
                insert.setNama(getNama(data));
                insert.setKeterangan(getKeteranganPerbaikan(data));
                insert.setIsChecked(isChecked(data));
                insert.setIsChanged(isChanged(data));
                insert.setUser(user.trim());
                insert.setKonfirmasi(getKonfirmasi(data));
                insert.setPrioritas(getPrioritas(data));
                insert.insertDataPerbaikan();
                data = insert.view_perbaikanKomputer_after_insert_bynoform();
                //</editor-fold>
                break;
            case "deleteDataPerbaikan":
                //<editor-fold defaultstate="collapsed" desc="deleteDataPerbaikan">
                data = request.getParameter("data");
                formPerbaikanKomputer delete    = new formPerbaikanKomputer();
                delete.setNoForm(getNoFormPK(data));
                delete.setTipe(getTipeFromJson(data));
                delete.setSubtipe(getSubTipeFromJson(data));
                delete.deleteDataPerbaikan();
                data = delete.view_perbaikanKomputer_after_insert_bynoform();
                //</editor-fold>
                break;
            case "selesaiUpdateDataPerbaikan":
                //<editor-fold defaultstate="collapsed" desc="selesaiUpdateDataPerbaikan">
                data = request.getParameter("data");
                formPerbaikanKomputer selesaiUpdate    = new formPerbaikanKomputer();
                selesaiUpdate.setNoForm(getNoFormPK(data));
                selesaiUpdate.setTipe(getTipeFromJson(data));
                selesaiUpdate.setSubtipe(getSubTipeFromJson(data));
                selesaiUpdate.setHnama(getHnama(data));
                selesaiUpdate.setNama(getNama(data));
                selesaiUpdate.setKeterangan(getKeteranganPerbaikan(data));
                selesaiUpdate.setIsChecked(isChecked(data));
                selesaiUpdate.setIsChanged(isChanged(data));
                selesaiUpdate.setUser(user.trim());
                selesaiUpdate.setKonfirmasi(getKonfirmasi(data));
                selesaiUpdate.setPrioritas(getPrioritas(data));
                selesaiUpdate.selesaiUpdateDataPerbaikan();
                data = selesaiUpdate.view_perbaikanKomputer_after_insert_bynoform();
                //</editor-fold>
                break;
            case "selesaiInsertDataPerbaikan":
                //<editor-fold defaultstate="collapsed" desc="selesaiInsertDataPerbaikan">
                data = request.getParameter("data");
                formPerbaikanKomputer selesaiInsert    = new formPerbaikanKomputer();
                selesaiInsert.setNoForm(getNoFormPK(data));
                selesaiInsert.setTipe(getTipeFromJson(data));
                selesaiInsert.setSubtipe(getSubTipeFromJson(data));
                selesaiInsert.setHnama(getHnama(data));
                selesaiInsert.setNama(getNama(data));
                selesaiInsert.setKeterangan(getKeteranganPerbaikan(data));
                selesaiInsert.setIsChecked(isChecked(data));
                selesaiInsert.setIsChanged(isChanged(data));
                selesaiInsert.setUser(user.trim());
                selesaiInsert.setKonfirmasi(getKonfirmasi(data));
                selesaiInsert.setPrioritas(getPrioritas(data));
                selesaiInsert.selesaiInsertDataPerbaikan();
                data = selesaiInsert.view_perbaikanKomputer_after_insert_bynoform();
                //</editor-fold>
                break;
            
            // tambahan di pengembangan part 2 eformPK
            case "formBaruJapri2":
                //<editor-fold defaultstate="collapsed" desc="formBaruJapri2">
                data = request.getParameter("data");
                formPerbaikanKomputer jsonFromJapri2 = new formPerbaikanKomputer();
                jsonFromJapri2.setUnit(getUnitKerja(data));
                jsonFromJapri2.setDepartemen(getDepartemen(data));
                jsonFromJapri2.setLokasiKerja(getLokasiKerja(data));
                
                jsonFromJapri2.setKepalaUnit(getKepalaUnit(data)); // null
                jsonFromJapri2.setProbleam(getMasalah(data)); // null
                jsonFromJapri2.setDevice(getAlat(data)); // null
               
                jsonFromJapri2.setApproveUser(Boolean.TRUE); // null
                
                jsonFromJapri2.setAcceptedBy(getAcceptedBy(data));
                jsonFromJapri2.setDoneBy(getDoneBy(data));
                
                jsonFromJapri2.setPrioritas(getPrioritas(data));
                jsonFromJapri2.setIsDone(isDone(data));
                jsonFromJapri2.setUser(user.trim());
                jsonFromJapri2.setUsrEdp(getUserEdp(data));
                jsonFromJapri2.setUsrNotify(getUserNotify(data));

                jsonFromJapri2.insertFormPerbaikanKomputerByEdp2();
                //jsonFromJapri2.insertPetugasSimpanPerbaikanKomputerByEdp();
                data = jsonFromJapri2.view_last_insertedform();
                //</editor-fold>
                break;
            case "getEFormDiajukan_ByEDP":
                //<editor-fold defaultstate="collapsed" desc="getEFormDiajukan_ByEDP">
                String dept = request.getParameter("dept");
                data = eform.view_submitted_eform_byedp(dept);
                //</editor-fold>
                break;
            case "rejectEFormDiajukan":
                //<editor-fold defaultstate="collapsed" desc="rejectEFormDiajukan">
                String noform = request.getParameter("noform");
                data = eform.rejectEFormDiajukan(noform, user);
                //</editor-fold>
                break;
            // end of part 2 eformPK
            default:
                redirectTO = false;
                break;
        }
        PrintWriter out = response.getWriter();
//        out.write("[" + data + "," + detail + ",[" + struktural + "]," + departemnt + "," + Table + "," + "[]]");
        out.write("[" + data + "," + detail + ",[" + struktural + "]," + departemnt + "," + Table + ",[{\"url\":\"" + url + "\"}]," + "[{\"url2\":\"" + url2 + "\"}],[" + notice + "],[{\"message\":\"" + message + "\"}],[]]");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        JSONArray result = new JSONArray();
        String data = "[]";
        Map param = request.getParameterMap();

        getJapriResources callResource   = new getJapriResources();
        formPerbaikanKomputer secResource = new formPerbaikanKomputer();

        try {
            if (param.containsKey("getDevice")) {
                result = callResource.selectDevices();
            }else if(param.containsKey("getTeamIT")){
                result = callResource.selectAllTeam();
            }else if(param.containsKey("getAuthorizedUsers")){
                result = callResource.selectAllAuthorizedUsers();
            }else if(param.containsKey("getStatistic")){
                String uname = request.getParameter("uname");
                result = callResource.getAllStatistics(uname);
            }else if(param.containsKey("isKanit")){
                String npp = request.getParameter("npp").trim();
                String username = request.getParameter("username").trim();
                String fullname = request.getParameter("fullname").trim();
                String lokasi = request.getParameter("lokasi").trim();
                String unit = request.getParameter("unit").trim();
                String dept = request.getParameter("dept").trim();
                result = callResource.isKanit(npp, username, fullname, lokasi, unit, dept);
            }else if(param.containsKey("getProcessData")){
                String noform = request.getParameter("noform").trim();
                result = callResource.getFormData(noform);
            }else if(param.containsKey("getPerbaikanKomputer")){
                String uname = request.getParameter("uname");
                result = callResource.getPerbaikanKomputer(uname);
            }
            
            else if(param.containsKey("getPerbaikanKomputer_Submitted")){
                String uname = request.getParameter("uname");
                result = callResource.getPerbaikanKomputerSubmitted(uname);
            }else if(param.containsKey("getMappingBagian")){
                result = callResource.getMappingBagian();
            }else if(param.containsKey("getMappingUser")){
                result = callResource.getMappingUser();
            }else if(param.containsKey("getUserDepartemen")){
                String kdBagian     = request.getParameter("kdBagian");
                result              = callResource.getUserDept(kdBagian);
            }else if(param.containsKey("getEFormDikonfirmasi")){
                String uname        = request.getParameter("uname");
                result              = callResource.getTotalConfirmationUser(uname);
            }else if(param.containsKey("getTokenUser")){
                String user_name    = request.getParameter("uname");
                String npp          = request.getParameter("npp");
                String fcm          = request.getParameter("fcm");
                result              = callResource.getTokenNotification(user_name, npp, fcm);
            }else if(param.containsKey("getTokenUserByNpp")){
                String npp          = request.getParameter("getTokenUserByNpp");
                result              = callResource.getTokenByNpp(npp);
            }else if(param.containsKey("getTokenUserByUsername")){
                String uname        = request.getParameter("getTokenUserByUsername");
                result              = callResource.getTokenByUsername(uname);
            }else if(param.containsKey("saveFirebaseToken")){
                String user_name    = request.getParameter("uname");
                String npp          = request.getParameter("npp");
                String fb_token     = request.getParameter("firebase_token");
                result              = callResource.getFirebaseTokenNotification(user_name, npp, fb_token);
            }else if(param.containsKey("getEformById")){
                String eform_id     = request.getParameter("getEformById");
                result              = secResource.view_by_noform(eform_id);
            }else if(param.containsKey("getAllTokenUsers")){
                result              = secResource.view_all_tokens();
            }
        } catch (Exception ex) {
            result = new JSONArray();
            result.add(helper.setErrorMessage(ex.getMessage()));
//            System.out.println(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        out.write(result.toJSONString());
//        System.out.println(result.toJSONString());
    }

    public String getCPUHeader(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("cpuHeader");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baru;

    }

    public formPerbaikanKomputer getProcesor(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtProc").toString();
            try {
                periksa = obj2.get("chkPriksaProc").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiProc").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetProc").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("1");
                proc.setSubtipe("1.1");

                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getMotherBoard(String jsonText)
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtMthbrd").toString();
            try {
                periksa = obj2.get("chkPriksaMthbrd").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiMthbrd").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetMthbrd").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("1");
                proc.setSubtipe("1.2");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getMemori(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtMem").toString();
            try {
                periksa = obj2.get("chkPriksaMem").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiMem").toString();
                if ("on".equals(penggantian)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetMem").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("1");
                proc.setSubtipe("1.3");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getHardisk(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtHrdisk").toString();
            try {
                periksa = obj2.get("chkPriksaHrdisk").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiHrdisk").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetHrdisk").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("1");
                proc.setSubtipe("1.4");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getVga(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtVga").toString();
            try {
                periksa = obj2.get("chkPriksaVga").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiVga").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetVga").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("1");
                proc.setSubtipe("1.5");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getSoundCard(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtSoundCard").toString();
            try {
                periksa = obj2.get("chkPriksaSoundCard").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiSoundCard").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetSoundCard").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("1");
                proc.setSubtipe("1.6");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getNetwork(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtLan").toString();
            try {
                periksa = obj2.get("chkPriksaLan").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiLan").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetLan").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("1");
                proc.setSubtipe("1.7");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getPowerSupply(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtPwrSup").toString();
            try {
                periksa = obj2.get("chkPriksaPwrSup").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiPwrSup").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetPwrSup").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("1");
                proc.setSubtipe("1.8");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getKeyBoard(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtkeybord").toString();
            try {
                periksa = obj2.get("chkPriksakeyboard").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantikeyboard").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetkeyboard").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("2");
                proc.setSubtipe("");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getMouse(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtMouse").toString();
            try {
                periksa = obj2.get("chkPriksaMouse").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiMouse").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetMouse").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("3");
                proc.setSubtipe("");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getMonitor(String jsonText)
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtMonitor").toString();
            try {
                periksa = obj2.get("chkPriksaMonitor").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiMonitor").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetMonitor").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("4");
                proc.setSubtipe("");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getPrinter(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtPrinter").toString();
            try {
                periksa = obj2.get("chkPriksaPrinter").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiPrinter").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetPrinter").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("5");
                proc.setSubtipe("");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getSpeaker(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtSpeaker").toString();
            try {
                periksa = obj2.get("chkPriksaSpeaker").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiSpeaker").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetSpeaker").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("6");
                proc.setSubtipe("");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getUPS(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtUPS").toString();
            try {
                periksa = obj2.get("chkPriksaUPS").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiUPS").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetUPS").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("7");
                proc.setSubtipe("");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public formPerbaikanKomputer getLain(String jsonText) 
    {
        formPerbaikanKomputer proc = new formPerbaikanKomputer();
        try {
            JSONParser parser = new JSONParser();
            String Nama = "", periksa = "", penggantian = "", ket = "";//array.get(0).toString();
            boolean periksaVal = false, penggantianVal = false;
            JSONObject obj2 = (JSONObject) parser.parse(jsonText);
            Nama = obj2.get("txtLain").toString();
            try {
                periksa = obj2.get("chkPriksaLain").toString();
                if ("on".equals(periksa)) {
                    periksaVal = true;
                }
            } catch (Exception e) {
                periksa = "";
            }
            try {
                penggantian = obj2.get("chkGantiLain").toString();
                if ("on".equals(penggantian)) {
                    penggantianVal = true;
                }
            } catch (Exception e) {
                penggantian = "";
            }

            ket = obj2.get("txtKetLain").toString();

            if (!"".equals(periksa.trim()) || !"".equals(Nama)) {
                proc.setNama(Nama);
                proc.setPeriksa(periksaVal);
                proc.setGanti(penggantianVal);
                proc.setKeterangan(ket);
                proc.setTipe("8");
                proc.setSubtipe("");
                proc.setValidasi(true);
            } else {
                proc.setValidasi(false);
            }

        } catch (ParseException ex) {
            Logger.getLogger(eFormPerbaikanKomputer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proc;

    }

    public String getUnitKerja(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtUnit");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baru;

    }

    public String getDepartemen(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtDepartemen");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baru;
    }

    public String getLokasiKerja(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtLokasiKerja");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baru;
    }

    public String getKepalaUnit(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtKplunit");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baru;

    }

    public String getTglPermintaan(String jsonText)
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtTglPermintaan");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baru;

    }
    
    public String getTglSelesai(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("tglSelesai");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baru;

    }
    
    public String getJamSelesai(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("jamSelesai");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baru;

    }

    public String getAlat(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtAlat");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return baru;

    }

    public String getMasalah(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtMasalah");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }

    public String getPrioritas(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("ddlPrioritas");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();
                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
//        System.out.println(baru);
        return baru;
    }
    
    public String getKonfirmasi(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("konfirmasi");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();
                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
//        System.out.println(baru);
        return baru;
    }

    public String getTanggalTerima(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtTglterima");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }

    public String getJamTerima(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtJamTerima");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }
    
    public String getDoneBy(String jsonText)
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("doneBy");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }
    
    public String getUserNotify(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("usrNotify");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }
    
    public String getUserEdp(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("usrEdp");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }
    
    public String getAcceptedBy(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("acceptedBy");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }
    
    public Boolean isChecked (String jsonText) 
    {
        Boolean baru = false;
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("isChecked");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = (Boolean) finder.getValue();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }
    
    public Boolean isChanged (String jsonText) 
    {
        Boolean baru = false;
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("isChanged");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = (Boolean) finder.getValue();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }
    
    public String isDone(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("isDone");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }

    public String getNoFormPK(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtNoform");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }
    
    public String getKeteranganPerbaikan(String jsonText)
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("txtPenyelesaian");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

        return baru;
    }
    
    public String getTipeFromJson(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("tipe");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
//        System.out.println(baru);
        return baru;

    }
    
    public String getHnama(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("hnama");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
//        System.out.println(baru);
        return baru;

    }
    
    public String getNama(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("nama");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
//        System.out.println(baru);
        return baru;

    }
    
    public String getSubTipeFromJson(String jsonText) 
    {
        String baru = "";
        JSONParser parser = new JSONParser();
        KeyFinder finder = new KeyFinder();
        finder.setMatchKey("subtipe");
        while (!finder.isEnd()) {
            try {
                parser.parse(jsonText, finder, true);
                if (finder.isFound()) {
                    finder.setFound(false);
                    baru = finder.getValue().toString();

                }
            } catch (ParseException ex) {
                Logger.getLogger(eFormPerbaikanKomputer.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
//        System.out.println(baru);
        return baru;

    }

    public void getListPegawaiStruktural(String savePath, String fileName, String lastFileName) 
    {
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        File flKemarin = new File(savePath + File.separator + lastFileName);
        if (flKemarin.exists()) {
            flKemarin.delete();
        }
        // Membuat File :list Diagnosa
        formPerbaikanKomputer diagFile = new formPerbaikanKomputer();

        File listpegStruktural = new File(savePath + File.separator + fileName);

        if (!listpegStruktural.exists()) {
//            System.out.println("Masuk");
            diagFile.view_pegawaiStruktural(listpegStruktural);

        }

    }
}
