/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tender.model.Palette;
import org.json.JSONArray;
import org.json.JSONObject;
import tender.model.RestaurantRelations;

/**
 *
 * @author marlon
 */
public class tender extends HttpServlet {

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
            /*request.setAttribute("address", request.getSession(false).getAttribute("personAddress").toString());
            request.setAttribute("city", request.getSession(false).getAttribute("personCity").toString());*/
            request.getRequestDispatcher("restaurantSelection.jsp").forward(request, response);
        } catch (Exception e) {

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
        //processRequest(request, response);
        try (PrintWriter out = response.getWriter()) {
            //String pk = request.getSession(false).getAttribute("personPK").toString();
            String pk = "2";

            Palette myPalette = new Palette();

            JSONObject json = new JSONObject();

            // put an "array"
            JSONArray arr = new JSONArray();
            for (Object like : myPalette.getLikedFood(pk)) {
                arr.put(like);
            }
            json.put("liked", arr);

            arr = new JSONArray();
            for (Object favourite : new RestaurantRelations().getFavourites(pk)) {
                arr.put(favourite);
            }
            json.put("favourites", arr);

            arr = new JSONArray();
            int i = 0;
            for (Object history : new RestaurantRelations().getHistory(pk)) {
                if (i < 5) {
                    arr.put(history);
                    break;
                }
                i++;
            }
            json.put("history", arr);
            // finally output the json string		
            out.print(json.toString());
            //out.println(myPalette.getFoodPreference("2")+"\n");
            /*out.println(myPalette.getLikedFood("2")+"\n");
            out.println(myPalette.getDislikedFood("2")+"\n");*/

        } catch (Exception e) {

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
