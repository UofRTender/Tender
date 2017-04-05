/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tender.model.friends;
import tender.model.groups;
import tender.model.query;
import tender.model.user;

/**
 *
 * @author marlon
 */
public class MyProfile extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        
        if (request.getParameterMap().containsKey("friendToAdd") && (request.getParameter("friendToAdd") != null || !request.getParameter("friendToAdd").equals(""))) {
            request.setAttribute("test", session);
            String pk = request.getSession(false).getAttribute("personPK").toString();
            String remotePK=request.getParameter("friendToAdd");
            user dude = new user();
            dude.user(Integer.parseInt(remotePK));
            
            friends requested=new friends(); 
            requested.friends(Integer.parseInt(pk), Integer.parseInt(remotePK));
            
            request.setAttribute("user", dude);
            if (!pk.equals(remotePK) && requested.showButton()) {
                request.setAttribute("addFriend", remotePK);
            }
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else {
            try (PrintWriter out = response.getWriter()) {
                if (session.getAttribute("personPK") == null) {
                    response.sendRedirect("notLoggedIn");
                } else {
                    HashMap info = new HashMap();
                    request.setAttribute("test", session);
                    String pk = request.getSession(false).getAttribute("personPK").toString();
                    
                    //groups group=request.getSession(false).getAttribute("groups");
                    //out.println(group);
                    user dude = new user();
                    dude.user(Integer.parseInt(pk));
                    info.put("pk", pk);

                    request.setAttribute("user", dude);
                    //request.setAttribute("groups", pk);
                    request.setAttribute("groups", new groups().getGroup(pk));
                    request.getRequestDispatcher("profile.jsp").forward(request, response);
                }
            }
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
        processRequest(request, response);
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
