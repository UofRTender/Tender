/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static tender.model.hash.hexEncode;
import tender.model.query;

/**
 *
 * @author marlon
 */
public class login extends HttpServlet {

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        query data = new query();
        HashMap info = new HashMap();
        HttpSession session = request.getSession(false);
        try (PrintWriter out = response.getWriter()) {
            try {
                if (email == null || password == null) {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    MessageDigest sha = MessageDigest.getInstance("SHA-1");
                    byte[] hashOne = sha.digest(password.getBytes());
                    String modPass = hexEncode(hashOne);

                    info.put("email", email);
                    info.put("password", modPass);

                    if (data.exists("person", info)) {
                        request.getSession().invalidate();
                        session = request.getSession();
                        session.setAttribute("personPK", data.getValue("person", "pk", info));
                        info.clear();
                        info.put("pk", session.getAttribute("personPK"));
                        session.setAttribute("personAddress", data.getValue("location", "address", info));
                        session.setAttribute("personCity", data.getValue("location", "city", info));
                        response.sendRedirect("profile");
                        //request.getRequestDispatcher("/profile").forward(request, response);
                    } else {
                        out.println("login unsuccessful");
                    }
                }

            } catch (Exception e) {
                out.println(e);
            }
        }
        //}
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
        HttpSession session = request.getSession(false);
        //if (session.getAttribute("personPK") != null || session.getAttribute("personPK") != "") {
        try (PrintWriter out = response.getWriter()) {
            if (session == null || session.getAttribute("personPK")=="null" || session.getAttribute("personPK")==null) {
                processRequest(request, response);
            } else {
                response.sendRedirect("profile");
            }

        }
        //}

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
