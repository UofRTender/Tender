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
import tender.model.groups;
import tender.model.user;

/**
 *
 * @author marlon
 */
public class viewGroup extends HttpServlet {

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
            out.println("<title>Servlet viewGroup</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet viewGroup at " + request.getContextPath() + "</h1>");
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
        groups members = new groups();
        try (PrintWriter out = response.getWriter()) {
            if (request.getSession(false).getAttribute("personPK") == null) {
                response.sendRedirect("notLoggedIn");
            } else if (!members.isMember(request.getParameter("id"), request.getSession(false).getAttribute("personPK").toString())) {
                out.println("youre not a member of this group");
            } else {

                String id = request.getParameter("id");

                user user;
                ArrayList<user> groupMembers = new ArrayList<>();

                for (Object groupMember : members.getGroupMembers(id)) {
                    user = new user();
                    user.user(Integer.parseInt(groupMember.toString()));
                    groupMembers.add(user);
                }
                String pk = request.getSession(false).getAttribute("personPK").toString();

                request.setAttribute("pk", id);
                request.setAttribute("groupName", members.getName(id));
                request.setAttribute("members", groupMembers);
                request.setAttribute("groups", new groups().getGroup(pk));
                request.getRequestDispatcher("viewGroup.jsp").forward(request, response);

                
                processRequest(request, response);
            }
        }
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
        if (request.getSession(false).getAttribute("personPK") == null) {
            response.sendRedirect("notLoggedIn");
        } else {
            try (PrintWriter out = response.getWriter()) {
                String pk = request.getSession(false).getAttribute("personPK").toString();
                groups members = new groups();
                members.leaveGroup(request.getParameter("gpk"), pk);
                response.sendRedirect("myGroups");
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
