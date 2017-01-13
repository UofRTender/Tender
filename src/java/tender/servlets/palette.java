/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tender.model.query;

/**
 *
 * @author marlon
 */
public class palette extends HttpServlet {

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
        query update = new query();
        //ArrayList foods=new ArrayList();
        HashMap id = new HashMap();
        id.put("user_id", request.getSession(false).getAttribute("personPK").toString());

        try {
            for (Object foods : update.getManyRows("palette", "foodtype", id)) {
                id.put("foodtype", foods);
                update.update("palette", "preference", id, request.getParameter(foods.toString()));
                id.clear();
                id.put("user_id", request.getSession(false).getAttribute("personPK").toString());
            }
        }catch(Exception e){
            
        }
        
        request.getRequestDispatcher("profile.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        query select = new query();
        HashMap id = new HashMap();
        id.put("user_id", request.getSession(false).getAttribute("personPK").toString());

        request.setAttribute("foodType", select.getManyRows("palette", "foodtype", id));
        request.setAttribute("preference", select.getManyRows("palette", "preference", id));

        request.getRequestDispatcher("palette.jsp").forward(request, response);
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
        processRequest(request, response);
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