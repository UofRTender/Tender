/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import tender.model.query;

/**
 *
 * @author marlon
 */
@WebServlet(name = "addHistory", urlPatterns = {"/addHistory"})
public class addHistory extends HttpServlet {

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
            out.println("<title>Servlet addHistory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addHistory at " + request.getContextPath() + "</h1>");
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
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            String pk = "2";
            String job = request.getParameter("job");

            query newHistory = new query();
            HashMap info = new HashMap();
            info.put("user_id", pk);

            if (job.equals("add")) {
                String id = request.getParameter("restaurant");
                info.put("restaurant_pk", id);
                info.put("timestamp", "now");
                newHistory.insert("history", info);
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date currentDate = new Date();
                format.format(currentDate);

                ArrayList history = newHistory.getManyRows("history", "timestamp", info);
                String selected = history.get(history.size() - 1).toString();//last visited 
                
                Date lastVisited = format.parse(selected);
                lastVisited.setDate(lastVisited.getDate() + 1);
                //out.println(lastVisited);
                if (currentDate.after(lastVisited)) {
                    out.println("true");//new place
                } else {
                    info.put("timestamp", selected);
                    String lastPlace = newHistory.getValue("history", "restaurant_pk", info);
                    JSONObject json = new JSONObject();
                    json.put("id", lastPlace);
                    out.println(json.toString());//no new place
                }
            }
        } catch (Exception e) {
            
        }
        //processRequest(request, response);
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
