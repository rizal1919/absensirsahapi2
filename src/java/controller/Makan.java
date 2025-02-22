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
import model.helper;
import model.makan;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author fadhil
 */
public class Makan extends HttpServlet {

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
        makan m_makan = new makan();

        try {
            if (param.containsKey("nopegawai")) {
                String nopegawai = request.getParameter("nopegawai").trim();
                result = m_makan.selectOneMakan(nopegawai);
            } else if (param.containsKey("npp") && param.containsKey("rating")) {
                String nopegawai = request.getParameter("npp").trim();
                result = m_makan.selectOneRating(nopegawai);
            } else if (param.containsKey("menu") && param.containsKey("tanggal")) {
                String tanggal = request.getParameter("tanggal").trim();
                result = m_makan.selectAllMenu(tanggal);
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
        JSONObject param = getJSONBody(request);
        makan m_makan = new makan();

        try {
            if (param.containsKey("nopegawai")) {
                String nopegawai = param.get("nopegawai").toString();
                result = m_makan.insertMakan(nopegawai);
            } else if (param.containsKey("counterMkn") && param.containsKey("npp") && param.containsKey("rating")) {
                String counterMkn = param.get("counterMkn").toString();
                String nopegawai = param.get("npp").toString();
                Double rating = ParseDouble(param.get("rating").toString());
                result = m_makan.updateRatingMakan(counterMkn, nopegawai, rating);
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    double ParseDouble(String strNumber) {
        if (strNumber != null && strNumber.length() > 0) {
            try {
                strNumber = strNumber.substring(0, strNumber.indexOf('.'));
                strNumber = strNumber.replaceAll("[^0-9]", "");
                return Double.parseDouble(strNumber);
            } catch (Exception e) {
                return -1;   // or some value to mark this field is wrong. or make a function validates field first ...
            }
        } else {
            return 0;
        }
    }

}
