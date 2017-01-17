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
import tender.model.friends;
import tender.model.query;
import tender.model.user;

/**
 *
 * @author marlon
 */
public class FriendsList extends HttpServlet {

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

        /* TODO output your page here. You may use following sample code. */
        friends newFriend = new friends();
        String pk = request.getSession(false).getAttribute("personPK").toString();
        String friendPk = request.getParameter("pendingID");
        String accepted = request.getParameter("accept");

        if (accepted != null) {
            newFriend.acceptRequest(Integer.parseInt(pk), Integer.parseInt(friendPk));
        } else {
            newFriend.rejectRequest(Integer.parseInt(pk), Integer.parseInt(friendPk));
        }

        doGet(request, response);

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
        friends friend = new friends();
        ArrayList<user> pendingRequests;
        ArrayList<user> confirmedFriends;
        int pk = Integer.parseInt(request.getSession(false).getAttribute("personPK").toString());

        pendingRequests = friend.getPendingFriends(pk);
        confirmedFriends = friend.getConfirmedFriends(pk);
        if (pendingRequests.size() > 0) {
            request.setAttribute("pending", pendingRequests);
        }
        if (confirmedFriends.size() > 0) {
            request.setAttribute("confirmed", confirmedFriends);
        }

        request.getRequestDispatcher("friendsList.jsp").forward(request, response);

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
