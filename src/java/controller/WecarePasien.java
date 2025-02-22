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
import model.wecarePasien;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author matius
 */
public class WecarePasien extends HttpServlet {

  

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
        wecarePasien m_wecarePasien=new wecarePasien();
         try {
            if (param.containsKey("kdDokter") && param.containsKey("tgl")) {
                String kdDokter = request.getParameter("kdDokter").trim();
                String tgl = request.getParameter("tgl").trim();
                m_wecarePasien.setKdDokter(kdDokter);
                
                if (kdDokter.equals("admin")) {
                    result = m_wecarePasien.getListPasienAdmin(tgl);
                } else {
                    result = m_wecarePasien.getListPasien(tgl);
                }
               
            } else if (param.containsKey("uname") && param.containsKey("pwd")) {
                String uname = request.getParameter("uname").trim();
                String pwd = request.getParameter("pwd").trim();
                result = m_wecarePasien.loginDokter(uname, pwd);
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
}
