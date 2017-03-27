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
import org.json.JSONArray;
import org.json.JSONObject;
import tender.model.Favourites;
import tender.model.History;
import tender.model.Palette;
import tender.model.RestaurantRelations;
import tender.model.groups;

/**
 *
 * @author marlon
 */
public class groupTender extends HttpServlet {

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
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            groups group = new groups();
            ArrayList groupTypeHistory = new ArrayList();
            JSONObject tender = new JSONObject();
            ArrayList<String[]> paletteScore = new ArrayList<>();
            JSONArray arr;

            String gpk = "4";//request.getParameter("gpk");
            ArrayList groupMembers = group.getGroupMembers(gpk);

            boolean increment = false;
            int index = 0;
            for (Object pk : groupMembers) {
                for (Object foodtype : new Palette().getLikedFood(pk.toString())) {
                    for (int i = 0; i < paletteScore.size(); i++) {
                        if (paletteScore.get(i)[0].equals(foodtype.toString())) {
                            index = i;
                            increment = true;
                        }
                    }
                    if (!increment) {
                        String[] temp = {foodtype.toString(), "1"};
                        paletteScore.add(temp);
                    } else {
                        increment = false;
                        paletteScore.get(index)[1] = Integer.toString(Integer.parseInt(paletteScore.get(index)[1]) + 1);
                    }
                }
            }

            //RETURNS THE ENTIRE GROUPS TYPE HISTORY
            for (Object type : new History("0").getTypeGroupHistory(gpk)) {
                groupTypeHistory.add(type.toString());
            }

            if (groupTypeHistory.isEmpty()) {
                ArrayList<String[]> max = new ArrayList<>();
                max.add(paletteScore.get(0));
                for (String[] checkMax : paletteScore) {
                    if (Integer.parseInt(max.get(0)[1]) < Integer.parseInt(checkMax[1])) {
                        max.add(0, checkMax);
                    }
                }
                tender.put("palette", max.get(0)[0]);
            } else {
                for (int k = 0; k < groupTypeHistory.size(); k++) {
                    Object type = groupTypeHistory.get(k);
                    for (int i = 0; i < paletteScore.size(); i++) {
                        if (type.equals(paletteScore.get(i)[0])) {
                            double score = Integer.parseInt(paletteScore.get(i)[1]);
                            double newScore = k + 1;
                            paletteScore.get(i)[1] = Double.toString(score * newScore / 10);
                        }
                    }
                }
            }
            
            ArrayList<String[]> paletteReturn = new ArrayList<>();
            paletteReturn.add(paletteScore.get(0));

            for (String[] max : paletteScore) {

                if (Integer.parseInt(paletteReturn.get(0)[1]) < Double.parseDouble(max[1])) {
                    paletteReturn.clear();
                    paletteReturn.add(max);
                }
            }

            tender.put("palette", paletteReturn.get(0)[0]);
            
            //ADDS INDIVIDUAL HISTORIES TO JSON
            arr = new JSONArray();
            for (Object pk : groupMembers) {
                for (Object history : new RestaurantRelations().getHistory(pk.toString())) {
                    arr.put(history);
                }
            }
            tender.put("indivHistory", arr);

            //ADDS GROUP HISTORY TO JSON
            arr = new JSONArray();
            for (Object history : new History("0").getGroupHistory(gpk)) {
                arr.put(history);
            }
            tender.put("groupHistory", arr);

            //ADDS INDIVIDUAL FAVOURITES TO JSON
            arr = new JSONArray();
            for (Object pk : groupMembers) {
                for (Object favourite : new RestaurantRelations().getFavourites(pk.toString())) {
                    arr.put(favourite);
                }
            }
            tender.put("favourites", arr);
             
            out.println(tender.toString());
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
