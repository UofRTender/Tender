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
import tender.model.Palette;
import org.json.JSONArray;
import org.json.JSONObject;
import tender.model.Favourites;
import tender.model.RestaurantRelations;
import tender.model.query;

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
        //response.setContentType("text/html;charset=UTF-8");
        //processRequest(request, response);
        try (PrintWriter out = response.getWriter()) {
            String pk = request.getSession(false).getAttribute("personPK").toString();
            boolean check = true;
            query query = new query();
            Palette myPalette = new Palette();
            ArrayList likes = myPalette.getLikedFood(pk);
            ArrayList<ArrayList> likeCount = new ArrayList<>();
            ArrayList<ArrayList> potentialPalette = new ArrayList<>();
            ArrayList mostRecent = new ArrayList();
            JSONObject tender = new JSONObject();
            JSONArray arr = new JSONArray();
            HashMap temp = new HashMap();

            arr = new JSONArray();
            for (Object favourite : new RestaurantRelations().getFavourites(pk)) {
                arr.put(favourite);
            }
            tender.put("favourites", arr);
            
            arr = new JSONArray();
            int k = 0;
            for (Object history : new RestaurantRelations().getHistory(pk)) {
                if (k < 5) {
                    arr.put(history);
                } else {
                    break;
                }
                k++;
            }

            tender.put("history", arr);
            //out.println(likes+"<br>");
            
            for (Object like : likes) {
                temp.clear();
                temp.put("type", like);
                temp.put("user_id", pk);
                likeCount.add(query.getManyRows("history", "pk", temp));
            }
            
            if (likeCount.isEmpty()) {
                tender.put("palette", likes.get((int) Math.floor(Math.random() * likes.size())));
                out.println(tender);
            } else {
                //out.println(likeCount+"<br>");
                temp.clear();
                for (int i = 0; i < likeCount.size(); i++) {
                    if (likeCount.get(i).isEmpty()) {
                        tender.put("palette", likes.get(i));
                        check = false;
                        out.println(tender);
                        break;
                    } else if (potentialPalette.isEmpty() || likeCount.get(i).size() < potentialPalette.get(0).size()) {
                        potentialPalette.clear();
                        potentialPalette.add(likeCount.get(i));
                    } else if (likeCount.get(i).size() == potentialPalette.get(0).size()) {
                        potentialPalette.add(likeCount.get(i));
                    }
                }

                //out.println(potentialPalette+"<br>");
                for (ArrayList candidates : potentialPalette) {
                    int largest = 0;
                    for (Object candidate : candidates) {
                        int challenger = Integer.parseInt(candidate.toString());
                        if (challenger > largest) {
                            largest = challenger;
                        }
                    }
                    mostRecent.add(largest);
                }

                int winner = 0;
                //out.println(mostRecent+"<br>");
                for (Object small : mostRecent) {
                    //out.println(small);
                    if (Integer.parseInt(small.toString()) < winner || winner == 0) {
                        winner = Integer.parseInt(small.toString());
                    }
                }
                //out.println("winner "+Integer.toString(winner)+"<br>");
                temp.clear();

                temp.put("pk", Integer.toString(winner));
                if (check) {
                    tender.put("palette", query.getValue("history", "type", temp));
                    out.println(tender);
                }
            }
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
