/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.PermissionModel;
import model.helper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rizal.Fathurrahman
 */
@WebServlet(name = "Permission", urlPatterns = {"/Permission"})
public class Permission extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Permission</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Permission at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        
//        // Get the JSON data sent from Android
//        String jsonData = request.getParameter("data");
//
//        try {
//            // Decode the JSON data
//            jsonData = URLDecoder.decode(jsonData, "UTF-8");
//
//            // Parse the JSON data to JSONObject
//            JSONObject jsonObject; jsonObject = new JSONObject(jsonData);
//
//            // Extract the listNpp array
//            JSONArray listNppArray = jsonObject.getJSONArray("listNpp");
//
//            // Loop through the array
//            for (int i = 0; i < listNppArray.length(); i++) {
//                String npp = listNppArray.getString(i);
//                // Process each NPP value
//                System.out.println("NPP: " + npp);
//            }
//
//            // You can now use this data as required
//        } catch (JSONException e) {
//            // Handle JSON parsing error
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON format");
//        }
//        incompatible types: string cannot be converted to map
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
        
        Map param = request.getParameterMap();
        PermissionModel permission = new PermissionModel();
        
        
        if(param.containsKey("changeRole")){
            
            String role = request.getParameter("role_id");
            
            permission.setNpp(request.getParameter("npp"));
            permission.setUserUpdate(request.getParameter("usr_update"));
            
            try{
                
                if(role.equals("US")){
                    if(permission.changeRoleToStaff()){
                        response.getWriter().write("akses US berhasil ditambahakan");
                    }else{
                        response.getWriter().write("akses US gagal ditambahakan");
                    }
                }else if(role.equals("UL")){
                    if(permission.changeRoleToLeader()){
                        response.getWriter().write("akses UL berhasil ditambahakan");
                    }else{
                        response.getWriter().write("akses UL gagal ditambahakan");
                    }
                }else if(role.equals("UA")){
                    if(permission.changeRoleToLeader()){
                        response.getWriter().write("akses UA berhasil ditambahakan");
                    }else{
                        response.getWriter().write("akses UA gagal ditambahakan");
                    }
                }
                
                
                
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }else if(param.containsKey("editAccess")){
            
            String role         = request.getParameter("role_id");
            String menu         = request.getParameter("menu_id");
            Boolean can_create  = Boolean.parseBoolean(request.getParameter("can_create"));
            Boolean can_read    = Boolean.parseBoolean(request.getParameter("can_read"));
            Boolean can_update  = Boolean.parseBoolean(request.getParameter("can_update"));
            Boolean can_delete  = Boolean.parseBoolean(request.getParameter("can_delete"));
            
            permission.setRole(role);
            permission.setMenuID(menu);
            permission.setNpp(request.getParameter("npp"));
            permission.setUserUpdate(request.getParameter("usr_update"));
            
            permission.setCanCreate(can_create);
            permission.setCanRead(can_read);
            permission.setCanUpdate(can_update);
            permission.setCanDelete(can_delete);
            
            try{
              
                if(permission.addAccessToRole()){
                    response.getWriter().write("akses fitur berhasil ditambahakan");
                }else{
                    response.getWriter().write("akses fitur gagal ditambahakan");
                }
            
            }catch (Exception ex) {
                System.out.println(ex.getMessage());
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
           
        }else if(param.containsKey("findUsers")){
            // Get the "npps" parameter from the request
            PrintWriter out = response.getWriter();

            // Get the npp parameter values
            String[] ids = request.getParameterValues("npp");

            // Initialize a JSONArray to hold the combined results
            JSONArray combinedResults = new JSONArray();

            try {
                // Loop through each npp value
                for (String npp : ids) {
                    // Call the findUsersFromPermission method
                    JSONArray result = permission.findUsersFromPermission(npp);

                    // Add each result from the current query to the combinedResults array
                    for (int i = 0; i < result.size(); i++) {
                        combinedResults.add(result.get(i));
                    }
                }

                // Output the combined results as the response
                out.write(combinedResults.toJSONString());

            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                out.write("{\"error\": \"An error occurred while processing the request.\"}");
            } finally {
                out.close();
            }

          
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
