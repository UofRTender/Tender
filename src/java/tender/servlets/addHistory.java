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
            String pk = request.getSession(false).getAttribute("personPK").toString();

            String job = request.getParameter("job");
            String table = request.getParameter("table");
            String groupId = request.getParameter("id");

            query newHistory = new query();
            HashMap info = new HashMap();

            if (table.equals("history")) {
                info.put("user_id", pk);
            } else {
                info.put("group_pk", groupId);
            }

            switch (job) {
                case "add": {
                    String id = request.getParameter("restaurant");
                    String name = request.getParameter("name");
                    String type = request.getParameter("palette");
                    info.put("restaurant_pk", id);
                    newHistory.delete("temphistory", info);
                    info.put("timestamp", "now");
                    info.put("type", type);
                    info.put("name", name);
                    newHistory.insert(table, info);
                    break;
                }
                case "check":
                    JSONObject json = new JSONObject();
                    if (table.equals("temphistory")) {
                        String id = newHistory.getValue(table, "restaurant_pk", info);
                        if (!request.getParameter("oldid").equals(id) && !id.equals("")) {
                            json.put("id", id);
                            json.put("old", id);
                            json.put("load", "true");
                        } else {
                            json.put("old", id);
                            json.put("load", "false");
                        }
                        out.println(json.toString());
                    } else {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date currentDate = new Date();
                        format.format(currentDate);
                        json.put("info", info.toString());
                        if (newHistory.exists(table, info)) {
                            ArrayList history = newHistory.getManyRows(table, "timestamp", info);
                            String selected = history.get(history.size() - 1).toString();//last visited
                            Date lastVisited = format.parse(selected);

                            lastVisited.setDate(lastVisited.getDate() + 1);

                            //json.put("newdate", lastVisited.toString());
                            //out.println(lastVisited);
                            json.put("current", currentDate.toString());
                            json.put("last", lastVisited);
                            if (currentDate.after(lastVisited)) {
                                json.put("new", "true");
                                out.println(json.toString());//new place
                            } else {
                                info.put("timestamp", selected);
                                String lastPlace = newHistory.getValue(table, "restaurant_pk", info);
                                json.put("new", "false");
                                json.put("id", lastPlace);
                                json.put("palette", newHistory.getValue(table, "type", info));
                                out.println(json.toString());//no new place
                            }
                        } else {
                            json.put("new", "true");
                            out.println(json.toString());//new place
                        }
                    }
                    break;
                default: {
                    newHistory.delete("temphistory", info);
                    String id = request.getParameter("restaurant");
                    info.put("restaurant_pk", id);
                    newHistory.insert(table, info);
                    break;
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
