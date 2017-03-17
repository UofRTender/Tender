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
public class Favourites {

    private String pk;
    private query query;
    HashMap conditions = new HashMap();

    public Favourites(String pk) {
        this.query = new query();
        this.pk = pk;
    }

    public HashMap getFavourites() {
        conditions.clear();
        conditions.put("user_id", pk);
        HashMap data = new HashMap();
        //data.put("test",query.getManyRows("favourites", "pk", conditions));
        for (Object favourite : query.getManyRows("favourites", "pk", conditions)) {
            conditions.clear();
            conditions.put("pk", favourite.toString());
            //data.put("pk", conditions);
            data.put("name", query.getValue("favourites", "name", conditions));
        }
        return data;
    }
}
