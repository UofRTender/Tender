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
import tender.model.query;
import tender.model.user;

/**
 *
 * @author marlon
 */
public class editGroup extends HttpServlet {

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
            out.println("<title>Servlet editGroup</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editGroup at " + request.getContextPath() + "</h1>");
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
            String newName = request.getParameter("group_name");
            String[] friends = request.getParameterValues("friend");
            String pk = request.getSession(false).getAttribute("personPK").toString();
            String groupPK = request.getParameter("gpk");
            groups updateGroup = new groups();

            try (PrintWriter out = response.getWriter()) {
                if (newName == null || newName.equals("")) {
                    if (friends == null) {
                        //nothing
                        out.println("nothing");
                        /*request.setAttribute("error", "group updated");
                    doPost(request, response);*/
                    } else {
                        for (String friend : friends) {
                            updateGroup.addMember(groupPK, friend);
                        }
                        request.setAttribute("error", "group name and group member changed");
                        response.sendRedirect("myGroups");
                    }
                } else if (friends == null) {
                    //edit name
                    updateGroup.updateName(groupPK, newName);
                    request.setAttribute("error", "group name updated");
                    response.sendRedirect("myGroups");
                } else {
                    //edit both
                    updateGroup.updateName(groupPK, newName);
                    for (String friend : friends) {
                        updateGroup.addMember(groupPK, friend);
                    }
                    request.setAttribute("error", "group name and group member changed");
                    response.sendRedirect("myGroups");
                }
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
            PrintWriter out = response.getWriter();
            String gpk = request.getParameter("gpk");
            ArrayList<user> groupMembers = new ArrayList<>();
            groups members = new groups();
            ArrayList<user> possibleMembers = new ArrayList<>();
            String pk = request.getSession(false).getAttribute("personPK").toString();

            for (Object groupMember : members.getGroupMembers(gpk)) {
                user user = new user();
                user.user(Integer.parseInt(groupMember.toString()));
                groupMembers.add(user);
            }

            friends friend = new friends();
            ArrayList<user> confirmedFriends;
            confirmedFriends = friend.getConfirmedFriends(Integer.parseInt(pk));

            if (confirmedFriends.size() > 0) {
                for (user conf : confirmedFriends) {
                    //out.println("confirmed friend " + conf.getPk());
                        if (!members.isMember(gpk, conf.getPk())) {
                            //out.println("possilbe " + conf.getPk());
                            possibleMembers.add(conf);
                        }
                    
                }
            }

            request.setAttribute("confirmed", possibleMembers);
            request.setAttribute("gpk", gpk);
            request.setAttribute("name", new groups().getName(gpk));
            request.setAttribute("groups", new groups().getGroup(pk));
            request.getRequestDispatcher("editgroup.jsp").forward(request, response);
            //processRequest(request, response);
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
