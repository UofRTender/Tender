/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author marlon
 */
public class RestaurantRelations {

    public ArrayList getFavourites(String pk) {
        ArrayList favourites = new ArrayList();
        query select = new query();
        HashMap id = new HashMap();
        id.put("user_id", pk);

        for (Object location : select.getManyRows("favourites", "restaurant_pk", id)) {
            favourites.add(location);
        }
        return favourites;
    }
    
    public ArrayList getHistory(String pk){
        ArrayList history = new ArrayList();
        query select = new query();
        HashMap id = new HashMap();
        id.put("user_id", pk);

        for (Object location : select.getManyRows("history", "restaurant_pk", id)) {
            history.add(location);
        }
        return history;
    }
}
