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
import tender.model.groups;
import tender.model.query;
import tender.model.user;

/**
 *
 * @author marlon
 */
public class search extends HttpServlet {

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
            String query = request.getParameter("friendToAdd");
            String[] parseQuery = query.split(" ");
            boolean add = true;
            String pk = request.getSession(false).getAttribute("personPK").toString();
            query search = new query();
            ArrayList<user> results = new ArrayList<>();
            user User = new user();
            HashMap conditions = new HashMap();
            for (int i = 0; i < parseQuery.length; i++) {
                parseQuery[i] = String.valueOf(parseQuery[i].charAt(0)).toUpperCase() + parseQuery[i].substring(1);
                out.println(parseQuery[i]);
            }

            for (int i = 0; i < 3; i++) {
                for (String info : parseQuery) {
                    conditions.clear();
                    switch (i) {
                        case 0:
                            conditions.put("firstname", info);
                            break;
                        case 1:
                            conditions.put("lastname", info);
                            break;
                        case 2:
                            conditions.put("email", info);
                            break;
                    }
                    //out.println("<br>"+search.getManyRows2("person", "pk", conditions)+"<br>");
                    for (Object data : search.getManyRows("person", "pk", conditions)) {
                        int num = Integer.parseInt(data.toString());

                        User = new user();
                        User.user(num);
                        for (user person : results) {
                            if (Integer.parseInt(person.getPk()) == Integer.parseInt(data.toString())) {
                                add = false;
                            }
                        }
                        if (add) {
                            results.add(User);
                        }
                        add = true;
                    }
                }
            }
            /*for (user info : results) {
                out.println(info.getString());
            }*/
            //out.println("done");
            request.setAttribute("users", results);

            request.setAttribute("groups", new groups().getGroup(pk));
            request.getRequestDispatcher("searchresults.jsp").forward(request, response);
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
