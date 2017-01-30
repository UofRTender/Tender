/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tender.model.friends;
import tender.model.groups;
import tender.model.user;
import tender.model.user;

/**
 *
 * @author marlon
 */
public class createGroup extends HttpServlet {

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
            out.println("<title>Servlet createGroup</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createGroup at " + request.getContextPath() + "</h1>");
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
        if (request.getSession(false).getAttribute("personPK") == null) {
            response.sendRedirect("notLoggedIn");
        } else {
            String[] friends = request.getParameterValues("friend");
            String groupName = request.getParameter("group_name");
            String pk = request.getSession(false).getAttribute("personPK").toString();

            if (groupName == null || groupName.equals("")) {
                request.setAttribute("error", "please provide a name for your group");
                doPost(request, response);
            }
            if (friends == null) {
                friends = new String[0];
            }
            groups newGroup = new groups();
            newGroup.makeGroup(groupName, pk, friends);
            response.sendRedirect("myGroups");
        }
        /*groups newGroup = new groups();
            newGroup.makeGroup(groupName, pk, friends);
            request.getRequestDispatcher("myGroups.jsp").include(request, response);*/
        //response.sendRedirect("myGroups");
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
        try (PrintWriter out = response.getWriter()) {
            if (request.getSession(false).getAttribute("personPK") == null) {
                response.sendRedirect("notLoggedIn");
            } else {
                String pk = request.getSession(false).getAttribute("personPK").toString();
                friends friend = new friends();
                ArrayList<user> confirmedFriends;
                confirmedFriends = friend.getConfirmedFriends(Integer.parseInt(pk));

                if (confirmedFriends.size() > 0) {
                    request.setAttribute("confirmed", confirmedFriends);
                }
                request.getRequestDispatcher("createGroup.jsp").forward(request, response);
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
