/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

/**
 *
 * @author fadhil
 */
public class helper {

    public static boolean IS_ONLINE = true;
//    public static boolean IS_ONLINE = false;

    public static JSONObject setErrorMessage(String message) {
        JSONObject result = new JSONObject();
        result.put("error", message);
        return result;
    }

    public static String getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());

    }

    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(new Date());
    }

    public static String getNowDate(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String imageToBase64(String filePath) {
        byte[] fileContent;
        try {
            fileContent = FileUtils.readFileToByteArray(new File(filePath));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            return encodedString;
        } catch (IOException ex) {
            Logger.getLogger(helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

    public static String getUrl(int port, String addr, String nopegawai) {

        String Url = "http://" + addr + ":" + port + "/apk_foto/" + nopegawai + "/";
//        String Url = "http://" + addr + "/apk_foto/" + nopegawai + "/";
        if (IS_ONLINE) {
//            Url = "http://mobileapp.adihusada.co.id" + "/apk_foto/" + nopegawai + "/";
//           Url = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_foto/" + nopegawai + "/";
            Url = "http://mobileapp.adihusada.com" + "/apk_foto/" + nopegawai + "/";
//            Url = "http://mobileapp.adihusada.net" + "/apk_foto/" + nopegawai + "/";

        }

        return Url;

    }

    public static String getBannerUrl(int port, String addr) {

        String Url = "http://" + addr + ":" + port + "/apk_banner/";
//        String Url = "http://" + addr +  "/apk_banner/";

        if (IS_ONLINE) {
            //Url = "http://mobileapp.adihusada.co.id" + "/apk_banner/";
            Url = "http://mobileapp.adihusada.com" + "/apk_banner/";
//            Url = "http://mobileapp.adihusada.net" + "/apk_banner/";
//            Url = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_banner/";
        }

        return Url;

    }

    public static String getPromoUrl(int port, String addr) {

        String Url = "http://" + addr + ":" + port + "/apk_promo/";
//        String Url = "http://" + addr + "/apk_promo/";

        if (IS_ONLINE) {
            //Url = "http://mobileapp.adihusada.co.id" + "/apk_promo/";
            Url = "http://mobileapp.adihusada.com" + "/apk_promo/";
//              Url = "http://mobileapp.adihusada.net" + "/apk_promo/";
//            Url = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_promo/";
        }

        return Url;

    }

    public static String getMenuUrl(int port, String addr) {

        String Url = "http://" + addr + ":" + port + "/apk_menu/";
//        String Url = "http://" + addr +  "/apk_menu/";

        if (IS_ONLINE) {
            //Url = "http://mobileapp.adihusada.co.id" + "/apk_menu/";\
            Url = "http://mobileapp.adihusada.com" + "/apk_menu/";
//            Url = "http://mobileapp.adihusada.net" + "/apk_menu/";
//            Url = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_menu/";
        }

        return Url;

    }

    public static String getVideoUrl(int port, String addr) {

        String Url = "http://" + addr + ":" + port + "/apk_video/";
//        String Url = "http://" + addr + "/apk_video/";

        if (IS_ONLINE) {
            //Url = "http://mobileapp.adihusada.co.id" + "/apk_video/";
//            Url = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_video/";
            Url = "http://mobileapp.adihusada.com" + "/apk_video/";
//              Url = "http://mobileapp.adihusada.net" + "/apk_video/";
        }

        return Url;

    }

    public static String getInfoUrl(int port, String addr) {

        String Url = "http://" + addr + ":" + port + "/apk_video/";
//        String Url = "http://" + addr +  "/apk_video/";

        if (IS_ONLINE) {
            //Url = "http://mobileapp.adihusada.co.id" + "/apk_video/";
//            Url = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_video/";
            Url = "http://mobileapp.adihusada.com" + "/apk_video/";
//            Url = "http://mobileapp.adihusada.net" + "/apk_video/";

        }

        return Url;

    }

    public static String getLampiranUrl(int port, String addr, String nopegawai, String filename) {

        String imageUrl = addr + ":" + port + "/apk_lampiran/" + nopegawai + "/" + filename;
//        String imageUrl = addr +  "/apk_lampiran/" + nopegawai + "/" + filename;

        if (IS_ONLINE) {
            //imageUrl = "mobileapp.adihusada.co.id" + "/apk_lampiran/" + nopegawai + "/" + filename;
//            imageUrl = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_lampiran/" + nopegawai + "/" + filename;
            imageUrl = "http://mobileapp.adihusada.com" + "/apk_lampiran/" + nopegawai + "/" + filename;
//            imageUrl = "http://mobileapp.adihusada.net" + "/apk_lampiran/" + nopegawai + "/" + filename;
        }

        return imageUrl;

    }

    public static String getImageUrl(int port, String addr, String nopegawai, String filename) {

        String imageUrl = "http://" + addr + ":" + port + "/apk_foto/" + nopegawai + "/" + filename;
//        String imageUrl = "http://" + addr +  "/apk_foto/" + nopegawai + "/" + filename;

        if (IS_ONLINE) {
            //imageUrl = "http://mobileapp.adihusada.co.id" + "/apk_foto/" + nopegawai + "/" + filename;
//            imageUrl = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_foto/" + nopegawai + "/" + filename;
            imageUrl = "http://mobileapp.adihusada.com" + "/apk_foto/" + nopegawai + "/" + filename;
//            imageUrl = "http://mobileapp.adihusada.net" + "/apk_foto/" + nopegawai + "/" + filename;
        }

        return imageUrl;

    }

    public static String getImgLapUrl(int port, String addr, String nopegawai, String filename) {

        String imageUrl = "http://" + addr + ":" + port + "/apk_foto/" + nopegawai + "/" + filename;
//        String imageUrl = "http://" + addr +  "/apk_foto/" + nopegawai + "/" + filename;

        if (IS_ONLINE) {
            //imageUrl = "http://mobileapp.adihusada.co.id" + "/apk_foto/" + nopegawai + "/" + filename;
//            imageUrl = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_foto/" + nopegawai + "/" + filename;
            imageUrl = "http://mobileapp.adihusada.com" + "/apk_foto/" + nopegawai + "/" + filename;
//            imageUrl = "http://mobileapp.adihusada.net" + "/apk_foto/" + nopegawai + "/" + filename;
        }

//        File upOne = helper.getUpOne();
//        String UPLOAD_DIRECTORY = upOne.getAbsolutePath() + File.separator + "webapps\\ROOT" + File.separator + "apk_foto";
//        String filedir = UPLOAD_DIRECTORY + File.separator + nopegawai + File.separator;
//
//        if (!new File(filedir, filename).isFile()) {
//            imageUrl = "";
//        }
//        try {
//            URL obj1 = new URL(imageUrl);
//            try {
//                HttpURLConnection.setFollowRedirects(false);
//                HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection();
//                int r_code = con1.getResponseCode();
//                if (r_code != HttpURLConnection.HTTP_OK) {
//                    imageUrl = "";
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(helper.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(helper.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return imageUrl;

    }

    public static String getFileUrl(int port, String addr, String filename) {

        String fileUrl = "http://" + addr + ":" + port + "/apk_file/" + filename;
//        String fileUrl = "http://" + addr +  "/apk_file/" + filename;

        if (IS_ONLINE) {
            //fileUrl = "http://mobileapp.adihusada.co.id" + "/apk_file/" + filename;
            fileUrl = "http://mobileapp.adihusada.com" + "/apk_file/" + filename;
//            fileUrl = "http://mobileapp.adihusada.net" + "/apk_file/" + filename;
//            fileUrl = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_file/" + filename;
        }

        return fileUrl;

    }

    public static String getSlipUrl(int port, String addr, String nopegawai, String filename) {

        String slipUrl = "http://" + addr + ":" + port + "/apk_file/" + nopegawai + "/" + filename;
//        String slipUrl = "http://" + addr +  "/apk_file/" + nopegawai + "/" + filename;

        if (IS_ONLINE) {
            //slipUrl = "http://mobileapp.adihusada.co.id" + "/apk_file/" + nopegawai + "/" + filename;
            slipUrl = "http://mobileapp.adihusada.com" + "/apk_file/" + nopegawai + "/" + filename;
//            slipUrl = "http://mobileapp.adihusada.net" + "/apk_file/" + nopegawai + "/" + filename;
//            slipUrl = "http://100.100.100.114:5758/absensirsahapi/" + "/apk_file/" + nopegawai + "/" + filename;
        }

//        File file = new File(slipUrl);
//
//        if (!file.exists()) {
//            slipUrl = "";
//        }
//        try {
//            URL obj1 = new URL(slipUrl);
//            try {
//                HttpURLConnection con1 = (HttpURLConnection) obj1.openConnection();
//                int r_code = con1.getResponseCode();
//                if (r_code != HttpURLConnection.HTTP_OK) {
//                    slipUrl = "";
//                }
//            } catch (IOException ex) {
//                Logger.getLogger(helper.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(helper.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return slipUrl;

    }

    public static File getUpOne() {
        File upOne;
        //UNTUK NETBEANS
        upOne = new File(System.getProperty("user.dir")).getParentFile();
        if (IS_ONLINE) {
            //LIVE atau CEK pakai yang ini...
            upOne = new File(System.getProperty("user.dir"));
        }
        return upOne;
    }
}
